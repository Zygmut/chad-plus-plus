package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Input extends BaseNode {
    private int type; // 0 int, 1 bool

    public Input(int type, int line, int column) {
        super(line, column);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Input [type=" + type + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        Variable dest;
        if (type == 0) {
            dest = codigoTresDir.putVar(null, TypeVar.INT);
            codigoTresDir.addInstr(new Instruction(dest.getId(), null, Operator.IN_INT, null));
        } else {
            dest = codigoTresDir.putVar(null, TypeVar.BOOL);
            codigoTresDir.addInstr(new Instruction(dest.getId(), null, Operator.IN_BOL, null));
        }
    }

}
