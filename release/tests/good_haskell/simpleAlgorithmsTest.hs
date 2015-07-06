{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1275 RegA               {-Initial Return Addr-}
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
		, Const 19 RegD                 {-Declaration of result(=0); Function main-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 23 RegA                 {-Return addr-}
		, Push RegA
		, Const 20 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 57)                 {-Jump to function testGcd-}
		, Load (Addr 20) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 19 RegD                 {-result = 1-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegA                 {-Return addr-}
		, Push RegA
		, Const 21 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 174)                {-Jump to function testFac-}
		, Load (Addr 21) RegA
		, Const 1 RegB
		, Compute Xor RegB RegA RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 6)
		, Const 19 RegD                 {-result = 2-}
		, Push RegD
		, Const 2 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 19) RegA
		, Out RegA
		, Load (Addr 19) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 6 RegA                  {-Function testGcd-}
		, Store RegA (Addr 12)
		, Const 9 RegA
		, Store RegA (Addr 13)
		, Const 66 RegA                 {-Return addr-}
		, Push RegA
		, Const 4 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 4) RegA
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
		, Store RegA (Addr 12)
		, Const 20 RegA
		, Store RegA (Addr 13)
		, Const 90 RegA                 {-Return addr-}
		, Push RegA
		, Const 4 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 4) RegA
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
		, Store RegA (Addr 12)
		, Const 5 RegA
		, Store RegA (Addr 13)
		, Const 114 RegA                {-Return addr-}
		, Push RegA
		, Const 4 RegA                  {-Result addr-}
		, Push RegA
		, Jump (Abs 136)                {-Jump to function gcd-}
		, Load (Addr 4) RegA
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
		, Const 14 RegD                 {-Declaration of t(=0); Function gcd-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 13) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-y NEq 0-}
		, Branch RegA (Rel 21)
		, Const 14 RegD                 {-t = y-}
		, Push RegD
		, Load (Addr 13) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 13 RegD                 {-y = x%y-}
		, Push RegD
		, Load (Addr 12) RegA
		, Push RegA
		, Load (Addr 13) RegA
		, Pop RegB
		, Compute Mod RegB RegA RegA    {-x Mod y-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 12 RegD                 {-x = t-}
		, Push RegD
		, Load (Addr 14) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-25))
		, Load (Addr 12) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 3 RegA                  {-Function testFac-}
		, Store RegA (Addr 22)
		, Const 181 RegA                {-Return addr-}
		, Push RegA
		, Const 52 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 52) RegA
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
		, Store RegA (Addr 22)
		, Const 203 RegA                {-Return addr-}
		, Push RegA
		, Const 52 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 52) RegA
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
		, Store RegA (Addr 22)
		, Const 225 RegA                {-Return addr-}
		, Push RegA
		, Const 52 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 52) RegA
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
		, Store RegA (Addr 22)
		, Const 247 RegA                {-Return addr-}
		, Push RegA
		, Const 52 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 52) RegA
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
		, Store RegA (Addr 22)
		, Const 269 RegA                {-Return addr-}
		, Push RegA
		, Const 52 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 291)                {-Jump to function fac-}
		, Load (Addr 52) RegA
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
		, Const 23 RegD                 {-Declaration of res(=1); Function fac-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 24 RegD                 {-Declaration of i(=1); For loop declaration-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 24) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 22) RegA
		, Pop RegB
		, Compute Gt RegB RegA RegA     {-i LtE n-}
		, Branch RegA (Rel 20)          {-Break from for loop-}
		, Const 23 RegD                 {-res = res*i; For loop body-}
		, Push RegD
		, Load (Addr 23) RegA
		, Push RegA
		, Load (Addr 24) RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-res Mul i-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 24 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 24) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-24))             {-Back to for loop-}
		, Load (Addr 23) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 38) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 38) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 38) RegA
		, Store RegA (Addr 65)
		, Const 65 RegA                 {-Base addr for (anon) array 1025786379-}
		, Store RegA (Addr 39)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 66 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 40)
		, Store Zero (Addr 66)
		, Store Zero (Addr 67)
		, Store Zero (Addr 68)
		, Store Zero (Addr 69)
		, Store Zero (Addr 70)
		, Store Zero (Addr 71)
		, Store Zero (Addr 72)
		, Store Zero (Addr 73)
		, Store Zero (Addr 74)
		, Store Zero (Addr 75)
		, Const 41 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 41) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 38 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 43 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 44 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 45 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 45) RegA
		, Push RegA
		, Load (Addr 38) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 45 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 43 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Load (Addr 42) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 40 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 44) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 43) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 44 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 44) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 38 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 42) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 41) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 40 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 44) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 40) RegA
		, Store RegA (Addr 0)
		, Const 492 RegA                {-Return addr-}
		, Push RegA
		, Const 46 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 499)                {-Jump to function shiftArray-}
		, Load (Addr 46) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 76 RegA                 {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 1)
		, Store Zero (Addr 76)
		, Store Zero (Addr 77)
		, Store Zero (Addr 78)
		, Store Zero (Addr 79)
		, Store Zero (Addr 80)
		, Store Zero (Addr 81)
		, Store Zero (Addr 82)
		, Store Zero (Addr 83)
		, Store Zero (Addr 84)
		, Store Zero (Addr 85)
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
		, Load (Addr 25) RegA           {-Function printInt-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 25) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 8)
		, Load (Addr 25) RegA
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Const 86 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 26)
		, Store Zero (Addr 86)
		, Store Zero (Addr 87)
		, Store Zero (Addr 88)
		, Store Zero (Addr 89)
		, Store Zero (Addr 90)
		, Store Zero (Addr 91)
		, Store Zero (Addr 92)
		, Store Zero (Addr 93)
		, Store Zero (Addr 94)
		, Store Zero (Addr 95)
		, Const 27 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 27) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 25 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 31 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 31) RegA
		, Push RegA
		, Load (Addr 25) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 31 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 28 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 25) RegA
		, Push RegA
		, Load (Addr 28) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 30) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 29) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 30) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 25 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 28) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 27) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 21)
		, Const 26 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 30) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 30 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 30) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 32 RegD                 {-Declaration of i(=pos); For loop declaration-}
		, Push RegD
		, Load (Addr 30) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 32) RegA           {-For loop condition-}
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 16)          {-Break from for loop-}
		, Load (Addr 26) RegE           {-For loop body-}
		, Load (Addr 32) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Out RegA
		, Const 32 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 32) RegA
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
		, Store RegA (Addr 96)
		, Const 34 RegA
		, Store RegA (Addr 97)
		, Const 37 RegA
		, Store RegA (Addr 98)
		, Const 21 RegA
		, Store RegA (Addr 99)
		, Const 96 RegA                 {-Base addr for (anon) array 583351682-}
		, Store RegA (Addr 16)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 100)
		, Const 17 RegA
		, Store RegA (Addr 101)
		, Const 28 RegA
		, Store RegA (Addr 102)
		, Const 35 RegA
		, Store RegA (Addr 103)
		, Const 21 RegA
		, Store RegA (Addr 104)
		, Const 100 RegA                {-Base addr for (anon) array 951317000-}
		, Store RegA (Addr 17)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 53) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 105)
		, Const 34 RegA
		, Store RegA (Addr 106)
		, Const 37 RegA
		, Store RegA (Addr 107)
		, Const 21 RegA
		, Store RegA (Addr 108)
		, Const 105 RegA                {-Base addr for (anon) array 843410864-}
		, Store RegA (Addr 54)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 109)
		, Const 17 RegA
		, Store RegA (Addr 110)
		, Const 28 RegA
		, Store RegA (Addr 111)
		, Const 35 RegA
		, Store RegA (Addr 112)
		, Const 21 RegA
		, Store RegA (Addr 113)
		, Const 109 RegA                {-Base addr for (anon) array 1306067302-}
		, Store RegA (Addr 55)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 114)
		, Const 114 RegA                {-Base addr for (anon) array 825605925-}
		, Store RegA (Addr 56)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 60) RegA           {-Function boolToInt-}
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
		, Load (Addr 18) RegA           {-Function intToBool-}
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
		, Const 64 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 64) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 63) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 61) RegE           {-For loop body-}
		, Load (Addr 64) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 62) RegE
		, Load (Addr 64) RegA
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
		, Const 64 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 64) RegA
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
		, Const 8 RegD                  {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 8) RegA            {-For loop condition-}
		, Push RegA
		, Load (Addr 7) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 5) RegE            {-For loop body-}
		, Load (Addr 8) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 6) RegE
		, Load (Addr 8) RegA
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
		, Const 8 RegD                  {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 8) RegA
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
		, Const 59 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 59) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 58) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 57) RegE           {-For loop body-}
		, Load (Addr 59) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 25)
		, Const 1029 RegA               {-Return addr-}
		, Push RegA
		, Const 60 RegA                 {-Result addr-}
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
		, Const 59 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 59) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 57) RegE
		, Load (Addr 58) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 25)
		, Const 1063 RegA               {-Return addr-}
		, Push RegA
		, Const 60 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 595)                {-Jump to function printInt-}
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
		, Const 1097 RegA               {-Return addr-}
		, Push RegA
		, Const 12 RegA                 {-Result addr-}
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
		, Const 49 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 50 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 48) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 49) RegA
		, Push RegA
		, Load (Addr 50) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 51 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 47) RegE
		, Load (Addr 50) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 50) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 47) RegE
		, Load (Addr 49) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 47 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 49) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 51) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 49 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 49) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 50 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 50) RegA
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
		, Const 35 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 34) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 35) RegA
		, Push RegA
		, Load (Addr 36) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 37 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 33) RegE
		, Load (Addr 36) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 36) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 33) RegE
		, Load (Addr 35) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 35) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 37) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 35 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 35) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 36 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 36) RegA
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