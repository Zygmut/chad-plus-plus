package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;

public class Output extends BaseNode {
    private Expresion expresion;

    public Output(Expresion expresion, int line, int column) {
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
        return "Output [expresion=" + expresion + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        this.expresion.generate3dc(codigoTresDir);
        codigoTresDir.addInstr(new Instruction(null, codigoTresDir.getLastVariable().getId(), Operator.OUT, null));
    }

}
