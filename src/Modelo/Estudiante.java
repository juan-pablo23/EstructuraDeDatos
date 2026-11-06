
package Modelo;

import java.util.LinkedList;

public class Estudiante extends Persona {

    private int semestre;
    private Double[][] notas;
    private LinkedList<String> historialMaterias;
    private String[][] codigosMaterias;
    private LinkedList<String> materiasAprobadas;

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);

        this.semestre = semestre;
        this.notas = new Double[10][20];
        this.historialMaterias = new LinkedList<>();
        this.codigosMaterias = new String[10][20];
        this.materiasAprobadas = new LinkedList<>();
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        if (semestre >= 1 && semestre <= 10) {
            this.semestre = semestre;
        } else {
            System.out.println("El semestre debe estar entre 1 y 10.");
        }
    }

    public Double[][] getNotas() {
        return notas;
    }

    public LinkedList<String> getHistorialMaterias() {
        return historialMaterias;
    }

    public LinkedList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Email: " + getEmail());
        System.out.println("Semestre: " + semestre);
    }

    public void registrarMateriasAprobadas(String codigoMateria) {
        if (!materiasAprobadas.contains(codigoMateria)) {
            materiasAprobadas.add(codigoMateria);
        }
    }

    public boolean haAprobadoMateria(String codigoMateria) {
        return materiasAprobadas.contains(codigoMateria);
    }

    public void asociarMateria(int indiceSemestre, int indiceMateria,
            String codigoMateria) {

        if (!posicionValida(indiceSemestre, indiceMateria)) {
            System.out.println("Posicion fuera de la matriz.");
            return;
        }

        if (codigoMateria == null || codigoMateria.trim().isEmpty()) {
            System.out.println("Codigo de materia no valido.");
            return;
        }

        for (int i = 0; i < 20; i++) {
            if (codigoMateria.equals(codigosMaterias[indiceSemestre][i])
                    && i != indiceMateria) {
                System.out.println("La materia ya esta asociada a este semestre.");
                return;
            }
        }

        codigosMaterias[indiceSemestre][indiceMateria] = codigoMateria;

        System.out.println("Materia asociada correctamente.");
    }

    public void asociarMateria(int indiceMateria, String codigoMateria) {
        asociarMateria(semestre - 1, indiceMateria, codigoMateria);
    }

    public void registrarNota(int indiceSemestre, int indiceMateria,
            double nota) {

        if (!posicionValida(indiceSemestre, indiceMateria)) {
            System.out.println("Posicion fuera de la matriz.");
            return;
        }

        if (codigosMaterias[indiceSemestre][indiceMateria] == null) {
            System.out.println("Primero debe asociar una materia a esta posicion.");
            return;
        }

        if (nota < 0.0 || nota > 5.0) {
            System.out.println("La nota debe estar entre 0 y 5.");
            return;
        }

        notas[indiceSemestre][indiceMateria] = nota;
        String codigo = codigosMaterias[indiceSemestre][indiceMateria];

        if (nota >= 3.0) {
            registrarMateriasAprobadas(codigo);
        } else {
            materiasAprobadas.remove(codigo);
        }

        System.out.println("Nota registrada correctamente.");
    }

    private boolean posicionValida(int indiceSemestre, int indiceMateria) {
        return indiceSemestre >= 0 && indiceSemestre < 10
                && indiceMateria >= 0 && indiceMateria < 20;
    }

    public double calcularPromedio(int indiceSemestre) {

        if (indiceSemestre < 0 || indiceSemestre >= 10) {
            System.out.println("Semestre no valido.");
            return -1;
        }

        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 20; i++) {
            if (notas[indiceSemestre][i] != null) {
                suma += notas[indiceSemestre][i];
                cantidad++;
            }
        }

        if (cantidad == 0) {
            return -1;
        }

        return suma / cantidad;
    }

    public double calcularPromedioSemestre(int indiceSemestre) {
        return calcularPromedio(indiceSemestre);
    }

    public double calcularPromedioAcumulado() {

        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {

                if (notas[i][j] != null) {
                    suma += notas[i][j];
                    cantidad++;
                }
            }
        }

        if (cantidad == 0) {
            return -1;
        }

        return suma / cantidad;
    }

    public void mostrarNotasMateria(int indiceSemestre) {

        if (indiceSemestre < 0 || indiceSemestre >= 10) {
            System.out.println("Semestre no valido.");
            return;
        }

        System.out.println("=== NOTAS DEL SEMESTRE "
                + (indiceSemestre + 1) + " ===");

        for (int i = 0; i < 20; i++) {

            if (codigosMaterias[indiceSemestre][i] != null) {

                System.out.println("Materia: "
                        + codigosMaterias[indiceSemestre][i]);

                if (notas[indiceSemestre][i] != null) {
                    System.out.println("Nota: "
                            + notas[indiceSemestre][i]);
                } else {
                    System.out.println("Sin nota registrada.");
                }
            }
        }

        double promedio = calcularPromedio(indiceSemestre);

        if (promedio != -1) {
            System.out.println("Promedio del semestre: " + promedio);
        }
    }

    public void finalizarMateria(int indiceMateria) {

        int indiceSemestre = semestre - 1;

        if (!posicionValida(indiceSemestre, indiceMateria)) {
            System.out.println("Posicion no valida.");
            return;
        }

        String codigo = codigosMaterias[indiceSemestre][indiceMateria];
        Double nota = notas[indiceSemestre][indiceMateria];

        if (codigo == null || nota == null) {
            System.out.println("La materia o su nota no estan registradas.");
            return;
        }

        if (!historialMaterias.contains(codigo)) {
            historialMaterias.add(codigo);
        }

        if (nota >= 3.0) {
            registrarMateriasAprobadas(codigo);
            System.out.println("Materia aprobada correctamente.");
        } else {
            materiasAprobadas.remove(codigo);
            System.out.println("Materia reprobada.");
        }

        System.out.println("Codigo: " + codigo);
        System.out.println("Nota final: " + nota);
    }

    public void mostrarReporteAcademico() {

        System.out.println("\n========== REPORTE ACADEMICO ==========");

        mostrarInformacion();

        int aprobadas = 0;
        int reprobadas = 0;
        int materiasConNotas = 0;

        for (int i = 0; i < 10; i++) {

            boolean tieneMaterias = false;

            for (int j = 0; j < 20; j++) {

                if (codigosMaterias[i][j] != null) {
                    tieneMaterias = true;
                    break;
                }
            }

            if (!tieneMaterias) {
                continue;
            }

            System.out.println("\n========== SEMESTRE "
                    + (i + 1) + " ==========");

            for (int j = 0; j < 20; j++) {

                String codigo = codigosMaterias[i][j];

                if (codigo == null) {
                    continue;
                }

                System.out.println("\nMateria: " + codigo);

                if (notas[i][j] == null) {
                    System.out.println("Sin nota registrada.");
                    continue;
                }

                double nota = notas[i][j];

                System.out.println("Nota: " + nota);
                materiasConNotas++;

                if (nota >= 3.0) {
                    aprobadas++;
                    System.out.println("Estado: APROBADA");
                } else {
                    reprobadas++;
                    System.out.println("Estado: REPROBADA");
                }
            }

            double promedio = calcularPromedio(i);

            if (promedio != -1) {
                System.out.println("\nPromedio del semestre: " + promedio);
            }
        }

        System.out.println("\n========== RESUMEN ==========");
        System.out.println("Materias con notas: " + materiasConNotas);
        System.out.println("Materias aprobadas: " + aprobadas);
        System.out.println("Materias reprobadas: " + reprobadas);

        double promedioAcumulado = calcularPromedioAcumulado();

        if (promedioAcumulado != -1) {
            System.out.println("Promedio acumulado: " + promedioAcumulado);
        } else {
            System.out.println("No hay calificaciones registradas.");
        }

        System.out.println("======================================");
    }

    public String getCodigoMateria(int indiceSemestre, int indiceMateria) {
        if (!posicionValida(indiceSemestre, indiceMateria)) {
            return null;
        }

        return codigosMaterias[indiceSemestre][indiceMateria];
    }

    public void deshacerRegistroNota(int indiceSemestre, int indiceMateria) {
        if (posicionValida(indiceSemestre, indiceMateria)) {
            notas[indiceSemestre][indiceMateria] = null;
            codigosMaterias[indiceSemestre][indiceMateria] = null;
        }
    }
}
