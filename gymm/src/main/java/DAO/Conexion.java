package DAO;

import Exceptions.AccesoDatosException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de conexiones a la base de datos MySQL "SistemaGimnasio".
 * Es una clase de utilidad: no se instancia, los DAO la usan llamando a
 * {@link #obtenerConexion()}.
 *
 * <p>Ajuste el usuario y la contraseña según su servidor local.</p>
 */
public final class Conexion {

    /** Código de error de MySQL: valor duplicado en una columna UNIQUE (por ejemplo, DNI repetido). */
    public static final int ERROR_DUPLICADO = 1062;

    /** Código de error de MySQL: no se puede borrar/modificar porque otras filas dependen de este registro. */
    public static final int ERROR_REGISTRO_REFERENCIADO = 1451;

    /** Código de error de MySQL: se hace referencia a un registro padre que no existe. */
    public static final int ERROR_REFERENCIA_INEXISTENTE = 1452;

    private static final String BASE = "SistemaGimnasio";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BASE;
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private Conexion() {
        // Clase de utilidad: se impide crear instancias.
    }

    /**
     * Abre una nueva conexión a la base de datos. Quien la reciba debe cerrarla
     * (lo más cómodo es usar try-with-resources).
     *
     * @return una conexión abierta, nunca {@code null}
     * @throws AccesoDatosException si no se puede conectar (servidor apagado,
     *                              credenciales incorrectas, driver ausente, etc.)
     */
    public static Connection obtenerConexion() throws AccesoDatosException {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo conectar a la base de datos '" + BASE
                    + "'. Verifique que MySQL esté iniciado y que el usuario y la contraseña sean correctos.", e);
        }
    }
}
