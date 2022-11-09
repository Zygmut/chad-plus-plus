package core;

import java.util.Objects;

public class Number {

    private Integer value;

    public Number() {
        this.value = 0;
    }

    public Number(Integer value) {
        this.value = value;
    }

    public Number(String value) {
        this.value = Integer.parseInt(value);
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Number)) {
            return false;
        }
        Number number = (Number) o;
        return Objects.equals(value, number.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Number [value=" + value + "]";
    }

}
