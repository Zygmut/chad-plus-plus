package core;

public class Chadpp extends BaseNode {

    private L_Fn functions;
    private Main main;

    public Chadpp() {
        super(0, 0);
        this.main = new Main();
        this.functions = null;
    }

    public Chadpp(Main main, int line, int column) {
        super(line, column);
        this.main = main;
        this.functions = null;
    }

    public Chadpp(Main main, L_Fn functions, int line, int column) {
        super(line, column);
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
        return "Chadpp [listOfFunctions=" + functions + ", main=" + main + " line=" + line + " column=" + column + "]";

    }

}