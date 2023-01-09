package core;

import intermediate_code.ThreeAddressCode;

public class L_Args extends BaseNode {
    private Expresion arg;
    private L_Args nextArg;

    public L_Args(Expresion arg, L_Args nextArg, int line, int column) {
        super(line, column);
        this.arg = arg;
        this.nextArg = nextArg;
    }

    public L_Args(Expresion arg, int line, int column) {
        super(line, column);
        this.arg = arg;
    }

    public Expresion getArg() {
        return arg;
    }

    public void setArg(Expresion arg) {
        this.arg = arg;
    }

    public L_Args getNextArg() {
        return nextArg;
    }

    public void setNextArg(L_Args nextArg) {
        this.nextArg = nextArg;
    }

    @Override
    public String toString() {
        return "L_Args [arg=" + arg + ", nextArg=" + nextArg + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        this.arg.generate3dc(codigoTresDir);
        codigoTresDir.addArg(codigoTresDir.getLastVariable());

        if (this.nextArg != null) {
            this.nextArg.generate3dc(codigoTresDir);
        }

    }

}
