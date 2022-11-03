package core;

import java.util.Objects;

public class ReturnInstruction extends Instruction {
    private Expresion expresion;

    public ReturnInstruction() {
        this.instructionType = TypeInstr.IR;
        this.expresion = new Expresion();
    }

    public ReturnInstruction(Expresion expresion) {
        this.instructionType = TypeInstr.IR;
        this.expresion = expresion;
    }

    public Expresion getExpresion() {
        return this.expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ReturnInstruction)) {
            return false;
        }
        ReturnInstruction returnInstruction = (ReturnInstruction) o;
        return Objects.equals(expresion, returnInstruction.expresion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(expresion);
    }

    @Override
    public String toString() {
        return "{ Return: " +
                " expresion='" + getExpresion() + "'" +
                "}";
    }

}
