package core;

public class Function {
    private TypeFn returnType;
    private Id id;
    private L_FArgs arguments;
    private L_Decls decls;
    private L_Instrs instrs;

    public Function(TypeFn returnType, Id id, L_FArgs arguments, L_Decls decls, L_Instrs instrs) {
        this.returnType = returnType;
        this.id = id;
        this.arguments = arguments;
        this.decls = decls;
        this.instrs = instrs;
    }

    public TypeFn getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeFn returnType) {
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

    @Override
    public String toString() {
        return "Function [returnType=" + returnType + ", id=" + id + ", arguments=" + arguments + ", decls=" + decls
                + ", instrs=" + instrs + "]";
    }

}
