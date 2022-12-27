package core;

import intermediate_code.ThreeAddressCode;

/*
*   loop(1,5)
*   for i = 1 to 5 do ++
*   loop(5,1)
*   for i = 5 downto 1 do --
*   loop(1,1)
*   for i = 1 to 1 do ++
*/
public class LoopNode extends BaseNode {
    private Expresion expresion1;
    private Expresion expresion2;
    private L_Instrs instrs;

    public LoopNode(Expresion expresion1, Expresion expresion2, L_Instrs instrs, int line, int column) {
        super(line, column);
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
        this.instrs = instrs;
    }

    public Expresion getExpresion1() {
        return expresion1;
    }

    public void setExpresion1(Expresion expresion1) {
        this.expresion1 = expresion1;
    }

    public Expresion getExpresion2() {
        return expresion2;
    }

    public void setExpresion2(Expresion expresion2) {
        this.expresion2 = expresion2;
    }

    public L_Instrs getInstrs() {
        return instrs;
    }

    public void setInstrs(L_Instrs instrs) {
        this.instrs = instrs;
    }

    @Override
    public String toString() {
        return "LoopNode [expresion1=" + expresion1 + ", expresion2=" + expresion2 + ", instrs=" + instrs + " line="
                + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // TODO Auto-generated method stub

    }

}
