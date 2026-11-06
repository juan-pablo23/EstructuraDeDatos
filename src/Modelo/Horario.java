
package Modelo;

public class Horario {

    private String codigoMateria;
    private String codigoSalon;
    private int dia;
    private int hora;
    private int duracion;

    public Horario(String codigoMateria, String codigoSalon,
            int dia, int hora) {

        this(codigoMateria, codigoSalon, dia, hora, 1);
    }

    public Horario(String codigoMateria, String codigoSalon,
            int dia, int hora, int duracion) {

        this.codigoMateria = codigoMateria;
        this.codigoSalon = codigoSalon;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
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

    public int getDuracion() {
        return duracion;
    }

    public void mostrarInformacion() {

        String[] dias = {
            "Lunes", "Martes", "Miercoles", "Jueves",
            "Viernes", "Sabado", "Domingo"
        };

        System.out.println("Materia: " + codigoMateria);
        System.out.println("Salon: " + codigoSalon);
        System.out.println("Dia: " + dias[dia]);
        System.out.println("Hora de inicio: " + hora + ":00");
        System.out.println("Duracion: " + duracion + " hora(s)");
        System.out.println("Hora de finalizacion: "
                + (hora + duracion) + ":00");
    }
}
