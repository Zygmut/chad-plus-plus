package core;

public class L_Args {
    private Expresion arg;
    private L_Args nextArg;

    public L_Args(Expresion arg, L_Args nextArg) {
        this.arg = arg;
        this.nextArg = nextArg;
    }

    public L_Args(Expresion arg) {
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
        return "L_Args [arg=" + arg + ", nextArg=" + nextArg + "]";
    }

}
