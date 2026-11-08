package Controllers;

import DAO.ActividadDAO;
import DAO.CrudDAO;
import Exceptions.GymException;
import Exceptions.ValidacionException;
import Models.Actividad;
import java.util.List;

/**
 * Controlador de actividades: valida los datos que escribió el usuario, arma el
 * objeto {@link Actividad} y se lo pasa al DAO.
 */
public class ActividadController {

    private final CrudDAO<Actividad> dao;

    /** Crea el controlador con el DAO real (base de datos MySQL). */
    public ActividadController() {
        this(new ActividadDAO());
    }

    /**
     * Crea el controlador con el DAO indicado.
     *
     * @param dao DAO que se usará para persistir actividades
     */
    public ActividadController(CrudDAO<Actividad> dao) {
        this.dao = dao;
    }

    /**
     * Valida los datos y registra una nueva actividad.
     *
     * @param nombre nombre de la actividad (obligatorio)
     * @param cupos cantidad de cupos, entero mayor que cero
     * @param horarios horario en que se dicta (opcional)
     * @param profesor nombre del profesor (opcional)
     * @param precio precio, número mayor o igual que cero
     * @throws GymException si algún dato es inválido o falla la base de datos
     */
    public void registrar(String nombre, String cupos, String horarios, String profesor,
            String precio) throws GymException {
        dao.insertar(construir(0, nombre, cupos, horarios, profesor, precio));
    }

    /**
     * Valida los datos y modifica una actividad existente.
     *
     * @param idActividad id de la actividad a modificar (debe ser mayor que cero)
     * @param nombre nombre de la actividad (obligatorio)
     * @param cupos cantidad de cupos, entero mayor que cero
     * @param horarios horario en que se dicta (opcional)
     * @param profesor nombre del profesor (opcional)
     * @param precio precio, número mayor o igual que cero
     * @throws GymException si algún dato es inválido, no hay actividad seleccionada o falla la base de datos
     */
    public void modificar(int idActividad, String nombre, String cupos, String horarios,
            String profesor, String precio) throws GymException {
        exigirSeleccion(idActividad, "modificar");
        dao.actualizar(construir(idActividad, nombre, cupos, horarios, profesor, precio));
    }

    /**
     * Elimina una actividad.
     *
     * @param idActividad id de la actividad (debe ser mayor que cero)
     * @throws GymException si no hay actividad seleccionada o falla la base de datos
     */
    public void eliminar(int idActividad) throws GymException {
        exigirSeleccion(idActividad, "eliminar");
        dao.eliminar(idActividad);
    }

    /**
     * Devuelve todas las actividades.
     *
     * @return lista de actividades
     * @throws GymException si falla la base de datos
     */
    public List<Actividad> listar() throws GymException {
        return dao.listar();
    }

    private void exigirSeleccion(int idActividad, String accion) throws ValidacionException {
        if (idActividad <= 0) {
            throw new ValidacionException("Seleccione una actividad de la tabla para " + accion + ".");
        }
    }

    private Actividad construir(int idActividad, String nombre, String cupos, String horarios,
            String profesor, String precio) throws ValidacionException {
        return new Actividad(
                idActividad,
                Validador.textoObligatorio(nombre, "Nombre de Clase", 100),
                Validador.enteroPositivo(cupos, "Cupos"),
                Validador.textoOpcional(horarios, "Horarios", 100),
                Validador.textoOpcional(profesor, "Profesor", 100),
                Validador.decimalNoNegativo(precio, "Precio"));
    }
}
