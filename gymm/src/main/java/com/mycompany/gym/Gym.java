package com.mycompany.gym;

import Interface.MenuPrincipal;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación "Sistema de Gestión de Gimnasio".
 * Su única responsabilidad es abrir el menú principal.
 */
public class Gym {

    /**
     * Inicia la aplicación mostrando el menú principal.
     * Las ventanas Swing deben crearse en el hilo de eventos (EDT), por eso se
     * usa {@link SwingUtilities#invokeLater(Runnable)}.
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setLocationRelativeTo(null); // centra la ventana en la pantalla
            menu.setVisible(true);
        });
    }
}
