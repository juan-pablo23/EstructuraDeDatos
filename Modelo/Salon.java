package Modelo;

public class Salon {

    private String codigo;
    private String nombre;
    private int capacidad;
    private boolean[][] disponibilidad;

    public Salon(String codigo, String nombre, int capacidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponibilidad = new boolean[7][24];

        for (int dia = 0; dia < 7; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                disponibilidad[dia][hora] = true;
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean estaDisponible(int dia, int hora) {

        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            return false;
        }

        return disponibilidad[dia][hora];
    }

    public boolean reservarHorario(int dia, int hora) {

        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            System.out.println("Error: dia u hora no validos.");
            return false;
        }

        if (!disponibilidad[dia][hora]) {
            System.out.println("El salon ya esta ocupado en ese horario.");
            return false;
        }

        disponibilidad[dia][hora] = false;

        System.out.println("Horario reservado correctamente.");
        return true;
    }

    public void liberarHorario(int dia, int hora) {

        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            System.out.println("Error: dia u hora no validos.");
            return;
        }

        disponibilidad[dia][hora] = true;

        System.out.println("Horario liberado correctamente.");
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Capacidad: " + capacidad);
    }
    
}
