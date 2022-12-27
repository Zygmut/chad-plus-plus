package symbol_table;

import java.util.ArrayList;

import core.TypeVar;

public class Symbol {
    /*
     * main(){
     * int a = 0;
     * BEGIN
     * print(fn(a)); // 1
     * print(a) #print (0)
     * }
     *
     * int alpha fn(int b){
     * int a = a; // Error variable ya declarada
     * BEGIN
     * a = a + 1 // [a] = 1
     * return a; return 1
     * }
     */
    private final String name;
    private final Type type;
    private final SubType subType;
    private ArrayList<Symbol> value;
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

    public TypeVar subTypeToTypeVar(SubType subType) {
        switch (subType.name()) {
            case "INT":
                return TypeVar.INT;
            case "BOOL":
                return TypeVar.BOOL;
            case "TUP":
                return TypeVar.TUP;
            default:
                System.out.println("Error converting SubType to TypeVar");
                return null;
        }
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

    public ArrayList<Symbol> getValue() {
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

    public void setValue(Symbol value) {
        this.value.add(value);
    }

    public void setValue(ArrayList<Symbol> values) {
        this.value = values;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return this.line;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Symbol) obj).getName()) && this.depth == ((Symbol) obj).getDepth();
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
