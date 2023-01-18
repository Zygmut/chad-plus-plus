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
    public boolean equals(Object obj) {
        Variable var = (Variable) obj;
        return Objects.equals(this.id, var.getId());
    }

    @Override
    public String toString() {
        return "Variable [type=" + type + ", id=" + id + ", isVolatile=" + isVolatile + "]";
    }

}