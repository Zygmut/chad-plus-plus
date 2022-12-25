package core;

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

}
