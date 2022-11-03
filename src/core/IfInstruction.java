package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IfInstruction extends Instruction {

    private Expresion expresion;
    private List<Instruction> lisfOfInstructions;

    public IfInstruction() {
        this.instructionType = TypeInstr.II;
        this.expresion = new Expresion();
        this.lisfOfInstructions = new ArrayList<>();
    }

    public IfInstruction(Expresion expresion, List<Instruction> lisfOfInstructions) {
        this.instructionType = TypeInstr.II;
        this.expresion = expresion;
        this.lisfOfInstructions = lisfOfInstructions;
    }

    public Expresion getExpresion() {
        return this.expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
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
        if (!(o instanceof IfInstruction)) {
            return false;
        }
        IfInstruction ifInstruction = (IfInstruction) o;
        return Objects.equals(expresion, ifInstruction.expresion)
                && Objects.equals(lisfOfInstructions, ifInstruction.lisfOfInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expresion, lisfOfInstructions);
    }

    @Override
    public String toString() {
        return "{ If: " +
                " expresion='" + getExpresion() + "'" +
                ", lisfOfInstructions='" + getLisfOfInstructions() + "'" +
                "}";
    }

}
