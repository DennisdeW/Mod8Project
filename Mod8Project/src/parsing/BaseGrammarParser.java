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
		TYPE=1, ID=2, INT=3, BOOL=4, VOID=5, STRING=6, FOR=7, WHILE=8, IF=9, ELSE=10, 
		TRUE=11, FALSE=12, AND=13, OR=14, XOR=15, NOT=16, RETURN=17, BREAK=18, 
		UNDERSCORE=19, DOT=20, LBRACE=21, RBRACE=22, LCURL=23, RCURL=24, LSQUARE=25, 
		RSQUARE=26, PLUS=27, MINUS=28, TIMES=29, DIV=30, SQUOT=31, DQUOT=32, BACKSLASH=33, 
		TIDLE=34, HAT=35, GT=36, LT=37, EQ=38, ARROW=39, GE=40, LE=41, WS=42;
	public static final String[] tokenNames = {
		"<INVALID>", "TYPE", "ID", "INT", "BOOL", "VOID", "STRING", "FOR", "WHILE", 
		"IF", "ELSE", "TRUE", "FALSE", "AND", "OR", "XOR", "NOT", "RETURN", "BREAK", 
		"'_'", "'.'", "'('", "')'", "'{'", "'}'", "'['", "']'", "'+'", "'-'", 
		"'*'", "'/'", "'''", "'\"'", "'\\'", "'~'", "'^'", "'>'", "'<'", "'='", 
		"ARROW", "GE", "LE", "WS"
	};
	public static final int
		RULE_field = 0;
	public static final String[] ruleNames = {
		"field"
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
	public static class FieldContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(BaseGrammarParser.ID); }
		public TerminalNode DOT() { return getToken(BaseGrammarParser.DOT, 0); }
		public TerminalNode ID(int i) {
			return getToken(BaseGrammarParser.ID, i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BaseGrammarListener ) ((BaseGrammarListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BaseGrammarVisitor ) return ((BaseGrammarVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2); match(ID);
			setState(3); match(DOT);
			setState(4); match(ID);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\t\4\2\t\2\3\2\3"+
		"\2\3\2\3\2\3\2\2\2\3\2\2\2\7\2\4\3\2\2\2\4\5\7\4\2\2\5\6\7\26\2\2\6\7"+
		"\7\4\2\2\7\3\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}