package intermediate_code;

import java.util.ArrayList;
import symbol_table.StructureReturnType;

/**
 * Clase que representa un procedimiento en el código intermedio.
 */
public class Procedimiento {

    /**
     * Identificador del procedimiento.
     */
    private String id;

    /**
     * Tipo de retorno de la estructura.
     *
     * @see StructureReturnType
     */
    private StructureReturnType returnType;

    /**
     * Lista de parametros del procedimiento.
     *
     * @see Variable
     */
    private ArrayList<Variable> parameters;

    /**
     * Lista de declaraciones del procedimiento.
     *
     * @see Variable
     */
    private ArrayList<Variable> declarations;

    public Procedimiento(String id, StructureReturnType returnType) {
        this.id = id;
        this.returnType = returnType;
        this.parameters = new ArrayList<>();
        this.declarations = new ArrayList<>();
    }

    /**
     * Añade un parametro al procedimiento.
     *
     * @param parameter Parametro a añadir.
     * @see Variable
     */
    public void addParameter(Variable parameter) {
        this.parameters.add(parameter);
    }

    /**
     * Añade una declaración al procedimiento.
     *
     * @param declaration Declaración a añadir.
     * @see Variable
     */
    public void addDeclaration(Variable declaration) {
        this.declarations.add(declaration);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StructureReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(StructureReturnType returnType) {
        this.returnType = returnType;
    }

    public ArrayList<Variable> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Variable> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<Variable> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(ArrayList<Variable> declarations) {
        this.declarations = declarations;
    }

    @Override
    public String toString() {
        String s = "id: " + id + "\n";
        s += "returns: " + returnType + "\n";
        s += "Parameters\n";
        for (Variable var : parameters) {
            s += "\t" + var + "\n";
        }
        s += "Declarations\n";
        for (Variable var : declarations) {
            s += "\t" + var + "\n";
        }
        return s;

    }

}