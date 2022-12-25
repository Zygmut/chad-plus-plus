package core;

import symbol_table.SymbolTable;
import intermediate_code.Instruction;
import java.util.ArrayList;

public class Main extends BaseNode {
    private L_Decls listaDecl;
    private L_Instrs listaInstr;
    private SymbolTable symbolTable;

    public Main() {
        super(0, 0);
        this.listaDecl = null;
        this.listaInstr = null;
        this.symbolTable = null;
    }

    public Main(L_Decls listaDecl, L_Instrs listaInstr, SymbolTable symbolTable, int line, int column) {
        super(line, column);
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
        return "Main [nextDecl=" + listaDecl + ", nextInstr=" + listaInstr + ", symbolTable=" + symbolTable + " line="
                + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
