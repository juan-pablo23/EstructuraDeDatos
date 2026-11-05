package Estructuras;

import java.util.Stack;

public class GestionarNavegacionReportes {

    private Stack<String> pilaAtras;
    private Stack<String> pilaAdelante;
    private String reporteActual;

    public GestionarNavegacionReportes() {

        pilaAtras = new Stack<>();
        pilaAdelante = new Stack<>();
        reporteActual = null;
    }

    public void abrirReporte(String nuevoReporte) {
        if (reporteActual != null) {
            pilaAtras.push(reporteActual);
        }

        reporteActual = nuevoReporte;
        pilaAdelante.clear();
        System.out.println("Reporte abierto: " + reporteActual);
    }

    public void volverAtras() {
        if (pilaAtras.isEmpty()) {
            System.out.println("No hay reportes anteriores.");
            return;
        }

        pilaAdelante.push(reporteActual);
        reporteActual = pilaAtras.pop();
        System.out.println("Regresando al reporte: " + reporteActual);
    }

    public void avanzar() {
        if (pilaAdelante.isEmpty()) {
            System.out.println("No hay reportes siguientes.");
            return;
        }

        pilaAtras.push(reporteActual);
        reporteActual = pilaAdelante.pop();
        System.out.println("Avanzando al reporte: " + reporteActual);
    }

    public void mostrarReporteActual() {
        if (reporteActual == null) {
            System.out.println("No hay ningun reporte abierto.");
            return;
        }

        System.out.println("Reporte actual: " + reporteActual);
    }
}
