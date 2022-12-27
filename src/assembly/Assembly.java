package assembly;

import java.util.ArrayList;
import intermediate_code.Instruction;

/**
 * Assembly
 */
public class Assembly {

    private ArrayList<Instruction> threeAddressCode;
    private ArrayList<String> assemblyCode;
    private ArrayList<Object> tv;
    private ArrayList<Object> tp;
    private ArrayList<Object> te;

    public Assembly(ArrayList<Instruction> threeAddressCode) {
        this.threeAddressCode = threeAddressCode;
        this.assemblyCode = new ArrayList<>();
        this.tv = new ArrayList<>();
        this.tp = new ArrayList<>();
        this.te = new ArrayList<>();
    }

    public void generateAssemblyCode() {
        generateAssemblyCodePreamble();
        // 68k assembly code
        assemblyCode();
        generateAssemblyCodePostamble();
    }

    private void generateAssemblyCodePreamble() {
        // 68k assembly code preamble
        assemblyCode.add("\t\tORG\t$1000");
        assemblyCode.add("START\t");

    }

    private void generateAssemblyCodePostamble() {
        // 68k assembly code postamble
        assemblyCode.add("\t\tSIMHALT");
        assemblyCode.add("\t\tEND\tSTART");
    }

    private void assemblyCode() {
        generateAssemblySubroutinesCode();
        for (Instruction instruction : threeAddressCode) {
            generateAssemblyCodeForInstruction(instruction);
        }
    }

    /**
     * Genera el código emsamblador para las subrutinas usadas en el programa. Las
     * subrutinas son:
     * <ul>
     * <li>PRINT_INT</li>
     * <li>PRINT_STRING</li>
     * <li>READ_INT</li>
     * <li>READ_STRING</li>
     * </ul>
     */
    private void generateAssemblySubroutinesCode() {
        // 68k assembly code for subroutines
        // PRINT_INT
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("PRINT_INT");
        assemblyCode.add("; PRINTS AN INTEGER");
        assemblyCode.add("; INPUT: D1 - INTEGER TO PRINT");
        assemblyCode.add("; OUTPUT: NONE");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tMOVE.L\t#3,D0\t; PRINT_INT");
        assemblyCode.add("\tTRAP\t#15\t; PRINT_INT CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; Return from subroutine");
        // PRINT_STRING
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("PRINT_STRING");
        assemblyCode.add("; PRINTS A STRING");
        assemblyCode.add("; INPUT: A1 - STRING TO PRINT");
        assemblyCode.add("; OUTPUT: NONE");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tMOVE.L\tA1,-(A7)\t; SAVE A1");
        assemblyCode.add("\tMOVE.L\t#0,D0\t; PRINT_STRING");
        assemblyCode.add("\tTRAP\t#15\t; PRINT_STRING CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,A1\t; RESTORE A1");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; Return from subroutine");
        // READ_INT
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("READ_INT");
        assemblyCode.add("; READS AN INTEGER");
        assemblyCode.add("; INPUT: NONE");
        assemblyCode.add("; OUTPUT: D1 - INTEGER READ");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tD1\t; CLEAR D1");
        assemblyCode.add("\tMOVE.L\t#4,D0\t; READ_INT");
        assemblyCode.add("\tTRAP\t#15\t; READ_INT CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; Return from subroutine");
        // READ_STRING
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("READ_STRING");
        assemblyCode.add("; READS A STRING");
        assemblyCode.add("; INPUT: NONE");
        assemblyCode.add("; OUTPUT: A1 - STRING READ");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tA1\t; CLEAR A1");
        assemblyCode.add("\tMOVE.L\t#2,D0\t; READ_STRING");
        assemblyCode.add("\tTRAP\t#15\t; READ_STRING CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; Return from subroutine");
    }

