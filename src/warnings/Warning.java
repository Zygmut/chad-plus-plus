package warnings;

import java.util.Objects;

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
        this.message = "";
        this.line = line;
        this.charloc = -1;
        this.phase = phase;
    }

    public Warning(WarningCode code, int line, int charloc, Phase phase) {
        this.code = code;
        this.message = "";
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Warning warning = (Warning) o;
        return line == warning.line && charloc == warning.charloc && withColor == warning.withColor
                && Objects.equals(color, warning.color) && Objects.equals(message, warning.message)
                && code == warning.code && phase == warning.phase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, message, code, line, charloc, phase, withColor);
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

    private void setMessage() {
        if (this.code != WarningCode.CUSTOM) {
            this.message = this.code.message;
        }
    }

    public boolean toggleColors() {
        this.withColor = !this.withColor;
        return this.withColor;
    }

    public String getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WarningCode getCode() {
        return code;
    }

    public void setCode(WarningCode code) {
        this.code = code;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharloc() {
        return charloc;
    }

    public void setCharloc(int charloc) {
        this.charloc = charloc;
    }

    public String getPhase() {
        return phase.message;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public boolean isWithColor() {
        return withColor;
    }

    public void setWithColor(boolean withColor) {
        this.withColor = withColor;
    }
}
