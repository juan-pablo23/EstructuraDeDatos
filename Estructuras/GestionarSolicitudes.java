package Estructuras;

import Modelo.Solicitud;
import Excepciones.EstudianteNoEncontradoException;
import Excepciones.MateriaNoEncontradaException;
import Excepciones.PrerrequisitoNoCumplidoException;
import java.util.LinkedList;
import java.util.Queue;

public class GestionarSolicitudes {

    private Queue<Solicitud> solicitudes;

    public GestionarSolicitudes() {
        solicitudes = new LinkedList<>();
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudes.offer(solicitud);
        System.out.println("Solicitud agregada correctamente.");
    }

    public void procesarSolicitud() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        Solicitud solicitud = solicitudes.poll();
        System.out.println("Procesando solicitud:");
        solicitud.mostrarInformacion();
    }

    public void mostrarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        System.out.println("=== SOLICITUDES PENDIENTES ===");

        for (Solicitud solicitud : solicitudes) {
            System.out.println("----------------------");
            solicitud.mostrarInformacion();
        }
    }

    public void procesarTodasLasSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes para procesar.");
            return;
        }

        while (!solicitudes.isEmpty()) {
            procesarSolicitud();
        }

        System.out.println("Todas las solicitudes fueron procesadas.");
    }

    public void procesarSolicitud(GestionarEstudiantes gestionEstudiantes, GestionarMaterias gestionMaterias) {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        Solicitud solicitud = solicitudes.poll();
        System.out.println("Procesando solicitud:");
        solicitud.mostrarInformacion();

        try {
            gestionMaterias.inscribirEstudianteRegistrado(solicitud.getCodigoMateria(), solicitud.getIdEstudiante(),
                    gestionEstudiantes);
        } catch (EstudianteNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (MateriaNoEncontradaException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (PrerrequisitoNoCumplidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("----------------------");
    }

    public void procesarTodasLasSolicitudes(GestionarEstudiantes gestionEstudiantes,
            GestionarMaterias gestionMaterias) {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes para procesar.");
            return;
        }

        while (!solicitudes.isEmpty()) {
            procesarSolicitud(gestionEstudiantes, gestionMaterias);

        }

        System.out.println("Todas las solicitudes fueron procesadas.");
    }
}
