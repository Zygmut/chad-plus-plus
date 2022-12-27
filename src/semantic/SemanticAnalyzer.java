package semantic;

import symbol_table.SymbolTable;
import symbol_table.Symbol;
import symbol_table.Symbol.Type;
import symbol_table.Symbol.SubType;
import utils.Phase;
import errors.ErrorHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

import core.*;
import errors.ErrorCode;

public class SemanticAnalyzer {

    private SymbolTable actualSymbolTable;
    private Function actualFunction;
    private Chadpp chadpp;
    // private ArrayList<Object> tv;
    // private ArrayList<Object> ta;

    public SemanticAnalyzer(Chadpp chadpp) {
        this.chadpp = chadpp;
    }

    public void run() {

        // Checkeamos el main
        Main main = chadpp.getMain();
        this.actualSymbolTable = main.getSymbolTable();
        checkMain(main);

        // Checkeamos todas las funciones
        for (L_Fn l_fn = chadpp.getL_Fn(); l_fn != null; l_fn = l_fn.getNextFn()) {
            checkFunction(l_fn.getFn());
        }
    }

    /**
     * @param main
     * @return boolean
     */
    public boolean checkMain(Main main) {

        // Checkeamos las declaraciones
        for (L_Decls l_Decls = main.getListaDecl(); l_Decls != null; l_Decls = l_Decls.nextDecl()) {
            exVariableDeclaration(l_Decls.getDecl());
        }

        // Checkeamos instrucciones
        for (L_Instrs l_Instrs = main.getListaInstr(); l_Instrs != null; l_Instrs = l_Instrs.nextInstr()) {
            checkInstruction(l_Instrs.getInstr());
        }

        return true;
    }

    /**
     * @param function
     * @return boolean
     */
    public boolean checkFunction(Function function) {
        // Enlazamos la tabla de simbolos de la funcion a la de su padre y la ponemos
        // como la actual para que los nodos interiores puedan acceder a ella.
        function.getSymbolTable().setParent(actualSymbolTable);
        this.actualSymbolTable = function.getSymbolTable();

        this.actualFunction = function;

        for (L_FArgs l_FArgs = function.getArguments(); l_FArgs != null, l_FArgs = l_FArgs.getNextArg()){
            //TODO: IMPLEMENT THIS
        }

        // Comprobar declaraciones de la función
        for (L_Decls l_Decls = function.getDecls(); l_Decls != null; l_Decls = l_Decls.nextDecl()) {
            exVariableDeclaration(l_Decls.getDecl());
        }

        // Comprobar instrucciones de la función
        for (L_Instrs l_Instrs = function.getInstrs(); l_Instrs != null; l_Instrs = l_Instrs.nextInstr()) {
            checkInstruction(l_Instrs.getInstr());
        }

        // Finalmente, cuando acabamos con los elementos de la funcion, dejamos la tabla
        // actual a la de su padre
        this.actualSymbolTable = this.actualSymbolTable.getParent();

        return true;
    }

    /**
     *
     * @param decl
     * @return boolean
     */
    public boolean exVariableDeclaration(Decl decl) {
        boolean isConstant = decl.isConstant();
        TypeVar typevar = decl.getType();
        Asignation asignation = decl.getAsignation();

        // Solo puede ocurrir cuando estamos mirando las declaraciones de la funcion
        // main
        int depth = (this.actualSymbolTable.getParent() == null) ? 0 : 1;

        if (!checkExpresion(asignation.getExpresion(), typevar)) {
            // TODO: Create the pertinent Error
            // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
            System.out.println("Error evaluando declaración: " + decl);
            return false;
        }

        boolean isInitialized = asignation.getExpresion().getValue() != null;

        for (L_Ids l_ids = asignation.getL_Ids(); l_ids != null; l_ids = l_ids.nextId()) {
            String id = l_ids.getId();

        }
        return true;
    }

