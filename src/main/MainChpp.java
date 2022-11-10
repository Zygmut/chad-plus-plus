package main;

import errors.ErrorHandler;
import tests.FileDataTests;
import utils.Env;
import utils.Sanity;
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
        // if (Env.TEST_MODE) {
        // new FileDataTests().runTests();
        // }
        //
        // if (Sanity.checkInput(args) == Env.Error) {
        // ErrorHandler.printErrors();
        // System.exit(0);
        // }
        Reader input;

        try {
            if (args.length > 0) {
                input = new FileReader(args[0]);
            } else {
                System.out.println("Escriu l'expressió que vols calcular:");
                System.out.print(">>> ");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                input = new CharArrayReader(in.readLine().toCharArray());
            }

            SymbolFactory sf = new ComplexSymbolFactory();
            Scanner scanner = new Scanner(input);
            Parser parser = new Parser(scanner, sf);
            Symbol result = parser.parse();
            System.out.println("Resultat: " + result.value);
        } catch (Exception e) {
            ErrorHandler.printErrors();
        }

    }
}