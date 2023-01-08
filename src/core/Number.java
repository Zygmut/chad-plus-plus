package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Number extends BaseNode {

    private int value;

    public Number(int value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public Number(String value, int line, int column) {
        super(line, column);
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Number [value=" + value + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        Variable var = codigoTresDir.putVar(null, TypeVar.INT);
        codigoTresDir.addInstr(new Instruction(var.getId(), Integer.toString(value), Operator.ASSIGN, null));

    }

}
