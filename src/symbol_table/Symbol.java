package symbol_table;

import java.util.ArrayList;

public class Symbol {

    private final String name;
    private final StructureType structureType;
    private final StructureReturnType structureReturnType;
    private ArrayList<Symbol> content; // content (ArrayList<Symbol>) o next (?LinkedList<Symbol>)
    private final boolean isGlobal;
    private final boolean isConstant;
    private int line;

    public Symbol(String name, StructureType structureType, StructureReturnType structureReturnType,
            ArrayList<Symbol> content, boolean isGlobal, boolean isConstant, int line) {
        this.name = name;
        this.structureType = structureType;
        this.structureReturnType = structureReturnType;
        this.content = content;
        this.isGlobal = isGlobal;
        this.isConstant = isConstant;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public StructureReturnType getStructureReturnType() {
        return structureReturnType;
    }

    public ArrayList<Symbol> getContent() {
        return content;
    }

    public void setContent(ArrayList<Symbol> content) {
        this.content = content;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public boolean isConstant() {
        return isConstant;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Symbol) obj).getName());
    }

    @Override
    public String toString() {
        return "Symbol [name=" + name + ", structureType=" + structureType + ", structureReturnType="
                + structureReturnType + ", content=" + content + ", isGlobal=" + isGlobal + ", isConstant=" + isConstant
                + ", line=" + line + "]";
    }
}
