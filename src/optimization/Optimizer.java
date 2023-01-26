package optimization;

import errors.ErrorCode;
import errors.ErrorHandler;
import intermediate_code.*;
import utils.Phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Optimizer {

    private final ThreeAddressCode threeAddressCode;
    private boolean continueOptimizations = true;
    private boolean errors = false;

    public Optimizer(ThreeAddressCode threeAddressCode) {
        this.threeAddressCode = threeAddressCode;
    }

    public ThreeAddressCode getThreeAddressCodeOptimized() {
        return this.threeAddressCode;
    }

    public void optimizeThreeAddressCode() {
        // Delete unused code
        // deleteUnusedFunctionsAndVaribales();
        while (this.continueOptimizations && !errors) {
            this.continueOptimizations = false;

            // Asignaciones diferidas
            differdAssignations();

            // Reorganizar operaciones
            reorganizeOperations();

            // doble etiquetas
            // doubleLabels();

            // brancament
            // forkBranches();

            // Evaluar expresiones
            evaluateExpresions();

            // Eliminar variables no usadas
            deleteUnusedVariables();

            // Eliminar saltos redundantes
            // Eliminación de etiquetas no usadas
            // TODO: Revisar. Nunca hara nada si no cambiamos lo de los ifs (los ifs tienen
            // TODO: hardcodeado == true)
            // removeUnusedLabels();

            // Eliminar código inaccesible
            // TODO: Revisar
            removeInnaccesibleCode();
        }
        // Reducción por fuerza
        strengthReduction();
    }

    private void reorganizeOperations() {

    }

    /**
     * Elimina las etiquetas que no tengan gotos o calls asociados
     */
    private void removeUnusedLabels() {
        ArrayList<Instruction> c3d = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Instruction> instr_to_remove = new ArrayList<>();
        boolean found;

        for (Instruction instruction : c3d) {
            if (!instruction.getOperation().equals(Operator.SKIP)) {
                continue;
            }

            found = false;
            for (Instruction instruction2 : c3d) {
                if (instruction.getOperation().equals(Operator.GOTO)
                        && instruction.getDest().equals(instruction2.getDest().substring(4))) {
                    found = true;
                    break;
                }
                if (instruction.getOperation().equals(Operator.CALL)
                        && instruction.getDest().equals(instruction2.getOp1())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                continue;
            }

            instr_to_remove.add(instruction);
        }
        for (Instruction instruction : instr_to_remove) {
            System.out.println("Removed label" + instruction);
            c3d.remove(instruction);
        }
        this.threeAddressCode.setCodigo3Dir(c3d);
    }

    private void forkBranches() {

    }

    private void strengthReduction() {
        ArrayList<Instruction> c3d = this.threeAddressCode.getCodigo3Dir();
        Instruction instr;

        for (int i = 0; i < c3d.size(); ++i) {
            instr = c3d.get(i);
            switch (instr.getOperation().name()) {
                case "MULT" -> {
                    try {
                        // Comprobar si es un valor númerico
                        int val = Integer.parseInt(instr.getOp2());
                        int exp = Math.getExponent(val);
                        // Cal exp
                        // Comprobar si es un potencia de 2
                        if (val % 2 == 0) {
                            // Si es potencia de dos -> x << pot2
                            instr.setOperation(Operator.SHIFTL);
                            instr.setOp2(Integer.toString(exp));
                        }
                    } catch (Exception ignored) {
                        // No es un número, no hacer nada
                    }
                }
                case "DIV" -> {
                    try {
                        // Comprobar si es un valor númerico
                        int val = Integer.parseInt(instr.getOp2());
                        // Cal exp
                        int exp = Math.getExponent(val);
                        // Comprobar si es un potencia de 2
                        if (val % 2 == 0) {
                            // Si es potencia de dos -> x >> pot2
                            instr.setOperation(Operator.SHIFTR);
                            instr.setOp2(Integer.toString(exp));
                        }
                    } catch (Exception ignored) {
                        // No es un número, no hacer nada
                        if (Objects.equals(instr.getOp1(), instr.getOp2())) {
                            instr.setOperation(Operator.ASSIGN);
                            instr.setOp1("1");
                            instr.setOp2(null);
                        }
                    }
                }
                case "ADD" -> {
                    // Mirar si son iguales op1 y op2
                    // x = y + y -> x = y * 2 -> x = y << 1
                    // Si lo son iguales x = << 1
                    if (Objects.equals(instr.getOp1(), instr.getOp2())) {
                        instr.setOperation(Operator.SHIFTL);
                        instr.setOp2("1");
                    }
                }
                case "SUB" -> {
                    // Mirar si son iguales op1 y op2
                    // Si lo son iguales x = 0
                    if (Objects.equals(instr.getOp1(), instr.getOp2())) {
                        instr.setOp2(null);
                        instr.setOp1("0");
                        instr.setOperation(Operator.ASSIGN);
                    }
                }
                default -> {
                    // No hacer nada
                }
            }
        }
        this.threeAddressCode.setCodigo3Dir(c3d);
    }

    /**
     * Elimina todo codigo que no es accedido. Si hay un goto no condicional o un
     * return borrar instrucciones hasta encontrar una etiqueta
     */
    private void removeInnaccesibleCode() {
        ArrayList<Instruction> instr_to_remove = new ArrayList<>();
        ArrayList<Procedimiento> prods_to_remove = new ArrayList<>();
        ArrayList<Procedimiento> tp = this.threeAddressCode.getTp();
        ArrayList<Variable> tv = this.threeAddressCode.getTv();
        ArrayList<Integer> lines = new ArrayList<>();
        ArrayList<Instruction> c3d = this.threeAddressCode.getCodigo3Dir();
        Instruction instr = null;
        Instruction aux_instr = null;
        boolean found = false;
        for (int i = 0; i < c3d.size(); i++) {
            instr = c3d.get(i);
            switch (instr.getOperation()) {
                case GOTO:
                case RETURN:
                    for (int j = i + 1; j < c3d.size(); j++) {
                        aux_instr = c3d.get(j);
                        if (aux_instr.getOperation().equals(Operator.SKIP)) {
                            i = j;
                            break;
                        }

                        instr_to_remove.add(aux_instr);
                        lines.add(j + 1);
                    }
                    break;
                case PMB:
                    // Miramos si hay algun call para este pmb
                    // Iteramos por todo el codigo en busqueda de un call con el destino igual al
                    // pmb
                    found = false;
                    for (Instruction instruction : c3d) {
                        if (instruction.getOperation().equals(Operator.CALL)
                                && ("run_" + instruction.getOp1()).equals(instr.getDest())) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        break;
                    }

                    // Si no lo encontramos, eliminamos desde esta instrucción -1 hasta encontrar un
                    // skip que empieze por run

                    // Añadimos el procedimiento a la lista para eliminar
                    for (Procedimiento procedimiento : this.threeAddressCode.getTp()) {
                        if (procedimiento.getId().equals(instr.getDest().substring(4))) {
                            prods_to_remove.add(procedimiento);
                            break;
                        }
                    }

                    aux_instr = c3d.get(i - 1);
                    instr_to_remove.add(c3d.get(i - 1));
                    lines.add(i);
                    for (int j = i; j < c3d.size(); j++) {
                        aux_instr = c3d.get(j);
                        if (aux_instr.getOperation().equals(Operator.SKIP)
                                && aux_instr.getDest().contains("run_")
                                && aux_instr.getDest().subSequence(0, 4).equals("run_")) {
                            i = j;
                            break;
                        }

                        instr_to_remove.add(aux_instr);
                        lines.add(j + 1);
                    }

                    break;
                default:
                    break;
            }

        }

        Variable var;
        for (Instruction instruction : instr_to_remove) {
            var = this.threeAddressCode.findVarById(instruction.getDest());
            c3d.remove(instruction);

            if (var == null) {
                continue;
            }

            // si son temporales se eliminan
            if (var.isVolatile()) {
                tv.remove(var);
                continue;
            }

            // si no lo son, se tiene que mirar si son globales. Si eso es cierto, no se
            // borran
            if (var.getId().endsWith("0")) {
                continue;
            }

            tv.remove(var);
        }

        for (Procedimiento procedimiento : prods_to_remove) {
            tp.remove(procedimiento);
        }

        this.continueOptimizations = !instr_to_remove.isEmpty();
        this.threeAddressCode.setTp(tp);
        this.threeAddressCode.setTv(tv);
        this.threeAddressCode.setCodigo3Dir(c3d);

    }

    private void deleteUnusedVariables() {
        ArrayList<Variable> varsToRemove = new ArrayList<>();
        ArrayList<Instruction> cod3Dir = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Variable> tVars = this.threeAddressCode.getTv();
        ArrayList<Instruction> declarations = new ArrayList<>();
        HashMap<String, Integer> vars = new HashMap<>();
        int count = 0;
        for (Instruction inst : cod3Dir) {
            if (Objects.nonNull(inst.getOp1())) {
                if (this.threeAddressCode.findVarById(inst.getOp1()) != null) {
                    count = vars.containsKey(inst.getOp1()) ? vars.get(inst.getOp1()) + 1 : 1;
                    vars.put(inst.getOp1(), count);
                }
            }
            if (Objects.nonNull(inst.getOp2())) {
                if (this.threeAddressCode.findVarById(inst.getOp2()) != null) {
                    count = vars.containsKey(inst.getOp2()) ? vars.get(inst.getOp2()) + 1 : 1;
                    vars.put(inst.getOp2(), count);
                }
            }

            if (Objects.nonNull(inst.getDest())) {
                if (this.threeAddressCode.findVarById(inst.getDest()) != null) {
                    count = vars.containsKey(inst.getDest()) ? vars.get(inst.getDest()) + 1 : 1;
                    vars.put(inst.getDest(), count);
                    declarations.add(inst);
                }
            }
        }
        for (Variable var : this.threeAddressCode.getTv()) {
            if (!vars.containsKey(var.getId())) {
                varsToRemove.add(var);
            }

            if (vars.containsKey(var.getId()) && vars.get(var.getId()) < 2) {
                varsToRemove.add(var);
            }
        }

        for (Variable var : varsToRemove) {
            tVars.remove(var);
            for (Instruction instruction : declarations) {
                if (instruction.getDest().equals(var.getId())) {
                    cod3Dir.remove(instruction);
                }
            }
        }

        this.threeAddressCode.setTv(tVars);
    }

    /**
     * Evalua las expresiones que son conocidas en tiempo de compilación
     */
    private void evaluateExpresions() {
        for (Instruction instr : this.threeAddressCode.getCodigo3Dir()) {
            // Arithmetic operations
            try {
                int var1 = Integer.parseInt(instr.getOp1());
                int var2 = Integer.parseInt(instr.getOp2());
                String result = "";
                switch (instr.getOperation()) {
                    case MULT:
                        instr.setOp1(Integer.toString(var1 * var2));
                        break;
                    case DIV:
                        if (var2 == 0) {
                            // ERROR: division by zero. Check your expresions
                            ErrorHandler.addError(ErrorCode.DIVISION_BY_ZERO_IN_EXPRESIONS,
                                    -1,
                                    Phase.OPTIMIZATION);
                            errors = true;
                            return;
                        }

                        instr.setOp1(Integer.toString(var1 / var2));
                        break;
                    case ADD:
                        instr.setOp1(Integer.toString(var1 + var2));
                        break;
                    case SUB:
                        instr.setOp1(Integer.toString(var1 - var2));
                        break;
                    case EQUAL:
                        result = var1 == var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    case LESS:
                        result = var1 < var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    case GREATER:
                        result = var1 > var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    default:
                        continue;
                }
                instr.setOperation(Operator.ASSIGN);
                instr.setOp2(null);
                this.continueOptimizations = true;
                continue;
            } catch (Exception ignored) {
            }

            // Logical operations
            try {
                boolean var1 = false;
                if (instr.getOp1().equals("true")) {
                    var1 = true;
                } else if (instr.getOp1().equals("false")) {
                    var1 = false;
                } else {
                    throw new Exception();
                }
                boolean var2 = false;
                if (instr.getOp2().equals("true")) {
                    var2 = true;
                } else if (instr.getOp2().equals("false")) {
                    var2 = false;
                } else {
                    throw new Exception();
                }
                String result = "";
                switch (instr.getOperation()) {
                    case EQUAL:
                        result = var1 == var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    case AND:
                        result = var1 && var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    case OR:
                        result = var1 || var2 ? "true" : "false";
                        instr.setOp1(result);
                        break;
                    default:
                        continue;
                }
                instr.setOperation(Operator.ASSIGN);
                instr.setOp2(null);
                this.continueOptimizations = true;
                continue;
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Elimina las variables temporales que se utilizan solo una vez. Se hace la
     * asignacion directamente.
     */
    private void differdAssignations() {
        ArrayList<Instruction> codigo3Dir = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Variable> tv = this.threeAddressCode.getTv();
        ArrayList<Instruction> insAEliminar = new ArrayList<>();
        ArrayList<Variable> varsAEliminar = new ArrayList<>();

        // Iterar por todas las instrucciones
        // Si encontramos una asignación -> ASSING
        // Si el destino es una variable temporal
        // Buscamos a partir de esta instruccion hacia adelante por su uso
        // Una vez encontrado sustituimos el operado que sea igual a la variable
        // temporal por el elemento asignado
        Instruction actInstr;
        Instruction nextInstr;
        Operator op;
        for (int i = 0; i < codigo3Dir.size(); i++) {
            actInstr = codigo3Dir.get(i);
            op = actInstr.getOperation();
            if (op.equals(Operator.ASSIGN)
                    && this.threeAddressCode.findVarById(actInstr.getDest()).isVolatile()) {
                for (int j = i + 1; j < codigo3Dir.size(); j++) {
                    nextInstr = codigo3Dir.get(j);
                    if (nextInstr.getOp1() != null && nextInstr.getOp1().equals(actInstr.getDest())) {
                        // Añadimos para eliminar
                        insAEliminar.add(actInstr);
                        varsAEliminar.add(this.threeAddressCode.findVarById(actInstr.getDest()));

                        // Sustituir
                        nextInstr.setOp1(actInstr.getOp1());
                        continue;
                    }
                    if (nextInstr.getOp2() != null && nextInstr.getOp2().equals(actInstr.getDest())) {
                        // Añadimos para eliminar
                        insAEliminar.add(actInstr);
                        varsAEliminar.add(this.threeAddressCode.findVarById(actInstr.getDest()));

                        // Sustituir
                        nextInstr.setOp2(actInstr.getOp1());
                        continue;
                    }
                    if (nextInstr.getDest() != null && nextInstr.getDest().equals(actInstr.getDest())) {
                        // Añadimos para eliminar
                        insAEliminar.add(actInstr);
                        varsAEliminar.add(this.threeAddressCode.findVarById(actInstr.getDest()));

                        // Sustituir
                        nextInstr.setDest(actInstr.getOp1());
                        continue;
                    }
                }
            }

        }

        this.continueOptimizations = !insAEliminar.isEmpty() && !varsAEliminar.isEmpty();

        // Eliminar las instrucciones
        for (Instruction ins : insAEliminar) {
            codigo3Dir.remove(ins);
        }

        // Eliminar las variables
        for (Variable var : varsAEliminar) {
            tv.remove(var);
        }

        this.threeAddressCode.setTv(tv);
        this.threeAddressCode.setCodigo3Dir(codigo3Dir);
    }

    /**
     * Elimina del código de tres direcciones y de la tabla de procedimientos, las
     * funciones que no se usan durante el programa. Adicionalmente elimina las
     * variables de la tabla de variables que no se usen durante el programa.
     */
    private void deleteUnusedFunctionsAndVaribales() {
        ArrayList<Procedimiento> tp = this.threeAddressCode.getTp();
        ArrayList<Instruction> codigo3Dir = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Variable> tv = this.threeAddressCode.getTv();
        boolean isUsed;
        Procedimiento prod;
        ArrayList<Procedimiento> prodsAEliminar = new ArrayList<>();
        ArrayList<Variable> varsAEliminar = new ArrayList<>();
        for (Procedimiento value : tp) {
            isUsed = false;
            // prodsAEliminar.clear();
            prod = value;
            // Mirar si hay algún call en alguna instuccion
            for (Instruction ins : codigo3Dir) {
                if (!Objects.equals(ins.getDest(), "run_main")
                        && ins.getOperation().name().equals("CALL")
                        && ins.getOp1().equals(prod.getId())) {
                    isUsed = true;
                    break;
                }
            }
            if (!isUsed) {
                // Eliminar prod
                if (!prod.getId().equals("main")) {
                    prodsAEliminar.add(prod);
                }
                // Eliminar ins de dentro de la procedure
                Instruction ins;
                int inicio = 0, fin = 0;
                boolean entrar = false;
                boolean guardar = false;
                for (int j = 0; j < codigo3Dir.size(); j++) {
                    ins = codigo3Dir.get(j);
                    if (ins.getOperation().name().equals("SKIP") && ins.getDest().equals("run_" + prod.getId())) {
                        inicio = j;
                        entrar = true;
                        if (!ins.getDest().equals("run_main")) {
                            guardar = true;
                        }
                    }
                    if (guardar) {
                        // Por cada variable meterlo en varsAEliminar
                        switch (ins.getOperation().name()) {
                            case "ASSIGN":
                            case "INDEXED_VALUE":
                            case "INDEXED_ASSIGN":
                                varsAEliminar.add(threeAddressCode.findVarById(ins.getDest()));
                                break;
                            default:
                                break;
                        }
                    }
                    if (ins.getOperation().name().equals("RETURN") && ins.getDest() == null && entrar) {
                        fin = j;
                        break;
                    }
                }
                // System.out.printf("INICIO: %d FIN %d\n", inicio, fin);
                if (fin >= inicio) {
                    codigo3Dir.subList(inicio, fin).clear();
                }
            }
        }
        for (Variable var : varsAEliminar) {
            // checkear si la variable es global -> continue
            boolean found = false;
            for (Variable variable : threeAddressCode.getTvg()) {
                if (variable.equals(var)) {
                    found = true;
                    break;
                }
            }
            // Si no es global la elimianos
            if (!found) {
                tv.remove(var);
            }
        }
        for (Procedimiento procedimiento : prodsAEliminar) {
            tp.remove(procedimiento);
        }
        this.threeAddressCode.setTp(tp);
        this.threeAddressCode.setTv(tv);
    }

}
