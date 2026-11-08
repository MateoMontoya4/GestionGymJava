package DAO;

import Exceptions.AccesoDatosException;
import java.util.List;

/**
 * Contrato común de todos los DAO del sistema: las cuatro operaciones del CRUD.
 * Cada entidad (Socio, Actividad, Clase) tiene su propio DAO que implementa esta
 * interfaz, así los controladores trabajan contra la interfaz y no contra una
 * clase concreta.
 *
 * @param <T> tipo de entidad que gestiona el DAO
 */
public interface CrudDAO<T> {

    /**
     * Inserta una nueva entidad en la base de datos (Create).
     *
     * @param entidad objeto a guardar
     * @throws AccesoDatosException si la inserción falla
     */
    void insertar(T entidad) throws AccesoDatosException;

    /**
     * Devuelve todas las entidades guardadas (Read).
     *
     * @return lista de entidades, vacía si no hay ninguna
     * @throws AccesoDatosException si la consulta falla
     */
    List<T> listar() throws AccesoDatosException;

    /**
     * Actualiza una entidad existente, identificada por su id (Update).
     *
     * @param entidad objeto con el id y los nuevos valores
     * @throws AccesoDatosException si no existe el registro o la actualización falla
     */
    void actualizar(T entidad) throws AccesoDatosException;

    /**
     * Elimina la entidad con el id indicado (Delete).
     *
     * @param id identificador del registro a borrar
     * @throws AccesoDatosException si no existe el registro o no se puede borrar
     */
    void eliminar(int id) throws AccesoDatosException;
}
