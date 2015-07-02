{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 152 RegA                {-Initial Return Addr-}
		, Push RegA
		, Const (0-1) RegA              {-Initial Result Addr-}
		, Push RegA
		, Branch SPID (Rel 4)
		, TestAndSet (Addr 1)
		, Receive Zero
		, Jump (Rel 5)
		, Read (Addr 1)
		, Receive RegA
		, Branch RegA (Rel 2)
		, Jump (Rel (0-3))
		, Jump (Abs 13)                 {-Jump To Main Function-}
		, Const 0 RegA                  {-Function main-}
		, Store RegA (Addr 0)
		, Const 20 RegA                 {-Return addr-}
		, Push RegA
		, Const 0 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 27)                 {-Jump to function printInt-}
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 7 RegA                  {-Base addr for array res; Function printInt-}
		, Store RegA (Addr 1)
		, Store Zero (Addr 7)
		, Store Zero (Addr 8)
		, Store Zero (Addr 9)
		, Store Zero (Addr 10)
		, Store Zero (Addr 11)
		, Store Zero (Addr 12)
		, Store Zero (Addr 13)
		, Store Zero (Addr 14)
		, Store Zero (Addr 15)
		, Store Zero (Addr 16)
		, Const 2 RegD                  {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 5 RegD                  {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 5) RegA
		, Push RegA
		, Load (Addr 0) RegA
		, Pop RegB
		, Compute Or RegB RegA RegA     {-first Or i-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-firstORi NEq 0-}
		, Branch RegA (Rel 52)
		, Const 5 RegD                  {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 2 RegD                  {-q = i/10-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Load (Addr 2) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 1 RegD                  {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 4) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 3) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-pos = pos-1-}
		, Push RegD
		, Load (Addr 4) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 0 RegD                  {-i = q-}
		, Push RegD
		, Load (Addr 2) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Const 6 RegD                  {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 4) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 6) RegA            {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 17)          {-Break from for loop-}
		, Const 1 RegE                  {-For loop body-}
		, Load (Deref RegE) RegE
		, Load (Addr 6) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 6 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 6) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-21))             {-Back to for loop-}
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, Nop 
		, EndProg 
		, EndProg
       ]

debug :: SystemState -> String
debug SysState{..} | all halted sprs = (show val) ++ "\n" ++ (show mem) ++ "\n" ++ (show smem)
				 	| cycleCount `mod` 1 == 0 = "Cycle " ++ (show cycleCount) ++ ": " ++ (show val) ++ "\n" ++ (show mem) ++ "\n" ++ (show smem) ++ "\n"
					where
						(RegFile val) = regbank $ sprs!!0
						(Memory mem) = localMem $ sprs!!0
						(Memory smem) = sharedMem
debug _ = ""
main = runDebug debug 1 prog
