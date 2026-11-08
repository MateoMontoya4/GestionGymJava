package Exceptions;

/**
 * Se lanza cuando falla el acceso a la base de datos: no hay conexión, la
 * consulta es rechazada, se viola una restricción (DNI repetido, registro con
 * inscripciones, etc.). Envuelve la {@link java.sql.SQLException} original para
 * que la capa de vista no dependa de JDBC.
 */
public class AccesoDatosException extends GymException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea la excepción con un mensaje para el usuario.
     *
     * @param mensaje descripción clara del problema de datos
     */
    public AccesoDatosException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea la excepción con un mensaje y la causa original.
     *
     * @param mensaje descripción clara del problema de datos
     * @param causa   la {@link java.sql.SQLException} original
     */
    public AccesoDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
