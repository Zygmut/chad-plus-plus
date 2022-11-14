package core;

public class L_Instrs {
    private Instr instr;
    private L_Instrs nextInstr;

    public L_Instrs(Instr instr, L_Instrs nextInstr) {
        this.instr = instr;
        this.nextInstr = nextInstr;
    }

    public L_Instrs(Instr instr) {
        this.instr = instr;
    }

    public Instr getInstr() {
        return instr;
    }

    public void setInstr(Instr instr) {
        this.instr = instr;
    }

    public L_Instrs getNextInstr() {
        return nextInstr;
    }

    public void setNextInstr(L_Instrs nextInstr) {
        this.nextInstr = nextInstr;
    }

    @Override
    public String toString() {
        return "L_Instrs [instr=" + instr + ", nextInstr=" + nextInstr + "]";
    }

}
