package persistencia;

import model.Clinica;

public interface IPersistencia {
    void guardar(Clinica clinica) throws Exception;
    Clinica cargar() throws Exception;
}
