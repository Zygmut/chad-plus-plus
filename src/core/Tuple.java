package core;

import java.util.ArrayList;

import intermediate_code.Instruction;

public class Tuple extends BaseNode {
    private L_Args tupleContent;

    public Tuple(L_Args tupleContent, int line, int column) {
        super(line, column);
        this.tupleContent = tupleContent;
    }

    public L_Args getTupleContent() {
        return tupleContent;
    }

    public void setTupleContent(L_Args tupleContent) {
        this.tupleContent = tupleContent;
    }

    @Override
    public String toString() {
        return "Tuple [tupleContent=" + tupleContent + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
