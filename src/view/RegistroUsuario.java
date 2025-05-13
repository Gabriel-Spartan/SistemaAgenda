package view;

import controller.RegistroController;
import javax.swing.JOptionPane;

public class RegistroUsuario extends javax.swing.JFrame {

    public RegistroUsuario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();
        jLblCedula = new javax.swing.JLabel();
        jLblNombre = new javax.swing.JLabel();
        jLblApellido = new javax.swing.JLabel();
        jLblContrasenia = new javax.swing.JLabel();
        jPwdContrasenia = new javax.swing.JPasswordField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jTxtCedula = new javax.swing.JTextField();
        jPwdConfirmaContrasenia = new javax.swing.JPasswordField();
        jBtnRegistrarle = new javax.swing.JButton();
        jBtnLogin = new javax.swing.JButton();
        jChkBoxVerContrasenia = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 315));

        jLblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLblTitulo.setText("REGÍSTRATE AHORA");

        jLblCedula.setText("Cédula");

        jLblNombre.setText("Nombre");

        jLblApellido.setText("Apellido");

        jLblContrasenia.setText("Contraseña");

        jTxtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtApellidoFocusLost(evt);
            }
        });

        jTxtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtNombreFocusLost(evt);
            }
        });

        jTxtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCedulaActionPerformed(evt);
            }
        });
        jTxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyTyped(evt);
            }
        });

        jBtnRegistrarle.setText("Registrarse");
        jBtnRegistrarle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarleActionPerformed(evt);
            }
        });

        jBtnLogin.setText("Login");

        jChkBoxVerContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChkBoxVerContraseniaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLblTitulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblContrasenia)
                            .addComponent(jLblApellido)
                            .addComponent(jLblNombre)
                            .addComponent(jLblCedula))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPwdContrasenia)
                            .addComponent(jTxtNombre)
                            .addComponent(jTxtApellido)
                            .addComponent(jTxtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jPwdConfirmaContrasenia, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jChkBoxVerContrasenia)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jBtnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnRegistrarle)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLblTitulo)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCedula)
                    .addComponent(jTxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblNombre)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblApellido)
                    .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblContrasenia)
                    .addComponent(jPwdContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jChkBoxVerContrasenia))
                .addGap(18, 18, 18)
                .addComponent(jPwdConfirmaContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnRegistrarle)
                    .addComponent(jBtnLogin))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCedulaActionPerformed

    }//GEN-LAST:event_jTxtCedulaActionPerformed

    private void jBtnRegistrarleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarleActionPerformed
        String cedula = jTxtCedula.getText().trim();
        String nombre = jTxtNombre.getText().trim();
        String apellido = jTxtApellido.getText().trim();
        String contrasenia = new String(jPwdContrasenia.getPassword()).trim();
        String confirma = new String(jPwdConfirmaContrasenia.getPassword()).trim();

        try {
            // Controlador
            RegistroController controller = new RegistroController();

            // Validación + creación
            model.Usuario u = new model.Usuario(cedula, nombre, apellido, contrasenia);
            String mensaje = controller.validarYRegistrar(u, confirma);

            JOptionPane.showMessageDialog(this, mensaje);

            if (mensaje.startsWith("✅")) {
                dispose(); // cerrar ventana
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ah ocurrido un error inesperado: " + e.getMessage(), "Problemas al Registrarse", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnRegistrarleActionPerformed

    private void jTxtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtNombreFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNombreFocusGained

    private void jTxtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtNombreFocusLost
        this.jTxtNombre.setText(capitalizar(this.jTxtNombre.getText()));
    }//GEN-LAST:event_jTxtNombreFocusLost

    private void jTxtApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtApellidoFocusLost
        this.jTxtApellido.setText(capitalizar(this.jTxtApellido.getText()));
    }//GEN-LAST:event_jTxtApellidoFocusLost

    private void jChkBoxVerContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChkBoxVerContraseniaActionPerformed
        if (jChkBoxVerContrasenia.isSelected()) {
            // Mostrar contraseña
            jPwdContrasenia.setEchoChar((char) 0);
            jPwdConfirmaContrasenia.setEchoChar((char) 0);
        } else {
            // Ocultar contraseña
            jPwdContrasenia.setEchoChar('•');
            jPwdConfirmaContrasenia.setEchoChar('•');
        }
    }//GEN-LAST:event_jChkBoxVerContraseniaActionPerformed

    private void jTxtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyPressed

    }//GEN-LAST:event_jTxtCedulaKeyPressed

    private void jTxtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyTyped
        char c = evt.getKeyChar();

        // Solo permitir dígitos y controlar longitud
        if (!Character.isDigit(c) || jTxtCedula.getText().length() >= 10) {
            evt.consume(); // bloquea el carácter
        }
    }//GEN-LAST:event_jTxtCedulaKeyTyped

    private String capitalizar(String texto) {
        if (texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLogin;
    private javax.swing.JButton jBtnRegistrarle;
    private javax.swing.JCheckBox jChkBoxVerContrasenia;
    private javax.swing.JLabel jLblApellido;
    private javax.swing.JLabel jLblCedula;
    private javax.swing.JLabel jLblContrasenia;
    private javax.swing.JLabel jLblNombre;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPwdConfirmaContrasenia;
    private javax.swing.JPasswordField jPwdContrasenia;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtNombre;
    // End of variables declaration//GEN-END:variables
}
