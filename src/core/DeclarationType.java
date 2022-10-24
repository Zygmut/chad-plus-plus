package core;

import java.util.Objects;

public class DeclarationType {
    /*
     * Tipos de declaraciones posibles
     * 1. int
     * 2. bool
     * 
     * Posiblemente se deba comprobar que el tipo de dato solo sea uno de los dos
     * anteriores.
     */
    private Type type;

    public DeclarationType() {
        this.type = new Type();
    }

    public DeclarationType(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        // if (type != Type.INT && type != Type.BOOL) {
        // throw new IllegalArgumentException("Type must be INT or BOOL");
        // }
        this.type = new Type();
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeclarationType)) {
            return false;
        }
        DeclarationType declarationType = (DeclarationType) o;
        return Objects.equals(type, declarationType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

}
