import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Curso> cursos = new ArrayList<>();
    private HashMap<Curso, ArrayList<Estudiante>> estudiantesPorCurso;

    public GestorAcademico(){

    }
    public GestorAcademico(ArrayList<Estudiante> estudiantes, ArrayList<Curso> cursos, HashMap<Curso, ArrayList<Estudiante>> estudiantesPorCurso) {
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        this.estudiantesPorCurso = estudiantesPorCurso;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public HashMap<Curso, ArrayList<Estudiante>> getEstudiantesPorCurso() {
        return estudiantesPorCurso;
    }

    public void setEstudiantesPorCurso(HashMap<Curso, ArrayList<Estudiante>> estudiantesPorCurso) {
        this.estudiantesPorCurso = estudiantesPorCurso;
    }

    @Override
    public String toString() {
        return "GestorAcademico{" +
                "estudiantes=" + estudiantes +
                ", cursos=" + cursos +
                ", estudiantesPorCurso=" + estudiantesPorCurso +
                '}';
    }
}
