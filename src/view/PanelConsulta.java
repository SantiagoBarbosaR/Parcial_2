package view;

import viewmodel.ClinicaViewModel;
import excepciones.*;

import javax.swing.*;

public class PanelConsulta extends JFrame {
    public PanelConsulta(ClinicaViewModel vm) {
        setTitle("Asignar Consulta");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblIdConsulta = new JLabel("ID Consulta:");
        JTextField txtIdConsulta = new JTextField();
        JLabel lblPaciente = new JLabel("ID Paciente:");
        JTextField txtPaciente = new JTextField();
        JLabel lblMedico = new JLabel("ID Médico:");
        JTextField txtMedico = new JTextField();
        JLabel lblSintomas = new JLabel("Síntomas:");
        JTextField txtSintomas = new JTextField();
        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        JTextField txtDiagnostico = new JTextField();
        JLabel lblTratamiento = new JLabel("Tratamiento:");
        JTextField txtTratamiento = new JTextField();
        JButton btnAsignar = new JButton("Asignar");

        lblIdConsulta.setBounds(20, 20, 150, 25);
        txtIdConsulta.setBounds(180, 20, 180, 25);
        lblPaciente.setBounds(20, 50, 150, 25);
        txtPaciente.setBounds(180, 50, 180, 25);
        lblMedico.setBounds(20, 80, 150, 25);
        txtMedico.setBounds(180, 80, 180, 25);
        lblSintomas.setBounds(20, 110, 150, 25);
        txtSintomas.setBounds(180, 110, 180, 25);
        lblDiagnostico.setBounds(20, 140, 150, 25);
        txtDiagnostico.setBounds(180, 140, 180, 25);
        lblTratamiento.setBounds(20, 170, 150, 25);
        txtTratamiento.setBounds(180, 170, 180, 25);
        btnAsignar.setBounds(140, 230, 120, 30);

        add(lblIdConsulta); add(txtIdConsulta);
        add(lblPaciente); add(txtPaciente);
        add(lblMedico); add(txtMedico);
        add(lblSintomas); add(txtSintomas);
        add(lblDiagnostico); add(txtDiagnostico);
        add(lblTratamiento); add(txtTratamiento);
        add(btnAsignar);

        btnAsignar.addActionListener(e -> {
            try {
                vm.agendarConsulta(
                        txtIdConsulta.getText(),
                        txtPaciente.getText(),
                        txtMedico.getText(),
                        txtSintomas.getText(),
                        txtDiagnostico.getText(),
                        txtTratamiento.getText()
                );

                JOptionPane.showMessageDialog(this, "Consulta asignada correctamente.");
                dispose();

            } catch (CampoVacioException | UsuarioNoEncontradoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setVisible(true);
    }
}
