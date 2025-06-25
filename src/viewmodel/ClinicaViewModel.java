package viewmodel;

import excepciones.CampoVacioException;
import excepciones.UsuarioNoEncontradoException;
import model.*;
import persistencia.*;

import java.io.*;
import java.util.List;

public class ClinicaViewModel {
    private Clinica clinica;
    private IPersistencia persistencia;

    public ClinicaViewModel() {
        this.clinica = new Clinica();
        this.persistencia = new PersistenciaArchivo();
    }

    public void cargarDatos() throws IOException, Exception {
        this.clinica = persistencia.cargar();
    }

    public void guardarDatos() throws IOException, Exception {
        persistencia.guardar(clinica);
    }

    public void registrarPaciente(String nombre, String id, String nacimiento) throws CampoVacioException {
        if (nombre.isEmpty() || id.isEmpty() || nacimiento.isEmpty()) {
            throw new CampoVacioException("Todos los campos del paciente son obligatorios.");
        }

        Paciente paciente = new Paciente(nombre, id, nacimiento);
        clinica.agregarPaciente(paciente);
    }

    public void registrarMedico(String nombre, String id, String especialidad, String password) throws CampoVacioException {
        if (nombre.isEmpty() || id.isEmpty() || especialidad.isEmpty() || password.isEmpty()) {
            throw new CampoVacioException("Todos los campos del médico son obligatorios.");
        }

        Medico medico = new Medico(nombre, id, especialidad);
        clinica.agregarMedico(medico);

        //  Guardar también en usuarios.txt con contraseña personalizada
        try (FileWriter fw = new FileWriter("usuarios.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(id + ";" + password + ";medico");

        } catch (IOException e) {
            System.err.println("No se pudo agregar al login: " + e.getMessage());
        }
    }

    public void agendarConsulta(String idConsulta, String idPaciente, String idMedico,
                                 String sintomas, String diagnostico, String tratamiento)
            throws CampoVacioException, UsuarioNoEncontradoException {

        if (idConsulta.isEmpty() || idPaciente.isEmpty() || idMedico.isEmpty() ||
            sintomas.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()) {
            throw new CampoVacioException("Todos los campos de la consulta son obligatorios.");
        }

        Paciente paciente = buscarPacientePorId(idPaciente);
        Medico medico = buscarMedicoPorId(idMedico);

        Consulta consulta = new Consulta(idConsulta, paciente, medico, sintomas, diagnostico, tratamiento);
        clinica.agregarConsulta(consulta);
    }

    public Paciente buscarPacientePorId(String id) throws UsuarioNoEncontradoException {
        for (Paciente p : clinica.getPacientes()) {
            if (p.getIdentificacion().equals(id)) {
                return p;
            }
        }
        throw new UsuarioNoEncontradoException("Paciente no encontrado con ID: " + id);
    }

    public Medico buscarMedicoPorId(String id) throws UsuarioNoEncontradoException {
        for (Medico m : clinica.getMedicos()) {
            if (m.getIdentificacion().equals(id)) {
                return m;
            }
        }
        throw new UsuarioNoEncontradoException("Médico no encontrado con ID: " + id);
    }

    public List<Consulta> obtenerHistorialPaciente(String idPaciente) {
        return clinica.obtenerHistorialPorPaciente(idPaciente);
    }

    public List<Consulta> obtenerConsultasMedico(String idMedico) {
        return clinica.obtenerConsultasPorMedico(idMedico);
    }

    public List<Paciente> getPacientes() {
        return clinica.getPacientes();
    }

    public List<Medico> getMedicos() {
        return clinica.getMedicos();
    }

    public List<Consulta> getConsultas() {
        return clinica.getConsultas();
    }
}
