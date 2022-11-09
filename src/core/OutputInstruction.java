package core;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OutputInstruction extends Instruction {
    private List<Object> contents;

    public OutputInstruction() {
        this.instructionType = TypeInstr.IO;
        this.contents = new LinkedList<>();
    }

    public OutputInstruction(List<Object> contents) {
        this.instructionType = TypeInstr.IO;
        this.contents = contents;
    }

    public List<Object> getContents() {
        return this.contents;
    }

    public void setContents(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OutputInstruction)) {
            return false;
        }
        OutputInstruction outputInstruction = (OutputInstruction) o;
        return Objects.equals(contents, outputInstruction.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contents);
    }

    @Override
    public String toString() {
        return "OutputInstruction [contents=" + contents + "]";
    }

}
