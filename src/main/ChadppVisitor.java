package main;

import org.antlr.runtime.tree.ParseTree;

import antlr.*;
import antlr.ChadppParser.*; // imports all Rule-Contexts

public class ChadppVisitor extends ChadppParserBaseVisitor {

    @Override
    public Object visitChadpp(ChadppContext ctx) {
        System.out.println("Visitamos CHADPP");
        return visitChildren(ctx);

        // return super.visitChadpp(ctx);
    }

    @Override
    public Object visitFunction(FunctionContext ctx) {
        System.out.println("Visitamos función: " + ctx.ID().getText());
        return super.visitFunction(ctx);
    }

    @Override
    public Object visitMain(MainContext ctx) {
        System.out.println("Visitamos el Main");
        return super.visitMain(ctx);
    }
}
