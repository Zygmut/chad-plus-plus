package errors;

import utils.ConsoleColor;
import utils.Phase;

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
    private ErrorCode code;
    // Error line
    private int line;
    // Character
    private int charloc;
    // Phase of the compiler
    private Phase phase;

    public Error(ErrorCode code, int line, Phase phase) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
        this.phase = phase;
    }

    public Error(ErrorCode code, int line, int charloc, Phase phase) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    public Error(String message, int line, int charloc, Phase phase) {
        this.code = ErrorCode.CUSTOM;
        this.message = message;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    @Override
    public String toString() {
        setMessage(); // Set the message based on the code
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColor.printColored(color, this.getPhase().toUpperCase()));
        sb.append(ConsoleColor.printColored(color, " "));
        sb.append(ConsoleColor.printColored(color, "ERROR"));
        // CLI argument error
        if (this.line != -1) {
            sb.append(ConsoleColor.printColored(color, " at line "));
            sb.append(ConsoleColor.printColored(color, Integer.toString(line)));

            if (this.charloc != -1) {
                sb.append(ConsoleColor.printColored(color, ":"));
                sb.append(ConsoleColor.printColored(color, Integer.toString(charloc)));

            }
        }

        sb.append(ConsoleColor.printColored(color, " - "));
        sb.append(ConsoleColor.printColored(color, message));

        return sb.toString();
    }

    /**
     *
     * @return the phase of the error
     */
    private String getPhase() {
        return this.phase.message;
    }

    /**
     * Set the message based on the code. See the error code in doc/errores.md or in
     * the ErrorCodes class.
     */
    private void setMessage() {
        if (this.code != ErrorCode.CUSTOM) {
            this.message = this.code.message;
        }
    }

}