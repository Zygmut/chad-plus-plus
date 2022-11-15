package core;

public class Output {
    private Expresion expresion;

    public Output(Expresion expresion) {
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
        return "Output [expresion=" + expresion + "]";
    }

}
