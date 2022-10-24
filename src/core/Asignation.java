package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Asignation {

    private List<Id> listOfIds;
    private Expresion expresion;

    public Asignation() {
        this.listOfIds = new ArrayList<>();
        this.expresion = new Expresion();
    }

    public Asignation(List<Id> listOfIds, Expresion expresion) {
        this.listOfIds = listOfIds;
        this.expresion = expresion;
    }

    public List<Id> getListOfIds() {
        return this.listOfIds;
    }

    public void setListOfIds(List<Id> listOfIds) {
        this.listOfIds = listOfIds;
    }

    public Expresion getExpresion() {
        return this.expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public void addId(Id id) {
        this.listOfIds.add(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Asignation)) {
            return false;
        }
        Asignation asignation = (Asignation) o;
        return Objects.equals(listOfIds, asignation.listOfIds) && Objects.equals(expresion, asignation.expresion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfIds, expresion);
    }

}
