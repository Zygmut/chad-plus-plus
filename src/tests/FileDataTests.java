package tests;

import utils.Env;

public class FileDataTests implements UnitTest {

    private static final Test<Object> unitTest = new Test<>();

    // Test. runtests(FileData, [test1, test2, test3])
    @Override
    public void runTests() {
        unitTest.run("FileData", new Runnable[] {
                FileDataTests::testInput,
                FileDataTests::testOutput,
                FileDataTests::filePath
        });
    }

    private static void testInput() {

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".pp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("example1.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

    }

    private static void testOutput() {
        // TODO Testear que el outputpath va bien
    }

    private static void filePath() {
        // TODO Testear que el filepath sea correcto
    }

}
