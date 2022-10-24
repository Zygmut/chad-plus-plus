package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function {
    private Typef typef;
    private Id id;
    // Parametros de la funcion
    private List<Param> params;
    private List<Declaration> listOfDeclaraions;
    private List<Instruction> lisfOfInstructions;

    public Function() {
        this.typef = new Typef();
        this.id = new Id();
        this.listOfDeclaraions = new ArrayList<Declaration>();
        this.lisfOfInstructions = new ArrayList<Instruction>();
    }

    public Function(Typef typef, Id id, List<Declaration> listOfDeclaraions, List<Instruction> lisfOfInstructions) {
        this.typef = typef;
        this.id = id;
        this.listOfDeclaraions = listOfDeclaraions;
        this.lisfOfInstructions = lisfOfInstructions;
    }

    public void addDeclaration(Declaration declaration) {
        this.listOfDeclaraions.add(declaration);
    }

    /*
     * Comprobar si es una lista o no
     * public void addParam(Param param) { this.params.add(param); }
     */

    public void addInstruction(Instruction instruction) {
        this.lisfOfInstructions.add(instruction);
    }

    public Typef getTypef() {
        return this.typef;
    }

    public void setTypef(Typef typef) {
        this.typef = typef;
    }

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public List<Declaration> getListOfDeclaraions() {
        return this.listOfDeclaraions;
    }

    public void setListOfDeclaraions(List<Declaration> listOfDeclaraions) {
        this.listOfDeclaraions = listOfDeclaraions;
    }

    public List<Instruction> getLisfOfInstructions() {
        return this.lisfOfInstructions;
    }

    public void setLisfOfInstructions(List<Instruction> lisfOfInstructions) {
        this.lisfOfInstructions = lisfOfInstructions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Function)) {
            return false;
        }
        Function function = (Function) o;
        return Objects.equals(typef, function.typef) && Objects.equals(id, function.id)
                && Objects.equals(listOfDeclaraions, function.listOfDeclaraions)
                && Objects.equals(lisfOfInstructions, function.lisfOfInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typef, id, listOfDeclaraions, lisfOfInstructions);
    }

}
