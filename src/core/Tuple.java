package core;

import intermediate_code.ThreeAddressCode;

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
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // TODO Auto-generated method stub

    }

}
