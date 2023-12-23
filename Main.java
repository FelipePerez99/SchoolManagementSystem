import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorAcademico gestorAcademico = new GestorAcademico();

        boolean deseaContinuar = true;

        do{

            System.out.print("La opción que desea realizar (estudiante/curso/inscribir/desinscribir/salir): ");
            String tipoEntidad = sc.nextLine();

            switch (tipoEntidad.toLowerCase()) {
                case "estudiante" -> {
                    Estudiante estudiante = crearEstudiante(sc);
                    gestorAcademico.matriculaEstudiante(estudiante);
                    //imprimirEstudiante(estudiante);
                }
                case "curso" -> {
                    Curso curso = crearCurso(sc);
                    gestorAcademico.agregarCurso(curso);
                    //imprimirCurso(curso);
                }
                case "inscribir" -> {
                    realizarInscripcion(gestorAcademico, sc);
                }
                case "desinscribir" -> {
                    realizarDesinscripcion(gestorAcademico, sc);
                }
                case "salir" ->{
                    deseaContinuar = false;
                }
                default->
                        System.out.println("Entidad no reconocido.");
            }
        }while(deseaContinuar);

        System.out.println("Programa finalizado.");

        sc.close();
    }

    private static Date obtenerFecha(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;

        while (fecha == null) {
            try {
                String inputFecha = scanner.nextLine();
                fecha = sdf.parse(inputFecha);
            } catch (ParseException e) {
                System.out.print("Formato de fecha incorrecto. Ingrese nuevamente (dd/MM/yyyy): ");
            }
        }

        return fecha;
    }
    private static Estudiante crearEstudiante(Scanner sc) {
        System.out.print("Ingrese el ID del estudiante: ");
        int idEstudiante = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el apellido del estudiante: ");
        String apellido = sc.nextLine();

        System.out.print("Ingrese la fecha de nacimiento del estudiante (formato dd/MM/yyyy): ");
        Date fechaNacimiento = obtenerFecha(sc);

        System.out.print("Ingrese el estado del estudiante: ");
        String estado = sc.nextLine();

        return new Estudiante(idEstudiante, nombre, apellido, fechaNacimiento, estado);
    }

    private static Curso crearCurso(Scanner sc) {
        System.out.print("Ingrese el ID del curso: ");
        int idCurso = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nombre del curso: ");
        String nombreCurso = sc.nextLine();

        System.out.print("Ingrese la descripción del curso: ");
        String descripcionCurso = sc.nextLine();

        System.out.print("Ingrese el número de créditos del curso: ");
        int creditosCurso = sc.nextInt();

        System.out.print("Ingrese la versión del curso: ");
        String versionCurso = sc.nextLine();

        sc.nextLine();

        return new Curso(idCurso, nombreCurso, descripcionCurso, creditosCurso, versionCurso);
    }

    private static void realizarInscripcion(GestorAcademico gestor, Scanner sc) {
        Estudiante estudiante = crearEstudiante(sc);
        System.out.print("Ingrese el ID del curso para inscribir al estudiante: ");
        int idCurso = sc.nextInt();
        sc.nextLine();

        try {
            gestor.inscribirEstudianteCurso(estudiante, idCurso);
            System.out.println("Estudiante inscrito correctamente en el curso.");
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error: El estudiante ya está inscrito en el curso.");
        }
    }

    private static void realizarDesinscripcion(GestorAcademico gestor, Scanner sc) {
        System.out.print("Ingrese el ID del estudiante para desinscribir: ");
        int idEstudiante = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el ID del curso para desinscribir al estudiante: ");
        int idCurso = sc.nextInt();
        sc.nextLine();

        try {
            gestor.desinscribirEstudianteCurso(idEstudiante, idCurso);
            System.out.println("Estudiante desinscrito correctamente del curso.");
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println("Error: El estudiante no está inscrito en el curso o el ID del curso no es válido.");
        }
    }

    private static void imprimirEstudiante(Estudiante estudiante) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("ID: " + estudiante.getId() +
                ", Nombre: " + estudiante.getNombre() +
                ", Apellido: " + estudiante.getApellido() +
                ", Fecha de Nacimiento: " + sdf.format(estudiante.getFechaNacimiento()) +
                ", Estado: " + estudiante.getEstado());
    }

    private static void imprimirCurso(Curso curso) {
        System.out.println("ID: " + curso.getId() + ", Nombre: " + curso.getNombre() + ", Descripción: " + curso.getDescripcion() + ", Créditos: "
                + curso.getNumeroCreditos() + ", Versión: " + curso.getVersion());
    }

}
