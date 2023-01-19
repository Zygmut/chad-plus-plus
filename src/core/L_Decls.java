package core;

import intermediate_code.ThreeAddressCode;

public class L_Decls extends BaseNode {
    private L_Decls nextDecl;
    private Decl decl;

    public L_Decls() {
        super(0, 0);
        this.nextDecl = null;
        this.decl = null;
    }

    public L_Decls(Decl decl, int line, int column) {
        super(line, column);
        this.nextDecl = null;
        this.decl = decl;
    }

    public L_Decls(Decl decl, L_Decls nextDecl, int line, int column) {
        super(line, column);
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
        return "L_Decls [decl=" + decl + ", nextDecl=" + nextDecl + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        decl.generate3dc(codigoTresDir);
        if (nextDecl != null) {
            nextDecl.generate3dc(codigoTresDir);
        }
    }

}
