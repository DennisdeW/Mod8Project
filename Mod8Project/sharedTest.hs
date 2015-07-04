{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 201 RegA                {-Initial Return Addr-}
		, Push RegA
		, Const (0-1) RegA              {-Initial Result Addr-}
		, Push RegA
		, Branch SPID (Rel 6)           {-Declaration of val(=0)-}
		, Const 0 RegD
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Branch SPID (Rel 6)           {-Declaration of addc(=0)-}
		, Const 1 RegD
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Branch SPID (Rel 6)           {-Declaration of subc(=0)-}
		, Const 2 RegD
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Branch SPID (Rel 4)
		, TestAndSet (Addr 3)
		, Receive Zero
		, Jump (Rel 5)
		, Read (Addr 3)
		, Receive RegA
		, Branch RegA (Rel 2)
		, Jump (Rel (0-3))
		, Jump (Abs 31)                 {-Jump To Main Function-}
		, Const 0 RegD                  {-Declaration of tmp(=1); Function main-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, TestAndSet (Addr 4)           {-Locking val_lock-}
		, Receive RegE
		, Branch RegE (Rel 2)
		, Jump (Rel (0-3))
		, Const 1 RegD                  {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 1) RegA            {-For loop condition-}
		, Push RegA
		, Const 3 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 3-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Const 56 RegA                 {-Return addr; For loop body-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 111)                {-Jump to function add-}
		, Const 1 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 1) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-20))             {-Back to for loop-}
		, Write Zero (Addr 4)           {-Unlocking val_lock-}
		, Const 0 RegD                  {-tmp = 3-}
		, Push RegD
		, Const 3 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, TestAndSet (Addr 4)           {-Locking val_lock-}
		, Receive RegE
		, Branch RegE (Rel 2)
		, Jump (Rel (0-3))
		, Const 2 RegD                  {-Declaration of j(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 2) RegA            {-For loop condition-}
		, Push RegA
		, Const 3 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-j Lt 3-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Const 92 RegA                 {-Return addr; For loop body-}
		, Push RegA
		, Const 3 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function sub-}
		, Const 2 RegD                  {-j = j+1; For loop assignment-}
		, Push RegD
		, Load (Addr 2) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-20))             {-Back to for loop-}
		, Write Zero (Addr 4)           {-Unlocking val_lock-}
		, Read (Addr 0)
		, Receive RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegD                  {-val = val+1; Function add-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-val Add 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Const 1 RegD                  {-addc = addc+1-}
		, Push RegD
		, Read (Addr 1)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-addc Add 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegD                  {-val = val-1; Function sub-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-val Sub 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Const 2 RegD                  {-subc = subc+1-}
		, Push RegD
		, Read (Addr 2)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-subc Add 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
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
main = runDebug debug 50 prog
