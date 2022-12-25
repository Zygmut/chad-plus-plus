package semantic;

import symbol_table.SymbolTable;
import symbol_table.Symbol;
import symbol_table.Symbol.Type;
import symbol_table.Symbol.SubType;
import utils.Phase;
import errors.ErrorHandler;

import java.util.ArrayList;

import core.*;
import errors.ErrorCode;

public class SemanticAnalyzer {

    private SymbolTable actualSymbolTable;
    private Chadpp chadpp;

    public SemanticAnalyzer(Chadpp chadpp) {
        this.chadpp = chadpp;
    }

    public void run() {

        // Checkeamos el main
        Main main = chadpp.getMain();
        this.actualSymbolTable = main.getSymbolTable();
        checkMain(main);

        // Checkeamos todas las funciones
        for (L_Fn l_fn = chadpp.getL_Fn(); l_fn != null; l_fn.getNextFn()) {
            checkFunction(l_fn.getFn());
        }
        /*
         * L_Fn l_fn = chadpp.getL_Fn();
         * while (l_fn.getNextFn() != null) {
         * checkFunction(l_fn.getFn());
         * l_fn = l_fn.getNextFn();
         * }
         * checkFunction(l_fn.getFn());
         */
    }

    public boolean checkMain(Main main) {

        // Checkeamos las declaraciones
        for (L_Decls l_Decls = main.getListaDecl(); l_Decls != null; l_Decls.nextDecl()) {
            exVariableDeclaration(l_Decls.getDecl());
        }

        /*
         * L_Decls l_Decls = main.getListaDecl();
         * while (l_Decls.nextDecl() != null) {
         * exVariableDeclaration(l_Decls.getDecl());
         * l_Decls = l_Decls.nextDecl();
         * }
         * exVariableDeclaration(l_Decls.getDecl());
         */

        // Checkeamos instrucciones
        for (L_Instrs l_Instrs = main.getListaInstr(); l_Instrs != null; l_Instrs.nextInstr()) {
            checkInstruction(l_Instrs.getInstr());
        }
        /*
         * L_Instrs l_Instrs = main.getListaInstr();
         * while (l_Instrs.nextInstr() != null) {
         * checkInstruction(l_Instrs.getInstr());
         * l_Instrs = l_Instrs.nextInstr();
         * }
         * checkInstruction(l_Instrs.getInstr());
         */

        return true;
    }

    public boolean checkFunction(Function function) {
        // Enlazamos la tabla de simbolos de la funcion a la de su padre y la ponemos
        // como la actual para que los nodos interiores puedan acceder a ella.
        function.getSymbolTable().setParent(actualSymbolTable);
        this.actualSymbolTable = function.getSymbolTable();

        // Comprobar declaraciones de la función
        for (L_Decls l_Decls = function.getDecls(); l_Decls != null; l_Decls = l_Decls.nextDecl()) {
            exVariableDeclaration(l_Decls.getDecl());
        }

        /*
         * L_Decls l_Decls = function.getDecls();
         * while (l_Decls.nextDecl() != null) {
         * exVariableDeclaration(l_Decls.getDecl());
         * l_Decls = l_Decls.nextDecl();
         * }
         * exVariableDeclaration(l_Decls.getDecl());
         */

        // Comprobar instrucciones de la función
        for (L_Instrs l_Instrs = function.getInstrs(); l_Instrs != null; l_Instrs.nextInstr()) {
            checkInstruction(l_Instrs.getInstr());
        }

        /*
         * L_Instrs l_Instrs = function.getInstrs();
         * while (l_Instrs.nextInstr() != null) {
         * checkInstruction(l_Instrs.getInstr());
         * l_Instrs = l_Instrs.nextInstr();
         * }
         * checkInstruction(l_Instrs.getInstr());
         */

        // Finalmente, cuando acabamos con los elementos de la funcion, dejamos la tabla
        // actual a la de su padre
        this.actualSymbolTable = this.actualSymbolTable.getParent();

        return true;
    }

    public boolean exVariableDeclaration(Decl decl) {
        boolean isConstant = decl.isConstant();
        TypeVar typevar = decl.getType();
        Asignation asignation = decl.getAsignation();

        // Solo puede ocurrir cuando estamos mirando las declaraciones de la funcion
        // main
        int depth = (this.actualSymbolTable.getParent() == null) ? 0 : 1;

        // Boolean isCorrect = checkExpresion(asignation.getExpresion(), typevar);
        if (!checkExpresion(asignation.getExpresion(), typevar)) {
            // Añadir error
            System.out.println("Error evaluando declaración: " + decl);
            return false;
        }

        /*
         * boolean isInitialized = true;
         * if (asignation.getExpresion().getValue() == null) {
         * isInitialized = false;
         * }
         */
        boolean isInitialized = asignation.getExpresion().getValue() != null;

        L_Ids l_ids = asignation.getL_Ids();
        String id;
        while (l_ids.nextId() != null) {
            // addSymbol con este ID
            id = l_ids.getId();
            if (this.actualSymbolTable.addSymbol(id, Type.VARIABLE, typevar, depth, isConstant, isInitialized, 0)) {
                // Error
            }
            l_ids = l_ids.nextId();

        }
        // addSymbol con el último ID
        id = l_ids.getId();
        if (this.actualSymbolTable.addSymbol(id, Type.VARIABLE, typevar, depth, isConstant, isInitialized, 0)) {
            // Error
        }

        return true;
    }

