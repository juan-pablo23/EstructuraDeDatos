package Estructuras;

import Modelo.Salon;
import java.util.TreeMap;

public class GestionarSalones {

    private TreeMap<String, Salon> salones;

    public GestionarSalones() {
        salones = new TreeMap<>();
    }

    public void agregarSalon(Salon salon) {
        String codigo = salon.getCodigo();

        if (salones.containsKey(codigo)) {
            System.out.println("Ya existe un salon con ese codigo.");
            return;
        }

        salones.put(codigo, salon);
        System.out.println("Salon registrado correctamente.");
    }

    public Salon buscarSalon(String codigo) {
        return salones.get(codigo);
    }

    public void mostrarSalones() {

        if (salones.isEmpty()) {
            System.out.println("No hay salones registrados.");
            return;
        }

        for (Salon salon : salones.values()) {
            System.out.println("------------------------");
            salon.mostrarInformacion();
        }
    }

    public void eliminarSalon(String codigo) {

        if (salones.remove(codigo) != null) {
            System.out.println("Salon eliminado correctamente.");
        } else {
            System.out.println("No se encontro el salon.");
        }
    }
}
