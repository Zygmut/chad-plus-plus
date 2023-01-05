package core;

import intermediate_code.ThreeAddressCode;

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
        /*
         * for (Variable var : codigoTresDir.listaIds()) {
         * asignar a cada id la expresion
         * }
         */
    }

}
