package core;

public class Value {
    private String currentInstance;
    private Expresion expresion;
    private Number number;
    private Bol bol;
    private Tuple tuple;
    private Id id;
    private CallFn callFn;

    public Value(String instance, String value) {
        switch (instance) {
            case "Number":
                this.currentInstance = instance;
                this.number = new Number(value);
                break;
            case "Bol":
                this.currentInstance = instance;
                this.bol = new Bol(value);
                break;
            case "Id":
                this.currentInstance = instance;
                this.id = new Id(value);
                break;
            default:
                this.currentInstance = "error";

                // return "not valid";
        }
    }

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

    public Value(CallFn callFn) {
        this.callFn = callFn;
        this.currentInstance = "CallFn";
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
            case "Bol":
                return "Value [bol=" + bol + "]";
            case "Id":
                return "Value [id=" + id + "]";
            case "CallFn":
                return "Value [callFn=" + callFn + "]";
            default:
                return "not valid";
        }

    }

}
