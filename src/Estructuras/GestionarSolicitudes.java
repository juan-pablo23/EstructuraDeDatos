package Estructuras;

import Modelo.Solicitud;
import Modelo.Materia;
import Modelo.Operacion;
import Excepciones.EstudianteNoEncontradoException;
import Excepciones.MateriaNoEncontradaException;
import Excepciones.PrerrequisitoNoCumplidoException;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestionarSolicitudes {

    private Queue<Solicitud> solicitudes;

    public GestionarSolicitudes() {
        solicitudes = new LinkedList<>();
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudes.offer(solicitud);
        System.out.println("Solicitud agregada correctamente.");
    }

    public void cargarSolicitudesCSV(String rutaArchivo) {
        int solicitudesCargadas = 0;

        try (BufferedReader lector = Files.newBufferedReader(Paths.get(rutaArchivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos[0].trim().equalsIgnoreCase("idEstudiante")) {
                    continue;
                }

                if (datos.length != 2) {
                    System.out.println("Fila ignorada: formato incorrecto.");
                    continue;
                }

                String idEstudiante = datos[0].trim();
                String codigoMateria = datos[1].trim();

                Solicitud solicitud = new Solicitud(
                        idEstudiante,
                        codigoMateria);

                agregarSolicitud(solicitud);
                solicitudesCargadas++;
            }

            System.out.println("\nTotal de solicitudes cargadas: " + solicitudesCargadas);

        } catch (IOException e) {

            System.out.println("Error al leer el archivo: " + e.getMessage());
            System.out.println("Carpeta desde donde se ejecuta Java:");
            System.out.println(System.getProperty("user.dir"));
        }
    }

    public void devolverSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
        System.out.println("Solicitud devuelta a la cola.");
    }

    public void retirarSolicitud(Solicitud solicitud) {
        solicitudes.remove(solicitud);
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

    public boolean procesarSolicitud(GestionarEstudiantes gestionEstudiantes, GestionarMaterias gestionMaterias,
            GestionarOperaciones gestionarOperaciones) {

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return false;
        }

        Solicitud solicitud = solicitudes.poll();

        System.out.println("Procesando solicitud:");
        solicitud.mostrarInformacion();

        String idEstudiante = solicitud.getIdEstudiante();

        try {
            Materia materia = gestionMaterias.buscarMateria(
                    solicitud.getCodigoMateria());

            boolean yaEstabaInscrito = materia.getEstudiantesInscritos()
                    .contains(idEstudiante);

            boolean yaEstabaEnEspera = materia.getColaEspera()
                    .contains(idEstudiante);

            if (yaEstabaInscrito || yaEstabaEnEspera) {
                System.out.println("El estudiante ya tiene una solicitud atendida para esta materia.");
                System.out.println("----------------------");
                return false;
            }

            gestionMaterias.inscribirEstudianteRegistrado(
                    solicitud.getCodigoMateria(),
                    idEstudiante,
                    gestionEstudiantes);

            boolean[] estabaInscrito = {
                    materia.getEstudiantesInscritos().contains(idEstudiante)
            };

            boolean estaEnEspera = materia.getColaEspera().contains(idEstudiante);

            if (!estabaInscrito[0] && !estaEnEspera) {
                System.out.println("No fue posible completar la solicitud.");
                System.out.println("----------------------");
                return false;
            }

            Operacion operacion = new Operacion(
                    "SOLICITUD",
                    "Procesamiento de solicitud del estudiante " + idEstudiante,
                    solicitud,

                    () -> {
                        if (estabaInscrito[0]) {
                            gestionMaterias.retirarEstudianteSinProcesarCola(
                                    solicitud.getCodigoMateria(),
                                    idEstudiante);
                        } else {
                            materia.retirarEstudianteEspera(idEstudiante);
                        }

                        devolverSolicitud(solicitud);
                    },

                    () -> {
                        gestionMaterias.inscribirEstudianteRegistrado(
                                solicitud.getCodigoMateria(),
                                idEstudiante,
                                gestionEstudiantes);

                        boolean inscrito = materia.getEstudiantesInscritos()
                                .contains(idEstudiante);

                        boolean enEspera = materia.getColaEspera()
                                .contains(idEstudiante);

                        if (inscrito || enEspera) {
                            retirarSolicitud(solicitud);
                            estabaInscrito[0] = inscrito;
                        }
                    });

            gestionarOperaciones.registrarOperacion(operacion);

            System.out.println("Solicitud procesada correctamente.");

            System.out.println("----------------------");
            return true;

        } catch (EstudianteNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());

        } catch (MateriaNoEncontradaException e) {
            System.out.println("ERROR: " + e.getMessage());

        } catch (PrerrequisitoNoCumplidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("----------------------");
        return false;
    }

    public void procesarTodasLasSolicitudes(GestionarEstudiantes gestionEstudiantes, GestionarMaterias gestionMaterias,
            GestionarOperaciones gestionarOperaciones) {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        while (!solicitudes.isEmpty()) {

            procesarSolicitud(gestionEstudiantes, gestionMaterias, gestionarOperaciones);
        }
    }
}
