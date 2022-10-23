package main;

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

    }

    @Override
    public void exitChadpp(ChadppContext ctx) {
        // Checkear reminders
        // comprobar si han habido llamadas de funciones no declaradas
        // comprobar si había llamada de variables globales no declaradas

        System.out.println("ADIOS");
        // super.exitChadpp(ctx);
    }

    @Override
    public void enterMain(MainContext ctx) {
        System.out.println("Entramos al Main");

        if (ctx.extrad() != null) {
            // Tratamiento de declaraciones
            this.enterExtrad(ctx.extrad());
            this.exitExtrad(ctx.extrad());
        }

        System.out.println("\tInstrucciones:");
        ctx.instrucciones().instr().forEach((instr) -> {
            this.enterInstr(instr);
            this.exitInstr(instr);
        });

        ctx.instrucciones();
    }

    @Override
    public void exitMain(MainContext ctx) {
        System.out.println("Salimos del Main");
    }

    @Override
    public void enterFunction(FunctionContext ctx) {
        // String function_name = ctx.id().ID().getText();
        String function_name = ctx.id().getText();
        String function_type = ctx.typef().getText();
        System.out.println(function_type + " " + function_name);
        if (ctx.params() != null) {
            System.out.println("\tParámetros:");
            this.enterParams(ctx.params());
            this.exitParams(ctx.params());
        }

        if (ctx.extrad() != null) {
            // Tratamiento de declaraciones
            // Nuevo ámbito en la tabla de símbolos
            this.enterExtrad(ctx.extrad());
            this.exitExtrad(ctx.extrad());
        }

        System.out.println("\tInstrucciones:");
        ctx.instrucciones().instr().forEach((instr) -> {
            this.enterInstr(instr);
            this.exitInstr(instr);
        });
        System.out.println();

        super.enterFunction(ctx);
    }

    @Override
    public void exitFunction(FunctionContext ctx) {
        // super.exitFunction(ctx);
    }

    @Override
    public void enterParams(ParamsContext ctx) {
        // FunctionContext funct = (FunctionContext) ctx.parent;
        // contexto del padre (Función)

        ctx.param().forEach((param) -> {
            System.out.println("\t\t" + param.type().getText() + " " + param.id().getText());
            // añadir el parámetro a su función padre
            // funcion "funct".add(param)
        });
    }

    @Override
    public void exitParams(ParamsContext ctx) {
        System.out.println();
    }

    @Override
    public void enterExtrad(ExtradContext ctx) {
        System.out.println("\tDeclaraciones:");
        ctx.decl().forEach(decl -> {
            this.enterDecl(decl);
            this.exitDecl(decl);
        });
        super.enterExtrad(ctx);
    }

    @Override
    public void exitExtrad(ExtradContext ctx) {
        System.out.println();
    }

    @Override
    public void enterDecl(DeclContext ctx) {
        String type = ctx.type().getText();
        boolean constant;
        if (ctx.CONSTANT() != null) {
            constant = true;
        } else {
            constant = false;
        }

        if (ctx.TUPLE() == null) {
            // System.out.println("\t\tNo es tupla");
        } else {
            // System.out.println("si es tupla");
        }

        ctx.asignacion().listaids().id().forEach((id) -> {
            System.out.println("\t\ttype: " + type);
            System.out.println("\t\tconstant: " + constant);
            System.out.println("\t\tid: " + id.getText());
            System.out.println("\t\tvalue: " + ctx.asignacion().expresion().getText() + "\n");
        });
    }

    @Override
    public void exitDecl(DeclContext ctx) {
        // super.exitDecl(ctx);
    }

    @Override
    public void enterInstr(InstrContext ctx) {
        /*
         * Opciones:
         * - While
         * - Loop
         * - If /else
         * - Callf
         * - Output
         * - Asignacion
         * - Return
         * 
         * esta parte va a ser muy mala de hacer si se deja así
         * 
         * posible idea:
         * - Modificar la gramática añadiendo marcadores (contra, ni puta idea de como
         * hacerlo)
         * EJ:
         * .M1 WHILE ... RKEY
         * | .M2 LOOP ... RKEY
         * | ...
         * 
         * - Modificar la gramática añadiendo producciones intermedias (como id -> ID)
         */

        System.out.println("\t\t" + ctx.getText());
    }

    @Override
    public void exitInstr(InstrContext ctx) {
        // super.exitInstr(ctx);
    }

}
