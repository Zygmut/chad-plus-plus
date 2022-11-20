package symbol_table;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Symbol> SymbolTable;

    public SymbolTable() {
        this.SymbolTable = new HashMap<String, Symbol>();
    }

    public void addSymbol(String name, Symbol type) {
        this.SymbolTable.put(name, type);
    }

    public void addSymbol(String name, String type, String value, String scope) {
        this.SymbolTable.put(name, new Symbol(name, type, value, scope));
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
