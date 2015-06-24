import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		 TestAndSet (Addr 1)
		,Receive RegA
		,Branch RegA (Rel 2)
		,Jump (Rel (0-3))
		,Read (Addr 0)
		,Receive RegB
		,Const 1 RegC
		,Compute Add RegB RegC RegB
		,Compute Add RegB RegC RegB
		,Compute Add RegB RegC RegB
		,Compute Add RegB RegC RegB
		,Write RegB (Addr 0)
		,Write Zero (Addr 1)
		,EndProg
       ]

main = run 1 prog
