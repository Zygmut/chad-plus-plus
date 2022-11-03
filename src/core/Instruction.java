package core;

import java.util.Objects;

public abstract class Instruction {

    protected TypeInstr instructionType;

    public Instruction() {
    }

    public Instruction(TypeInstr instructionType) {
        this.instructionType = instructionType;
    }

    public TypeInstr getInstructionType() {
        return this.instructionType;
    }

    public void setInstructionType(TypeInstr instructionType) {
        this.instructionType = instructionType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Instruction)) {
            return false;
        }
        Instruction instruction = (Instruction) o;
        return Objects.equals(instructionType, instruction.instructionType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instructionType);
    }

}
