package symbol_table;

import java.util.ArrayList;

import errors.ErrorCode;
import errors.ErrorHandler;
import utils.Phase;

public class Symbol {

    private final String name;
    private final Type type;
    private final SubType subType;
    private final ArrayList<Object> value;
    private int depth;
    private final boolean isConstant;
    private final boolean isInitialized;
    private int line;

    public enum Type {
        FUNCTION,
        VARIABLE,
        PARAMETER,
    }

    public enum SubType {
        INT,
        BOOL,
        TUP,
        VOID,
    }

    public Symbol(String name, Type type, SubType subType,
            int depth, boolean isConstant, boolean isInitialized, int line) {
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.value = new ArrayList<>();
        this.depth = depth;
        this.isConstant = isConstant;
        this.isInitialized = isInitialized;
        this.line = line;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public SubType getSubType() {
        return this.subType;
    }

    public ArrayList<Object> getValue() {
        return this.value;
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean getIsConstant() {
        return this.isConstant;
    }

    public boolean isIsConstant() {
        return this.isConstant;
    }

    public boolean getIsInitialized() {
        return this.isInitialized;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setValue(Object value) {
        this.value.add(value);
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return this.line;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", subType=" + subType +
                ", value=" + value +
                ", depth=" + depth +
                ", isConstant=" + isConstant +
                ", isInitialized=" + isInitialized +
                ", line=" + line +
                '}';
    }
}
