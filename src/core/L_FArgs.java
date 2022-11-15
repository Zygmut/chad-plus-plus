package core;

public class L_FArgs {

    private FnArg arg;
    private L_FArgs nextArg;

    public L_FArgs(FnArg arg, L_FArgs nextArg) {
        this.arg = arg;
        this.nextArg = nextArg;
    }

    public FnArg getArg() {
        return arg;
    }

    public void setArg(FnArg arg) {
        this.arg = arg;
    }

    public L_FArgs getNextArg() {
        return nextArg;
    }

    public void setNextArg(L_FArgs nextArg) {
        this.nextArg = nextArg;
    }

    @Override
    public String toString() {
        return "L_FArgs [arg=" + arg + ", nextArg=" + nextArg + "]";
    }

}
