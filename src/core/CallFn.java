package core;

import intermediate_code.ThreeAddressCode;
import symbol_table.StructureReturnType;
import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.Procedimiento;

public class CallFn extends BaseNode {
    private Id id;
    private L_Args args;

    public CallFn(Id id, L_Args args, int line, int column) {
        super(line, column);
        this.id = id;
        this.args = args;
    }

    public CallFn(Id id, int line, int column) {
        super(line, column);
        this.id = id;
        this.args = null;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public L_Args getArgs() {
        return args;
    }

    public void setArgs(L_Args args) {
        this.args = args;
    }

    private TypeVar structureReturnTypeToTypeVar(StructureReturnType srt) {
        switch (srt.name()) {
            case "INT":
                return TypeVar.INT;
            case "BOOL":
                return TypeVar.BOOL;
            case "TUP":
                return TypeVar.TUP;
            default:
                return null;
        }

    }

    @Override
    public String toString() {
        return "CallFn [id=" + id + ", args=" + args + " line=" + line + " column=" + column + "]";
    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        Procedimiento prod = codigoTresDir.getProcedimiento(this.id.getValue());
        String varName = codigoTresDir.putVar(null, structureReturnTypeToTypeVar(prod.getReturnType())).getId();
        codigoTresDir.addInstr(new Instruction(varName, id.getValue(), Operator.CALL, null));
    }

}
