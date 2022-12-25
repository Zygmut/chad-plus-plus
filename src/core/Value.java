package core;

public class Value {
    private String currentInstance;
    private Expresion expresion;
    private Number number;
    private Bol bol;
    private Tuple tuple;
    private Id id;
    private CallFn callFn;
    private A_Tuple aTuple;
    private Input input;

    public Value(String instance, String value, int line, int column) {
        switch (instance) {
            case "Number":
                this.currentInstance = instance;
                this.number = new Number(value, line, column);
                break;
            case "Bol":
                this.currentInstance = instance;
                this.bol = new Bol(value, line, column);
                break;
            case "Id":
                this.currentInstance = instance;
                this.id = new Id(value, line, column);
                break;
            default:
                this.currentInstance = "error";

                // return "not valid";
        }
    }

    public Value(A_Tuple aTuple) {
        this.currentInstance = "A_Tuple";
        this.aTuple = aTuple;
    }

    public Value(Input input) {
        this.currentInstance = "Input";
        this.input = input;
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

    public String getCurrentInstance() {
        return currentInstance;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public Number getNumber() {
        return number;
    }

    public Bol getBol() {
        return bol;
    }

    public Tuple getTuple() {
        return tuple;
    }

    public Id getId() {
        return id;
    }

    public CallFn getCallFn() {
        return callFn;
    }

    public A_Tuple getaTuple() {
        return aTuple;
    }

    public Input getInput() {
        return input;
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
            case "A_Tuple":
                return "Value [aTuple=" + aTuple + "]";
            case "Input":
                return "Value [input=" + input + "]";
            default:
                return "not valid";
        }

    }

}
