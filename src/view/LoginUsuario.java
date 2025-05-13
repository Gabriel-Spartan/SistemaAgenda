package view;

import controller.LoginController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LoginUsuario extends javax.swing.JFrame {

    private static final Color COLOR_FONDO_GENERAL = new Color(39, 39, 43);
    private static final Color COLOR_PANEL = new Color(153, 153, 153);
    private static final Color COLOR_CAMPO_TEXTO = new Color(135, 152, 163);
    private static final Color COLOR_TEXTO = Color.BLACK;

    private static final Color COLOR_BTN_INGRESAR = new Color(173, 179, 183);
    private static final Color COLOR_BTN_INGRESAR_HOVER = new Color(210, 210, 210);

    private static final Color COLOR_BTN_REGISTRARSE = new Color(124, 124, 132);
    private static final Color COLOR_BTN_REGISTRARSE_HOVER = new Color(179, 179, 182);

    public LoginUsuario() {
        initComponents();
        this.setSize(350, 420);
        this.setLocationRelativeTo(null);
        disenioInterfaz();
        getRootPane().setDefaultButton(jBtnIngresar);
        System.out.println(getClass().getResource("/resources/img/icono.png"));


    }

    // FrontEnd
    private void disenioInterfaz() {
        this.getContentPane().setBackground(COLOR_FONDO_GENERAL);
        jPanel1.setBackground(COLOR_PANEL);

        // Etiquetas
        configurarEtiqueta(jlbCedula);
        configurarEtiqueta(jlbContrasenia);
        configurarEtiqueta(jlbRegistrarse);

        // Fuente personalizada para el título
        try {
            Font creepyFont = null;

            // Intentar cargar desde recursos empaquetados
            InputStream is = getClass().getClassLoader().getResourceAsStream("resources/fonts/Creepy.ttf");
            if (is != null) {
                creepyFont = Font.createFont(Font.TRUETYPE_FONT, is);
            } else {
                // Cargar desde archivo local
                File fontFile = new File("src/resources/fonts/Creepy.ttf");
                if (fontFile.exists()) {
                    creepyFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                } else {
                    System.err.println("❌ No se encontró el archivo de fuente.");
                }
            }

            if (creepyFont != null) {
                jlbTitulo.setFont(creepyFont.deriveFont(Font.BOLD, 30f));
            } else {
                jlbTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
            }

        } catch (Exception e) {
            e.printStackTrace();
            jlbTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
        }

        jlbTitulo.setForeground(COLOR_TEXTO);

        // Campos
        configurarCampoTexto(jTxtCedula);
        configurarCampoTexto(jPwdContrasenia);

        // Botones
        configurarBotonHover(jBtnIngresar, COLOR_BTN_INGRESAR, COLOR_BTN_INGRESAR_HOVER);
        configurarBotonHover(jBtnRegistrarse, COLOR_BTN_REGISTRARSE, COLOR_BTN_REGISTRARSE_HOVER);

        // Checkbox (ojo)
        jtbVerContrasenia.setText("👁");
        jtbVerContrasenia.setBorderPainted(false);
        jtbVerContrasenia.setContentAreaFilled(false);
        jtbVerContrasenia.setFocusPainted(false);
        jtbVerContrasenia.setOpaque(false);
        jtbVerContrasenia.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    private void configurarEtiqueta(javax.swing.JLabel etiqueta) {
        etiqueta.setForeground(COLOR_TEXTO);
        etiqueta.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
    }

    private void configurarCampoTexto(javax.swing.JTextField campo) {
        campo.setBackground(COLOR_CAMPO_TEXTO);
        campo.setForeground(COLOR_TEXTO);
        campo.setFont(new Font("MV Boli", Font.PLAIN, 16));
    }

    private void configurarCampoTexto(javax.swing.JPasswordField campo) {
        campo.setBackground(COLOR_CAMPO_TEXTO);
        campo.setForeground(COLOR_TEXTO);
        campo.setFont(new Font("MV Boli", Font.PLAIN, 16));
    }

    private void configurarBotonHover(javax.swing.JButton boton, Color colorNormal, Color colorHover) {
        boton.setBackground(colorNormal);
        boton.setForeground(COLOR_TEXTO);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorNormal);
            }
        });
    }

    //BackEnd
    private void realizarLogin() {
        String cedula = jTxtCedula.getText().trim();
        String contrasena = new String(jPwdContrasenia.getPassword()).trim();

        LoginController controller = new LoginController();
        String mensaje = controller.iniciarSesion(cedula, contrasena);

        if (mensaje.startsWith("✅")) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. ¡Bienvenido!", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new VistaPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, mensaje, "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlbCedula = new javax.swing.JLabel();
        jlbContrasenia = new javax.swing.JLabel();
        jlbTitulo = new javax.swing.JLabel();
        jTxtCedula = new javax.swing.JTextField();
        jBtnIngresar = new javax.swing.JButton();
        jBtnRegistrarse = new javax.swing.JButton();
        jPwdContrasenia = new javax.swing.JPasswordField();
        jtbVerContrasenia = new javax.swing.JToggleButton();
        jlbRegistrarse = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbCedula.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jlbCedula.setText("Cédula");
        jPanel1.add(jlbCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jlbContrasenia.setText("Contraseña");
        jPanel1.add(jlbContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jlbTitulo.setFont(new java.awt.Font("Creepy", 0, 30)); // NOI18N
        jlbTitulo.setText("INICIAR SESIÓN");
        jPanel1.add(jlbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jTxtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtCedulaKeyTyped(evt);
            }
        });
        jPanel1.add(jTxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 200, 30));

        jBtnIngresar.setText("Ingresar");
        jBtnIngresar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jBtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 90, 30));

        jBtnRegistrarse.setText("Registrarse");
        jBtnRegistrarse.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jBtnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarseActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 325, 110, 30));

        jPwdContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPwdContraseniaActionPerformed(evt);
            }
        });
        jPanel1.add(jPwdContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, 30));

        jtbVerContrasenia.setText("👁");
        jtbVerContrasenia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbVerContrasenia.setOpaque(true);
        jtbVerContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbVerContraseniaActionPerformed(evt);
            }
        });
        jPanel1.add(jtbVerContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 50, 30));

        jlbRegistrarse.setText("¿No tienes una cuenta?");
        jPanel1.add(jlbRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOpaque(true);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 280, 250, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarseActionPerformed
        RegistroUsuario ventana = new RegistroUsuario();
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }//GEN-LAST:event_jBtnRegistrarseActionPerformed

    private void jBtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIngresarActionPerformed
        realizarLogin();
    }//GEN-LAST:event_jBtnIngresarActionPerformed

    private void jTxtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyTyped
        char c = evt.getKeyChar();

        // Solo permitir dígitos y controlar longitud
        if (!Character.isDigit(c) || jTxtCedula.getText().length() >= 10) {
            evt.consume(); // bloquea el carácter
        }
    }//GEN-LAST:event_jTxtCedulaKeyTyped

    private void jtbVerContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbVerContraseniaActionPerformed
        // TODO add your handling code here:
        if (jtbVerContrasenia.isSelected()) {
            // Mostrar contraseña
            jPwdContrasenia.setEchoChar((char) 0);
            jtbVerContrasenia.setForeground(new Color(96, 95, 120));
        } else {
            // Ocultar contraseña
            jPwdContrasenia.setEchoChar('•');
            jtbVerContrasenia.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtbVerContraseniaActionPerformed

    private void jPwdContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPwdContraseniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPwdContraseniaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        LoginUsuario ventana = new LoginUsuario();
        ventana.setLocationRelativeTo(null);  // Centra la ventana
        ventana.setResizable(false);          // Opcional: no permitir redimensionar
        ventana.setVisible(true);
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnIngresar;
    private javax.swing.JButton jBtnRegistrarse;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPwdContrasenia;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JLabel jlbCedula;
    private javax.swing.JLabel jlbContrasenia;
    private javax.swing.JLabel jlbRegistrarse;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JToggleButton jtbVerContrasenia;
    // End of variables declaration//GEN-END:variables
}
