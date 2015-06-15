grammar BaseGrammar;

import LexerRules;

block : LCURL stat* RCURL;

func : DEF type ID typedparams block;

typedparams : LBRACE (type ID (COMMA type ID)*)? RBRACE;
params : LBRACE (ID (COMMA ID)*)? RBRACE;
call : ID params;

expr
	: MINUS expr			# negNumExpr
	| EXCLAMATION expr 		# negBoolExpr
	| expr TIMES expr 		# multExpr
	| expr DIV expr 		# divExpr
	| expr MINUS expr		# minExpr
	| expr PLUS expr 		# plusExpr
	| expr boolOp expr		# boolOpExpr
	| expr comp expr 		# compExpr
	| LBRACE expr RBRACE	# parExpr
	| call					# funcExpr
	| ID					# idExpr
	| NUMBER				# numExpr
	| TRUE					# trueExpr
	| FALSE					# falseExpr
	;

stat
	: decl SEMI													# declStat
	| assign SEMI											# assignStat
	| IF expr block (ELSE IF expr block)* (ELSE block)?			# ifStat
	| WHILE expr block											# whileStat
	| call														# funcStat
	| block														# blockStat
	| FOR LBRACE decl SEMI expr SEMI assign	RBRACE block		# forStat						
	;

decl : type ID EQ expr;
assign : ID EQ expr;

type : INT | BOOL | TYPE;
val : NUMBER | ID | TRUE | FALSE;
comp : LT | GT | EQ | LE | GE | NE;
boolOp : AND | OR | XOR;
prefix : MINUS | NOT;