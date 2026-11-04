package Estructuras;

import Modelo.Horario;
import Modelo.Salon;
import java.util.LinkedList;

public class GestionarHorarios {

    private LinkedList<Horario> horarios;

    public GestionarHorarios() {
        horarios = new LinkedList<>();
    }

    public void asignarHorario(String codigoMateria, String codigoSalon, int dia, int hora, GestionarSalones gestionSalones) {
        Salon salon = gestionSalones.buscarSalon(codigoSalon);

        if (salon == null) {
            System.out.println("Error: el salon no existe.");
            return;
        }

        if (!salon.estaDisponible(dia, hora)) {
            System.out.println("Error: el salon esta ocupado.");
            return;
        }

        if (salon.reservarHorario(dia, hora)) {
            Horario nuevoHorario = new Horario(codigoMateria, codigoSalon, dia, hora);
            horarios.add(nuevoHorario);
            System.out.println("Horario asignado correctamente.");
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
}
