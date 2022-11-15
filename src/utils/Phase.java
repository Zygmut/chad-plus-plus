package utils;

// Constantes para deteminar el que fase del programa se ha producido el error o
public enum Phase {
    PRE_COMPILER_PHASE("sanity checker"),
    LEXICAL_PHASE("sexical"),
    SYNTACTIC_PHASE("syntactic"),
    SEMANTIC_PHASE("semantic"),
    CODE_GENERATION_PHASE("code generation");

    public String message;

    Phase(String message) {
        this.message = message;
    }
}
