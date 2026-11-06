package Excepciones;

public class HorarioConflictivoException extends RuntimeException {

    public HorarioConflictivoException(String mensaje) {
        super(mensaje);
    }
}
