package core;

import java.util.ArrayList;
import java.util.List;

public class WhileInstruction extends Instruction {
    private Expresion expresion;
    private List<Instruction> lisfOfInstructions;

    public WhileInstruction() {
        this.instructionType = TypeInstr.IW;
        this.expresion = new Expresion();
        this.lisfOfInstructions = new ArrayList<>();
    }

    public WhileInstruction(Expresion expresion, List<Instruction> lisfOfInstructions) {
        this.instructionType = TypeInstr.IW;
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
    public String toString() {
        return "WhileInstruction [expresion=" + expresion + ", lisfOfInstructions=" + lisfOfInstructions + "]";
    }

}
