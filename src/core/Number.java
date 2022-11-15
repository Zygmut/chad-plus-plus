package core;

public class Number {

    private int value;

    public Number(int value) {
        this.value = value;
    }

    public Number(String value) {
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
        return "Number [value=" + value + "]";
    }

}
