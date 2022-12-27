package core;

import intermediate_code.ThreeAddressCode;

public class Instr extends BaseNode {
    private String currentInstance;
    private IfNode ifNode;
    private WhileNode whileNode;
    private LoopNode loopNode;
    private ReturnNode returnNode;
    private Output output;
    private Input input;
    private Asignation asignation;
    private CallFn callFn;

    public Instr(IfNode ifNode, int line, int column) {
        super(line, column);
        this.currentInstance = "IfNode";
        this.ifNode = ifNode;
    }

    public Instr(WhileNode whileNode, int line, int column) {
        super(line, column);
        this.currentInstance = "WhileNode";
        this.whileNode = whileNode;
    }

    public Instr(LoopNode loopNode, int line, int column) {
        super(line, column);
        this.currentInstance = "LoopNode";
        this.loopNode = loopNode;
    }

    public Instr(ReturnNode returnNode, int line, int column) {
        super(line, column);
        this.currentInstance = "ReturnNode";
        this.returnNode = returnNode;
    }

    public Instr(Output output, int line, int column) {
        super(line, column);
        this.currentInstance = "Output";
        this.output = output;
    }

    public Instr(Input input, int line, int column) {
        super(line, column);
        this.currentInstance = "Input";
        this.input = input;
    }

    public Instr(Asignation asignation, int line, int column) {
        super(line, column);
        this.currentInstance = "Asignation";
        this.asignation = asignation;
    }

    public Instr(CallFn callFn, int line, int column) {
        super(line, column);
        this.currentInstance = "CallFn";
        this.callFn = callFn;
    }

    public String getCurrentInstance() {
        return this.currentInstance;
    }

    public IfNode getIfNode() {
        return this.ifNode;
    }

    public WhileNode getWhileNode() {
        return this.whileNode;
    }

    public LoopNode getLoopNode() {
        return this.loopNode;
    }

    public ReturnNode getReturnNode() {
        return this.returnNode;
    }

    public Output getOutput() {
        return this.output;
    }

    public Input getInput() {
        return this.input;
    }

    public Asignation getAsignation() {
        return this.asignation;
    }

    public CallFn getCallFn() {
        return this.callFn;
    }

    @Override
    public String toString() {
        switch (currentInstance) {
            case "IfNode":
                return "Instr [ifnode=" + ifNode + ", line=" + line + ", column=" + column + "]";
            case "WhileNode":
                return "Instr [whileNode=" + whileNode + ", line=" + line + ", column=" + column + "]";
            case "LoopNode":
                return "Instr [loopNode=" + loopNode + ", line=" + line + ", column=" + column + "]";
            case "ReturnNode":
                return "Instr [returnNode=" + returnNode + ", line=" + line + ", column=" + column + "]";
            case "Output":
                return "Instr [output=" + output + ", line=" + line + ", column=" + column + "]";
            case "Input":
                return "Instr [input=" + input + ", line=" + line + ", column=" + column + "]";
            case "Asignation":
                return "Instr [asignation=" + asignation + ", line=" + line + ", column=" + column + "]";
            case "CallFn":
                return "Instr [callFn=" + callFn + ", line=" + line + ", column=" + column + "]";
            default:
                return "Not valid";
        }
    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // TODO Auto-generated method stub

    }
}
