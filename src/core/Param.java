package core;

import java.util.Objects;

public class Param {
    private TypeVar type;
    private Id id;

    public Param() {
        this.type = TypeVar.BOOL;
        this.id = new Id();
    }

    public Param(TypeVar type, Id id) {
        this.type = type;
        this.id = id;
    }

    public TypeVar getType() {
        return this.type;
    }

    public void setType(TypeVar type) {
        this.type = type;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Param)) {
            return false;
        }
        Param param = (Param) o;
        return Objects.equals(type, param.type) && Objects.equals(id, param.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }

    @Override
    public String toString() {
        return "{" +
                " type='" + getType() + "'" +
                ", id='" + getId() + "'" +
                "}";
    }

}
