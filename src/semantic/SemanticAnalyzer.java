package semantic;

import symbol_table.SymbolTable;
import symbol_table.Symbol;
import symbol_table.Symbol.Type;
import symbol_table.Symbol.SubType;
import utils.Phase;
import errors.ErrorHandler;

import java.util.ArrayList;

import core.Expresion;
import core.Instr;
import core.L_Args;
import core.ReturnNode;
import core.Value;
import errors.ErrorCode;

public class SemanticAnalyzer {

    private SymbolTable symbolTable;

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * Checks the use of a variable
     * 
     * @param id
     * @param line
     * 
     * @return true if the variable is valid, false otherwise
     */

    public boolean checkVariableDeclaration(String id, int line) {
        Symbol symbol = this.symbolTable.getSymbol(id);
        if (symbol == null) {
            // Variable no declarada
            ErrorHandler.addError(ErrorCode.UNDECLARED_VARIABLE, line, Phase.SEMANTIC);
            return false;
        }

        if (symbol.getType() != Type.VARIABLE) {
            // No es una variable
            ErrorHandler.addError(ErrorCode.NOT_A_VARIABLE, line, Phase.SEMANTIC);
            return false;
        }
        return true;
    }

    /**
     * Checks the use of a function
     * 
     * @param id
     * @param args
     * @param line
     * 
     * @return true if the function is valid, false otherwise
     */
    public boolean checkFunctionDeclaration(String id, L_Args args, int line) {
        Symbol symbol = this.symbolTable.getSymbol(id);
        if (symbol == null) {
            ErrorHandler.addError(ErrorCode.UNDECLARED_FUNCTION, line, Phase.SEMANTIC);
            return false;
        }

        if (symbol.getType() != Type.FUNCTION) {
            ErrorHandler.addError(ErrorCode.NOT_A_FUNCTION, line, Phase.SEMANTIC);
            return false;
        }

        ArrayList<Object> values = symbol.getValue();
        ArrayList<Expresion> argsList = new ArrayList<>();
        int argsSize = 0;

        while (args != null) {
            argsSize++;
            args = args.getNextArg();
            argsList.add(args.getArg());
        }

        if (values.size() != argsSize) {
            ErrorHandler.addError(ErrorCode.WRONG_NUMBER_OF_ARGUMENTS, line, Phase.SEMANTIC);
            return false;
        }

        ArrayList<Value> valuesList = new ArrayList<>();
        for (Expresion arg : argsList) {
            Expresion exp = arg;
            while (exp != null) {
                valuesList.add(exp.getValue());
                exp = exp.getNextExpresion();
            }
        }

        // !TODO: Implement this part
        // Hay que comprobar que los tipos de los argumentos de la llamada a la función
        // son los mismos que los de la función
        for (int i = 0; i < argsSize; i++) {
            if (values.get(i) != argsList.get(i)) {
                // ErrorHandler.addError(ErrorCode.WRONG_ARGUMENT_TYPE, line, Phase.SEMANTIC);
                return false;
            }
        }
        // Hasta aquí

        return true;
    }

    public boolean checkExpression() {

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

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

}
