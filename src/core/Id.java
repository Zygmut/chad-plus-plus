package core;

import java.util.Objects;

public class Id {

    private String value;

    public Id() {
    }

    public Id(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Id)) {
            return false;
        }
        Id id = (Id) o;
        return Objects.equals(value, id.value);
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
