package errors;

/**
 * ErrorCodes - Clase que contiene los códigos de error de la aplicación.
 * Se utiliza para identificar el tipo de error que se ha producido en la
 * compilación y evitar los 'magic numbers'.
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 */
public enum ErrorCode {

    CUSTOM(""),
    FILE_NOT_FOUND("Couldn't find file."),
    INVALID_FILE_EXTENSION("Invalid file extension."),
    INVALID_FILE("Invalid file."),
    INVALID_TOKEN("Invalid Token."),
    PARSER_ERROR("Parser error."),
    TUPLE_ASSIGNATION_TO_NON_TUPLE("Tuple assignation to non-tuple."),
    UNDECLARED_IDENTIFIER("Undeclared identifier."),
    INCOMPATIBLE_TYPES(
            "Incompatible types. The return type of the function is not the same as the return type of the expression."),
    INVALID_ASSIGNMENT("Invalid assignment."),
    INVALID_RETURN("Invalid return."),
    WRONG_NUMBER_OF_ARGUMENTS("Wrong number of arguments."),
    INVALID_FUNCTION_CALL("Invalid function call."),
    INVALID_EXPRESSION("Invalid expression."),
    INVALID_INSTRUCTION("Invalid instruction."),
    UNDECLARED_FUNCTION("Undeclared function."),
    UNDECLARED_VARIABLE("Undeclared variable."),
    NOT_A_FUNCTION("A function was expected but a variable was found."),
    NOT_A_VARIABLE("A variable was expected but a function was found."),
    TUPLE_CAN_NOT_CONTAIN_TUPLE("Tuple can not contain tuple."),
    SET_TUPLES_ONLY_ALONE("Set tuples only alone."),
    TUPLE_INDEX_OUT_OF_BOUNDS("Index out of bounds."),
    UNSUPPORTED_OPERATION("Unsupported operation."),
    UNSUPPORTED_ELEMENT("Unsupported element."),
    RETURN_TYPE_TUPLE_IN_TUPLE("Return type tuple in tuple it's not allowed."),
    INVALID_NUMBER_OF_ARGUMENTS("Invalid number of arguments."),
    INVALID_ARGUMENT_TYPE("Invalid argument type."),
    MALFORMED_EXPRESSION("Malformed expression."),
    UNSUPPORTED_EXPRESSION_TYPE("Unsupported expression type."),
    ID_NOT_DECLARED_OR_NOT_FOUND("Id not found."),
    NO_RETURN_IN_A_FUNCTION_WITH_RETURN_VALUE("No return in a function with return value."),
    RETURN_VALUE_DOES_NOT_MATCH("Return value does not match the function return"),
    FUNCTION_NO_ARGUMENTS_ARGUMENTS_PASSED("Function has no parameters but parameters were passed."),
    FUNCTION_ARGUMENTS_PASSED_NO_ARGUMENTS("Function has parameters but no parameters were passed."),
    CANNOT_MODIFY_CONSTANT_VARIABLES("Cannot modify the value of a constant defined variable"),
    ASIGNATION_TYPE_DOES_NOT_MATCH("Expresion type does not match variable type"),
    DUPLICATED_VARIABLE("This variable has been declared previously."),
    DUPLICATED_FUNCTION("This function has been declared previously."),
    EXPRESION_MUST_BE_ARITHMETIC("Expresion must return an arithmetic value."),
    EXPRESION_MUST_BE_LOGICAL("Expresion must return a logical value."),
    CANNOT_RETURN_TUPLE_PRIMITIVE("Cannot return tuple primitive, create a variable");

    public String message;

    ErrorCode(String message) {
        this.message = message;
    }

}