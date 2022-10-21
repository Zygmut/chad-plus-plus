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

public class Main {

    private static final boolean VISUALIZATION = true;

    public static void main(String[] args) {
        System.out.println("Chad++ Compiler");
        try {
            InputStream inputStream = new FileInputStream("./tests/example1.txt");
            Lexer lexer = new ChadppLexer(CharStreams.fromStream(inputStream));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            ChadppParser parser = new ChadppParser(tokenStream);

            if (VISUALIZATION) {
                guiTreeVisualization(parser);
            } else {
                ChadppParser.ChadppContext chadppContext = parser.chadpp();
                ChadppVisitor visitor = new ChadppVisitor();
                visitor.visit(chadppContext);

            }

        } catch (IOException e) {

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
