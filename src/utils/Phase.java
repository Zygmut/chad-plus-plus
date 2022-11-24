package utils;

// Constantes para deteminar el que fase del programa se ha producido el error o
public enum Phase {
    PRE_COMPILER("sanity checker"),
    LEXICAL("lexical"),
    SYNTACTIC("syntactic"),
    SEMANTIC("semantic"),
    CODE_GENERATION("code generation");

    public String message;

    Phase(String message) {
        this.message = message;
    }
}
