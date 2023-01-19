package semantic;

import core.*;
import errors.ErrorCode;
import errors.ErrorHandler;
import symbol_table.StructureReturnType;
import symbol_table.StructureType;
import symbol_table.Symbol;
import symbol_table.SymbolTable;
import utils.Phase;
import warnings.WarningCode;
import warnings.WarningHandler;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Analizador semántico. Verifica que el código sea correcto semánticamente, y
 * en caso contrario lanza un error.
 *
 * @see SymbolTable
 * @see ErrorHandler
 * @see ErrorCode
 * @see core
 */
public class SemanticAnalyzer {

    /**
     * Tabla de simbolos
     */
    private final SymbolTable st;

    /**
     * Constructor, recibe la tabla de simbolos por parámetro
     *
     * @param st Tabla de simbolos
     */
    public SemanticAnalyzer(SymbolTable st) {
        this.st = st;
    }

    /**
     * Verfica si la función llamada es correcta. En caso de que no sea correcta
     * devuelve false y en caso contrario devuelve true.
     *
     * @param callFn Nodo CallFn
     * @return boolean
     */
    public boolean checkFunction(CallFn callFn) {
        if (st.getFunction(callFn.getId().getValue()) == null) {
            // ERROR -> function not defined
            ErrorHandler.addError(ErrorCode.UNDECLARED_FUNCTION,
                    callFn.getLine(),
                    callFn.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        return true;
    }

    /**
     * Verifica que la llamada a una función sea correcta.
     *
     * @param callFn Nodo CallFn
     * @return boolean
     * @see CallFn
     */
    public boolean checkCallFArgs(CallFn callFn) {
        // Mirar si la funcion esta definida
        if (!checkFunction(callFn)) {
            return false;
        }

        // Sacar los parametros de la funcion
        Stack<Symbol> fnParams = st.getParameters(callFn.getId().getValue());

        // Cogemos los argumentos de la llamada
        L_Args l_args = callFn.getArgs();

        // Si no le hemos pasado argumentos pero si tiene => ERROR
        if (l_args == null && !fnParams.empty()) {
            // ERROR LA funcion definida no tiene argumentos y le hemos pasado argumentos
            ErrorHandler.addError(ErrorCode.FUNCTION_ARGUMENTS_PASSED_NO_ARGUMENTS,
                    callFn.getLine(),
                    callFn.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }

        // Si le hemos pasado argumentos pero no tiene => ERROR
        if (l_args != null && fnParams.empty()) {
            // ERROR LA funcion definida no tiene argumentos y le hemos pasado argumentos
            ErrorHandler.addError(ErrorCode.FUNCTION_NO_ARGUMENTS_ARGUMENTS_PASSED,
                    callFn.getLine(),
                    callFn.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }

        // Pasamos todos los argumentos a un arraylist
        ArrayList<StructureReturnType> args = new ArrayList<>();

        boolean hasError = false;
        StructureReturnType type = null;
        for (L_Args arg = l_args; arg != null; arg = arg.getNextArg()) {
            type = checkExpresion(arg.getArg());
            if (type == null) {
                // Error -> malformed expression
                ErrorHandler.addError(ErrorCode.MALFORMED_EXPRESSION,
                        arg.getArg().getLine(),
                        arg.getArg().getColumn(),
                        Phase.SEMANTIC);
                hasError = true;
            } else {
                args.add(type);
            }
        }
        // Si el tamaño de los argumentos y los params no coincide => ERROR
        if (args.size() != fnParams.size()) {
            ErrorHandler.addError(ErrorCode.INVALID_NUMBER_OF_ARGUMENTS,
                    l_args.getLine(),
                    l_args.getColumn(),
                    Phase.SEMANTIC);
            hasError = true;
        }

        // Iteramos por los parametros y argumentos
        while (!fnParams.empty()) {
            Symbol param = fnParams.pop();
            StructureReturnType arg = args.remove(0);
            if (param.getStructureReturnType() != arg) {
                // ERROR INCOMPATIBLE TYPES
                ErrorHandler.addError(
                        "Incompatible types, expected " + param.getStructureReturnType().name() + " but got " + arg,
                        callFn.getLine(),
                        -1,
                        Phase.SEMANTIC);
                hasError = true;
            }
        }

        return !hasError;
    }

    /**
     * Verifica que la expresión sea correcta, a partir de una expresión. Y devuelve
     * el tipo de la expresión si es correcta y en caso contrario devuelve null.
     *
     * @param exp Expresión
     * @return StructureReturnType | null
     * @see Expresion
     */
    public StructureReturnType checkExpresion(Expresion exp) {
        Value value = exp.getValue();
        StructureReturnType type;
        switch (value.getCurrentInstance()) {
            case "Number":
                type = StructureReturnType.INT;
                break;
            case "Bol":
                type = StructureReturnType.BOOL;
                break;
            case "Id":
                // Revisar que existe el id
                Symbol symbol = st.getSymbol(value.getId().getValue());
                if (symbol == null) {
                    ErrorHandler.addError(ErrorCode.UNDECLARED_VARIABLE,
                            exp.getLine(),
                            exp.getColumn(),
                            Phase.SEMANTIC);
                    return null;
                }
                type = symbol.getStructureReturnType();
                break;
            case "CallFn":
                // Revisar que existe la funcion
                Symbol symbolCallFn = st.getFunction(value.getCallFn().getId().getValue());
                if (symbolCallFn == null) {
                    ErrorHandler.addError(ErrorCode.UNDECLARED_FUNCTION,
                            exp.getLine(),
                            exp.getColumn(),
                            Phase.SEMANTIC);
                    return null;
                }
                type = symbolCallFn.getStructureReturnType();
                break;
            case "Input":
                type = StructureReturnType.INT;
                if (value.getInput().getType() == 1) {
                    type = StructureReturnType.BOOL;
                }
                break;
            case "A_Tuple":
                if (st.getSymbol(value.getaTuple().getId().getValue()).getStructureType()
                        .equals(StructureType.PARAMETER)) {
                    // WARNING: parameter not known at compile time, results may differ
                    WarningHandler.addWarning(WarningCode.VARIABLE_CONTENT_NOT_KNOWN_AT_COMPILE_TIME,
                            exp.getLine(),
                            exp.getColumn(),
                            Phase.SEMANTIC);
                    type = StructureReturnType.INT_OR_BOOL;
                    break;
                }
                Symbol symbolNTupleArg = st.getNTupleArgument(value.getaTuple().getId().getValue(),
                        value.getaTuple().getAccess().getValue());
                if (symbolNTupleArg == null) {
                    ErrorHandler.addError(ErrorCode.TUPLE_INDEX_OUT_OF_BOUNDS,
                            exp.getLine(),
                            exp.getColumn(),
                            Phase.SEMANTIC);
                    return null;
                }
                type = symbolNTupleArg.getStructureReturnType();
                break;
            case "Tuple":
                type = StructureReturnType.TUP;
                break;
            case "Expresion":
                type = checkExpresion(value.getExpresion());
                if (type == null) {
                    // The error will be called in .cup or asignation
                    return null;
                }
                break;
            default:
                // ERROR unsuported element
                ErrorHandler.addError(ErrorCode.UNSUPPORTED_ELEMENT,
                        exp.getLine(),
                        exp.getColumn(),
                        Phase.SEMANTIC);
                type = null;
        }

        Op op = exp.getOp();
        if (op == null) {
            return type;
        }

        switch (type.name()) {
            case "BOOL":
                switch (op.name()) {
                    case "PLUS":
                    case "MINUS":
                    case "DIV":
                    case "MULT":
                        return null;
                    default:
                        break;
                }
                break;
            case "INT":
                switch (op.name()) {
                    case "AND":
                    case "OR":
                        return null;
                    default:
                        break;
                }
                break;
            case "TUP":
                if (op != null) {
                    return null;
                }
                break;
            default:
                return null;

        }
        StructureReturnType returnNextExpresion = checkExpresion(exp.getNextExpresion());
        if (type.equals(StructureReturnType.INT_OR_BOOL)) {
            type = returnNextExpresion;
        } else {
            if (returnNextExpresion.equals(StructureReturnType.INT_OR_BOOL)) {
                returnNextExpresion = type;
            } else {
                if (returnNextExpresion != type) {
                    return null;
                }
            }
        }

        if (type.equals(StructureReturnType.INT) && returnNextExpresion.equals(StructureReturnType.INT)) {
            switch (op.name()) {
                case "REQUAL":
                case "LT":
                case "GT":
                    return StructureReturnType.BOOL;
                default:
                    break;
            }
        }

        // Division by zero
        if (op.name().equals("DIV") && exp.getNextExpresion().getValue().getCurrentInstance().equals("Number")
                && exp.getNextExpresion().getValue().getNumber().getValue() == 0) {
            WarningHandler.addWarning(WarningCode.DIVISION_BY_ZERO,
                    exp.getLine(),
                    exp.getColumn(),
                    Phase.SEMANTIC);

        }
        return type;
    }

    /**
     * Wrapper de checkExpresion para generar los errores pertinentes. Devuelve true
     * si la expresion esta bien formada y false en caso contrario
     *
     * @param e
     * @returns false | true
     */
    public boolean checkExp(Expresion e) {
        if (checkExpresion(e) == null) {
            ErrorHandler.addError(
                    ErrorCode.MALFORMED_EXPRESSION,
                    e.getLine(),
                    e.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        return true;
    }

    /**
     * Verifica el Id de una expresión. Y devuelve el tipo de la expresión si
     * es correcta y en caso contrario devuelve null.
     *
     * @param exp idExp
     * @return StructureReturnType | null
     * @see Id
     * @see StructureReturnType
     */
    public StructureReturnType checkId(Id exp) {
        Symbol symbol = st.getSymbol(exp.getValue());
        if (symbol == null) {
            ErrorHandler.addError(ErrorCode.UNDECLARED_VARIABLE,
                    exp.getLine(),
                    exp.getColumn(),
                    Phase.SEMANTIC);
            // Error semantico, no existe el id
            return null;
        }
        return symbol.getStructureReturnType();
    }

    /**
     * Verifica que la asignación o declaración sea correcta. Y devuelve el tipo de
     * la expresión si es correcta y en caso contrario devuelve null.
     *
     * @param assig         Asignación
     * @param inDeclaration Boolean
     * @return StructureReturnType | null
     * @see Asignation
     * @see StructureReturnType
     */
    public StructureReturnType checkAsignation(Asignation assig, boolean InDeclaration) {
        // No se puede asignar valores a funciones o valores constantes
        // Cogemos todos los ids y vamos revisando que ninguno sea funcion o constanste
        ArrayList<String> lids = new ArrayList<>();
        ArrayList<Integer> lines = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        for (L_Ids id = assig.getL_Ids(); id != null; id = id.nextId()) {
            lids.add(id.getId());
            lines.add(id.getLine());
            cols.add(id.getColumn());
        }

        ArrayList<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < lids.size(); i++) {
            symbols.add(st.getSymbol(lids.get(i)));
            if (symbols.get(i) == null) {
                // ERROR undefined variable
                ErrorHandler.addError(ErrorCode.UNDECLARED_VARIABLE,
                        lines.get(i),
                        cols.get(i),
                        Phase.SEMANTIC);
                return null;
            }
            if (symbols.get(i).isConstant() && !InDeclaration) {
                // ERROR cannot modify values of constants variables
                ErrorHandler.addError(ErrorCode.CANNOT_MODIFY_CONSTANT_VARIABLES,
                        lines.get(i),
                        cols.get(i),
                        Phase.SEMANTIC);
                return null;
            }
        }

        // Evaluamos la asignación y revisamos que los tipos de lids sean del mismo tipo
        // de la expresión
        StructureReturnType expEval = this.checkExpresion(assig.getExpresion());
        for (int i = 0; i < symbols.size(); i++) {
            if (!symbols.get(i).getStructureReturnType().equals(expEval)) {
                if (expEval == StructureReturnType.TUP) {
                    // ERROR tuple asignation to non tuple structure
                    ErrorHandler.addError(ErrorCode.TUPLE_ASSIGNATION_TO_NON_TUPLE,
                            lines.get(i),
                            cols.get(i),
                            Phase.SEMANTIC);
                    return null;
                }

                // ERROR Expresion type does not match variable type
                ErrorHandler.addError(ErrorCode.ASIGNATION_TYPE_DOES_NOT_MATCH,
                        lines.get(i),
                        cols.get(i),
                        Phase.SEMANTIC);
                return null;
            }
        }
        return expEval;
    }

    /**
     * Verifica el tamaño de la tupla. Comprueba que el tamaño de la tupla sea el
     * mismo que el de la declaración inicial. Y devuelve el tipo de la expresión si
     * es correcta y en caso contrario devuelve null.
     *
     * @param ass Assignation
     * @return boolean true si el tamaño es correcto, false en caso contrario
     * @see Tuple
     */
    public boolean checkTupleSize(Asignation ass) {
        L_Args l_args = ass.getExpresion().getValue().getTuple().getTupleContent();
        int size = 0;

        while (l_args.getArg() != null) {
            size++;
            l_args = l_args.getNextArg();
        }

        Symbol symbol = st.getSymbol(ass.getL_Ids().getId());
        if (symbol == null) {
            return false;
        }

        return (symbol.getContent().size() != size);

    }

    /**
     * Checkea la expresion pasada para el retorno de una funcion. Devuelve el
     * StructureReturnType o null en caso que falle
     *
     * @param e
     * @returns StructureReturnType | null
     */
    public StructureReturnType checkReturnValue(Expresion e) {
        if (e.getValue().getCurrentInstance().equals("Tuple") && e.getOp() == null && e.getNextExpresion() == null) {
            // ERROR: cannot return tuple primitive, create a variable
            ErrorHandler.addError(ErrorCode.CANNOT_RETURN_TUPLE_PRIMITIVE,
                    e.getLine(),
                    e.getColumn(),
                    Phase.SEMANTIC);
            return null;
        }

        return this.checkExpresion(e);

    }

    /**
     * Verifica que el tipo de retorno sea correcto. Comprueba que el tipo de
     * retorno sea el mismo que el de la función.
     *
     * @see Symbol
     */
    public void checkReturns() {
        // Coger el StructureReturnType de la funcion acutal access[1]
        Integer[] range = st.getTa().get(st.getTaIndex());
        StructureReturnType frt = st.getTs().get(range[1]).getStructureReturnType();
        // Recorrer inversamente el conjunto de simbolos para obtener todos los
        // nodos de retorno
        int nReturns = 0;
        Symbol symbol;
        for (int i = range[0]; i < range[1]; i++) {
            symbol = st.getTs().get(i);
            if (symbol.getStructureType().equals(StructureType.RETURN)) {
                nReturns++;
                StructureReturnType symbolReturn = symbol.getStructureReturnType();
                if (symbolReturn == null) {
                    // ERROR is handled outside
                    return;
                }
                if (!symbol.getStructureReturnType().equals(frt)) {
                    // ERROR RETURN TYPE DOES NOT MATCH
                    ErrorHandler.addError(ErrorCode.RETURN_VALUE_DOES_NOT_MATCH,
                            symbol.getLine(),
                            Phase.SEMANTIC);
                }
            }
        }

        // Funcion con return value sin nodos de retorno
        if (nReturns == 0) {
            // ERROR NO RETURN IN A FUNCTION WITH RETURN VALUE
            ErrorHandler.addError(ErrorCode.NO_RETURN_IN_A_FUNCTION_WITH_RETURN_VALUE,
                    st.getTs().get(range[1]).getLine(), // Cogemos la funcion de la tabla de simbolos
                    Phase.SEMANTIC);
        }

    }

    /**
     * Verifica que las dos expresiones sean de caracter aritmetico. Devuelve true
     * si ambas son aritmenticas y false en caso contrario y genera los errores
     * pertinentes
     *
     * @param e1
     * @param e2
     * @return true | false
     */
    public boolean checkLoop(Expresion e1, Expresion e2) {
        StructureReturnType returnE1 = checkExpresion(e1);
        if (returnE1 == null) {
            ErrorHandler.addError(
                    ErrorCode.MALFORMED_EXPRESSION,
                    e1.getLine(),
                    e1.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        if (returnE1 != StructureReturnType.INT) {
            // ERROR: Expresion must return an arithmetic value
            ErrorHandler.addError(ErrorCode.EXPRESION_MUST_BE_ARITHMETIC,
                    e1.getLine(),
                    e1.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        StructureReturnType returnE2 = checkExpresion(e2);

        if (returnE2 == null) {
            ErrorHandler.addError(
                    ErrorCode.MALFORMED_EXPRESSION,
                    e2.getLine(),
                    e2.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        if (returnE2 != StructureReturnType.INT) {
            // ERROR: Expresion must return an arithmetic value
            ErrorHandler.addError(ErrorCode.EXPRESION_MUST_BE_ARITHMETIC,
                    e2.getLine(),
                    e2.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }

        return true;
    }

    /**
     * Verifica que la expresión pasada por parametro sea de caracter lógico.
     * Devuelve true si la expresion es logica y false en caso contrario y genera
     * los errores pertinentes
     *
     * @param e
     * @return true | false
     */
    public boolean checkLogicalExpresion(Expresion e) {
        StructureReturnType returnE = checkExpresion(e);

        if (returnE == null) {
            ErrorHandler.addError(
                    ErrorCode.MALFORMED_EXPRESSION,
                    e.getLine(),
                    e.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        if (returnE != StructureReturnType.BOOL) {
            // ERROR: Expresion must return a logical value
            ErrorHandler.addError(ErrorCode.EXPRESION_MUST_BE_LOGICAL,
                    e.getLine(),
                    e.getColumn(),
                    Phase.SEMANTIC);
            return false;
        }
        return true;

    }

}
