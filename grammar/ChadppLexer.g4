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

// Logic
LAND: '&&';
LOR: '||';
LNOT: '!';

// Relational
REQUAL: '==';
RLESS: '<';
RGRE: '>';

// Arithmetic
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';

// I/O
OUTPUT: 'output';
INPUTINT: 'inputint';
INPUTBOL: 'inputbol';

COMMA: ',';
LPAREN: '(';
RPAREN: ')';
LSKEY: '[';
RSKEY: ']';
LKEY: '{';
RKEY: '}';
SEMICOLON: ';';
EQUAL: '=';

STRING_LIT: '"' .*? '"';
ID: [a-zA-Z][a-zA-Z_0-9]*;
NUMBER:
	'0'
	| [1-9][0-9]*; // Number literal signs must be handled in parser

COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
WS:
	[ \t]+ -> skip; // Els espais en blanc no interessen, botar-los
ENDLINE: [\r\n]+ -> skip;
