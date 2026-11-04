package Modelo;

import java.util.LinkedList;

public class Estudiante extends Persona {

    private int semestre;
    private Double[][] notas;
    private LinkedList<String> historialMaterias;
    private String[] codigosMaterias;

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);

        this.semestre = semestre;
        this.notas = new Double[10][20];
        this.historialMaterias = new LinkedList<>();
        this.codigosMaterias = new String[10];
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Double[][] getNotas() {
        return notas;
    }

    public LinkedList<String> getHistorialMaterias() {
        return historialMaterias;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Email: " + getEmail());
        System.out.println("Semestre: " + semestre);
    }

    private LinkedList<String> materiasAprobadas; {
        this.materiasAprobadas = new LinkedList<>();
    }

    public void registrarMateriasAprobadas(String codigoMateria) {
        if (!materiasAprobadas.contains(codigoMateria)) {
            materiasAprobadas.add(codigoMateria);
        }
    }

    public boolean haAprobadoMateria(String codigoMateria) {
        return materiasAprobadas.contains(codigoMateria);
    }

    public LinkedList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void registrarNota(int indiceMateria, int indiceEvaluacion, double nota) {

        if (indiceMateria < 0 || indiceMateria >= 10 || indiceEvaluacion < 0 || indiceEvaluacion >= 20) {
            System.out.println("Error: posicion fuera de la matriz.");
            return;
        }

        if (nota < 0.0 || nota > 5.0) {
            System.out.println("Error: la nota debe estar entre 0 y 5.");
            return;
        }

        notas[indiceMateria][indiceEvaluacion] = nota;
        System.out.println("Nota registrada correctamente.");
    }

    public double calcularPromedio(int indiceMateria) {
        if (indiceMateria < 0 || indiceMateria >= 10) {
            System.out.println("Error: materia no valida.");
            return -1;
        }

        double suma = 0;
        int cantidadNotas = 0;

        for (int i = 0; i < 20; i++) {

            if (notas[indiceMateria][i] != null) {
                suma += notas[indiceMateria][i];
                cantidadNotas++;
            }
        }

        if (cantidadNotas == 0) {
            System.out.println("La materia no tiene notas registradas.");
            return -1;
        }

        return suma / cantidadNotas;
    }

    public void mostrarNotasMateria(int indiceMateria) {
        if (indiceMateria < 0 || indiceMateria >= 10) {
            System.out.println("Error: materia no valida.");
            return;
        }

        System.out.println("=== CALIFICACIONES ===");

        for (int i = 0; i < 20; i++) {

            if (notas[indiceMateria][i] != null) {
                System.out.println(
                        "Evaluacion " + (i + 1) + ": "
                                + notas[indiceMateria][i]);
            }
        }

        double promedio = calcularPromedio(indiceMateria);

        if (promedio != -1) {
            System.out.println("Promedio: " + promedio);

            if (promedio >= 3.0) {
                System.out.println("Estado: APROBADA");
            } else {
                System.out.println("Estado: REPROBADA");
            }
        }
    }

    public void asociarMateria(int indiceMateria, String codigoMateria) {
        if (indiceMateria < 0 || indiceMateria >= 10) {
            System.out.println("Error: posicion de materia no valida.");
            return;
        }

        if (codigoMateria == null || codigoMateria.isEmpty()) {
            System.out.println("Error: codigo de materia no valido.");
            return;
        }

        for (int i = 0; i < 10; i++) {

            if (codigoMateria.equals(codigosMaterias[i]) && i != indiceMateria) {
                System.out.println("La materia ya esta asociada a otra fila.");
                return;
            }
        }

        codigosMaterias[indiceMateria] = codigoMateria;
        System.out.println("Materia asociada correctamente.");
    }

    public void finalizarMateria(int indiceMateria) {
        if (indiceMateria < 0 || indiceMateria >= 10) {
            System.out.println("Error: materia no valida.");
            return;
        }

        String codigo = codigosMaterias[indiceMateria];

        if (codigo == null) {
            System.out.println("La fila no tiene una materia asociada.");
            return;
        }

        double promedio = calcularPromedio(indiceMateria);

        if (promedio == -1) {
            System.out.println("No se puede finalizar una materia sin notas.");
            return;
        }

        if (!historialMaterias.contains(codigo)) {
            historialMaterias.add(codigo);
        }

        if (promedio >= 3.0) {
            registrarMateriasAprobadas(codigo);

            System.out.println("Materia aprobada correctamente.");

        } else {
            materiasAprobadas.remove(codigo);
            System.out.println("Materia reprobada.");
        }

        System.out.println("Codigo de materia: " + codigo);
        System.out.println("Promedio final: " + promedio);
    }
}
