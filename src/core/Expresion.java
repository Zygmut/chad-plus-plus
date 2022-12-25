package core;

import intermediate_code.Instruction;
import java.util.ArrayList;

public class Expresion extends BaseNode {
    private Value value;
    private Expresion nextExpresion;
    private Op op;

    public Expresion(Value value, Expresion nextExpresion, Op op, int line, int column) {
        super(line, column);
        this.value = value;
        this.nextExpresion = nextExpresion;
        this.op = op;
    }

    public Expresion(Value value, int line, int column) {
        super(line, column);
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
        return "Expresion [value=" + value + ", nextExpresion=" + nextExpresion + ", op=" + op + " line=" + line
                + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
