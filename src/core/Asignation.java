package core;

public class Asignation {

    private String id;
    private Expresion expresion;

    public Asignation(String id, Expresion expresion) {
        this.id = id;
        this.expresion = expresion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "Asignation [id=" + id + ", expresion=" + expresion + "]";
    }

}
