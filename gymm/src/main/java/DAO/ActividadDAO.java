package DAO;

import Exceptions.AccesoDatosException;
import Models.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad {@link Actividad}: es la única clase que conoce las consultas
 * SQL de la tabla {@code actividad}.
 */
public class ActividadDAO implements CrudDAO<Actividad> {

    private static final String SQL_INSERTAR = "INSERT INTO actividad "
            + "(nombre_clase, cupos, horarios, profesor, precio) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT id_actividad, nombre_clase, cupos, horarios, "
            + "profesor, precio FROM actividad ORDER BY id_actividad";
    private static final String SQL_ACTUALIZAR = "UPDATE actividad SET nombre_clase = ?, cupos = ?, "
            + "horarios = ?, profesor = ?, precio = ? WHERE id_actividad = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM actividad WHERE id_actividad = ?";

    /**
     * Registra una nueva actividad.
     *
     * @param actividad actividad a guardar (su id lo genera la base de datos)
     * @throws AccesoDatosException si la base de datos falla
     */
    @Override
    public void insertar(Actividad actividad) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            cargarDatos(ps, actividad);
            ps.executeUpdate(); // sin esta línea el INSERT nunca se ejecuta
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo registrar la actividad: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todas las actividades ordenadas por id.
     *
     * @return lista de actividades (vacía si no hay ninguna)
     * @throws AccesoDatosException si la consulta falla
     */
    @Override
    public List<Actividad> listar() throws AccesoDatosException {
        List<Actividad> actividades = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                actividades.add(new Actividad(
                        rs.getInt("id_actividad"),
                        rs.getString("nombre_clase"),
                        rs.getInt("cupos"),
                        rs.getString("horarios"),
                        rs.getString("profesor"),
                        rs.getDouble("precio")));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo obtener la lista de actividades: " + e.getMessage(), e);
        }
        return actividades;
    }

    /**
     * Modifica los datos de una actividad existente.
     *
     * @param actividad actividad con su id y los nuevos valores
     * @throws AccesoDatosException si la actividad no existe o la base falla
     */
    @Override
    public void actualizar(Actividad actividad) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            cargarDatos(ps, actividad);
            ps.setInt(6, actividad.getIdActividad());
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe una actividad con ID " + actividad.getIdActividad() + ".");
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo modificar la actividad: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina una actividad.
     *
     * @param id identificador de la actividad
     * @throws AccesoDatosException si no existe o todavía tiene inscripciones asociadas
     */
    @Override
    public void eliminar(int id) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe una actividad con ID " + id + ".");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == Conexion.ERROR_REGISTRO_REFERENCIADO) {
                throw new AccesoDatosException(
                        "No se puede eliminar la actividad porque tiene inscripciones. Elimine primero esas inscripciones.", e);
            }
            throw new AccesoDatosException("No se pudo eliminar la actividad: " + e.getMessage(), e);
        }
    }

    /** Copia los cinco datos de la actividad en los parámetros 1 a 5 de la consulta (INSERT y UPDATE comparten este orden). */
    private void cargarDatos(PreparedStatement ps, Actividad actividad) throws SQLException {
        ps.setString(1, actividad.getNombreActividad());
        ps.setInt(2, actividad.getCupos());
        ps.setString(3, actividad.getHorarios());
        ps.setString(4, actividad.getProfesor());
        ps.setDouble(5, actividad.getPrecio());
    }
}
