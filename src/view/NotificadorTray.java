package view;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.*;

import util.ConexionBD;

public class NotificadorTray {
    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray no soportado.");
            return;
        }
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("src/resources/img/imageEve.jpg");
            TrayIcon trayIcon = new TrayIcon(image, "Notificador Agenda");
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);

            // Para evitar notificar varias veces el mismo evento
            java.util.Set<String> eventosNotificados = new java.util.HashSet<>();
            while (true) {
                try (Connection conn = ConexionBD.conectar()) {
                    // Obtiene la fecha y hora actual
                    LocalDateTime ahora = LocalDateTime.now().withSecond(0).withNano(0);

                    // --- Notificación 5 minutos antes ---
                    LocalDateTime cincoAntes = ahora.plusMinutes(5);
                    String sql5min = "SELECT TIT_EVE, DES_EVE, FEC_EVE, HOR_EVE FROM eventos WHERE FEC_EVE = ? AND HOR_EVE = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql5min)) {
                        ps.setString(1, cincoAntes.toLocalDate().toString());
                        ps.setString(2, cincoAntes.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:00")));
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String titulo = rs.getString("TIT_EVE");
                            String desc = rs.getString("DES_EVE");
                            String fec = rs.getString("FEC_EVE");
                            String hor = rs.getString("HOR_EVE");
                            String idEvento = fec + " " + hor + "-" + titulo + "-5min";
                            if (!eventosNotificados.contains(idEvento)) {
                                reproducirSonidoYNotificacion(trayIcon, titulo, desc);
                                eventosNotificados.add(idEvento);
                            }
                        }
                    }

                    // --- Notificación a la hora exacta ---
                    String sqlExacta = "SELECT TIT_EVE, DES_EVE, FEC_EVE, HOR_EVE FROM eventos WHERE FEC_EVE = ? AND HOR_EVE = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sqlExacta)) {
                        ps.setString(1, ahora.toLocalDate().toString());
                        ps.setString(2, ahora.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:00")));
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String titulo = rs.getString("TIT_EVE");
                            String desc = rs.getString("DES_EVE");
                            String fec = rs.getString("FEC_EVE");
                            String hor = rs.getString("HOR_EVE");
                            String idEvento = fec + " " + hor + "-" + titulo + "-exacta";
                            if (!eventosNotificados.contains(idEvento)) {
                                reproducirSonidoYNotificacion(trayIcon, titulo, desc);
                                eventosNotificados.add(idEvento);
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error al consultar eventos: " + ex.getMessage());
                }
                // Espera hasta el siguiente minuto exacto
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
        // Mostrar notificación tipo tray y detener sonido al hacer clic en la notificación
        trayIcon.displayMessage("⏰ Evento: " + titulo, desc, MessageType.INFO);
        // Listener para detener el sonido al hacer clic en la notificación
        // Listener que se elimina a sí mismo tras el primer clic
        java.awt.event.ActionListener stopSoundListener = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (clip[0] != null && clip[0].isRunning()) {
                    clip[0].stop();
                    clip[0].close();
                }
                trayIcon.removeActionListener(this);
            }
        };
        trayIcon.addActionListener(stopSoundListener);
        // Como no hay evento de "cerrar" en displayMessage, el sonido solo se detendrá al hacer clic en la notificación
    }
}
