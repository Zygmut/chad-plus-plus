package tests;

import utils.ConsoleColor;

public class Test<T> {

    private String OK() {
        return ConsoleColor.printColored(ConsoleColor.GREEN, "\tOK");
    }

    private String ERROR() {
        return ConsoleColor.printColored(ConsoleColor.RED, "\tERROR");
    }

    public void customAssert(T object, T excpected) {
        System.out.println((object.equals(excpected)) ? OK() : ERROR());
    }
}
