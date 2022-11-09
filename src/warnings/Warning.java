package warnings;

import utils.ConsoleColor;

public class Warning {

    private final String color = ConsoleColor.YELLOW;
    private String message;
    private int code;
    private int line;
    private int charloc;

    public Warning(int code, int line) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
    }

    public Warning(int code, int line, int charloc) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
    }

    @Override
    public String toString() {
        setMessage();

        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColor.printColored(color, "WARNING"));
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
