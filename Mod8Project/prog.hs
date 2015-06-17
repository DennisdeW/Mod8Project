import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 6 RegA
		, Const 7 RegB
		, Compute Mul RegA RegB RegC
		, EndProg
       ]

main = run 1 prog
