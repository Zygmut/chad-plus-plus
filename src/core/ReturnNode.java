package core;

import java.util.ArrayList;

import intermediate_code.Instruction;

public class ReturnNode extends BaseNode {
    private Expresion expresion;

    public ReturnNode(Expresion expresion, int line, int column) {
        super(line, column);
        this.expresion = expresion;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "ReturnNode [expresion=" + expresion + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
