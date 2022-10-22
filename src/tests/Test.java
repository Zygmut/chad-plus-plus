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

    /**
     * Ejecuta todas las funciones en un wrapper puramente estetico para mejor
     * reconocimiento
     *
     * @param Name
     * @param functions
     */
    public void runTests(String Name, Runnable[] functions) {
        String header = "=== " + Name + " Unit Tests ===";
        String separator = "-".repeat(header.length());
        String footer = "=".repeat(header.length());

        System.out.println();
        System.out.println(ConsoleColor.printColored(ConsoleColor.BLUE, header));

        for (int i = 0; i < functions.length; i++) {

            functions[i].run();

            if (i < functions.length - 1) {
                System.out.println(ConsoleColor.printColored(ConsoleColor.BLUE, separator));

            }
        }
        System.out.println(ConsoleColor.printColored(ConsoleColor.BLUE, footer));
    }
}
