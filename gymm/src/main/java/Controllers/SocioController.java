package Controllers;

import DAO.CrudDAO;
import DAO.SocioDAO;
import Exceptions.GymException;
import Exceptions.ValidacionException;
import Models.Socio;
import java.util.List;

/**
 * Controlador de socios: recibe los datos que escribió el usuario en la vista,
 * los valida, arma el objeto {@link Socio} y se lo pasa al DAO. La vista nunca
 * habla directamente con la base de datos.
 */
public class SocioController {

    /** Valores posibles del estado de la membresía (los que ya tenía el combo de la vista). */
    public static final String[] ESTADOS_MEMBRESIA = {"Activo", "Inactivo", "Pendiente"};

    /** Valores posibles del apto médico. */
    public static final String[] APTO_MEDICO = {"Sí", "No"};

    private final CrudDAO<Socio> dao;

    /** Crea el controlador con el DAO real (base de datos MySQL). */
    public SocioController() {
        this(new SocioDAO());
    }

    /**
     * Crea el controlador con el DAO indicado. Permite reemplazar la base de datos
     * por otra implementación (por ejemplo, en pruebas).
     *
     * @param dao DAO que se usará para persistir socios
     */
    public SocioController(CrudDAO<Socio> dao) {
        this.dao = dao;
    }

    /**
     * Valida los datos y registra un nuevo socio.
     *
     * @param nombre nombre y apellido
     * @param dni documento, solo números
     * @param telefono teléfono (opcional)
     * @param email correo electrónico (opcional, pero si se escribe debe tener formato válido)
     * @param aptMedico una de {@link #APTO_MEDICO}
     * @param estado una de {@link #ESTADOS_MEMBRESIA}
     * @throws GymException si algún dato es inválido o falla la base de datos
     */
    public void registrar(String nombre, String dni, String telefono, String email,
            String aptMedico, String estado) throws GymException {
        dao.insertar(construir(0, nombre, dni, telefono, email, aptMedico, estado));
    }

    /**
     * Valida los datos y modifica un socio existente.
     *
     * @param idSocio id del socio a modificar (debe ser mayor que cero)
     * @param nombre nombre y apellido
     * @param dni documento, solo números
     * @param telefono teléfono (opcional)
     * @param email correo electrónico (opcional)
     * @param aptMedico una de {@link #APTO_MEDICO}
     * @param estado una de {@link #ESTADOS_MEMBRESIA}
     * @throws GymException si algún dato es inválido, no hay socio seleccionado o falla la base de datos
     */
    public void modificar(int idSocio, String nombre, String dni, String telefono, String email,
            String aptMedico, String estado) throws GymException {
        exigirSeleccion(idSocio, "modificar");
        dao.actualizar(construir(idSocio, nombre, dni, telefono, email, aptMedico, estado));
    }

    /**
     * Elimina un socio.
     *
     * @param idSocio id del socio (debe ser mayor que cero)
     * @throws GymException si no hay socio seleccionado o falla la base de datos
     */
    public void eliminar(int idSocio) throws GymException {
        exigirSeleccion(idSocio, "eliminar");
        dao.eliminar(idSocio);
    }

    /**
     * Devuelve todos los socios.
     *
     * @return lista de socios
     * @throws GymException si falla la base de datos
     */
    public List<Socio> listar() throws GymException {
        return dao.listar();
    }

    private void exigirSeleccion(int idSocio, String accion) throws ValidacionException {
        if (idSocio <= 0) {
            throw new ValidacionException("Seleccione un socio de la tabla para " + accion + ".");
        }
    }

    private Socio construir(int idSocio, String nombre, String dni, String telefono, String email,
            String aptMedico, String estado) throws ValidacionException {
        String emailLimpio = Validador.textoOpcional(email, "Email", 100);
        if (!emailLimpio.isEmpty() && !emailLimpio.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new ValidacionException("El Email no tiene un formato válido (ejemplo: nombre@correo.com).");
        }
        return new Socio(
                idSocio,
                Validador.textoObligatorio(nombre, "Nombre", 100),
                Validador.enteroPositivo(dni, "DNI"),
                Validador.textoOpcional(telefono, "Teléfono", 30),
                emailLimpio,
                Validador.opcionValida(aptMedico, APTO_MEDICO, "Apt. Médico"),
                Validador.opcionValida(estado, ESTADOS_MEMBRESIA, "Estado"));
    }
}
