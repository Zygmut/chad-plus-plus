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

    // Error message
    private String message;
    // Error code
    private int code;
    // Error line
    private int line;

    public Error(int code, int line) {
        this.code = code;
        this.line = line;
    }

    @Override
    public String toString() {
        setMessage(); // Set the message based on the code
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColor.printColored(ConsoleColor.RED, "ERROR"));
        // CLI argument error
        if (line != -1) {
            sb.append(ConsoleColor.printColored(ConsoleColor.RED, "at line "));
            sb.append(ConsoleColor.printColored(ConsoleColor.RED, Integer.toString(line)));
            sb.append(ConsoleColor.printColored(ConsoleColor.RED, " "));
        }

        sb.append(ConsoleColor.printColored(ConsoleColor.RED, ": "));
        sb.append(ConsoleColor.printColored(ConsoleColor.RED, message));

        return sb.toString();
    }

    /**
     * Set the message based on the code. See the error code in doc/errores.md or in
     * the ErrorCodes class.
     */
    private void setMessage() {
        switch (code) {
            case 0:
                message = "Invalid argument, couldn't find file.";
                break;
            case 1:
                message = "Invalid argument, invalid file extension.";
                break;
            case 2:
                message = "Invalid argument, invalid file.";
                break;
            default:
                message = "Error code not found, invalid error code.";
                break;
        }
    }

}