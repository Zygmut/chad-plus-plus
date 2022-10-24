package core;

import java.util.Objects;

public class Instruction {

    private InstructionType instructionType;

    public Instruction() {
        this.instructionType = new InstructionType();
    }

    public Instruction(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public InstructionType getInstructionType() {
        return this.instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
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
