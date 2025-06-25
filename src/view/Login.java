package view;

import viewmodel.ClinicaViewModel;
import javax.swing.*;
import java.io.*;

public class Login extends JFrame {

    public Login() {
        setTitle("Login de Usuario");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Ingresar");

        lblUsuario.setBounds(20, 30, 100, 25);
        txtUsuario.setBounds(120, 30, 180, 25);
        lblPassword.setBounds(20, 70, 100, 25);
        txtPassword.setBounds(120, 70, 180, 25);
        btnLogin.setBounds(100, 130, 120, 30);

        add(lblUsuario); add(txtUsuario);
        add(lblPassword); add(txtPassword);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());

            String rol = validarUsuario(usuario, pass);

            if (rol != null) {
                ClinicaViewModel vm = new ClinicaViewModel();
                try {
                    vm.cargarDatos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error cargando datos: " + ex.getMessage());
                }
                new VentanaPrincipal(vm, rol, usuario);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });

        setVisible(true);
    }

    private String validarUsuario(String usuario, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    if (partes[0].equalsIgnoreCase(usuario) && partes[1].equals(pass)) {
                        return partes[2]; // devuelve el rol si coincide
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error leyendo archivo de usuarios");
        }
        return null;
    }
}
