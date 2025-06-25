package view;

import viewmodel.ClinicaViewModel;
import model.Consulta;

import javax.swing.*;
import java.util.List;

public class PanelHistorial extends JFrame {
    private String rol;
    private String usuario;

    public PanelHistorial(ClinicaViewModel vm, String rol, String usuario) {
        this.rol = rol.toLowerCase();
        this.usuario = usuario;

        setTitle("Historial Clínico");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblID = new JLabel("ID Paciente:");
        JTextField txtID = new JTextField();
        JButton btnBuscarPaciente = new JButton("Ver historial paciente");
        JButton btnBuscarMedico = new JButton("Ver consultas médico"); // solo para admin
        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);

        lblID.setBounds(20, 10, 120, 25);
        txtID.setBounds(150, 10, 200, 25);
        btnBuscarPaciente.setBounds(20, 50, 200, 25);
        btnBuscarMedico.setBounds(230, 50, 200, 25);
        scroll.setBounds(20, 90, 440, 250);

        add(lblID); add(txtID);
        add(btnBuscarPaciente);
        add(scroll);

        if (rol.equals("admin")) {
            add(btnBuscarMedico);

            btnBuscarMedico.addActionListener(e -> {
                List<Consulta> consultas = vm.obtenerConsultasMedico(txtID.getText());
                mostrarConsultas(consultas, area);
            });
        }

        btnBuscarPaciente.addActionListener(e -> {
            List<Consulta> historial = vm.obtenerHistorialPaciente(txtID.getText());
            mostrarConsultas(historial, area);
        });

        setVisible(true);
    }

    private void mostrarConsultas(List<Consulta> consultas, JTextArea area) {
        if (consultas.isEmpty()) {
            area.setText("No hay consultas encontradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Consulta c : consultas) {
            sb.append("Consulta ID: ").append(c.getId()).append("\n")
              .append("Paciente: ").append(c.getPaciente().getNombre()).append("\n")
              .append("Médico: ").append(c.getMedico().getNombre()).append("\n")
              .append("Síntomas: ").append(c.getSintomas()).append("\n")
              .append("Diagnóstico: ").append(c.getDiagnostico()).append("\n")
              .append("Tratamiento: ").append(c.getTratamiento()).append("\n")
              .append("-----------------------------\n");
        }
        area.setText(sb.toString());
    }
}
