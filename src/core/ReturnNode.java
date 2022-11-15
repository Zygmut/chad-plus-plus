package core;

public class ReturnNode {
    private Expresion expresion;

    public ReturnNode(Expresion expresion) {
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
        return "ReturnNode [expresion=" + expresion + "]";
    }

}
