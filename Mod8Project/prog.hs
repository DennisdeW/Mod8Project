import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Write Zero (Addr 0)
		, TestAndSet (Addr 0)
		, Receive RegA
		, Branch RegA (Rel 2)
		, Jump (Rel (0-3))
		, EndProg
       ]

main = run 1 prog
