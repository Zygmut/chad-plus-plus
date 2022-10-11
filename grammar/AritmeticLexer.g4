lexer grammar AritmeticLexer;

@header {
/*
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */

package compiladores.chadpp.compiler_components;
}

/******************************************************************************
 * Notació: *
 Regles per a l'analitzador lèxic en MAJÚSCULES *
 Regles per a l'analitzador
 * sintàctic en minúscules *
 * ****************************************************************************
 */

TRUE: 'verdadero';
FALSE: 'false';
OMEGA: 'omega';
ALPHA: 'alpha';
OTUPUT: 'output';
INPUTINT: 'inputint';
INPUTBOL: 'inputbol';
INPUTSTR: 'inputstr';
CONSTANT: 'const';
RETURN: 'return';
INT: 'int';
BOOLEAN: 'bol';
STRING: 'str';
VOID: 'nil';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
BEGIN: 'begin';

REQUAL: '==';
RNOTEQUAL: '!=';
LAND: '&&';
LOR: '||';
PLUSPLUS: '++';
MINUSMINUS: '--';
COMMA: ',';
LPAREN: '(';
RPAREN: ')';
LSKEY: '[';
RSKEY: ']';
LKEY: '{';
RKEY: '}';
SEMICOLON: ';';
EQUAL: '=';
PLUS: '+';
MINUS: '-';
MUL: '*';
RLESS: '<';
RGRE: '>';
LNOT: '!';

STRING_LIT: '"' .*? '"';
ID: [a-zA-Z_][a-zA-Z_0-9]*;
NUMBER:
	'0'
	| [1-9][0-9]*; // Number literal signs must be handled in parser
/*
 * approach from a 2012 blog, probably for ANTLR 3
 COMMENT : '//' ( options { greedy=false; } :
 * .*)
 '//' -> skip;
 */
COMMENT: '//' .*? '//' -> skip; // TODO: Is '?' needed?
WS:
	[ \t]+ -> skip; // Els espais en blanc no interessen, botar-los
ENDLINE: [\r\n]+;
