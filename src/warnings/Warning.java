package warnings;

import utils.ConsoleColor;
import utils.Phase;

public class Warning {

    private final String color = ConsoleColor.YELLOW;
    private String message;
    private WarningCode code;
    private int line;
    private int charloc;
    private Phase phase;

    public Warning(WarningCode code, int line, Phase phase) {
        this.code = code;
        this.line = line;
        this.charloc = -1;
        this.phase = phase;
    }

    public Warning(WarningCode code, int line, int charloc, Phase phase) {
        this.code = code;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    public Warning(String message, int line, int charloc, Phase phase) {
        this.code = WarningCode.CUSTOM;
        this.message = message;
        this.line = line;
        this.charloc = charloc;
        this.phase = phase;
    }

    @Override
    public String toString() {
        setMessage();
        StringBuilder sb = new StringBuilder();

        sb.append(ConsoleColor.printColored(color, this.getPhase().toUpperCase()));
        sb.append(ConsoleColor.printColored(color, " "));
        sb.append(ConsoleColor.printColored(color, "WARNING"));
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

    private void setMessage() {
        if (this.code != WarningCode.CUSTOM) {
            this.message = this.code.message;
        }
    }
}
