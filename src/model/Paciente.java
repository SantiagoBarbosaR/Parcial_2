package model;

public class Paciente extends Persona {
    private String fechaNacimiento;

    public Paciente(String nombre, String id, String fechaNacimiento) {
        super(nombre, id);
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String getTipo() {
        return "Paciente";
    }
}
