package errors;

import utils.ConsoleColor;
import utils.Phase;
import java.util.Objects;

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
    private boolean withColor = true;

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
        if (withColor) {
            sb.append(color);
        }

        sb.append(this.getPhase().toUpperCase());
        sb.append(" ");
        sb.append("ERROR");
        // CLI argument error
        if (this.line != -1) {
            sb.append(" at line ");
            sb.append(Integer.toString(line));

            if (this.charloc != -1) {
                sb.append(":");
                sb.append(Integer.toString(charloc));

            }
        }

        sb.append(" - ");
        sb.append(message);
        if (withColor) {
            sb.append(ConsoleColor.RESET);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return line == error.line && charloc == error.charloc && withColor == error.withColor
                && Objects.equals(message, error.message) && code == error.code && phase == error.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, message, code, line, charloc, phase, withColor);
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

    public boolean toggleColors() {
        this.withColor = !this.withColor;
        return this.withColor;
    }
}
