package Estructuras;

import Modelo.Solicitud;
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
}
