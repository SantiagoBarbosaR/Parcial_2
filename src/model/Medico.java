package model;

public class Medico extends Persona {
    private String especialidad;

    public Medico(String nombre, String id, String especialidad) {
        super(nombre, id);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String getTipo() {
        return "Medico";
    }
}
