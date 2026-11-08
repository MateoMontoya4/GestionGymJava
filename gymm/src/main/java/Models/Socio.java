package Models;

/**
 * Entidad que representa a un socio del gimnasio.
 */
public class Socio {

    private int idSocio;
    private String nombre;
    private int dni;
    private String telefono;
    private String email;
    private String aptMedico;
    private String estadoMembresia;

    /** Constructor vacío: permite crear el objeto y completar los datos con los setters. */
    public Socio() {
    }

    /**
     * Constructor completo.
     *
     * @param idSocio         identificador del socio (lo asigna la base de datos)
     * @param nombre          nombre y apellido
     * @param dni             documento de identidad
     * @param telefono        teléfono de contacto
     * @param email           correo electrónico
     * @param aptMedico       si presentó el apto médico (por ejemplo "Sí" o "No")
     * @param estadoMembresia estado de la membresía (por ejemplo "Activo")
     */
    public Socio(int idSocio, String nombre, int dni, String telefono, String email,
            String aptMedico, String estadoMembresia) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.aptMedico = aptMedico;
        this.estadoMembresia = estadoMembresia;
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

    /**
     * Devuelve el nombre del socio.
     *
     * @return el nombre del socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del socio.
     *
     * @param nombre el nuevo nombre del socio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el DNI del socio.
     *
     * @return el DNI del socio
     */
    public int getDni() {
        return dni;
    }

    /**
     * Modifica el DNI del socio.
     *
     * @param dni el nuevo DNI del socio
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Devuelve el teléfono del socio.
     *
     * @return el teléfono del socio
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el teléfono del socio.
     *
     * @param telefono el nuevo teléfono del socio
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve el email del socio.
     *
     * @return el email del socio
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica el email del socio.
     *
     * @param email el nuevo email del socio
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve el estado del apto médico.
     *
     * @return el estado del apto médico
     */
    public String getAptMedico() {
        return aptMedico;
    }

    /**
     * Modifica el estado del apto médico.
     *
     * @param aptMedico el nuevo estado del apto médico
     */
    public void setAptMedico(String aptMedico) {
        this.aptMedico = aptMedico;
    }

    /**
     * Devuelve el estado de la membresía.
     *
     * @return el estado de la membresía
     */
    public String getEstadoMembresia() {
        return estadoMembresia;
    }

    /**
     * Modifica el estado de la membresía.
     *
     * @param estadoMembresia el nuevo estado de la membresía
     */
    public void setEstadoMembresia(String estadoMembresia) {
        this.estadoMembresia = estadoMembresia;
    }
}
