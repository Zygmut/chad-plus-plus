package intermediate_code;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import core.Chadpp;
import utils.Env;

/**
 * CodigoTresDrirecciones
 */
public class ThreeAddressCode {

  // Lista de instrucciones de código de 3 direcciones
  private ArrayList<Instruction> codigo3Dir;
  // Árbol
  private Chadpp tree;

  public ThreeAddressCode(Chadpp tree) {
    this.tree = tree;
    this.codigo3Dir = new ArrayList<>();
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
