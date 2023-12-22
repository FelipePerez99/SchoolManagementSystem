import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico  implements ServiciosAcademicosI{
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

    @Override
    public void matriculaEstudiante(Estudiante estudiante) {
        boolean estudianteMatriculado = false;

        for (Estudiante estudiant : estudiantes){
            if(estudiante.getId() == estudiant.getId()){
                estudianteMatriculado = true;
                break;
            }
        }

        if(estudianteMatriculado == false){
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        boolean cursoMatriculado = false;

        for (Curso curs : cursos){
            if(curso.getId() == curso.getId()){
                cursoMatriculado = true;
                break;
            }
        }

        if(cursoMatriculado == false){
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        Curso cursoElegido = null;

        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                cursoElegido = curso;
                break;
            }
        }

        if (cursoElegido != null && estudiantesPorCurso.containsKey(cursoElegido)) {
            ArrayList<Estudiante> estudiantesInscritos = estudiantesPorCurso.get(cursoElegido);

            if (estudiantesInscritos.contains(estudiante) == false) {
                estudiantesInscritos.add(estudiante);
            } else {
                throw new EstudianteYaInscritoException("El estudiante ya está inscrito");
            }
        }
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Curso cursoElegido = null;

        // Buscar el curso con el id proporcionado
        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                cursoElegido = curso;
                break;
            }
        }

        if (cursoElegido != null && estudiantesPorCurso.containsKey(cursoElegido)) {
            ArrayList<Estudiante> estudiantesInscritos = estudiantesPorCurso.get(cursoElegido);

            if (!estudiantesInscritos.removeIf(inscrito -> inscrito.getId() == idEstudiante)) {
                throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito.");
            }
        }
    }

}
