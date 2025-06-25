package view;

import viewmodel.ClinicaViewModel;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private ClinicaViewModel viewModel;
    private String rol;
    private String usuario;

    public VentanaPrincipal(ClinicaViewModel viewModel, String rol, String usuario) {
        this.viewModel = viewModel;
        this.rol = rol.toLowerCase();
        this.usuario = usuario;
        setTitle("Bienvenido: " + usuario + " | Rol: " + rol);
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnRegistro = new JButton("Registrar Paciente / Médico");
        JButton btnConsulta = new JButton("Asignar Consulta");
        JButton btnHistorial = new JButton("Historial");
        JButton btnGuardar = new JButton("Guardar Cambios");
        JButton btnSalir = new JButton("Salir");

        setLayout(new GridLayout(5, 1, 10, 10));

        // Mostrar opciones según el rol
        if (rol.equals("admin")) {
            add(btnRegistro);
            add(btnHistorial);
        } else if (rol.equals("medico")) {
            add(btnConsulta);
            add(btnHistorial);
        }

        add(btnGuardar);
        add(btnSalir);

        btnRegistro.addActionListener(e -> new PanelRegistro(viewModel));
        btnConsulta.addActionListener(e -> new PanelConsulta(viewModel));
        btnHistorial.addActionListener(e -> new PanelHistorial(viewModel, rol, usuario)); // <- se pasa rol y usuario

        btnGuardar.addActionListener(e -> {
            try {
                viewModel.guardarDatos();
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });

        btnSalir.addActionListener(e -> {
            try {
                viewModel.guardarDatos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar antes de salir: " + ex.getMessage());
            }
            System.exit(0);
        });

        setVisible(true);
    }
}
