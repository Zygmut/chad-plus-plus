package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AsignationIsntruction extends Instruction {
    private List<Id> listaids;
    private Expresion expresion;

    public AsignationIsntruction() {
        this.instructionType = TypeInstr.IA;
        this.listaids = new ArrayList<>();
        this.expresion = new Expresion();
    }

    public AsignationIsntruction(List<Id> listaids, Expresion expresion) {
        this.instructionType = TypeInstr.IA;
        this.listaids = listaids;
        this.expresion = expresion;
    }

    public List<Id> getListaids() {
        return this.listaids;
    }

    public void setListaids(List<Id> listaids) {
        this.listaids = listaids;
    }

    public Expresion getExpresion() {
        return this.expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AsignationIsntruction)) {
            return false;
        }
        AsignationIsntruction asignationIsntruction = (AsignationIsntruction) o;
        return Objects.equals(listaids, asignationIsntruction.listaids)
                && Objects.equals(expresion, asignationIsntruction.expresion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaids, expresion);
    }

    @Override
    public String toString() {
        return "AsignationIsntruction [listaids=" + listaids + ", expresion=" + expresion + "]";
    }

}
