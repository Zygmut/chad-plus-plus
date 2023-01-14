package core;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class A_Tuple extends BaseNode {

  private Id id;
  private Number access;

  public A_Tuple(Id id, Number access, int line, int column) {
    super(line, column);
    this.id = id;
    this.access = access;
  }

  public Id getId() {
    return id;
  }

  public void setId(Id id) {
    this.id = id;
  }

  public Number getAccess() {
    return access;
  }

  public void setAccess(Number access) {
    this.access = access;
  }

  @Override
  public String toString() {
    return ("A_Tuple [id=" + id + ", access=" + access + " line=" + line + " column=" + column + "]");
  }

  @Override
  public void generate3dc(ThreeAddressCode codigoTresDir) {
    Variable var1 = codigoTresDir.putVar(null, TypeVar.INT);
    codigoTresDir
        .addInstr(new Instruction(var1.getId(), Integer.toString(access.getValue()), Operator.ASSIGN, null));
    Variable var2 = codigoTresDir.putVar(null, TypeVar.INT);
    // Multiplicamos por el acceso más grande que tenemos en nuestras estructuras
    // soportadas. En este caso el int que ocupa 4
    codigoTresDir.addInstr(new Instruction(var2.getId(), var1.getId(), Operator.MULT, "2"));
    Variable var3 = codigoTresDir.putVar(null, TypeVar.INT);
    codigoTresDir.addInstr(new Instruction(var3.getId(), id.getValue(), Operator.INDEXED_VALUE, var2.getId()));
  }

}
