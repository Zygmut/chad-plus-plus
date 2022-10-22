package utils;

/**
 * Env - Variables de entorno y constantes
 *
 * En esta clase se definen las variables de entorno y las constantes que se
 * emplearan a lo largo del programa.
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 */
public class Env {
    // Test mode (Unit tests)
    public static final boolean TEST_MODE = true;

    // Debug mode
    public static final boolean DEBUG_MODE = false;

    // Constante que indica si se desea visualizar el arbol de parseo
    public static final boolean VISUALIZATION = false;

    // Valores por defecto del fichero, al iniciar el programa su contenido se
    // sobreescribe
    public static final FileData FILE_DATA = new FileData();

    public static final String welcomeString = "This is the Chad++ compiler!";

    // Constantes para el manejo de errores y excepciones en el input del programa
    public static final int Ok = 0;
    public static final int Error = -2;
    public static final int Warning = -1;
}
