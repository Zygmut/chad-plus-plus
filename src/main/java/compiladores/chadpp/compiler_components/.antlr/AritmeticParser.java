// Generated from c:\Users\ruben\Documents\Github\chad-plus-plus\src\main\java\compiladores\chadpp\compiler_components\AritmeticParser.g4 by ANTLR 4.9.2

/*
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 *
 * OMEGA COMPILER
 */

package compiladores.chadpp.compiler_components;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AritmeticParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE=1, FALSE=2, OMEGA=3, ALPHA=4, OTUPUT=5, INPUTINT=6, INPUTBOL=7, INPUTSTR=8, 
		CONSTANT=9, RETURN=10, INT=11, BOOLEAN=12, STRING=13, VOID=14, IF=15, 
		ELSE=16, WHILE=17, BEGIN=18, REQUAL=19, RNOTEQUAL=20, LAND=21, LOR=22, 
		PLUSPLUS=23, MINUSMINUS=24, COMMA=25, LPAREN=26, RPAREN=27, LSKEY=28, 
		RSKEY=29, LKEY=30, RKEY=31, SEMICOLON=32, EQUAL=33, PLUS=34, MINUS=35, 
		MUL=36, RLESS=37, RGRE=38, LNOT=39, STRING_LIT=40, ID=41, NUMBER=42, COMMENT=43, 
		WS=44, ENDLINE=45;
	public static final int
		RULE_omega = 0, RULE_function = 1, RULE_extrad = 2, RULE_decl = 3, RULE_asignacion = 4, 
		RULE_listaids = 5, RULE_typef = 6, RULE_type = 7, RULE_params = 8, RULE_param = 9, 
		RULE_args = 10, RULE_expresion = 11, RULE_cont_expresion = 12, RULE_callf = 13, 
		RULE_op = 14, RULE_post = 15, RULE_bool = 16, RULE_instr = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"omega", "function", "extrad", "decl", "asignacion", "listaids", "typef", 
			"type", "params", "param", "args", "expresion", "cont_expresion", "callf", 
			"op", "post", "bool", "instr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'verdadero'", "'false'", "'omega'", "'alpha'", "'output'", "'inputint'", 
			"'inputbol'", "'inputstr'", "'const'", "'return'", "'int'", "'bol'", 
			"'str'", "'nil'", "'if'", "'else'", "'while'", "'begin'", "'=='", "'!='", 
			"'&&'", "'||'", "'++'", "'--'", "','", "'('", "')'", "'['", "']'", "'{'", 
			"'}'", "';'", "'='", "'+'", "'-'", "'*'", "'<'", "'>'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRUE", "FALSE", "OMEGA", "ALPHA", "OTUPUT", "INPUTINT", "INPUTBOL", 
			"INPUTSTR", "CONSTANT", "RETURN", "INT", "BOOLEAN", "STRING", "VOID", 
			"IF", "ELSE", "WHILE", "BEGIN", "REQUAL", "RNOTEQUAL", "LAND", "LOR", 
			"PLUSPLUS", "MINUSMINUS", "COMMA", "LPAREN", "RPAREN", "LSKEY", "RSKEY", 
			"LKEY", "RKEY", "SEMICOLON", "EQUAL", "PLUS", "MINUS", "MUL", "RLESS", 
			"RGRE", "LNOT", "STRING_LIT", "ID", "NUMBER", "COMMENT", "WS", "ENDLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AritmeticParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AritmeticParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class OmegaContext extends ParserRuleContext {
		public TerminalNode OMEGA() { return getToken(AritmeticParser.OMEGA, 0); }
		public TerminalNode LKEY() { return getToken(AritmeticParser.LKEY, 0); }
		public TerminalNode RKEY() { return getToken(AritmeticParser.RKEY, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ExtradContext extrad() {
			return getRuleContext(ExtradContext.class,0);
		}
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public OmegaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_omega; }
	}

	public final OmegaContext omega() throws RecognitionException {
		OmegaContext _localctx = new OmegaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_omega);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOLEAN) | (1L << STRING) | (1L << VOID))) != 0)) {
				{
				{
				setState(36);
				function();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			match(OMEGA);
			setState(43);
			match(LKEY);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONSTANT) | (1L << INT) | (1L << BOOLEAN) | (1L << STRING))) != 0)) {
				{
				setState(44);
				extrad();
				}
			}

			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OTUPUT) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
				{
				{
				setState(47);
				instr();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(RKEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TypefContext typef() {
			return getRuleContext(TypefContext.class,0);
		}
		public TerminalNode ALPHA() { return getToken(AritmeticParser.ALPHA, 0); }
		public TerminalNode ID() { return getToken(AritmeticParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(AritmeticParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AritmeticParser.RPAREN, 0); }
		public TerminalNode LKEY() { return getToken(AritmeticParser.LKEY, 0); }
		public TerminalNode RKEY() { return getToken(AritmeticParser.RKEY, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ExtradContext extrad() {
			return getRuleContext(ExtradContext.class,0);
		}
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			typef();
			setState(56);
			match(ALPHA);
			setState(57);
			match(ID);
			setState(58);
			match(LPAREN);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOLEAN) | (1L << STRING))) != 0)) {
				{
				setState(59);
				params();
				}
			}

			setState(62);
			match(RPAREN);
			setState(63);
			match(LKEY);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONSTANT) | (1L << INT) | (1L << BOOLEAN) | (1L << STRING))) != 0)) {
				{
				setState(64);
				extrad();
				}
			}

			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OTUPUT) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
				{
				{
				setState(67);
				instr();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			match(RKEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtradContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(AritmeticParser.BEGIN, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ExtradContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extrad; }
	}

	public final ExtradContext extrad() throws RecognitionException {
		ExtradContext _localctx = new ExtradContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_extrad);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				decl();
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONSTANT) | (1L << INT) | (1L << BOOLEAN) | (1L << STRING))) != 0) );
			setState(80);
			match(BEGIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public TerminalNode CONSTANT() { return getToken(AritmeticParser.CONSTANT, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONSTANT) {
				{
				setState(82);
				match(CONSTANT);
				}
			}

			setState(85);
			type();
			setState(86);
			asignacion();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AsignacionContext extends ParserRuleContext {
		public ListaidsContext listaids() {
			return getRuleContext(ListaidsContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(AritmeticParser.EQUAL, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(AritmeticParser.SEMICOLON, 0); }
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			listaids();
			setState(89);
			match(EQUAL);
			setState(90);
			expresion();
			setState(91);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaidsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(AritmeticParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(AritmeticParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AritmeticParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AritmeticParser.COMMA, i);
		}
		public ListaidsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaids; }
	}

	public final ListaidsContext listaids() throws RecognitionException {
		ListaidsContext _localctx = new ListaidsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_listaids);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(93);
					match(ID);
					setState(94);
					match(COMMA);
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(100);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypefContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(AritmeticParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typef; }
	}

	public final TypefContext typef() throws RecognitionException {
		TypefContext _localctx = new TypefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typef);
		try {
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(VOID);
				}
				break;
			case INT:
			case BOOLEAN:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(AritmeticParser.INT, 0); }
		public TerminalNode BOOLEAN() { return getToken(AritmeticParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(AritmeticParser.STRING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOLEAN) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AritmeticParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AritmeticParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_params);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(108);
					param();
					setState(109);
					match(COMMA);
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(116);
			param();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(AritmeticParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			type();
			setState(119);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AritmeticParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AritmeticParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_args);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(121);
					expresion();
					setState(122);
					match(COMMA);
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(129);
			expresion();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpresionContext extends ParserRuleContext {
		public List<Cont_expresionContext> cont_expresion() {
			return getRuleContexts(Cont_expresionContext.class);
		}
		public Cont_expresionContext cont_expresion(int i) {
			return getRuleContext(Cont_expresionContext.class,i);
		}
		public List<OpContext> op() {
			return getRuleContexts(OpContext.class);
		}
		public OpContext op(int i) {
			return getRuleContext(OpContext.class,i);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expresion);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(131);
					cont_expresion();
					setState(132);
					op();
					}
					} 
				}
				setState(138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(139);
			cont_expresion();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cont_expresionContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(AritmeticParser.NUMBER, 0); }
		public TerminalNode STRING_LIT() { return getToken(AritmeticParser.STRING_LIT, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AritmeticParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AritmeticParser.RPAREN, 0); }
		public TerminalNode LNOT() { return getToken(AritmeticParser.LNOT, 0); }
		public TerminalNode ID() { return getToken(AritmeticParser.ID, 0); }
		public PostContext post() {
			return getRuleContext(PostContext.class,0);
		}
		public CallfContext callf() {
			return getRuleContext(CallfContext.class,0);
		}
		public Cont_expresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cont_expresion; }
	}

	public final Cont_expresionContext cont_expresion() throws RecognitionException {
		Cont_expresionContext _localctx = new Cont_expresionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cont_expresion);
		int _la;
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(STRING_LIT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				bool();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				match(LPAREN);
				setState(145);
				expresion();
				setState(146);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				match(LNOT);
				setState(149);
				expresion();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(150);
				match(ID);
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUSPLUS || _la==MINUSMINUS) {
					{
					setState(151);
					post();
					}
				}

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(154);
				callf();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallfContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AritmeticParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(AritmeticParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AritmeticParser.RPAREN, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode INPUTINT() { return getToken(AritmeticParser.INPUTINT, 0); }
		public TerminalNode INPUTBOL() { return getToken(AritmeticParser.INPUTBOL, 0); }
		public TerminalNode INPUTSTR() { return getToken(AritmeticParser.INPUTSTR, 0); }
		public CallfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callf; }
	}

	public final CallfContext callf() throws RecognitionException {
		CallfContext _localctx = new CallfContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_callf);
		int _la;
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(ID);
				setState(158);
				match(LPAREN);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << LPAREN) | (1L << LNOT) | (1L << STRING_LIT) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(159);
					args();
					}
				}

				setState(162);
				match(RPAREN);
				}
				break;
			case INPUTINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(INPUTINT);
				setState(164);
				match(LPAREN);
				setState(165);
				match(RPAREN);
				}
				break;
			case INPUTBOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				match(INPUTBOL);
				setState(167);
				match(LPAREN);
				setState(168);
				match(RPAREN);
				}
				break;
			case INPUTSTR:
				enterOuterAlt(_localctx, 4);
				{
				setState(169);
				match(INPUTSTR);
				setState(170);
				match(LPAREN);
				setState(171);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(AritmeticParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AritmeticParser.MINUS, 0); }
		public TerminalNode MUL() { return getToken(AritmeticParser.MUL, 0); }
		public TerminalNode LAND() { return getToken(AritmeticParser.LAND, 0); }
		public TerminalNode LOR() { return getToken(AritmeticParser.LOR, 0); }
		public TerminalNode REQUAL() { return getToken(AritmeticParser.REQUAL, 0); }
		public TerminalNode RNOTEQUAL() { return getToken(AritmeticParser.RNOTEQUAL, 0); }
		public TerminalNode RLESS() { return getToken(AritmeticParser.RLESS, 0); }
		public TerminalNode RGRE() { return getToken(AritmeticParser.RGRE, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << REQUAL) | (1L << RNOTEQUAL) | (1L << LAND) | (1L << LOR) | (1L << PLUS) | (1L << MINUS) | (1L << MUL) | (1L << RLESS) | (1L << RGRE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostContext extends ParserRuleContext {
		public TerminalNode PLUSPLUS() { return getToken(AritmeticParser.PLUSPLUS, 0); }
		public TerminalNode MINUSMINUS() { return getToken(AritmeticParser.MINUSMINUS, 0); }
		public PostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_post; }
	}

	public final PostContext post() throws RecognitionException {
		PostContext _localctx = new PostContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_post);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			_la = _input.LA(1);
			if ( !(_la==PLUSPLUS || _la==MINUSMINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(AritmeticParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(AritmeticParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstrContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(AritmeticParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(AritmeticParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AritmeticParser.RPAREN, 0); }
		public List<TerminalNode> LKEY() { return getTokens(AritmeticParser.LKEY); }
		public TerminalNode LKEY(int i) {
			return getToken(AritmeticParser.LKEY, i);
		}
		public List<TerminalNode> RKEY() { return getTokens(AritmeticParser.RKEY); }
		public TerminalNode RKEY(int i) {
			return getToken(AritmeticParser.RKEY, i);
		}
		public List<InstrContext> instr() {
			return getRuleContexts(InstrContext.class);
		}
		public InstrContext instr(int i) {
			return getRuleContext(InstrContext.class,i);
		}
		public TerminalNode IF() { return getToken(AritmeticParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(AritmeticParser.ELSE, 0); }
		public CallfContext callf() {
			return getRuleContext(CallfContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(AritmeticParser.SEMICOLON, 0); }
		public TerminalNode OTUPUT() { return getToken(AritmeticParser.OTUPUT, 0); }
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public TerminalNode ID() { return getToken(AritmeticParser.ID, 0); }
		public PostContext post() {
			return getRuleContext(PostContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(AritmeticParser.RETURN, 0); }
		public InstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instr; }
	}

	public final InstrContext instr() throws RecognitionException {
		InstrContext _localctx = new InstrContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_instr);
		int _la;
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(WHILE);
				setState(181);
				match(LPAREN);
				setState(182);
				expresion();
				setState(183);
				match(RPAREN);
				setState(184);
				match(LKEY);
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OTUPUT) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
					{
					{
					setState(185);
					instr();
					}
					}
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(191);
				match(RKEY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(IF);
				setState(194);
				match(LPAREN);
				setState(195);
				expresion();
				setState(196);
				match(RPAREN);
				setState(197);
				match(LKEY);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OTUPUT) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
					{
					{
					setState(198);
					instr();
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(204);
				match(RKEY);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(205);
					match(ELSE);
					setState(206);
					match(LKEY);
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OTUPUT) | (1L << INPUTINT) | (1L << INPUTBOL) | (1L << INPUTSTR) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
						{
						{
						setState(207);
						instr();
						}
						}
						setState(212);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(213);
					match(RKEY);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				callf();
				setState(217);
				match(SEMICOLON);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				match(OTUPUT);
				setState(220);
				match(LPAREN);
				setState(221);
				expresion();
				setState(222);
				match(RPAREN);
				setState(223);
				match(SEMICOLON);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(225);
				asignacion();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(226);
				match(ID);
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUSPLUS || _la==MINUSMINUS) {
					{
					setState(227);
					post();
					}
				}

				setState(230);
				match(SEMICOLON);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(231);
				match(RETURN);
				setState(232);
				expresion();
				setState(233);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\3\2\3\2\5\2\60\n\2\3\2\7\2"+
		"\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3?\n\3\3\3\3\3"+
		"\3\3\5\3D\n\3\3\3\7\3G\n\3\f\3\16\3J\13\3\3\3\3\3\3\4\6\4O\n\4\r\4\16"+
		"\4P\3\4\3\4\3\5\5\5V\n\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\7\7b"+
		"\n\7\f\7\16\7e\13\7\3\7\3\7\3\b\3\b\5\bk\n\b\3\t\3\t\3\n\3\n\3\n\7\nr"+
		"\n\n\f\n\16\nu\13\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\7\f\177\n\f\f\f"+
		"\16\f\u0082\13\f\3\f\3\f\3\r\3\r\3\r\7\r\u0089\n\r\f\r\16\r\u008c\13\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u009b"+
		"\n\16\3\16\5\16\u009e\n\16\3\17\3\17\3\17\5\17\u00a3\n\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00af\n\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00bd\n\23\f\23\16"+
		"\23\u00c0\13\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00ca\n\23"+
		"\f\23\16\23\u00cd\13\23\3\23\3\23\3\23\3\23\7\23\u00d3\n\23\f\23\16\23"+
		"\u00d6\13\23\3\23\5\23\u00d9\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\5\23\u00e7\n\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00ee\n\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\6"+
		"\3\2\r\17\4\2\25\30$(\3\2\31\32\3\2\3\4\2\u0100\2)\3\2\2\2\49\3\2\2\2"+
		"\6N\3\2\2\2\bU\3\2\2\2\nZ\3\2\2\2\fc\3\2\2\2\16j\3\2\2\2\20l\3\2\2\2\22"+
		"s\3\2\2\2\24x\3\2\2\2\26\u0080\3\2\2\2\30\u008a\3\2\2\2\32\u009d\3\2\2"+
		"\2\34\u00ae\3\2\2\2\36\u00b0\3\2\2\2 \u00b2\3\2\2\2\"\u00b4\3\2\2\2$\u00ed"+
		"\3\2\2\2&(\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2"+
		"+)\3\2\2\2,-\7\5\2\2-/\7 \2\2.\60\5\6\4\2/.\3\2\2\2/\60\3\2\2\2\60\64"+
		"\3\2\2\2\61\63\5$\23\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65"+
		"\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7!\2\28\3\3\2\2\29:\5\16\b\2"+
		":;\7\6\2\2;<\7+\2\2<>\7\34\2\2=?\5\22\n\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2"+
		"\2@A\7\35\2\2AC\7 \2\2BD\5\6\4\2CB\3\2\2\2CD\3\2\2\2DH\3\2\2\2EG\5$\23"+
		"\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7!\2"+
		"\2L\5\3\2\2\2MO\5\b\5\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2"+
		"\2\2RS\7\24\2\2S\7\3\2\2\2TV\7\13\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX"+
		"\5\20\t\2XY\5\n\6\2Y\t\3\2\2\2Z[\5\f\7\2[\\\7#\2\2\\]\5\30\r\2]^\7\"\2"+
		"\2^\13\3\2\2\2_`\7+\2\2`b\7\33\2\2a_\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2"+
		"\2\2df\3\2\2\2ec\3\2\2\2fg\7+\2\2g\r\3\2\2\2hk\7\20\2\2ik\5\20\t\2jh\3"+
		"\2\2\2ji\3\2\2\2k\17\3\2\2\2lm\t\2\2\2m\21\3\2\2\2no\5\24\13\2op\7\33"+
		"\2\2pr\3\2\2\2qn\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2"+
		"\2\2vw\5\24\13\2w\23\3\2\2\2xy\5\20\t\2yz\7+\2\2z\25\3\2\2\2{|\5\30\r"+
		"\2|}\7\33\2\2}\177\3\2\2\2~{\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084"+
		"\5\30\r\2\u0084\27\3\2\2\2\u0085\u0086\5\32\16\2\u0086\u0087\5\36\20\2"+
		"\u0087\u0089\3\2\2\2\u0088\u0085\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u008e\5\32\16\2\u008e\31\3\2\2\2\u008f\u009e\7,\2\2\u0090\u009e\7*\2"+
		"\2\u0091\u009e\5\"\22\2\u0092\u0093\7\34\2\2\u0093\u0094\5\30\r\2\u0094"+
		"\u0095\7\35\2\2\u0095\u009e\3\2\2\2\u0096\u0097\7)\2\2\u0097\u009e\5\30"+
		"\r\2\u0098\u009a\7+\2\2\u0099\u009b\5 \21\2\u009a\u0099\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009e\5\34\17\2\u009d\u008f\3"+
		"\2\2\2\u009d\u0090\3\2\2\2\u009d\u0091\3\2\2\2\u009d\u0092\3\2\2\2\u009d"+
		"\u0096\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u009c\3\2\2\2\u009e\33\3\2\2"+
		"\2\u009f\u00a0\7+\2\2\u00a0\u00a2\7\34\2\2\u00a1\u00a3\5\26\f\2\u00a2"+
		"\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00af\7\35"+
		"\2\2\u00a5\u00a6\7\b\2\2\u00a6\u00a7\7\34\2\2\u00a7\u00af\7\35\2\2\u00a8"+
		"\u00a9\7\t\2\2\u00a9\u00aa\7\34\2\2\u00aa\u00af\7\35\2\2\u00ab\u00ac\7"+
		"\n\2\2\u00ac\u00ad\7\34\2\2\u00ad\u00af\7\35\2\2\u00ae\u009f\3\2\2\2\u00ae"+
		"\u00a5\3\2\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00ab\3\2\2\2\u00af\35\3\2\2"+
		"\2\u00b0\u00b1\t\3\2\2\u00b1\37\3\2\2\2\u00b2\u00b3\t\4\2\2\u00b3!\3\2"+
		"\2\2\u00b4\u00b5\t\5\2\2\u00b5#\3\2\2\2\u00b6\u00b7\7\23\2\2\u00b7\u00b8"+
		"\7\34\2\2\u00b8\u00b9\5\30\r\2\u00b9\u00ba\7\35\2\2\u00ba\u00be\7 \2\2"+
		"\u00bb\u00bd\5$\23\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1"+
		"\u00c2\7!\2\2\u00c2\u00ee\3\2\2\2\u00c3\u00c4\7\21\2\2\u00c4\u00c5\7\34"+
		"\2\2\u00c5\u00c6\5\30\r\2\u00c6\u00c7\7\35\2\2\u00c7\u00cb\7 \2\2\u00c8"+
		"\u00ca\5$\23\2\u00c9\u00c8\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2"+
		"\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce"+
		"\u00d8\7!\2\2\u00cf\u00d0\7\22\2\2\u00d0\u00d4\7 \2\2\u00d1\u00d3\5$\23"+
		"\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5"+
		"\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d9\7!\2\2\u00d8"+
		"\u00cf\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00ee\3\2\2\2\u00da\u00db\5\34"+
		"\17\2\u00db\u00dc\7\"\2\2\u00dc\u00ee\3\2\2\2\u00dd\u00de\7\7\2\2\u00de"+
		"\u00df\7\34\2\2\u00df\u00e0\5\30\r\2\u00e0\u00e1\7\35\2\2\u00e1\u00e2"+
		"\7\"\2\2\u00e2\u00ee\3\2\2\2\u00e3\u00ee\5\n\6\2\u00e4\u00e6\7+\2\2\u00e5"+
		"\u00e7\5 \21\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2"+
		"\2\2\u00e8\u00ee\7\"\2\2\u00e9\u00ea\7\f\2\2\u00ea\u00eb\5\30\r\2\u00eb"+
		"\u00ec\7\"\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00b6\3\2\2\2\u00ed\u00c3\3\2"+
		"\2\2\u00ed\u00da\3\2\2\2\u00ed\u00dd\3\2\2\2\u00ed\u00e3\3\2\2\2\u00ed"+
		"\u00e4\3\2\2\2\u00ed\u00e9\3\2\2\2\u00ee%\3\2\2\2\31)/\64>CHPUcjs\u0080"+
		"\u008a\u009a\u009d\u00a2\u00ae\u00be\u00cb\u00d4\u00d8\u00e6\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}