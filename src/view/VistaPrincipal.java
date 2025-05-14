package view;

import javax.swing.table.DefaultTableModel;
import session.UsuarioActivo;
import model.Usuario;
import java.util.List;
import controller.ReporteController;
import java.util.Date;
import javax.swing.JOptionPane;
import exception.ExcepcionVista;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.SwingUtilities;

public class VistaPrincipal extends javax.swing.JFrame {

    private JCalendar calendario;  // <-- guardamos la referencia

    public VistaPrincipal() {
        initComponents();

        setTitle("Agenda de Eventos");
        setLocationRelativeTo(null);

        // Panel de botones arriba del calendario
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAgenda = new JButton("Agenda");
        btnAgenda.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAgenda.setBackground(new Color(96, 187, 144));
        btnAgenda.setForeground(Color.WHITE);
        btnAgenda.setFocusPainted(false);

        JButton btnReportes = new JButton("Reportes");
        btnReportes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnReportes.setBackground(new Color(135, 178, 158));
        btnReportes.setForeground(Color.WHITE);
        btnReportes.setFocusPainted(false);

        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.add(btnAgenda);
        panelBotones.add(btnReportes);

        // Calendario personalizado
        calendario = new JCalendar();
        calendario.setWeekOfYearVisible(false);
        calendario.setDecorationBackgroundColor(new Color(135, 178, 158, 60));
        calendario.setSundayForeground(Color.RED);
        calendario.setTodayButtonVisible(true);
        calendario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        calendario.getDayChooser().setFont(new Font("Segoe UI", Font.BOLD, 16));
        calendario.setBackground(new Color(255, 255, 255));
        calendario.getDayChooser().setDecorationBackgroundColor(new Color(96, 187, 144));

        // cuando cambie el día, mes o año, vuelvo a resaltar
        calendario.addPropertyChangeListener("calendar", evt -> {
            new Thread(this::highlightEventDays).start();
        });

        // Botón Agendar
        JButton btnAgendar = new JButton("Agendar evento");
        btnAgendar.setFocusPainted(false);
        btnAgendar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAgendar.setBackground(new Color(96, 187, 144));
        btnAgendar.setForeground(Color.WHITE);
        btnAgendar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnAgendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgendar.setOpaque(true);

        // Efecto hover para el botón Agendar
        btnAgendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgendar.setBackground(new Color(135, 178, 158));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgendar.setBackground(new Color(96, 187, 144));
            }
        });

        // Acción del botón Agendar
        btnAgendar.addActionListener(e -> {
            try {
                // 1) Tomar fecha del calendario
                java.util.Date selDate = calendario.getDate();
                java.sql.Date fecha = new java.sql.Date(selDate.getTime());

                // 2) Mostrar diálogo
                EventoDialog dlg = new EventoDialog(this);
                dlg.setVisible(true);
                if (!dlg.isConfirmado()) return;

                // 3) Construir evento
                String idUsu = session.UsuarioActivo.getUsuarioActual().getIdUsu();
                model.Evento ev = new model.Evento(
                    idUsu,
                    fecha,
                    dlg.getHora(),
                    dlg.getTitulo(),
                    dlg.getDescripcion()
                );

                // 4) Guardar y refrescar tabla
                controller.EventoController ec = new controller.EventoController();
                if (ec.crearEvento(ev)) {
                    java.util.List<model.Evento> lista = ec.listarEventos(idUsu);
                    session.UsuarioActivo.actualizarEventos(lista);
                    cargarEventosEnTabla(lista);
                    new Thread(() -> highlightEventDays()).start(); // vuelvo a resaltar
                    JOptionPane.showMessageDialog(this, "✅ Evento creado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "❌ Error al insertar",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                    "❌ Error:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel central para calendario y botón
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(245, 247, 250));
        JPanel panelCalendario = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 220, 230, 180));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);
            }
        };
        panelCalendario.setOpaque(false);
        panelCalendario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCalendario.add(calendario, BorderLayout.CENTER);
        panelCalendario.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        panelCalendario.add(btnAgendar, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCentral.add(panelCalendario, gbc);

        // Cambia y agrega los componentes a jPanel3 (solo pestaña de eventos)
        jPanel3.setLayout(new BorderLayout());
        jPanel3.removeAll();
        jPanel3.add(panelCentral, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();

        // Acción del botón Reportes
        btnReportes.addActionListener(e -> {
            jTabbedPane1.setSelectedIndex(1); // Va a la pestaña de Reportes
        });

        // Acción del botón Agenda
        btnAgenda.addActionListener(e -> {
            jTabbedPane1.setSelectedIndex(0); // Va a la pestaña de Agenda/Eventos
        });

        // Mostrar información del usuario actual
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        jCbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(meses));
        int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        jSpnAnio.setValue(anioActual);
        jDchReporteDia.setDate(new java.util.Date());

        if (UsuarioActivo.haySesionActiva()) {
            Usuario usuario = UsuarioActivo.getUsuarioActual();
            this.jLblUsuario.setText("Usuario - " + usuario.getNomUsu() + " " + usuario.getApeUsu());

            cargarEventosEnTabla(UsuarioActivo.getEventosDelUsuario());
            this.jTblEventos.getColumnModel().getColumn(0).setPreferredWidth(30);
            this.jTblEventos.getColumnModel().getColumn(1).setPreferredWidth(20);
            new Thread(() -> highlightEventDays()).start();  // resaltar los días con evento
        } else {
            this.jLblUsuario.setText("Usuario no identificado");
        }
    }

    private void cargarEventosEnTabla(List<model.Evento> eventos) {
        String[] columnas = {"Fecha", "Hora", "Título", "Descripción"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (model.Evento e : eventos) {
            Object[] fila = {
                e.getFecEve().toString(),
                e.getHorEve().toString(),
                e.getTitEve(),
                e.getDesEve()
            };
            modelo.addRow(fila);
        }

        jTblEventos.setModel(modelo);
    }

    /**
     * Recorre los días del mes que muestra el calendario y pinta
     * de verde aquellos que tengan un evento en UsuarioActivo.
     */
    private void highlightEventDays() {
        try {
            List<model.Evento> eventos = session.UsuarioActivo.getEventosDelUsuario();
            Calendar disp = Calendar.getInstance();
            disp.setTime(calendario.getDate());
            int mesDisp = disp.get(Calendar.MONTH), añoDisp = disp.get(Calendar.YEAR);

            Component[] comps = calendario.getDayChooser().getDayPanel().getComponents();
            // 1) Resetear fondo
            for (Component c : comps) {
                if (c instanceof JButton) {
                    SwingUtilities.invokeLater(() -> c.setBackground(Color.WHITE));
                }
            }
            // 2) Por cada evento, si coincide mes/año, colorear el día
            for (model.Evento ev : eventos) {
                Calendar ce = Calendar.getInstance();
                ce.setTime(ev.getFecEve());
                if (ce.get(Calendar.MONTH) == mesDisp && ce.get(Calendar.YEAR) == añoDisp) {
                    String diaStr = String.valueOf(ce.get(Calendar.DAY_OF_MONTH));
                    for (Component c : comps) {
                        if (c instanceof JButton && ((JButton)c).getText().equals(diaStr)) {
                            SwingUtilities.invokeLater(() ->
                                c.setBackground(new Color(96, 187, 144))
                            );
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblEventos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLblUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDchReporteDia = new com.toedter.calendar.JDateChooser();
        jCbxDias = new javax.swing.JComboBox<>();
        jSpnAnio = new javax.swing.JSpinner();
        jBtnReporteMes = new javax.swing.JButton();
        jBtnReporteDia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTblEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTblEventos);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Mis Eventos");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Cerrar Sesión");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(193, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eventos", jPanel3);

        jCbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBtnReporteMes.setText("Reporte por Mes");
        jBtnReporteMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteMesActionPerformed(evt);
            }
        });

        jBtnReporteDia.setText("Reporte por Día");
        jBtnReporteDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReporteDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jDchReporteDia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jCbxDias, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jSpnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jBtnReporteDia)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnReporteMes)
                            .addGap(30, 30, 30)))
                    .addGap(73, 73, 73)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(146, 146, 146)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCbxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDchReporteDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSpnAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnReporteMes)
                        .addComponent(jBtnReporteDia))
                    .addContainerGap(146, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Reportes", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnReporteMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteMesActionPerformed
        try {
            int mes = jCbxDias.getSelectedIndex() + 1;
            int anio = (int) jSpnAnio.getValue();

            ReporteController.generarPorMes(mes, anio);

        } catch (ExcepcionVista ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), ex.getTitulo(), JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "❌ Error inesperado al generar el reporte por mes.\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnReporteMesActionPerformed

    private void jBtnReporteDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReporteDiaActionPerformed
        try {
            Date fecha = jDchReporteDia.getDate();
            controller.ReporteController.generarPorDia(fecha);
        } catch (ExcepcionVista ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), ex.getTitulo(), JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "❌ Error inesperado al generar el reporte por mes.\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnReporteDiaActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // Cierra la sesión activa
        session.UsuarioActivo.cerrarSesion();

        // Regresa a la pantalla de login
        view.LoginUsuario login = new view.LoginUsuario();
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);

        // Cierra la ventana actual
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnReporteDia;
    private javax.swing.JButton jBtnReporteMes;
    private javax.swing.JComboBox<String> jCbxDias;
    private com.toedter.calendar.JDateChooser jDchReporteDia;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLblUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpnAnio;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTblEventos;
    // End of variables declaration//GEN-END:variables
}
