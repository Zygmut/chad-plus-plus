package intermediate_code;

/**
 * Instruction
 */
public class Instruction {
    /*
     * DEST = opr1 op opr2
     * ET: action
     * if opr1 op opr2 then action
     * pmb
     * return
     */
    private String dest;
    private String op1;
    private String op2;
    private Operator operation;

    // DEST = op1 OP op2;
    public Instruction(String dest, String op1, Operator operation, String op2) {
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
        this.operation = operation;
    }

    public String getDest() {
        return this.dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getOp1() {
        return this.op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return this.op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public Operator getOperation() {
        return this.operation;
    }

    public void setOperation(Operator operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        switch (operation.name()) {
            case "GOTO":
            case "PMB":
            case "RETURN":
                return operation.name() + " " + dest;
            case "SKIP":
                return dest + ": " + operation.name();
            case "EXIT":
                return operation.name();
            case "IN_INT":
            case "IN_BOL":
                return dest + " = " + operation.name();
            case "ASSIGN":
                return dest + " = " + op1;
            case "ADD":
                return dest + " = " + op1 + " + " + op2;
            case "SUB":
                return dest + " = " + op1 + " - " + op2;
            case "MULT":
                return dest + " = " + op1 + " * " + op2;
            case "DIV":
                return dest + " = " + op1 + " / " + op2;
            case "OR":
                return dest + " = " + op1 + " OR " + op2;
            case "AND":
                return dest + " = " + op1 + " AND " + op2;
            case "GREATER":
                return dest + " = " + op1 + " > " + op2;
            case "LESS":
                return dest + " = " + op1 + " < " + op2;
            case "EQUAL":
                return dest + " = " + op1 + " == " + op2;
            case "CALL":
                return dest + " = " + operation.name() + " " + op1;
            case "INDEXED_ASSIGN":
                return dest + "[" + op1 + "] = " + op2;
            case "INDEXED_VALUE":
                return dest + " = " + op1 + "[" + op2 + "]";
            case "IF":
                return "IF " + op1 + " == true GOTO " + dest;
            case "OUT":
                return "OUT " + op1;
            case "PARAM":
                return operation.name() + " " + op1;
            case "SHIFTR":
                return dest + " = " + op1 + " >> " + op2;
            case "SHIFTL":
                return dest + " = " + op1 + " << " + op2;
            default:
                return "{" +
                        " dest='" + this.dest + "'" +
                        ", op1='" + this.op1 + "'" +
                        ", op2='" + this.op2 + "'" +
                        ", operation='" + this.operation + "'" +
                        "}";
        }
    }
}