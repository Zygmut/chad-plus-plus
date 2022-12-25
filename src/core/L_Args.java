package core;

import java.util.ArrayList;

import intermediate_code.Instruction;

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
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
