package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

/*
*   loop(1,5)
*   for i = 1 to 5 do ++
*   loop(5,1)
*   for i = 5 downto 1 do --
*   loop(1,1)
*   for i = 1 to 1 do ++
*/
public class LoopNode extends BaseNode {
    private Expresion expresion1;
    private Expresion expresion2;
    private L_Instrs instrs;

    public LoopNode(Expresion expresion1, Expresion expresion2, L_Instrs instrs, int line, int column) {
        super(line, column);
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
        this.instrs = instrs;
    }

    public Expresion getExpresion1() {
        return expresion1;
    }

    public void setExpresion1(Expresion expresion1) {
        this.expresion1 = expresion1;
    }

    public Expresion getExpresion2() {
        return expresion2;
    }

    public void setExpresion2(Expresion expresion2) {
        this.expresion2 = expresion2;
    }

    public L_Instrs getInstrs() {
        return instrs;
    }

    public void setInstrs(L_Instrs instrs) {
        this.instrs = instrs;
    }

    @Override
    public String toString() {
        return "LoopNode [expresion1=" + expresion1 + ", expresion2=" + expresion2 + ", instrs=" + instrs + " line="
                + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // Init -> iter = Inicio = 1, fin = 5
        // iter == fin goto exitloop
        // codigo
        // if iter < fin, iter ++
        // if iter > fin, iter --
        // exitloop

        // Iniciamos la variable de iteracion
        Variable variter = codigoTresDir.putVar(null, TypeVar.INT);
        expresion1.generate3dc(codigoTresDir);
        codigoTresDir.addInstr(
                new Instruction(variter.getId(), codigoTresDir.getLastVariable().getId(), Operator.ASSIGN, null));

        // Iniciamos la variable de final
        Variable vartarget = codigoTresDir.putVar(null, TypeVar.INT);
        expresion2.generate3dc(codigoTresDir);
        codigoTresDir.addInstr(
                new Instruction(vartarget.getId(), codigoTresDir.getLastVariable().getId(), Operator.ASSIGN, null));

        String initLabel = codigoTresDir.newLabel(); // Inicio del loop
        String exitLabel = codigoTresDir.newLabel(); // Fin del loop
        String lessLabel = codigoTresDir.newLabel(); // Si iter < fin, iter ++
        String greaterLabel = codigoTresDir.newLabel(); // Si iter > fin, iter --

        // Inicio del loop
        codigoTresDir.addInstr(new Instruction(initLabel, null, Operator.SKIP, null));

        // Mirar si la primera expresión es igual a la segunda
        Variable varEqual = codigoTresDir.putVar(null, TypeVar.BOOL);
        codigoTresDir.addInstr(new Instruction(varEqual.getId(), variter.getId(), Operator.EQUAL, vartarget.getId()));
        codigoTresDir.addInstr(new Instruction(exitLabel, varEqual.getId(), Operator.IF, null));

        // Ejecutamos el codigo pertinente
        if (this.instrs != null) {
            this.instrs.generate3dc(codigoTresDir);
        }

        // Si la primera expresión es menor a la segunda, entonces iteramos ++
        Variable varLess = codigoTresDir.putVar(null, TypeVar.BOOL);
        codigoTresDir.addInstr(new Instruction(varLess.getId(), variter.getId(), Operator.LESS, vartarget.getId()));
        codigoTresDir.addInstr(new Instruction(lessLabel, varLess.getId(), Operator.IF, null));
        codigoTresDir.addInstr(new Instruction(greaterLabel, null, Operator.GOTO, null));

        // ++
        codigoTresDir.addInstr(new Instruction(lessLabel, null, Operator.SKIP, null));
        Variable varPlus = codigoTresDir.putVar(null, TypeVar.INT);
        codigoTresDir.addInstr(new Instruction(varPlus.getId(), variter.getId(), Operator.ADD, "1"));
        codigoTresDir.addInstr(new Instruction(variter.getId(), varPlus.getId(), Operator.ASSIGN, null));
        codigoTresDir.addInstr(new Instruction(initLabel, null, Operator.GOTO, null));

        // --
        codigoTresDir.addInstr(new Instruction(greaterLabel, null, Operator.SKIP, null));
        Variable varSub = codigoTresDir.putVar(null, TypeVar.INT);
        codigoTresDir.addInstr(new Instruction(varSub.getId(), variter.getId(), Operator.SUB, "1"));
        codigoTresDir.addInstr(new Instruction(variter.getId(), varSub.getId(), Operator.ASSIGN, null));
        codigoTresDir.addInstr(new Instruction(initLabel, null, Operator.GOTO, null));

        // Fin del loop
        codigoTresDir.addInstr(new Instruction(exitLabel, null, Operator.SKIP, null));

    }

}
