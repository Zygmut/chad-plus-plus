package core;

import java.util.List;
import java.util.Objects;

public class LoopInstruction extends Instruction {

    private Expresion start;
    private Expresion finish;
    private List<Instruction> instructions;

    public LoopInstruction() {
        this.instructionType = TypeInstr.IL;
    }

    public LoopInstruction(Expresion start, Expresion finish, List<Instruction> instructions) {
        this.start = start;
        this.finish = finish;
        this.instructions = instructions;
    }

    public Expresion getStart() {
        return this.start;
    }

    public void setStart(Expresion start) {
        this.start = start;
    }

    public Expresion getFinish() {
        return this.finish;
    }

    public void setFinish(Expresion finish) {
        this.finish = finish;
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoopInstruction)) {
            return false;
        }
        LoopInstruction loopInstruction = (LoopInstruction) o;
        return Objects.equals(start, loopInstruction.start) && Objects.equals(finish, loopInstruction.finish)
                && Objects.equals(instructions, loopInstruction.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish, instructions);
    }

    @Override
    public String toString() {
        return "{" +
                " start='" + getStart() + "'" +
                ", finish='" + getFinish() + "'" +
                ", instructions='" + getInstructions() + "'" +
                "}";
    }
}