package core;

public class Asignation {

    private L_Ids lid;
    private Expresion expresion;

    public Asignation(L_Ids id, Expresion expresion) {
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
        return "Asignation [L_Ids=" + lid + ", expresion=" + expresion + "]";
    }

}
