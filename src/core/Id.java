package core;

public class Id {
    private String value;

    public Id(String value) {
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
        return "Id [value=" + value + "]";
    }

}
