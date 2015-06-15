lexer grammar LexerRules;

INT : I N T;
BOOL : B O O L;
VOID : V O I D;
STRING : S T R I N G;
FOR : F O R;
WHILE : W H I L E;
IF : I F;
ELSE : E L S E; 
TRUE : T R U E;
FALSE : F A L S E;
AND : A N D;
OR : O R;
XOR : X O R;
NOT : N O T;
RETURN : R E T U R N;
BREAK : B R E A K;
DEF : D E F;

UNDERSCORE : '_';
DOT : '.';
COMMA : ',';
SEMI : ';';
EXCLAMATION : '!';
LBRACE : '(';
RBRACE : ')';
LCURL : '{';
RCURL : '}';
LSQUARE : '[';
RSQUARE : ']';
PLUS : '+';
MINUS : '-';
TIMES : '*';
DIV : '/';
SQUOT : '\'';
DQUOT : '"';
BACKSLASH : '\\';
TIDLE : '~';
HAT : '^';
GT : '>';
LT : '<';
EQ : '=';
ARROW : MINUS GT;
GE : EQ GT;
LE : EQ LT;
NE : EXCLAMATION EQ;


TYPE : UCASE (LETTER|DIGIT|UNDERSCORE)*;
ID : LCASE (LETTER|DIGIT|UNDERSCORE)*;
NUMBER : NONZERO DIGIT* | ZERO;


fragment LCASE: [a-z];
fragment UCASE: [A-Z];
fragment LETTER: LCASE | UCASE;
fragment ZERO : '0';
fragment NONZERO : [1-9];
fragment DIGIT : ZERO | NONZERO;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

WS :  [ \r\n\t] -> skip;