package core;

import java.util.ArrayList;

import intermediate_code.Instruction;
import intermediate_code.Operator;
import intermediate_code.ThreeAddressCode;
import intermediate_code.Variable;

public class Expresion extends BaseNode {
    private Value value;
    private Expresion nextExpresion;
    private Op op;

    public Expresion(Value value, Expresion nextExpresion, Op op, int line, int column) {
        super(line, column);
        this.value = value;
        this.nextExpresion = nextExpresion;
        this.op = op;
    }

    public Expresion(Value value, int line, int column) {
        super(line, column);
        this.value = value;
        this.nextExpresion = null;
        this.op = null;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Expresion getNextExpresion() {
        return nextExpresion;
    }

    public void setNextExpresion(Expresion nextExpresion) {
        this.nextExpresion = nextExpresion;
    }

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "Expresion [value=" + value + ", nextExpresion=" + nextExpresion + ", op=" + op + " line=" + line
                + " column=" + column + "]";

    }

    public ArrayList<Object> toListOfObjects(Expresion expresion) {
        ArrayList<Object> list = new ArrayList<>();
        for (Expresion exp = expresion; exp != null; exp = exp.getNextExpresion()) {
            list.add(exp.getValue());
            if (exp.getOp() == null) {
                break;
            }
            list.add(exp.getOp());
        }

        // Mirar los valores pares de la lista, ya que son los Values
        // Mirar la instancia de estos Values
        // Si es una expresión se llama
        return list;
    }

