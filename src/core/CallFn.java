package core;

import intermediate_code.ThreeAddressCode;

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

    @Override
    public String toString() {
        return "CallFn [id=" + id + ", args=" + args + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // TODO Auto-generated method stub

    }

}
