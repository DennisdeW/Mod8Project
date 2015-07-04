{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1465 RegA               {-Initial Return Addr-}
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
		, Const 12 RegD                 {-Declaration of result(=0); Function main-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 23 RegA                 {-Return addr-}
		, Push RegA
		, Const 13 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 57)                 {-Jump to function testArraysEqual-}
		, Load (Addr 13) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 12 RegD                 {-result = 1-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegA                 {-Return addr-}
		, Push RegA
		, Const 14 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 208)                {-Jump to function testIntToStr-}
		, Load (Addr 14) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 12 RegD                 {-result = 2-}
		, Push RegD
		, Const 2 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 12) RegA
		, Out RegA
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 77 RegA                 {-Base addr for array a; Function testArraysEqual-}
		, Store RegA (Addr 60)
		, Const 1 RegA
		, Store RegA (Addr 77)
		, Const 2 RegA
		, Store RegA (Addr 78)
		, Const 3 RegA
		, Store RegA (Addr 79)
		, Const 80 RegA                 {-Base addr for array b-}
		, Store RegA (Addr 61)
		, Const 1 RegA
		, Store RegA (Addr 80)
		, Const 2 RegA
		, Store RegA (Addr 81)
		, Const 3 RegA
		, Store RegA (Addr 82)
		, Const 83 RegA                 {-Base addr for array c-}
		, Store RegA (Addr 62)
		, Const 0 RegA
		, Store RegA (Addr 83)
		, Const 0 RegA
		, Store RegA (Addr 84)
		, Const 85 RegA                 {-Base addr for array d-}
		, Store RegA (Addr 63)
		, Store Zero (Addr 85)
		, Store Zero (Addr 86)
		, Load (Addr 60) RegA
		, Store RegA (Addr 34)
		, Load (Addr 60) RegA
		, Store RegA (Addr 35)
		, Const 3 RegA
		, Store RegA (Addr 36)
		, Const 94 RegA                 {-Return addr-}
		, Push RegA
		, Const 64 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 64) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Load (Addr 60) RegA
		, Store RegA (Addr 34)
		, Load (Addr 61) RegA
		, Store RegA (Addr 35)
		, Const 3 RegA
		, Store RegA (Addr 36)
		, Const 118 RegA                {-Return addr-}
		, Push RegA
		, Const 64 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 64) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Load (Addr 61) RegA
		, Store RegA (Addr 34)
		, Load (Addr 60) RegA
		, Store RegA (Addr 35)
		, Const 3 RegA
		, Store RegA (Addr 36)
		, Const 142 RegA                {-Return addr-}
		, Push RegA
		, Const 64 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 64) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Load (Addr 60) RegA
		, Store RegA (Addr 34)
		, Load (Addr 62) RegA
		, Store RegA (Addr 35)
		, Const 2 RegA
		, Store RegA (Addr 36)
		, Const 166 RegA                {-Return addr-}
		, Push RegA
		, Const 64 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 64) RegA
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
		, Load (Addr 62) RegA
		, Store RegA (Addr 34)
		, Load (Addr 63) RegA
		, Store RegA (Addr 35)
		, Const 2 RegA
		, Store RegA (Addr 36)
		, Const 188 RegA                {-Return addr-}
		, Push RegA
		, Const 64 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 64) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Const 38 RegD                 {-Declaration of i1(=1234); Function testIntToStr-}
		, Push RegD
		, Const 1234 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 87 RegA                 {-Base addr for array arr1-}
		, Store RegA (Addr 39)
		, Const 1 RegA
		, Store RegA (Addr 87)
		, Const 2 RegA
		, Store RegA (Addr 88)
		, Const 3 RegA
		, Store RegA (Addr 89)
		, Const 4 RegA
		, Store RegA (Addr 90)
		, Load (Addr 39) RegA
		, Store RegA (Addr 34)
		, Load (Addr 38) RegA
		, Store RegA (Addr 22)
		, Const 232 RegA                {-Return addr-}
		, Push RegA
		, Const 40 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 40) RegA
		, Store RegA (Addr 35)
		, Const 4 RegA
		, Store RegA (Addr 36)
		, Const 241 RegA                {-Return addr-}
		, Push RegA
		, Const 41 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 41) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Const 42 RegD                 {-Declaration of i2(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 91 RegA                 {-Base addr for array arr2-}
		, Store RegA (Addr 43)
		, Const 0 RegA
		, Store RegA (Addr 91)
		, Load (Addr 43) RegA
		, Store RegA (Addr 34)
		, Load (Addr 42) RegA
		, Store RegA (Addr 22)
		, Const 272 RegA                {-Return addr-}
		, Push RegA
		, Const 40 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 40) RegA
		, Store RegA (Addr 35)
		, Const 1 RegA
		, Store RegA (Addr 36)
		, Const 281 RegA                {-Return addr-}
		, Push RegA
		, Const 41 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 41) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Const 44 RegD                 {-Declaration of i3(=9876)-}
		, Push RegD
		, Const 9876 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 92 RegA                 {-Base addr for array arr3-}
		, Store RegA (Addr 45)
		, Const 9 RegA
		, Store RegA (Addr 92)
		, Const 8 RegA
		, Store RegA (Addr 93)
		, Const 7 RegA
		, Store RegA (Addr 94)
		, Const 6 RegA
		, Store RegA (Addr 95)
		, Load (Addr 45) RegA
		, Store RegA (Addr 34)
		, Load (Addr 44) RegA
		, Store RegA (Addr 22)
		, Const 318 RegA                {-Return addr-}
		, Push RegA
		, Const 40 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 40) RegA
		, Store RegA (Addr 35)
		, Const 4 RegA
		, Store RegA (Addr 36)
		, Const 327 RegA                {-Return addr-}
		, Push RegA
		, Const 41 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 41) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Const 46 RegD                 {-Declaration of i4(=-10)-}
		, Push RegD
		, Const 10 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 96 RegA                 {-Base addr for array arr4-}
		, Store RegA (Addr 47)
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Store RegA (Addr 96)
		, Const 1 RegA
		, Store RegA (Addr 97)
		, Const 0 RegA
		, Store RegA (Addr 98)
		, Load (Addr 47) RegA
		, Store RegA (Addr 34)
		, Load (Addr 46) RegA
		, Store RegA (Addr 22)
		, Const 368 RegA                {-Return addr-}
		, Push RegA
		, Const 40 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 40) RegA
		, Store RegA (Addr 35)
		, Const 3 RegA
		, Store RegA (Addr 36)
		, Const 377 RegA                {-Return addr-}
		, Push RegA
		, Const 41 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 41) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Const 99 RegA                 {-Base addr for array x; Function testMirrorArray-}
		, Store RegA (Addr 49)
		, Const 4 RegA
		, Store RegA (Addr 99)
		, Const 3 RegA
		, Store RegA (Addr 100)
		, Const 2 RegA
		, Store RegA (Addr 101)
		, Const 1 RegA
		, Store RegA (Addr 102)
		, Const 103 RegA                {-Base addr for array y-}
		, Store RegA (Addr 50)
		, Const 1 RegA
		, Store RegA (Addr 103)
		, Const 2 RegA
		, Store RegA (Addr 104)
		, Const 3 RegA
		, Store RegA (Addr 105)
		, Const 4 RegA
		, Store RegA (Addr 106)
		, Const 107 RegA                {-Base addr for array a-}
		, Store RegA (Addr 51)
		, Const 1 RegA
		, Store RegA (Addr 107)
		, Push Zero
		, Pop RegA
		, Store RegA (Addr 108)
		, Const 1 RegA
		, Store RegA (Addr 109)
		, Const 110 RegA                {-Base addr for array b-}
		, Store RegA (Addr 52)
		, Const 1 RegA
		, Store RegA (Addr 110)
		, Push Zero
		, Pop RegA
		, Store RegA (Addr 111)
		, Const 1 RegA
		, Store RegA (Addr 112)
		, Load (Addr 49) RegA
		, Store RegA (Addr 4)
		, Const 4 RegA
		, Store RegA (Addr 5)
		, Const 444 RegA                {-Return addr-}
		, Push RegA
		, Const 53 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1293)               {-Jump to function flipArray-}
		, Load (Addr 51) RegA
		, Store RegA (Addr 55)
		, Const 3 RegA
		, Store RegA (Addr 56)
		, Const 453 RegA                {-Return addr-}
		, Push RegA
		, Const 53 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1359)               {-Jump to function flipArray-}
		, Load (Addr 49) RegA
		, Store RegA (Addr 34)
		, Load (Addr 50) RegA
		, Store RegA (Addr 35)
		, Const 4 RegA
		, Store RegA (Addr 36)
		, Const 464 RegA                {-Return addr-}
		, Push RegA
		, Const 53 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1069)               {-Jump to function arraysEqual-}
		, Load (Addr 53) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Load (Addr 51) RegA
		, Store RegA (Addr 73)
		, Load (Addr 52) RegA
		, Store RegA (Addr 74)
		, Const 3 RegA
		, Store RegA (Addr 75)
		, Const 488 RegA                {-Return addr-}
		, Push RegA
		, Const 54 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1118)               {-Jump to function arraysEqual-}
		, Load (Addr 54) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
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
		, Load (Addr 22) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 22) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 22) RegA
		, Store RegA (Addr 113)
		, Const 113 RegA                {-Base addr for (anon) array 418958713-}
		, Store RegA (Addr 23)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 114 RegA                {-Base addr for array res-}
		, Store RegA (Addr 24)
		, Store Zero (Addr 114)
		, Store Zero (Addr 115)
		, Store Zero (Addr 116)
		, Store Zero (Addr 117)
		, Store Zero (Addr 118)
		, Store Zero (Addr 119)
		, Store Zero (Addr 120)
		, Store Zero (Addr 121)
		, Store Zero (Addr 122)
		, Store Zero (Addr 123)
		, Const 25 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 22) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 25) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 22 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 22) RegA
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
		, Load (Addr 22) RegA
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
		, Load (Addr 22) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 22) RegA
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
		, Const 22 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 26) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 25) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
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
		, Load (Addr 24) RegA
		, Store RegA (Addr 0)
		, Const 667 RegA                {-Return addr-}
		, Push RegA
		, Const 30 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 674)                {-Jump to function shiftArray-}
		, Load (Addr 30) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 124 RegA                {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 1)
		, Store Zero (Addr 124)
		, Store Zero (Addr 125)
		, Store Zero (Addr 126)
		, Store Zero (Addr 127)
		, Store Zero (Addr 128)
		, Store Zero (Addr 129)
		, Store Zero (Addr 130)
		, Store Zero (Addr 131)
		, Store Zero (Addr 132)
		, Store Zero (Addr 133)
		, Const 2 RegD                  {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 2) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 0) RegE
		, Load (Addr 2) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 2 RegD                  {-i = i+1-}
		, Push RegD
		, Load (Addr 2) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-j = j+1-}
		, Push RegD
		, Load (Addr 3) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 2) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 1 RegD                  {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 2) RegA
		, Push RegA
		, Load (Addr 3) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 0) RegE
		, Load (Addr 2) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 2 RegD                  {-i = i+1-}
		, Push RegD
		, Load (Addr 2) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 1) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 65) RegA           {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 65) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 65) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 134 RegA                {-Base addr for array res-}
		, Store RegA (Addr 66)
		, Store Zero (Addr 134)
		, Store Zero (Addr 135)
		, Store Zero (Addr 136)
		, Store Zero (Addr 137)
		, Store Zero (Addr 138)
		, Store Zero (Addr 139)
		, Store Zero (Addr 140)
		, Store Zero (Addr 141)
		, Store Zero (Addr 142)
		, Store Zero (Addr 143)
		, Const 67 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 65) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 67) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 65 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 65) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 68 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 69 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 70 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 71 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 71) RegA
		, Push RegA
		, Load (Addr 65) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 71 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 68 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 65) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 69 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 65) RegA
		, Push RegA
		, Load (Addr 68) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 66 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 70) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 69) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 70 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 70) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 65 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 68) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 67) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 66 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 70) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 70 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 70) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 72 RegD                 {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 70) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 72) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 66) RegE           {-For loop body-}
		, Load (Addr 72) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 72 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 72) RegA
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
		, Load (Addr 15) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 144)
		, Const 34 RegA
		, Store RegA (Addr 145)
		, Const 37 RegA
		, Store RegA (Addr 146)
		, Const 21 RegA
		, Store RegA (Addr 147)
		, Const 144 RegA                {-Base addr for (anon) array 1179792105-}
		, Store RegA (Addr 16)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 148)
		, Const 17 RegA
		, Store RegA (Addr 149)
		, Const 28 RegA
		, Store RegA (Addr 150)
		, Const 35 RegA
		, Store RegA (Addr 151)
		, Const 21 RegA
		, Store RegA (Addr 152)
		, Const 148 RegA                {-Base addr for (anon) array 106999035-}
		, Store RegA (Addr 17)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 31) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 153)
		, Const 34 RegA
		, Store RegA (Addr 154)
		, Const 37 RegA
		, Store RegA (Addr 155)
		, Const 21 RegA
		, Store RegA (Addr 156)
		, Const 153 RegA                {-Base addr for (anon) array 2112233878-}
		, Store RegA (Addr 32)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 157)
		, Const 17 RegA
		, Store RegA (Addr 158)
		, Const 28 RegA
		, Store RegA (Addr 159)
		, Const 35 RegA
		, Store RegA (Addr 160)
		, Const 21 RegA
		, Store RegA (Addr 161)
		, Const 157 RegA                {-Base addr for (anon) array 333040629-}
		, Store RegA (Addr 33)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 21) RegA           {-Function boolToInt-}
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
		, Load (Addr 48) RegA           {-Function intToBool-}
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
		, Const 37 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 37) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 36) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 34) RegE           {-For loop body-}
		, Load (Addr 37) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 35) RegE
		, Load (Addr 37) RegA
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
		, Const 37 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 37) RegA
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
		, Const 76 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 76) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 75) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 73) RegE           {-For loop body-}
		, Load (Addr 76) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 74) RegE
		, Load (Addr 76) RegA
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
		, Const 76 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 76) RegA
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
		, Const 20 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 20) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 19) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 18) RegE           {-For loop body-}
		, Load (Addr 20) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 65)
		, Const 1194 RegA               {-Return addr-}
		, Push RegA
		, Const 21 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 770)                {-Jump to function printInt-}
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
		, Const 20 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 20) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 18) RegE
		, Load (Addr 19) RegA
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
		, Store RegA (Addr 15)
		, Const 1257 RegA               {-Return addr-}
		, Push RegA
		, Const 12 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 959)                {-Jump to function printBool-}
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
		, Const 57 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 58 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 56) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 57) RegA
		, Push RegA
		, Load (Addr 58) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 59 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 55) RegE
		, Load (Addr 58) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 55 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 58) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 55) RegE
		, Load (Addr 57) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 55 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 57) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 59) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 57 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 57) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 58 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 58) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-j Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-51))
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

main = run 1 prog
