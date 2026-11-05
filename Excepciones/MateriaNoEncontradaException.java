package Excepciones;

public class MateriaNoEncontradaException extends RuntimeException {

    public MateriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
