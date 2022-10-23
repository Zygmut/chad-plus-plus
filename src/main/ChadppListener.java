package main;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.*;
import antlr.ChadppParser.*; // imports all Rule-Contexts

public class ChadppListener extends ChadppParserBaseListener {

    private final ChadppParser parser;
    // private TableId tableId;

    public ChadppListener(ChadppParser parser) {
        this.parser = parser;
    }

    // Crear una clase para control de errores??
    // public ChadppErrors init() {
    public void init() {
        // Creación de Tablas ID's
        // Toda las palabras reservadas y mierdas
        ChadppParser.ChadppContext localContext = parser.chadpp();
        this.enterChadpp(localContext);
        this.exitChadpp(localContext);
    }

    @Override
    public void enterChadpp(ChadppContext ctx) {
        System.out.println("HOLA");

        // procesar main
        MainContext mainContext = ctx.main();
        this.enterMain(mainContext);
        this.exitMain(mainContext);

        // procesar funciones
        ctx.function().forEach(function -> {
            this.enterFunction(function);
            this.exitFunction(function);
        });

        // super.enterChadpp(ctx);
    }

    @Override
    public void exitChadpp(ChadppContext ctx) {
        // Checkear reminders
        // comprobar si han habido llamadas de funciones no declaradas
        // comprobar si había llamada de variables globales no declaradas

        System.out.println("ADIOS");
        super.exitChadpp(ctx);
    }

    @Override
    public void enterFunction(FunctionContext ctx) {

        super.enterFunction(ctx);
    }

    @Override
    public void exitFunction(FunctionContext ctx) {
        String id = ctx.id().ID().getText();

        System.out.println("Función: " + id);
        System.out.println("\tsus hijos:\n" + ctx.children.toString() + "\n");
        super.exitFunction(ctx);
    }

    @Override
    public void enterArgs(ArgsContext ctx) {
        super.enterArgs(ctx);
    }

    @Override
    public void enterAsignacion(AsignacionContext ctx) {
        super.enterAsignacion(ctx);
    }

    @Override
    public void enterBool(BoolContext ctx) {
        super.enterBool(ctx);
    }

    @Override
    public void enterCallf(CallfContext ctx) {
        super.enterCallf(ctx);
    }

    @Override
    public void enterCont_expresion(Cont_expresionContext ctx) {
        super.enterCont_expresion(ctx);
    }

    @Override
    public void enterDecl(DeclContext ctx) {
        super.enterDecl(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void enterExpresion(ExpresionContext ctx) {
        super.enterExpresion(ctx);
    }

    @Override
    public void enterExtrad(ExtradContext ctx) {
        ctx.decl().forEach(decl -> {
            this.enterDecl(decl);
            this.exitDecl(decl);
        });
        super.enterExtrad(ctx);
    }

    @Override
    public void enterInstr(InstrContext ctx) {
        super.enterInstr(ctx);
    }

    @Override
    public void enterInstrucciones(InstruccionesContext ctx) {
        super.enterInstrucciones(ctx);
    }

    @Override
    public void enterListaids(ListaidsContext ctx) {
        super.enterListaids(ctx);
    }

    @Override
    public void enterMain(MainContext ctx) {
        System.out.println("Entramos al Main");

        if (ctx.extrad().decl().size() != 0) {
            // Tratamiento de declaraciones
            this.enterExtrad(ctx.extrad());
            this.exitExtrad(ctx.extrad());
        }

        ctx.instrucciones();
    }

    @Override
    public void enterOp(OpContext ctx) {
        super.enterOp(ctx);
    }

    @Override
    public void enterParam(ParamContext ctx) {
        super.enterParam(ctx);
    }

    @Override
    public void enterParams(ParamsContext ctx) {
        super.enterParams(ctx);
    }

    @Override
    public void enterTuple(TupleContext ctx) {
        super.enterTuple(ctx);
    }

    @Override
    public void enterTuple_decl(Tuple_declContext ctx) {
        super.enterTuple_decl(ctx);
    }

    @Override
    public void enterType(TypeContext ctx) {
        super.enterType(ctx);
    }

    @Override
    public void enterTypef(TypefContext ctx) {
        super.enterTypef(ctx);
    }

    @Override
    public void exitArgs(ArgsContext ctx) {
        super.exitArgs(ctx);
    }

    @Override
    public void exitAsignacion(AsignacionContext ctx) {
        super.exitAsignacion(ctx);
    }

    @Override
    public void exitBool(BoolContext ctx) {
        super.exitBool(ctx);
    }

    @Override
    public void exitCallf(CallfContext ctx) {
        super.exitCallf(ctx);
    }

    @Override
    public void exitCont_expresion(Cont_expresionContext ctx) {
        super.exitCont_expresion(ctx);
    }

    @Override
    public void exitDecl(DeclContext ctx) {
        super.exitDecl(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void exitExpresion(ExpresionContext ctx) {
        super.exitExpresion(ctx);
    }

    @Override
    public void exitExtrad(ExtradContext ctx) {
        super.exitExtrad(ctx);
    }

    @Override
    public void exitInstr(InstrContext ctx) {
        super.exitInstr(ctx);
    }

    @Override
    public void exitInstrucciones(InstruccionesContext ctx) {
        super.exitInstrucciones(ctx);
    }

    @Override
    public void exitListaids(ListaidsContext ctx) {
        super.exitListaids(ctx);
    }

    @Override
    public void exitMain(MainContext ctx) {
        System.out.println("Salimos del Main");
        super.exitMain(ctx);
    }

    @Override
    public void exitOp(OpContext ctx) {
        super.exitOp(ctx);
    }

    @Override
    public void exitParam(ParamContext ctx) {
        super.exitParam(ctx);
    }

    @Override
    public void exitParams(ParamsContext ctx) {
        super.exitParams(ctx);
    }

    @Override
    public void exitTuple(TupleContext ctx) {
        super.exitTuple(ctx);
    }

    @Override
    public void exitTuple_decl(Tuple_declContext ctx) {
        super.exitTuple_decl(ctx);
    }

    @Override
    public void exitType(TypeContext ctx) {
        super.exitType(ctx);
    }

    @Override
    public void exitTypef(TypefContext ctx) {
        super.exitTypef(ctx);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
