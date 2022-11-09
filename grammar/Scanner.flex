/**
  Per poder compilar aquest fitxer s'ha d'haver instal·lat JFlex
 **/ 

/**
 * Assignatura 21742 - Compiladors I 
 * Estudis: Grau en Informàtica 
 * Itinerari: Computació 
 * Curs: 2017-2018
 *
 * Professor: Pere Palmer
 */
// El codi que es copiarà tal qual al document. A l'inici
package grammar;

import java.io.*;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import grammar.ParserSym;

%%

/****
 Indicació de quin tipus d'analitzador sintàctic s'utilitzarà. En aquest cas 
 es fa ús de Java CUP.
 ****/
%cup
/****
La línia anterior és una alternativa a la indicació element a element:

%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol

****/

%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

// Declaracions

digit     = [0-9]
digits    = {digit}+

WS        = [ \t]+
ENDLINE   = [\r\n]+


// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res
// ha de ser en el format adient: mètodes, atributs, etc. 
%{
    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/
    private Complex symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }
    
    /**
     Construcció d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }
%}


%%

// Regles/accions

{digits} { return symbol(ParserSym.valor, this.yytext()); }
"+"      { return symbol(ParserSym.ADD);                  }
"-"      { return symbol(ParserSym.SUB);                  }
"*"      { return symbol(ParserSym.MUL);                  }
"/"      { return symbol(ParserSym.DIV);                  }
"%"      { return symbol(ParserSym.MOD);                  }
"("      { return symbol(ParserSym.LPAREN);               }
")"      { return symbol(ParserSym.RPAREN);               }

{WS}     { /* no fer tres */ }
{ENDLINE} { return symbol(ParserSym.EOF);                 }

[^]      { return symbol(ParserSym.error);                }
