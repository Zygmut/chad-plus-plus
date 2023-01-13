package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;

public class IfNode extends BaseNode {
    private Expresion expresion;
    private L_Instrs instrs;

    public IfNode(Expresion expresion, L_Instrs instrs, int line, int column) {
        super(line, column);
        this.expresion = expresion;
        this.instrs = instrs;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public L_Instrs getInstrs() {
        return instrs;
    }

    public void setInstrs(L_Instrs instrs) {
        this.instrs = instrs;
    }

    @Override
    public String toString() {
        return "IfNode [expresion=" + expresion + ", instrs=" + instrs + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        this.expresion.generate3dc(codigoTresDir);
        String eTrue = codigoTresDir.newLabel();
        String eFalse = codigoTresDir.newLabel();
        codigoTresDir.addInstr(new Instruction(eTrue, codigoTresDir.getLastVariable().getId(), Operator.IF, null));
        codigoTresDir.addInstr(new Instruction(eFalse, null, Operator.GOTO, null));
        codigoTresDir.addInstr(new Instruction(eTrue, null, Operator.SKIP, null));
        if (this.instrs != null) {
            this.instrs.generate3dc(codigoTresDir);
        }
        codigoTresDir.addInstr(new Instruction(eFalse, null, Operator.SKIP, null));
    }

}
