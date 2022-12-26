package main;

import errors.ErrorCode;
import errors.ErrorHandler;
import tests.FileDataTests;
import utils.Env;
import utils.Phase;
import utils.Sanity;
import warnings.WarningHandler;

import java.io.FileReader;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
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

            // semantic
            parser.getSemanticAnalyzer().run();
            System.out.println(parser.getSemanticAnalyzer().printSymbolTables());

            //SymbolTable symbolTable = parser.getSemanticAnalyzer().getSymbolTable();
            //System.out.println(symbolTable.printSymbolTable());

            // c3@
            ThreeAddressCode c3d = new ThreeAddressCode(parser.getTree());
            c3d.generate();
            //Chadpp tree = parser.getTree();
            //System.out.println(tree);

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

}