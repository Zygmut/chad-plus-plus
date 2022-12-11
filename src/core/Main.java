package core;

import java.util.HashMap;

import symbol_table.SymbolTable;

public class Main {
    private L_Decls listaDecl;
    private L_Instrs listaInstr;
    private SymbolTable symbolTable;

    public Main() {
        this.listaDecl = null;
        this.listaInstr = null;
        this.symbolTable = null;
    }

    public Main(L_Decls listaDecl, L_Instrs listaInstr, SymbolTable symbolTable) {
        this.listaDecl = listaDecl;
        this.listaInstr = listaInstr;
        this.symbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public L_Decls getListaDecl() {
        return listaDecl;
    }

    public L_Instrs getListaInstr() {
        return listaInstr;
    }

    @Override
    public String toString() {
        return "Main [nextDecl=" + listaDecl + ", nextInstr=" + listaInstr + ", symbolTable=" + symbolTable + "]";
    }

}
