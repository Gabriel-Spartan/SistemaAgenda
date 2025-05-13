package view;

import controller.RegistroController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.InputStream;
import javax.swing.JOptionPane;

public class RegistroUsuario extends javax.swing.JFrame {

    private static final Color COLOR_FONDO_GENERAL = new Color(39, 39, 43);
    private static final Color COLOR_PANEL = new Color(153, 153, 153);
    private static final Color COLOR_CAMPO_TEXTO = new Color(205, 205, 210);
    private static final Color COLOR_TEXTO = Color.BLACK;

    private static final Color COLOR_BTN_REGISTRARSE = new Color(4, 70, 39);
    private static final Color COLOR_BTN_REGISTRARSE_HOVER = new Color(39, 117, 98);
    private static final Color COLOR_BTN_LOGIN = new Color(92, 118, 137);
    private static final Color COLOR_BTN_LOGIN_HOVER = new Color(83, 91, 111);

    private static int contadorError = 0;

    public RegistroUsuario() {
        initComponents();
        setLocationRelativeTo(null);
        setSize(360, 500);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/img/icono.png")));
        disenioInterfaz();
        getRootPane().setDefaultButton(jBtnRegistrarle);
    }

    private void disenioInterfaz() {
        this.getContentPane().setBackground(COLOR_FONDO_GENERAL);
        jPanel1.setBackground(COLOR_PANEL);

        configurarEtiqueta(jLblCedula);
        configurarEtiqueta(jLblNombre);
        configurarEtiqueta(jLblApellido);
        configurarEtiqueta(jLblContrasenia);
        configurarEtiqueta(jLblConfContrasenia);
        configurarEtiqueta(jLblTitulo);

        configurarCampoTexto(jTxtCedula);
        configurarCampoTexto(jTxtNombre);
        configurarCampoTexto(jTxtApellido);
        configurarCampoTexto(jPwdContrasenia);
        configurarCampoTexto(jPwdConfirmaContrasenia);

        try (InputStream is = getClass().getResourceAsStream("/resources/fonts/Creepy.ttf")) {
            if (is != null) {
                Font creepyFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 35f);
                jLblTitulo.setFont(creepyFont);
            }
        } catch (Exception e) {
            jLblTitulo.setFont(new Font("Dialog", Font.BOLD, 30));
            System.err.println("Error fuente: " + e.getMessage());
        }

        // Checkbox
        jtbVerContrasenia.setText("👁");
        jtbVerContrasenia.setBorderPainted(false);
        jtbVerContrasenia.setContentAreaFilled(false);
        jtbVerContrasenia.setFocusPainted(false);
        jtbVerContrasenia.setOpaque(false);
        jtbVerContrasenia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jtbVerConfContrasenia.setText("👁");
        jtbVerConfContrasenia.setBorderPainted(false);
        jtbVerConfContrasenia.setContentAreaFilled(false);
        jtbVerConfContrasenia.setFocusPainted(false);
        jtbVerConfContrasenia.setOpaque(false);
        jtbVerConfContrasenia.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Botones
        jBtnLogin.setFont(new Font("HYGungSo-Bold", Font.PLAIN, 14));
        jBtnRegistrarle.setFont(new Font("HYGungSo-Bold", Font.PLAIN, 14));

