package Exceptions;

/**
 * Excepción base de todo el sistema. Las demás excepciones propias la extienden,
 * de modo que la capa de vista pueda capturar {@code GymException} y mostrar el
 * mensaje al usuario sin importar de qué tipo de problema se trate.
 */
public class GymException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Crea una excepción con un mensaje para el usuario.
     *
     * @param mensaje descripción clara del problema
     */
    public GymException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea una excepción con un mensaje y la causa original.
     *
     * @param mensaje descripción clara del problema
     * @param causa   excepción original que provocó este error (por ejemplo, una {@link java.sql.SQLException})
     */
    public GymException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
