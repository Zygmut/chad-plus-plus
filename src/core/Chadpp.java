package core;

import intermediate_code.ThreeAddressCode;

public class Chadpp extends BaseNode {

    private L_Fn functions;
    private Main main;
    private L_Decls l_decls;

    public Chadpp() {
        super(0, 0);
        this.main = new Main();
        this.functions = null;
        this.l_decls = null;
    }

    public Chadpp(L_Decls l_decls, Main main, int line, int column) {
        super(line, column);
        this.main = main;
        this.functions = null;
        this.l_decls = null;
    }

    public Chadpp(L_Decls l_decls, Main main, L_Fn functions, int line, int column) {
        super(line, column);
        this.main = main;
        this.functions = functions;
        this.l_decls = l_decls;
    }

    public L_Fn getFunctions() {
        return functions;
    }

    public void setFunctions(L_Fn functions) {
        this.functions = functions;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public L_Decls getL_decls() {
        return l_decls;
    }

    public void setL_decls(L_Decls l_decls) {
        this.l_decls = l_decls;
    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        if (l_decls != null) {
            l_decls.generate3dc(codigoTresDir);
        }

        main.generate3dc(codigoTresDir);

        if (functions != null) {
            functions.generate3dc(codigoTresDir);
        }
    }

    @Override
    public String toString() {
        return "Chadpp [functions=" + functions + ", main=" + main + ", l_decls=" + l_decls + "]";
    }
}