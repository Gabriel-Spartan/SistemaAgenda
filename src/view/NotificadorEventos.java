package view;

import model.Evento;
import session.UsuarioActivo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class NotificadorEventos extends Thread {

    private boolean ejecutando = true;

    public void detener() {
        ejecutando = false;
    }

    @Override
    public void run() {
        esperarInicioDelMinuto();
        while (ejecutando) {
            try {
                List<Evento> eventos = UsuarioActivo.getEventosDelUsuario();
                if (eventos != null) {
                    LocalDateTime ahora = LocalDateTime.now().withSecond(0).withNano(0);
                    for (Evento evento : eventos) {
                        LocalDateTime fechaEvento = LocalDateTime.of(
                                evento.getFecEve().toLocalDate(),
                                evento.getHorEve().toLocalTime()
                        );

                        if (fechaEvento.equals(ahora)) {
                            mostrarMensajeConSonido("¡Ya es hora del evento!", evento.getTitEve(), evento.getDesEve());
                        } else if (fechaEvento.minusMinutes(5).equals(ahora)) {
                            mostrarMensajeConSonido("En 5 minutos empieza el evento:", evento.getTitEve(), evento.getDesEve());
                        }
                    }
                }
                Thread.sleep(60 * 1000); // Verifica cada minuto
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void esperarInicioDelMinuto() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime siguienteMinuto = ahora.plusMinutes(1).withSecond(0).withNano(0);
        long millisEspera = Duration.between(ahora, siguienteMinuto).toMillis();
        try {
            Thread.sleep(millisEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensajeConSonido(String titulo, String detalle, String descripcion) {
        SwingUtilities.invokeLater(() -> {
            Clip clip = null;
            ImageIcon icono = null;
            try {
                // Imagen personalizada
                java.net.URL url = getClass().getResource("/resources/img/imageEve.jpg");
                if (url != null) {
                    Image img = new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(img);
                }
            } catch (Exception e) { /* ignora */ }

            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        getClass().getResource("/resources/sounds/The-big-adventure-Google-Android-8-Ringtone.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch (Exception e) {
                Toolkit.getDefaultToolkit().beep();
            }

            // Panel personalizado
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel lblTitulo = new JLabel("<html><b>" + titulo + "</b></html>");
            lblTitulo.setForeground(new Color(255, 87, 34));
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
            JLabel lblEvento = new JLabel("<html><b>Título:</b> " + detalle + "<br><b>Descripción:</b> " + descripcion + "</html>");
            lblEvento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            panel.add(lblTitulo);
            panel.add(Box.createVerticalStrut(10));
            panel.add(lblEvento);

            JOptionPane.showMessageDialog(
                    null,
                    panel,
                    "⏰ Recordatorio de Evento",
                    JOptionPane.WARNING_MESSAGE,
                    icono
            );

            // Detener el sonido solo cuando se cierre el mensaje
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }
        });
    }
}