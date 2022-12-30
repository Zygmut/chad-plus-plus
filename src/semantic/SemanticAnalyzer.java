package semantic;

import core.*;
import java.util.Stack;
import java.util.ArrayList;
import symbol_table.Symbol;
import symbol_table.SymbolTable;
import utils.Phase;
import symbol_table.StructureReturnType;
import symbol_table.StructureType;
import errors.ErrorCode;
import errors.ErrorHandler;

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
    private SymbolTable st;

    /**
     * Constructor, recibe la tabla de simbolos por parámetro
     * 
     * @param st Tabla de simbolos
     */
    public SemanticAnalyzer(SymbolTable st) {
        this.st = st;
    }

    /**
     * Verifica que la llamada a una función sea correcta.
     *
     * @param callFn Nodo CallFn
     * @return boolean[2] -> [0] = true si el número de argumentos es correcto,
     *         false en caso contrario [1] = tipo de error (false = error de tipos
     *         no validos, true = error de número de argumentos)
     * @see CallFn
     */
    public boolean[] checkCallFArgs(CallFn callFn) {
        Stack<Symbol> pila = st.getParameters(callFn.getId().getValue());

        L_Args l_Args = callFn.getArgs();

        for (Expresion param = l_Args.getArg(); pila.empty() && param != null;) {
            Symbol arg = (Symbol) pila.pop();

            StructureReturnType argType = arg.getStructureReturnType();
            StructureReturnType type = checkExpresion(param);

            if (type == null || type != argType) {
                // Error tipos no coinciden
                // [0] = false -> Error semantico, [1] = Error de tipos
                return new boolean[] { false, false };
            }

            l_Args = l_Args.getNextArg();
            param = l_Args.getArg();
        }

        // si la pila NO está vacia o quedan parametros por evaluar -> error
        if (!pila.empty() || l_Args.getArg() != null) {
            // [0] = false -> Error semantico, [1] = Error de número de argumentos
            return new boolean[] { false, true };
        }

        return new boolean[] { true, false };
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
                type = st.getSymbol(value.getId().getValue()).getStructureReturnType();
                break;
            case "CallFn":
                type = st.getFunction(value.getCallFn().getId().getValue()).getStructureReturnType();
                break;
            case "Input":
                type = StructureReturnType.INT;
                if (value.getInput().getType() == 1) {
                    type = StructureReturnType.BOOL;
                }
                break;
            case "A_Tuple":
                type = st.getNTupleArgument(value.getaTuple().getId().getValue(),
                        value.getaTuple().getAccess().getValue())
                        .getStructureReturnType();
                break;
            case "Tuple":
                type = StructureReturnType.TUP;
                break;
            case "Expresion":
                type = checkExpresion(value.getExpresion());
                if (type == null) {
                    // TODO: ERROR en la expresion
                    return null;
                }
                break;
            default:
                // TODO: Error unsuported
                type = null;
        }

        Op op = exp.getOp();
        if (op == null) {
            return type;
        }

        if (type == StructureReturnType.BOOL) {
            if (op.ordinal() < 4) {
                // operación Incompatible (operaciones aritméticas cuando la variable es tipo
                // BOOL)
                return null;
            }

        } else if (type == StructureReturnType.INT) {
            if (op.ordinal() >= 4) {
                // operación Incompatible (operaciones lógicas cuando la variable es tipo INT)
                return null;
            }

        } else if (type == StructureReturnType.TUP) {
            if (op != null) {
                // NO SE PERMITEN OPERACIONES CON TUPLAS
                return null;
            }

        } else {
            // StructureReturnType = VOID
            return null;
        }

        if (checkExpresion(exp.getNextExpresion()) != type) {
            return null;
        }

        return type;
    }

    /**
     * Verifica el Id de una expresión. Y devuelve el tipo de la expresión si
     * es correcta y en caso contrario devuelve null.
     *
     * @param Id idExp
     * @return StructureReturnType | null
     * @see Id
     * @see StructureReturnType
     */
    public StructureReturnType checkId(Id exp) {
        Symbol symbol = st.getSymbol(exp.getValue());
        if (symbol == null) {
            // Error semantico, no existe el id
            return null;
        }
        return symbol.getStructureReturnType();
    }

    /**
     * Verifica que la asignación o declaración sea correcta. Y devuelve el tipo de
     * la expresión si es correcta y en caso contrario devuelve null.
     * 
     * @param assig Asignación
     * @return StructureReturnType | null
     * @see Asignation
     * @see StructureReturnType
     */
    public StructureReturnType checkAsignation(Asignation assig) {
        return null;
    }

    /**
     * Verifica el tamaño de la tupla. Comprueba que el tamaño de la tupla sea el
     * mismo que el de la declaración inicial. Y devuelve el tipo de la expresión si
     * es correcta y en caso contrario devuelve null.
     * 
     * @param tup Tupla
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
     * Verifica que el tipo de retorno sea correcto. Comprueba que el tipo de
     * retorno sea el mismo que el de la función.
     *
     * @param rtn
     * @return void
     * @see Symbol
     */
    public void checkReturns() {
        // Coger el StructureReturnType de la funcion acutal access[1]
        Integer[] range = st.getTa().get(st.getTaIndex());
        StructureReturnType frt = st.getTs().get(range[1]).getStructureReturnType();
        // Recorrer inversamente el conjunto de simbolos para obtener todos los
        // nodos de retorno
        int nReturns = 0;
        int index = range[1] - 1;
        for (Symbol symbol = st.getTs().get(index); symbol.getStructureType()
                .equals(StructureType.RETURN); symbol = st.getTs().get(index--)) {
            nReturns++;
            if (!symbol.getStructureReturnType().equals(frt)) {
                // ERROR RETURN TYPE DOES NOT MATCH
                ErrorHandler.addError(ErrorCode.RETURN_VALUE_DOES_NOT_MATCH,
                        symbol.getLine(),
                        Phase.SEMANTIC);
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
}
