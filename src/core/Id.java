package core;

public class Id extends BaseNode {
    private String value;

    public Id(String value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Id [value=" + value + " line=" + line + " column=" + column + "]";

    }

}
