package semantic;

import symbol_table.SymbolTable;
import errors.ErrorHandler;
import errors.ErrorCode;

public class SemanticAnalyzer {

    private SymbolTable symbolTable;

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public boolean checkVariableDeclaration() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkFunctionDeclaration() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkExpression() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkAssignment() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkReturn() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkFunctionCall() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkInstruction() {
        // !TODO: Implement this method
        return true;
    }

    public boolean checkIdentifier() {
        // !TODO: Implement this method
        return true;
    }

}
