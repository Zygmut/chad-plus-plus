package optimization;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.Procedimiento;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

import java.util.ArrayList;
import java.util.Objects;

public class Optimizer {

    private final ThreeAddressCode threeAddressCode;
    private boolean continueOptimizations = true;

    public Optimizer(ThreeAddressCode threeAddressCode) {
        this.threeAddressCode = threeAddressCode;
    }

    public ThreeAddressCode getThreeAddressCodeOptimized() {
        return this.threeAddressCode;
    }

    public void optimizeThreeAddressCode() {
        deleteUnusedFunctionsAndVaribales();
        while (this.continueOptimizations) {
            optimizeDifferdAssignations();
            // doble etiquetas
            // brancament
            // Evaluancion de expresiones
        }
    }

    /**
     * Elimina las variables temporales que se utilizan solo una vez. Se hace la
     * asignacion directamente.
     */
    private void optimizeDifferdAssignations() {
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
                    codigo3Dir.subList(inicio, fin + 1).clear();
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
