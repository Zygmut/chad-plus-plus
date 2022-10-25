package tests;

import utils.Env;
import utils.Sanity;

public class FileDataTests implements UnitTest {

    private static final Test<Object> unitTest = new Test<>();

    // Test. runtests(FileData, [test1, test2, test3])
    @Override
    public void runTests() {
        unitTest.run("FileData", new Runnable[] {
                FileDataTests::testExtension,
                FileDataTests::testName,
                FileDataTests::testInput,
                FileDataTests::testOutput,
                FileDataTests::testFilePath
        });
    }

    private static void testExtension() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("test.exe", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("test.deb", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

    }

    private static void testName() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.txt", "");
        unitTest.customAssert(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH + "test.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(System.getProperty("user.dir") + "\\test.chpp"), "");
        unitTest.customAssert(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".test.exe", "");
        unitTest.customAssert(Env.FILE_DATA.getFileName(), "");

    }

    private static void testInput() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("." + Env.SLASH + "a.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH + "a.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("src" + Env.SLASH + "a.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), true);

        // Resultado esperado: "ERROR" -> No ha reconocido la absencia argumentos
        Env.FILE_DATA.setMultipleFileData("", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH, "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("a", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".txt", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".chpp", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("a.", "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);

    }

    private static void testOutput() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "");
        unitTest.customAssert(Env.FILE_DATA.getOutputPath(), "." + Env.SLASH + "target" + Env.SLASH);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "out");
        unitTest.customAssert(Env.FILE_DATA.getOutputPath(), "out");

        // Resultado esperado: "OK"
        // TODO Decide on what to do in this occasion
        // Env.FILE_DATA.setMultipleFileData("test.chpp", "out" + Env.SLASH);
        // unitTest.customAssert(Env.FILE_DATA.getOutputPath(), "out" + Env.SLASH);

    }

    private static void testFilePath() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath("\\tests\\test.chpp"), "");
        unitTest.customAssert(Env.FILE_DATA.getFilePath(),
                Sanity.sanitizePath(System.getProperty("user.dir") + "\\tests\\test.chpp"));

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(".\\tests\\test.chpp"), "");
        unitTest.customAssert(Env.FILE_DATA.getFilePath(),
                Sanity.sanitizePath(Sanity.sanitizePath(System.getProperty("user.dir") + "\\tests\\test.chpp")));

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(".\\tests\\"), "");
        unitTest.customAssert(Env.FILE_DATA.checkFileData(), false);
    }

}
