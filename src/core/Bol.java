package core;

public class Bol {
    private boolean value;

    public Bol(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Bol [value=" + value + "]";
    }

}
