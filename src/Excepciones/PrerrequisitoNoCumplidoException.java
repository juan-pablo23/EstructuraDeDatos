package Excepciones;

public class PrerrequisitoNoCumplidoException extends RuntimeException {

    public PrerrequisitoNoCumplidoException(String mensaje) {
        super(mensaje);
    }
}