package Estructuras;

import Modelo.Edificio;

public class GestionarEdificios {

    private Edificio[] edificios;
    private int[][] distancias;
    private int cantidadEdificios;

    public GestionarEdificios(int capacidad) {
        edificios = new Edificio[capacidad];
        distancias = new int[capacidad][capacidad];
        cantidadEdificios = 0;

        for (int i = 0; i < capacidad; i++) {
            
            for (int j = 0; j < capacidad; j++) {

                if (i == j) {
                    distancias[i][j] = 0;
                } else {
                    distancias[i][j] = -1;
                }
            }
        }
    }

    public void agregarEdificio(Edificio edificio) {
        if (cantidadEdificios >= edificios.length) {
            System.out.println("No hay espacio para mas edificios.");
            return;
        }

        for (int i = 0; i < cantidadEdificios; i++) {

            if (edificios[i].getCodigo().equals(edificio.getCodigo())) {
                System.out.println("El edificio ya existe.");
                return;
            }
        }

        edificios[cantidadEdificios] = edificio;
        cantidadEdificios++;
        System.out.println("Edificio registrado correctamente.");
    }

    public int buscarEdificio(String codigo) {
        for (int i = 0; i < cantidadEdificios; i++) {

            if (edificios[i].getCodigo().equals(codigo)) {
                return i;
            }
        }

        return -1;
    }

    public void mostrarEdificios() {
        if (cantidadEdificios == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }

        for (int i = 0; i < cantidadEdificios; i++) {
            System.out.println("------------------------");
            edificios[i].mostrarInformacion();
        }
    }

    public void agregarDistancia(String codigoOrigen, String codigoDestino, int distancia) {
        int origen = buscarEdificio(codigoOrigen);
        int destino = buscarEdificio(codigoDestino);

        if (origen == -1 || destino == -1) {
            System.out.println("Error: uno de los edificios no existe.");
            return;
        }

        if (distancia <= 0) {
            System.out.println("La distancia debe ser mayor que cero.");
            return;
        }

        distancias[origen][destino] = distancia;
        distancias[destino][origen] = distancia;
        System.out.println("Distancia registrada correctamente.");
    }

    public void mostrarMatrizDistancias() {
        System.out.println("\n=== MATRIZ DE DISTANCIAS ===");

        for (int i = 0; i < cantidadEdificios; i++) {

            for (int j = 0; j < cantidadEdificios; j++) {
                System.out.print(distancias[i][j] + "\t");
            }

            System.out.println();
        }
    }
}
