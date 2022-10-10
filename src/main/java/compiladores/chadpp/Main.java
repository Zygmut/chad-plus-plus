package compiladores.chadpp;

import org.antlr.runtime.Token;
import org.antlr.v4.parse.GrammarToken;

/**
 *
 * Main class of the project
 */
public class Main {

    public static void main(String[] args) {
        var l = new GrammarToken(null, Token.SKIP_TOKEN);
        System.out.println(l);
        System.out.println("Hello World!");
    }
}
