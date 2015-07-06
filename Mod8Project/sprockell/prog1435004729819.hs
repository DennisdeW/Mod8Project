{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1 RegA
		, Const 7 RegB
		, Const 1 RegC
		, Compute Equal RegB RegC RegC
		, Branch RegC (Rel 5)
		, Const 1 RegC
		, Compute Sub RegB RegC RegB
		, Compute Mul RegA RegB RegA
		, Jump (Abs 2)
		, EndProg 
		, EndProg
       ]

debug :: SystemState -> String
debug SysState{..} | halted $ sprs!!0 = (show val) ++ "\n" ++ (show mem)
				 	| cycleCount `mod` 1 == 0 = "Cycle " ++ (show cycleCount) ++ ": " ++ (show val) ++ "\n" ++ (show mem) ++ "\n"
					where
						(RegFile val) = regbank $ sprs!!0
						(Memory mem) = localMem $ sprs!!0
debug _ = ""
main = runDebug debug 1 prog
