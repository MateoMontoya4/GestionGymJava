package Controllers;

import DAO.ClaseDAO;
import DAO.CrudDAO;
import Exceptions.GymException;
import Exceptions.ValidacionException;
import Models.Clase;
import java.util.List;

/**
 * Controlador de clases (inscripciones): une a un socio con una actividad.
 */
public class ClaseController {

    private final CrudDAO<Clase> dao;

    /** Crea el controlador con el DAO real (base de datos MySQL). */
    public ClaseController() {
        this(new ClaseDAO());
    }

    /**
     * Crea el controlador con el DAO indicado.
     *
     * @param dao DAO que se usará para persistir inscripciones
     */
    public ClaseController(CrudDAO<Clase> dao) {
        this.dao = dao;
    }

    /**
     * Inscribe un socio en una actividad.
     *
     * @param idSocio id del socio elegido (mayor que cero)
     * @param idActividad id de la actividad elegida (mayor que cero)
     * @throws GymException si falta elegir socio o actividad, o falla la base de datos
     */
    public void inscribir(int idSocio, int idActividad) throws GymException {
        validarSocioYActividad(idSocio, idActividad);
        dao.insertar(new Clase(0, idActividad, idSocio));
    }

    /**
     * Cambia el socio y/o la actividad de una inscripción existente.
     *
     * @param idClase id de la inscripción a modificar (mayor que cero)
     * @param idSocio id del socio elegido (mayor que cero)
     * @param idActividad id de la actividad elegida (mayor que cero)
     * @throws GymException si falta algún dato, no hay inscripción seleccionada o falla la base de datos
     */
    public void modificar(int idClase, int idSocio, int idActividad) throws GymException {
        exigirSeleccion(idClase, "modificar");
        validarSocioYActividad(idSocio, idActividad);
        dao.actualizar(new Clase(idClase, idActividad, idSocio));
    }

    /**
     * Da de baja una inscripción.
     *
     * @param idClase id de la inscripción (mayor que cero)
     * @throws GymException si no hay inscripción seleccionada o falla la base de datos
     */
    public void darDeBaja(int idClase) throws GymException {
        exigirSeleccion(idClase, "dar de baja");
        dao.eliminar(idClase);
    }

    /**
     * Devuelve todas las inscripciones.
     *
     * @return lista de inscripciones
     * @throws GymException si falla la base de datos
     */
    public List<Clase> listar() throws GymException {
        return dao.listar();
    }

    private void exigirSeleccion(int idClase, String accion) throws ValidacionException {
        if (idClase <= 0) {
            throw new ValidacionException("Seleccione una inscripción de la tabla para " + accion + ".");
        }
    }

    private void validarSocioYActividad(int idSocio, int idActividad) throws ValidacionException {
        if (idSocio <= 0) {
            throw new ValidacionException("Debe elegir un socio. Si la lista está vacía, registre socios primero.");
        }
        if (idActividad <= 0) {
            throw new ValidacionException("Debe elegir una actividad. Si la lista está vacía, registre actividades primero.");
        }
    }
}
