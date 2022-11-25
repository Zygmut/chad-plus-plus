package symbol_table;

import java.util.HashMap;

import core.TypeFn;
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

    public boolean addSymbol(String name, Type type, TypeVar subType,
            int depth, boolean isConstant, boolean isInitialized, int line) {
        // !TODO: Implement this method

        // Get subType from TypeVar
        SubType subTypeSymbol = (subType == null) ? SubType.VOID : getSubType(subType);
        System.out.println("subTypeSymbol: " + subTypeSymbol);
        this.SymbolTable.put(name, new Symbol(name, type, subTypeSymbol, depth, isConstant, isInitialized, line));
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
        return null;
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
