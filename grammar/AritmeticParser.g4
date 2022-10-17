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

main: function* MAIN LKEY extrad? instr+ RKEY;

function:
	typef ALPHA ID LPAREN params? RPAREN LKEY extrad? instr+ RKEY;

extrad: decl+ BEGIN;

// Declaraciones de las variables y constantes
decl: CONSTANT? type asignacion;

//asignacion: listaids '=' expresion SEMICOLON;
asignacion: listaids EQUAL expresion SEMICOLON;

//listaids: (ID ',')* ID;
listaids: (ID COMMA)* ID;

// Tipos de variables o funciones
typef: VOID | type;

type: INT | BOOLEAN;

// Parametros de una funcion
params: (param COMMA)* param;
//params: (param ',')* param;

param: type ID;

// Parametros de una funcion al llamarla en el programa
args: (expresion COMMA)* expresion;
//args: (expresion ',')* expresion;

// Gestion de expresiones
expresion: (cont_expresion op)* cont_expresion;

tuple: LSKEY expresion (COMMA expresion)* RSKEY;

cont_expresion:
	NUMBER
	| tuple
	| bool
	| LPAREN expresion RPAREN
	| LNOT expresion
	| ID post?
	| callf;

callf:
	ID LPAREN args? RPAREN
	| INPUTINT LPAREN RPAREN
	| INPUTBOL LPAREN RPAREN;

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
	| ID post? SEMICOLON
	| RETURN expresion SEMICOLON;
