package DAO;

import Exceptions.AccesoDatosException;
import Models.Clase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad {@link Clase}: gestiona las inscripciones de socios a
 * actividades en la tabla {@code clase}.
 */
public class ClaseDAO implements CrudDAO<Clase> {

    private static final String SQL_INSERTAR = "INSERT INTO clase (id_actividad, id_socio) VALUES (?, ?)";
    private static final String SQL_LISTAR = "SELECT id_clase, id_actividad, id_socio FROM clase ORDER BY id_clase";
    private static final String SQL_ACTUALIZAR = "UPDATE clase SET id_actividad = ?, id_socio = ? WHERE id_clase = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM clase WHERE id_clase = ?";

    /**
     * Inscribe un socio en una actividad.
     *
     * @param clase inscripción a guardar (su id lo genera la base de datos)
     * @throws AccesoDatosException si el socio o la actividad no existen, o la base falla
     */
    @Override
    public void insertar(Clase clase) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            ps.setInt(1, clase.getIdActividad());
            ps.setInt(2, clase.getIdSocio());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw traducir(e, "registrar");
        }
    }

    /**
     * Obtiene todas las inscripciones ordenadas por id.
     *
     * @return lista de inscripciones (vacía si no hay ninguna)
     * @throws AccesoDatosException si la consulta falla
     */
    @Override
    public List<Clase> listar() throws AccesoDatosException {
        List<Clase> clases = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clases.add(new Clase(
                        rs.getInt("id_clase"),
                        rs.getInt("id_actividad"),
                        rs.getInt("id_socio")));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo obtener la lista de inscripciones: " + e.getMessage(), e);
        }
        return clases;
    }

    /**
     * Cambia el socio y/o la actividad de una inscripción existente.
     *
     * @param clase inscripción con su id y los nuevos valores
     * @throws AccesoDatosException si la inscripción no existe, el socio o la actividad no existen, o la base falla
     */
    @Override
    public void actualizar(Clase clase) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setInt(1, clase.getIdActividad());
            ps.setInt(2, clase.getIdSocio());
            ps.setInt(3, clase.getIdClase());
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe una inscripción con ID " + clase.getIdClase() + ".");
            }
        } catch (SQLException e) {
            throw traducir(e, "modificar");
        }
    }

    /**
     * Da de baja (elimina) una inscripción.
     *
     * @param id identificador de la inscripción
     * @throws AccesoDatosException si no existe o la base falla
     */
    @Override
    public void eliminar(int id) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe una inscripción con ID " + id + ".");
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo eliminar la inscripción: " + e.getMessage(), e);
        }
    }

    /** Convierte un error de SQL en un mensaje entendible; el caso típico es un socio o actividad inexistente. */
    private AccesoDatosException traducir(SQLException e, String accion) {
        if (e.getErrorCode() == Conexion.ERROR_REFERENCIA_INEXISTENTE) {
            return new AccesoDatosException("No se pudo " + accion
                    + " la inscripción: el socio o la actividad seleccionados no existen.", e);
        }
        return new AccesoDatosException("No se pudo " + accion + " la inscripción: " + e.getMessage(), e);
    }
}
