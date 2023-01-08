package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Id extends BaseNode {
    private String value;

    public Id(String value, int line, int column) {
        super(line, column);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Id [value=" + value + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        Variable var = codigoTresDir.putVar(value, null);
        String destName = codigoTresDir.putVar(null, var.getType()).getId();
        codigoTresDir.addInstr(new Instruction(destName, var.getId(), Operator.ASSIGN, null));
    }

}
