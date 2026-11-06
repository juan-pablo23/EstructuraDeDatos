package Modelo;

public class Operacion {

    private String tipo;
    private String descripcion;
    private Object dato;
    private Runnable accionDeshacer;
    private Runnable accionRehacer;

    public Operacion(String tipo, String descripcion, Object dato) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dato = dato;
        this.accionDeshacer = null;
        this.accionRehacer = null;
    }

    public Operacion(String tipo, String descripcion, Object dato, Runnable accionDeshacer, Runnable accionRehacer) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dato = dato;
        this.accionDeshacer = accionDeshacer;
        this.accionRehacer = accionRehacer;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Object getDato() {
        return dato;
    }

    public void deshacer() {
        if (accionDeshacer != null) {
            accionDeshacer.run();
        }
    }

    public void rehacer() {
        if (accionRehacer != null) {
            accionRehacer.run();
        }
    }

    public void mostrarInformacion() {
        System.out.println("Tipo de operacion: " + tipo);
        System.out.println("Descripcion: " + descripcion);
    }
}
