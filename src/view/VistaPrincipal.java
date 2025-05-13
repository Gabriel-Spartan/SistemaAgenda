package view;

import javax.swing.table.DefaultTableModel;
import session.UsuarioActivo;
import model.Usuario;
import java.util.List;
import controller.ReporteController;
import java.util.Date;
import javax.swing.JOptionPane;
import exception.ExcepcionVista;

public class VistaPrincipal extends javax.swing.JFrame {

    public VistaPrincipal() {
        initComponents();

        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        jCbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(meses));
        int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        jSpnAnio.setValue(anioActual);
        jDchReporteDia.setDate(new java.util.Date());

        // Mostrar información del usuario actual
        if (UsuarioActivo.haySesionActiva()) {
            Usuario usuario = UsuarioActivo.getUsuarioActual();
            this.jLblUsuario.setText("Usuario - " + usuario.getNomUsu() + " " + usuario.getApeUsu());

            cargarEventosEnTabla(UsuarioActivo.getEventosDelUsuario());
            this.jTblEventos.getColumnModel().getColumn(0).setPreferredWidth(30);
            this.jTblEventos.getColumnModel().getColumn(1).setPreferredWidth(20);
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
