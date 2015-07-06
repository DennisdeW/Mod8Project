{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1270 RegA               {-Initial Return Addr-}
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
		, Const 27 RegD                 {-Declaration of result(=0); Function main-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 23 RegA                 {-Return addr-}
		, Push RegA
		, Const 28 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 57)                 {-Jump to function testGcd-}
		, Load (Addr 28) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 27 RegD                 {-result = 1-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegA                 {-Return addr-}
		, Push RegA
		, Const 29 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 174)                {-Jump to function testFac-}
		, Load (Addr 29) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 27 RegD                 {-result = 2-}
		, Push RegD
		, Const 2 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 27) RegA
		, Out RegA
		, Load (Addr 27) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 6 RegA                  {-Function testGcd-}
		, Store RegA (Addr 17)
		, Const 9 RegA
		, Store RegA (Addr 18)
		, Const 66 RegA                 {-Return addr-}
		, Push RegA
		, Const 25 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 25) RegA
		, Push RegA                     {-Store Result-}
		, Const 3 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-gcd(6,9) NEq 3-}
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
		, Const 100 RegA
		, Store RegA (Addr 17)
		, Const 20 RegA
		, Store RegA (Addr 18)
		, Const 90 RegA                 {-Return addr-}
		, Push RegA
		, Const 25 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 25) RegA
		, Push RegA                     {-Store Result-}
		, Const 20 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-gcd(100,20) NEq 20-}
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
		, Const 17 RegA
		, Store RegA (Addr 17)
		, Const 5 RegA
		, Store RegA (Addr 18)
		, Const 114 RegA                {-Return addr-}
		, Push RegA
		, Const 25 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 25) RegA
		, Push RegA                     {-Store Result-}
		, Const 1 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-gcd(17,5) NEq 1-}
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
		, Const 19 RegD                 {-Declaration of t(=0); Function gcd-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 18) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-y NEq 0-}
		, Branch RegA (Rel 21)
		, Const 19 RegD                 {-t = y-}
		, Push RegD
		, Load (Addr 18) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 18 RegD                 {-y = x%y-}
		, Push RegD
		, Load (Addr 17) RegA
		, Push RegA
		, Load (Addr 18) RegA
		, Pop RegB
		, Compute Mod RegB RegA RegA    {-x Mod y-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 17 RegD                 {-x = t-}
		, Push RegD
		, Load (Addr 19) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-25))
		, Load (Addr 17) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 3 RegA                  {-Function testFac-}
		, Store RegA (Addr 34)
		, Const 181 RegA                {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 26) RegA
		, Push RegA                     {-Store Result-}
		, Const 6 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-fac(3) NEq 6-}
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
		, Const 4 RegA
		, Store RegA (Addr 34)
		, Const 203 RegA                {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 26) RegA
		, Push RegA                     {-Store Result-}
		, Const 24 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-fac(4) NEq 24-}
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
		, Const 5 RegA
		, Store RegA (Addr 34)
		, Const 225 RegA                {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 26) RegA
		, Push RegA                     {-Store Result-}
		, Const 120 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-fac(5) NEq 120-}
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
		, Const 10 RegA
		, Store RegA (Addr 34)
		, Const 247 RegA                {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 26) RegA
		, Push RegA                     {-Store Result-}
		, Const 3628800 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-fac(10) NEq 3628800-}
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
		, Const 0 RegA
		, Store RegA (Addr 34)
		, Const 269 RegA                {-Return addr-}
		, Push RegA
		, Const 26 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 26) RegA
		, Push RegA                     {-Store Result-}
		, Const 1 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-fac(0) NEq 1-}
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
		, Const 35 RegD                 {-Declaration of res(=1); Function fac-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-Declaration of i(=1); For loop declaration-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 36) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 34) RegA
		, Pop RegB
		, Compute Gt RegB RegA RegA     {-i LtE n-}
		, Branch RegA (Rel 20)          {-Break from for loop-}
		, Const 35 RegD                 {-res = res*i; For loop body-}
		, Push RegD
		, Load (Addr 35) RegA
		, Push RegA
		, Load (Addr 36) RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-res Mul i-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 36) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-24))             {-Back to for loop-}
		, Load (Addr 35) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 37) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 37) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 37) RegA
		, Store RegA (Addr 66)
		, Const 66 RegA                 {-Base addr for (anon) array 428089645-}
		, Store RegA (Addr 38)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 67 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 39)
		, Store Zero (Addr 67)
		, Store Zero (Addr 68)
		, Store Zero (Addr 69)
		, Store Zero (Addr 70)
		, Store Zero (Addr 71)
		, Store Zero (Addr 72)
		, Store Zero (Addr 73)
		, Store Zero (Addr 74)
		, Store Zero (Addr 75)
		, Store Zero (Addr 76)
		, Const 40 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 40) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 37 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 41 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 43 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 44 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 44) RegA
		, Push RegA
		, Load (Addr 37) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 44 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 41 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 37) RegA
		, Push RegA
		, Load (Addr 41) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 39 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 43) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 42) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 43 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 43) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 37 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 41) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 40) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 39 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 43) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 39) RegA
		, Store RegA (Addr 8)
		, Const 492 RegA                {-Return addr-}
		, Push RegA
		, Const 45 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 499)                {-Jump to function shiftArray-}
		, Load (Addr 45) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 77 RegA                 {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 9)
		, Store Zero (Addr 77)
		, Store Zero (Addr 78)
		, Store Zero (Addr 79)
		, Store Zero (Addr 80)
		, Store Zero (Addr 81)
		, Store Zero (Addr 82)
		, Store Zero (Addr 83)
		, Store Zero (Addr 84)
		, Store Zero (Addr 85)
		, Store Zero (Addr 86)
		, Const 10 RegD                 {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 11 RegD                 {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 10) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 8) RegE
		, Load (Addr 10) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 10 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 11 RegD                 {-j = j+1-}
		, Push RegD
		, Load (Addr 11) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 10) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 9 RegD                  {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 10) RegA
		, Push RegA
		, Load (Addr 11) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 8) RegE
		, Load (Addr 10) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 10 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 10) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 9) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 0) RegA            {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 0) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 0) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 87 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 1)
		, Store Zero (Addr 87)
		, Store Zero (Addr 88)
		, Store Zero (Addr 89)
		, Store Zero (Addr 90)
		, Store Zero (Addr 91)
		, Store Zero (Addr 92)
		, Store Zero (Addr 93)
		, Store Zero (Addr 94)
		, Store Zero (Addr 95)
		, Store Zero (Addr 96)
		, Const 2 RegD                  {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 2) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 0 RegD                  {-i = -i-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 5 RegD                  {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 6 RegD                  {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 6) RegA
		, Push RegA
		, Load (Addr 0) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 6 RegD                  {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 3 RegD                  {-q = i/10-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 4 RegD                  {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 0) RegA
		, Push RegA
		, Load (Addr 3) RegA
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
		, Load (Addr 5) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 4) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 5 RegD                  {-pos = pos-1-}
		, Push RegD
		, Load (Addr 5) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 0 RegD                  {-i = q-}
		, Push RegD
		, Load (Addr 3) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 2) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 1 RegD                  {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 5) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 5 RegD                  {-pos = pos-1-}
		, Push RegD
		, Load (Addr 5) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 7 RegD                  {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 5) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 7) RegA            {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 1) RegE            {-For loop body-}
		, Load (Addr 7) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 7 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 7) RegA
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
		, Load (Addr 59) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 97)
		, Const 34 RegA
		, Store RegA (Addr 98)
		, Const 37 RegA
		, Store RegA (Addr 99)
		, Const 21 RegA
		, Store RegA (Addr 100)
		, Const 97 RegA                 {-Base addr for (anon) array 232042179-}
		, Store RegA (Addr 60)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 101)
		, Const 17 RegA
		, Store RegA (Addr 102)
		, Const 28 RegA
		, Store RegA (Addr 103)
		, Const 35 RegA
		, Store RegA (Addr 104)
		, Const 21 RegA
		, Store RegA (Addr 105)
		, Const 101 RegA                {-Base addr for (anon) array 993208674-}
		, Store RegA (Addr 61)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 20) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 106)
		, Const 34 RegA
		, Store RegA (Addr 107)
		, Const 37 RegA
		, Store RegA (Addr 108)
		, Const 21 RegA
		, Store RegA (Addr 109)
		, Const 106 RegA                {-Base addr for (anon) array 154146418-}
		, Store RegA (Addr 21)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 110)
		, Const 17 RegA
		, Store RegA (Addr 111)
		, Const 28 RegA
		, Store RegA (Addr 112)
		, Const 35 RegA
		, Store RegA (Addr 113)
		, Const 21 RegA
		, Store RegA (Addr 114)
		, Const 110 RegA                {-Base addr for (anon) array 1664093259-}
		, Store RegA (Addr 22)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 115)
		, Const 115 RegA                {-Base addr for (anon) array 1719727892-}
		, Store RegA (Addr 23)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 12) RegA           {-Function boolToInt-}
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
		, Load (Addr 24) RegA           {-Function intToBool-}
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
		, Const 33 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 33) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 32) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 30) RegE           {-For loop body-}
		, Load (Addr 33) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 31) RegE
		, Load (Addr 33) RegA
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
		, Const 33 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 33) RegA
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
		, Const 16 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 16) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 15) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 13) RegE           {-For loop body-}
		, Load (Addr 16) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 14) RegE
		, Load (Addr 16) RegA
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
		, Const 16 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 16) RegA
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
		, Const 64 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 64) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 63) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 62) RegE           {-For loop body-}
		, Load (Addr 64) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 0)
		, Const 1029 RegA               {-Return addr-}
		, Push RegA
		, Const 65 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 595)                {-Jump to function printInt-}
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
		, Const 64 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 64) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 62) RegE
		, Load (Addr 63) RegA
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
		, Const 48 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 48) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 47) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 46) RegE           {-For loop body-}
		, Load (Addr 48) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 59)
		, Const 1092 RegA               {-Return addr-}
		, Push RegA
		, Const 49 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 784)                {-Jump to function printBool-}
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
		, Const 48 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 48) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 46) RegE
		, Load (Addr 47) RegA
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
		, Const 51 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 52 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 50) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 51) RegA
		, Push RegA
		, Load (Addr 52) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 53 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 49) RegE
		, Load (Addr 52) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 49 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 52) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 49) RegE
		, Load (Addr 51) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 49 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 51) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 53) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 51 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 51) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 52 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 52) RegA
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
		, Const 56 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 57 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 55) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 56) RegA
		, Push RegA
		, Load (Addr 57) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 58 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 54) RegE
		, Load (Addr 57) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 54 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 57) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 54) RegE
		, Load (Addr 56) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 54 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 56) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 58) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 56 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 56) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 57 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 57) RegA
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
