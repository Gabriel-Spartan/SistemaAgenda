package view;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class EditarEventoDialog extends JDialog {
    private JTextField       tituloField;
    private JTextArea        descripcionArea;
    private JDateChooser     dateChooser;
    private JComboBox<String> horaCombo;
    private JComboBox<String> minutoCombo;
    private boolean confirmado = false;

    public EditarEventoDialog(Frame parent) {
        super(parent, "Editar Evento", true);
        initComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        // --> TÍTULO
        c.gridx = 0; c.gridy = 0; content.add(new JLabel("Título:"), c);
        c.gridx = 1; 
        tituloField = new JTextField(20);
        content.add(tituloField, c);

        // --> DESCRIPCIÓN
        c.gridx = 0; c.gridy = 1; 
        content.add(new JLabel("Descripción:"), c);
        c.gridx = 1;
        descripcionArea = new JTextArea(4, 20);
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        content.add(new JScrollPane(descripcionArea), c);

        // --> FECHA
        c.gridx = 0; c.gridy = 2;
        content.add(new JLabel("Fecha:"), c);
        c.gridx = 1;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        content.add(dateChooser, c);

        // --> HORA
        c.gridx = 0; c.gridy = 3;
        content.add(new JLabel("Hora:"), c);
        c.gridx = 1;
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        horaCombo = new JComboBox<>();
        minutoCombo = new JComboBox<>();
        for (int i = 0; i < 24; i++) horaCombo.addItem(String.format("%02d", i));
        for (int i = 0; i < 60; i++) minutoCombo.addItem(String.format("%02d", i));
        timePanel.add(horaCombo);
        timePanel.add(new JLabel(" : "));
        timePanel.add(minutoCombo);
        content.add(timePanel, c);

        // --> BOTONES
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2; c.anchor = GridBagConstraints.EAST;
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        btns.add(btnCancelar);
        btns.add(btnGuardar);
        content.add(btns, c);

        btnGuardar.addActionListener(e -> {
            confirmado = true;
            dispose();
        });
        btnCancelar.addActionListener(e -> dispose());

        setContentPane(content);
    }

    // getters para recuperar valores
    public boolean isConfirmado() {
        return confirmado;
    }
    public String getTitulo() {
        return tituloField.getText().trim();
    }
    public String getDescripcion() {
        return descripcionArea.getText().trim();
    }
    public Date getFecha() {
        return dateChooser.getDate();
    }
    public Time getHora() {
        int h = Integer.parseInt((String)horaCombo.getSelectedItem());
        int m = Integer.parseInt((String)minutoCombo.getSelectedItem());
        return Time.valueOf(LocalTime.of(h, m));
    }

    // setters para precargar datos antes de mostrar
    public void setTitulo(String t) {
        tituloField.setText(t);
    }
    public void setDescripcion(String d) {
        descripcionArea.setText(d);
    }
    public void setFecha(Date d) {
        dateChooser.setDate(d);
    }
    public void setHora(Time h) {
        int hh = h.toLocalTime().getHour();
        int mm = h.toLocalTime().getMinute();
        horaCombo.setSelectedItem(String.format("%02d", hh));
        minutoCombo.setSelectedItem(String.format("%02d", mm));
    }
}