package errors;

import utils.ConsoleColor;

/**
 * Error - Clase con los metadatos de un error
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */
public class Error {

    private final String color = ConsoleColor.RED;
    // Error message
    private String message;
    // Error code
    private int code;
    // Error line
    private int line;
    // Character
    private int charloc;

    public Error(int code, int line) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
    }

    public Error(int code, int line, int charloc) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
    }

    @Override
    public String toString() {
        setMessage(); // Set the message based on the code
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColor.printColored(color, "ERROR"));
        // CLI argument error
        if (this.line != -1) {
            sb.append(ConsoleColor.printColored(color, " at line "));
            sb.append(ConsoleColor.printColored(color, Integer.toString(line)));

            if (this.charloc != -1) {
                sb.append(ConsoleColor.printColored(color, ":"));
                sb.append(ConsoleColor.printColored(color, Integer.toString(line)));

            }
        }

        sb.append(ConsoleColor.printColored(color, " - "));
        sb.append(ConsoleColor.printColored(color, message));

        return sb.toString();
    }

    /**
     * Set the message based on the code. See the error code in doc/errores.md or in
     * the ErrorCodes class.
     */
    private void setMessage() {
        switch (this.code) {
            case ErrorCodes.ARGUMENT_FILE_NOT_FOUND:
                this.message = "Invalid argument, couldn't find file.";
                break;
            case ErrorCodes.ARGUMENT_INVALID_FILE_EXTENSION:
                this.message = "Invalid argument, invalid file extension.";
                break;
            case ErrorCodes.ARGUMENT_INVALID_FILE:
                this.message = "Invalid argument, invalid file.";
                break;
            default:
                this.message = "Error code not found, invalid error code.";
                break;
        }
    }

}