package Estructuras;

import Modelo.Facultad;

public class GestionarFacultades {

    private Facultad[] facultades;
    private int cantidadFacultades;

    public GestionarFacultades() {
        facultades = new Facultad[5];
        cantidadFacultades = 0;
    }

    public void agregarFacultad(Facultad facultad) {

        if (cantidadFacultades >= facultades.length) {
            System.out.println("No se pueden agregar mas facultades.");
            return;
        }

        for (int i = 0; i < cantidadFacultades; i++) {
            
            if (facultades[i].getCodigo().equals(facultad.getCodigo())) {
                System.out.println("La facultad ya existe.");
                return;
            }
        }

        facultades[cantidadFacultades] = facultad;
        cantidadFacultades++;
        System.out.println("Facultad agregada correctamente.");
    }

    public Facultad buscarFacultad(String codigo) {

        for (int i = 0; i < cantidadFacultades; i++) {

            if (facultades[i].getCodigo().equals(codigo)) {
                return facultades[i];
            }
        }

        return null;
    }

    public void mostrarFacultades() {

        if (cantidadFacultades == 0) {
            System.out.println("No hay facultades registradas.");
            return;
        }

        for (int i = 0; i < cantidadFacultades; i++) {
            System.out.println("----------------------");
            facultades[i].mostrarInformacion();
        }
    }

    public void eliminarFacultad(String codigo) {
        int posicion = -1;

        for (int i = 0; i < cantidadFacultades; i++) {

            if (facultades[i].getCodigo().equals(codigo)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("No se encontro la facultad.");
            return;
        }

        for (int i = posicion; i < cantidadFacultades - 1; i++) {
            facultades[i] = facultades[i + 1];
        }

        facultades[cantidadFacultades - 1] = null;
        cantidadFacultades--;
        System.out.println("Facultad eliminada correctamente.");
    }
}