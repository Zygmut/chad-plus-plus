package symbol_table;

import java.util.HashMap;
import java.util.ArrayList;

public class SymbolTable {

    private HashMap<String, Symbol> SymbolTable;
    private ArrayList<SymbolTable> children;

    public SymbolTable() {
        this.SymbolTable = new HashMap<>();
        this.children = null;
    }

    public SymbolTable(HashMap<String, Symbol> symbolTable, ArrayList<SymbolTable> children) {
        SymbolTable = symbolTable;
        this.children = children;
    }

    public HashMap<String, Symbol> getSymbolTable() {
        return SymbolTable;
    }

    public void setSymbolTable(HashMap<String, Symbol> symbolTable) {
        SymbolTable = symbolTable;
    }

    public ArrayList<SymbolTable> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SymbolTable> children) {
        this.children = children;
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
