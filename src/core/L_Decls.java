package core;

import java.util.ArrayList;

import intermediate_code.Instruction;

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
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
