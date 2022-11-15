package core;

public class Instr {
    private String currentInstance;
    private IfNode ifNode;
    private WhileNode whileNode;
    private LoopNode loopNode;
    private ReturnNode returnNode;
    private Output output;
    private Input input;
    private Asignation asignation;
    private CallFn callFn;

    public Instr(IfNode ifNode) {
        this.currentInstance = "IfNode";
        this.ifNode = ifNode;
    }

    public Instr(WhileNode whileNode) {
        this.currentInstance = "WhileNode";
        this.whileNode = whileNode;
    }

    public Instr(LoopNode loopNode) {
        this.currentInstance = "LoopNode";
        this.loopNode = loopNode;
    }

    public Instr(ReturnNode returnNode) {
        this.currentInstance = "ReturnNode";
        this.returnNode = returnNode;
    }

    public Instr(Output output) {
        this.currentInstance = "Output";
        this.output = output;
    }

    public Instr(Input input) {
        this.currentInstance = "Input";
        this.input = input;
    }

    public Instr(Asignation asignation) {
        this.currentInstance = "Asignation";
        this.asignation = asignation;
    }

    public Instr(CallFn callFn) {
        this.currentInstance = "CallFn";
        this.callFn = callFn;
    }

    @Override
    public String toString() {
        switch (currentInstance) {
            case "IfNode":
                return "Instr [ifnode=" + ifNode + "]";
            case "WhileNode":
                return "Instr [whileNode=" + whileNode + "]";
            case "LoopNode":
                return "Instr [loopNode=" + loopNode + "]";
            case "ReturnNode":
                return "Instr [returnNode=" + returnNode + "]";
            case "Output":
                return "Instr [output=" + output + "]";
            case "Input":
                return "Instr [input=" + input + "]";
            case "Asignation":
                return "Instr [asignation=" + asignation + "]";
            case "CallFn":
                return "Instr [callFn=" + callFn + "]";
            default:
                return "Not valid";
        }
    }
}