    /**
     * @param expresion
     * @param typeVar
     * @return boolean
     */
    public boolean checkExpresion(Expresion expresion, TypeVar typeVar) {
        Value value = expresion.getValue();
        // Expression: value Op Expresion
        // value
        switch (value.getCurrentInstance()) {
            case "Expresion":
                Expresion ex = value.getExpresion();
                if (!checkExpresion(ex, typeVar)) {
                    // TODO: Create the pertinent Error
                    return false;
                }
                break;
            case "Number":
                if (typeVar != TypeVar.INT) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
                    return false;
                }
                break;
            // Unico caso => [1,2]
            // tup a = [1,2];
            // valor = [1,2]
            // op = null
            // nextExp = null

            case "Tuple":
                if (typeVar != TypeVar.TUP) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
                    // error int a = [1, 2]
                    return false;
                }
                if (expresion.getOp() != null) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
                    // error tup a = [1,2] '+' [3,4]
                }
                if (expresion.getNextExpresion() != null) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
                    // error tup a = [1,2] '+' [3,4] => No puede tener ninguna expresion a la
                    // derecha
                }
                value.getTuple();
                // TODO: implement this
                return false;
            // break;
            case "Bol":
                if (typeVar != TypeVar.BOOL) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
                    return false;
                }
                break;
            case "Id":
                String id = value.getId().getValue();
                if (!checkVariableDeclaration(id, 0, typeVar)) {
                    // TODO: Create the pertinent Error
                    // ErrorHandler.addError(ErrorCode.??, 0, Phase.SEMANTIC);
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

        // OP
        /*
         * 1. Descomponer la expresion --> (Token, valor) Token --> id y valor -->
         * Operando 2. Comprobar tipos (operación tiene sentido) --> (1+2) < (1<3) -->
         * Error de tipos 3. Orden operaciones --> A+B*C --> A + (B*C). Stack from =>
         * BC*A+
         */
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
        } else if (typeVar == TypeVar.INT) {
            if (op.ordinal() >= 4) {
                // operación Incompatible (operaciones lógicas cuando la variable es tipo INT)
                return false;
            }
        } else { // typeVar == TypeVar.TUP
            // TODO: Implement this
        }

        return (checkExpresion(expresion.getNextExpresion(), typeVar));
    }

    /**
     * @param instr
     * @return boolean
     */
    public boolean checkInstruction(Instr instr) {
        switch (instr.getCurrentInstance()) {
            case "IfNode":
                if (!checkExpresion(instr.getIfNode().getExpresion(), TypeVar.BOOL)) {
                    // error Expresión no valida (no es condicional)
                    return false;
                }
                break;
            case "WhileNode":
                if (!checkExpresion(instr.getWhileNode().getExpresion(), TypeVar.BOOL)) {
                    // error Expresión no valida (no es condicional)
                    return false;
                }
                break;
            case "LoopNode":
                if (!checkExpresion(instr.getLoopNode().getExpresion1(), TypeVar.INT)) {
                    // error Expresión no valida (no es condicional)
                    return false;
                }
                if (!checkExpresion(instr.getLoopNode().getExpresion2(), TypeVar.INT)) {
                    // error Expresión no valida (no es condicional)
                    return false;
                }
                break;
            case "ReturnNode":
                TypeVar returnType = this.actualFunction.getReturnType();
                if (!checkExpresion(instr.getReturnNode().getExpresion(), returnType)) {
                    // error Expresión no valida
                    // TypeVar del retorno diferente al TypeVar de la Función
                    return false;
                }
                break;
            case "Output":
                // TODO: Implementar esto
                break;
            case "Input":
                // TODO: IMPLEMENT THIS
                break;
            case "Asignation":
                Asignation asignation = instr.getAsignation();
                L_Ids l_Ids = asignation.getL_Ids();
                Symbol symbol = actualSymbolTable.lookup(l_Ids.getId());
                if (symbol == null) {
                    SymbolTable parent = this.actualSymbolTable.getParent();
                    if (parent == null) {
                        // TODO: Implement this error
                        // error, variable no declarada
                        return false;
                    }
                    symbol = parent.lookup(l_Ids.getId());
                    if (symbol == null) {
                        // TODO: Implement this error
                        // error, variable no declarada
                        return false;
                    }
                }
                if (symbol.getType() != Type.FUNCTION) {
                    // TODO: error, variable es una función
                    return false;
                }
                TypeVar targetType = symbol.subTypeToTypeVar(symbol.getSubType());

                for (l_Ids = l_Ids.nextId(); l_Ids == null; l_Ids = l_Ids.nextId()) {
                    Symbol symbol2 = actualSymbolTable.lookup(l_Ids.getId());

                    // CODIGO REPETIDO, SI SE AÑADE EL LOOK UP AL PADRE SERÍA UNA BUENA
                    // OPTIMIZACIÓN DE CÓDIGO
                    if (symbol2 == null) {
                        SymbolTable parent = this.actualSymbolTable.getParent();
                        if (parent == null) {
                            // error, variable no declarada
                            return false;
                        }
                        symbol2 = parent.lookup(l_Ids.getId());
                        if (symbol2 == null) {
                            // error, variable no declarada
                            return false;
                        }
                    }
                    // END OF LOOK UP

                    if (targetType != symbol2.subTypeToTypeVar(symbol2.getSubType())) {
                        // TODO: Implement this error
                        // error, id's de distinto tipo
                        return false;
                    }
                }
                // UNA VEZ COMPROBADO QUE TODOS LOS IDS SON DEL MISMO TIPO:
                if (checkExpresion(asignation.getExpresion(), targetType)) {
                    // TODO: Implement this error
                    // error, expresión.typevar != L_Ids type var
                    return false;
                }
                break;
            case "CallFn":
                // TODO: IMPLEMENT THIS
                break;
            default:
                // TODO: Cambiar formato del error
                System.out.println("ERROR DEL COMPILADOR (SEMANTICO), currentInstance no reconocida");
                System.out.println("Instr: " + instr);
                return false;
        }
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

        if (typeVar == null) {
            return true;
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

    /**
     * @return boolean
     */
    public boolean checkExpression() {

        return false;
    }

    /**
     * @return boolean
     */
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

    /**
     * @return boolean
     */
    public boolean checkFunctionCall() {

        return false;
    }

    /**
     * @return boolean
     */
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

    /**
     * @return SymbolTable
     */
    public SymbolTable getSymbolTable() {
        return this.actualSymbolTable;
    }

    /**
     * @return String
     */
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

    public String printVariableTables() {
        return "Work in progress";
    }

    public String printFunctionTables() {
        return "Work in progress";
    }

}
