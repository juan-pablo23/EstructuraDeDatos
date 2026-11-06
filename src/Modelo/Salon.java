
package Modelo;

import Excepciones.HorarioConflictivoException;

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

    public boolean consultarDisponibilidad(int dia, int hora, int duracion) {

        if (dia < 0 || dia >= 7 || hora < 0 || duracion <= 0
                || hora + duracion > 24) {
            return false;
        }

        for (int i = hora; i < hora + duracion; i++) {
            if (!disponibilidad[dia][i]) {
                return false;
            }
        }

        return true;
    }

    public void reservarHorario(int dia, int hora) {
        reservarHorario(dia, hora, 1);
    }

    public void reservarHorario(int dia, int hora, int duracion) {

        if (!consultarDisponibilidad(dia, hora, duracion)) {
            throw new HorarioConflictivoException(
                    "El horario no es valido o el salon esta ocupado.");
        }

        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = false;
        }
    }

    public void liberarHorario(int dia, int hora) {
        liberarHorario(dia, hora, 1);
    }

    public void liberarHorario(int dia, int hora, int duracion) {

        if (dia < 0 || dia >= 7 || hora < 0 || duracion <= 0
                || hora + duracion > 24) {
            throw new HorarioConflictivoException(
                    "El horario que intenta liberar no es valido.");
        }

        for (int i = hora; i < hora + duracion; i++) {
            disponibilidad[dia][i] = true;
        }

        System.out.println("Horario liberado correctamente.");
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Capacidad: " + capacidad);
    }
}
