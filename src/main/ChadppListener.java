package main;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.*;
import antlr.ChadppParser.*; // imports all Rule-Contexts

public class ChadppListener extends ChadppParserBaseListener {

    @Override
    public void exitFunction(FunctionContext ctx) {
        String id = ctx.ID().getText();

        System.out.println("Función: " + id);
        System.out.println("\tsus hijos:\n" + ctx.children.toString() + "\n");

        super.exitFunction(ctx);
    }

    @Override
    public void enterArgs(ArgsContext ctx) {
        // TODO Auto-generated method stub
        super.enterArgs(ctx);
    }

    @Override
    public void enterAsignacion(AsignacionContext ctx) {
        // TODO Auto-generated method stub
        super.enterAsignacion(ctx);
    }

    @Override
    public void enterBool(BoolContext ctx) {
        // TODO Auto-generated method stub
        super.enterBool(ctx);
    }

    @Override
    public void enterCallf(CallfContext ctx) {
        // TODO Auto-generated method stub
        super.enterCallf(ctx);
    }

    @Override
    public void enterChadpp(ChadppContext ctx) {
        System.out.println("HOLA");
        super.enterChadpp(ctx);
    }

    @Override
    public void enterCont_expresion(Cont_expresionContext ctx) {
        // TODO Auto-generated method stub
        super.enterCont_expresion(ctx);
    }

    @Override
    public void enterDecl(DeclContext ctx) {
        // TODO Auto-generated method stub
        super.enterDecl(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        // TODO Auto-generated method stub
        super.enterEveryRule(ctx);
    }

    @Override
    public void enterExpresion(ExpresionContext ctx) {
        // TODO Auto-generated method stub
        super.enterExpresion(ctx);
    }

    @Override
    public void enterExtrad(ExtradContext ctx) {
        // TODO Auto-generated method stub
        super.enterExtrad(ctx);
    }

    @Override
    public void enterFunction(FunctionContext ctx) {
        // TODO Auto-generated method stub
        super.enterFunction(ctx);
    }

    @Override
    public void enterInstr(InstrContext ctx) {
        // TODO Auto-generated method stub
        super.enterInstr(ctx);
    }

    @Override
    public void enterInstrucciones(InstruccionesContext ctx) {
        // TODO Auto-generated method stub
        super.enterInstrucciones(ctx);
    }

    @Override
    public void enterListaids(ListaidsContext ctx) {
        // TODO Auto-generated method stub
        super.enterListaids(ctx);
    }

    @Override
    public void enterMain(MainContext ctx) {
        // TODO Auto-generated method stub
        super.enterMain(ctx);
    }

    @Override
    public void enterOp(OpContext ctx) {
        // TODO Auto-generated method stub
        super.enterOp(ctx);
    }

    @Override
    public void enterParam(ParamContext ctx) {
        // TODO Auto-generated method stub
        super.enterParam(ctx);
    }

    @Override
    public void enterParams(ParamsContext ctx) {
        // TODO Auto-generated method stub
        super.enterParams(ctx);
    }

    @Override
    public void enterPost(PostContext ctx) {
        // TODO Auto-generated method stub
        super.enterPost(ctx);
    }

    @Override
    public void enterTuple(TupleContext ctx) {
        // TODO Auto-generated method stub
        super.enterTuple(ctx);
    }

    @Override
    public void enterTuple_decl(Tuple_declContext ctx) {
        // TODO Auto-generated method stub
        super.enterTuple_decl(ctx);
    }

    @Override
    public void enterType(TypeContext ctx) {
        // TODO Auto-generated method stub
        super.enterType(ctx);
    }

    @Override
    public void enterTypef(TypefContext ctx) {
        // TODO Auto-generated method stub
        super.enterTypef(ctx);
    }

    @Override
    public void exitArgs(ArgsContext ctx) {
        // TODO Auto-generated method stub
        super.exitArgs(ctx);
    }

    @Override
    public void exitAsignacion(AsignacionContext ctx) {
        // TODO Auto-generated method stub
        super.exitAsignacion(ctx);
    }

    @Override
    public void exitBool(BoolContext ctx) {
        // TODO Auto-generated method stub
        super.exitBool(ctx);
    }

    @Override
    public void exitCallf(CallfContext ctx) {
        // TODO Auto-generated method stub
        super.exitCallf(ctx);
    }

    @Override
    public void exitChadpp(ChadppContext ctx) {
        // TODO Auto-generated method stub
        super.exitChadpp(ctx);
    }

    @Override
    public void exitCont_expresion(Cont_expresionContext ctx) {
        // TODO Auto-generated method stub
        super.exitCont_expresion(ctx);
    }

    @Override
    public void exitDecl(DeclContext ctx) {
        // TODO Auto-generated method stub
        super.exitDecl(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        // TODO Auto-generated method stub
        super.exitEveryRule(ctx);
    }

    @Override
    public void exitExpresion(ExpresionContext ctx) {
        // TODO Auto-generated method stub
        super.exitExpresion(ctx);
    }

    @Override
    public void exitExtrad(ExtradContext ctx) {
        // TODO Auto-generated method stub
        super.exitExtrad(ctx);
    }

    @Override
    public void exitInstr(InstrContext ctx) {
        // TODO Auto-generated method stub
        super.exitInstr(ctx);
    }

    @Override
    public void exitInstrucciones(InstruccionesContext ctx) {
        // TODO Auto-generated method stub
        super.exitInstrucciones(ctx);
    }

    @Override
    public void exitListaids(ListaidsContext ctx) {
        // TODO Auto-generated method stub
        super.exitListaids(ctx);
    }

    @Override
    public void exitMain(MainContext ctx) {
        // TODO Auto-generated method stub
        super.exitMain(ctx);
    }

    @Override
    public void exitOp(OpContext ctx) {
        // TODO Auto-generated method stub
        super.exitOp(ctx);
    }

    @Override
    public void exitParam(ParamContext ctx) {
        // TODO Auto-generated method stub
        super.exitParam(ctx);
    }

    @Override
    public void exitParams(ParamsContext ctx) {
        // TODO Auto-generated method stub
        super.exitParams(ctx);
    }

    @Override
    public void exitPost(PostContext ctx) {
        // TODO Auto-generated method stub
        super.exitPost(ctx);
    }

    @Override
    public void exitTuple(TupleContext ctx) {
        // TODO Auto-generated method stub
        super.exitTuple(ctx);
    }

    @Override
    public void exitTuple_decl(Tuple_declContext ctx) {
        // TODO Auto-generated method stub
        super.exitTuple_decl(ctx);
    }

    @Override
    public void exitType(TypeContext ctx) {
        // TODO Auto-generated method stub
        super.exitType(ctx);
    }

    @Override
    public void exitTypef(TypefContext ctx) {
        // TODO Auto-generated method stub
        super.exitTypef(ctx);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        // TODO Auto-generated method stub
        super.visitErrorNode(node);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        // TODO Auto-generated method stub
        super.visitTerminal(node);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
