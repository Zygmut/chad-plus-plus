package core;

import intermediate_code.ThreeAddressCode;

abstract class BaseNode {

  protected int line;
  protected int column;

  public BaseNode(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return this.line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public int getColumn() {
    return this.column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public abstract void generate3dc(ThreeAddressCode codigoTresDir);
}
