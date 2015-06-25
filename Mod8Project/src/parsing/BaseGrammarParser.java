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
		INT=1, BOOL=2, VOID=3, STRING=4, FOR=5, WHILE=6, IF=7, ELSE=8, TRUE=9, 
		FALSE=10, AND=11, OR=12, XOR=13, NOT=14, RETURN=15, BREAK=16, DEF=17, 
		LOCK=18, UNLOCK=19, SHARED=20, PROGRAM=21, SPID=22, OUT=23, IN=24, UNDERSCORE=25, 
		DOT=26, COMMA=27, SEMI=28, EXCLAMATION=29, LBRACE=30, RBRACE=31, LCURL=32, 
		RCURL=33, LSQUARE=34, RSQUARE=35, PLUS=36, MINUS=37, TIMES=38, DIV=39, 
		MOD=40, AMP=41, SQUOT=42, DQUOT=43, BACKSLASH=44, TIDLE=45, HAT=46, GT=47, 
		LT=48, EQ=49, ARROW=50, GE=51, LE=52, NE=53, TYPE=54, ID=55, NUMBER=56, 
		WS=57;
	public static final String[] tokenNames = {
		"<INVALID>", "INT", "BOOL", "VOID", "STRING", "FOR", "WHILE", "IF", "ELSE", 
		"TRUE", "FALSE", "AND", "OR", "XOR", "NOT", "RETURN", "BREAK", "DEF", 
		"LOCK", "UNLOCK", "SHARED", "PROGRAM", "SPID", "OUT", "IN", "'_'", "'.'", 
		"','", "';'", "'!'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'&'", "'''", "'\"'", "'\\'", "'~'", "'^'", 
		"'>'", "'<'", "'='", "ARROW", "GE", "LE", "NE", "TYPE", "ID", "NUMBER", 
		"WS"
	};
	public static final int
		RULE_program = 0, RULE_progdef = 1, RULE_block = 2, RULE_topLevelBlock = 3, 
		RULE_func = 4, RULE_typedparams = 5, RULE_params = 6, RULE_call = 7, RULE_expr = 8, 
		RULE_stat = 9, RULE_decl = 10, RULE_assign = 11, RULE_type = 12, RULE_val = 13, 
		RULE_comp = 14, RULE_boolOp = 15, RULE_prefix = 16, RULE_derefID = 17;
	public static final String[] ruleNames = {
		"program", "progdef", "block", "topLevelBlock", "func", "typedparams", 
		"params", "call", "expr", "stat", "decl", "assign", "type", "val", "comp", 
		"boolOp", "prefix", "derefID"
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
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public List<TerminalNode> SEMI() { return getTokens(BaseGrammarParser.SEMI); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public TerminalNode SEMI(int i) {
			return getToken(BaseGrammarParser.SEMI, i);
		}
		public ProgdefContext progdef() {
			return getRuleContext(ProgdefContext.class,0);
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
			setState(36); progdef();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				{
				setState(37); decl();
				setState(38); match(SEMI);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45); func();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << DEF) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				setState(50);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case SHARED:
				case TYPE:
					{
					{
					setState(46); decl();
					setState(47); match(SEMI);
					}
					}
					break;
				case DEF:
					{
					setState(49); func();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54);
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
			setState(55); match(PROGRAM);
			setState(56); match(ID);
			setState(60);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(57); match(LBRACE);
				setState(58); match(NUMBER);
				setState(59); match(RBRACE);
				}
			}

			setState(62); match(SEMI);
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
			setState(64); match(LCURL);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << FOR) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LOCK) | (1L << SHARED) | (1L << OUT) | (1L << IN) | (1L << LCURL) | (1L << TIMES) | (1L << TYPE) | (1L << ID))) != 0)) {
				{
				{
				setState(65); stat();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71); match(RCURL);
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
			setState(73); block();
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
			setState(75); match(DEF);
			setState(76); type(0);
			setState(77); match(ID);
			setState(78); typedparams();
			setState(79); topLevelBlock();
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
			setState(81); match(LBRACE);
			setState(93);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << TYPE))) != 0)) {
				{
				setState(82); type(0);
				setState(83); match(ID);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(84); match(COMMA);
					setState(85); type(0);
					setState(86); match(ID);
					}
					}
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(95); match(RBRACE);
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
		public TerminalNode LBRACE() { return getToken(BaseGrammarParser.LBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(BaseGrammarParser.COMMA); }
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
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
			setState(97); match(LBRACE);
			setState(106);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(98); val();
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(99); match(COMMA);
					setState(100); val();
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(108); match(RBRACE);
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
			setState(110); match(ID);
			setState(111); params();
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new NegNumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(114); match(MINUS);
				setState(115); expr(17);
				}
				break;
			case 2:
				{
				_localctx = new NegBoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116); match(EXCLAMATION);
				setState(117); expr(16);
				}
				break;
			case 3:
				{
				_localctx = new RefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118); match(AMP);
				setState(119); expr(15);
				}
				break;
			case 4:
				{
				_localctx = new DerefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120); match(TIMES);
				setState(121); expr(14);
				}
				break;
			case 5:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(LBRACE);
				setState(123); expr(0);
				setState(124); match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126); call();
				}
				break;
			case 7:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127); match(ID);
				}
				break;
			case 8:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); match(NUMBER);
				}
				break;
			case 9:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129); match(TRUE);
				}
				break;
			case 10:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130); match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(156);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(134); match(TIMES);
						setState(135); expr(14);
						}
						break;
					case 2:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(136);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(137); match(DIV);
						setState(138); expr(13);
						}
						break;
					case 3:
						{
						_localctx = new MinExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(139);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(140); match(MINUS);
						setState(141); expr(12);
						}
						break;
					case 4:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(143); match(PLUS);
						setState(144); expr(11);
						}
						break;
					case 5:
						{
						_localctx = new ModExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(146); match(MOD);
						setState(147); expr(10);
						}
						break;
					case 6:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(149); boolOp();
						setState(150); expr(9);
						}
						break;
					case 7:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(153); comp();
						setState(154); expr(8);
						}
						break;
					}
					} 
				}
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
			setState(223);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(161); match(LOCK);
				setState(162); match(LBRACE);
				setState(163); match(ID);
				setState(164); match(RBRACE);
				setState(165); block();
				}
				break;
			case 2:
				_localctx = new DeclStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(166); decl();
				setState(167); match(SEMI);
				}
				break;
			case 3:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(169); assign();
				setState(170); match(SEMI);
				}
				break;
			case 4:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(172); match(IF);
				setState(173); expr(0);
				setState(174); block();
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(175); match(ELSE);
						setState(176); match(IF);
						setState(177); expr(0);
						setState(178); block();
						}
						} 
					}
					setState(184);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(187);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(185); match(ELSE);
					setState(186); block();
					}
				}

				}
				break;
			case 5:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(189); match(WHILE);
				setState(190); expr(0);
				setState(191); block();
				}
				break;
			case 6:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(193); call();
				setState(194); match(SEMI);
				}
				break;
			case 7:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(196); block();
				}
				break;
			case 8:
				_localctx = new ForStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(197); match(FOR);
				setState(198); match(LBRACE);
				setState(199); decl();
				setState(200); match(SEMI);
				setState(201); expr(0);
				setState(202); match(SEMI);
				setState(203); assign();
				setState(204); match(RBRACE);
				setState(205); block();
				}
				break;
			case 9:
				_localctx = new OutStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(207); match(OUT);
				setState(208); match(LBRACE);
				setState(209); expr(0);
				setState(210); match(RBRACE);
				setState(211); match(SEMI);
				}
				break;
			case 10:
				_localctx = new InStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(213); match(IN);
				setState(214); match(LBRACE);
				setState(215); match(ID);
				setState(216); match(RBRACE);
				setState(217); match(SEMI);
				}
				break;
			case 11:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(218); match(RETURN);
				setState(220);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(219); expr(0);
					}
				}

				setState(222); match(SEMI);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode EQ() { return getToken(BaseGrammarParser.EQ, 0); }
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
			setState(226);
			_la = _input.LA(1);
			if (_la==SHARED) {
				{
				setState(225); match(SHARED);
				}
			}

			setState(228); type(0);
			setState(229); match(ID);
			setState(230); match(EQ);
			setState(231); expr(0);
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
			setState(233); derefID();
			setState(234); match(EQ);
			setState(235); expr(0);
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
		public TerminalNode TIMES() { return getToken(BaseGrammarParser.TIMES, 0); }
		public TerminalNode TYPE() { return getToken(BaseGrammarParser.TYPE, 0); }
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(238); match(INT);
				}
				break;
			case BOOL:
				{
				setState(239); match(BOOL);
				}
				break;
			case VOID:
				{
				setState(240); match(VOID);
				}
				break;
			case TYPE:
				{
				setState(241); match(TYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(244);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(245); match(TIMES);
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class ValContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(BaseGrammarParser.ID, 0); }
		public TerminalNode SPID() { return getToken(BaseGrammarParser.SPID, 0); }
		public TerminalNode FALSE() { return getToken(BaseGrammarParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(BaseGrammarParser.TRUE, 0); }
		public TerminalNode NUMBER() { return getToken(BaseGrammarParser.NUMBER, 0); }
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_val);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << SPID) | (1L << ID) | (1L << NUMBER))) != 0)) ) {
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
		enterRule(_localctx, 28, RULE_comp);
		try {
			setState(260);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(253); match(LT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 2);
				{
				setState(254); match(GT);
				}
				break;
			case EQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(255); match(EQ);
				setState(256); match(EQ);
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 4);
				{
				setState(257); match(LE);
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 5);
				{
				setState(258); match(GE);
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 6);
				{
				setState(259); match(NE);
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
		public TerminalNode XOR() { return getToken(BaseGrammarParser.XOR, 0); }
		public TerminalNode AND() { return getToken(BaseGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(BaseGrammarParser.OR, 0); }
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
		enterRule(_localctx, 30, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
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
		enterRule(_localctx, 32, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
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
		enterRule(_localctx, 34, RULE_derefID);
		try {
			setState(269);
			switch (_input.LA(1)) {
			case TIMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(266); match(TIMES);
				setState(267); derefID();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(268); match(ID);
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
		case 12: return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 13);
		case 1: return precpred(_ctx, 12);
		case 2: return precpred(_ctx, 11);
		case 3: return precpred(_ctx, 10);
		case 4: return precpred(_ctx, 9);
		case 5: return precpred(_ctx, 8);
		case 6: return precpred(_ctx, 7);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3;\u0112\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\7\2+\n\2\f\2\16\2.\13\2\3\2\3\2\3\2\3\2\3\2"+
		"\7\2\65\n\2\f\2\16\28\13\2\3\3\3\3\3\3\3\3\3\3\5\3?\n\3\3\3\3\3\3\4\3"+
		"\4\7\4E\n\4\f\4\16\4H\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\7\7[\n\7\f\7\16\7^\13\7\5\7`\n\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\7\bh\n\b\f\b\16\bk\13\b\5\bm\n\b\3\b\3\b\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u0086\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u009f\n\n\f\n\16\n\u00a2\13\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13\u00b7\n\13\f\13\16\13\u00ba\13\13\3\13\3"+
		"\13\5\13\u00be\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00df\n\13\3\13\5\13\u00e2\n"+
		"\13\3\f\5\f\u00e5\n\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u00f5\n\16\3\16\3\16\7\16\u00f9\n\16\f\16\16\16\u00fc"+
		"\13\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0107\n\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\5\23\u0110\n\23\3\23\2\4\22\32\24\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\5\5\2\13\f\30\309:\3\2\r\17"+
		"\4\2\20\20\'\'\u0130\2&\3\2\2\2\49\3\2\2\2\6B\3\2\2\2\bK\3\2\2\2\nM\3"+
		"\2\2\2\fS\3\2\2\2\16c\3\2\2\2\20p\3\2\2\2\22\u0085\3\2\2\2\24\u00e1\3"+
		"\2\2\2\26\u00e4\3\2\2\2\30\u00eb\3\2\2\2\32\u00f4\3\2\2\2\34\u00fd\3\2"+
		"\2\2\36\u0106\3\2\2\2 \u0108\3\2\2\2\"\u010a\3\2\2\2$\u010f\3\2\2\2&,"+
		"\5\4\3\2\'(\5\26\f\2()\7\36\2\2)+\3\2\2\2*\'\3\2\2\2+.\3\2\2\2,*\3\2\2"+
		"\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\66\5\n\6\2\60\61\5\26\f\2\61\62\7\36"+
		"\2\2\62\65\3\2\2\2\63\65\5\n\6\2\64\60\3\2\2\2\64\63\3\2\2\2\658\3\2\2"+
		"\2\66\64\3\2\2\2\66\67\3\2\2\2\67\3\3\2\2\28\66\3\2\2\29:\7\27\2\2:>\7"+
		"9\2\2;<\7 \2\2<=\7:\2\2=?\7!\2\2>;\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7\36"+
		"\2\2A\5\3\2\2\2BF\7\"\2\2CE\5\24\13\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG"+
		"\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7#\2\2J\7\3\2\2\2KL\5\6\4\2L\t\3\2\2\2"+
		"MN\7\23\2\2NO\5\32\16\2OP\79\2\2PQ\5\f\7\2QR\5\b\5\2R\13\3\2\2\2S_\7 "+
		"\2\2TU\5\32\16\2U\\\79\2\2VW\7\35\2\2WX\5\32\16\2XY\79\2\2Y[\3\2\2\2Z"+
		"V\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]`\3\2\2\2^\\\3\2\2\2_T\3\2\2"+
		"\2_`\3\2\2\2`a\3\2\2\2ab\7!\2\2b\r\3\2\2\2cl\7 \2\2di\5\34\17\2ef\7\35"+
		"\2\2fh\5\34\17\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jm\3\2\2\2ki\3"+
		"\2\2\2ld\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\7!\2\2o\17\3\2\2\2pq\79\2\2qr\5"+
		"\16\b\2r\21\3\2\2\2st\b\n\1\2tu\7\'\2\2u\u0086\5\22\n\23vw\7\37\2\2w\u0086"+
		"\5\22\n\22xy\7+\2\2y\u0086\5\22\n\21z{\7(\2\2{\u0086\5\22\n\20|}\7 \2"+
		"\2}~\5\22\n\2~\177\7!\2\2\177\u0086\3\2\2\2\u0080\u0086\5\20\t\2\u0081"+
		"\u0086\79\2\2\u0082\u0086\7:\2\2\u0083\u0086\7\13\2\2\u0084\u0086\7\f"+
		"\2\2\u0085s\3\2\2\2\u0085v\3\2\2\2\u0085x\3\2\2\2\u0085z\3\2\2\2\u0085"+
		"|\3\2\2\2\u0085\u0080\3\2\2\2\u0085\u0081\3\2\2\2\u0085\u0082\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0084\3\2\2\2\u0086\u00a0\3\2\2\2\u0087\u0088"+
		"\f\17\2\2\u0088\u0089\7(\2\2\u0089\u009f\5\22\n\20\u008a\u008b\f\16\2"+
		"\2\u008b\u008c\7)\2\2\u008c\u009f\5\22\n\17\u008d\u008e\f\r\2\2\u008e"+
		"\u008f\7\'\2\2\u008f\u009f\5\22\n\16\u0090\u0091\f\f\2\2\u0091\u0092\7"+
		"&\2\2\u0092\u009f\5\22\n\r\u0093\u0094\f\13\2\2\u0094\u0095\7*\2\2\u0095"+
		"\u009f\5\22\n\f\u0096\u0097\f\n\2\2\u0097\u0098\5 \21\2\u0098\u0099\5"+
		"\22\n\13\u0099\u009f\3\2\2\2\u009a\u009b\f\t\2\2\u009b\u009c\5\36\20\2"+
		"\u009c\u009d\5\22\n\n\u009d\u009f\3\2\2\2\u009e\u0087\3\2\2\2\u009e\u008a"+
		"\3\2\2\2\u009e\u008d\3\2\2\2\u009e\u0090\3\2\2\2\u009e\u0093\3\2\2\2\u009e"+
		"\u0096\3\2\2\2\u009e\u009a\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\23\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4"+
		"\7\24\2\2\u00a4\u00a5\7 \2\2\u00a5\u00a6\79\2\2\u00a6\u00a7\7!\2\2\u00a7"+
		"\u00e2\5\6\4\2\u00a8\u00a9\5\26\f\2\u00a9\u00aa\7\36\2\2\u00aa\u00e2\3"+
		"\2\2\2\u00ab\u00ac\5\30\r\2\u00ac\u00ad\7\36\2\2\u00ad\u00e2\3\2\2\2\u00ae"+
		"\u00af\7\t\2\2\u00af\u00b0\5\22\n\2\u00b0\u00b8\5\6\4\2\u00b1\u00b2\7"+
		"\n\2\2\u00b2\u00b3\7\t\2\2\u00b3\u00b4\5\22\n\2\u00b4\u00b5\5\6\4\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bd\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb"+
		"\u00bc\7\n\2\2\u00bc\u00be\5\6\4\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00e2\3\2\2\2\u00bf\u00c0\7\b\2\2\u00c0\u00c1\5\22\n\2\u00c1"+
		"\u00c2\5\6\4\2\u00c2\u00e2\3\2\2\2\u00c3\u00c4\5\20\t\2\u00c4\u00c5\7"+
		"\36\2\2\u00c5\u00e2\3\2\2\2\u00c6\u00e2\5\6\4\2\u00c7\u00c8\7\7\2\2\u00c8"+
		"\u00c9\7 \2\2\u00c9\u00ca\5\26\f\2\u00ca\u00cb\7\36\2\2\u00cb\u00cc\5"+
		"\22\n\2\u00cc\u00cd\7\36\2\2\u00cd\u00ce\5\30\r\2\u00ce\u00cf\7!\2\2\u00cf"+
		"\u00d0\5\6\4\2\u00d0\u00e2\3\2\2\2\u00d1\u00d2\7\31\2\2\u00d2\u00d3\7"+
		" \2\2\u00d3\u00d4\5\22\n\2\u00d4\u00d5\7!\2\2\u00d5\u00d6\7\36\2\2\u00d6"+
		"\u00e2\3\2\2\2\u00d7\u00d8\7\32\2\2\u00d8\u00d9\7 \2\2\u00d9\u00da\79"+
		"\2\2\u00da\u00db\7!\2\2\u00db\u00e2\7\36\2\2\u00dc\u00de\7\21\2\2\u00dd"+
		"\u00df\5\22\n\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3"+
		"\2\2\2\u00e0\u00e2\7\36\2\2\u00e1\u00a3\3\2\2\2\u00e1\u00a8\3\2\2\2\u00e1"+
		"\u00ab\3\2\2\2\u00e1\u00ae\3\2\2\2\u00e1\u00bf\3\2\2\2\u00e1\u00c3\3\2"+
		"\2\2\u00e1\u00c6\3\2\2\2\u00e1\u00c7\3\2\2\2\u00e1\u00d1\3\2\2\2\u00e1"+
		"\u00d7\3\2\2\2\u00e1\u00dc\3\2\2\2\u00e2\25\3\2\2\2\u00e3\u00e5\7\26\2"+
		"\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7"+
		"\5\32\16\2\u00e7\u00e8\79\2\2\u00e8\u00e9\7\63\2\2\u00e9\u00ea\5\22\n"+
		"\2\u00ea\27\3\2\2\2\u00eb\u00ec\5$\23\2\u00ec\u00ed\7\63\2\2\u00ed\u00ee"+
		"\5\22\n\2\u00ee\31\3\2\2\2\u00ef\u00f0\b\16\1\2\u00f0\u00f5\7\3\2\2\u00f1"+
		"\u00f5\7\4\2\2\u00f2\u00f5\7\5\2\2\u00f3\u00f5\78\2\2\u00f4\u00ef\3\2"+
		"\2\2\u00f4\u00f1\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5"+
		"\u00fa\3\2\2\2\u00f6\u00f7\f\3\2\2\u00f7\u00f9\7(\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\33\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\t\2\2\2\u00fe\35\3\2\2\2\u00ff"+
		"\u0107\7\62\2\2\u0100\u0107\7\61\2\2\u0101\u0102\7\63\2\2\u0102\u0107"+
		"\7\63\2\2\u0103\u0107\7\66\2\2\u0104\u0107\7\65\2\2\u0105\u0107\7\67\2"+
		"\2\u0106\u00ff\3\2\2\2\u0106\u0100\3\2\2\2\u0106\u0101\3\2\2\2\u0106\u0103"+
		"\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2\u0107\37\3\2\2\2\u0108"+
		"\u0109\t\3\2\2\u0109!\3\2\2\2\u010a\u010b\t\4\2\2\u010b#\3\2\2\2\u010c"+
		"\u010d\7(\2\2\u010d\u0110\5$\23\2\u010e\u0110\79\2\2\u010f\u010c\3\2\2"+
		"\2\u010f\u010e\3\2\2\2\u0110%\3\2\2\2\27,\64\66>F\\_il\u0085\u009e\u00a0"+
		"\u00b8\u00bd\u00de\u00e1\u00e4\u00f4\u00fa\u0106\u010f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}