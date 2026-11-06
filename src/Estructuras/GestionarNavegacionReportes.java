package Estructuras;

import java.util.Stack;

public class GestionarNavegacionReportes {

    private Stack<String> pilaAtras;
    private Stack<String> pilaAdelante;
    private String reporteActual;
    private String idEstudianteReporte;

    public GestionarNavegacionReportes() {
        pilaAtras = new Stack<>();
        pilaAdelante = new Stack<>();
        reporteActual = null;
        idEstudianteReporte = null;
    }

    public void abrirReporte(String nuevoReporte) {

        if (reporteActual != null) {
            pilaAtras.push(reporteActual);
        }

        reporteActual = nuevoReporte;
        idEstudianteReporte = null;
        pilaAdelante.clear();

        System.out.println("Reporte abierto: " + nuevoReporte);
    }

    public void abrirReporteAcademico(String idEstudiante) {

        if (reporteActual != null) {
            pilaAtras.push(reporteActual);
        }

        pilaAdelante.clear();

        idEstudianteReporte = idEstudiante;

        // Guardamos también el ID para conservarlo en la navegación.
        reporteActual = "Reporte academico individual|" + idEstudiante;

        System.out.println("Reporte academico abierto.");
    }

    public void volverAtras() {

        if (pilaAtras.isEmpty()) {
            System.out.println("No hay reportes anteriores.");
            return;
        }

        pilaAdelante.push(reporteActual);
        reporteActual = pilaAtras.pop();

        System.out.println("Regresando al reporte: " + obtenerNombreReporte());
    }

    public void avanzar() {

        if (pilaAdelante.isEmpty()) {
            System.out.println("No hay reportes siguientes.");
            return;
        }

        pilaAtras.push(reporteActual);
        reporteActual = pilaAdelante.pop();

        System.out.println("Avanzando al reporte: " + obtenerNombreReporte());
    }

    public void mostrarReporteActual() {

        if (reporteActual == null) {
            System.out.println("No hay ningun reporte abierto.");
            return;
        }

        System.out.println("Reporte actual: " + obtenerNombreReporte());
    }

    private String obtenerNombreReporte() {

        if (reporteActual.startsWith("Reporte academico individual|")) {
            return "Reporte academico individual";
        }

        return reporteActual;
    }

    public void mostrarContenidoReporte(
            GestionarEstudiantes estudiantes,
            GestionarMaterias materias,
            GestionarSalones salones,
            GestionarHorarios horarios,
            GestionarEdificios edificios,
            GestionarFacultades facultades) {

        if (reporteActual == null || reporteActual.isEmpty()) {
            System.out.println("No hay ningun reporte abierto.");
            return;
        }

        System.out.println("\n========== "
                + obtenerNombreReporte().toUpperCase()
                + " ==========");

        if (reporteActual.startsWith("Reporte academico individual|")) {

            idEstudianteReporte = reporteActual.substring(
                    "Reporte academico individual|".length());

            estudiantes.mostrarReporteAcademico(idEstudianteReporte);

        } else {

            switch (reporteActual) {

                case "Reporte de estudiantes":
                    estudiantes.mostrarEstudiantes();
                    break;

                case "Reporte de materias":
                    materias.mostrarMaterias();
                    break;

                case "Reporte de salones":
                    salones.mostrarSalones();
                    break;

                case "Reporte de horarios":
                    horarios.mostrarHorarios();
                    break;

                case "Reporte de edificios":
                    edificios.mostrarEdificios();
                    break;

                case "Reporte de facultades":
                    facultades.mostrarFacultades();
                    break;

                default:
                    System.out.println("No se reconoce el reporte seleccionado.");
            }
        }

        System.out.println("==========================================");
    }
}