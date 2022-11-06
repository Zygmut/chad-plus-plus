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

    // Operating system
    public static final String OS = System.getProperty("os.name");
    public static final String SLASH = OS.contains("Windows") ? "\\" : "/";

    // Test mode (Unit tests)
    public static final boolean TEST_MODE = false;

    // Debug mode
    public static final boolean DEBUG_MODE = false;

    // Constante que indica si se desea visualizar el arbol de parseo
    public static final boolean VISUALIZATION = false;

    // Valores por defecto del fichero, al iniciar el programa su contenido se
    // sobreescribe
    public static final FileData FILE_DATA = new FileData();
    public static final String OUTPUTPATH = "./target/";
    public static final String[] VALID_EXT = { "txt", "chpp" };

    public static final String welcomeString = "This is the Chad++ compiler!\n"
            + "\tUsage:\t  chadpp <inputFile> \n\t       or chadpp <inputFile> <outputFile>\n\n"
            + "\tNote: <outputFile> defaults to " + OUTPUTPATH;

    // Constantes para el manejo de errores y excepciones en el input del programa
    public static final int Ok = 0;
    public static final int Error = -2;
    public static final int Warning = -1;
}
