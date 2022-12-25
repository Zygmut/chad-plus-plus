package core;

public class Number extends BaseNode {

    private int value;

    public Number(int value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public Number(String value, int line, int column) {
        super(line, column);
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Number [value=" + value + " line=" + line + " column=" + column + "]";

    }

}
