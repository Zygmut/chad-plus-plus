package main;

import java.util.List;

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
            this.enterExtrad(ctx.extrad());
            this.exitExtrad(ctx.extrad());
        }

        System.out.println("\tInstrucciones:");

        ctx.instr().forEach((instr) -> {
            String contx = getProdInstr(instr);
            System.out.println(contx);
            enterInstr(instr, contx);
        });
    }

    @Override
    public void exitMain(MainContext ctx) {
        System.out.println("Salimos del Main\n");
    }

    @Override
    public void enterFunction(FunctionContext ctx) {
        String function_name = ctx.id().getText();
        String function_type = ctx.typef().getText();
        System.out.println(function_type + " " + function_name);
        if (ctx.params() != null) {
            System.out.println("\tParámetros:");
            this.enterParams(ctx.params());
            this.exitParams(ctx.params());
        }

        if (ctx.extrad() != null) {
            this.enterExtrad(ctx.extrad());
            this.exitExtrad(ctx.extrad());
        }

        System.out.println("\tInstrucciones:");
        ctx.instr().forEach((instr) -> {
            String contx = getProdInstr(instr);
            System.out.println(contx);
            enterInstr(instr, contx);
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

    // **********************| GESTIÓN DE INSTRUCCIONES|***************************

    @Override
    public void enterII(IIContext ctx) {

        List<OpContext> listOp = ctx.expresion().op();
        List<Cont_expresionContext> listcontExpr = ctx.expresion().cont_expresion();

        System.out.println("IF ANALISY");
        System.out.print("Condición:\n\t");
        int indx = 0;
        while (indx < listOp.size()) {
            System.out.print(" " + listcontExpr.get(indx).getText() + " " + listOp.get(indx).getText());
            indx++;
        }
        System.out.println(" " + listcontExpr.get(indx).getText());

        // TO DO: DIFERENCIAR LAS INSTRUCCIONES QUE SON DEL 'THEN' Y DEL 'ELSE'
        System.out.println("Instrucciones THEN");
        ctx.instr().forEach((instr) -> {
            String instrClass = getProdInstr(instr);
            System.out.println("\t" + instrClass);
        });
    }

    @Override
    public void enterIL(ILContext ctx) {
    }

    @Override
    public void enterIO(IOContext ctx) {
    }

    @Override
    public void enterIR(IRContext ctx) {
    }

    @Override
    public void enterIW(IWContext ctx) {
    }

    @Override
    public void enterIA(IAContext ctx) {
    }

    @Override
    public void enterIC(ICContext ctx) {
    }

    // *************************| Funciones Auxiliares |***************************
    private String getProdInstr(InstrContext instr) {
        String tmp = instr.getClass().getName().toString();
        return tmp.substring(tmp.indexOf("$") + 1);
    }

    private void enterInstr(InstrContext instr, String contx) {
        switch (contx) {
            case "ICContext":
                enterIC((ChadppParser.ICContext) instr);
                break;

            case "ILContext":
                enterIL((ChadppParser.ILContext) instr);
                break;

            case "IIContext":
                enterII((ChadppParser.IIContext) instr);
                break;

            case "IRContext":
                enterIR((ChadppParser.IRContext) instr);
                break;
            case "IOContext":
                enterIO((ChadppParser.IOContext) instr);
                break;

            case "IAContext":
                enterIA((ChadppParser.IAContext) instr);
                break;

            case "IWContext":
                enterIW((ChadppParser.IWContext) instr);
                break;

            default:
                System.out.println("F: Erorr en el Listener");
                break;
        }
    }

}
