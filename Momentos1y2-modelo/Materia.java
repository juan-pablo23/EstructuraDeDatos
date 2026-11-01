import java.util.LinkedList;
public class Materia {
    
    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int creditos;
    private LinkedList<String> prerrequisitos;

    public Materia (String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
        this.prerrequisitos = new LinkedList<>();
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

    public void agregarPrerrequisito(String codigoMateria) {
        prerrequisitos.add(codigoMateria);
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cupos maximos: " + cuposMaximos);
        System.out.println("Creditos: " + creditos);
        System.out.println("Prerrequisitos: " + prerrequisitos);
    }
}
