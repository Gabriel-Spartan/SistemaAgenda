package view;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.sound.sampled.*;

import util.ConexionBD;

public class NotificadorTray {

    public static void main(String[] args) {
        System.out.println("[NotificadorTray] ✅ NotificadorTray iniciado...");
        if (!SystemTray.isSupported()) {
            System.out.println("[NotificadorTray] ❌ System tray no soportado.");
            return;
        }

        System.out.println("[NotificadorTray] Ejecutándose...");
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(
                    NotificadorTray.class.getResource("/resources/img/imageEve.jpg"));
            TrayIcon trayIcon = new TrayIcon(image, "Notificador Agenda");
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);

            Set<String> eventosNotificados = new HashSet<>();

            while (true) {
                System.out.println("[NotificadorTray] ⏳ Verificando usuario activo...");

                String cedula = leerCedulaDesdeArchivo();
                if (cedula == null || cedula.isEmpty()) {
                    System.out.println("[NotificadorTray] ⚠️ No hay sesión activa en session.txt");
                } else {
                    try (Connection conn = ConexionBD.conectar()) {
                        LocalDateTime ahora = LocalDateTime.now().withSecond(0).withNano(0);

                        // Notificación 5 minutos antes
                        LocalDateTime cincoAntes = ahora.plusMinutes(5);
                        notificarEventos(conn, cincoAntes, cedula, "-5min", trayIcon, eventosNotificados);

                        // Notificación exacta
                        notificarEventos(conn, ahora, cedula, "-exacta", trayIcon, eventosNotificados);
                    } catch (Exception ex) {
                        System.out.println("[NotificadorTray] ❌ Error al consultar eventos: " + ex.getMessage());
                    }
                }

                try {
                    Thread.sleep(60000 - (System.currentTimeMillis() % 60000));
                } catch (InterruptedException ie) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String leerCedulaDesdeArchivo() {
        try {
            Path path = Paths.get("session.txt");
            if (Files.exists(path)) {
                return new String(Files.readAllBytes(path), StandardCharsets.UTF_8).trim();
            }
        } catch (IOException e) {
            System.out.println("[NotificadorTray] ⚠️ Error al leer session.txt: " + e.getMessage());
        }
        return null;
    }

    private static void notificarEventos(Connection conn, LocalDateTime fechaHora, String cedula,
            String sufijo, TrayIcon trayIcon, Set<String> eventosNotificados)
            throws SQLException {
        String sql = "SELECT TIT_EVE, DES_EVE, FEC_EVE, HOR_EVE FROM eventos WHERE FEC_EVE = ? AND HOR_EVE = ? AND ID_USU_PER = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fechaHora.toLocalDate().toString());
            ps.setString(2, fechaHora.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:00")));
            ps.setString(3, cedula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String titulo = rs.getString("TIT_EVE");
                String desc = rs.getString("DES_EVE");
                String fec = rs.getString("FEC_EVE");
                String hor = rs.getString("HOR_EVE");
                String idEvento = fec + " " + hor + "-" + titulo + sufijo;
                if (!eventosNotificados.contains(idEvento)) {
                    reproducirSonidoYNotificacion(trayIcon, titulo, desc);
                    eventosNotificados.add(idEvento);
                }
            }
        }
    }

    private static void reproducirSonidoYNotificacion(TrayIcon trayIcon, String titulo, String desc) {
        final Clip[] clip = {null};
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    NotificadorTray.class.getResource("/resources/sounds/The-big-adventure-Google-Android-8-Ringtone.wav"));
            clip[0] = AudioSystem.getClip();
            clip[0].open(audioInputStream);
            clip[0].loop(Clip.LOOP_CONTINUOUSLY);
            clip[0].start();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
        }

        // Notificación bandeja (opcional, puede dejarse)
        trayIcon.displayMessage("⏰ Evento: " + titulo, desc, MessageType.INFO);

        // Ventana emergente real que sí espera al usuario
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    desc,
                    "⏰ Evento: " + titulo,
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            // Cuando el usuario cierre/acepte, se detiene el sonido
            if (clip[0] != null && clip[0].isRunning()) {
                clip[0].stop();
                clip[0].close();
            }
        });
    }

}