    public boolean checkExpresion(Expresion expresion, TypeVar typeVar) {
        Value value = expresion.getValue();
        // Expression: value Op Expresion
        // value
        switch (value.getCurrentInstance()) {
            case "Expresion":
                Expresion ex = value.getExpresion();
                if (!checkExpresion(ex, typeVar)) {
                    // System.out.println("Error, ");
                    return false;
                }
                break;
            case "Number":
                if (typeVar != TypeVar.INT) {
                    // System.out.println("Error, ");
                    ErrorHandler.addError(ErrorCode.INCOMPATIBLE_TYPES, 0, Phase.SEMANTIC);
                    return false;
                }
                break;
            case "Tuple":
                value.getTuple();
                // TODO: implement this
                return false;
            // break;
            case "Bol":
                if (typeVar != TypeVar.BOOL) {
                    // System.out.println("Error, ");
                    ErrorHandler.addError(ErrorCode.INCOMPATIBLE_TYPES, 0, Phase.SEMANTIC);
                    return false;
                }
                break;
            case "Id":
                String id = value.getId().getValue();
                if (!checkVariableDeclaration(id, 0, typeVar)) {
                    System.out.println("no declarada o no tipo variable");
                    return false;
                }
                break;
            case "CallFn":
                value.getCallFn();
                // TODO: implement this
                return false;
            // break;
            case "A_Tuple":
                value.getaTuple();
                // TODO: implement this
                return false;
            // break;
            case "Input":
                value.getInput();
                // TODO: implement this
                return false;
            // break;
            default:
                // ERROR DEL COMPILADOR
                return false;
        }

        Op op = expresion.getOp();
        if (op == null) {
            return true;
        }

        if (typeVar == TypeVar.BOOL) {
            if (op.ordinal() < 4) {
                // operación Incompatible (operaciones aritméticas cuando la variable es tipo
                // BOOL)
                return false;
            }
            // todo ok
        } else if (typeVar == TypeVar.INT) {
            if (op.ordinal() >= 4) {
                // operación Incompatible (operaciones lógicas cuando la variable es tipo INT)
                return false;
            }
            // todo ok
        } else { // typeVar == TypeVar.TUP
            // TODO: Implement this
        }

        return (checkExpresion(expresion.getNextExpresion(), typeVar));
    }

    public boolean checkInstruction(Instr instr) {
        // addSymbol
        return true;
    }

    /**
     * Checks the use of a variable
     * 
     * @param id
     * @param line
     * 
     * @return true if the variable is valid, false otherwise
     */

    public boolean checkVariableDeclaration(String id, int line, TypeVar typeVar) {
        Symbol symbol = this.actualSymbolTable.getSymbol(id);
        if (symbol == null) {
            SymbolTable parentSymbolTable = this.actualSymbolTable.getParent();

            if (parentSymbolTable != null) {
                symbol = parentSymbolTable.getSymbol(id);

                if (symbol == null) {
                    // Variable no declarada
                    ErrorHandler.addError(ErrorCode.UNDECLARED_VARIABLE, line, Phase.SEMANTIC);
                    return false;
                }
            }
        }

        if (symbol.getType() != Type.VARIABLE) {
            // No es una variable
            ErrorHandler.addError(ErrorCode.NOT_A_VARIABLE, line, Phase.SEMANTIC);
            return false;
        }

        if (symbol.getSubType().ordinal() != typeVar.ordinal()) {
            // No es una variable
            ErrorHandler.addError(ErrorCode.INCOMPATIBLE_TYPES, line, Phase.SEMANTIC);
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
        Symbol symbol = this.actualSymbolTable.getSymbol(id);
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
        SubType functionSubType = this.actualSymbolTable.getSymbol(id).getSubType();
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
        Symbol symbol = this.actualSymbolTable.getSymbol(id);
        if (symbol == null) {
            ErrorHandler.addError(ErrorCode.UNDECLARED_IDENTIFIER, line, Phase.SEMANTIC);
            return false;
        }
        return true;
    }

    public SymbolTable getSymbolTable() {
        return this.actualSymbolTable;
    }

    public String printSymbolTables() {
        String r = "Main:\n" + this.chadpp.getMain().getSymbolTable().printSymbolTable() + "\n";

        L_Fn l_fn = chadpp.getL_Fn();
        Function function;
        while (l_fn.getNextFn() != null) {
            function = l_fn.getFn();

            r += function.getId().getValue() + ":\n";
            r += function.getSymbolTable().printSymbolTable() + "\n";

            l_fn = l_fn.getNextFn();
        }
        function = l_fn.getFn();

        r += function.getId().getValue() + ":\n";
        r += function.getSymbolTable().printSymbolTable() + "\n";

        return r;

    }

}
