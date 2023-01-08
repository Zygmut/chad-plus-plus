package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import symbol_table.StructureReturnType;

public class Function extends BaseNode {
    private TypeVar returnType;
    private Id id;
    private L_FArgs arguments;
    private L_Decls decls;
    private L_Instrs instrs;

    public Function(TypeVar returnType, Id id, L_FArgs arguments, L_Decls decls, L_Instrs instrs,
            int line, int column) {
        super(line, column);
        this.returnType = returnType;
        this.id = id;
        this.arguments = arguments;
        this.decls = decls;
        this.instrs = instrs;
    }

    public TypeVar getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeVar returnType) {
        this.returnType = returnType;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public L_FArgs getArguments() {
        return arguments;
    }

    public void setArguments(L_FArgs arguments) {
        this.arguments = arguments;
    }

    public L_Decls getDecls() {
        return decls;
    }

    public void setDecls(L_Decls decls) {
        this.decls = decls;
    }

    public L_Instrs getInstrs() {
        return instrs;
    }

    public void setInstrs(L_Instrs instrs) {
        this.instrs = instrs;
    }

    private StructureReturnType returnTypetoStructureReturnType() {
        if (returnType == null) {
            return StructureReturnType.VOID;
        }
        switch (returnType.name()) {
            case "INT":
                return StructureReturnType.INT;
            case "BOOL":
                return StructureReturnType.BOOL;
            case "TUP":
                return StructureReturnType.TUP;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Function [returnType=" + returnType + ", id=" + id + ", arguments=" + arguments + ", decls=" + decls
                + ", instrs=" + instrs + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        codigoTresDir.newFn(id.getValue(), returnTypetoStructureReturnType());
        codigoTresDir.addInstr(new Instruction("run_" + id.getValue(), null, Operator.SKIP, null));

        if (this.arguments != null) {
            codigoTresDir.toggleParams();
            this.arguments.generate3dc(codigoTresDir);
            codigoTresDir.toggleParams();
        }
        if (this.decls != null) {
            this.decls.generate3dc(codigoTresDir);
        }
        if (this.instrs != null) {
            this.instrs.generate3dc(codigoTresDir);
        }
        codigoTresDir.addInstr(new Instruction(null, null, Operator.RETURN, null));
    }

}
