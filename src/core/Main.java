package core;

import java.util.HashMap;

public class Main {
    private L_Decls listaDecl;
    private L_Instrs listaInstr;

    public Main() {
        this.listaDecl = null;
        this.listaInstr = null;
    }

    public Main(L_Decls listaDecl, L_Instrs listaInstr) {
        this.listaDecl = listaDecl;
        this.listaInstr = listaInstr;
    }

    public void run(HashMap<String, Object> hm) {
        // listaDecl.run(hm);
        listaInstr.run(hm);
    }

    @Override
    public String toString() {
        return "Main [nextDecl=" + listaDecl + ", nextInstr=" + listaInstr + "]";
    }

}
