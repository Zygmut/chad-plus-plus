package tests;

import utils.ConsoleColor;
import utils.Env;
import utils.FileData;

public class FileDataTests {

    private static final Test<Object> unitTest = new Test<>();

    public static void runFileDataTests() {
        System.out.println(ConsoleColor.printColored(ConsoleColor.BLUE, "\n=== File Unit Tests ==="));

        // Resultado esperado: "OK"
        unitTest.customAssert(new FileData(), Env.FILE_DATA);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("", "pp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test", "txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("example1", "chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("", "txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        System.out.println(ConsoleColor.printColored(ConsoleColor.BLUE, "======================="));
    }
}
