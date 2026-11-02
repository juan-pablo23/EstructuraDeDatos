package Modelo;
public class Profesor extends Persona {

    private String departamento;
    private double salario;

    public Profesor (String nombre, String id, String email, String departamento, double salario) {
        super (nombre, id, email);

        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override 
    public void mostrarInformacion(){
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Email: " + getEmail());
        System.out.println("Departamento: " + departamento);
        System.out.println("Salario: " + salario + "$");
    }
}
