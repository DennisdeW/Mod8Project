grammar BaseGrammar;

import LexerRules;

program : (decl SEMI)* func ((decl SEMI)|func)*;

block : LCURL stat* RCURL;

func : DEF type ID typedparams block;

typedparams : LBRACE (type ID (COMMA type ID)*)? RBRACE;
params : LBRACE (val (COMMA val)*)? RBRACE;
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
	| call					# callExpr
	| ID					# idExpr
	| NUMBER				# numExpr
	| TRUE					# trueExpr
	| FALSE					# falseExpr
	;

stat
	: LOCK stat UNLOCK											# lockStat
	| decl SEMI													# declStat
	| assign SEMI												# assignStat
	| IF expr block (ELSE IF expr block)* (ELSE block)?			# ifStat
	| WHILE expr block											# whileStat
	| call SEMI													# callStat
	| block														# blockStat
	| FOR LBRACE decl SEMI expr SEMI assign	RBRACE block		# forStat
	| RETURN expr? SEMI											# returnStat						
	;

decl : type ID EQ expr;
assign : ID EQ expr;

type : INT | BOOL | VOID | TYPE;
val : NUMBER | ID | TRUE | FALSE;
comp : LT | GT | EQ EQ | LE | GE | NE;
boolOp : AND | OR | XOR;
prefix : MINUS | NOT;
