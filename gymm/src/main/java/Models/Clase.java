package Models;

/**
 * Entidad que representa la inscripción de un {@link Socio} a una {@link Actividad}.
 * Es la tercera entidad del sistema: combina a las otras dos.
 */
public class Clase {

    private int idClase;
    private int idActividad;
    private int idSocio;

    /** Constructor vacío: permite crear el objeto y completar los datos con los setters. */
    public Clase() {
    }

    /**
     * Constructor completo.
     *
     * @param idClase     identificador de la inscripción (lo asigna la base de datos)
     * @param idActividad identificador de la actividad a la que se inscribe
     * @param idSocio     identificador del socio inscripto
     */
    public Clase(int idClase, int idActividad, int idSocio) {
        this.idClase = idClase;
        this.idActividad = idActividad;
        this.idSocio = idSocio;
    }

    /**
     * Devuelve el identificador de la inscripción.
     *
     * @return el identificador de la inscripción
     */
    public int getIdClase() {
        return idClase;
    }

    /**
     * Modifica el identificador de la inscripción.
     *
     * @param idClase el nuevo identificador de la inscripción
     */
    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    /**
     * Devuelve el identificador de la actividad.
     *
     * @return el identificador de la actividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * Modifica el identificador de la actividad.
     *
     * @param idActividad el nuevo identificador de la actividad
     */
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * Devuelve el identificador del socio.
     *
     * @return el identificador del socio
     */
    public int getIdSocio() {
        return idSocio;
    }

    /**
     * Modifica el identificador del socio.
     *
     * @param idSocio el nuevo identificador del socio
     */
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
}
