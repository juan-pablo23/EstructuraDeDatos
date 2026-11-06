package Estructuras;

import Modelo.Operacion;
import Excepciones.PilaDeshacerVaciaException;
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
           throw new PilaDeshacerVaciaException(
                   "No hay operaciones para deshacer.");
       }
   
       Operacion operacion = pilaDeshacer.pop();
   
       operacion.deshacer();
   
       pilaRehacer.push(operacion);
   
       System.out.println("Operacion deshecha: "
               + operacion.getDescripcion());
   }
   
   public void rehacerOperacion() {
   
       if (pilaRehacer.isEmpty()) {
           throw new PilaDeshacerVaciaException(
                   "No hay operaciones para rehacer.");
       }
   
       Operacion operacion = pilaRehacer.pop();
   
       operacion.rehacer();
   
       pilaDeshacer.push(operacion);
   
       System.out.println("Operacion rehecha: "
               + operacion.getDescripcion());
   }
}
