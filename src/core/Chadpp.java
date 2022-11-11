package core;

public class Chadpp {

    private L_Fn nextFn;
    private Main main;

    public Chadpp() {
        this.main = new Main();
        this.nextFn = new L_Fn();
    }

    public Chadpp(Main main, L_Fn functions) {
        this.main = main;
        this.nextFn = functions;
    }

    public void setRoot(Main main, L_Fn functions) {
        this.main = main;
        this.nextFn = functions;
    }

    public L_Fn getNextFn() {
        return nextFn;
    }

    public void setNextFn(L_Fn nextFn) {
        this.nextFn = nextFn;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Chadpp [listOfFunctions=" + nextFn + ", main=" + main + "]";
    }

}