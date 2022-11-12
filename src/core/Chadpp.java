package core;

import java.util.HashMap;

public class Chadpp {

    HashMap<String, Object> hm = new HashMap<>();

    private L_Function functions;
    private Main main;

    public Chadpp() {
        this.main = new Main();
        this.functions = new L_Function();
    }

    public Chadpp(Main main) {
        this.main = main;
        this.functions = null;
    }

    public Chadpp(Main main, L_Function functions) {
        this.main = main;
        this.functions = functions;
    }

    public void exec() {
        this.main.run(hm);
    }

    @Override
    public String toString() {
        return "Chadpp [listOfFunctions=" + functions + ", main=" + main + "]";
    }

}