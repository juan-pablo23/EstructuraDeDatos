
package Estructuras;

import Modelo.Horario;
import Modelo.Salon;
import Excepciones.HorarioConflictivoException;
import java.util.LinkedList;

public class GestionarHorarios {

    private LinkedList<Horario> horarios;

    public GestionarHorarios() {
        horarios = new LinkedList<>();
    }

    public void asignarHorario(String codigoMateria, String codigoSalon,
            int dia, int hora, GestionarSalones gestionSalones) {

        asignarHorario(codigoMateria, codigoSalon, dia, hora,
                1, gestionSalones);
    }

    public void asignarHorario(String codigoMateria, String codigoSalon,
            int dia, int hora, int duracion,
            GestionarSalones gestionSalones) {

        Salon salon = gestionSalones.buscarSalon(codigoSalon);

        if (salon == null) {
            System.out.println("Error: el salon no existe.");
            return;
        }

        try {

            salon.reservarHorario(dia, hora, duracion);

            Horario nuevoHorario = new Horario(
                    codigoMateria, codigoSalon,
                    dia, hora, duracion);

            horarios.add(nuevoHorario);

            System.out.println("Horario asignado correctamente.");

        } catch (HorarioConflictivoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarHorarios() {

        if (horarios.isEmpty()) {
            System.out.println("No hay horarios registrados.");
            return;
        }

        for (Horario horario : horarios) {
            System.out.println("------------------------");
            horario.mostrarInformacion();
        }
    }

    public void eliminarHorario(String codigoMateria, String codigoSalon,
            int dia, int hora, GestionarSalones gestionSalones) {

        Horario horarioEliminar = null;

        for (Horario horario : horarios) {

            if (horario.getCodigoMateria().equals(codigoMateria)
                    && horario.getCodigoSalon().equals(codigoSalon)
                    && horario.getDia() == dia
                    && horario.getHora() == hora) {

                horarioEliminar = horario;
                break;
            }
        }

        if (horarioEliminar == null) {
            System.out.println("No se encontro el horario.");
            return;
        }

        horarios.remove(horarioEliminar);

        Salon salon = gestionSalones.buscarSalon(codigoSalon);

        if (salon != null) {
            salon.liberarHorario(
                    dia, hora, horarioEliminar.getDuracion());
        }

        System.out.println("Horario eliminado correctamente.");
    }

    public void eliminarHorario(String codigoMateria, String codigoSalon,
            int dia, int hora, GestionarSalones gestionSalones,
            GestionarOperaciones gestionarOperaciones) {

        Horario horarioEliminar = null;

        for (Horario horario : horarios) {

            if (horario.getCodigoMateria().equals(codigoMateria)
                    && horario.getCodigoSalon().equals(codigoSalon)
                    && horario.getDia() == dia
                    && horario.getHora() == hora) {

                horarioEliminar = horario;
                break;
            }
        }

        if (horarioEliminar == null) {
            System.out.println("No se encontro el horario.");
            return;
        }

        Salon salon = gestionSalones.buscarSalon(codigoSalon);

        if (salon == null) {
            System.out.println("No se encontro el salon.");
            return;
        }

        Horario horarioGuardado = horarioEliminar;

        horarios.remove(horarioEliminar);

        salon.liberarHorario(
                dia, hora, horarioGuardado.getDuracion());

        Modelo.Operacion operacion = new Modelo.Operacion(
                "HORARIO",
                "Eliminacion de horario de la materia " + codigoMateria,
                horarioGuardado,

                () -> {
                    salon.reservarHorario(
                            dia, hora, horarioGuardado.getDuracion());

                    horarios.add(horarioGuardado);

                    System.out.println("Horario recuperado.");
                },

                () -> {
                    horarios.remove(horarioGuardado);

                    salon.liberarHorario(
                            dia, hora, horarioGuardado.getDuracion());

                    System.out.println("Eliminacion repetida.");
                });

        gestionarOperaciones.registrarOperacion(operacion);

        System.out.println("Horario eliminado correctamente.");
    }
}
