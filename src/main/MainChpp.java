package main;

import errors.ErrorCode;
import errors.ErrorHandler;
import tests.FileDataTests;
import utils.Env;
import utils.Phase;
import utils.Sanity;
import warnings.WarningHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import assembly.Assembly;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import grammar.Scanner;
import intermediate_code.ThreeAddressCode;
import grammar.Parser;

/**
 * Main - Clase principal del programa
 *
 * En esta clase se define el punto de entrada del programa.
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 */
public class MainChpp {

    public static void main(String[] args) {
        // Unit Testing
        if (Env.TEST_MODE) {
            new FileDataTests().runTests();
        }

        if (Sanity.checkInput(args) == Env.Error) {
            ErrorHandler.printErrors();
            System.exit(0);
        }

        File rutaFicherosGenerados = new File(Env.GENERATED_FILES);
        if (!rutaFicherosGenerados.exists()) {
            rutaFicherosGenerados.mkdir();
        }

        // Scanner
        FileReader input = null;
        try {
            input = new FileReader(Env.FILE_DATA.getFilePath());
        } catch (Exception e) {
            ErrorHandler.addError(ErrorCode.FILE_NOT_FOUND, -1, Phase.PRE_COMPILER);
        }

        // Lexer
        Scanner scanner = new Scanner(input);

        if (WarningHandler.hasWarnings()) {
            WarningHandler.printWarnings();
            saveTable(WarningHandler.getWarningString(), "Warnings.txt");
        }

        if (ErrorHandler.hasErrors()) {
            ErrorHandler.printErrors();
            saveTable(ErrorHandler.getErrorsString(), "Errores.txt");
            System.exit(0);
        }

        // Front-end
        SymbolFactory sf = null;
        Parser parser = null;
        try {
            sf = new ComplexSymbolFactory();
            parser = new Parser(scanner, sf);
            parser.parse();

            // Guardar la tabla de simbolos
            saveTable(parser.getSymbolTable().toString(), "TablaSimbolos.txt");

            // Guardar los tokens
            saveTokens(scanner.tokens);
        } catch (Exception e) {
            if (!ErrorHandler.hasErrors()) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        if (WarningHandler.hasWarnings()) {
            WarningHandler.printWarnings();
            saveTable(WarningHandler.getWarningString(), "Warnings.txt");
        }

        if (ErrorHandler.hasErrors()) {
            ErrorHandler.printErrors();
            saveTable(ErrorHandler.getErrorsString(), "Errores.txt");
            System.exit(0);
        }

        // Back-end
        try {
            // c3@
            ThreeAddressCode c3d = new ThreeAddressCode();
            parser.getTree().generate3dc(c3d);
            c3d.saveThreeAddressCode();
            saveTable(c3d.getTpString(), "TablaProcedimientos.txt");
            saveTable(c3d.getTvString(), "TablaVariables.txt");

            // ASM
            Assembly asm = new Assembly(c3d, parser.getSymbolTable());
            asm.generateAssemblyCode();
            asm.saveAssemblyCode(Env.FILE_DATA.getFileName() + ".x68");
        } catch (Exception e) {
            if (!ErrorHandler.hasErrors()) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        WarningHandler.printWarnings();
        saveTable(WarningHandler.getWarningString(), "Warnings.txt");

        ErrorHandler.printErrors();
        saveTable(ErrorHandler.getErrorsString(), "Errores.txt");

    }

    private static void saveTokens(ArrayList<ComplexSymbol> tokens) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + Env.SLASH + "Tokens.txt"));
            for (int i = 0; i < tokens.size(); i++) {
                writer.write(tokens.get(i).getName() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    private static void saveTable(String table, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + "/" + fileName));
            writer.write(table);
            writer.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

}