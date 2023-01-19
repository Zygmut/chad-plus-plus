package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Bol extends BaseNode {
    private boolean value;

    public Bol(String value, int line, int column) {
        super(line, column);
        this.value = Boolean.parseBoolean(value);
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Bol [value=" + value + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        Variable var = codigoTresDir.putVar(null, TypeVar.BOOL);
        codigoTresDir.addInstr(new Instruction(var.getId(), Boolean.toString(value), Operator.ASSIGN, null));
    }

}
