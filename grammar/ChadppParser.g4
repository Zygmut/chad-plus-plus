parser grammar ChadppParser;

options {
	tokenVocab = ChadppLexer;
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
 * Notació: * Regles per a l'analitzador lèxic en MAJÚSCULES * Regles per a l'analitzador sintàctic
 * en minúscules * ****************************************************************************
 */

/***************************
 Regles per a l'analitzador sintàctic
 */

chadpp: function* main;

//main '{' extrad? instr+ '}'
main: MAIN LKEY extrad? instr+ RKEY;

// typef alpha ID '(' params? ')' '{' extrad? instr+ '}'
function:
	typefn ALPHA id LPAREN params? RPAREN LKEY extrad? instr+ RKEY;

//Lista de declaraciones
extrad: decl+ BEGIN;

// Declaraciones de las variables y constantes
decl: CONSTANT? typevar TUPLE? asignacion;

//asignacion: listaids '=' expresion ';' ;
asignacion: listaids EQUAL expresion SEMICOLON;

//listaids: (ID ',')* ID;
listaids: (id COMMA)* id;

// Tipos de variables o funciones
typefn: VOID | typevar;

typevar: INT | BOOLEAN;

// Parametros de una funcion
params: (param COMMA)* param;
//params: (param ',')* param;

param: typevar id;

// Parametros de una funcion al llamarla en el programa
args: (expresion COMMA)* expresion;
//args: (expresion ',')* expresion;

// Gestion de expresiones
expresion: (cont_expresion op)* cont_expresion;

tuple_decl: LSKEY expresion (COMMA expresion)* RSKEY;

tuple: id LSKEY number RSKEY;

cont_expresion:
	number
	| id
	| tuple
	| bol
	| LPAREN expresion RPAREN
	| LNOT expresion
	| callf;

callf:
	id LPAREN args? RPAREN
	| INPUTINT LPAREN RPAREN
	| INPUTBOL LPAREN RPAREN;

op: PLUS | MINUS | MUL | LAND | LOR | REQUAL | RLESS | RGRE;

bol: TRUE | FALSE;

instr:
	WHILE LPAREN expresion RPAREN LKEY instr* RKEY					# IW
	| LOOP LPAREN expresion COMMA expresion RPAREN LKEY instr* RKEY	# IL
	| IF LPAREN expresion RPAREN LKEY instr* RKEY (
		ELSE LKEY instr* RKEY
	)?								# II
	| RETURN expresion SEMICOLON	# IR
	| callf SEMICOLON				# IC
	| OUTPUT LPAREN (STRING_LIT | expresion) (
		COMMA ( STRING_LIT | expresion)
	)* RPAREN SEMICOLON	# IO
	| asignacion		# IA;

id: ID;
number: NUMBER;