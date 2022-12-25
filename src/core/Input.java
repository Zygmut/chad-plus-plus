package core;

import java.util.ArrayList;

import intermediate_code.Instruction;

public class Input extends BaseNode {
    private int type; // 0 int, 1 bool

    public Input(int type, int line, int column) {
        super(line, column);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Input [type=" + type + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
