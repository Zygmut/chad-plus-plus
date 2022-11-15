package core;

public class Expresion {
    private Value value;
    private Expresion nextExpresion;
    private Op op;

    public Expresion(Value value, Expresion nextExpresion, Op op) {
        this.value = value;
        this.nextExpresion = nextExpresion;
        this.op = op;
    }

    public Expresion(Value value) {
        this.value = value;
        this.nextExpresion = null;
        this.op = null;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Expresion getNextExpresion() {
        return nextExpresion;
    }

    public void setNextExpresion(Expresion nextExpresion) {
        this.nextExpresion = nextExpresion;
    }

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "Expresion [value=" + value + ", nextExpresion=" + nextExpresion + ", op=" + op + "]";
    }

}
