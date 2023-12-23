package Entity;

import java.util.Date;

public class Estudiante extends Persona {

    public Estudiante() {
    }

    public Estudiante(int id, String nombre, String apellido, Date fechaNacimiento, String estado) {
        super(id, nombre, apellido, fechaNacimiento, estado);
    }
}
