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
        unitTest.assertT(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "");
        unitTest.assertT(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("test.exe", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("test.deb", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

    }

    private static void testName() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.txt", "");
        unitTest.assertEq(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH + "test.chpp", "");
        unitTest.assertEq(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(System.getProperty("user.dir") + "\\test.chpp"), "");
        unitTest.assertEq(Env.FILE_DATA.getFileName(), "test");

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".test.exe", "");
        unitTest.assertEq(Env.FILE_DATA.getFileName(), "");

    }

    private static void testInput() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("." + Env.SLASH + "a.chpp", "");
        unitTest.assertT(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH + "a.chpp", "");
        unitTest.assertT(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("src" + Env.SLASH + "a.chpp", "");
        unitTest.assertT(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR" -> No ha reconocido la absencia argumentos
        Env.FILE_DATA.setMultipleFileData("", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(Env.SLASH, "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("a", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".txt", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(".chpp", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("a.", "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());

    }

    private static void testOutput() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "");
        unitTest.assertEq(Env.FILE_DATA.getOutputPath(), "." + Env.SLASH + "target" + Env.SLASH);

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test.chpp", "out");
        unitTest.assertEq(Env.FILE_DATA.getOutputPath(), "out");

        // Resultado esperado: "OK"
        // TODO Decide on what to do in this occasion
        // Env.FILE_DATA.setMultipleFileData("test.chpp", "out" + Env.SLASH);
        // unitTest.customAssert(Env.FILE_DATA.getOutputPath(), "out" + Env.SLASH);

    }

    private static void testFilePath() {

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath("\\tests\\test.chpp"), "");
        unitTest.assertEq(Env.FILE_DATA.getFilePath(),
                Sanity.sanitizePath(System.getProperty("user.dir") + "\\tests\\test.chpp"));

        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(".\\tests\\test.chpp"), "");
        unitTest.assertEq(Env.FILE_DATA.getFilePath(),
                Sanity.sanitizePath(Sanity.sanitizePath(System.getProperty("user.dir") + "\\tests\\test.chpp")));

        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData(Sanity.sanitizePath(".\\tests\\"), "");
        unitTest.assertF(Env.FILE_DATA.checkFileData());
    }

}
