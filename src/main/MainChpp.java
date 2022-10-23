package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.ChadppLexer;
import antlr.ChadppParser;
import tests.FileDataTests;
import utils.ConsoleColor;
import utils.Env;
import utils.Sanity;

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
            System.out.println(ConsoleColor.printColored(ConsoleColor.RED, "Error in arguments"));
            System.exit(0);
        }

        try {
            InputStream inputStream = new FileInputStream(Env.FILE_DATA.getFilePath());
            Lexer lexer = new ChadppLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            ChadppParser parser = new ChadppParser(tokenStream);

            if (Env.VISUALIZATION) {
                guiTreeVisualization(parser);
            } else {
                /*
                 * ChadppParser.ChadppContext chadppContext = parser.chadpp();
                 * ChadppVisitor visitor = new ChadppVisitor();
                 * visitor.visit(chadppContext);
                 */
                ChadppListener listener = new ChadppListener(parser);
                listener.init();
            }

        } catch (IOException e) {
            System.out.println(ConsoleColor
                    .printColored(ConsoleColor.RED,
                            "Fichero no encontrado. Comprueba si la ruta asignada es la correcta."));
            e.printStackTrace();
        }

    }

    private static void guiTreeVisualization(ChadppParser parser) {
        ParseTree tree = parser.chadpp();

        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        JScrollPane scrPane = new JScrollPane(panel);
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Scale a little
        panel.add(viewer);
        frame.add(scrPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}