        configurarBotonHover(jBtnRegistrarle, COLOR_BTN_REGISTRARSE, COLOR_BTN_REGISTRARSE_HOVER);
        configurarBotonHover(jBtnLogin, COLOR_BTN_LOGIN, COLOR_BTN_LOGIN_HOVER);

    }

    private void configurarEtiqueta(javax.swing.JLabel etiqueta) {
        etiqueta.setForeground(COLOR_TEXTO);
        etiqueta.setFont(new Font("Palatino Linotype", Font.BOLD, 14));
    }

    private void configurarCampoTexto(javax.swing.JTextField campo) {
        campo.setBackground(COLOR_CAMPO_TEXTO);
        campo.setForeground(COLOR_TEXTO);
        campo.setFont(new Font("MV Boli", Font.PLAIN, 14));
    }

    private void configurarCampoTexto(javax.swing.JPasswordField campo) {
        campo.setBackground(COLOR_CAMPO_TEXTO);
        campo.setForeground(COLOR_TEXTO);
        campo.setFont(new Font("MV Boli", Font.PLAIN, 14));
    }

    private void mostrarErrorConImagen(String mensaje) {
        String[] imagenes = {
            "/resources/img/erroranime1.png",
            "/resources/img/erroranime2.png",
            "/resources/img/erroranime3.png"
        };

        String ruta = imagenes[contadorError % imagenes.length];
        java.net.URL url = getClass().getResource(ruta);

        if (url != null) {
            javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(url);
            java.awt.Image imagenEscalada = originalIcon.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(imagenEscalada);
            JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE, icono);
        } else {
            JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
        }

        contadorError++;
    }

    private void configurarBotonHover(javax.swing.JButton boton, Color colorNormal, Color colorHover) {
        boton.setBackground(colorNormal);
        boton.setForeground(COLOR_TEXTO);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorNormal);
            }
        });
    }

    private void mostrarExitoConImagen(String mensaje) {
        String ruta = "/resources/img/exito.png";
        java.net.URL url = getClass().getResource(ruta);

        if (url != null) {
            javax.swing.ImageIcon originalIcon = new javax.swing.ImageIcon(url);
            java.awt.Image imagenEscalada = originalIcon.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(imagenEscalada);
            JOptionPane.showMessageDialog(this, mensaje, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE, icono);
        } else {
            JOptionPane.showMessageDialog(this, mensaje, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblTitulo = new javax.swing.JLabel();
        jLblCedula = new javax.swing.JLabel();
        jLblNombre = new javax.swing.JLabel();
        jLblApellido = new javax.swing.JLabel();
        jLblConfContrasenia = new javax.swing.JLabel();
        jPwdContrasenia = new javax.swing.JPasswordField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jTxtCedula = new javax.swing.JTextField();
        jPwdConfirmaContrasenia = new javax.swing.JPasswordField();
        jBtnRegistrarle = new javax.swing.JButton();
        jtbVerContrasenia = new javax.swing.JToggleButton();
        jLblContrasenia = new javax.swing.JLabel();
        jtbVerConfContrasenia = new javax.swing.JToggleButton();
        jBtnLogin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(300, 315));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblTitulo.setText("Registrate Ahora");
        jPanel1.add(jLblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, -1));

        jLblCedula.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLblCedula.setText("Cédula");
        jPanel1.add(jLblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 65, -1, 20));

        jLblNombre.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLblNombre.setText("Nombre");
        jPanel1.add(jLblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        jLblApellido.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLblApellido.setText("Apellido");
        jPanel1.add(jLblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 30));

        jLblConfContrasenia.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLblConfContrasenia.setText("Confirmar contraseña");
        jPanel1.add(jLblConfContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 30));

        jPwdContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jPwdContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 220, 35));

        jTxtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTxtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtApellidoFocusLost(evt);
            }
        });
        jPanel1.add(jTxtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 220, 35));

        jTxtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTxtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtNombreFocusLost(evt);
            }
        });
        jPanel1.add(jTxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 220, 35));

        jTxtCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jPanel1.add(jTxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 220, 35));

        jPwdConfirmaContrasenia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jPwdConfirmaContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 220, 35));

        jBtnRegistrarle.setBackground(new java.awt.Color(204, 204, 204));
        jBtnRegistrarle.setFont(new java.awt.Font("HYGungSo-Bold", 0, 14)); // NOI18N
        jBtnRegistrarle.setForeground(new java.awt.Color(0, 0, 0));
        jBtnRegistrarle.setText("Registrarse");
        jBtnRegistrarle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnRegistrarle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarleActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnRegistrarle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 120, 30));

        jtbVerContrasenia.setText("👁");
        jtbVerContrasenia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbVerContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbVerContraseniaActionPerformed(evt);
            }
        });
        jPanel1.add(jtbVerContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 50, 35));

        jLblContrasenia.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jLblContrasenia.setText("Contraseña");
        jPanel1.add(jLblContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 241, -1, 30));

        jtbVerConfContrasenia.setText("👁");
        jtbVerConfContrasenia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbVerConfContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbVerConfContraseniaActionPerformed(evt);
            }
        });
        jPanel1.add(jtbVerConfContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 50, 35));

        jBtnLogin.setFont(new java.awt.Font("HYGungSo-Bold", 0, 14)); // NOI18N
        jBtnLogin.setForeground(new java.awt.Color(0, 0, 0));
        jBtnLogin.setText("Volver Al Login");
        jBtnLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jBtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 160, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOpaque(true);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 408, 260, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
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

            if (mensaje.startsWith("✅")) {
                mostrarExitoConImagen(mensaje);
                dispose();
            } else {
                mostrarErrorConImagen(mensaje);
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

    private void jTxtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtCedulaKeyPressed

    }//GEN-LAST:event_jTxtCedulaKeyPressed

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

    private void jtbVerConfContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbVerConfContraseniaActionPerformed
        // TODO add your handling code here:

        if (jtbVerConfContrasenia.isSelected()) {
            jPwdConfirmaContrasenia.setEchoChar((char) 0);
            jtbVerConfContrasenia.setForeground(new Color(96, 95, 120));
        } else {
            jPwdConfirmaContrasenia.setEchoChar('•');
            jtbVerConfContrasenia.setForeground(new Color(0, 0, 0));
        }

    }//GEN-LAST:event_jtbVerConfContraseniaActionPerformed

    private void jBtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoginActionPerformed
        // TODO add your handling code here:
        this.dispose(); // Cierra la ventana de registro
        new LoginUsuario().setVisible(true); // Abre la ventana de login
    }//GEN-LAST:event_jBtnLoginActionPerformed

    private String capitalizar(String texto) {
        if (texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLogin;
    private javax.swing.JButton jBtnRegistrarle;
    private javax.swing.JLabel jLblApellido;
    private javax.swing.JLabel jLblCedula;
    private javax.swing.JLabel jLblConfContrasenia;
    private javax.swing.JLabel jLblContrasenia;
    private javax.swing.JLabel jLblNombre;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPwdConfirmaContrasenia;
    private javax.swing.JPasswordField jPwdContrasenia;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtCedula;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JToggleButton jtbVerConfContrasenia;
    private javax.swing.JToggleButton jtbVerContrasenia;
    // End of variables declaration//GEN-END:variables
}
