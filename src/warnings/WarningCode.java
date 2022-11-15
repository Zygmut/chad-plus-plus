package warnings;

/**
 * WarningCodes - Clase que contiene los códigos de warning de la aplicación.
 * Se utiliza para identificar el tipo de warning que se ha producido en la
 * compilación y evitar los 'magic numbers'.
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 */
public enum WarningCode {
    CUSTOM(""),
    UNUSED_VARIABLE("Unused variable."),
    UNUSED_METHOD("Unused method."),
    INACCESIBLE_LINE("Line cannot be accessed.");

    public String message;

    WarningCode(String message) {
        this.message = message;
    }
}
