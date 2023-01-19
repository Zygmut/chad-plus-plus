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
import errors.ErrorCode;
import utils.Phase;
import java.util.ArrayList;

import grammar.ParserSym;

%%

%cup
%public
%class Scanner

%integer
%line
%column

%eofval{
  return symbol(ParserSym.EOF);
%eofval}


ID            = [a-zA-Z][a-zA-Z_0-9]*
NUMBER        = 0 | [\+\-]?[1-9][0-9]*
// STRING_LIT    = \" .*? \"               // Optional
COMMENT       = "$" [^*] ~"$" | "$" "$"
LINE_COMMENT  = "#" [^\r\n]* [\r|\n|\r\n]?
WS            = [ \t]+
ENDLINE       = [\r\n]+


%{
    public ArrayList<ComplexSymbol> tokens = new ArrayList<>();

    /*
     * Create ComplexSymbol without attribute
     */
    private ComplexSymbol symbol(int type) {
        ComplexSymbol cs = new ComplexSymbol(ParserSym.terminalNames[type], type);
        cs.left = yyline + 1;
        cs.right = yycolumn;
        tokens.add(cs);
        return cs;
    }

    /*
     * Create ComplexSymbol with attribute
     */
    private ComplexSymbol symbol(int type, Object value) {
        ComplexSymbol cs = new ComplexSymbol(ParserSym.terminalNames[type], type, value);
        cs.left = yyline + 1;
        cs.right = yycolumn;
        tokens.add(cs);
        return cs;
    }

    private void error(){
        ErrorHandler.addError(ErrorCode.INVALID_TOKEN, yyline + 1, yycolumn, Phase.LEXICAL);
    }
%}


%%

// Rules & actions
// terminals

// reserved key words
"BEGIN"         { return symbol(ParserSym.BEGIN);                 }
"main"          { return symbol(ParserSym.MAIN);                  }
"alpha"         { return symbol(ParserSym.ALPHA);                 }
"const"         { return symbol(ParserSym.CONST);                 }
"return"        { return symbol(ParserSym.RETURN);                }
"true"          { return symbol(ParserSym.VBOL, "true");          }
"false"         { return symbol(ParserSym.VBOL, "false");         }

// types
"int"           { return symbol(ParserSym.INT);                   }
"bol"           { return symbol(ParserSym.BOL);                   }
"tup"           { return symbol(ParserSym.TUP);                   }
"void"          { return symbol(ParserSym.VOID);                  }

// code branching
"if"            { return symbol(ParserSym.IF);                    }
// optional "else"          { return symbol(ParserSym.ELSE);                  }
"while"         { return symbol(ParserSym.WHILE);                 }
"loop"          { return symbol(ParserSym.LOOP);                  }

// logic 2
"&&"            { return symbol(ParserSym.AND);                   }
"||"            { return symbol(ParserSym.OR);                    }
// "!"             { return symbol(ParserSym.NOT);                   }

// relational 2
"=="            { return symbol(ParserSym.REQUAL);                }
"<"             { return symbol(ParserSym.LT);                    }
">"             { return symbol(ParserSym.GT);                    }

// arithmetic 2
"+"             { return symbol(ParserSym.PLUS);                  }
"-"             { return symbol(ParserSym.MINUS);                 }
"*"             { return symbol(ParserSym.MULT);                  }
"/"             { return symbol(ParserSym.DIV);                   }

// I/O
"output"        { return symbol(ParserSym.OUT);                   }
"inputint"      { return symbol(ParserSym.ININT);                 }
"inputbol"      { return symbol(ParserSym.INBOL);                 }

// extras
"="             { return symbol(ParserSym.EQUAL);                 }
";"             { return symbol(ParserSym.SEMICOLON);             }
","             { return symbol(ParserSym.COMMA);                 }
"("             { return symbol(ParserSym.LPAREN);                }
")"             { return symbol(ParserSym.RPAREN);                }
"{"             { return symbol(ParserSym.LKEY);                  }
"}"             { return symbol(ParserSym.RKEY);                  }
"["             { return symbol(ParserSym.LSKEY);                 }
"]"             { return symbol(ParserSym.RSKEY);                 }

// non-terminals
{NUMBER}        { return symbol(ParserSym.NUMBER, this.yytext()); }
{ID}            { return symbol(ParserSym.ID, this.yytext());     }

{WS}            {                                                 }
{COMMENT}       {                                                 }
{LINE_COMMENT}  {                                                 }
{ENDLINE}       {                                                 }

[^]             { error();                                        }
