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
    INACCESIBLE_LINE("Line cannot be accessed."),
    CHANGE_TYPE_OF_GLOBAL_TUPLE(
            "Changed the type of a global defined tuple argument, program might return unexpected results"),
    VARIABLE_CONTENT_NOT_KNOWN_AT_COMPILE_TIME(
            "The variable content you are trying to access is not known at compile time. Due to the lack of program flow analysis and boundary checking, program might return unexpected results."),
    DIVISION_BY_ZERO("Division by zero.");

    public String message;

    WarningCode(String message) {
        this.message = message;
    }
}
