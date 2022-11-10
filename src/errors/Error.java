package errors;

import utils.ConsoleColor;
import utils.Env;

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
    // Phase of the compiler
    private int phase;

    public Error(int code, int line, int phase) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
        this.phase = phase;
    }

    public Error(int code, int line, int charloc, int phase) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    @Override
    public String toString() {
        setMessage(); // Set the message based on the code
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColor.printColored(color, "ERROR"));
        // CLI argument error
        if (this.line != -1) {
            sb.append(ConsoleColor.printColored(color, " during the " + this.getPhase() + " phase of the compilation"));
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
     * 
     * @return the phase of the error
     */
    private String getPhase() {
        switch (this.phase) {
            case Env.PRE_COMPILER_PHASE:
                return "sanity check";
            case Env.LEXICAL_PHASE:
                return "lexical";
            case Env.SYNTACTIC_PHASE:
                return "syntactic";
            case Env.SEMANTIC_PHASE:
                return "semantic";
            case Env.CODE_GENERATION_PHASE:
                return "code generation";
            default:
                return "the phase set is not valid";
        }
    }

    /**
     * Set the message based on the code. See the error code in doc/errores.md or in
     * the ErrorCodes class.
     */
    private void setMessage() {
        switch (this.code) {
            case ErrorCodes.FILE_NOT_FOUND:
                this.message = "Invalid argument, couldn't find file.";
                break;
            case ErrorCodes.INVALID_FILE_EXTENSION:
                this.message = "Invalid argument, invalid file extension.";
                break;
            case ErrorCodes.INVALID_FILE:
                this.message = "Invalid argument, invalid file.";
                break;
            case ErrorCodes.INVALID_TOKEN:
                this.message = "Invalid Token.";
                break;
            default:
                this.message = "Error code not found, invalid error code.";
                break;
        }
    }

}