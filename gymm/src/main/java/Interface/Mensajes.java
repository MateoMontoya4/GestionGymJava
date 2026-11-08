package Interface;

import Exceptions.GymException;
import Exceptions.ValidacionException;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Cuadros de diálogo comunes a todas las pantallas. Centralizarlos evita repetir
 * el mismo código de {@code JOptionPane} en cada vista.
 */
final class Mensajes {

    private Mensajes() {
        // Clase de utilidad: no se instancia.
    }

    /**
     * Muestra el mensaje de una excepción del sistema. Los errores de validación
     * (el usuario cargó mal un dato) se muestran como advertencia; los demás, como error.
     *
     * @param padre ventana sobre la que se muestra el cuadro
     * @param e excepción cuyo mensaje se informa
     */
    static void mostrarError(Component padre, GymException e) {
        if (e instanceof ValidacionException) {
            JOptionPane.showMessageDialog(padre, e.getMessage(), "Datos incorrectos", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(padre, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra un mensaje informativo.
     *
     * @param padre ventana sobre la que se muestra el cuadro
     * @param texto mensaje para el usuario
     */
    static void informar(Component padre, String texto) {
        JOptionPane.showMessageDialog(padre, texto, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Pide confirmación al usuario (Sí / No).
     *
     * @param padre ventana sobre la que se muestra el cuadro
     * @param pregunta pregunta a confirmar
     * @return {@code true} si el usuario eligió "Sí"
     */
    static boolean confirmar(Component padre, String pregunta) {
        return JOptionPane.showConfirmDialog(padre, pregunta, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
}
