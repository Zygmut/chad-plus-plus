package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Asignation extends BaseNode {

    private L_Ids lid;
    private Expresion expresion;

    public Asignation(L_Ids id, Expresion expresion, int line, int column) {
        super(line, column);
        this.lid = id;
        this.expresion = expresion;
    }

    public L_Ids getL_Ids() {
        return lid;
    }

    public void setL_Ids(L_Ids lid) {
        this.lid = lid;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "Asignation [L_Ids=" + lid + ", expresion=" + expresion + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        lid.generate3dc(codigoTresDir);
        this.expresion.generate3dc(codigoTresDir);
        String expLoc = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1).getId();
        for (Variable var : codigoTresDir.getLids()) {
            codigoTresDir.addInstr(new Instruction(var.getId(), expLoc, Operator.ASSIGN, null));
        }

        codigoTresDir.purgeIds();
    }

}
