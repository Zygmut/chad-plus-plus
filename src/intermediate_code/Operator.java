package intermediate_code;

/**
 * Operator
 */
public enum Operator {
    MULT,
    DIV,
    ADD,
    SUB,
    EQUAL,
    LESS,
    GREATER,
    AND,
    OR,
    GOTO,
    SKIP,
    ASSIGN,
    PARAM,
    CALL,
    RETURN,
    PMB,
    IN_INT,
    IN_BOL,
    PRINT_INT,
    PRINT_BOL,
    // a = b[c]
    INDEXED_VALUE,
    // a[b] = c
    INDEXED_ASSIGN,
    IF,
    OUT,
    EXIT
}