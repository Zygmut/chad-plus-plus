package assembly;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.TypeVar;
import intermediate_code.Instruction;
import intermediate_code.ThreeAddressCode;
import utils.Env;

/**
 * Assembly
 */
public class Assembly {

    private ThreeAddressCode threeAddressCode;
    private ArrayList<String> assemblyCode;

    public Assembly(ThreeAddressCode threeAddressCode) {
        this.threeAddressCode = threeAddressCode;
        this.assemblyCode = new ArrayList<>();
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
        assemblyCode.add("START");
    }

    private void generateAssemblyCodePostamble() {
        // 68k assembly code postamble
        assemblyCode.add("\t\tSIMHALT");
        assemblyCode.add("\t\tEND\tSTART");
        generateAssemblySubroutinesCode();
    }

    private void assemblyCode() {
        for (Instruction instruction : threeAddressCode.getCodigo3Dir()) {
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
     * <li>STRING_TO_BOOLEAN</li>
     * <li>BOOLEAN_TO_STRING</li>
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
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tMOVE.L\t#3,D0\t; PRINT_INT");
        assemblyCode.add("\tTRAP\t#15\t; PRINT_INT CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        // PRINT_STRING
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("PRINT_STRING");
        assemblyCode.add("; PRINTS A STRING");
        assemblyCode.add("; INPUT: A1 - STRING TO PRINT");
        assemblyCode.add("; OUTPUT: NONE");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tMOVE.L\t#14,D0\t; PRINT_STRING");
        assemblyCode.add("\tTRAP\t#15\t; PRINT_STRING CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        // READ_INT
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("READ_INT");
        assemblyCode.add("; READS AN INTEGER");
        assemblyCode.add("; INPUT: NONE");
        assemblyCode.add("; OUTPUT: D1 - INTEGER READ");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tCLR.L\tD1\t; CLEAR D1");
        assemblyCode.add("\tMOVE.L\t#4,D0\t; READ_INT");
        assemblyCode.add("\tTRAP\t#15\t; READ_INT CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        // READ_STRING
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("READ_STRING");
        assemblyCode.add("; READS A STRING");
        assemblyCode.add("; INPUT: NONE");
        assemblyCode.add("; OUTPUT: A1 - STRING READ");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tMOVE.L\tD0, A1\t; CLEAR A1");
        assemblyCode.add("\tMOVE.L\t#2,D0\t; READ_STRING");
        assemblyCode.add("\tTRAP\t#15\t; READ_STRING CALL TO OS");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        // STRING_TO_BOOLEAN
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("STRING_TO_BOOLEAN");
        assemblyCode.add("; CONVERTS A STRING TO A BOOLEAN");
        assemblyCode.add("; ASSERT: INPUT STRING IS:");
        assemblyCode.add("; TRUE/true OR FALSE/false");
        assemblyCode.add("; INPUT: A1 - STRING TO CONVERT");
        assemblyCode.add("; OUTPUT: D1 - BOOLEAN VALUE");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVEM.L\tD0/A1,-(A7)\t; SAVE D0/A1");
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tMOVE.B\t(A1),D0\t: FIRST CHARACTER");
        assemblyCode.add("\tCMP.W\t#'F',D0\t; IS FALSE?");
        assemblyCode.add("\tBEQ\t.STR_F\t; IS F");
        assemblyCode.add("\tCMP.W\t#'f',D0\t; IS FALSE?");
        assemblyCode.add("\tBEQ\t.STR_F\t; IS f");
        assemblyCode.add("\tMOVE.L\t#1,D1"); // true
        assemblyCode.add("\tJMP\t.STR_END\t; END");
        assemblyCode.add(".STR_F"); // false
        assemblyCode.add("\tMOVE.L\t#0,D1");
        assemblyCode.add(".STR_END");
        assemblyCode.add("\tMOVEM.L\t(A7)+,D0/D1\t; SAVE D0/A1");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        // BOOLEAN_TO_STRING
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("BOOLEAN_TO_STRING");
        assemblyCode.add("; CONVERTS A BOOLEAN TO A STRING");
        assemblyCode.add("; INPUT: D1 - BOOLEAN TO CONVERT");
        assemblyCode.add("; OUTPUT: A1 - STRING VALUE");
        assemblyCode.add("; -----------------------------------------------------------------------------");
        assemblyCode.add("\tMOVE.L\tD0,-(A7)\t; SAVE D0");
        assemblyCode.add("\tCLR.L\tD0\t; CLEAR D0");
        assemblyCode.add("\tMOVE.L\tD0,A1\t: CLEAR A1");
        assemblyCode.add("\tCMP\t#0,D1\t; CHECK IF D1 IS 0");
        assemblyCode.add("\tBEQ\t.ISFALSE\t; IF D1 IS 0, GO TO ISFALSE");
        assemblyCode.add("\tLEA\t.VALTRUE,A1\t; LOAD ADDRESS OF TRUE STRING");
        assemblyCode.add("\tJMP\t.END\t; JUMP TO END");
        assemblyCode.add(".ISFALSE");
        assemblyCode.add("\tLEA\t.VALFALSE,A1\t; LOAD ADDRESS OF FALSE STRING");
        assemblyCode.add(".END");
        assemblyCode.add("\tMOVE.L\t(A7)+,D0\t; RESTORE D0");
        assemblyCode.add("\tRTS\t; RETURN FROM SUBROUTINE");
        assemblyCode.add(".VALFALSE\tDC.B\t'false',0");
        assemblyCode.add(".VALTRUE\tDC.B\t'true',0");
        assemblyCode.add("\tDS.W\t0");
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
            case PRINT_BOL:
                printBol(instruction);
                break;
            case EQUAL:
                // case NOT_EQUAL:
            case LESS:
                // case LESS_EQUAL:
            case GREATER:
                // case GREATER_EQUAL:
                compare(instruction);
                break;
            case IF:
                ifInstruction(instruction);
                break;
            case INDEXED_ASSIGN:
                indexedAssign(instruction);
                break;
            case INDEXED_VALUE:
                indexedValue(instruction);
                break;
            case IN_BOL:
                input(instruction);
            case IN_INT:
                input(instruction);
                break;
            case OUT:
                out(instruction);
                break;
            case EXIT:
                // simhalt
                break;
        }
    }

    private void input(Instruction instruction) {
    }

    private void out(Instruction instruction) {
        // Dependiendo del tipo (INT o BOL) se llama a printInt o printBol
        // String A1 | Int D1
        System.out.println(instruction);
        if (this.threeAddressCode.putVar(instruction.getOp1(), null).getType().equals(TypeVar.INT)) {
            // assemblyCode.add("\tMOVE.L\t" + instruction.getOp1() + ",D1");
            assemblyCode.add("\tJMP\tPRINT_INT");
        } else {
            // assemblyCode.add("\tMOVE.L\t" + instruction.getOp1() + ",D1");
            assemblyCode.add("\tJMP\tBOOLEAN_TO_STRING");
            assemblyCode.add("\tJMP\tPRINT_STRING");
        }
    }

    private void indexedValue(Instruction instruction) {
    }

    private void indexedAssign(Instruction instruction) {
    }

    private void ifInstruction(Instruction instruction) {
    }

    private void printBol(Instruction instruction) {
    }

    private void printInt(Instruction instruction) {
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
        String var1 = ins.getOp1();
        String var2 = ins.getOp2();

        assemblyCode.add("\tCMP.W\t" + var1 + "," + var2);

        switch (ins.getOperation()) {
            case EQUAL:
                assemblyCode.add("\tBEQ\t" + ins.getDest());
                break;
            case LESS:
                assemblyCode.add("\tBLT\t" + ins.getDest());
                break;
            case GREATER:
                assemblyCode.add("\tBGT\t" + ins.getDest());
                break;
            default: // Nunca va llegar, pero es para evitar warnings
                break;
        }
    }

    public void saveAssemblyCode(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + Env.SLASH + fileName));
            for (String line : assemblyCode) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}