    private void aritmeticOperation(ArrayList<Object> list, ThreeAddressCode codigoTresDir) {
        Op op = null;
        Variable var1 = null;
        Variable var2 = null;
        Variable dest = null;
        Operator c3dop = null;
        Value value1 = null;
        Value value2 = null;

        // recorrido para / y *
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) {
                // Sacamos el operador de la operación
                op = (Op) list.get(i);
                // En este bucle solo se contemplan las multiplicaciones y divisiones
                if (op == Op.MULT || op == Op.DIV) {
                    // Miramos si la primera variable es un value, si no lo es debe ser una variable
                    // ya calculada
                    if (list.get(i - 1) instanceof Value) {
                        value1 = (Value) list.get(i - 1);
                        value1.generate3dc(codigoTresDir);
                        var1 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                    } else {
                        var1 = (Variable) list.get(i - 1);
                    }

                    // Miramos si la segunda variable es un value, si no lo es debe ser una variable
                    // ya calculada
                    if (list.get(i + 1) instanceof Value) {
                        value2 = (Value) list.get(i + 1);
                        value2.generate3dc(codigoTresDir);
                        var2 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                    } else {
                        var2 = (Variable) list.get(i + 1);
                    }

                    // Generamos una variable para el destino
                    dest = codigoTresDir.putVar(null, TypeVar.INT);

                    // Parseamos el operando a Operator
                    c3dop = op == Op.MULT ? Operator.MULT : Operator.DIV;

                    // Generamos la instrucción de 3 dir
                    codigoTresDir.addInstr(new Instruction(dest.getId(), var1.getId(), c3dop, var2.getId()));

                    // Eliminar los elementos que se acaban de operar
                    list.remove(i - 1);
                    list.remove(i - 1);
                    list.remove(i - 1);

                    // Añadimos la variable al arraylist para sustituir los valores operados
                    list.add(i - 1, dest);

                    // Ya que hemos eliminado elementos, reseteamos el valor de i a -1 para que
                    // despues de que le sume 1 el for, estemos al principio de la lista
                    i = -1;
                }
            }
        }
        // recorrido para + y -
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) {
                // Miramos si la primera variable es un value, si no lo es debe ser una variable
                // ya calculada
                if (list.get(i - 1) instanceof Value) {
                    value1 = (Value) list.get(i - 1);
                    value1.generate3dc(codigoTresDir);
                    var1 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                } else {
                    var1 = (Variable) list.get(i - 1);
                }

                // Miramos si la segunda variable es un value, si no lo es debe ser una variable
                // ya calculada
                if (list.get(i + 1) instanceof Value) {
                    value2 = (Value) list.get(i + 1);
                    value2.generate3dc(codigoTresDir);
                    var2 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                } else {
                    var2 = (Variable) list.get(i + 1);
                }

                // Generamos una variable para el destino
                dest = codigoTresDir.putVar(null, TypeVar.INT);

                // Parseamos el operando a Operator
                c3dop = (Op) list.get(i) == Op.PLUS ? Operator.ADD : Operator.SUB;

                // Generamos la instrucción de tres direcciones
                codigoTresDir.addInstr(new Instruction(dest.getId(), var1.getId(), c3dop, var2.getId()));

                // Eliminar los elementos que se acaban de operar
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);

                // Añadimos la variable al arraylist para sustituir los valores operados
                list.add(i - 1, dest);

                // Ya que hemos eliminado elementos, reseteamos el valor de i a -1 para que
                // despues de que le sume 1 el for, estemos al principio de la lista
                i = -1;
            }
        }

    }

    private void logicalOperation(ArrayList<Object> list, ThreeAddressCode codigoTresDir) {
        Variable var1 = null;
        Variable var2 = null;
        Variable dest = null;
        Operator c3dop = null;
        Value value1 = null;
        Value value2 = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) {
                // Miramos si la primera variable es un value, si no lo es debe ser una variable
                // ya calculada
                if (list.get(i - 1) instanceof Value) {
                    value1 = (Value) list.get(i - 1);
                    value1.generate3dc(codigoTresDir);
                    var1 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                } else {
                    var1 = (Variable) list.get(i - 1);
                }

                // Miramos si la segunda variable es un value, si no lo es debe ser una variable
                // ya calculada
                if (list.get(i + 1) instanceof Value) {
                    value2 = (Value) list.get(i + 1);
                    value2.generate3dc(codigoTresDir);
                    var2 = codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1);
                } else {
                    var2 = (Variable) list.get(i + 1);
                }

                // Generamos una variable para el destino
                dest = codigoTresDir.putVar(null, TypeVar.BOOL);

                // Parseamos el operando a Operator
                switch ((Op) list.get(i)) {
                    case REQUAL:
                        c3dop = Operator.EQUAL;
                        break;
                    case LT:
                        c3dop = Operator.LESS;
                        break;
                    case GT:
                        c3dop = Operator.GREATER;
                        break;
                    case AND:
                        c3dop = Operator.AND;
                        break;
                    case OR:
                        c3dop = Operator.OR;
                        break;
                    default:
                        break;
                }

                // Generamos la instrucción de tres direcciones
                codigoTresDir.addInstr(new Instruction(dest.getId(), var1.getId(), c3dop, var2.getId()));

                // Eliminar los elementos que se acaban de operar
                list.remove(i - 1);
                list.remove(i - 1);
                list.remove(i - 1);

                // Añadimos la variable al arraylist para sustituir los valores operados
                list.add(i - 1, dest);

                // Ya que hemos eliminado elementos, reseteamos el valor de i a -1 para que
                // despues de que le sume 1 el for, estemos al principio de la lista
                i = -1;
            }
        }
    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        // Generamos una lista de objectos con todos los values y operandos
        // 3 / fn() + 1 * 5
        // [3, /, fn(), +, 1, *, 5]
        ArrayList<Object> list = toListOfObjects(this);

        // Revisamos si tienen expresiones i.e parentesis, en tal caso, llamamos a su
        // codigo de tres direcciones
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Expresion) {
                ((Expresion) list.get(i)).generate3dc(codigoTresDir);
                // Sustituimos el valor de la expresión por la última variable de la tabla de
                // variables generada, que es donde debería encontrarse el resultado de la
                // expresión evaluada
                list.remove(i);
                list.add(i, codigoTresDir.getTv().get(codigoTresDir.getTv().size() - 1));
            }
        }

        // Si solo tiene un valor, generamos el codigo de ese y retornamos
        if (list.size() == 1) {
            if (list.get(0) instanceof Variable) {
                return;
            }
            ((Value) list.get(0)).generate3dc(codigoTresDir);
            return;
        }

        // checkeamos si la operación es aritmetica o lógica y llamamos a su respectivo
        // handler
        if (((Op) list.get(1)).ordinal() >= 4) {
            logicalOperation(list, codigoTresDir);
        } else {
            aritmeticOperation(list, codigoTresDir);
        }

    }

}
