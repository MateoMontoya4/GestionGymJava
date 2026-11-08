package Exceptions;

/**
 * Se lanza cuando los datos que ingresó el usuario no son válidos
 * (campo vacío, número mal escrito, opción inexistente, etc.).
 * Se detecta en la capa de controladores, antes de tocar la base de datos.
 */
public class ValidacionException extends GymException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea la excepción con el motivo de la validación fallida.
     *
     * @param mensaje explicación de qué dato es incorrecto y cómo corregirlo
     */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea la excepción con el motivo y la causa original.
     *
     * @param mensaje explicación de qué dato es incorrecto y cómo corregirlo
     * @param causa   excepción original (por ejemplo, un {@link NumberFormatException})
     */
    public ValidacionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
