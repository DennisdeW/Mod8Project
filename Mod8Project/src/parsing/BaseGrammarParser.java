// Generated from BaseGrammar.g4 by ANTLR 4.4
package parsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BaseGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, BOOL=2, VOID=3, STRING=4, ENUM=5, FOR=6, WHILE=7, IF=8, ELSE=9, 
		TRUE=10, FALSE=11, AND=12, OR=13, XOR=14, NAND=15, NOR=16, NXOR=17, NOT=18, 
		RETURN=19, BREAK=20, DEF=21, LOCK=22, UNLOCK=23, SHARED=24, PROGRAM=25, 
		SPID=26, OUT=27, IN=28, UNDERSCORE=29, DOT=30, COMMA=31, SEMI=32, COLON=33, 
		EXCLAMATION=34, LBRACE=35, RBRACE=36, LCURL=37, RCURL=38, LSQUARE=39, 
		RSQUARE=40, PLUS=41, MINUS=42, TIMES=43, DIV=44, MOD=45, AMP=46, SQUOT=47, 
		DQUOT=48, BACKSLASH=49, TIDLE=50, HAT=51, GT=52, LT=53, EQ=54, ARROW=55, 
		GE=56, LE=57, NE=58, TYPE=59, ID=60, NUMBER=61, COMMENT=62, WS=63;
	public static final String[] tokenNames = {
		"<INVALID>", "INT", "BOOL", "VOID", "STRING", "ENUM", "FOR", "WHILE", 
		"IF", "ELSE", "TRUE", "FALSE", "AND", "OR", "XOR", "NAND", "NOR", "NXOR", 
		"NOT", "RETURN", "BREAK", "DEF", "LOCK", "UNLOCK", "SHARED", "PROGRAM", 
		"SPID", "OUT", "IN", "'_'", "'.'", "','", "';'", "':'", "'!'", "'('", 
		"')'", "'{'", "'}'", "'['", "']'", "'+'", "'-'", "'*'", "'/'", "'%'", 
		"'&'", "'''", "'\"'", "'\\'", "'~'", "'^'", "'>'", "'<'", "'='", "ARROW", 
		"GE", "LE", "NE", "TYPE", "ID", "NUMBER", "COMMENT", "WS"
	};
	public static final int
		RULE_program = 0, RULE_progdef = 1, RULE_block = 2, RULE_topLevelBlock = 3, 
		RULE_func = 4, RULE_typedparams = 5, RULE_params = 6, RULE_call = 7, RULE_expr = 8, 
		RULE_stat = 9, RULE_decl = 10, RULE_assign = 11, RULE_arrayVal = 12, RULE_type = 13, 
		RULE_enumDecl = 14, RULE_val = 15, RULE_comp = 16, RULE_boolOp = 17, RULE_prefix = 18, 
		RULE_derefID = 19;
	public static final String[] ruleNames = {
		"program", "progdef", "block", "topLevelBlock", "func", "typedparams", 
		"params", "call", "expr", "stat", "decl", "assign", "arrayVal", "type", 
		"enumDecl", "val", "comp", "boolOp", "prefix", "derefID"
	};

	@Override
	public String getGrammarFileName() { return "BaseGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BaseGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(BaseGrammarParser.SEMI); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public TerminalNode SEMI(int i) {
			return getToken(BaseGrammarParser.SEMI, i);
		}
		public EnumDeclContext enumDecl(int i) {
			return getRuleContext(EnumDeclContext.class,i);
		}
		public ProgdefContext progdef() {
			return getRuleContext(ProgdefContext.class,0);
		}
		public List<EnumDeclContext> enumDecl() {
			return getRuleContexts(EnumDeclContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); progdef();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ENUM) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				{
				setState(43);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case SHARED:
				case TYPE:
					{
					setState(41); decl();
					}
					break;
				case ENUM:
					{
					setState(42); enumDecl();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(45); match(SEMI);
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52); func();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ENUM) | (1L << DEF) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				setState(60);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case ENUM:
				case SHARED:
				case TYPE:
					{
					{
					setState(55);
					switch (_input.LA(1)) {
					case INT:
					case BOOL:
					case VOID:
					case SHARED:
					case TYPE:
						{
						setState(53); decl();
						}
						break;
					case ENUM:
						{
						setState(54); enumDecl();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(57); match(SEMI);
					}
					}
					break;
				case DEF:
					{
					setState(59); func();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ProgdefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public TerminalNode PROGRAM() { return getToken(BaseGrammarParser.PROGRAM, 0); }
		public ProgdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_progdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterProgdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitProgdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitProgdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgdefContext progdef() throws RecognitionException {
		ProgdefContext _localctx = new ProgdefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_progdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(PROGRAM);
			setState(66); match(ID);
			setState(70);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(67); match(LBRACE);
				setState(68); match(NUMBER);
				setState(69); match(RBRACE);
				}
			}

			setState(72); match(SEMI);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LCURL() { return getToken(BaseGrammarParser.LCURL, 0); }
		public TerminalNode RCURL() { return getToken(BaseGrammarParser.RCURL, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); match(LCURL);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << FOR) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LOCK) | (1L << SHARED) | (1L << OUT) | (1L << IN) | (1L << LCURL) | (1L << TIMES) | (1L << TYPE) | (1L << ID))) != 0)) {
				{
				{
				setState(75); stat();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81); match(RCURL);
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

	public static class TopLevelBlockContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TopLevelBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterTopLevelBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitTopLevelBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitTopLevelBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopLevelBlockContext topLevelBlock() throws RecognitionException {
		TopLevelBlockContext _localctx = new TopLevelBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_topLevelBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); block();
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

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TopLevelBlockContext topLevelBlock() {
			return getRuleContext(TopLevelBlockContext.class,0);
		}
		public TerminalNode DEF() { return getToken(BaseGrammarParser.DEF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypedparamsContext typedparams() {
			return getRuleContext(TypedparamsContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(DEF);
			setState(86); type(0);
			setState(87); match(ID);
			setState(88); typedparams();
			setState(89); topLevelBlock();
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

	public static class TypedparamsContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(BaseGrammarParser.ID); }
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BaseGrammarParser.COMMA); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode ID(int i) {
			return getToken(BaseGrammarParser.ID, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BaseGrammarParser.COMMA, i);
		}
		public TypedparamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedparams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterTypedparams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitTypedparams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitTypedparams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedparamsContext typedparams() throws RecognitionException {
		TypedparamsContext _localctx = new TypedparamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_typedparams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(LBRACE);
			setState(103);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << TYPE))) != 0)) {
				{
				setState(92); type(0);
				setState(93); match(ID);
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(94); match(COMMA);
					setState(95); type(0);
					setState(96); match(ID);
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(105); match(RBRACE);
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
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BaseGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BaseGrammarParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(LBRACE);
			setState(116);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << TYPE) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(108); expr(0);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(109); match(COMMA);
					setState(110); expr(0);
					}
					}
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(118); match(RBRACE);
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

	public static class CallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(ID);
			setState(121); params();
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstArrayExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RSQUARE() { return getToken(BaseGrammarParser.RSQUARE, 0); }
		public TerminalNode LSQUARE() { return getToken(BaseGrammarParser.LSQUARE, 0); }
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public ConstArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterConstArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitConstArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitConstArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(BaseGrammarParser.MINUS, 0); }
		public MinExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterMinExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitMinExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitMinExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MOD() { return getToken(BaseGrammarParser.MOD, 0); }
		public ModExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterModExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitModExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitModExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(BaseGrammarParser.TRUE, 0); }
		public TrueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitTrueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitTrueExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolOpExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public BoolOpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterBoolOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitBoolOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitBoolOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegBoolExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EXCLAMATION() { return getToken(BaseGrammarParser.EXCLAMATION, 0); }
		public NegBoolExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterNegBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitNegBoolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitNegBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode TIMES() { return getToken(BaseGrammarParser.TIMES, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExprContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public NumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterNumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitNumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PlusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(BaseGrammarParser.PLUS, 0); }
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterPlusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitPlusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitPlusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprArrayExprContext extends ExprContext {
		public ArrayValContext arrayVal() {
			return getRuleContext(ArrayValContext.class,0);
		}
		public ExprArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterExprArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitExprArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitExprArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParExprContext extends ExprContext {
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public ParExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterParExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitParExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitParExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RefExprContext extends ExprContext {
		public TerminalNode AMP() { return getToken(BaseGrammarParser.AMP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RefExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterRefExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitRefExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitRefExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DerefExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(BaseGrammarParser.TIMES, 0); }
		public DerefExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterDerefExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitDerefExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitDerefExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public CompExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterCompExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitCompExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EnumExprContext extends ExprContext {
		public TerminalNode DOT() { return getToken(BaseGrammarParser.DOT, 0); }
		public List<TerminalNode> TYPE() { return getTokens(BaseGrammarParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(BaseGrammarParser.TYPE, i);
		}
		public EnumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterEnumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitEnumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitEnumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DIV() { return getToken(BaseGrammarParser.DIV, 0); }
		public DivExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitDivExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallExprContext extends ExprContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CallExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayLiteralExprContext extends ExprContext {
		public TerminalNode RSQUARE() { return getToken(BaseGrammarParser.RSQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LSQUARE() { return getToken(BaseGrammarParser.LSQUARE, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BaseGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BaseGrammarParser.COMMA, i);
		}
		public ArrayLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterArrayLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitArrayLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitArrayLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseExprContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(BaseGrammarParser.FALSE, 0); }
		public FalseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterFalseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitFalseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitFalseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpidExprContext extends ExprContext {
		public TerminalNode SPID() { return getToken(BaseGrammarParser.SPID, 0); }
		public SpidExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterSpidExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitSpidExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitSpidExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegNumExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(BaseGrammarParser.MINUS, 0); }
		public NegNumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterNegNumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitNegNumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitNegNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new NegNumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(124); match(MINUS);
				setState(125); expr(22);
				}
				break;
			case 2:
				{
				_localctx = new NegBoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126); match(EXCLAMATION);
				setState(127); expr(21);
				}
				break;
			case 3:
				{
				_localctx = new RefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(AMP);
				setState(129); expr(20);
				}
				break;
			case 4:
				{
				_localctx = new DerefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130); match(TIMES);
				setState(131); expr(19);
				}
				break;
			case 5:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132); match(LBRACE);
				setState(133); expr(0);
				setState(134); match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); call();
				}
				break;
			case 7:
				{
				_localctx = new ConstArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137); match(ID);
				setState(138); match(LSQUARE);
				setState(139); match(NUMBER);
				setState(140); match(RSQUARE);
				}
				break;
			case 8:
				{
				_localctx = new ExprArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141); arrayVal();
				}
				break;
			case 9:
				{
				_localctx = new ArrayLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142); match(LSQUARE);
				setState(143); expr(0);
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(144); match(COMMA);
					setState(145); expr(0);
					}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(151); match(RSQUARE);
				}
				break;
			case 10:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153); match(ID);
				}
				break;
			case 11:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154); match(NUMBER);
				}
				break;
			case 12:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155); match(TRUE);
				}
				break;
			case 13:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156); match(FALSE);
				}
				break;
			case 14:
				{
				_localctx = new SpidExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157); match(SPID);
				}
				break;
			case 15:
				{
				_localctx = new EnumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158); match(TYPE);
				setState(159); match(DOT);
				setState(160); match(TYPE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(186);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(164); boolOp();
						setState(165); expr(19);
						}
						break;
					case 2:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(168); comp();
						setState(169); expr(18);
						}
						break;
					case 3:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(171);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(172); match(TIMES);
						setState(173); expr(17);
						}
						break;
					case 4:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(174);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(175); match(DIV);
						setState(176); expr(16);
						}
						break;
					case 5:
						{
						_localctx = new MinExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(177);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(178); match(MINUS);
						setState(179); expr(15);
						}
						break;
					case 6:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(180);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(181); match(PLUS);
						setState(182); expr(14);
						}
						break;
					case 7:
						{
						_localctx = new ModExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(184); match(MOD);
						setState(185); expr(13);
						}
						break;
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfStatContext extends StatContext {
		public List<TerminalNode> ELSE() { return getTokens(BaseGrammarParser.ELSE); }
		public List<TerminalNode> IF() { return getTokens(BaseGrammarParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(BaseGrammarParser.IF, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public TerminalNode ELSE(int i) {
			return getToken(BaseGrammarParser.ELSE, i);
		}
		public IfStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitIfStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStatContext extends StatContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockStatContext extends StatContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public TerminalNode LOCK() { return getToken(BaseGrammarParser.LOCK, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterLockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitLockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitLockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OutStatContext extends StatContext {
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public TerminalNode OUT() { return getToken(BaseGrammarParser.OUT, 0); }
		public OutStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterOutStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitOutStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitOutStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallStatContext extends StatContext {
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CallStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterCallStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitCallStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitCallStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatContext extends StatContext {
		public TerminalNode RETURN() { return getToken(BaseGrammarParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public ReturnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitReturnStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclStatContext extends StatContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public DeclStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterDeclStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitDeclStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitDeclStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InStatContext extends StatContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public TerminalNode IN() { return getToken(BaseGrammarParser.IN, 0); }
		public InStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterInStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitInStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitInStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatContext extends StatContext {
		public TerminalNode SEMI() { return getToken(BaseGrammarParser.SEMI, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public AssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitAssignStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStatContext extends StatContext {
		public TerminalNode RBRACE() { return getToken(BaseGrammarParser.RBRACE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(BaseGrammarParser.SEMI); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public TerminalNode FOR() { return getToken(BaseGrammarParser.FOR, 0); }
		public TerminalNode SEMI(int i) {
			return getToken(BaseGrammarParser.SEMI, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterForStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitForStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitForStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(BaseGrammarParser.WHILE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stat);
		int _la;
		try {
			int _alt;
			setState(253);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(191); match(LOCK);
				setState(192); match(LBRACE);
				setState(193); match(ID);
				setState(194); match(RBRACE);
				setState(195); block();
				}
				break;
			case 2:
				_localctx = new DeclStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(196); decl();
				setState(197); match(SEMI);
				}
				break;
			case 3:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(199); assign();
				setState(200); match(SEMI);
				}
				break;
			case 4:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(202); match(IF);
				setState(203); expr(0);
				setState(204); block();
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(205); match(ELSE);
						setState(206); match(IF);
						setState(207); expr(0);
						setState(208); block();
						}
						} 
					}
					setState(214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				setState(217);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(215); match(ELSE);
					setState(216); block();
					}
				}

				}
				break;
			case 5:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(219); match(WHILE);
				setState(220); expr(0);
				setState(221); block();
				}
				break;
			case 6:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(223); call();
				setState(224); match(SEMI);
				}
				break;
			case 7:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(226); block();
				}
				break;
			case 8:
				_localctx = new ForStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(227); match(FOR);
				setState(228); match(LBRACE);
				setState(229); decl();
				setState(230); match(SEMI);
				setState(231); expr(0);
				setState(232); match(SEMI);
				setState(233); assign();
				setState(234); match(RBRACE);
				setState(235); block();
				}
				break;
			case 9:
				_localctx = new OutStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(237); match(OUT);
				setState(238); match(LBRACE);
				setState(239); expr(0);
				setState(240); match(RBRACE);
				setState(241); match(SEMI);
				}
				break;
			case 10:
				_localctx = new InStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(243); match(IN);
				setState(244); match(LBRACE);
				setState(245); match(ID);
				setState(246); match(RBRACE);
				setState(247); match(SEMI);
				}
				break;
			case 11:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(248); match(RETURN);
				setState(250);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << TYPE) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(249); expr(0);
					}
				}

				setState(252); match(SEMI);
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

	public static class DeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RSQUARE() { return getToken(BaseGrammarParser.RSQUARE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode LSQUARE() { return getToken(BaseGrammarParser.LSQUARE, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TerminalNode EQ() { return getToken(BaseGrammarParser.EQ, 0); }
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public TerminalNode SHARED() { return getToken(BaseGrammarParser.SHARED, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_la = _input.LA(1);
			if (_la==SHARED) {
				{
				setState(255); match(SHARED);
				}
			}

			setState(258); type(0);
			setState(259); match(ID);
			setState(260); match(EQ);
			setState(267);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(261); expr(0);
				}
				break;
			case 2:
				{
				setState(262); type(0);
				setState(263); match(LSQUARE);
				setState(264); match(NUMBER);
				setState(265); match(RSQUARE);
				}
				break;
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

	public static class AssignContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EQ() { return getToken(BaseGrammarParser.EQ, 0); }
		public ArrayValContext arrayVal() {
			return getRuleContext(ArrayValContext.class,0);
		}
		public DerefIDContext derefID() {
			return getRuleContext(DerefIDContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(269); derefID();
				}
				break;
			case 2:
				{
				setState(270); arrayVal();
				}
				break;
			}
			setState(273); match(EQ);
			setState(274); expr(0);
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

	public static class ArrayValContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode RSQUARE() { return getToken(BaseGrammarParser.RSQUARE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LSQUARE() { return getToken(BaseGrammarParser.LSQUARE, 0); }
		public ArrayValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterArrayVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitArrayVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitArrayVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayValContext arrayVal() throws RecognitionException {
		ArrayValContext _localctx = new ArrayValContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arrayVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(ID);
			setState(277); match(LSQUARE);
			setState(278); expr(0);
			setState(279); match(RSQUARE);
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
		public TerminalNode BOOL() { return getToken(BaseGrammarParser.BOOL, 0); }
		public TerminalNode RSQUARE() { return getToken(BaseGrammarParser.RSQUARE, 0); }
		public TerminalNode TIMES() { return getToken(BaseGrammarParser.TIMES, 0); }
		public TerminalNode TYPE() { return getToken(BaseGrammarParser.TYPE, 0); }
		public TerminalNode LSQUARE() { return getToken(BaseGrammarParser.LSQUARE, 0); }
		public TerminalNode VOID() { return getToken(BaseGrammarParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode INT() { return getToken(BaseGrammarParser.INT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(282); match(INT);
				}
				break;
			case BOOL:
				{
				setState(283); match(BOOL);
				}
				break;
			case VOID:
				{
				setState(284); match(VOID);
				}
				break;
			case TYPE:
				{
				setState(285); match(TYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(293);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(288);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(289); match(TIMES);
						}
						break;
					case 2:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(290);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(291); match(LSQUARE);
						setState(292); match(RSQUARE);
						}
						break;
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EnumDeclContext extends ParserRuleContext {
		public List<TerminalNode> TYPE() { return getTokens(BaseGrammarParser.TYPE); }
		public TerminalNode ENUM() { return getToken(BaseGrammarParser.ENUM, 0); }
		public List<TerminalNode> COMMA() { return getTokens(BaseGrammarParser.COMMA); }
		public TerminalNode COLON() { return getToken(BaseGrammarParser.COLON, 0); }
		public TerminalNode TYPE(int i) {
			return getToken(BaseGrammarParser.TYPE, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(BaseGrammarParser.COMMA, i);
		}
		public EnumDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterEnumDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitEnumDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitEnumDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDeclContext enumDecl() throws RecognitionException {
		EnumDeclContext _localctx = new EnumDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_enumDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298); match(ENUM);
			setState(299); match(TYPE);
			setState(300); match(COLON);
			setState(301); match(TYPE);
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(302); match(COMMA);
				setState(303); match(TYPE);
				}
				}
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ValContext extends ParserRuleContext {
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
	 
		public ValContext() { }
		public void copyFrom(ValContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdValContext extends ValContext {
		public DerefIDContext derefID() {
			return getRuleContext(DerefIDContext.class,0);
		}
		public IdValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterIdVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitIdVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitIdVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrValContext extends ValContext {
		public ArrayValContext arrayVal() {
			return getRuleContext(ArrayValContext.class,0);
		}
		public ArrValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterArrVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitArrVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitArrVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumValContext extends ValContext {
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public NumValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterNumVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitNumVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitNumVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueValContext extends ValContext {
		public TerminalNode TRUE() { return getToken(BaseGrammarParser.TRUE, 0); }
		public TrueValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterTrueVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitTrueVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitTrueVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpidValContext extends ValContext {
		public TerminalNode SPID() { return getToken(BaseGrammarParser.SPID, 0); }
		public SpidValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterSpidVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitSpidVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitSpidVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseValContext extends ValContext {
		public TerminalNode FALSE() { return getToken(BaseGrammarParser.FALSE, 0); }
		public FalseValContext(ValContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterFalseVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitFalseVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitFalseVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_val);
		try {
			setState(315);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new NumValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(309); match(NUMBER);
				}
				break;
			case 2:
				_localctx = new IdValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(310); derefID();
				}
				break;
			case 3:
				_localctx = new TrueValContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(311); match(TRUE);
				}
				break;
			case 4:
				_localctx = new FalseValContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(312); match(FALSE);
				}
				break;
			case 5:
				_localctx = new SpidValContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(313); match(SPID);
				}
				break;
			case 6:
				_localctx = new ArrValContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(314); arrayVal();
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

	public static class CompContext extends ParserRuleContext {
		public TerminalNode GE() { return getToken(BaseGrammarParser.GE, 0); }
		public TerminalNode EQ(int i) {
			return getToken(BaseGrammarParser.EQ, i);
		}
		public TerminalNode LT() { return getToken(BaseGrammarParser.LT, 0); }
		public TerminalNode GT() { return getToken(BaseGrammarParser.GT, 0); }
		public TerminalNode LE() { return getToken(BaseGrammarParser.LE, 0); }
		public List<TerminalNode> EQ() { return getTokens(BaseGrammarParser.EQ); }
		public TerminalNode NE() { return getToken(BaseGrammarParser.NE, 0); }
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitComp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitComp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comp);
		try {
			setState(324);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(317); match(LT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 2);
				{
				setState(318); match(GT);
				}
				break;
			case EQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(319); match(EQ);
				setState(320); match(EQ);
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 4);
				{
				setState(321); match(LE);
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 5);
				{
				setState(322); match(GE);
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 6);
				{
				setState(323); match(NE);
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

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode NOR() { return getToken(BaseGrammarParser.NOR, 0); }
		public TerminalNode XOR() { return getToken(BaseGrammarParser.XOR, 0); }
		public TerminalNode NAND() { return getToken(BaseGrammarParser.NAND, 0); }
		public TerminalNode AND() { return getToken(BaseGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(BaseGrammarParser.OR, 0); }
		public TerminalNode NXOR() { return getToken(BaseGrammarParser.NXOR, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitBoolOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitBoolOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR) | (1L << NAND) | (1L << NOR) | (1L << NXOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class PrefixContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BaseGrammarParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BaseGrammarParser.MINUS, 0); }
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			_la = _input.LA(1);
			if ( !(_la==NOT || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class DerefIDContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode TIMES() { return getToken(BaseGrammarParser.TIMES, 0); }
		public DerefIDContext derefID() {
			return getRuleContext(DerefIDContext.class,0);
		}
		public DerefIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_derefID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterDerefID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitDerefID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitDerefID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DerefIDContext derefID() throws RecognitionException {
		DerefIDContext _localctx = new DerefIDContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_derefID);
		try {
			setState(333);
			switch (_input.LA(1)) {
			case TIMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(330); match(TIMES);
				setState(331); derefID();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(332); match(ID);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8: return expr_sempred((ExprContext)_localctx, predIndex);
		case 13: return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 18);
		case 1: return precpred(_ctx, 17);
		case 2: return precpred(_ctx, 16);
		case 3: return precpred(_ctx, 15);
		case 4: return precpred(_ctx, 14);
		case 5: return precpred(_ctx, 13);
		case 6: return precpred(_ctx, 12);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return precpred(_ctx, 2);
		case 8: return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u0152\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\5\2.\n\2\3\2\3\2\7\2\62\n\2"+
		"\f\2\16\2\65\13\2\3\2\3\2\3\2\5\2:\n\2\3\2\3\2\3\2\7\2?\n\2\f\2\16\2B"+
		"\13\2\3\3\3\3\3\3\3\3\3\3\5\3I\n\3\3\3\3\3\3\4\3\4\7\4O\n\4\f\4\16\4R"+
		"\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\7\7e\n\7\f\7\16\7h\13\7\5\7j\n\7\3\7\3\7\3\b\3\b\3\b\3\b\7\br\n\b"+
		"\f\b\16\bu\13\b\5\bw\n\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n"+
		"\u0095\n\n\f\n\16\n\u0098\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\n\u00a4\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00d5\n\13\f\13\16\13\u00d8\13\13\3\13"+
		"\3\13\5\13\u00dc\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00fd\n\13\3\13\5\13\u0100\n"+
		"\13\3\f\5\f\u0103\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010e\n"+
		"\f\3\r\3\r\5\r\u0112\n\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0121\n\17\3\17\3\17\3\17\3\17\3\17\7\17\u0128\n"+
		"\17\f\17\16\17\u012b\13\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0133\n"+
		"\20\f\20\16\20\u0136\13\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u013e\n"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0147\n\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\25\5\25\u0150\n\25\3\25\2\4\22\34\26\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(\2\4\3\2\16\23\4\2\24\24,,\u017f\2*\3\2"+
		"\2\2\4C\3\2\2\2\6L\3\2\2\2\bU\3\2\2\2\nW\3\2\2\2\f]\3\2\2\2\16m\3\2\2"+
		"\2\20z\3\2\2\2\22\u00a3\3\2\2\2\24\u00ff\3\2\2\2\26\u0102\3\2\2\2\30\u0111"+
		"\3\2\2\2\32\u0116\3\2\2\2\34\u0120\3\2\2\2\36\u012c\3\2\2\2 \u013d\3\2"+
		"\2\2\"\u0146\3\2\2\2$\u0148\3\2\2\2&\u014a\3\2\2\2(\u014f\3\2\2\2*\63"+
		"\5\4\3\2+.\5\26\f\2,.\5\36\20\2-+\3\2\2\2-,\3\2\2\2./\3\2\2\2/\60\7\""+
		"\2\2\60\62\3\2\2\2\61-\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2"+
		"\2\64\66\3\2\2\2\65\63\3\2\2\2\66@\5\n\6\2\67:\5\26\f\28:\5\36\20\29\67"+
		"\3\2\2\298\3\2\2\2:;\3\2\2\2;<\7\"\2\2<?\3\2\2\2=?\5\n\6\2>9\3\2\2\2>"+
		"=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\3\3\2\2\2B@\3\2\2\2CD\7\33\2"+
		"\2DH\7>\2\2EF\7%\2\2FG\7?\2\2GI\7&\2\2HE\3\2\2\2HI\3\2\2\2IJ\3\2\2\2J"+
		"K\7\"\2\2K\5\3\2\2\2LP\7\'\2\2MO\5\24\13\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2"+
		"\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7(\2\2T\7\3\2\2\2UV\5\6\4\2V\t\3\2"+
		"\2\2WX\7\27\2\2XY\5\34\17\2YZ\7>\2\2Z[\5\f\7\2[\\\5\b\5\2\\\13\3\2\2\2"+
		"]i\7%\2\2^_\5\34\17\2_f\7>\2\2`a\7!\2\2ab\5\34\17\2bc\7>\2\2ce\3\2\2\2"+
		"d`\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gj\3\2\2\2hf\3\2\2\2i^\3\2\2\2"+
		"ij\3\2\2\2jk\3\2\2\2kl\7&\2\2l\r\3\2\2\2mv\7%\2\2ns\5\22\n\2op\7!\2\2"+
		"pr\5\22\n\2qo\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2"+
		"\2vn\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7&\2\2y\17\3\2\2\2z{\7>\2\2{|\5\16"+
		"\b\2|\21\3\2\2\2}~\b\n\1\2~\177\7,\2\2\177\u00a4\5\22\n\30\u0080\u0081"+
		"\7$\2\2\u0081\u00a4\5\22\n\27\u0082\u0083\7\60\2\2\u0083\u00a4\5\22\n"+
		"\26\u0084\u0085\7-\2\2\u0085\u00a4\5\22\n\25\u0086\u0087\7%\2\2\u0087"+
		"\u0088\5\22\n\2\u0088\u0089\7&\2\2\u0089\u00a4\3\2\2\2\u008a\u00a4\5\20"+
		"\t\2\u008b\u008c\7>\2\2\u008c\u008d\7)\2\2\u008d\u008e\7?\2\2\u008e\u00a4"+
		"\7*\2\2\u008f\u00a4\5\32\16\2\u0090\u0091\7)\2\2\u0091\u0096\5\22\n\2"+
		"\u0092\u0093\7!\2\2\u0093\u0095\5\22\n\2\u0094\u0092\3\2\2\2\u0095\u0098"+
		"\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u009a\7*\2\2\u009a\u00a4\3\2\2\2\u009b\u00a4\7>\2"+
		"\2\u009c\u00a4\7?\2\2\u009d\u00a4\7\f\2\2\u009e\u00a4\7\r\2\2\u009f\u00a4"+
		"\7\34\2\2\u00a0\u00a1\7=\2\2\u00a1\u00a2\7 \2\2\u00a2\u00a4\7=\2\2\u00a3"+
		"}\3\2\2\2\u00a3\u0080\3\2\2\2\u00a3\u0082\3\2\2\2\u00a3\u0084\3\2\2\2"+
		"\u00a3\u0086\3\2\2\2\u00a3\u008a\3\2\2\2\u00a3\u008b\3\2\2\2\u00a3\u008f"+
		"\3\2\2\2\u00a3\u0090\3\2\2\2\u00a3\u009b\3\2\2\2\u00a3\u009c\3\2\2\2\u00a3"+
		"\u009d\3\2\2\2\u00a3\u009e\3\2\2\2\u00a3\u009f\3\2\2\2\u00a3\u00a0\3\2"+
		"\2\2\u00a4\u00be\3\2\2\2\u00a5\u00a6\f\24\2\2\u00a6\u00a7\5$\23\2\u00a7"+
		"\u00a8\5\22\n\25\u00a8\u00bd\3\2\2\2\u00a9\u00aa\f\23\2\2\u00aa\u00ab"+
		"\5\"\22\2\u00ab\u00ac\5\22\n\24\u00ac\u00bd\3\2\2\2\u00ad\u00ae\f\22\2"+
		"\2\u00ae\u00af\7-\2\2\u00af\u00bd\5\22\n\23\u00b0\u00b1\f\21\2\2\u00b1"+
		"\u00b2\7.\2\2\u00b2\u00bd\5\22\n\22\u00b3\u00b4\f\20\2\2\u00b4\u00b5\7"+
		",\2\2\u00b5\u00bd\5\22\n\21\u00b6\u00b7\f\17\2\2\u00b7\u00b8\7+\2\2\u00b8"+
		"\u00bd\5\22\n\20\u00b9\u00ba\f\16\2\2\u00ba\u00bb\7/\2\2\u00bb\u00bd\5"+
		"\22\n\17\u00bc\u00a5\3\2\2\2\u00bc\u00a9\3\2\2\2\u00bc\u00ad\3\2\2\2\u00bc"+
		"\u00b0\3\2\2\2\u00bc\u00b3\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc\u00b9\3\2"+
		"\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\23\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\30\2\2\u00c2\u00c3\7%\2"+
		"\2\u00c3\u00c4\7>\2\2\u00c4\u00c5\7&\2\2\u00c5\u0100\5\6\4\2\u00c6\u00c7"+
		"\5\26\f\2\u00c7\u00c8\7\"\2\2\u00c8\u0100\3\2\2\2\u00c9\u00ca\5\30\r\2"+
		"\u00ca\u00cb\7\"\2\2\u00cb\u0100\3\2\2\2\u00cc\u00cd\7\n\2\2\u00cd\u00ce"+
		"\5\22\n\2\u00ce\u00d6\5\6\4\2\u00cf\u00d0\7\13\2\2\u00d0\u00d1\7\n\2\2"+
		"\u00d1\u00d2\5\22\n\2\u00d2\u00d3\5\6\4\2\u00d3\u00d5\3\2\2\2\u00d4\u00cf"+
		"\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00db\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\7\13\2\2\u00da\u00dc\5"+
		"\6\4\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u0100\3\2\2\2\u00dd"+
		"\u00de\7\t\2\2\u00de\u00df\5\22\n\2\u00df\u00e0\5\6\4\2\u00e0\u0100\3"+
		"\2\2\2\u00e1\u00e2\5\20\t\2\u00e2\u00e3\7\"\2\2\u00e3\u0100\3\2\2\2\u00e4"+
		"\u0100\5\6\4\2\u00e5\u00e6\7\b\2\2\u00e6\u00e7\7%\2\2\u00e7\u00e8\5\26"+
		"\f\2\u00e8\u00e9\7\"\2\2\u00e9\u00ea\5\22\n\2\u00ea\u00eb\7\"\2\2\u00eb"+
		"\u00ec\5\30\r\2\u00ec\u00ed\7&\2\2\u00ed\u00ee\5\6\4\2\u00ee\u0100\3\2"+
		"\2\2\u00ef\u00f0\7\35\2\2\u00f0\u00f1\7%\2\2\u00f1\u00f2\5\22\n\2\u00f2"+
		"\u00f3\7&\2\2\u00f3\u00f4\7\"\2\2\u00f4\u0100\3\2\2\2\u00f5\u00f6\7\36"+
		"\2\2\u00f6\u00f7\7%\2\2\u00f7\u00f8\7>\2\2\u00f8\u00f9\7&\2\2\u00f9\u0100"+
		"\7\"\2\2\u00fa\u00fc\7\25\2\2\u00fb\u00fd\5\22\n\2\u00fc\u00fb\3\2\2\2"+
		"\u00fc\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\7\"\2\2\u00ff\u00c1"+
		"\3\2\2\2\u00ff\u00c6\3\2\2\2\u00ff\u00c9\3\2\2\2\u00ff\u00cc\3\2\2\2\u00ff"+
		"\u00dd\3\2\2\2\u00ff\u00e1\3\2\2\2\u00ff\u00e4\3\2\2\2\u00ff\u00e5\3\2"+
		"\2\2\u00ff\u00ef\3\2\2\2\u00ff\u00f5\3\2\2\2\u00ff\u00fa\3\2\2\2\u0100"+
		"\25\3\2\2\2\u0101\u0103\7\32\2\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2"+
		"\2\u0103\u0104\3\2\2\2\u0104\u0105\5\34\17\2\u0105\u0106\7>\2\2\u0106"+
		"\u010d\78\2\2\u0107\u010e\5\22\n\2\u0108\u0109\5\34\17\2\u0109\u010a\7"+
		")\2\2\u010a\u010b\7?\2\2\u010b\u010c\7*\2\2\u010c\u010e\3\2\2\2\u010d"+
		"\u0107\3\2\2\2\u010d\u0108\3\2\2\2\u010e\27\3\2\2\2\u010f\u0112\5(\25"+
		"\2\u0110\u0112\5\32\16\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\78\2\2\u0114\u0115\5\22\n\2\u0115\31\3\2\2"+
		"\2\u0116\u0117\7>\2\2\u0117\u0118\7)\2\2\u0118\u0119\5\22\n\2\u0119\u011a"+
		"\7*\2\2\u011a\33\3\2\2\2\u011b\u011c\b\17\1\2\u011c\u0121\7\3\2\2\u011d"+
		"\u0121\7\4\2\2\u011e\u0121\7\5\2\2\u011f\u0121\7=\2\2\u0120\u011b\3\2"+
		"\2\2\u0120\u011d\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121"+
		"\u0129\3\2\2\2\u0122\u0123\f\4\2\2\u0123\u0128\7-\2\2\u0124\u0125\f\3"+
		"\2\2\u0125\u0126\7)\2\2\u0126\u0128\7*\2\2\u0127\u0122\3\2\2\2\u0127\u0124"+
		"\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\35\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\7\7\2\2\u012d\u012e\7=\2\2"+
		"\u012e\u012f\7#\2\2\u012f\u0134\7=\2\2\u0130\u0131\7!\2\2\u0131\u0133"+
		"\7=\2\2\u0132\u0130\3\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134"+
		"\u0135\3\2\2\2\u0135\37\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u013e\7?\2\2"+
		"\u0138\u013e\5(\25\2\u0139\u013e\7\f\2\2\u013a\u013e\7\r\2\2\u013b\u013e"+
		"\7\34\2\2\u013c\u013e\5\32\16\2\u013d\u0137\3\2\2\2\u013d\u0138\3\2\2"+
		"\2\u013d\u0139\3\2\2\2\u013d\u013a\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013c"+
		"\3\2\2\2\u013e!\3\2\2\2\u013f\u0147\7\67\2\2\u0140\u0147\7\66\2\2\u0141"+
		"\u0142\78\2\2\u0142\u0147\78\2\2\u0143\u0147\7;\2\2\u0144\u0147\7:\2\2"+
		"\u0145\u0147\7<\2\2\u0146\u013f\3\2\2\2\u0146\u0140\3\2\2\2\u0146\u0141"+
		"\3\2\2\2\u0146\u0143\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0145\3\2\2\2\u0147"+
		"#\3\2\2\2\u0148\u0149\t\2\2\2\u0149%\3\2\2\2\u014a\u014b\t\3\2\2\u014b"+
		"\'\3\2\2\2\u014c\u014d\7-\2\2\u014d\u0150\5(\25\2\u014e\u0150\7>\2\2\u014f"+
		"\u014c\3\2\2\2\u014f\u014e\3\2\2\2\u0150)\3\2\2\2\37-\639>@HPfisv\u0096"+
		"\u00a3\u00bc\u00be\u00d6\u00db\u00fc\u00ff\u0102\u010d\u0111\u0120\u0127"+
		"\u0129\u0134\u013d\u0146\u014f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}