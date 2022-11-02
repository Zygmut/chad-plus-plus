package core;

import java.util.Objects;

public class Declaration {

    private String constant; // Check if needs to be a String or a class
    private TypeVar type;
    private String tuple; // Check if needs to be a String or a class
    private Asignation asignation;

    public Declaration(String constant, TypeVar type, String tuple, Asignation asignation) {
        this.constant = constant;
        this.type = type;
        this.tuple = tuple;
        this.asignation = asignation;
    }

    public Declaration() {
        this.constant = "";
        this.type = new TypeVar();
        this.tuple = "";
        this.asignation = new Asignation();
    }

    public String getConstant() {
        return this.constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public TypeVar getType() {
        return this.type;
    }

    public void setType(TypeVar type) {
        this.type = type;
    }

    public String getTuple() {
        return this.tuple;
    }

    public void setTuple(String tuple) {
        this.tuple = tuple;
    }

    public Asignation getAsignation() {
        return this.asignation;
    }

    public void setAsignation(Asignation asignation) {
        this.asignation = asignation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Declaration)) {
            return false;
        }
        Declaration declaration = (Declaration) o;
        return Objects.equals(constant, declaration.constant) && Objects.equals(type, declaration.type)
                && Objects.equals(tuple, declaration.tuple) && Objects.equals(asignation, declaration.asignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant, type, tuple, asignation);
    }

}
