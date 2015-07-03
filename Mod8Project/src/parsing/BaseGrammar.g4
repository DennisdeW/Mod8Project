grammar BaseGrammar;

import LexerRules;

program
:
	progdef
	(
		decl SEMI
	)* func
	(
		(
			decl SEMI
		)
		| func
	)*
;

progdef
:
	PROGRAM ID
	(
		LBRACE NUMBER RBRACE
	)? SEMI
;

block
:
	LCURL stat* RCURL
;

topLevelBlock
:
	block
;

func
:
	DEF type ID typedparams topLevelBlock
;

typedparams
:
	LBRACE
	(
		type ID
		(
			COMMA type ID
		)*
	)? RBRACE
;

params
:
	LBRACE
	(
		expr
		(
			COMMA expr
		)*
	)? RBRACE
;

call
:
	ID params
;

expr
:
	MINUS expr # negNumExpr
	| EXCLAMATION expr # negBoolExpr
	| AMP expr # refExpr
	| TIMES expr # derefExpr
	| expr TIMES expr # multExpr
	| expr DIV expr # divExpr
	| expr MINUS expr # minExpr
	| expr PLUS expr # plusExpr
	| expr MOD expr # modExpr
	| expr boolOp expr # boolOpExpr
	| expr comp expr # compExpr
	| LBRACE expr RBRACE # parExpr
	| call # callExpr
	| ID LSQUARE NUMBER RSQUARE # constArrayExpr
	| arrayVal # exprArrayExpr
	| LSQUARE expr
	(
		COMMA expr
	)* RSQUARE # arrayLiteralExpr
	| ID # idExpr
	| NUMBER # numExpr
	| TRUE # trueExpr
	| FALSE # falseExpr
;

stat
:
	LOCK LBRACE ID RBRACE block # lockStat
	| decl SEMI # declStat
	| assign SEMI # assignStat
	| IF expr block
	(
		ELSE IF expr block
	)*
	(
		ELSE block
	)? # ifStat
	| WHILE expr block # whileStat
	| call SEMI # callStat
	| block # blockStat
	| FOR LBRACE decl SEMI expr SEMI assign RBRACE block # forStat
	| OUT LBRACE expr RBRACE SEMI # outStat
	| IN LBRACE ID RBRACE SEMI # inStat
	| RETURN expr? SEMI # returnStat
;

decl
:
	SHARED? type ID EQ (expr | type LSQUARE NUMBER RSQUARE)
;

assign
:
	(
		derefID
		| arrayVal
	) EQ expr
;

arrayVal
:
	ID LSQUARE expr RSQUARE
;

type
:
	INT
	| BOOL
	| VOID
	| TYPE
	| type TIMES
	| type LSQUARE RSQUARE
;

val
:
	NUMBER #numVal
	| derefID   #idVal
	| TRUE #trueVal
	| FALSE #falseVal
	| SPID #spidVal
	| arrayVal #arrVal
;

comp
:
	LT
	| GT
	| EQ EQ
	| LE
	| GE
	| NE
;

boolOp
:
	AND
	| OR
	| XOR
;

prefix
:
	MINUS
	| NOT
;

derefID
:
	TIMES derefID
	| ID
;

