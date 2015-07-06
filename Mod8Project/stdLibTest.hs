{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1445 RegA               {-Initial Return Addr-}
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
		, Const 35 RegD                 {-Declaration of result(=0); Function main-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 23 RegA                 {-Return addr-}
		, Push RegA
		, Const 36 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 57)                 {-Jump to function testArraysEqual-}
		, Load (Addr 36) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 35 RegD                 {-result = 1-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegA                 {-Return addr-}
		, Push RegA
		, Const 37 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 208)                {-Jump to function testIntToStr-}
		, Load (Addr 37) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 35 RegD                 {-result = 2-}
		, Push RegD
		, Const 2 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 35) RegA
		, Out RegA
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 78 RegA                 {-Base addr for array a; Function testArraysEqual-}
		, Store RegA (Addr 54)
		, Const 1 RegA
		, Store RegA (Addr 78)
		, Const 2 RegA
		, Store RegA (Addr 79)
		, Const 3 RegA
		, Store RegA (Addr 80)
		, Const 81 RegA                 {-Base addr for array b-}
		, Store RegA (Addr 55)
		, Const 1 RegA
		, Store RegA (Addr 81)
		, Const 2 RegA
		, Store RegA (Addr 82)
		, Const 3 RegA
		, Store RegA (Addr 83)
		, Const 84 RegA                 {-Base addr for array c-}
		, Store RegA (Addr 56)
		, Const 0 RegA
		, Store RegA (Addr 84)
		, Const 0 RegA
		, Store RegA (Addr 85)
		, Const 86 RegA                 {-Base addr for array d-}
		, Store RegA (Addr 57)
		, Store Zero (Addr 86)
		, Store Zero (Addr 87)
		, Load (Addr 54) RegA
		, Store RegA (Addr 19)
		, Load (Addr 54) RegA
		, Store RegA (Addr 20)
		, Const 3 RegA
		, Store RegA (Addr 21)
		, Const 94 RegA                 {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 58) RegA
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
		, Load (Addr 54) RegA
		, Store RegA (Addr 19)
		, Load (Addr 55) RegA
		, Store RegA (Addr 20)
		, Const 3 RegA
		, Store RegA (Addr 21)
		, Const 118 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 58) RegA
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
		, Load (Addr 55) RegA
		, Store RegA (Addr 19)
		, Load (Addr 54) RegA
		, Store RegA (Addr 20)
		, Const 3 RegA
		, Store RegA (Addr 21)
		, Const 142 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 58) RegA
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
		, Load (Addr 54) RegA
		, Store RegA (Addr 19)
		, Load (Addr 56) RegA
		, Store RegA (Addr 20)
		, Const 2 RegA
		, Store RegA (Addr 21)
		, Const 166 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 58) RegA
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
		, Load (Addr 56) RegA
		, Store RegA (Addr 19)
		, Load (Addr 57) RegA
		, Store RegA (Addr 20)
		, Const 2 RegA
		, Store RegA (Addr 21)
		, Const 188 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 58) RegA
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
		, Const 0 RegD                  {-Declaration of i1(=1234); Function testIntToStr-}
		, Push RegD
		, Const 1234 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 88 RegA                 {-Base addr for array arr1-}
		, Store RegA (Addr 1)
		, Const 1 RegA
		, Store RegA (Addr 88)
		, Const 2 RegA
		, Store RegA (Addr 89)
		, Const 3 RegA
		, Store RegA (Addr 90)
		, Const 4 RegA
		, Store RegA (Addr 91)
		, Load (Addr 1) RegA
		, Store RegA (Addr 19)
		, Load (Addr 0) RegA
		, Store RegA (Addr 10)
		, Const 232 RegA                {-Return addr-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 2) RegA
		, Store RegA (Addr 20)
		, Const 4 RegA
		, Store RegA (Addr 21)
		, Const 241 RegA                {-Return addr-}
		, Push RegA
		, Const 3 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 3) RegA
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
		, Const 4 RegD                  {-Declaration of i2(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 92 RegA                 {-Base addr for array arr2-}
		, Store RegA (Addr 5)
		, Const 0 RegA
		, Store RegA (Addr 92)
		, Load (Addr 5) RegA
		, Store RegA (Addr 19)
		, Load (Addr 4) RegA
		, Store RegA (Addr 10)
		, Const 272 RegA                {-Return addr-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 2) RegA
		, Store RegA (Addr 20)
		, Const 1 RegA
		, Store RegA (Addr 21)
		, Const 281 RegA                {-Return addr-}
		, Push RegA
		, Const 3 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 3) RegA
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
		, Const 6 RegD                  {-Declaration of i3(=9876)-}
		, Push RegD
		, Const 9876 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 93 RegA                 {-Base addr for array arr3-}
		, Store RegA (Addr 7)
		, Const 9 RegA
		, Store RegA (Addr 93)
		, Const 8 RegA
		, Store RegA (Addr 94)
		, Const 7 RegA
		, Store RegA (Addr 95)
		, Const 6 RegA
		, Store RegA (Addr 96)
		, Load (Addr 7) RegA
		, Store RegA (Addr 19)
		, Load (Addr 6) RegA
		, Store RegA (Addr 10)
		, Const 318 RegA                {-Return addr-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 2) RegA
		, Store RegA (Addr 20)
		, Const 4 RegA
		, Store RegA (Addr 21)
		, Const 327 RegA                {-Return addr-}
		, Push RegA
		, Const 3 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 3) RegA
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
		, Const 8 RegD                  {-Declaration of i4(=-10)-}
		, Push RegD
		, Const 10 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 97 RegA                 {-Base addr for array arr4-}
		, Store RegA (Addr 9)
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Store RegA (Addr 97)
		, Const 1 RegA
		, Store RegA (Addr 98)
		, Const 0 RegA
		, Store RegA (Addr 99)
		, Load (Addr 9) RegA
		, Store RegA (Addr 19)
		, Load (Addr 8) RegA
		, Store RegA (Addr 10)
		, Const 368 RegA                {-Return addr-}
		, Push RegA
		, Const 2 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 508)                {-Jump to function intToStr-}
		, Load (Addr 2) RegA
		, Store RegA (Addr 20)
		, Const 3 RegA
		, Store RegA (Addr 21)
		, Const 377 RegA                {-Return addr-}
		, Push RegA
		, Const 3 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 3) RegA
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
		, Const 100 RegA                {-Base addr for array x; Function testMirrorArray-}
		, Store RegA (Addr 68)
		, Const 4 RegA
		, Store RegA (Addr 100)
		, Const 3 RegA
		, Store RegA (Addr 101)
		, Const 2 RegA
		, Store RegA (Addr 102)
		, Const 1 RegA
		, Store RegA (Addr 103)
		, Const 104 RegA                {-Base addr for array y-}
		, Store RegA (Addr 69)
		, Const 1 RegA
		, Store RegA (Addr 104)
		, Const 2 RegA
		, Store RegA (Addr 105)
		, Const 3 RegA
		, Store RegA (Addr 106)
		, Const 4 RegA
		, Store RegA (Addr 107)
		, Const 108 RegA                {-Base addr for array a-}
		, Store RegA (Addr 70)
		, Const 1 RegA
		, Store RegA (Addr 108)
		, Push Zero
		, Pop RegA
		, Store RegA (Addr 109)
		, Const 1 RegA
		, Store RegA (Addr 110)
		, Const 111 RegA                {-Base addr for array b-}
		, Store RegA (Addr 71)
		, Const 1 RegA
		, Store RegA (Addr 111)
		, Push Zero
		, Pop RegA
		, Store RegA (Addr 112)
		, Const 1 RegA
		, Store RegA (Addr 113)
		, Load (Addr 68) RegA
		, Store RegA (Addr 59)
		, Const 4 RegA
		, Store RegA (Addr 60)
		, Const 444 RegA                {-Return addr-}
		, Push RegA
		, Const 72 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1303)               {-Jump to function flipArray-}
		, Load (Addr 70) RegA
		, Store RegA (Addr 30)
		, Const 3 RegA
		, Store RegA (Addr 31)
		, Const 453 RegA                {-Return addr-}
		, Push RegA
		, Const 72 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1374)               {-Jump to function flipArray-}
		, Load (Addr 68) RegA
		, Store RegA (Addr 19)
		, Load (Addr 69) RegA
		, Store RegA (Addr 20)
		, Const 4 RegA
		, Store RegA (Addr 21)
		, Const 464 RegA                {-Return addr-}
		, Push RegA
		, Const 72 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1079)               {-Jump to function arraysEqual-}
		, Load (Addr 72) RegA
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
		, Load (Addr 70) RegA
		, Store RegA (Addr 64)
		, Load (Addr 71) RegA
		, Store RegA (Addr 65)
		, Const 3 RegA
		, Store RegA (Addr 66)
		, Const 488 RegA                {-Return addr-}
		, Push RegA
		, Const 73 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 1128)               {-Jump to function arraysEqual-}
		, Load (Addr 73) RegA
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
		, Load (Addr 10) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 10) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 10) RegA
		, Store RegA (Addr 114)
		, Const 114 RegA                {-Base addr for (anon) array 289710123-}
		, Store RegA (Addr 11)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 115 RegA                {-Base addr for array res-}
		, Store RegA (Addr 12)
		, Store Zero (Addr 115)
		, Store Zero (Addr 116)
		, Store Zero (Addr 117)
		, Store Zero (Addr 118)
		, Store Zero (Addr 119)
		, Store Zero (Addr 120)
		, Store Zero (Addr 121)
		, Store Zero (Addr 122)
		, Store Zero (Addr 123)
		, Store Zero (Addr 124)
		, Const 13 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 13) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 10 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 14 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 15 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 16 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 17 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 17) RegA
		, Push RegA
		, Load (Addr 10) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 17 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 14 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 15 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Load (Addr 14) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 12 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 16) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 15) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 16 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 16) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 10 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 14) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 13) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 12 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 16) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 12) RegA
		, Store RegA (Addr 26)
		, Const 667 RegA                {-Return addr-}
		, Push RegA
		, Const 18 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 674)                {-Jump to function shiftArray-}
		, Load (Addr 18) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 125 RegA                {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 27)
		, Store Zero (Addr 125)
		, Store Zero (Addr 126)
		, Store Zero (Addr 127)
		, Store Zero (Addr 128)
		, Store Zero (Addr 129)
		, Store Zero (Addr 130)
		, Store Zero (Addr 131)
		, Store Zero (Addr 132)
		, Store Zero (Addr 133)
		, Store Zero (Addr 134)
		, Const 28 RegD                 {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 28) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 26) RegE
		, Load (Addr 28) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 28 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 28) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-j = j+1-}
		, Push RegD
		, Load (Addr 29) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 28) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 27 RegD                 {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 28) RegA
		, Push RegA
		, Load (Addr 29) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 26) RegE
		, Load (Addr 28) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 28) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 27) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 46) RegA           {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 46) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 46) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 135 RegA                {-Base addr for array res-}
		, Store RegA (Addr 47)
		, Store Zero (Addr 135)
		, Store Zero (Addr 136)
		, Store Zero (Addr 137)
		, Store Zero (Addr 138)
		, Store Zero (Addr 139)
		, Store Zero (Addr 140)
		, Store Zero (Addr 141)
		, Store Zero (Addr 142)
		, Store Zero (Addr 143)
		, Store Zero (Addr 144)
		, Const 48 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 46) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 48) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 46 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 46) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 49 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 50 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 51 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 52 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 52) RegA
		, Push RegA
		, Load (Addr 46) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 52 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 49 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 46) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 50 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 46) RegA
		, Push RegA
		, Load (Addr 49) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 51) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 50) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 51 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 51) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 46 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 49) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 48) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 47 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 51) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 51 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 51) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 53 RegD                 {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 51) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 53) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 47) RegE           {-For loop body-}
		, Load (Addr 53) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 53 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 53) RegA
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
		, Load (Addr 74) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 145)
		, Const 34 RegA
		, Store RegA (Addr 146)
		, Const 37 RegA
		, Store RegA (Addr 147)
		, Const 21 RegA
		, Store RegA (Addr 148)
		, Const 145 RegA                {-Base addr for (anon) array 387137936-}
		, Store RegA (Addr 75)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 149)
		, Const 17 RegA
		, Store RegA (Addr 150)
		, Const 28 RegA
		, Store RegA (Addr 151)
		, Const 35 RegA
		, Store RegA (Addr 152)
		, Const 21 RegA
		, Store RegA (Addr 153)
		, Const 149 RegA                {-Base addr for (anon) array 1686091139-}
		, Store RegA (Addr 76)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 42) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 154)
		, Const 34 RegA
		, Store RegA (Addr 155)
		, Const 37 RegA
		, Store RegA (Addr 156)
		, Const 21 RegA
		, Store RegA (Addr 157)
		, Const 154 RegA                {-Base addr for (anon) array 945150386-}
		, Store RegA (Addr 43)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 158)
		, Const 17 RegA
		, Store RegA (Addr 159)
		, Const 28 RegA
		, Store RegA (Addr 160)
		, Const 35 RegA
		, Store RegA (Addr 161)
		, Const 21 RegA
		, Store RegA (Addr 162)
		, Const 158 RegA                {-Base addr for (anon) array 1836777499-}
		, Store RegA (Addr 44)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 163)
		, Const 163 RegA                {-Base addr for (anon) array 225137455-}
		, Store RegA (Addr 45)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 77) RegA           {-Function boolToInt-}
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
		, Load (Addr 38) RegA           {-Function intToBool-}
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
		, Const 22 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 22) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 21) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 19) RegE           {-For loop body-}
		, Load (Addr 22) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 20) RegE
		, Load (Addr 22) RegA
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
		, Const 22 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 22) RegA
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
		, Const 67 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 67) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 66) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 64) RegE           {-For loop body-}
		, Load (Addr 67) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 65) RegE
		, Load (Addr 67) RegA
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
		, Const 67 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 67) RegA
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
		, Const 25 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 25) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 24) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 23) RegE           {-For loop body-}
		, Load (Addr 25) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 46)
		, Const 1204 RegA               {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
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
		, Const 25 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 23) RegE
		, Load (Addr 24) RegA
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
		, Const 41 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 41) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 40) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 39) RegE           {-For loop body-}
		, Load (Addr 41) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 74)
		, Const 1267 RegA               {-Return addr-}
		, Push RegA
		, Const 42 RegA                 {-Result addr-}
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
		, Const 41 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 39) RegE
		, Load (Addr 40) RegA
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
		, Const 61 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 62 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 60) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 61) RegA
		, Push RegA
		, Load (Addr 62) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 63 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 59) RegE
		, Load (Addr 62) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 59 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 62) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 59) RegE
		, Load (Addr 61) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 59 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 61) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 63) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 61 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 61) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 62 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 62) RegA
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
		, Const 32 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 31) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 32) RegA
		, Push RegA
		, Load (Addr 33) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 34 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 30) RegE
		, Load (Addr 33) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 33) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 30) RegE
		, Load (Addr 32) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 32) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 34) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 32 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 32) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 33) RegA
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

main = run 1 prog
