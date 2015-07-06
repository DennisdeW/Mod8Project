{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1 RegA
		, Const 1001 RegB
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
debug SysState{..} | halted $ sprs!!0 = show val
					where
						(RegFile val) = regbank $ sprs!!0
debug _ = ""
main = runDebug debug 3 prog
