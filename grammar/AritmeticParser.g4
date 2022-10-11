parser grammar AritmeticParser;

options {
	tokenVocab = AritmeticLexer;
}

@header {
/*
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 * OMEGA COMPILER
 */

package antlr;
}

/******************************************************************************
 * Notació: *
 Regles per a l'analitzador lèxic en MAJÚSCULES *
 Regles per a l'analitzador
 * sintàctic en minúscules *
 * ****************************************************************************
 */

/***************************
 Regles per a l'analitzador sintàctic
 */

omega: function* OMEGA '{' extrad? instr* '}';

function: typef ALPHA ID '(' params? ')' '{' extrad? instr* '}';

extrad: decl+ BEGIN;

// Declaraciones de las variables y constantes
decl: CONSTANT? type asignacion;

asignacion: listaids '=' expresion SEMICOLON;

listaids: (ID ',')* ID;

// Tipos de variables o funciones
typef: VOID | type;

type: INT | BOOLEAN | STRING;

// Parametros de una funcion
params: (param ',')* param;

param: type ID;

// Parametros de una funcion al llamarla en el programa

args: (expresion ',')* expresion;

// Gestion de expresiones
expresion: (cont_expresion op)* cont_expresion;

cont_expresion:
	NUMBER
	| STRING_LIT
	| bool
	| '(' expresion ')'
	| LNOT expresion
	| ID post?
	| callf;

callf:
	ID '(' args? ')'
	| INPUTINT '(' ')'
	| INPUTBOL '(' ')'
	| INPUTSTR '(' ')';

op:
	PLUS
	| MINUS
	| MUL
	| LAND
	| LOR
	| REQUAL
	| RNOTEQUAL
	| RLESS
	| RGRE;

post: PLUSPLUS | MINUSMINUS;
bool: TRUE | FALSE;

// Instrucciones
instr:
	WHILE '(' expresion ')' '{' instr* '}'
	| IF '(' expresion ')' '{' instr* '}' (ELSE '{' instr* '}')?
	| callf SEMICOLON
	| OTUPUT '(' expresion ')' SEMICOLON
	| asignacion
	| ID post? SEMICOLON
	| RETURN expresion SEMICOLON;
