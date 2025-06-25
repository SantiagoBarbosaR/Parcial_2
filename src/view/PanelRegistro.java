package view;

import viewmodel.ClinicaViewModel;
import excepciones.*;

import javax.swing.*;

public class PanelRegistro extends JFrame {
    public PanelRegistro(ClinicaViewModel vm) {
        setTitle("Registro de Usuario");
        setSize(400, 380);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTipo = new JLabel("Tipo:");
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Paciente", "Médico"});
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblID = new JLabel("Identificación:");
        JTextField txtID = new JTextField();
        JLabel lblExtra = new JLabel("Nacimiento / Especialidad:");
        JTextField txtExtra = new JTextField();

        // Campo de contraseña (solo visible para médicos)
        JLabel lblPass = new JLabel("Contraseña:");
        JPasswordField txtPass = new JPasswordField();
        lblPass.setVisible(false);
        txtPass.setVisible(false);

        JButton btnGuardar = new JButton("Guardar");

        // Posiciones
        lblTipo.setBounds(20, 20, 150, 25);
        tipoCombo.setBounds(180, 20, 180, 25);
        lblNombre.setBounds(20, 60, 150, 25);
        txtNombre.setBounds(180, 60, 180, 25);
        lblID.setBounds(20, 100, 150, 25);
        txtID.setBounds(180, 100, 180, 25);
        lblExtra.setBounds(20, 140, 180, 25);
        txtExtra.setBounds(180, 140, 180, 25);
        lblPass.setBounds(20, 180, 150, 25);
        txtPass.setBounds(180, 180, 180, 25);
        btnGuardar.setBounds(130, 240, 120, 30);

        // Agregar componentes
        add(lblTipo); add(tipoCombo);
        add(lblNombre); add(txtNombre);
        add(lblID); add(txtID);
        add(lblExtra); add(txtExtra);
        add(lblPass); add(txtPass);
        add(btnGuardar);

        // Mostrar campos de contraseña solo si es médico
        tipoCombo.addActionListener(e -> {
            String tipo = (String) tipoCombo.getSelectedItem();
            boolean esMedico = tipo.equals("Médico");
            lblPass.setVisible(esMedico);
            txtPass.setVisible(esMedico);
        });

        btnGuardar.addActionListener(e -> {
            try {
                String tipo = (String) tipoCombo.getSelectedItem();
                String nombre = txtNombre.getText().trim();
                String id = txtID.getText().trim();
                String extra = txtExtra.getText().trim();

                if (tipo.equals("Paciente")) {
                    vm.registrarPaciente(nombre, id, extra);
                    JOptionPane.showMessageDialog(this, "Paciente registrado correctamente.");
                } else {
                    String password = new String(txtPass.getPassword()).trim();
                    vm.registrarMedico(nombre, id, extra, password);
                    JOptionPane.showMessageDialog(this,
                            "Médico registrado correctamente.\n" +
                            "Usuario: " + id + "\nContraseña: " + password);
                }

                dispose();
            } catch (CampoVacioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
