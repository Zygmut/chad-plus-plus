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

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import symbol_table.SymbolTable;
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

        Scanner scanner = new Scanner(input);

        if (ErrorHandler.hasErrors()) {
            ErrorHandler.printErrors();
            System.exit(0);
        }

        // Parser
        try {
            // lexer + sintactic
            SymbolFactory sf = new ComplexSymbolFactory();
            Parser parser = new Parser(scanner, sf);
            parser.parse();

            // System.out.println(scanner.lines);
            saveTokens(scanner.lines);

            // semantic
            parser.getSemanticAnalyzer().run();
            System.out.println(parser.getSemanticAnalyzer().printSymbolTables());
            saveTable(parser.getSemanticAnalyzer().printSymbolTables(), "TablaSimbolos.txt");
            saveTable(parser.getSemanticAnalyzer().printVariableTables(), "TablaVariables.txt");
            saveTable(parser.getSemanticAnalyzer().printFunctionTables(), "TablaFunciones.txt");

            // SymbolTable symbolTable = parser.getSemanticAnalyzer().getSymbolTable();
            // System.out.println(symbolTable.printSymbolTable());

            // c3@
            ThreeAddressCode c3d = new ThreeAddressCode(parser.getTree());
            c3d.generate();
            c3d.saveThreeAddressCode();

            // Chadpp tree = parser.getTree();
            // System.out.println(tree);

        } catch (Exception e) {
            ErrorHandler.printErrors();
            System.exit(0);
        }

        if (ErrorHandler.hasErrors()) {
            ErrorHandler.printErrors();
            System.exit(0);
        }

        ErrorHandler.printErrors();
        WarningHandler.printWarnings();

    }

    private static void saveTokens(ArrayList<ComplexSymbol> tokens) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + "/" + "Tokens.txt"));
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