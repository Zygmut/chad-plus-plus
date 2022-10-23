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
main: MAIN LKEY extrad? instrucciones RKEY;

// typef alpha ID '(' params? ')' '{' extrad? instr+ '}'
function:
	typef ALPHA id LPAREN params? RPAREN LKEY extrad? instrucciones RKEY;

//Lista de instrucciones en una función (o en el main)
instrucciones: instr+;

//Lista de declaraciones
extrad: decl+ BEGIN;

// Declaraciones de las variables y constantes
decl: CONSTANT? type TUPLE? asignacion;

//asignacion: listaids '=' expresion ';' ;
asignacion: listaids EQUAL expresion SEMICOLON;

//listaids: (ID ',')* ID;
listaids: (id COMMA)* id;

// Tipos de variables o funciones
typef: VOID | type;

type: INT | BOOLEAN;

// Parametros de una funcion
params: (param COMMA)* param;
//params: (param ',')* param;

param: type id;

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
	| bool
	| LPAREN expresion RPAREN
	| LNOT expresion
	| callf;

callf:
	id LPAREN args? RPAREN
	| INPUTINT LPAREN RPAREN
	| INPUTBOL LPAREN RPAREN;

op: PLUS | MINUS | MUL | LAND | LOR | REQUAL | RLESS | RGRE;

bool: TRUE | FALSE;

// Instrucciones
instr:
	WHILE LPAREN expresion RPAREN LKEY instr* RKEY
	| LOOP LPAREN expresion COMMA expresion RPAREN LKEY instr* RKEY
	| IF LPAREN expresion RPAREN LKEY instr* RKEY (
		ELSE LKEY instr* RKEY
	)?
	| callf SEMICOLON
	| OUTPUT LPAREN (STRING_LIT | expresion) (
		COMMA (
			STRING_LIT
			| expresion
		) // Mirar si concatenar string con ',' o con '+'
	)* RPAREN SEMICOLON
	| asignacion
	| RETURN expresion SEMICOLON;

id: ID;

number: NUMBER;