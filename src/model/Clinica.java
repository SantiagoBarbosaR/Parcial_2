package model;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    public void agregarPaciente(Paciente p) {
        pacientes.add(p);
    }

    public void agregarMedico(Medico m) {
        medicos.add(m);
    }

    public void agregarConsulta(Consulta c) {
        consultas.add(c);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Consulta> obtenerHistorialPorPaciente(String idPaciente) {
        List<Consulta> historial = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getPaciente().getIdentificacion().equals(idPaciente)) {
                historial.add(c);
            }
        }
        return historial;
    }

    public List<Consulta> obtenerConsultasPorMedico(String idMedico) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getMedico().getIdentificacion().equals(idMedico)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
