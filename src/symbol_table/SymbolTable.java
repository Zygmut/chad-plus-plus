package symbol_table;

import java.util.HashMap;

import symbol_table.Symbol.SubType;
import symbol_table.Symbol.Type;

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

    public boolean addSymbol(String name, Type type, SubType subType,
            int depth, boolean isConstant, boolean isInitialized, int line) {
        // !TODO: Implement this method
        return false;
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
