package symbol_table;

public class SymbolTable {
    private int currentLevel;
    // ts
    // ta
    // td

    /**
     * Remove all entries & free storage. Hard reset of variables
     */
    public void free() {
    }

    /**
     * Add a new Symbol to the table
     *
     * @param symbol
     */
    public void add(Symbol symbol) {
    }

    /**
     * Given a Symbol, return its description
     *
     * @param symbol
     * @return
     */
    public Symbol lookup(String id) {
        return null;
    }

    /**
     * Enter block. currentLevel++ & update ta
     */
    public void enter() {

    }

    /**
     * Exit block. Update td, te & currentLevel
     */
    public void exit() {

    }
}
