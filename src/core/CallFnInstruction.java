package core;

import java.util.Objects;

public class CallFnInstruction extends Instruction {
    private Function function;

    public CallFnInstruction() {
        this.instructionType = TypeInstr.IC;
        this.function = new Function();
    }

    public CallFnInstruction(Function function) {
        this.instructionType = TypeInstr.IC;
        this.function = function;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CallFnInstruction)) {
            return false;
        }
        CallFnInstruction callFnInstruction = (CallFnInstruction) o;
        return Objects.equals(function, callFnInstruction.function);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(function);
    }

    @Override
    public String toString() {
        return "{ Function call: " +
                " function='" + getFunction() + "'" +
                "}";
    }

}
