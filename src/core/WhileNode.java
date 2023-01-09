package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;

public class WhileNode extends BaseNode {
    private Expresion expresion;
    private L_Instrs instrs;

    public WhileNode(Expresion expresion, L_Instrs instrs, int line, int column) {
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
        return "WhileNode [expresion=" + expresion + ", instrs=" + instrs + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        String evalLabel = codigoTresDir.newLabel();
        String evalTrue = codigoTresDir.newLabel();
        String evalFalse = codigoTresDir.newLabel();

        codigoTresDir.addInstr(new Instruction(evalLabel, null, Operator.SKIP, null));
        this.expresion.generate3dc(codigoTresDir);
        codigoTresDir.addInstr(new Instruction(evalTrue, codigoTresDir.getLastVariable().getId(), Operator.IF, null));
        codigoTresDir.addInstr(new Instruction(evalFalse, null, Operator.GOTO, null));

        codigoTresDir.addInstr(new Instruction(evalTrue, null, Operator.SKIP, null));

        this.instrs.generate3dc(codigoTresDir);

        codigoTresDir.addInstr(new Instruction(evalLabel, null, Operator.GOTO, null));
        codigoTresDir.addInstr(new Instruction(evalFalse, null, Operator.SKIP, null));

    }

}
