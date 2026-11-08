package DAO;

import Exceptions.AccesoDatosException;
import Models.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad {@link Socio}: es la única clase que conoce las consultas SQL
 * de la tabla {@code socio}.
 */
public class SocioDAO implements CrudDAO<Socio> {

    private static final String SQL_INSERTAR = "INSERT INTO socio "
            + "(nombre, dni, telefono, email, apt_medico, estado_membresia) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT id_socio, nombre, dni, telefono, email, "
            + "apt_medico, estado_membresia FROM socio ORDER BY id_socio";
    private static final String SQL_ACTUALIZAR = "UPDATE socio SET nombre = ?, dni = ?, telefono = ?, "
            + "email = ?, apt_medico = ?, estado_membresia = ? WHERE id_socio = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM socio WHERE id_socio = ?";

    /**
     * Registra un nuevo socio.
     *
     * @param socio socio a guardar (su id lo genera la base de datos)
     * @throws AccesoDatosException si el DNI ya existe o la base de datos falla
     */
    @Override
    public void insertar(Socio socio) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            cargarDatos(ps, socio);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw traducir(e, "registrar", socio.getDni());
        }
    }

    /**
     * Obtiene todos los socios ordenados por id.
     *
     * @return lista de socios (vacía si no hay ninguno)
     * @throws AccesoDatosException si la consulta falla
     */
    @Override
    public List<Socio> listar() throws AccesoDatosException {
        List<Socio> socios = new ArrayList<>();
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                socios.add(new Socio(
                        rs.getInt("id_socio"),
                        rs.getString("nombre"),
                        rs.getInt("dni"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("apt_medico"),
                        rs.getString("estado_membresia")));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo obtener la lista de socios: " + e.getMessage(), e);
        }
        return socios;
    }

    /**
     * Modifica los datos de un socio existente.
     *
     * @param socio socio con su id y los nuevos valores
     * @throws AccesoDatosException si el socio no existe, el DNI ya pertenece a otro o la base falla
     */
    @Override
    public void actualizar(Socio socio) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            cargarDatos(ps, socio);
            ps.setInt(7, socio.getIdSocio());
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe un socio con ID " + socio.getIdSocio() + ".");
            }
        } catch (SQLException e) {
            throw traducir(e, "modificar", socio.getDni());
        }
    }

    /**
     * Elimina un socio.
     *
     * @param id identificador del socio
     * @throws AccesoDatosException si no existe o todavía tiene inscripciones asociadas
     */
    @Override
    public void eliminar(int id) throws AccesoDatosException {
        try (Connection con = Conexion.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new AccesoDatosException("No existe un socio con ID " + id + ".");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == Conexion.ERROR_REGISTRO_REFERENCIADO) {
                throw new AccesoDatosException(
                        "No se puede eliminar el socio porque tiene inscripciones. Elimine primero sus inscripciones.", e);
            }
            throw new AccesoDatosException("No se pudo eliminar el socio: " + e.getMessage(), e);
        }
    }

    /** Copia los seis datos del socio en los parámetros 1 a 6 de la consulta (INSERT y UPDATE comparten este orden). */
    private void cargarDatos(PreparedStatement ps, Socio socio) throws SQLException {
        ps.setString(1, socio.getNombre());
        ps.setInt(2, socio.getDni());
        ps.setString(3, socio.getTelefono());
        ps.setString(4, socio.getEmail());
        ps.setString(5, socio.getAptMedico());
        ps.setString(6, socio.getEstadoMembresia());
    }

    /** Convierte un error de SQL en un mensaje entendible; el caso más común es el DNI repetido. */
    private AccesoDatosException traducir(SQLException e, String accion, int dni) {
        if (e.getErrorCode() == Conexion.ERROR_DUPLICADO) {
            return new AccesoDatosException("Ya existe un socio con el DNI " + dni + ".", e);
        }
        return new AccesoDatosException("No se pudo " + accion + " el socio: " + e.getMessage(), e);
    }
}
