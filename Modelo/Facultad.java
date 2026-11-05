package Modelo;

public class Facultad {
    
    private String codigo;
    private String nombre;
    private String decano;

    public Facultad(String codigo, String nombre, String decano) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.decano = decano;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDecano() {
        return decano;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Decano: " + decano);
    }
}
