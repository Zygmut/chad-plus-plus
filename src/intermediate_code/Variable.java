package intermediate_code;

import core.TypeVar;

import java.util.Objects;

/**
 * Variable
 */
public class Variable {

    private TypeVar type;
    private String id;
    private boolean isVolatile;

    public Variable(TypeVar type, String id, boolean isVolatile) {
        this.type = type;
        this.id = id;
        this.isVolatile = isVolatile;
    }

    public TypeVar getType() {
        return type;
    }

    public void setType(TypeVar type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVolatile() {
        return isVolatile;
    }

    public void setVolatile(boolean isVolatile) {
        this.isVolatile = isVolatile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Variable variable = (Variable) o;
        return isVolatile == variable.isVolatile && type == variable.type && Objects.equals(id, variable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, isVolatile);
    }

    @Override
    public String toString() {
        return "Variable [type=" + type + ", id=" + id + ", isVolatile=" + isVolatile + "]";
    }

}