package core;

public class Main {
    private L_Decls nextDecl;
    private L_Instrs nextInstr;

    public Main() {
        this.nextDecl = null;
        this.nextInstr = null;
    }

    public Main(L_Decls nextDecl, L_Instrs nextInstr) {
        this.nextDecl = nextDecl;
        this.nextInstr = nextInstr;
    }

    public L_Decls getNextDecl() {
        return nextDecl;
    }

    public void setNextDecl(L_Decls nextDecl) {
        this.nextDecl = nextDecl;
    }

    public L_Instrs getNextInstr() {
        return nextInstr;
    }

    public void setNextInstr(L_Instrs nextInstr) {
        this.nextInstr = nextInstr;
    }

    @Override
    public String toString() {
        return "Main [nextDecl=" + nextDecl + ", nextInstr=" + nextInstr + "]";
    }

}
