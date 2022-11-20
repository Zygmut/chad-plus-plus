package symbol_table;

public class Symbol {

    private final String name;
    private final String type;
    private final String value;
    private final String scope;

    public Symbol(String name, String type, String value, String scope) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

}
