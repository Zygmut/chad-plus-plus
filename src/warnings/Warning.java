package warnings;

import utils.ConsoleColor;

public class Warning {

    private String message;
    private int code;
    private int line;

    public Warning(int code, int line) {
        this.code = code;
        this.line = line;
    }

    @Override
    public String toString() {
        setMessage();

        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColor.printColored(ConsoleColor.YELLOW, "WARNING"));
        sb.append(ConsoleColor.printColored(ConsoleColor.YELLOW, " at line "));
        sb.append(ConsoleColor.printColored(ConsoleColor.YELLOW, Integer.toString(line)));
        sb.append(ConsoleColor.printColored(ConsoleColor.YELLOW, ": "));
        sb.append(ConsoleColor.printColored(ConsoleColor.YELLOW, message));

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
