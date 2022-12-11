package core;

import java.util.HashMap;

public class Chadpp {

    private L_Fn functions;
    private Main main;

    public Chadpp() {
        this.main = new Main();
        this.functions = null;
    }

    public Chadpp(Main main) {
        this.main = main;
        this.functions = null;
    }

    public Chadpp(Main main, L_Fn functions) {
        this.main = main;
        this.functions = functions;
    }

    public Main getMain() {
        return this.main;
    }

    public L_Fn getL_Fn() {
        return this.functions;
    }

    @Override
    public String toString() {
        return "Chadpp [listOfFunctions=" + functions + ", main=" + main + "]";
    }

}