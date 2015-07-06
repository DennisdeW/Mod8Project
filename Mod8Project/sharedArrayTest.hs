{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1090 RegA               {-Initial Return Addr-}
		, Push RegA
		, Const (0-1) RegA              {-Initial Result Addr-}
		, Push RegA
		, Const 3 RegA                  {-Base addr for array arr-}
		, Write RegA (Addr 0)
		, Const 1 RegA
		, Write RegA (Addr 3)
		, Const 2 RegA
		, Write RegA (Addr 4)
		, Const 3 RegA
		, Write RegA (Addr 5)
		, Const 4 RegA
		, Write RegA (Addr 6)
		, Const 5 RegA
		, Write RegA (Addr 7)
		, Branch SPID (Rel 7)           {-Declaration of twoDone(=false)-}
		, Const 1 RegD
		, Push RegD
		, Push Zero
		, Pop RegA
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
		, Jump (Abs 32)                 {-Jump To Main Function-}
		, Const 0 RegD                  {-Declaration of i(=0); For loop declaration; Function main-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 0) RegA            {-For loop condition-}
		, Push RegA
		, Const 5 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 5-}
		, Branch RegA (Rel 54)          {-Break from for loop-}
		, Push SPID                     {-For loop body-}
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-$SPID Equal 0-}
		, Branch RegA (Rel 2)
		, Jump (Rel 20)
		, Const 0 RegD                  {-arr[i] = arr[i]+1-}
		, Read (Deref RegD)             {-Get base addr for array arr-}
		, Receive RegD
		, Load (Addr 0) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegE
		, Load (Addr 0) RegA
		, Compute Add RegE RegA RegD
		, Read (Deref RegD)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-arr[i] Add 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Jump (Rel 19)
		, Const 0 RegD                  {-arr[i] = arr[i]-1-}
		, Read (Deref RegD)             {-Get base addr for array arr-}
		, Receive RegD
		, Load (Addr 0) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegE
		, Load (Addr 0) RegA
		, Compute Add RegE RegA RegD
		, Read (Deref RegD)
		, Receive RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-arr[i] Sub 1-}
		, Pop RegD
		, Write RegA (Deref RegD)
		, Const 0 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-58))             {-Back to for loop-}
		, Push SPID
		, Const 1 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-$SPID Equal 1-}
		, Branch RegA (Rel 2)
		, Jump (Rel 7)
		, Const 1 RegD                  {-twoDone = true-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Write RegA (Deref RegD)
		, Jump (Rel 39)
		, Read (Addr 1)
		, Receive RegA
		, Const 1 RegB
		, Compute Nxor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel (0-5))
		, Const 3 RegD                  {-Declaration of b(=arraysEqual(arr,[1,2,3,4,5],5))-}
		, Push RegD
		, Read (Addr 0)
		, Receive RegA
		, Write RegA (Addr 11)
		, Const 1 RegA
		, Store RegA (Addr 59)
		, Const 2 RegA
		, Store RegA (Addr 60)
		, Const 3 RegA
		, Store RegA (Addr 61)
		, Const 4 RegA
		, Store RegA (Addr 62)
		, Const 5 RegA
		, Store RegA (Addr 63)
		, Const 59 RegA                 {-Base addr for (anon) array 1881561036-}
		, Store RegA (Addr 1)
		, Store RegA (Addr 12)
		, Const 5 RegA
		, Store RegA (Addr 13)
		, Const 139 RegA                {-Return addr-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 724)                {-Jump to function arraysEqual-}
		, Load (Addr 2) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 3) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Out RegA
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 41) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 41) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 41) RegA
		, Store RegA (Addr 64)
		, Const 64 RegA                 {-Base addr for (anon) array 1149377174-}
		, Store RegA (Addr 42)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 65 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 43)
		, Store Zero (Addr 65)
		, Store Zero (Addr 66)
		, Store Zero (Addr 67)
		, Store Zero (Addr 68)
		, Store Zero (Addr 69)
		, Store Zero (Addr 70)
		, Store Zero (Addr 71)
		, Store Zero (Addr 72)
		, Store Zero (Addr 73)
		, Store Zero (Addr 74)
		, Const 44 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 44) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 41 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 45 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 46 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 48 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 48) RegA
		, Push RegA
		, Load (Addr 41) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 48 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 45 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 46 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Load (Addr 45) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 43 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 47) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 46) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 47) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 41 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 45) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 44) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 43 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 47) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 43) RegA
		, Store RegA (Addr 23)
		, Const 312 RegA                {-Return addr-}
		, Push RegA
		, Const 49 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 319)                {-Jump to function shiftArray-}
		, Load (Addr 49) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 75 RegA                 {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 24)
		, Store Zero (Addr 75)
		, Store Zero (Addr 76)
		, Store Zero (Addr 77)
		, Store Zero (Addr 78)
		, Store Zero (Addr 79)
		, Store Zero (Addr 80)
		, Store Zero (Addr 81)
		, Store Zero (Addr 82)
		, Store Zero (Addr 83)
		, Store Zero (Addr 84)
		, Const 25 RegD                 {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 25) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 23) RegE
		, Load (Addr 25) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 25 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-j = j+1-}
		, Push RegD
		, Load (Addr 26) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 25) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 24 RegD                 {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 25) RegA
		, Push RegA
		, Load (Addr 26) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 23) RegE
		, Load (Addr 25) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 25 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 24) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 15) RegA           {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 15) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 15) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 85 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 16)
		, Store Zero (Addr 85)
		, Store Zero (Addr 86)
		, Store Zero (Addr 87)
		, Store Zero (Addr 88)
		, Store Zero (Addr 89)
		, Store Zero (Addr 90)
		, Store Zero (Addr 91)
		, Store Zero (Addr 92)
		, Store Zero (Addr 93)
		, Store Zero (Addr 94)
		, Const 17 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 15) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 17) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 15 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 15) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 18 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 19 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 20 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 21 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 21) RegA
		, Push RegA
		, Load (Addr 15) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 21 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 18 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 15) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 19 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 15) RegA
		, Push RegA
		, Load (Addr 18) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 16 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 20) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 19) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 20 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 20) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 15 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 18) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 17) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 16 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 20) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 20 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 20) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 22 RegD                 {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 20) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 22) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 16) RegE           {-For loop body-}
		, Load (Addr 22) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 22 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 22) RegA
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
		, Load (Addr 50) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 95)
		, Const 34 RegA
		, Store RegA (Addr 96)
		, Const 37 RegA
		, Store RegA (Addr 97)
		, Const 21 RegA
		, Store RegA (Addr 98)
		, Const 95 RegA                 {-Base addr for (anon) array 1409545055-}
		, Store RegA (Addr 51)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 99)
		, Const 17 RegA
		, Store RegA (Addr 100)
		, Const 28 RegA
		, Store RegA (Addr 101)
		, Const 35 RegA
		, Store RegA (Addr 102)
		, Const 21 RegA
		, Store RegA (Addr 103)
		, Const 99 RegA                 {-Base addr for (anon) array 454305524-}
		, Store RegA (Addr 52)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 7) RegA            {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 104)
		, Const 34 RegA
		, Store RegA (Addr 105)
		, Const 37 RegA
		, Store RegA (Addr 106)
		, Const 21 RegA
		, Store RegA (Addr 107)
		, Const 104 RegA                {-Base addr for (anon) array 442987331-}
		, Store RegA (Addr 8)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 108)
		, Const 17 RegA
		, Store RegA (Addr 109)
		, Const 28 RegA
		, Store RegA (Addr 110)
		, Const 35 RegA
		, Store RegA (Addr 111)
		, Const 21 RegA
		, Store RegA (Addr 112)
		, Const 108 RegA                {-Base addr for (anon) array 1016550616-}
		, Store RegA (Addr 9)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 113)
		, Const 113 RegA                {-Base addr for (anon) array 645482568-}
		, Store RegA (Addr 10)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 53) RegA           {-Function boolToInt-}
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
		, Load (Addr 54) RegA           {-Function intToBool-}
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
		, Const 14 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 14) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 13) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 11) RegE           {-For loop body-}
		, Load (Addr 14) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 12) RegE
		, Load (Addr 14) RegA
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
		, Const 14 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 14) RegA
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
		, Const 35 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 35) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 34) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 32) RegE           {-For loop body-}
		, Load (Addr 35) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 33) RegE
		, Load (Addr 35) RegA
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
		, Const 35 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 35) RegA
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
		, Const 57 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 57) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 56) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 55) RegE           {-For loop body-}
		, Load (Addr 57) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 15)
		, Const 849 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 415)                {-Jump to function printInt-}
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
		, Const 57 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 57) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 55) RegE
		, Load (Addr 56) RegA
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
		, Const 6 RegD                  {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 6) RegA            {-For loop condition-}
		, Push RegA
		, Load (Addr 5) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 4) RegE            {-For loop body-}
		, Load (Addr 6) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 50)
		, Const 912 RegA                {-Return addr-}
		, Push RegA
		, Const 7 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 604)                {-Jump to function printBool-}
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
		, Const 6 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 6) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 4) RegE
		, Load (Addr 5) RegA
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
		, Const 29 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 28) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 29) RegA
		, Push RegA
		, Load (Addr 30) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 31 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 27) RegE
		, Load (Addr 30) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 30) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 27) RegE
		, Load (Addr 29) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 29) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 31) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 29) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 30) RegA
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
		, Const 38 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 39 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 38) RegA
		, Push RegA
		, Load (Addr 39) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 40 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 36) RegE
		, Load (Addr 39) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 39) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 36) RegE
		, Load (Addr 38) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 38) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 40) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 39 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 39) RegA
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

main = run 2 prog
