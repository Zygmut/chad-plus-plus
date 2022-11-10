package warnings;

import utils.ConsoleColor;
import utils.Env;

public class Warning {

    private final String color = ConsoleColor.YELLOW;
    private String message;
    private int code;
    private int line;
    private int charloc;
    private int phase;

    public Warning(int code, int line, int phase) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
        this.phase = phase;
    }

    public Warning(int code, int line, int charloc, int phase) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    @Override
    public String toString() {
        setMessage();

        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColor.printColored(color, "WARNING"));
        sb.append(ConsoleColor.printColored(color, " during the " + this.getPhase() + " phase of the compilation"));
        sb.append(ConsoleColor.printColored(color, " at line "));
        sb.append(ConsoleColor.printColored(color, Integer.toString(line)));
        if (this.charloc != -1) {
            sb.append(ConsoleColor.printColored(color, ":"));
            sb.append(ConsoleColor.printColored(color, Integer.toString(line)));

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

    private void setMessage() {
        switch (this.code) {
            case WarningCodes.UNUSED_METHOD:
                this.message = "Unused method.";
                break;
            case WarningCodes.UNUSED_VARIABLE:
                this.message = "Unused variable.";
                break;
            case WarningCodes.INACCESIBLE_LINE:
                this.message = "Line not accessed.";
                break;
            default:
                this.message = "Warning code not found, invalid warning code.";
                break;
        }
    }
}