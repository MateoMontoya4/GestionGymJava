package Models;

/**
 * Entidad que representa una actividad que ofrece el gimnasio (yoga, pesas, etc.).
 */
public class Actividad {

    private int idActividad;
    private String nombreActividad;
    private int cupos;
    private String horarios;
    private String profesor;
    private double precio;

    /** Constructor vacío: permite crear el objeto y completar los datos con los setters. */
    public Actividad() {
    }

    /**
     * Constructor completo.
     *
     * @param idActividad     identificador de la actividad (lo asigna la base de datos)
     * @param nombreActividad nombre de la actividad (yoga, pesas, etc.)
     * @param cupos           cantidad máxima de lugares disponibles
     * @param horarios        horario en que se dicta
     * @param profesor        nombre del profesor a cargo
     * @param precio          precio de la actividad
     */
    public Actividad(int idActividad, String nombreActividad, int cupos, String horarios,
            String profesor, double precio) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.cupos = cupos;
        this.horarios = horarios;
        this.profesor = profesor;
        this.precio = precio;
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
     * Devuelve el nombre de la actividad.
     *
     * @return el nombre de la actividad
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Modifica el nombre de la actividad.
     *
     * @param nombreActividad el nuevo nombre de la actividad
     */
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    /**
     * Devuelve la cantidad máxima de cupos.
     *
     * @return la cantidad máxima de cupos
     */
    public int getCupos() {
        return cupos;
    }

    /**
     * Modifica la cantidad máxima de cupos.
     *
     * @param cupos la nueva cantidad máxima de cupos
     */
    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    /**
     * Devuelve el horario de la actividad.
     *
     * @return el horario de la actividad
     */
    public String getHorarios() {
        return horarios;
    }

    /**
     * Modifica el horario de la actividad.
     *
     * @param horarios el nuevo horario de la actividad
     */
    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    /**
     * Devuelve el nombre del profesor.
     *
     * @return el nombre del profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Modifica el nombre del profesor.
     *
     * @param profesor el nuevo nombre del profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * Devuelve el precio de la actividad.
     *
     * @return el precio de la actividad
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Modifica el precio de la actividad.
     *
     * @param precio el nuevo precio de la actividad
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
