package persistencia;

import model.*;
import java.io.*;
import java.util.*;

public class PersistenciaArchivo implements IPersistencia {
    private final String archivoPacientes = "pacientes.txt";
    private final String archivoMedicos = "medicos.txt";
    private final String archivoConsultas = "consultas.txt";

    @Override
    public void guardar(Clinica clinica) throws IOException {
        guardarPacientes(clinica.getPacientes());
        guardarMedicos(clinica.getMedicos());
        guardarConsultas(clinica.getConsultas());
    }

    @Override
    public Clinica cargar() throws IOException {
        Clinica clinica = new Clinica();
        Map<String, Paciente> mapaPacientes = cargarPacientes(clinica);
        Map<String, Medico> mapaMedicos = cargarMedicos(clinica);
        cargarConsultas(clinica, mapaPacientes, mapaMedicos);
        return clinica;
    }

    private void guardarPacientes(List<Paciente> pacientes) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoPacientes))) {
            for (Paciente p : pacientes) {
                pw.println(p.getNombre() + ";" + p.getIdentificacion() + ";" + p.getFechaNacimiento());
            }
        }
    }

    private void guardarMedicos(List<Medico> medicos) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoMedicos))) {
            for (Medico m : medicos) {
                pw.println(m.getNombre() + ";" + m.getIdentificacion() + ";" + m.getEspecialidad());
            }
        }
    }

    private void guardarConsultas(List<Consulta> consultas) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoConsultas))) {
            for (Consulta c : consultas) {
                pw.println(c.getId() + ";" +
                           c.getPaciente().getIdentificacion() + ";" +
                           c.getMedico().getIdentificacion() + ";" +
                           c.getSintomas() + ";" +
                           c.getDiagnostico() + ";" +
                           c.getTratamiento());
            }
        }
    }

    private Map<String, Paciente> cargarPacientes(Clinica clinica) throws IOException {
        Map<String, Paciente> mapa = new HashMap<>();
        File file = new File(archivoPacientes);
        if (!file.exists()) return mapa;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    Paciente p = new Paciente(datos[0], datos[1], datos[2]);
                    clinica.agregarPaciente(p);
                    mapa.put(datos[1], p);
                }
            }
        }
        return mapa;
    }

    private Map<String, Medico> cargarMedicos(Clinica clinica) throws IOException {
        Map<String, Medico> mapa = new HashMap<>();
        File file = new File(archivoMedicos);
        if (!file.exists()) return mapa;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    Medico m = new Medico(datos[0], datos[1], datos[2]);
                    clinica.agregarMedico(m);
                    mapa.put(datos[1], m);
                }
            }
        }
        return mapa;
    }

    private void cargarConsultas(Clinica clinica, Map<String, Paciente> pacientes, Map<String, Medico> medicos) throws IOException {
        File file = new File(archivoConsultas);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    Paciente p = pacientes.get(datos[1]);
                    Medico m = medicos.get(datos[2]);
                    if (p != null && m != null) {
                        Consulta c = new Consulta(datos[0], p, m, datos[3], datos[4], datos[5]);
                        clinica.agregarConsulta(c);
                    }
                }
            }
        }
    }
}
