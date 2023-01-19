package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import symbol_table.StructureReturnType;

public class Main extends BaseNode {
    private L_Decls listaDecl;
    private L_Instrs listaInstr;

    public Main() {
        super(0, 0);
        this.listaDecl = null;
        this.listaInstr = null;
    }

    public Main(L_Decls listaDecl, L_Instrs listaInstr, int line, int column) {
        super(line, column);
        this.listaDecl = listaDecl;
        this.listaInstr = listaInstr;
    }

    public L_Decls getListaDecl() {
        return listaDecl;
    }

    public L_Instrs getListaInstr() {
        return listaInstr;
    }

    @Override
    public String toString() {
        return "Main [nextDecl=" + listaDecl + ", nextInstr=" + listaInstr + " line="
                + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        codigoTresDir.newFn("main", StructureReturnType.VOID);
        if (this.listaDecl != null) {
            this.listaDecl.generate3dc(codigoTresDir);
        }

        if (this.listaInstr != null) {
            this.listaInstr.generate3dc(codigoTresDir);
        }
        codigoTresDir.addInstr(new Instruction(null, null, Operator.EXIT, null));
    }

}
