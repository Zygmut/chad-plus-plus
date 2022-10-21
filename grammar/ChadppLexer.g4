lexer grammar ChadppLexer;

@header {
/*
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */

package antlr;
}

/******************************************************************************
 * Notació: * Regles per a l'analitzador lèxic en MAJÚSCULES * Regles per a l'analitzador sintàctic
 * en minúscules * ****************************************************************************
 */

TRUE: 'true';
FALSE: 'false';
MAIN: 'main';
ALPHA: 'alpha';
OUTPUT: 'output';
INPUTINT: 'inputint';
INPUTBOL: 'inputbol';
CONSTANT: 'const';
RETURN: 'return';
INT: 'int';
BOOLEAN: 'bol';
TUPLE: 'tup';
VOID: 'void';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
BEGIN: 'BEGIN';
LOOP: 'loop';
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
ID: [a-zA-Z][a-zA-Z_0-9]*;
NUMBER:
	'0'
	| [1-9][0-9]*; // Number literal signs must be handled in parser
/*
 * approach from a 2012 blog, probably for ANTLR 3 COMMENT : '//' ( options { greedy=false; } : .*)
 * '//' -> skip;
 */
COMMENT: '//' .*? '//' -> skip; // TODO: Is '?' needed?
WS:
	[ \t]+ -> skip; // Els espais en blanc no interessen, botar-los
ENDLINE: [\r\n]+ -> skip;
