package tests;

import utils.Env;

public class FileDataTests {
    public static void runFileDataTests() {
        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("", ".pp", "", "");
        System.out.println(Env.FILE_DATA.checkFileData() ? "OK" : "ERROR");
        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("test", ".txt", "", "");
        System.out.println(Env.FILE_DATA.checkFileData() ? "OK" : "ERROR");
        // Resultado esperado: "OK"
        Env.FILE_DATA.setMultipleFileData("example1", ".chpp", "", "");
        System.out.println(Env.FILE_DATA.checkFileData() ? "OK" : "ERROR");
        // Resultado esperado: "ERROR"
        Env.FILE_DATA.setMultipleFileData("", ".txt", "", "");
        System.out.println(Env.FILE_DATA.checkFileData() ? "OK" : "ERROR");

    }
}
