package core;

public class WhileNode {
    private Expresion expresion;
    private L_Instrs instrs;

    public WhileNode(Expresion expresion, L_Instrs instrs) {
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
        return "WhileNode [expresion=" + expresion + ", instrs=" + instrs + "]";
    }

}
