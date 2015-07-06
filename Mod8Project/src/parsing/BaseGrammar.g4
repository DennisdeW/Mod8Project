grammar BaseGrammar;

program
:
	progdef
	(
		(
			decl
			| enumDecl
		) SEMI
	)* func
	(
		(
			(
				decl
				| enumDecl
			) SEMI
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
	| expr boolOp expr # boolOpExpr
	| expr comp expr # compExpr
	| expr TIMES expr # multExpr
	| expr DIV expr # divExpr
	| expr MINUS expr # minExpr
	| expr PLUS expr # plusExpr
	| expr MOD expr # modExpr
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
	| SPID # spidExpr
	| TYPE DOT TYPE # enumExpr
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
	SHARED? type ID EQ
	(
		expr
		| type LSQUARE NUMBER RSQUARE
	)
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

enumDecl
:
	ENUM TYPE COLON TYPE
	(
		COMMA TYPE
	)*
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
	| NAND
	| NOR
	| NXOR
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

/////////////////////////////////////////////////////////////////////////////////

INT
:
	I N T
;

BOOL
:
	B O O L
;

VOID
:
	V O I D
;

STRING
:
	S T R I N G
;

ENUM
:
	E N U M
;

FOR
:
	F O R
;

WHILE
:
	W H I L E
;

IF
:
	I F
;

ELSE
:
	E L S E
;

TRUE
:
	T R U E
;

FALSE
:
	F A L S E
;

AND
:
	A N D
;

OR
:
	O R
;

XOR
:
	X O R
;

NAND
:
	N A N D
;

NOR
:
	N O R
;

NXOR
:
	N X O R
;

NOT
:
	N O T
;

RETURN
:
	R E T U R N
;

BREAK
:
	B R E A K
;

DEF
:
	D E F
;

LOCK
:
	L O C K
;

UNLOCK
:
	U N L O C K
;

SHARED
:
	S H A R E D
;

PROGRAM
:
	P R O G R A M
;

SPID
:
	'$' S P I D
;

OUT
:
	O U T
;

IN
:
	I N
;

UNDERSCORE
:
	'_'
;

DOT
:
	'.'
;

COMMA
:
	','
;

SEMI
:
	';'
;

COLON
:
	':'
;

EXCLAMATION
:
	'!'
;

LBRACE
:
	'('
;

RBRACE
:
	')'
;

LCURL
:
	'{'
;

RCURL
:
	'}'
;

LSQUARE
:
	'['
;

RSQUARE
:
	']'
;

PLUS
:
	'+'
;

MINUS
:
	'-'
;

TIMES
:
	'*'
;

DIV
:
	'/'
;

MOD
:
	'%'
;

AMP
:
	'&'
;

SQUOT
:
	'\''
;

DQUOT
:
	'"'
;

BACKSLASH
:
	'\\'
;

TIDLE
:
	'~'
;

HAT
:
	'^'
;

GT
:
	'>'
;

LT
:
	'<'
;

EQ
:
	'='
;

ARROW
:
	MINUS GT
;

GE
:
	GT EQ
;

LE
:
	LT EQ
;

NE
:
	EXCLAMATION EQ
;

TYPE
:
	UCASE
	(
		LETTER
		| DIGIT
		| UNDERSCORE
	)*
;

ID
:
	LCASE
	(
		LETTER
		| DIGIT
		| UNDERSCORE
	)*
;

NUMBER
:
	NONZERO DIGIT*
	| ZERO
;

fragment
LCASE
:
	[a-z]
;

fragment
UCASE
:
	[A-Z]
;

fragment
LETTER
:
	LCASE
	| UCASE
;

fragment
ZERO
:
	'0'
;

fragment
NONZERO
:
	[1-9]
;

fragment
DIGIT
:
	ZERO
	| NONZERO
;

fragment
A
:
	[aA]
;

fragment
B
:
	[bB]
;

fragment
C
:
	[cC]
;

fragment
D
:
	[dD]
;

fragment
E
:
	[eE]
;

fragment
F
:
	[fF]
;

fragment
G
:
	[gG]
;

fragment
H
:
	[hH]
;

fragment
I
:
	[iI]
;

fragment
J
:
	[jJ]
;

fragment
K
:
	[kK]
;

fragment
L
:
	[lL]
;

fragment
M
:
	[mM]
;

fragment
N
:
	[nN]
;

fragment
O
:
	[oO]
;

fragment
P
:
	[pP]
;

fragment
Q
:
	[qQ]
;

fragment
R
:
	[rR]
;

fragment
S
:
	[sS]
;

fragment
T
:
	[tT]
;

fragment
U
:
	[uU]
;

fragment
V
:
	[vV]
;

fragment
W
:
	[wW]
;

fragment
X
:
	[xX]
;

fragment
Y
:
	[yY]
;

fragment
Z
:
	[zZ]
;

COMMENT
:
	DIV TIMES .*? TIMES DIV -> skip
;

WS
:
	[ \r\n\t] -> skip
;