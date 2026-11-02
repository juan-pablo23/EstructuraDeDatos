package Modelo;
import java.util.LinkedList;
import java.util.Queue;

public class Materia {
    
    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int creditos;
    private LinkedList<String> prerrequisitos;
    private LinkedList<String> estudiantesInscritos;
    private Queue<String> colaEspera;

    public Materia (String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
        this.prerrequisitos = new LinkedList<>();
        this.estudiantesInscritos = new LinkedList<>();
        this.colaEspera = new LinkedList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public int getCreditos() {
        return creditos;
    }

    public LinkedList<String> getPrerrequisitos() {
        return prerrequisitos;
    }

    public LinkedList<String> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public Queue<String> getColaEspera() {
        return colaEspera;
    }

    public void agregarPrerrequisito(String codigoMateria) {
        prerrequisitos.add(codigoMateria);
    }

    public boolean tieneCupo() {
        return estudiantesInscritos.size() < cuposMaximos;
    }

    public void agregarEstudianteInscrito(String idEstudiante) {
        estudiantesInscritos.add(idEstudiante);
    }

    public void agregarEstudianteEnEspera(String idEstudiante) {
        colaEspera.add(idEstudiante);
    }

    public boolean retirarEstudiante(String idEstudiante) {
        return estudiantesInscritos.remove(idEstudiante);
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cupos maximos: " + cuposMaximos);
        System.out.println("Creditos: " + creditos);
        System.out.println("Prerrequisitos: " + prerrequisitos);
        System.out.println("Estudiantes inscritos: " + estudiantesInscritos);
        System.out.println("Estudiantes en espera: " + colaEspera);
    }
}
