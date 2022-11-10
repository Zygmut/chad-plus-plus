/*
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */

package grammar;

import java.io.*;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import errors.ErrorHandler;
import errors.ErrorCodes;
import utils.Env;

import grammar.ParserSym;

%%

%cup

%public
%class Scanner

%eofval{
  return symbol(ParserSym.EOF);
%eofval}


ID            = [a-zA-Z][a-zA-Z_0-9]* 
NUMBER        = 0 | [\+\-]?[1-9][0-9]*
STRING_LIT    = \" .*? \"
COMMENT       = "/*" .*? "*/"
LINE_COMMENT  = "//" ~[\r\n]*
WS            = [ \t]+
ENDLINE       = [\r\n]+


%{
    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }

    /**
     Construcció d'un symbol amb un atribut associat.
     **/
    private ComplexSymbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }
%}


%%

// Rules & actions

// non-terminals
{NUMBER}        { return symbol(ParserSym.NUMBER, this.yytext()); }
{ID}            { return symbol(ParserSym.ID, this.yytext()); }

// terminals

// reserved key words
"BEGIN"         { return symbol(ParserSym.ADD);                  }
"true"          { return symbol(ParserSym.ADD);                  }
"false"         { return symbol(ParserSym.ADD);                  }
"main"          { return symbol(ParserSym.ADD);                  }
"alpha"         { return symbol(ParserSym.ADD);                  }
"const"         { return symbol(ParserSym.ADD);                  }
"return"        { return symbol(ParserSym.ADD);                  }

// types
"int"           { return symbol(ParserSym.ADD);                  }
"bol"           { return symbol(ParserSym.ADD);                  }
"tup"           { return symbol(ParserSym.ADD);                  }
"void"          { return symbol(ParserSym.ADD);                  }

// code branching
"if"            { return symbol(ParserSym.ADD);                  }
"else"          { return symbol(ParserSym.ADD);                  }
"while"         { return symbol(ParserSym.ADD);                  }
"loop"          { return symbol(ParserSym.ADD);                  }

// logic
"&&"            { return symbol(ParserSym.ADD);                  }
"||"            { return symbol(ParserSym.ADD);                  }
"!"             { return symbol(ParserSym.ADD);                  }

// arithmetic
"+"             { return symbol(ParserSym.ADD);                  }
"-"             { return symbol(ParserSym.ADD);                  }
"*"             { return symbol(ParserSym.ADD);                  }
"/"             { return symbol(ParserSym.ADD);                  }

// I/O
"output"        { return symbol(ParserSym.ADD);                  }
"inputint"      { return symbol(ParserSym.ADD);                  }
"inputbol"      { return symbol(ParserSym.ADD);                  }

//Extras
"="             { return symbol(ParserSym.ADD);                  }
";"             { return symbol(ParserSym.ADD);                  }
","             { return symbol(ParserSym.ADD);                  }
"("             { return symbol(ParserSym.ADD);                  }
")"             { return symbol(ParserSym.ADD);                  }
"{"             { return symbol(ParserSym.ADD);                  }
"}"             { return symbol(ParserSym.ADD);                  }
"["             { return symbol(ParserSym.ADD);                  }
"]"             { return symbol(ParserSym.ADD);                  }

{WS}            {                                               }
{COMMENT}       {                                               }
{LINE_COMMENT}  {                                               }
{ENDLINE}       { return symbol(ParserSym.EOF);                 }

[^]             { ErrorHandler.addError(ErrorCodes.INVALID_TOKEN, yyline, yycolumn, Env.LEXICAL_PHASE);                }
