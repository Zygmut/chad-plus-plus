package core;

public class L_Fn {
    private Function fn;
    private L_Fn nextFn;

    public L_Fn(Function fn, L_Fn nextFn) {
        this.fn = fn;
        this.nextFn = nextFn;
    }

    public Function getFn() {
        return fn;
    }

    public void setFn(Function fn) {
        this.fn = fn;
    }

    public L_Fn getNextFn() {
        return nextFn;
    }

    public void setNextFn(L_Fn nextFn) {
        this.nextFn = nextFn;
    }

    @Override
    public String toString() {
        return "L_Fn [fn=" + fn + ", nextFn=" + nextFn + "]";
    }

}
