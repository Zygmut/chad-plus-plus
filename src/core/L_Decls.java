package core;

public class L_Decls {
    private L_Decls nextDecl;
    private Decl decl;

    public L_Decls() {
        this.nextDecl = null;
        this.decl = null;
    }

    public L_Decls(Decl decl) {
        this.nextDecl = null;
        this.decl = decl;
    }

    public L_Decls(Decl decl, L_Decls nextDecl) {
        this.nextDecl = nextDecl;
        this.decl = decl;
    }

    public L_Decls nextDecl() {
        return nextDecl;
    }

    public void setNextDecl(L_Decls nextDecl) {
        this.nextDecl = nextDecl;
    }

    public Decl getDecl() {
        return decl;
    }

    public void setDecl(Decl decl) {
        this.decl = decl;
    }

    @Override
    public String toString() {
        return "L_Decls [decl=" + decl + ", nextDecl=" + nextDecl + "]";
    }

}
