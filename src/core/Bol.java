package core;

import java.util.Objects;

public class Bol {

    private Boolean value;

    public Bol() {
        this.value = false;
    }

    public Bol(boolean value) {
        this.value = value;
    }

    public Bol(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bol)) {
            return false;
        }
        Bol bol = (Bol) o;
        return Objects.equals(value, bol.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "{" +
                " value='" + getValue() + "'" +
                "}";
    }
}
