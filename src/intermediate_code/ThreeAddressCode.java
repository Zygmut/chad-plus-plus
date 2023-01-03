package intermediate_code;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import core.Chadpp;
import core.TypeVar;
import symbol_table.StructureReturnType;
import utils.Env;

/**
 * CodigoTresDrirecciones
 */
public class ThreeAddressCode {

  /**
   * Lista de instrucciones de código de 3 direcciones
   *
   * @see Instruction
   */
  private ArrayList<Instruction> codigo3Dir;

  /**
   * Árbol sintáctico del código fuente
   *
   * @see Chadpp
   */
  private Chadpp tree;

  /**
   * Tabla de variables
   *
   * @see Variable
   */
  private ArrayList<Variable> tv;

  /**
   * Subconjunto de la tabla de variables. Contiene todas las declaraciones
   * globales
   *
   * @see Variable
   */
  private ArrayList<Variable> tvg;

  /**
   * Tabla de funciones
   *
   * @see Funcion
   */
  private ArrayList<Procedimiento> tp;

  /**
   * Contador de etiquetas
   */
  private int labelCounter = 0;

  /**
   * Constructor de la clase, inicializa las variables de la clase.
   *
   * @param tree Árbol sintáctico del código fuente
   */
  public ThreeAddressCode(Chadpp tree) {
    this.tree = tree;
    this.codigo3Dir = new ArrayList<>();
    this.tv = new ArrayList<>();
    this.tvg = new ArrayList<>();
    this.tp = new ArrayList<>();
  }

  /**
   * Crea una nueva etiqueta y la devuelve.
   *
   * @return String nueva
   */
  public String newLabel() {
    return "e" + labelCounter++;
  }

  /**
   * Dados los parametros, o crea la variable y te la devuelve o si existe te pasa
   * una referencia de esa variable
   * 
   * @param id
   * @param variableType
   * @return Variable
   */
  public Variable putVar(String id, TypeVar variableType) {
    // Mirar si id es null -> variable volatil
    Variable var = null;
    if (id == null) {
      var = new Variable(variableType, "t" + tv.size(), true);
    } else {

      // Si tp esta vacio, implica que no hemos entado a ninguna funcion y por lo
      // tanto estamos creando una declaracion global
      if (tp.isEmpty()) {
        var = new Variable(variableType, id + "_" + tp.size(), false);
        tvg.add(var);
      } else {
        Procedimiento procedure = tp.get(tp.size() - 1);

        ArrayList<Variable> aux = new ArrayList<>();
        aux.addAll(procedure.getParameters());
        aux.addAll(procedure.getDeclarations());

        for (Variable variable : aux) {
          if (variable.getId().equals(id + "_" + tp.size())) {
            var = variable;
            break;
          }
        }

        if (var == null) {
          var = new Variable(variableType, id + "_" + tp.size(), false);
        }

      }

    }
    tv.add(var);
    return var;
  }

  public Procedimiento newFn(String id, StructureReturnType fnReturnType) {
    Procedimiento fn = new Procedimiento(id, fnReturnType);
    tp.add(fn);
    return fn;
  }

  /**
   * Genera el código 3 direcciones del código fuente
   */
  public void generate() {
    tree.generate3dc(this);
  }

  /**
   * Guarda el el código de 3 direcciones en un fichero de texto
   */
  public void saveThreeAddressCode() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + "/" + "Codigo3Dir.txt"));
      for (int i = 0; i < codigo3Dir.size(); i++) {
        writer.write(codigo3Dir.get(i).toString());
      }
      writer.close();
    } catch (IOException err) {
      System.out.println(err);
    }
  }

  @Override
  public String toString() {
    String out = "";
    for (Instruction instruction : codigo3Dir) {
      out += instruction.toString() + "\n";

    }
    return out;
  }

}
