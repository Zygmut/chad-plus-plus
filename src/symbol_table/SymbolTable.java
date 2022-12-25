package symbol_table;

import java.util.ArrayList;
import java.util.HashMap;

import core.TypeVar;
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

    // Se emplea para el CUP
    public boolean addSymbol(String name, Type type, TypeVar subType,
            int depth, boolean isConstant, boolean isInitialized, int line) {
        return internalCheckAndAddSymbol(name, type, subType, depth, isConstant, isInitialized, line, null);
    }

    // Se emplea en el semantico
    public boolean addSymbol(String name, Type type, TypeVar subType,
            int depth, boolean isConstant, boolean isInitialized, int line, ArrayList<Object> values) {
        return internalCheckAndAddSymbol(name, type, subType, depth, isConstant, isInitialized, line, values);
    }

    private boolean internalCheckAndAddSymbol(String name, Type type, TypeVar subType,
            int depth, boolean isConstant, boolean isInitialized, int line, ArrayList<Object> values) {
        // !TODO: Implement this method
        // Get subType from TypeVar
        SubType subTypeSymbol = (subType == null) ? SubType.VOID : getSubType(subType);
        Symbol symbol = new Symbol(name, type, subTypeSymbol, depth, isConstant, isInitialized, line);
        symbol.setValue(null);
        this.SymbolTable.put(name, symbol);
        return false;
    }

    private SubType getSubType(TypeVar subType) {
        switch (subType) {
            case INT:
                return SubType.INT;
            case BOOL:
                return SubType.BOOL;
            case TUP:
                return SubType.TUP;
        }
        return SubType.VOID;
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

    public String printSymbolTable() {
        StringBuilder print = new StringBuilder();
        for (String key : this.SymbolTable.keySet()) {
            print.append("Key: " + this.SymbolTable.get(key) + "\n");
        }
        return print.toString();
    }

}
