{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 4 RegA
		, Const 8 RegB
		, Compute Mul RegA RegB RegA
		, EndProg 
		, EndProg
       ]

debug :: SystemState -> String
debug SysState{..} | halted $ sprs!!0 = show $ (regbank (sprs!!0))
debug _ = ""
main = runDebug debug 1 prog
