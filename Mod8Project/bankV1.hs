{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1122 RegA               {-Initial Return Addr-}
		, Push RegA
		, Const (0-1) RegA              {-Initial Result Addr-}
		, Push RegA
		, Branch SPID (Rel 6)           {-Declaration of account(=1000)-}
		, Const 0 RegD
		, Push RegD
		, Const 1000 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Branch SPID (Rel 6)           {-Declaration of doneCount(=0)-}
		, Const 1 RegD
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Branch SPID (Rel 4)
		, TestAndSet (Addr 2)
		, Receive Zero
		, Jump (Rel 5)
		, Read (Addr 2)
		, Receive RegA
		, Branch RegA (Rel 2)
		, Jump (Rel (0-3))
		, Jump (Abs 25)                 {-Jump To Main Function-}
		, Push SPID                     {-Function main-}
		, Const 5 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-$SPID Lt 5-}
		, Branch RegA (Rel 2)
		, Jump (Rel 34)
		, Const 49 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 49) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 22)          {-Break from for loop-}
		, Load (Addr 49) RegA           {-For loop body-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-i Mul 10-}
		, Store RegA (Addr 12)
		, Const 53 RegA                 {-Return addr-}
		, Push RegA
		, Const 50 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 145)                {-Jump to function addToAccount-}
		, Const 49 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 49) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-26))             {-Back to for loop-}
		, Jump (Rel 33)
		, Const 50 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 50) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 22)          {-Break from for loop-}
		, Load (Addr 50) RegA           {-For loop body-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-i Mul 10-}
		, Store RegA (Addr 0)
		, Const 86 RegA                 {-Return addr-}
		, Push RegA
		, Const 51 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 165)                {-Jump to function takeFromAccount-}
		, Const 50 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 50) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-26))             {-Back to for loop-}
		, TestAndSet (Addr 4)           {-Locking done_lock-}
		, Receive RegE
		, Branch RegE (Rel 2)
		, Jump (Rel (0-3))
		, Const 1 RegD                  {-doneCount = doneCount+1-}
		, Push RegD
		, Read (Addr 1)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-doneCount Add 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Write Zero (Addr 4)           {-Unlocking done_lock-}
		, Push SPID
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-$SPID Equal 0-}
		, Branch RegA (Rel 2)
		, Jump (Rel 22)
		, Read (Addr 1)
		, Receive RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-doneCount Lt 10-}
		, Branch RegA (Rel 2)
		, Jump (Rel (0-7))
		, Read (Addr 0)
		, Receive RegA
		, Push RegA
		, Const 1000 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-account Equal 1000-}
		, Branch RegA (Rel 2)
		, Jump (Rel 4)
		, Const 0 RegA
		, Out RegA
		, Jump (Rel 3)
		, Const 1 RegA
		, Out RegA
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, TestAndSet (Addr 3)           {-Locking acc_lock; Function addToAccount-}
		, Receive RegE
		, Branch RegE (Rel 2)
		, Jump (Rel (0-3))
		, Const 0 RegD                  {-account = account+amt-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegA
		, Push RegA
		, Load (Addr 12) RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-account Add amt-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Write Zero (Addr 3)           {-Unlocking acc_lock-}
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, TestAndSet (Addr 3)           {-Locking acc_lock; Function takeFromAccount-}
		, Receive RegE
		, Branch RegE (Rel 2)
		, Jump (Rel (0-3))
		, Const 0 RegD                  {-account = account-amt-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegA
		, Push RegA
		, Load (Addr 0) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-account Sub amt-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Write Zero (Addr 3)           {-Unlocking acc_lock-}
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 31) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 31) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 31) RegA
		, Store RegA (Addr 58)
		, Const 58 RegA                 {-Base addr for (anon) array 674882504-}
		, Store RegA (Addr 32)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 59 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 33)
		, Store Zero (Addr 59)
		, Store Zero (Addr 60)
		, Store Zero (Addr 61)
		, Store Zero (Addr 62)
		, Store Zero (Addr 63)
		, Store Zero (Addr 64)
		, Store Zero (Addr 65)
		, Store Zero (Addr 66)
		, Store Zero (Addr 67)
		, Store Zero (Addr 68)
		, Const 34 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 31) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 34) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 31 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 31) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 35 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 37 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 38) RegA
		, Push RegA
		, Load (Addr 31) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 38 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 35 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 31) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 31) RegA
		, Push RegA
		, Load (Addr 35) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 37) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 36) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 37 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 31 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 35) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 34) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 33 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 37) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 33) RegA
		, Store RegA (Addr 19)
		, Const 344 RegA                {-Return addr-}
		, Push RegA
		, Const 39 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 351)                {-Jump to function shiftArray-}
		, Load (Addr 39) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 69 RegA                 {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 20)
		, Store Zero (Addr 69)
		, Store Zero (Addr 70)
		, Store Zero (Addr 71)
		, Store Zero (Addr 72)
		, Store Zero (Addr 73)
		, Store Zero (Addr 74)
		, Store Zero (Addr 75)
		, Store Zero (Addr 76)
		, Store Zero (Addr 77)
		, Store Zero (Addr 78)
		, Const 21 RegD                 {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 22 RegD                 {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 21) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 19) RegE
		, Load (Addr 21) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 21 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 21) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 22 RegD                 {-j = j+1-}
		, Push RegD
		, Load (Addr 22) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 21) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 20 RegD                 {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 21) RegA
		, Push RegA
		, Load (Addr 22) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 19) RegE
		, Load (Addr 21) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 21 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 21) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 20) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 23) RegA           {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 23) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 23) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 79 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 24)
		, Store Zero (Addr 79)
		, Store Zero (Addr 80)
		, Store Zero (Addr 81)
		, Store Zero (Addr 82)
		, Store Zero (Addr 83)
		, Store Zero (Addr 84)
		, Store Zero (Addr 85)
		, Store Zero (Addr 86)
		, Store Zero (Addr 87)
		, Store Zero (Addr 88)
		, Const 25 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 23) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 25) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 23 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 23) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 29) RegA
		, Push RegA
		, Load (Addr 23) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 29 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 23) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 23) RegA
		, Push RegA
		, Load (Addr 26) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 24 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 28) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 27) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 28) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 23 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 26) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 25) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 24 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 28) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 28) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 28) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 30) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 24) RegE           {-For loop body-}
		, Load (Addr 30) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 30 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 30) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-20))             {-Back to for loop-}
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 51) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 89)
		, Const 34 RegA
		, Store RegA (Addr 90)
		, Const 37 RegA
		, Store RegA (Addr 91)
		, Const 21 RegA
		, Store RegA (Addr 92)
		, Const 89 RegA                 {-Base addr for (anon) array 989896315-}
		, Store RegA (Addr 52)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 93)
		, Const 17 RegA
		, Store RegA (Addr 94)
		, Const 28 RegA
		, Store RegA (Addr 95)
		, Const 35 RegA
		, Store RegA (Addr 96)
		, Const 21 RegA
		, Store RegA (Addr 97)
		, Const 93 RegA                 {-Base addr for (anon) array 1744457797-}
		, Store RegA (Addr 53)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 54) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 98)
		, Const 34 RegA
		, Store RegA (Addr 99)
		, Const 37 RegA
		, Store RegA (Addr 100)
		, Const 21 RegA
		, Store RegA (Addr 101)
		, Const 98 RegA                 {-Base addr for (anon) array 584580417-}
		, Store RegA (Addr 55)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 102)
		, Const 17 RegA
		, Store RegA (Addr 103)
		, Const 28 RegA
		, Store RegA (Addr 104)
		, Const 35 RegA
		, Store RegA (Addr 105)
		, Const 21 RegA
		, Store RegA (Addr 106)
		, Const 102 RegA                {-Base addr for (anon) array 2000779038-}
		, Store RegA (Addr 56)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 107)
		, Const 107 RegA                {-Base addr for (anon) array 663083782-}
		, Store RegA (Addr 57)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 14) RegA           {-Function boolToInt-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Const 1 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 13) RegA           {-Function intToBool-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-i Equal 0-}
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Push Zero
		, Pop RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 1 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 43 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 43) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 42) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 40) RegE           {-For loop body-}
		, Load (Addr 43) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 41) RegE
		, Load (Addr 43) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-x[i] NEq y[i]-}
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Push Zero
		, Pop RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 43 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 43) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-36))             {-Back to for loop-}
		, Const 1 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 18 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 18) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 17) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 15) RegE           {-For loop body-}
		, Load (Addr 18) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 16) RegE
		, Load (Addr 18) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-x[i] NEq y[i]-}
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Push Zero
		, Pop RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 18 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 18) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-36))             {-Back to for loop-}
		, Const 1 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 43 RegA                 {-Function printArray-}
		, Out RegA
		, Const 3 RegD                  {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 3) RegA            {-For loop condition-}
		, Push RegA
		, Load (Addr 2) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 1) RegE            {-For loop body-}
		, Load (Addr 3) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 23)
		, Const 881 RegA                {-Return addr-}
		, Push RegA
		, Const 4 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 447)                {-Jump to function printInt-}
		, Const 4 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Out RegA
		, Const 16 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Out RegA
		, Const 3 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 3) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 1) RegE
		, Load (Addr 2) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 45 RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 43 RegA                 {-Function printArray-}
		, Out RegA
		, Const 11 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 11) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 10) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 9) RegE            {-For loop body-}
		, Load (Addr 11) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 51)
		, Const 944 RegA                {-Return addr-}
		, Push RegA
		, Const 12 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 636)                {-Jump to function printBool-}
		, Const 4 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Out RegA
		, Const 16 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Out RegA
		, Const 11 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 11) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 9) RegE
		, Load (Addr 10) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 45 RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 6 RegD                  {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 7 RegD                  {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 5) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 6) RegA
		, Push RegA
		, Load (Addr 7) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 8 RegD                  {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 4) RegE
		, Load (Addr 7) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 7) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 4) RegE
		, Load (Addr 6) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 6) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 8) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 6 RegD                  {-i = i+1-}
		, Push RegD
		, Load (Addr 6) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 7 RegD                  {-j = j-1-}
		, Push RegD
		, Load (Addr 7) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-j Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-51))
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 46 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 45) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 46) RegA
		, Push RegA
		, Load (Addr 47) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 48 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 44) RegE
		, Load (Addr 47) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 44 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 47) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 44) RegE
		, Load (Addr 46) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 44 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 46) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 48) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 46 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 46) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 47) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-j Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-51))
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, EndProg 
		, EndProg
       ]

main = run 10 prog
