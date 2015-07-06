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
		RULE_enumDecl = 14, RULE_comp = 15, RULE_boolOp = 16, RULE_prefix = 17, 
		RULE_derefID = 18;
	public static final String[] ruleNames = {
		"program", "progdef", "block", "topLevelBlock", "func", "typedparams", 
		"params", "call", "expr", "stat", "decl", "assign", "arrayVal", "type", 
		"enumDecl", "comp", "boolOp", "prefix", "derefID"
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
			setState(38); progdef();
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ENUM) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				{
				setState(41);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case SHARED:
				case TYPE:
					{
					setState(39); decl();
					}
					break;
				case ENUM:
					{
					setState(40); enumDecl();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(43); match(SEMI);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50); func();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ENUM) | (1L << DEF) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				setState(58);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case ENUM:
				case SHARED:
				case TYPE:
					{
					{
					setState(53);
					switch (_input.LA(1)) {
					case INT:
					case BOOL:
					case VOID:
					case SHARED:
					case TYPE:
						{
						setState(51); decl();
						}
						break;
					case ENUM:
						{
						setState(52); enumDecl();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(55); match(SEMI);
					}
					}
					break;
				case DEF:
					{
					setState(57); func();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(62);
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
			setState(63); match(PROGRAM);
			setState(64); match(ID);
			setState(68);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(65); match(LBRACE);
				setState(66); match(NUMBER);
				setState(67); match(RBRACE);
				}
			}

			setState(70); match(SEMI);
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
			setState(72); match(LCURL);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << FOR) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LOCK) | (1L << SHARED) | (1L << OUT) | (1L << IN) | (1L << LCURL) | (1L << TIMES) | (1L << TYPE) | (1L << ID))) != 0)) {
				{
				{
				setState(73); stat();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79); match(RCURL);
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
			setState(81); block();
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
			setState(83); match(DEF);
			setState(84); type(0);
			setState(85); match(ID);
			setState(86); typedparams();
			setState(87); topLevelBlock();
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
			setState(89); match(LBRACE);
			setState(101);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << TYPE))) != 0)) {
				{
				setState(90); type(0);
				setState(91); match(ID);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(92); match(COMMA);
					setState(93); type(0);
					setState(94); match(ID);
					}
					}
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(103); match(RBRACE);
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
			setState(105); match(LBRACE);
			setState(114);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << TYPE) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(106); expr(0);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(107); match(COMMA);
					setState(108); expr(0);
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(116); match(RBRACE);
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
			setState(118); match(ID);
			setState(119); params();
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
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new NegNumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(122); match(MINUS);
				setState(123); expr(22);
				}
				break;
			case 2:
				{
				_localctx = new NegBoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124); match(EXCLAMATION);
				setState(125); expr(21);
				}
				break;
			case 3:
				{
				_localctx = new RefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126); match(AMP);
				setState(127); expr(20);
				}
				break;
			case 4:
				{
				_localctx = new DerefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(TIMES);
				setState(129); expr(19);
				}
				break;
			case 5:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130); match(LBRACE);
				setState(131); expr(0);
				setState(132); match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); call();
				}
				break;
			case 7:
				{
				_localctx = new ConstArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135); match(ID);
				setState(136); match(LSQUARE);
				setState(137); match(NUMBER);
				setState(138); match(RSQUARE);
				}
				break;
			case 8:
				{
				_localctx = new ExprArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139); arrayVal();
				}
				break;
			case 9:
				{
				_localctx = new ArrayLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140); match(LSQUARE);
				setState(141); expr(0);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(142); match(COMMA);
					setState(143); expr(0);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(149); match(RSQUARE);
				}
				break;
			case 10:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151); match(ID);
				}
				break;
			case 11:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152); match(NUMBER);
				}
				break;
			case 12:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153); match(TRUE);
				}
				break;
			case 13:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154); match(FALSE);
				}
				break;
			case 14:
				{
				_localctx = new SpidExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155); match(SPID);
				}
				break;
			case 15:
				{
				_localctx = new EnumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156); match(TYPE);
				setState(157); match(DOT);
				setState(158); match(TYPE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(186);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(184);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(162); boolOp();
						setState(163); expr(19);
						}
						break;
					case 2:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(165);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(166); comp();
						setState(167); expr(18);
						}
						break;
					case 3:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(169);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(170); match(TIMES);
						setState(171); expr(17);
						}
						break;
					case 4:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(172);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(173); match(DIV);
						setState(174); expr(16);
						}
						break;
					case 5:
						{
						_localctx = new MinExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(175);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(176); match(MINUS);
						setState(177); expr(15);
						}
						break;
					case 6:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(178);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(179); match(PLUS);
						setState(180); expr(14);
						}
						break;
					case 7:
						{
						_localctx = new ModExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(181);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(182); match(MOD);
						setState(183); expr(13);
						}
						break;
					}
					} 
				}
				setState(188);
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
			setState(251);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(189); match(LOCK);
				setState(190); match(LBRACE);
				setState(191); match(ID);
				setState(192); match(RBRACE);
				setState(193); block();
				}
				break;
			case 2:
				_localctx = new DeclStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(194); decl();
				setState(195); match(SEMI);
				}
				break;
			case 3:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(197); assign();
				setState(198); match(SEMI);
				}
				break;
			case 4:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(200); match(IF);
				setState(201); expr(0);
				setState(202); block();
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(203); match(ELSE);
						setState(204); match(IF);
						setState(205); expr(0);
						setState(206); block();
						}
						} 
					}
					setState(212);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				setState(215);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(213); match(ELSE);
					setState(214); block();
					}
				}

				}
				break;
			case 5:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(217); match(WHILE);
				setState(218); expr(0);
				setState(219); block();
				}
				break;
			case 6:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(221); call();
				setState(222); match(SEMI);
				}
				break;
			case 7:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(224); block();
				}
				break;
			case 8:
				_localctx = new ForStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(225); match(FOR);
				setState(226); match(LBRACE);
				setState(227); decl();
				setState(228); match(SEMI);
				setState(229); expr(0);
				setState(230); match(SEMI);
				setState(231); assign();
				setState(232); match(RBRACE);
				setState(233); block();
				}
				break;
			case 9:
				_localctx = new OutStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(235); match(OUT);
				setState(236); match(LBRACE);
				setState(237); expr(0);
				setState(238); match(RBRACE);
				setState(239); match(SEMI);
				}
				break;
			case 10:
				_localctx = new InStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(241); match(IN);
				setState(242); match(LBRACE);
				setState(243); match(ID);
				setState(244); match(RBRACE);
				setState(245); match(SEMI);
				}
				break;
			case 11:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(246); match(RETURN);
				setState(248);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << TYPE) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(247); expr(0);
					}
				}

				setState(250); match(SEMI);
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
			setState(254);
			_la = _input.LA(1);
			if (_la==SHARED) {
				{
				setState(253); match(SHARED);
				}
			}

			setState(256); type(0);
			setState(257); match(ID);
			setState(258); match(EQ);
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(259); expr(0);
				}
				break;
			case 2:
				{
				setState(260); type(0);
				setState(261); match(LSQUARE);
				setState(262); match(NUMBER);
				setState(263); match(RSQUARE);
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
			setState(269);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(267); derefID();
				}
				break;
			case 2:
				{
				setState(268); arrayVal();
				}
				break;
			}
			setState(271); match(EQ);
			setState(272); expr(0);
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
			setState(274); match(ID);
			setState(275); match(LSQUARE);
			setState(276); expr(0);
			setState(277); match(RSQUARE);
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
			setState(284);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(280); match(INT);
				}
				break;
			case BOOL:
				{
				setState(281); match(BOOL);
				}
				break;
			case VOID:
				{
				setState(282); match(VOID);
				}
				break;
			case TYPE:
				{
				setState(283); match(TYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(293);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(291);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(286);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(287); match(TIMES);
						}
						break;
					case 2:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(288);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(289); match(LSQUARE);
						setState(290); match(RSQUARE);
						}
						break;
					}
					} 
				}
				setState(295);
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
			setState(296); match(ENUM);
			setState(297); match(TYPE);
			setState(298); match(COLON);
			setState(299); match(TYPE);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(300); match(COMMA);
				setState(301); match(TYPE);
				}
				}
				setState(306);
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
		enterRule(_localctx, 30, RULE_comp);
		try {
			setState(314);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(307); match(LT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 2);
				{
				setState(308); match(GT);
				}
				break;
			case EQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(309); match(EQ);
				setState(310); match(EQ);
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 4);
				{
				setState(311); match(LE);
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 5);
				{
				setState(312); match(GE);
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 6);
				{
				setState(313); match(NE);
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
		enterRule(_localctx, 32, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
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
		enterRule(_localctx, 34, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
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
		enterRule(_localctx, 36, RULE_derefID);
		try {
			setState(323);
			switch (_input.LA(1)) {
			case TIMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(320); match(TIMES);
				setState(321); derefID();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(322); match(ID);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3A\u0148\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\7\2\60\n\2\f\2\16\2"+
		"\63\13\2\3\2\3\2\3\2\5\28\n\2\3\2\3\2\3\2\7\2=\n\2\f\2\16\2@\13\2\3\3"+
		"\3\3\3\3\3\3\3\3\5\3G\n\3\3\3\3\3\3\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7c"+
		"\n\7\f\7\16\7f\13\7\5\7h\n\7\3\7\3\7\3\b\3\b\3\b\3\b\7\bp\n\b\f\b\16\b"+
		"s\13\b\5\bu\n\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0093\n"+
		"\n\f\n\16\n\u0096\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a2"+
		"\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00bb\n\n\f\n\16\n\u00be\13\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\7\13\u00d3\n\13\f\13\16\13\u00d6\13\13\3\13\3\13\5\13"+
		"\u00da\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u00fb\n\13\3\13\5\13\u00fe\n\13\3\f\5\f"+
		"\u0101\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u010c\n\f\3\r\3\r\5"+
		"\r\u0110\n\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u011f\n\17\3\17\3\17\3\17\3\17\3\17\7\17\u0126\n\17\f\17\16"+
		"\17\u0129\13\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0131\n\20\f\20\16"+
		"\20\u0134\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u013d\n\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\24\5\24\u0146\n\24\3\24\2\4\22\34\25\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\4\3\2\16\23\4\2\24\24,,\u0171"+
		"\2(\3\2\2\2\4A\3\2\2\2\6J\3\2\2\2\bS\3\2\2\2\nU\3\2\2\2\f[\3\2\2\2\16"+
		"k\3\2\2\2\20x\3\2\2\2\22\u00a1\3\2\2\2\24\u00fd\3\2\2\2\26\u0100\3\2\2"+
		"\2\30\u010f\3\2\2\2\32\u0114\3\2\2\2\34\u011e\3\2\2\2\36\u012a\3\2\2\2"+
		" \u013c\3\2\2\2\"\u013e\3\2\2\2$\u0140\3\2\2\2&\u0145\3\2\2\2(\61\5\4"+
		"\3\2),\5\26\f\2*,\5\36\20\2+)\3\2\2\2+*\3\2\2\2,-\3\2\2\2-.\7\"\2\2.\60"+
		"\3\2\2\2/+\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2"+
		"\2\63\61\3\2\2\2\64>\5\n\6\2\658\5\26\f\2\668\5\36\20\2\67\65\3\2\2\2"+
		"\67\66\3\2\2\289\3\2\2\29:\7\"\2\2:=\3\2\2\2;=\5\n\6\2<\67\3\2\2\2<;\3"+
		"\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\3\3\2\2\2@>\3\2\2\2AB\7\33\2\2B"+
		"F\7>\2\2CD\7%\2\2DE\7?\2\2EG\7&\2\2FC\3\2\2\2FG\3\2\2\2GH\3\2\2\2HI\7"+
		"\"\2\2I\5\3\2\2\2JN\7\'\2\2KM\5\24\13\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2"+
		"NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7(\2\2R\7\3\2\2\2ST\5\6\4\2T\t\3\2\2"+
		"\2UV\7\27\2\2VW\5\34\17\2WX\7>\2\2XY\5\f\7\2YZ\5\b\5\2Z\13\3\2\2\2[g\7"+
		"%\2\2\\]\5\34\17\2]d\7>\2\2^_\7!\2\2_`\5\34\17\2`a\7>\2\2ac\3\2\2\2b^"+
		"\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eh\3\2\2\2fd\3\2\2\2g\\\3\2\2\2"+
		"gh\3\2\2\2hi\3\2\2\2ij\7&\2\2j\r\3\2\2\2kt\7%\2\2lq\5\22\n\2mn\7!\2\2"+
		"np\5\22\n\2om\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2sq\3\2\2"+
		"\2tl\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7&\2\2w\17\3\2\2\2xy\7>\2\2yz\5\16"+
		"\b\2z\21\3\2\2\2{|\b\n\1\2|}\7,\2\2}\u00a2\5\22\n\30~\177\7$\2\2\177\u00a2"+
		"\5\22\n\27\u0080\u0081\7\60\2\2\u0081\u00a2\5\22\n\26\u0082\u0083\7-\2"+
		"\2\u0083\u00a2\5\22\n\25\u0084\u0085\7%\2\2\u0085\u0086\5\22\n\2\u0086"+
		"\u0087\7&\2\2\u0087\u00a2\3\2\2\2\u0088\u00a2\5\20\t\2\u0089\u008a\7>"+
		"\2\2\u008a\u008b\7)\2\2\u008b\u008c\7?\2\2\u008c\u00a2\7*\2\2\u008d\u00a2"+
		"\5\32\16\2\u008e\u008f\7)\2\2\u008f\u0094\5\22\n\2\u0090\u0091\7!\2\2"+
		"\u0091\u0093\5\22\n\2\u0092\u0090\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097"+
		"\u0098\7*\2\2\u0098\u00a2\3\2\2\2\u0099\u00a2\7>\2\2\u009a\u00a2\7?\2"+
		"\2\u009b\u00a2\7\f\2\2\u009c\u00a2\7\r\2\2\u009d\u00a2\7\34\2\2\u009e"+
		"\u009f\7=\2\2\u009f\u00a0\7 \2\2\u00a0\u00a2\7=\2\2\u00a1{\3\2\2\2\u00a1"+
		"~\3\2\2\2\u00a1\u0080\3\2\2\2\u00a1\u0082\3\2\2\2\u00a1\u0084\3\2\2\2"+
		"\u00a1\u0088\3\2\2\2\u00a1\u0089\3\2\2\2\u00a1\u008d\3\2\2\2\u00a1\u008e"+
		"\3\2\2\2\u00a1\u0099\3\2\2\2\u00a1\u009a\3\2\2\2\u00a1\u009b\3\2\2\2\u00a1"+
		"\u009c\3\2\2\2\u00a1\u009d\3\2\2\2\u00a1\u009e\3\2\2\2\u00a2\u00bc\3\2"+
		"\2\2\u00a3\u00a4\f\24\2\2\u00a4\u00a5\5\"\22\2\u00a5\u00a6\5\22\n\25\u00a6"+
		"\u00bb\3\2\2\2\u00a7\u00a8\f\23\2\2\u00a8\u00a9\5 \21\2\u00a9\u00aa\5"+
		"\22\n\24\u00aa\u00bb\3\2\2\2\u00ab\u00ac\f\22\2\2\u00ac\u00ad\7-\2\2\u00ad"+
		"\u00bb\5\22\n\23\u00ae\u00af\f\21\2\2\u00af\u00b0\7.\2\2\u00b0\u00bb\5"+
		"\22\n\22\u00b1\u00b2\f\20\2\2\u00b2\u00b3\7,\2\2\u00b3\u00bb\5\22\n\21"+
		"\u00b4\u00b5\f\17\2\2\u00b5\u00b6\7+\2\2\u00b6\u00bb\5\22\n\20\u00b7\u00b8"+
		"\f\16\2\2\u00b8\u00b9\7/\2\2\u00b9\u00bb\5\22\n\17\u00ba\u00a3\3\2\2\2"+
		"\u00ba\u00a7\3\2\2\2\u00ba\u00ab\3\2\2\2\u00ba\u00ae\3\2\2\2\u00ba\u00b1"+
		"\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b7\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\23\3\2\2\2\u00be\u00bc\3\2\2"+
		"\2\u00bf\u00c0\7\30\2\2\u00c0\u00c1\7%\2\2\u00c1\u00c2\7>\2\2\u00c2\u00c3"+
		"\7&\2\2\u00c3\u00fe\5\6\4\2\u00c4\u00c5\5\26\f\2\u00c5\u00c6\7\"\2\2\u00c6"+
		"\u00fe\3\2\2\2\u00c7\u00c8\5\30\r\2\u00c8\u00c9\7\"\2\2\u00c9\u00fe\3"+
		"\2\2\2\u00ca\u00cb\7\n\2\2\u00cb\u00cc\5\22\n\2\u00cc\u00d4\5\6\4\2\u00cd"+
		"\u00ce\7\13\2\2\u00ce\u00cf\7\n\2\2\u00cf\u00d0\5\22\n\2\u00d0\u00d1\5"+
		"\6\4\2\u00d1\u00d3\3\2\2\2\u00d2\u00cd\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d9\3\2\2\2\u00d6\u00d4\3\2"+
		"\2\2\u00d7\u00d8\7\13\2\2\u00d8\u00da\5\6\4\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00fe\3\2\2\2\u00db\u00dc\7\t\2\2\u00dc\u00dd\5\22"+
		"\n\2\u00dd\u00de\5\6\4\2\u00de\u00fe\3\2\2\2\u00df\u00e0\5\20\t\2\u00e0"+
		"\u00e1\7\"\2\2\u00e1\u00fe\3\2\2\2\u00e2\u00fe\5\6\4\2\u00e3\u00e4\7\b"+
		"\2\2\u00e4\u00e5\7%\2\2\u00e5\u00e6\5\26\f\2\u00e6\u00e7\7\"\2\2\u00e7"+
		"\u00e8\5\22\n\2\u00e8\u00e9\7\"\2\2\u00e9\u00ea\5\30\r\2\u00ea\u00eb\7"+
		"&\2\2\u00eb\u00ec\5\6\4\2\u00ec\u00fe\3\2\2\2\u00ed\u00ee\7\35\2\2\u00ee"+
		"\u00ef\7%\2\2\u00ef\u00f0\5\22\n\2\u00f0\u00f1\7&\2\2\u00f1\u00f2\7\""+
		"\2\2\u00f2\u00fe\3\2\2\2\u00f3\u00f4\7\36\2\2\u00f4\u00f5\7%\2\2\u00f5"+
		"\u00f6\7>\2\2\u00f6\u00f7\7&\2\2\u00f7\u00fe\7\"\2\2\u00f8\u00fa\7\25"+
		"\2\2\u00f9\u00fb\5\22\n\2\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fe\7\"\2\2\u00fd\u00bf\3\2\2\2\u00fd\u00c4\3\2"+
		"\2\2\u00fd\u00c7\3\2\2\2\u00fd\u00ca\3\2\2\2\u00fd\u00db\3\2\2\2\u00fd"+
		"\u00df\3\2\2\2\u00fd\u00e2\3\2\2\2\u00fd\u00e3\3\2\2\2\u00fd\u00ed\3\2"+
		"\2\2\u00fd\u00f3\3\2\2\2\u00fd\u00f8\3\2\2\2\u00fe\25\3\2\2\2\u00ff\u0101"+
		"\7\32\2\2\u0100\u00ff\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2\2\2"+
		"\u0102\u0103\5\34\17\2\u0103\u0104\7>\2\2\u0104\u010b\78\2\2\u0105\u010c"+
		"\5\22\n\2\u0106\u0107\5\34\17\2\u0107\u0108\7)\2\2\u0108\u0109\7?\2\2"+
		"\u0109\u010a\7*\2\2\u010a\u010c\3\2\2\2\u010b\u0105\3\2\2\2\u010b\u0106"+
		"\3\2\2\2\u010c\27\3\2\2\2\u010d\u0110\5&\24\2\u010e\u0110\5\32\16\2\u010f"+
		"\u010d\3\2\2\2\u010f\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\78"+
		"\2\2\u0112\u0113\5\22\n\2\u0113\31\3\2\2\2\u0114\u0115\7>\2\2\u0115\u0116"+
		"\7)\2\2\u0116\u0117\5\22\n\2\u0117\u0118\7*\2\2\u0118\33\3\2\2\2\u0119"+
		"\u011a\b\17\1\2\u011a\u011f\7\3\2\2\u011b\u011f\7\4\2\2\u011c\u011f\7"+
		"\5\2\2\u011d\u011f\7=\2\2\u011e\u0119\3\2\2\2\u011e\u011b\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011d\3\2\2\2\u011f\u0127\3\2\2\2\u0120\u0121\f\4"+
		"\2\2\u0121\u0126\7-\2\2\u0122\u0123\f\3\2\2\u0123\u0124\7)\2\2\u0124\u0126"+
		"\7*\2\2\u0125\u0120\3\2\2\2\u0125\u0122\3\2\2\2\u0126\u0129\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\35\3\2\2\2\u0129\u0127\3\2\2"+
		"\2\u012a\u012b\7\7\2\2\u012b\u012c\7=\2\2\u012c\u012d\7#\2\2\u012d\u0132"+
		"\7=\2\2\u012e\u012f\7!\2\2\u012f\u0131\7=\2\2\u0130\u012e\3\2\2\2\u0131"+
		"\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133\37\3\2\2"+
		"\2\u0134\u0132\3\2\2\2\u0135\u013d\7\67\2\2\u0136\u013d\7\66\2\2\u0137"+
		"\u0138\78\2\2\u0138\u013d\78\2\2\u0139\u013d\7;\2\2\u013a\u013d\7:\2\2"+
		"\u013b\u013d\7<\2\2\u013c\u0135\3\2\2\2\u013c\u0136\3\2\2\2\u013c\u0137"+
		"\3\2\2\2\u013c\u0139\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d"+
		"!\3\2\2\2\u013e\u013f\t\2\2\2\u013f#\3\2\2\2\u0140\u0141\t\3\2\2\u0141"+
		"%\3\2\2\2\u0142\u0143\7-\2\2\u0143\u0146\5&\24\2\u0144\u0146\7>\2\2\u0145"+
		"\u0142\3\2\2\2\u0145\u0144\3\2\2\2\u0146\'\3\2\2\2\36+\61\67<>FNdgqt\u0094"+
		"\u00a1\u00ba\u00bc\u00d4\u00d9\u00fa\u00fd\u0100\u010b\u010f\u011e\u0125"+
		"\u0127\u0132\u013c\u0145";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}