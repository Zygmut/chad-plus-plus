package core;

import intermediate_code.ThreeAddressCode;

public class Decl extends BaseNode {

    private boolean isConstant;
    private TypeVar type;
    private Asignation asignation;

    public Decl(boolean isConstant, TypeVar type, Asignation asignation, int line, int column) {
        super(line, column);
        this.isConstant = isConstant;
        this.type = type;
        this.asignation = asignation;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public void setConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }

    public TypeVar getType() {
        return type;
    }

    public void setType(TypeVar type) {
        this.type = type;
    }

    public Asignation getAsignation() {
        return asignation;
    }

    public void setAsignation(Asignation asignation) {
        this.asignation = asignation;
    }

    @Override
    public String toString() {
        return "Decl [isConstant=" + isConstant + ", type=" + type + ", asignation=" + asignation + " line=" + line
                + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        codigoTresDir.startDeclaration(type);
        asignation.generate3dc(codigoTresDir);
        codigoTresDir.endDeclaration();
    }

}
