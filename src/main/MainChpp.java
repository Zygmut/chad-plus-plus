package main;

import errors.ErrorCode;
import errors.ErrorHandler;
import optimization.Optimizer;
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
        long tInicio = System.currentTimeMillis();
        Scanner scanner = new Scanner(input);
        long tFin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución del lexer: " + (tFin - tInicio) + " milisegundos");

        if (ErrorHandler.hasErrors()) {
            if (WarningHandler.hasWarnings()) {
                WarningHandler.printWarnings();
                saveTable(WarningHandler.getWarningString(), "Warnings.txt");
            }
            ErrorHandler.printErrors();
            saveTable(ErrorHandler.getErrorsString(), "Errores.txt");
            System.exit(0);
        }

        // Front-end
        SymbolFactory sf = null;
        Parser parser = null;
        try {
            tInicio = System.currentTimeMillis();
            sf = new ComplexSymbolFactory();
            parser = new Parser(scanner, sf);
            parser.parse();
            tFin = System.currentTimeMillis();
            System.out.println("Tiempo de ejecución del parser + semantic: " + (tFin - tInicio) + " milisegundos");

        } catch (Exception e) {
            if (!ErrorHandler.hasErrors()) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        if (ErrorHandler.hasErrors()) {
            if (WarningHandler.hasWarnings()) {
                WarningHandler.printWarnings();
                saveTable(WarningHandler.getWarningString(), "Warnings.txt");
            }
            ErrorHandler.printErrors();
            saveTable(ErrorHandler.getErrorsString(), "Errores.txt");
            System.exit(0);
        } else {
            // Guardar la tabla de simbolos
            saveTable(parser.getSymbolTable().toString(), "TablaSimbolos.txt");

            // Guardar los tokens
            saveTokens(scanner.tokens);
        }

        // Back-end
        ThreeAddressCode c3d = new ThreeAddressCode();
        Assembly asm = new Assembly(c3d, parser.getSymbolTable());
        try {
            // c3@
            tInicio = System.currentTimeMillis();
            parser.getTree().generate3dc(c3d);
            tFin = System.currentTimeMillis();
            System.out.println(
                    "Tiempo de ejecución del generador de código intermedio: " + (tFin - tInicio) + " milisegundos");

            // ASM
            tInicio = System.currentTimeMillis();
            asm.generateAssemblyCode();
            tFin = System.currentTimeMillis();
            System.out.println(
                    "Tiempo de ejecución del generador de código ensamblador: " + (tFin - tInicio) + " milisegundos");
        } catch (Exception e) {
            if (!ErrorHandler.hasErrors()) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        if (ErrorHandler.hasErrors()) {
            if (WarningHandler.hasWarnings()) {
                WarningHandler.printWarnings();
                saveTable(WarningHandler.getWarningString(), "Warnings.txt");
            }
            ErrorHandler.printErrors();
            saveTable(ErrorHandler.getErrorsString(), "Errores.txt");
            System.exit(0);
        } else {
            c3d.saveThreeAddressCode("Codigo3Dir.txt");
            saveTable(c3d.getTpString(), "TablaProcedimientos.txt");
            saveTable(c3d.getTvString(), "TablaVariables.txt");
            asm.saveAssemblyCode(Env.FILE_DATA.getFileName() + ".x68");
        }

        ThreeAddressCode newC3d = null;
        Assembly asmOpt = null;
        try {
            // Optimizador
            Optimizer optimizer = new Optimizer(c3d);
            tInicio = System.currentTimeMillis();
            optimizer.optimizeThreeAddressCode();
            tFin = System.currentTimeMillis();
            System.out.println("Tiempo de ejecución del optimizador: " + (tFin - tInicio) + " milisegundos");
            newC3d = optimizer.getThreeAddressCodeOptimized();

            // ASM Opt
            asmOpt = new Assembly(newC3d, parser.getSymbolTable());
            tInicio = System.currentTimeMillis();
            asmOpt.generateAssemblyCode();
            tFin = System.currentTimeMillis();
            System.out.println(
                    "Tiempo de ejecución del generador de código ensamblador optimizado: " + (tFin - tInicio)
                            + " milisegundos");

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

        if (!ErrorHandler.hasErrors()) {
            newC3d.saveThreeAddressCode("Codigo3Dir_Opt.txt");
            saveTable(newC3d.getTpString(), "TablaProcedimientos_Opt.txt");
            saveTable(newC3d.getTvString(), "TablaVariables_Opt.txt");
            asmOpt.saveAssemblyCode(Env.FILE_DATA.getFileName() + "_opt.x68");

        }

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