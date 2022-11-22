package semantic;

import symbol_table.SymbolTable;
import symbol_table.Symbol;
import symbol_table.Symbol.Type;
import symbol_table.Symbol.SubType;
import utils.Phase;
import errors.ErrorHandler;
import core.Instr;
import core.ReturnNode;
import errors.ErrorCode;

public class SemanticAnalyzer {

    private SymbolTable symbolTable;

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public boolean checkVariableDeclaration() {
        // !TODO: Implement this method
        return false;
    }

    public boolean checkFunctionDeclaration() {
        // !TODO: Implement this method
        return false;
    }

    public boolean checkExpression() {
        // !TODO: Implement this method
        return false;
    }

    public boolean checkAssignment() {
        // !TODO: Implement this method
        return false;
    }

    /**
     * Checks if the return type of a function is compatible with the return type.
     * 
     * @param id        - The name of the function.
     * @param subType   - The return type of the function.
     * @param returnExp - The return expression of the function.
     * @param line      - The line number of the return statement.
     * 
     * @return true if the return type is compatible, false otherwise
     */
    public boolean checkReturn(String id, SubType subType, ReturnNode returnExp, int line) {
        // Check if the return type of the function is the same as the return type of
        // the expression. If not, add an error to the error handler
        SubType functionSubType = this.symbolTable.getSymbol(id).getSubType();
        if (functionSubType != subType) {
            ErrorHandler.addError(ErrorCode.INCOMPATIBLE_TYPES, line, Phase.SEMANTIC);
            return false;
        }
        return true;
    }

    public boolean checkFunctionCall() {
        // !TODO: Implement this method
        return false;
    }

    public boolean checkInstruction() {
        // !TODO: Implement this method
        return false;
    }

    /**
     * This method checks if the ID is declared in the current scope.
     * 
     * @param id   - The ID to check
     * @param line - The line where the ID is declared
     * 
     * @return true if the ID is declared in the current scope, false otherwise.
     */
    public boolean checkIdentifier(String id, int line) {
        Symbol symbol = this.symbolTable.getSymbol(id);
        if (symbol == null) {
            ErrorHandler.addError(ErrorCode.UNDECLARED_IDENTIFIER, line, Phase.SEMANTIC);
            return false;
        }
        return true;
    }

}
