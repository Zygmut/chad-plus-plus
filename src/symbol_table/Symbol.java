package symbol_table;

import java.util.Objects;

import core.TypeFn;

public class Symbol {
    private String id;
    private int scope; // level
    private SymbolType type;
    private TypeFn subtype;

    public Symbol(String id, int scope, SymbolType type, TypeFn subtype) {
        this.id = id;
        this.scope = scope;
        this.type = type;
        this.subtype = subtype;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScope() {
        return this.scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public SymbolType getType() {
        return this.type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public TypeFn getSubtype() {
        return this.subtype;
    }

    public void setSubtype(TypeFn subtype) {
        this.subtype = subtype;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Symbol)) {
            return false;
        }
        Symbol symbol = (Symbol) o;
        return Objects.equals(id, symbol.id) && scope == symbol.scope && Objects.equals(type, symbol.type)
                && Objects.equals(subtype, symbol.subtype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scope, type, subtype);
    }

    @Override
    public String toString() {
        return "{ Symbol: " +
                " id='" + getId() + "'" +
                ", scope='" + getScope() + "'" +
                ", type='" + getType() + "'" +
                ", subtype='" + getSubtype() + "'" +
                "}";
    }

}
