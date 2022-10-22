package tests;

import utils.Env;
import utils.FileData;

public class FileDataTests implements UnitTest {

    private static final Test<Object> unitTest = new Test<>();

    // Test. runtests(FileData, [test1, test2, test3])
    @Override
    public void runTests() {
        unitTest.run("FileData", new Runnable[] {
                FileDataTests::test1
        });

    }

    private static void test1() {

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

    }
}
