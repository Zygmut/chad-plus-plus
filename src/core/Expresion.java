package core;

import java.util.ArrayList;

import intermediate_code.ThreeAddressCode;

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

    public ArrayList<Object> toListOfObjects(Expresion expresion) {
        ArrayList<Object> list = new ArrayList<>();
        for (Expresion exp = expresion; exp != null; exp = exp.getNextExpresion()) {
            list.add(exp.getValue());
            if (exp.getOp() == null) {
                break;
            }
            list.add(exp.getOp());
        }

        // Mirar los valores pares de la lista, ya que son los Values
        // Mirar la instancia de estos Values
        // Si es una expresión se llama
        return list;
    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        ArrayList<Object> list = toListOfObjects(this);
        // Prioridad 30 / 2 * 5 -> (30 / 2) * 5
        // 1. Buscar exp -> Obtener lista
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Expresion) {
                // list.set(i, toListOfObjects((Expresion) list.get(i)));
            }
        }
        // 2. Buscar Value y operandos

    }

}
