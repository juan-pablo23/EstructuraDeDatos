package Estructuras;

import Modelo.Operacion;
import java.util.Stack;

public class GestionarOperaciones {
    private Stack<Operacion> pilaDeshacer;
    private Stack<Operacion> pilaRehacer;

    public GestionarOperaciones() {
        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
    }

    public void registrarOperacion(Operacion operacion) {
        pilaDeshacer.push(operacion);
        pilaRehacer.clear();
        System.out.println("Operacion registrada correctamente.");
    }

    public void deshacerOperacion() {
        if (pilaDeshacer.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }

        Operacion operacion = pilaDeshacer.pop();
        operacion.deshacer();
        pilaRehacer.push(operacion);
        System.out.println("Operacion deshecha: " + operacion.getDescripcion());
    }

    public void rehacerOperacion() {
        if (pilaRehacer.isEmpty()) {
            System.out.println("No hay operaciones para rehacer.");
            return;
        }

        Operacion operacion = pilaRehacer.pop();
        operacion.rehacer();
        pilaDeshacer.push(operacion);
        System.out.println("Operacion rehecha: " + operacion.getDescripcion());
    }
}
