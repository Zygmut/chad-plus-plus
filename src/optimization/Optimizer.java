package optimization;

import intermediate_code.Instruction;
import intermediate_code.Procedimiento;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

import java.util.ArrayList;
import java.util.Objects;

public class Optimizer {

    private final ThreeAddressCode threeAddressCode;

    public Optimizer(ThreeAddressCode threeAddressCode) {
        this.threeAddressCode = threeAddressCode;
    }

    public ThreeAddressCode getThreeAddressCodeOptimized() {
        return this.threeAddressCode;
    }

    public void optimizeThreeAddressCode() {
        deleteUnusedFunctions();
        deleteUnusedVariables();
        optimizeVariableAssignations();
        assert false : "TODO: Implementar optimazeThreeAddressCode";
    }

    /**
     * Optimiza la asignación de variables extras e innecesarias. Si es posible
     * eliminar variables temporales se realizará.
     */
    private void optimizeVariableAssignations() {
        ArrayList<Instruction> codigo3Dir = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Variable> tv = this.threeAddressCode.getTv();

    }

    /**
     * Elimina del código de tres direcciones y la tabla de variables, las variables
     * no empleadas durante el programa.
     */
    private void deleteUnusedVariables() {
        ArrayList<Instruction> codigo3Dir = this.threeAddressCode.getCodigo3Dir();
        ArrayList<Variable> tv = this.threeAddressCode.getTv();
        // ArrayList<String> varADel

    }

    /**
     * Elimina del código de tres direcciones y de la tabla de procedimientos, las
     * funciones que no se usan durante el programa.
     */
    private void deleteUnusedFunctions() {
        ArrayList<Procedimiento> tp = this.threeAddressCode.getTp();
        ArrayList<Instruction> codigo3Dir = this.threeAddressCode.getCodigo3Dir();
        boolean isUsed;
        Procedimiento prod;
        ArrayList<Procedimiento> prodsAEliminar = new ArrayList<>();
        for (Procedimiento value : tp) {
            isUsed = false;
            prodsAEliminar.clear();
            prod = value;
            // Mirar si hay algún call en alguna instuccion
            for (Instruction ins : codigo3Dir) {
                // System.out.println(ins);
                if (!Objects.equals(ins.getDest(), "run_main")
                        && ins.getOperation().name().equals("CALL") && ins.getOp1().equals(prod.getId())) {
                    isUsed = true;
                    break;
                }
            }
            if (!isUsed) {
                // Eliminar prod
                prodsAEliminar.add(prod);
                // Eliminar ins de dentro de la procedure
                Instruction ins;
                int inicio = 0, fin = 0;
                boolean entrar = false;
                for (int j = 0; j < codigo3Dir.size(); j++) {
                    ins = codigo3Dir.get(j);
                    if (ins.getOperation().name().equals("SKIP") && ins.getDest().equals("run_" + prod.getId())) {
                        inicio = j;
                        entrar = true;
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
        for (Procedimiento procedimiento : prodsAEliminar) {
            tp.remove(procedimiento);
        }
        this.threeAddressCode.setTp(tp);
    }

}
