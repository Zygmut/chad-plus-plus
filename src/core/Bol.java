package core;

import intermediate_code.Instruction;
import java.util.ArrayList;

public class Bol extends BaseNode {
    private boolean value;

    public Bol(String value, int line, int column) {
        super(line, column);
        this.value = Boolean.parseBoolean(value);
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Bol [value=" + value + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ArrayList<Instruction> code) {
        // TODO Auto-generated method stub

    }

}
