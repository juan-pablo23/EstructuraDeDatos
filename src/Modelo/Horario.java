package Modelo;

public class Horario {
    
    private String codigoMateria;
    private String codigoSalon;
    private int dia;
    private int hora;

    public Horario(String codigoMateria, String codigoSalon, int dia, int hora) {
        this.codigoMateria = codigoMateria;
        this.codigoSalon = codigoSalon;
        this.dia = dia;
        this.hora = hora;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public String getCodigoSalon() {
        return codigoSalon;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public void mostrarInformacion() {
        String[] dias = {
            "Lunes", "Martes", "Miercoles", "Jueves",
            "Viernes", "Sabado", "Domingo"
        };

        System.out.println("Materia: " + codigoMateria);
        System.out.println("Salon: " + codigoSalon);
        System.out.println("Dia: " + dias[dia]);
        System.out.println("Hora: " + hora + ":00");
    }
}
