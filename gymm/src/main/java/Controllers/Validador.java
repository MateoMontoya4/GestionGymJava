package Controllers;

import Exceptions.ValidacionException;

/**
 * Utilidades de validación que comparten los controladores. Cada método recibe el
 * texto tal como lo escribió el usuario y devuelve el valor ya convertido, o lanza
 * {@link ValidacionException} con un mensaje claro si el dato no es válido.
 */
final class Validador {

    private Validador() {
        // Clase de utilidad: no se instancia.
    }

    /**
     * Exige que el texto no esté vacío ni supere un largo máximo.
     *
     * @param valor texto ingresado
     * @param campo nombre del campo, para armar el mensaje de error
     * @param largoMaximo cantidad máxima de caracteres permitida
     * @return el texto sin espacios al principio ni al final
     * @throws ValidacionException si está vacío o es demasiado largo
     */
    static String textoObligatorio(String valor, String campo, int largoMaximo) throws ValidacionException {
        if (valor == null || valor.isBlank()) {
            throw new ValidacionException("El campo " + campo + " es obligatorio.");
        }
        return textoOpcional(valor, campo, largoMaximo);
    }

    /**
     * Acepta un texto vacío, pero controla el largo máximo.
     *
     * @param valor texto ingresado (puede ser {@code null})
     * @param campo nombre del campo, para armar el mensaje de error
     * @param largoMaximo cantidad máxima de caracteres permitida
     * @return el texto sin espacios al principio ni al final ("" si no se ingresó nada)
     * @throws ValidacionException si es demasiado largo
     */
    static String textoOpcional(String valor, String campo, int largoMaximo) throws ValidacionException {
        String limpio = valor == null ? "" : valor.trim();
        if (limpio.length() > largoMaximo) {
            throw new ValidacionException("El campo " + campo + " no puede superar los " + largoMaximo + " caracteres.");
        }
        return limpio;
    }

    /**
     * Convierte el texto en un número entero mayor que cero.
     *
     * @param valor texto ingresado
     * @param campo nombre del campo, para armar el mensaje de error
     * @return el número entero
     * @throws ValidacionException si no es un entero o es menor o igual que cero
     */
    static int enteroPositivo(String valor, String campo) throws ValidacionException {
        int numero;
        try {
            numero = Integer.parseInt(textoObligatorio(valor, campo, 12));
        } catch (NumberFormatException e) {
            throw new ValidacionException("El campo " + campo + " debe ser un número entero (sin puntos ni letras).", e);
        }
        if (numero <= 0) {
            throw new ValidacionException("El campo " + campo + " debe ser mayor que cero.");
        }
        return numero;
    }

    /**
     * Convierte el texto en un número decimal mayor o igual que cero.
     * Acepta tanto coma como punto decimal ("1500,50" o "1500.50").
     *
     * @param valor texto ingresado
     * @param campo nombre del campo, para armar el mensaje de error
     * @return el número decimal
     * @throws ValidacionException si no es un número válido o es negativo
     */
    static double decimalNoNegativo(String valor, String campo) throws ValidacionException {
        double numero;
        try {
            numero = Double.parseDouble(textoObligatorio(valor, campo, 12).replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new ValidacionException("El campo " + campo + " debe ser un número válido (por ejemplo 1500 o 1500,50).", e);
        }
        if (!Double.isFinite(numero) || numero < 0) {
            throw new ValidacionException("El campo " + campo + " no puede ser negativo.");
        }
        return numero;
    }

    /**
     * Comprueba que el valor sea una de las opciones permitidas.
     *
     * @param valor valor elegido
     * @param opciones opciones válidas
     * @param campo nombre del campo, para armar el mensaje de error
     * @return el mismo valor, ya validado
     * @throws ValidacionException si el valor no está entre las opciones
     */
    static String opcionValida(String valor, String[] opciones, String campo) throws ValidacionException {
        for (String opcion : opciones) {
            if (opcion.equals(valor)) {
                return valor;
            }
        }
        throw new ValidacionException("Seleccione un valor válido para " + campo + ".");
    }
}
