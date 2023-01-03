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
        return "{" +
                " dest='" + this.dest + "'" +
                ", op1='" + this.op1 + "'" +
                ", op2='" + this.op2 + "'" +
                ", operation='" + this.operation + "'" +
                "}";
    }

}