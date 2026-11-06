package Excepciones;

public class CupoLlenoException extends RuntimeException {

    public CupoLlenoException(String mensaje) {
        super(mensaje);
    }
}
