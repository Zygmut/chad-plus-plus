package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoopInstruction extends Instruction {

    private Expresion start;
    private Expresion finish;
    private List<Instruction> lisfOfInstructions;

    public LoopInstruction() {
        this.instructionType = TypeInstr.IL;
        this.start = new Expresion();
        this.finish = new Expresion();
        this.lisfOfInstructions = new ArrayList<>();
    }

    public LoopInstruction(Expresion start, Expresion finish, List<Instruction> instructions) {
        this.instructionType = TypeInstr.IL;
        this.start = start;
        this.finish = finish;
        this.lisfOfInstructions = instructions;
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
        return this.lisfOfInstructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.lisfOfInstructions = instructions;
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
                && Objects.equals(lisfOfInstructions, loopInstruction.lisfOfInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish, lisfOfInstructions);
    }

    @Override
    public String toString() {
        return "LoopInstruction [start=" + start + ", finish=" + finish + ", lisfOfInstructions=" + lisfOfInstructions
                + "]";
    }

}