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
		COMMENT=57, WS=58;
	public static final String[] tokenNames = {
		"<INVALID>", "INT", "BOOL", "VOID", "STRING", "FOR", "WHILE", "IF", "ELSE", 
		"TRUE", "FALSE", "AND", "OR", "XOR", "NOT", "RETURN", "BREAK", "DEF", 
		"LOCK", "UNLOCK", "SHARED", "PROGRAM", "SPID", "OUT", "IN", "'_'", "'.'", 
		"','", "';'", "'!'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'&'", "'''", "'\"'", "'\\'", "'~'", "'^'", 
		"'>'", "'<'", "'='", "ARROW", "GE", "LE", "NE", "TYPE", "ID", "NUMBER", 
		"COMMENT", "WS"
	};
	public static final int
		RULE_program = 0, RULE_progdef = 1, RULE_block = 2, RULE_topLevelBlock = 3, 
		RULE_func = 4, RULE_typedparams = 5, RULE_params = 6, RULE_call = 7, RULE_expr = 8, 
		RULE_stat = 9, RULE_decl = 10, RULE_assign = 11, RULE_arrayVal = 12, RULE_type = 13, 
		RULE_val = 14, RULE_comp = 15, RULE_boolOp = 16, RULE_prefix = 17, RULE_derefID = 18;
	public static final String[] ruleNames = {
		"program", "progdef", "block", "topLevelBlock", "func", "typedparams", 
		"params", "call", "expr", "stat", "decl", "assign", "arrayVal", "type", 
		"val", "comp", "boolOp", "prefix", "derefID"
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
			setState(38); progdef();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				{
				setState(39); decl();
				setState(40); match(SEMI);
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47); func();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << DEF) | (1L << SHARED) | (1L << TYPE))) != 0)) {
				{
				setState(52);
				switch (_input.LA(1)) {
				case INT:
				case BOOL:
				case VOID:
				case SHARED:
				case TYPE:
					{
					{
					setState(48); decl();
					setState(49); match(SEMI);
					}
					}
					break;
				case DEF:
					{
					setState(51); func();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(56);
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
			setState(57); match(PROGRAM);
			setState(58); match(ID);
			setState(62);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(59); match(LBRACE);
				setState(60); match(NUMBER);
				setState(61); match(RBRACE);
				}
			}

			setState(64); match(SEMI);
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
			setState(66); match(LCURL);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << FOR) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << LOCK) | (1L << SHARED) | (1L << OUT) | (1L << IN) | (1L << LCURL) | (1L << TIMES) | (1L << TYPE) | (1L << ID))) != 0)) {
				{
				{
				setState(67); stat();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73); match(RCURL);
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
			setState(75); block();
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
			setState(77); match(DEF);
			setState(78); type(0);
			setState(79); match(ID);
			setState(80); typedparams();
			setState(81); topLevelBlock();
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
			setState(83); match(LBRACE);
			setState(95);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << TYPE))) != 0)) {
				{
				setState(84); type(0);
				setState(85); match(ID);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(86); match(COMMA);
					setState(87); type(0);
					setState(88); match(ID);
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(97); match(RBRACE);
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
			setState(99); match(LBRACE);
			setState(108);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(100); expr(0);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(101); match(COMMA);
					setState(102); expr(0);
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(110); match(RBRACE);
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
			setState(112); match(ID);
			setState(113); params();
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
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new NegNumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(116); match(MINUS);
				setState(117); expr(20);
				}
				break;
			case 2:
				{
				_localctx = new NegBoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118); match(EXCLAMATION);
				setState(119); expr(19);
				}
				break;
			case 3:
				{
				_localctx = new RefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120); match(AMP);
				setState(121); expr(18);
				}
				break;
			case 4:
				{
				_localctx = new DerefExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122); match(TIMES);
				setState(123); expr(17);
				}
				break;
			case 5:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124); match(LBRACE);
				setState(125); expr(0);
				setState(126); match(RBRACE);
				}
				break;
			case 6:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128); call();
				}
				break;
			case 7:
				{
				_localctx = new ConstArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129); match(ID);
				setState(130); match(LSQUARE);
				setState(131); match(NUMBER);
				setState(132); match(RSQUARE);
				}
				break;
			case 8:
				{
				_localctx = new ExprArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133); arrayVal();
				}
				break;
			case 9:
				{
				_localctx = new ArrayLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); match(LSQUARE);
				setState(135); expr(0);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(136); match(COMMA);
					setState(137); expr(0);
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143); match(RSQUARE);
				}
				break;
			case 10:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145); match(ID);
				}
				break;
			case 11:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146); match(NUMBER);
				}
				break;
			case 12:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147); match(TRUE);
				}
				break;
			case 13:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148); match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(174);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(151);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(152); match(TIMES);
						setState(153); expr(17);
						}
						break;
					case 2:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(155); match(DIV);
						setState(156); expr(16);
						}
						break;
					case 3:
						{
						_localctx = new MinExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(158); match(MINUS);
						setState(159); expr(15);
						}
						break;
					case 4:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(161); match(PLUS);
						setState(162); expr(14);
						}
						break;
					case 5:
						{
						_localctx = new ModExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(164); match(MOD);
						setState(165); expr(13);
						}
						break;
					case 6:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(166);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(167); boolOp();
						setState(168); expr(12);
						}
						break;
					case 7:
						{
						_localctx = new CompExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(170);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(171); comp();
						setState(172); expr(11);
						}
						break;
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
			setState(241);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(179); match(LOCK);
				setState(180); match(LBRACE);
				setState(181); match(ID);
				setState(182); match(RBRACE);
				setState(183); block();
				}
				break;
			case 2:
				_localctx = new DeclStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(184); decl();
				setState(185); match(SEMI);
				}
				break;
			case 3:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(187); assign();
				setState(188); match(SEMI);
				}
				break;
			case 4:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(190); match(IF);
				setState(191); expr(0);
				setState(192); block();
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(193); match(ELSE);
						setState(194); match(IF);
						setState(195); expr(0);
						setState(196); block();
						}
						} 
					}
					setState(202);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(205);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(203); match(ELSE);
					setState(204); block();
					}
				}

				}
				break;
			case 5:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(207); match(WHILE);
				setState(208); expr(0);
				setState(209); block();
				}
				break;
			case 6:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(211); call();
				setState(212); match(SEMI);
				}
				break;
			case 7:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(214); block();
				}
				break;
			case 8:
				_localctx = new ForStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(215); match(FOR);
				setState(216); match(LBRACE);
				setState(217); decl();
				setState(218); match(SEMI);
				setState(219); expr(0);
				setState(220); match(SEMI);
				setState(221); assign();
				setState(222); match(RBRACE);
				setState(223); block();
				}
				break;
			case 9:
				_localctx = new OutStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(225); match(OUT);
				setState(226); match(LBRACE);
				setState(227); expr(0);
				setState(228); match(RBRACE);
				setState(229); match(SEMI);
				}
				break;
			case 10:
				_localctx = new InStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(231); match(IN);
				setState(232); match(LBRACE);
				setState(233); match(ID);
				setState(234); match(RBRACE);
				setState(235); match(SEMI);
				}
				break;
			case 11:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(236); match(RETURN);
				setState(238);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << EXCLAMATION) | (1L << LBRACE) | (1L << LSQUARE) | (1L << MINUS) | (1L << TIMES) | (1L << AMP) | (1L << ID) | (1L << NUMBER))) != 0)) {
					{
					setState(237); expr(0);
					}
				}

				setState(240); match(SEMI);
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
			setState(244);
			_la = _input.LA(1);
			if (_la==SHARED) {
				{
				setState(243); match(SHARED);
				}
			}

			setState(246); type(0);
			setState(247); match(ID);
			setState(248); match(EQ);
			setState(255);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
			case EXCLAMATION:
			case LBRACE:
			case LSQUARE:
			case MINUS:
			case TIMES:
			case AMP:
			case ID:
			case NUMBER:
				{
				setState(249); expr(0);
				}
				break;
			case INT:
			case BOOL:
			case VOID:
			case TYPE:
				{
				setState(250); type(0);
				setState(251); match(LSQUARE);
				setState(252); match(NUMBER);
				setState(253); match(RSQUARE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(259);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(257); derefID();
				}
				break;
			case 2:
				{
				setState(258); arrayVal();
				}
				break;
			}
			setState(261); match(EQ);
			setState(262); expr(0);
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
			setState(264); match(ID);
			setState(265); match(LSQUARE);
			setState(266); expr(0);
			setState(267); match(RSQUARE);
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
			setState(274);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(270); match(INT);
				}
				break;
			case BOOL:
				{
				setState(271); match(BOOL);
				}
				break;
			case VOID:
				{
				setState(272); match(VOID);
				}
				break;
			case TYPE:
				{
				setState(273); match(TYPE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(283);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(281);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(276);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(277); match(TIMES);
						}
						break;
					case 2:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(278);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(279); match(LSQUARE);
						setState(280); match(RSQUARE);
						}
						break;
					}
					} 
				}
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 28, RULE_val);
		try {
			setState(292);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				_localctx = new NumValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(286); match(NUMBER);
				}
				break;
			case 2:
				_localctx = new IdValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(287); derefID();
				}
				break;
			case 3:
				_localctx = new TrueValContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(288); match(TRUE);
				}
				break;
			case 4:
				_localctx = new FalseValContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(289); match(FALSE);
				}
				break;
			case 5:
				_localctx = new SpidValContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(290); match(SPID);
				}
				break;
			case 6:
				_localctx = new ArrValContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(291); arrayVal();
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
		enterRule(_localctx, 30, RULE_comp);
		try {
			setState(301);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(294); match(LT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 2);
				{
				setState(295); match(GT);
				}
				break;
			case EQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(296); match(EQ);
				setState(297); match(EQ);
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 4);
				{
				setState(298); match(LE);
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 5);
				{
				setState(299); match(GE);
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 6);
				{
				setState(300); match(NE);
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
		enterRule(_localctx, 32, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
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
		enterRule(_localctx, 34, RULE_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
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
			setState(310);
			switch (_input.LA(1)) {
			case TIMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(307); match(TIMES);
				setState(308); derefID();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(309); match(ID);
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
		case 0: return precpred(_ctx, 16);
		case 1: return precpred(_ctx, 15);
		case 2: return precpred(_ctx, 14);
		case 3: return precpred(_ctx, 13);
		case 4: return precpred(_ctx, 12);
		case 5: return precpred(_ctx, 11);
		case 6: return precpred(_ctx, 10);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u013b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3\2"+
		"\3\2\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\3\3\3\3\3\3\3\3\3\5\3A\n\3\3"+
		"\3\3\3\3\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7]\n\7\f\7\16\7`\13\7\5\7b\n\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\7\bj\n\b\f\b\16\bm\13\b\5\bo\n\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u008d\n\n\f\n\16\n\u0090\13\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\n\u0098\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b1\n\n\f"+
		"\n\16\n\u00b4\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c9\n\13\f\13\16\13"+
		"\u00cc\13\13\3\13\3\13\5\13\u00d0\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00f1\n\13"+
		"\3\13\5\13\u00f4\n\13\3\f\5\f\u00f7\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0102\n\f\3\r\3\r\5\r\u0106\n\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17\u0115\n\17\3\17\3\17\3\17\3\17"+
		"\3\17\7\17\u011c\n\17\f\17\16\17\u011f\13\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u0127\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0130\n"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\5\24\u0139\n\24\3\24\2\4\22\34"+
		"\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\4\3\2\r\17\4\2\20\20"+
		"\'\'\u0164\2(\3\2\2\2\4;\3\2\2\2\6D\3\2\2\2\bM\3\2\2\2\nO\3\2\2\2\fU\3"+
		"\2\2\2\16e\3\2\2\2\20r\3\2\2\2\22\u0097\3\2\2\2\24\u00f3\3\2\2\2\26\u00f6"+
		"\3\2\2\2\30\u0105\3\2\2\2\32\u010a\3\2\2\2\34\u0114\3\2\2\2\36\u0126\3"+
		"\2\2\2 \u012f\3\2\2\2\"\u0131\3\2\2\2$\u0133\3\2\2\2&\u0138\3\2\2\2(."+
		"\5\4\3\2)*\5\26\f\2*+\7\36\2\2+-\3\2\2\2,)\3\2\2\2-\60\3\2\2\2.,\3\2\2"+
		"\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\618\5\n\6\2\62\63\5\26\f\2\63\64"+
		"\7\36\2\2\64\67\3\2\2\2\65\67\5\n\6\2\66\62\3\2\2\2\66\65\3\2\2\2\67:"+
		"\3\2\2\28\66\3\2\2\289\3\2\2\29\3\3\2\2\2:8\3\2\2\2;<\7\27\2\2<@\79\2"+
		"\2=>\7 \2\2>?\7:\2\2?A\7!\2\2@=\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\36\2\2"+
		"C\5\3\2\2\2DH\7\"\2\2EG\5\24\13\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2"+
		"\2\2IK\3\2\2\2JH\3\2\2\2KL\7#\2\2L\7\3\2\2\2MN\5\6\4\2N\t\3\2\2\2OP\7"+
		"\23\2\2PQ\5\34\17\2QR\79\2\2RS\5\f\7\2ST\5\b\5\2T\13\3\2\2\2Ua\7 \2\2"+
		"VW\5\34\17\2W^\79\2\2XY\7\35\2\2YZ\5\34\17\2Z[\79\2\2[]\3\2\2\2\\X\3\2"+
		"\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_b\3\2\2\2`^\3\2\2\2aV\3\2\2\2ab\3"+
		"\2\2\2bc\3\2\2\2cd\7!\2\2d\r\3\2\2\2en\7 \2\2fk\5\22\n\2gh\7\35\2\2hj"+
		"\5\22\n\2ig\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2lo\3\2\2\2mk\3\2\2\2"+
		"nf\3\2\2\2no\3\2\2\2op\3\2\2\2pq\7!\2\2q\17\3\2\2\2rs\79\2\2st\5\16\b"+
		"\2t\21\3\2\2\2uv\b\n\1\2vw\7\'\2\2w\u0098\5\22\n\26xy\7\37\2\2y\u0098"+
		"\5\22\n\25z{\7+\2\2{\u0098\5\22\n\24|}\7(\2\2}\u0098\5\22\n\23~\177\7"+
		" \2\2\177\u0080\5\22\n\2\u0080\u0081\7!\2\2\u0081\u0098\3\2\2\2\u0082"+
		"\u0098\5\20\t\2\u0083\u0084\79\2\2\u0084\u0085\7$\2\2\u0085\u0086\7:\2"+
		"\2\u0086\u0098\7%\2\2\u0087\u0098\5\32\16\2\u0088\u0089\7$\2\2\u0089\u008e"+
		"\5\22\n\2\u008a\u008b\7\35\2\2\u008b\u008d\5\22\n\2\u008c\u008a\3\2\2"+
		"\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7%\2\2\u0092\u0098\3\2\2\2\u0093"+
		"\u0098\79\2\2\u0094\u0098\7:\2\2\u0095\u0098\7\13\2\2\u0096\u0098\7\f"+
		"\2\2\u0097u\3\2\2\2\u0097x\3\2\2\2\u0097z\3\2\2\2\u0097|\3\2\2\2\u0097"+
		"~\3\2\2\2\u0097\u0082\3\2\2\2\u0097\u0083\3\2\2\2\u0097\u0087\3\2\2\2"+
		"\u0097\u0088\3\2\2\2\u0097\u0093\3\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095"+
		"\3\2\2\2\u0097\u0096\3\2\2\2\u0098\u00b2\3\2\2\2\u0099\u009a\f\22\2\2"+
		"\u009a\u009b\7(\2\2\u009b\u00b1\5\22\n\23\u009c\u009d\f\21\2\2\u009d\u009e"+
		"\7)\2\2\u009e\u00b1\5\22\n\22\u009f\u00a0\f\20\2\2\u00a0\u00a1\7\'\2\2"+
		"\u00a1\u00b1\5\22\n\21\u00a2\u00a3\f\17\2\2\u00a3\u00a4\7&\2\2\u00a4\u00b1"+
		"\5\22\n\20\u00a5\u00a6\f\16\2\2\u00a6\u00a7\7*\2\2\u00a7\u00b1\5\22\n"+
		"\17\u00a8\u00a9\f\r\2\2\u00a9\u00aa\5\"\22\2\u00aa\u00ab\5\22\n\16\u00ab"+
		"\u00b1\3\2\2\2\u00ac\u00ad\f\f\2\2\u00ad\u00ae\5 \21\2\u00ae\u00af\5\22"+
		"\n\r\u00af\u00b1\3\2\2\2\u00b0\u0099\3\2\2\2\u00b0\u009c\3\2\2\2\u00b0"+
		"\u009f\3\2\2\2\u00b0\u00a2\3\2\2\2\u00b0\u00a5\3\2\2\2\u00b0\u00a8\3\2"+
		"\2\2\u00b0\u00ac\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\23\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b6\7\24\2"+
		"\2\u00b6\u00b7\7 \2\2\u00b7\u00b8\79\2\2\u00b8\u00b9\7!\2\2\u00b9\u00f4"+
		"\5\6\4\2\u00ba\u00bb\5\26\f\2\u00bb\u00bc\7\36\2\2\u00bc\u00f4\3\2\2\2"+
		"\u00bd\u00be\5\30\r\2\u00be\u00bf\7\36\2\2\u00bf\u00f4\3\2\2\2\u00c0\u00c1"+
		"\7\t\2\2\u00c1\u00c2\5\22\n\2\u00c2\u00ca\5\6\4\2\u00c3\u00c4\7\n\2\2"+
		"\u00c4\u00c5\7\t\2\2\u00c5\u00c6\5\22\n\2\u00c6\u00c7\5\6\4\2\u00c7\u00c9"+
		"\3\2\2\2\u00c8\u00c3\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00cf\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7\n"+
		"\2\2\u00ce\u00d0\5\6\4\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00f4\3\2\2\2\u00d1\u00d2\7\b\2\2\u00d2\u00d3\5\22\n\2\u00d3\u00d4\5"+
		"\6\4\2\u00d4\u00f4\3\2\2\2\u00d5\u00d6\5\20\t\2\u00d6\u00d7\7\36\2\2\u00d7"+
		"\u00f4\3\2\2\2\u00d8\u00f4\5\6\4\2\u00d9\u00da\7\7\2\2\u00da\u00db\7 "+
		"\2\2\u00db\u00dc\5\26\f\2\u00dc\u00dd\7\36\2\2\u00dd\u00de\5\22\n\2\u00de"+
		"\u00df\7\36\2\2\u00df\u00e0\5\30\r\2\u00e0\u00e1\7!\2\2\u00e1\u00e2\5"+
		"\6\4\2\u00e2\u00f4\3\2\2\2\u00e3\u00e4\7\31\2\2\u00e4\u00e5\7 \2\2\u00e5"+
		"\u00e6\5\22\n\2\u00e6\u00e7\7!\2\2\u00e7\u00e8\7\36\2\2\u00e8\u00f4\3"+
		"\2\2\2\u00e9\u00ea\7\32\2\2\u00ea\u00eb\7 \2\2\u00eb\u00ec\79\2\2\u00ec"+
		"\u00ed\7!\2\2\u00ed\u00f4\7\36\2\2\u00ee\u00f0\7\21\2\2\u00ef\u00f1\5"+
		"\22\n\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\u00f4\7\36\2\2\u00f3\u00b5\3\2\2\2\u00f3\u00ba\3\2\2\2\u00f3\u00bd\3"+
		"\2\2\2\u00f3\u00c0\3\2\2\2\u00f3\u00d1\3\2\2\2\u00f3\u00d5\3\2\2\2\u00f3"+
		"\u00d8\3\2\2\2\u00f3\u00d9\3\2\2\2\u00f3\u00e3\3\2\2\2\u00f3\u00e9\3\2"+
		"\2\2\u00f3\u00ee\3\2\2\2\u00f4\25\3\2\2\2\u00f5\u00f7\7\26\2\2\u00f6\u00f5"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\5\34\17\2"+
		"\u00f9\u00fa\79\2\2\u00fa\u0101\7\63\2\2\u00fb\u0102\5\22\n\2\u00fc\u00fd"+
		"\5\34\17\2\u00fd\u00fe\7$\2\2\u00fe\u00ff\7:\2\2\u00ff\u0100\7%\2\2\u0100"+
		"\u0102\3\2\2\2\u0101\u00fb\3\2\2\2\u0101\u00fc\3\2\2\2\u0102\27\3\2\2"+
		"\2\u0103\u0106\5&\24\2\u0104\u0106\5\32\16\2\u0105\u0103\3\2\2\2\u0105"+
		"\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\7\63\2\2\u0108\u0109\5"+
		"\22\n\2\u0109\31\3\2\2\2\u010a\u010b\79\2\2\u010b\u010c\7$\2\2\u010c\u010d"+
		"\5\22\n\2\u010d\u010e\7%\2\2\u010e\33\3\2\2\2\u010f\u0110\b\17\1\2\u0110"+
		"\u0115\7\3\2\2\u0111\u0115\7\4\2\2\u0112\u0115\7\5\2\2\u0113\u0115\78"+
		"\2\2\u0114\u010f\3\2\2\2\u0114\u0111\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0113\3\2\2\2\u0115\u011d\3\2\2\2\u0116\u0117\f\4\2\2\u0117\u011c\7("+
		"\2\2\u0118\u0119\f\3\2\2\u0119\u011a\7$\2\2\u011a\u011c\7%\2\2\u011b\u0116"+
		"\3\2\2\2\u011b\u0118\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\35\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0127\7:\2\2"+
		"\u0121\u0127\5&\24\2\u0122\u0127\7\13\2\2\u0123\u0127\7\f\2\2\u0124\u0127"+
		"\7\30\2\2\u0125\u0127\5\32\16\2\u0126\u0120\3\2\2\2\u0126\u0121\3\2\2"+
		"\2\u0126\u0122\3\2\2\2\u0126\u0123\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0125"+
		"\3\2\2\2\u0127\37\3\2\2\2\u0128\u0130\7\62\2\2\u0129\u0130\7\61\2\2\u012a"+
		"\u012b\7\63\2\2\u012b\u0130\7\63\2\2\u012c\u0130\7\66\2\2\u012d\u0130"+
		"\7\65\2\2\u012e\u0130\7\67\2\2\u012f\u0128\3\2\2\2\u012f\u0129\3\2\2\2"+
		"\u012f\u012a\3\2\2\2\u012f\u012c\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u012e"+
		"\3\2\2\2\u0130!\3\2\2\2\u0131\u0132\t\2\2\2\u0132#\3\2\2\2\u0133\u0134"+
		"\t\3\2\2\u0134%\3\2\2\2\u0135\u0136\7(\2\2\u0136\u0139\5&\24\2\u0137\u0139"+
		"\79\2\2\u0138\u0135\3\2\2\2\u0138\u0137\3\2\2\2\u0139\'\3\2\2\2\34.\66"+
		"8@H^akn\u008e\u0097\u00b0\u00b2\u00ca\u00cf\u00f0\u00f3\u00f6\u0101\u0105"+
		"\u0114\u011b\u011d\u0126\u012f\u0138";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}