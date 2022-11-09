package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    private List<Declaration> listOfDeclaraions;
    private List<Instruction> lisfOfInstructions;

    public Main() {
        this.listOfDeclaraions = new ArrayList<Declaration>();
        this.lisfOfInstructions = new ArrayList<Instruction>();
    }

    public Main(List<Declaration> listOfDeclaraions, List<Instruction> lisfOfInstructions) {
        this.listOfDeclaraions = listOfDeclaraions;
        this.lisfOfInstructions = lisfOfInstructions;
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

    public void addDeclaration(Declaration declaration) {
        this.listOfDeclaraions.add(declaration);
    }

    public void addInstruction(Instruction instruction) {
        this.lisfOfInstructions.add(instruction);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Main)) {
            return false;
        }
        Main main = (Main) o;
        return Objects.equals(listOfDeclaraions, main.listOfDeclaraions)
                && Objects.equals(lisfOfInstructions, main.lisfOfInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfDeclaraions, lisfOfInstructions);
    }

    @Override
    public String toString() {
        return "Main [listOfDeclaraions=" + listOfDeclaraions + ", lisfOfInstructions=" + lisfOfInstructions + "]";
    }

}
