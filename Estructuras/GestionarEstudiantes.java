package Estructuras;
import Modelo.Estudiante;
import java.util.HashMap;

public class GestionarEstudiantes {
    private HashMap<String, Estudiante> estudiantes;

    public GestionarEstudiantes() {
        estudiantes = new HashMap<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        String id = estudiante.getId();

        if (estudiantes.containsKey(id)) {
            System.out.println("Ya existe un estudiante con esa identificacion.");
            return;
        }

        estudiantes.put(id, estudiante);
        System.out.println("Estudiante registrado correctamente");
    }

    public Estudiante buscarEstudiante(String id) {
        return estudiantes.get(id);
    }

    public void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante estudiante : estudiantes.values()) {
            System.out.println("---------------------------");
            estudiante.mostrarInformacion();
        }
    }

    public void eliminarEstudiantes(String id) {
        if (estudiantes.remove(id) != null) {
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("No se encontro el estudiante.");
        }
    }
}
