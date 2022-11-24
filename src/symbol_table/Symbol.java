package symbol_table;

public class Symbol {

    private final String id;
    private final Type type;
    private final SubType subType;
    private final String value;
    private final String scope;

    public enum Type {
        FUNCTION,
        VARIABLE,
        PARAMETER,
    }

    public enum SubType {
        INT,
        BOOL,
        TUP,
        VOID,
    }

    public Symbol(String id, Type type, SubType subType, String value, String scope) {
        this.id = id;
        this.type = type;
        this.subType = subType;
        this.value = value;
        this.scope = scope;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public SubType getSubType() {
        return subType;
    }

    public String getValue() {
        return value;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", subType=" + subType +
                ", value='" + value + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

}
