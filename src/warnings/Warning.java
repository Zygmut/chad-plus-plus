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
    private boolean withColor = true;

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
        if (withColor) {
            sb.append(color);
        }
        sb.append(this.getPhase().toUpperCase());
        sb.append(" ");
        sb.append("WARNING");
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