    /**
     * Genera el código emsamblador para una instrucción. Las instrucciones son:
     * <ul>
     * <li>MULT</li>
     * <li>DIV</li>
     * <li>ADD</li>
     * <li>SUB</li>
     * <li>AND</li>
     * <li>OR</li>
     * <li>GOTO</li>
     * <li>SKIP</li>
     * <li>ASSIGN</li>
     * <li>PRINT_INT</li>
     * <li>PARAM</li>
     * <li>CALL</li>
     * <li>RETURN</li>
     * <li>EQUAL</li>
     * <li>NOT_EQUAL</li>
     * <li>LESS</li>
     * <li>LESS_EQUAL</li>
     * <li>GREATER</li>
     * <li>GREATER_EQUAL</li>
     * </ul>
     * 
     * @param instruction Instrucción a traducir.
     * @see Instruction
     */
    private void generateAssemblyCodeForInstruction(Instruction instruction) {
        // 68k assembly code for instruction
        switch (instruction.getOperation()) {
            case MULT:
                mult(instruction);
                break;
            case DIV:
                div(instruction);
                break;
            case ADD:
                add(instruction);
                break;
            case SUB:
                sub(instruction);
                break;
            case AND:
                and(instruction);
                break;
            case OR:
                or(instruction);
                break;
            case GOTO:
                assemblyCode.add("\tJMP\t" + instruction.getDest());
                break;
            case SKIP:
                assemblyCode.add("." + instruction.getDest() + ":");
                break;
            case ASSIGN:
                assign(instruction);
                break;
            case PARAM:
                params(instruction);
                break;
            case CALL:
                call(instruction);
                break;
            case RETURN:
                returnSubroutine(instruction);
                break;
            case PMB:
                pmb(instruction);
                break;
            case PRINT_INT:
                printInt(instruction);
                break;
            case EQUAL:
            case NOT_EQUAL:
            case LESS:
            case LESS_EQUAL:
            case GREATER:
            case GREATER_EQUAL:
                compare(instruction);
                break;
        }
    }

    private void add(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tADD.W\t" + ins.getOp2() + ",D0");
        assemblyCode.add("\tMOVE.W\tD0," + ins.getDest());
    }

    private void sub(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tSUB.W\t" + ins.getOp2() + ",D0");
        assemblyCode.add("\tMOVE.W\tD0," + ins.getDest());
    }

    private void mult(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tEXT.L\tD0");
        assemblyCode.add("\tMOVE.W\t" + ins.getOp2() + ",D1");
        assemblyCode.add("\tEXT.L\tD1");
        assemblyCode.add("\tMULS.W\tD1,D0");
        assemblyCode.add("\tMOVE.W\tD0," + ins.getDest());
    }

    private void div(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tEXT.L\tD0");
        assemblyCode.add("\tMOVE.W\t" + ins.getOp2() + ",D1");
        assemblyCode.add("\tEXT.L\tD1");
        assemblyCode.add("\tDIVS.W\tD1,D0");
        assemblyCode.add("\tMOVE.W\tD0," + ins.getDest());
    }

    private void and(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tAND.W\t" + ins.getOp2() + ",D0");
        assemblyCode.add("\tBMI\t" + ins.getDest());
    }

    private void or(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",D0");
        assemblyCode.add("\tOR.W\t" + ins.getOp2() + ",D0");
        assemblyCode.add("\tBMI\t" + ins.getDest());
    }

    private void assign(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + "," + ins.getDest());
    }

    private void params(Instruction ins) {
        assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",-(A7)");
    }

    private void call(Instruction ins) {
        assemblyCode.add("\tJSR\t" + ins.getDest());
        // Calcular el tamaño de los parámetros para la pila
        int size = 0;
        // ...
        assemblyCode.add("\tADDA.L\t#" + size + ",A7");
    }

    private void returnSubroutine(Instruction ins) {
        // Vaciar la pila dependiendo del tamaño de los parámetros
        // ...
        assemblyCode.add("\tRTS");
    }

    private void pmb(Instruction ins) {
        // assemblyCode.add("\tMOVE.W\t" + ins.getOp1() + ",-(A7)");
    }

    private void compare(Instruction ins) {
        Variable var1 = (Variable) ins.getOp1();
        Variable var2 = (Variable) ins.getOp2();

        assemblyCode.add("\tCMP.W\t" + var1.getName() + "," + var2.getName());

        switch (ins.getOperation()) {
            case EQUAL:
                assemblyCode.add("\tBEQ\t" + ins.getDest());
                break;
            case NOT_EQUAL:
                assemblyCode.add("\tBNE\t" + ins.getDest());
                break;
            case LESS:
                assemblyCode.add("\tBLT\t" + ins.getDest());
                break;
            case LESS_EQUAL:
                assemblyCode.add("\tBLE\t" + ins.getDest());
                break;
            case GREATER:
                assemblyCode.add("\tBGT\t" + ins.getDest());
                break;
            case GREATER_EQUAL:
                assemblyCode.add("\tBGE\t" + ins.getDest());
                break;
            default: // Nunca va llegar, pero es para evitar warnings
                break;
        }
    }

}