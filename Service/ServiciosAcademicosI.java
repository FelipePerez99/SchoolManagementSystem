package Service;

import Entity.Curso;
import Entity.Estudiante;
import Excepcion.EstudianteNoInscritoEnCursoException;
import Excepcion.EstudianteYaInscritoException;

public interface ServiciosAcademicosI {

    void matriculaEstudiante(Estudiante estudiante);
    void agregarCurso(Curso curso);
    void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException;
    void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException;

}
