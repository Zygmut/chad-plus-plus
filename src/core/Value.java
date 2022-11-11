package core;

public class Value {
    private String currentInstance;
    private Expresion expresion;
    private Number number;
    private Tuple tuple;
    private Id id;
    private Function function;

    public Value(Expresion expresion) {
        this.expresion = expresion;
        this.currentInstance = "Expresion";
    }

    public Value(Number number) {
        this.number = number;
        this.currentInstance = "Number";
    }

    public Value(Tuple tuple) {
        this.tuple = tuple;
        this.currentInstance = "Tuple";
    }

    public Value(Id id) {
        this.id = id;
        this.currentInstance = "Id";
    }

    public Value(Function function) {
        this.function = function;
        this.currentInstance = "Function";
    }

    @Override
    public String toString() {
        switch (currentInstance) {
            case "Expresion":
                return "Value [expresion=" + expresion + "]";
            case "Number":
                return "Value [number=" + number + "]";
            case "Tuple":
                return "Value [tuple=" + tuple + "]";
            case "Id":
                return "Value [id=" + id + "]";
            case "Function":
                return "Value [function=" + function + "]";
            default:
                return "not valid";
        }

    }

}
