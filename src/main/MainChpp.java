package main;

import errors.ErrorCodes;
import errors.ErrorHandler;
import tests.FileDataTests;
import utils.Env;
import utils.Phase;
import utils.Sanity;
import warnings.WarningHandler;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import grammar.Scanner;
import grammar.Parser;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
            ErrorHandler.addError(ErrorCodes.FILE_NOT_FOUND, -1, Phase.PRE_COMPILER_PHASE);
        }

        Scanner scanner = new Scanner(input);

        if (ErrorHandler.hasErrors()) {
            ErrorHandler.printErrors();
            System.exit(0);
        }

        // Parser
        try {
            SymbolFactory sf = new ComplexSymbolFactory();
            Parser parser = new Parser(scanner, sf);
            Symbol s = parser.parse();
            System.out.println(s.toString());
            System.out.println(s.value.toString());

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