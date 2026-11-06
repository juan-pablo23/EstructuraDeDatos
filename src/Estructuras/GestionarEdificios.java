package Estructuras;

import Modelo.Edificio;

public class GestionarEdificios {

    private Edificio[] edificios;
    private int[][] distancias;
    private int cantidadEdificios;

    public GestionarEdificios(int capacidad) {

        if (capacidad < 5) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser de minimo 5 edificios.");
        }

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

    public void mostrarEdificio(String codigo) {
        int posicion = buscarEdificio(codigo);

        if (posicion == -1) {
            System.out.println("No se encontro el edificio.");
            return;
        }

        System.out.println("\n=== EDIFICIO ENCONTRADO ===");
        edificios[posicion].mostrarInformacion();
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

    public void calcularRutaMasCorta(String codigoOrigen, String codigoDestino) {
        int origen = buscarEdificio(codigoOrigen);
        int destino = buscarEdificio(codigoDestino);

        if (origen == -1 || destino == -1) {
            System.out.println("Error: uno de los edificios no existe.");
            return;
        }

        int cantidad = cantidadEdificios;
        int[] distanciasMinimas = new int[cantidad];
        int[] anteriores = new int[cantidad];
        boolean[] visitados = new boolean[cantidad];

        for (int i = 0; i < cantidad; i++) {
            distanciasMinimas[i] = Integer.MAX_VALUE;
            anteriores[i] = -1;
            visitados[i] = false;
        }

        distanciasMinimas[origen] = 0;

        for (int i = 0; i < cantidad; i++) {
            int actual = -1;
            int menorDistancia = Integer.MAX_VALUE;

            for (int j = 0; j < cantidad; j++) {

                if (!visitados[j] && distanciasMinimas[j] < menorDistancia) {
                    menorDistancia = distanciasMinimas[j];
                    actual = j;
                }
            }

            if (actual == -1) {
                break;
            }

            visitados[actual] = true;

            if (actual == destino) {
                break;
            }

            for (int j = 0; j < cantidad; j++) {
                int distancia = distancias[actual][j];

                if (!visitados[j] && distancia > 0 && distanciasMinimas[actual] != Integer.MAX_VALUE) {
                    int nuevaDistancia = distanciasMinimas[actual] + distancia;

                    if (nuevaDistancia < distanciasMinimas[j]) {
                        distanciasMinimas[j] = nuevaDistancia;
                        anteriores[j] = actual;
                    }
                }
            }
        }

        if (distanciasMinimas[destino] == Integer.MAX_VALUE) {
            System.out.println("No existe una ruta entre los edificios.");
            return;
        }

        int[] ruta = new int[cantidad];
        int cantidadRuta = 0;
        int actual = destino;

        while (actual != -1) {
            ruta[cantidadRuta] = actual;
            cantidadRuta++;
            actual = anteriores[actual];
        }

        System.out.println("\n=== RUTA MAS CORTA ===");

        for (int i = cantidadRuta - 1; i >= 0; i--) {
            System.out.print(edificios[ruta[i]].getNombre());

            if (i > 0) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.println("Distancia total: " + distanciasMinimas[destino] + " metros.");
    }

    public void eliminarEdificio(String codigo) {
        int posicion = buscarEdificio(codigo);

        if (posicion == -1) {
            System.out.println("No se encontro el edificio.");
            return;
        }

        for (int i = posicion; i < cantidadEdificios - 1; i++) {
            edificios[i] = edificios[i + 1];
        }

        edificios[cantidadEdificios - 1] = null;

        for (int i = posicion; i < cantidadEdificios - 1; i++) {
            for (int j = 0; j < cantidadEdificios; j++) {
                distancias[i][j] = distancias[i + 1][j];
            }
        }

        for (int j = posicion; j < cantidadEdificios - 1; j++) {
            for (int i = 0; i < cantidadEdificios - 1; i++) {
                distancias[i][j] = distancias[i][j + 1];
            }
        }

        cantidadEdificios--;

        for (int i = 0; i < edificios.length; i++) {
            distancias[cantidadEdificios][i] = -1;
            distancias[i][cantidadEdificios] = -1;
        }

        distancias[cantidadEdificios][cantidadEdificios] = 0;
        System.out.println("Edificio eliminado correctamente.");
    }
}
