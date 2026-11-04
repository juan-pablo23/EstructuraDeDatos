package Estructuras;

import Modelo.Materia;
import Modelo.Estudiante;
import java.util.LinkedList;

public class GestionarMaterias {

    private LinkedList<Materia> materias;

    public GestionarMaterias() {
        materias = new LinkedList<>();
    }

    public void agregarMateria(Materia materia) {
        if (buscarMateria(materia.getCodigo()) != null) {
            System.out.println("Error: ya existe una materia con ese codigo.");
            return;
        }

        materias.add(materia);
        System.out.println("Materia agregada correctamente.");
    }

    public Materia buscarMateria(String codigo) {
        for (Materia materia : materias) {

            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }
        }

        return null;
    }

    public void mostrarMaterias() {

        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }

        for (Materia materia : materias) {
            System.out.println("----------------");
            materia.mostrarInformacion();
        }
    }

    public void inscribirEstudiantes(String codigoMateria, String idEstudiante) {
        Materia materia = buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("Error: esa materia no existe.");
            return;
        }

        if (materia.getEstudiantesInscritos().contains(idEstudiante)) {
            System.out.println("El estudiante ya esta inscrito.");
            return;
        }

        if (materia.getColaEspera().contains(idEstudiante)) {
            System.out.println("El estudiante ya esta en cola de espera.");
            return;
        }

        if (materia.tieneCupo()) {
            materia.agregarEstudianteInscrito(idEstudiante);
            System.out.println("Estudiante inscrito correctamente.");

        } else {
            materia.agregarEstudianteEnEspera(idEstudiante);
            System.out.println("No hay cupos disponibles.");
            System.out.println("Estudiante agregado a la cola de espera.");
        }
    }

    public void retirarEstudiante(String codigoMateria, String idEstudiante) {
        Materia materia = buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("Error: esa materia no existe.");
            return;
        }

        boolean retirado = materia.retirarEstudiante(idEstudiante);

        if (!retirado) {
            System.out.println("El estudiante no esta incrito en esta materia.");
            return;
        }

        System.out.println("Estudiante retirado correctamente.");

        if (!materia.getColaEspera().isEmpty()) {
            String siguienteEstudiante = materia.getColaEspera().poll();
            materia.agregarEstudianteInscrito(siguienteEstudiante);
            System.out.println("se libero un cupo.");
            System.out.println("El estudiante: " + siguienteEstudiante + " fue inscrito automaticamente.");
        }
    }

    public void inscribirEstudianteConPrerrequisitos(String codigoMateria, Estudiante estudiante) {
        Materia materia = buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("Error: esa materia no existe.");
            return;
        }

        String idEstudiante = estudiante.getId();

        if (materia.getEstudiantesInscritos().contains(idEstudiante)) {
            System.out.println("El estudiante ya esta inscrito.");
        }

        if (materia.getColaEspera().contains(idEstudiante)) {
            System.out.println("El estudiante ya esta en la cola de espera.");
            return;
        }

        for (String codigoPrerrequisito : materia.getPrerrequisitos()) {
            if (!estudiante.haAprobadoMateria(codigoPrerrequisito)) {
                System.out.println("Inscripcion rechazada. No ha aprobado: " + codigoPrerrequisito);
                return;
            }
        }

        if (materia.tieneCupo()) {
            materia.agregarEstudianteInscrito(idEstudiante);
            System.out.println("Estudiante inscrito correctamente.");

        } else {
            materia.agregarEstudianteEnEspera(idEstudiante);
            System.out.println("No hay cupos disponibles.");
            System.out.println("Estudiante agregado a la cola de espera.");
        }
    }

    public void inscribirEstudianteRegistrado(String codigoMateria, String idEstudiante,
            GestionarEstudiantes gestionEstudiantes) {
        Estudiante estudiante = gestionEstudiantes.buscarEstudiante(idEstudiante);

        if (estudiante == null) {
            System.out.println("Error: el estudiante no esta registrado.");
            return;
        }

        inscribirEstudianteConPrerrequisitos(codigoMateria, estudiante);
    }
}
