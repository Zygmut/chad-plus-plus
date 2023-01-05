package core;

import intermediate_code.ThreeAddressCode;

public class L_Fn extends BaseNode {
    private Function fn;
    private L_Fn nextFn;

    public L_Fn(Function fn, L_Fn nextFn, int line, int column) {
        super(line, column);
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
        return "L_Fn [fn=" + fn + ", nextFn=" + nextFn + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        fn.generate3dc(codigoTresDir);
        if (nextFn != null) {
            nextFn.generate3dc(codigoTresDir);
        }
    }

}
