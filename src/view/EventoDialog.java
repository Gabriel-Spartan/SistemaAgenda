package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Time;

public class EventoDialog extends JDialog {
    private boolean confirmado = false;
    private JTextField tituloField;
    private JTextArea descArea;
    private JComboBox<String> hourCombo;
    private JComboBox<String> minuteCombo;

    public EventoDialog(Frame parent) {
        super(parent, "Crear evento", true);
        initComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel content = new JPanel(new BorderLayout(10,10));
        content.setBorder(new EmptyBorder(15,15,15,15));

        // Título
        JLabel lblTitulo = new JLabel("Título:");
        tituloField = new JTextField(20);

        // Descripción
        JLabel lblDesc = new JLabel("Descripción:");
        descArea = new JTextArea(4,20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        // Hora
        JLabel lblHora = new JLabel("Hora:");
        hourCombo = new JComboBox<>();
        minuteCombo = new JComboBox<>();
        // Horas 00–23
        for (int h = 0; h < 24; h++) {
            hourCombo.addItem(String.format("%02d", h));
        }
        // Minutos 00–59 (cada minuto)
        for (int m = 0; m < 60; m++) {
            minuteCombo.addItem(String.format("%02d", m));
        }
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        timePanel.add(hourCombo);
        timePanel.add(new JLabel(":"));
        timePanel.add(minuteCombo);

        // Layout
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0; c.gridy=0; form.add(lblTitulo, c);
        c.gridx=1; form.add(tituloField, c);

        c.gridx=0; c.gridy=1; form.add(lblDesc, c);
        c.gridx=1; form.add(new JScrollPane(descArea), c);

        c.gridx=0; c.gridy=2; form.add(lblHora, c);
        c.gridx=1; form.add(timePanel, c);

        content.add(form, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());
        JButton btnOK = new JButton("Guardar");
        btnOK.addActionListener(e -> onGuardar());
        botones.add(btnCancel);
        botones.add(btnOK);
        content.add(botones, BorderLayout.SOUTH);

        setContentPane(content);
    }

    private void onGuardar() {
        if (tituloField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El título es obligatorio",
                "Atención",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }
    public String getTitulo() {
        return tituloField.getText().trim();
    }
    public String getDescripcion() {
        return descArea.getText().trim();
    }
    public Time getHora() {
        String hh = (String)hourCombo.getSelectedItem();
        String mm = (String)minuteCombo.getSelectedItem();
        return Time.valueOf(hh + ":" + mm + ":00");
    }
}