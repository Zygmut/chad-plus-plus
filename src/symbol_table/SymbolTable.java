package symbol_table;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Symbol> SymbolTable;
    private SymbolTable parent;

    public SymbolTable() {
        this.SymbolTable = new HashMap<>();
        this.parent = null;
    }

    public SymbolTable(SymbolTable parent) {
        this.SymbolTable = new HashMap<>();
        this.parent = parent;
    }

    public HashMap<String, Symbol> getSymbolTable() {
        return SymbolTable;
    }

    public void setSymbolTable(HashMap<String, Symbol> symbolTable) {
        SymbolTable = symbolTable;
    }

    public SymbolTable getParent() {
        return parent;
    }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    public void addSymbol(String name, Symbol type) {
        this.SymbolTable.put(name, type);
    }

    public void removeSymbol(String name) {
        this.SymbolTable.remove(name);
    }

    public Symbol getSymbol(String name) {
        return this.SymbolTable.get(name);
    }

    public boolean containsSymbol(String name) {
        return this.SymbolTable.containsKey(name);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "SymbolTable=" + SymbolTable +
                '}';
    }

}
