package core;

public class LoopNode {
    private Expresion expresion1;
    private Expresion expresion2;
    private L_Instrs instrs;

    public LoopNode(Expresion expresion1, Expresion expresion2, L_Instrs instrs) {
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
        return "LoopNode [expresion1=" + expresion1 + ", expresion2=" + expresion2 + ", instrs=" + instrs + "]";
    }

}
