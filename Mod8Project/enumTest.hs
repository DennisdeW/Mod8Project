{-# LANGUAGE RecordWildCards #-}
import System
import Sprockell
import TypesEtc

prog :: [Instruction]
prog = [
		  Const 1009 RegA               {-Initial Return Addr-}
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
		, Const 23 RegD                 {-Declaration of t(=Days.TUESDAY); Function main-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 24 RegD                 {-Declaration of w(=Days.WEDNESDAY)-}
		, Push RegD
		, Const 2 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 59 RegA                 {-Base addr for array weekend-}
		, Store RegA (Addr 25)
		, Const 5 RegA
		, Store RegA (Addr 59)
		, Const 6 RegA
		, Store RegA (Addr 60)
		, Const 26 RegD                 {-Declaration of dayPtr(=&t)-}
		, Push RegD
		, Load (Addr 23) RegA
		, Const 23 RegA                 {-Create pointer to t-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 26 RegD                 {-*dayPtr = Days.THURSDAY-}
		, Load (Deref RegD) RegD
		, Push RegD
		, Const 3 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 23) RegA
		, Push RegA
		, Const 3 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-t NEq Days.THURSDAY-}
		, Branch RegA (Rel 10)
		, Load (Addr 25) RegD           {-weekend[1]-}
		, Const 1 RegA
		, Compute Add RegA RegD RegD
		, Load (Deref RegD) RegA        {-weekend[1]-}
		, Push RegA
		, Const 6 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-weekend[1] NEq Days.SUNDAY-}
		, Branch RegA (Rel 4)
		, Jump (Rel 7)
		, Const 1 RegA
		, Out RegA
		, Jump (Rel 6)
		, Const 2 RegA
		, Out RegA
		, Jump (Rel 3)
		, Const 0 RegA
		, Out RegA
		, Const 0 RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 27) RegA           {-Function intToStr-}
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i GtE 0-}
		, Push RegA
		, Load (Addr 27) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 10-}
		, Pop RegB
		, Compute And RegB RegA RegA    {-(i>=0) And (i<10)-}
		, Branch RegA (Rel 2)
		, Jump (Rel 11)
		, Load (Addr 27) RegA
		, Store RegA (Addr 61)
		, Const 61 RegA                 {-Base addr for (anon) array 1263927050-}
		, Store RegA (Addr 28)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 62 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 29)
		, Store Zero (Addr 62)
		, Store Zero (Addr 63)
		, Store Zero (Addr 64)
		, Store Zero (Addr 65)
		, Store Zero (Addr 66)
		, Store Zero (Addr 67)
		, Store Zero (Addr 68)
		, Store Zero (Addr 69)
		, Store Zero (Addr 70)
		, Store Zero (Addr 71)
		, Const 30 RegD                 {-Declaration of neg(=i<0)-}
		, Push RegD
		, Load (Addr 27) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 0-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 30) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 9)
		, Const 27 RegD                 {-i = -i-}
		, Push RegD
		, Load (Addr 27) RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 31 RegD                 {-Declaration of q(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 32 RegD                 {-Declaration of r(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-Declaration of pos(=9)-}
		, Push RegD
		, Const 9 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 34 RegD                 {-Declaration of first(=true)-}
		, Push RegD
		, Const 1 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 34) RegA
		, Push RegA
		, Load (Addr 27) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute NEq RegB RegA RegA    {-i NEq 0-}
		, Pop RegB
		, Compute Nor RegB RegA RegA    {-first Or (i!=0)-}
		, Branch RegA (Rel 52)
		, Const 34 RegD                 {-first = false-}
		, Push RegD
		, Push Zero
		, Pop RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 31 RegD                 {-q = i/10-}
		, Push RegD
		, Load (Addr 27) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Div RegB RegA RegA    {-i Div 10-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 32 RegD                 {-r = i-(q*10)-}
		, Push RegD
		, Load (Addr 27) RegA
		, Push RegA
		, Load (Addr 31) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute Mul RegB RegA RegA    {-q Mul 10-}
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub (q*10)-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 29 RegD                 {-res[pos] = r-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 33) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 32) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 33 RegD                 {-pos = pos-1-}
		, Push RegD
		, Load (Addr 33) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-pos Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 27 RegD                 {-i = q-}
		, Push RegD
		, Load (Addr 31) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-60))
		, Load (Addr 30) RegA
		, Branch RegA (Rel 2)
		, Jump (Rel 12)
		, Const 29 RegD                 {-res[pos] = -3-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 33) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Const 3 RegA
		, Push RegA
		, Pop RegA
		, Compute Sub Zero RegA RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 29) RegA
		, Store RegA (Addr 39)
		, Const 231 RegA                {-Return addr-}
		, Push RegA
		, Const 35 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 238)                {-Jump to function shiftArray-}
		, Load (Addr 35) RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 72 RegA                 {-Base addr for array res; Function shiftArray-}
		, Store RegA (Addr 40)
		, Store Zero (Addr 72)
		, Store Zero (Addr 73)
		, Store Zero (Addr 74)
		, Store Zero (Addr 75)
		, Store Zero (Addr 76)
		, Store Zero (Addr 77)
		, Store Zero (Addr 78)
		, Store Zero (Addr 79)
		, Store Zero (Addr 80)
		, Store Zero (Addr 81)
		, Const 41 RegD                 {-Declaration of i(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-Declaration of j(=0)-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 41) RegA
		, Push RegA
		, Const 9 RegA
		, Pop RegB
		, Compute Lt RegB RegA RegA     {-i Lt 9-}
		, Push RegA
		, Load (Addr 39) RegE
		, Load (Addr 41) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Const 0 RegA
		, Pop RegB
		, Compute Equal RegB RegA RegA  {-arr[i] Equal 0-}
		, Pop RegB
		, Compute Nand RegB RegA RegA   {-(i<9) And (arr[i]==0)-}
		, Branch RegA (Rel 20)
		, Const 41 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 42 RegD                 {-j = j+1-}
		, Push RegD
		, Load (Addr 42) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-j Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-35))
		, Load (Addr 41) RegA
		, Push RegA
		, Const 10 RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt 10-}
		, Branch RegA (Rel 26)
		, Const 40 RegD                 {-res[i-j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array res-}
		, Load (Addr 41) RegA
		, Push RegA
		, Load (Addr 42) RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-i Sub j-}
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 39) RegE
		, Load (Addr 41) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 41 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 41) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-30))
		, Load (Addr 40) RegA
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
		, Const 82 RegA                 {-Base addr for array res-}
		, Store RegA (Addr 1)
		, Store Zero (Addr 82)
		, Store Zero (Addr 83)
		, Store Zero (Addr 84)
		, Store Zero (Addr 85)
		, Store Zero (Addr 86)
		, Store Zero (Addr 87)
		, Store Zero (Addr 88)
		, Store Zero (Addr 89)
		, Store Zero (Addr 90)
		, Store Zero (Addr 91)
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
		, Load (Addr 52) RegA           {-Function printBool-}
		, Branch RegA (Rel 2)
		, Jump (Rel 13)
		, Const 36 RegA
		, Store RegA (Addr 92)
		, Const 34 RegA
		, Store RegA (Addr 93)
		, Const 37 RegA
		, Store RegA (Addr 94)
		, Const 21 RegA
		, Store RegA (Addr 95)
		, Const 92 RegA                 {-Base addr for (anon) array 646710031-}
		, Store RegA (Addr 53)
		, Out RegA
		, Jump (Rel 14)
		, Const 22 RegA
		, Store RegA (Addr 96)
		, Const 17 RegA
		, Store RegA (Addr 97)
		, Const 28 RegA
		, Store RegA (Addr 98)
		, Const 35 RegA
		, Store RegA (Addr 99)
		, Const 21 RegA
		, Store RegA (Addr 100)
		, Const 96 RegA                 {-Base addr for (anon) array 758201484-}
		, Store RegA (Addr 54)
		, Out RegA
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 43) RegA           {-Function boolToStr-}
		, Branch RegA (Rel 2)
		, Jump (Rel 18)
		, Const 36 RegA
		, Store RegA (Addr 101)
		, Const 34 RegA
		, Store RegA (Addr 102)
		, Const 37 RegA
		, Store RegA (Addr 103)
		, Const 21 RegA
		, Store RegA (Addr 104)
		, Const 101 RegA                {-Base addr for (anon) array 1036420486-}
		, Store RegA (Addr 44)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Jump (Rel 19)
		, Const 22 RegA
		, Store RegA (Addr 105)
		, Const 17 RegA
		, Store RegA (Addr 106)
		, Const 28 RegA
		, Store RegA (Addr 107)
		, Const 35 RegA
		, Store RegA (Addr 108)
		, Const 21 RegA
		, Store RegA (Addr 109)
		, Const 105 RegA                {-Base addr for (anon) array 2107393518-}
		, Store RegA (Addr 45)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Const 0 RegA
		, Store RegA (Addr 110)
		, Const 110 RegA                {-Base addr for (anon) array 1073862849-}
		, Store RegA (Addr 46)
		, Pop RegB                      {-Get Result addr-}
		, Pop RegC                      {-Get Return addr-}
		, Compute Lt RegB Zero RegD     {-Is Result addr valid?-}
		, Branch RegD (Rel 2)
		, Store RegA (Deref RegB)       {-Store Result-}
		, Jump (Ind RegC)               {-return-}
		, Load (Addr 9) RegA            {-Function boolToInt-}
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
		, Load (Addr 8) RegA            {-Function intToBool-}
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
		, Const 13 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 13) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 12) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 10) RegE           {-For loop body-}
		, Load (Addr 13) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 11) RegE
		, Load (Addr 13) RegA
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
		, Const 13 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 13) RegA
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
		, Const 17 RegD                 {-Declaration of i(=0); For loop declaration; Function arraysEqual-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 17) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 16) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt len-}
		, Branch RegA (Rel 32)          {-Break from for loop-}
		, Load (Addr 14) RegE           {-For loop body-}
		, Load (Addr 17) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Push RegA
		, Load (Addr 15) RegE
		, Load (Addr 17) RegA
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
		, Const 17 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 17) RegA
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
		, Const 38 RegD                 {-Declaration of i(=0); For loop declaration-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 38) RegA           {-For loop condition-}
		, Push RegA
		, Load (Addr 37) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt (len-1)-}
		, Branch RegA (Rel 31)          {-Break from for loop-}
		, Load (Addr 36) RegE           {-For loop body-}
		, Load (Addr 38) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Store RegA (Addr 0)
		, Const 768 RegA                {-Return addr-}
		, Push RegA
		, Const 39 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 334)                {-Jump to function printInt-}
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
		, Const 38 RegD                 {-i = i+1; For loop assignment-}
		, Push RegD
		, Load (Addr 38) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Jump (Rel (0-39))             {-Back to for loop-}
		, Load (Addr 36) RegE
		, Load (Addr 37) RegA
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
		, Store RegA (Addr 52)
		, Const 831 RegA                {-Return addr-}
		, Push RegA
		, Const 58 RegA                 {-Result addr-}
		, Push RegA
		, Jump (Abs 523)                {-Jump to function printBool-}
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
		, Const 20 RegD                 {-Declaration of i(=0); Function flipArray-}
		, Push RegD
		, Const 0 RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 21 RegD                 {-Declaration of j(=len-1)-}
		, Push RegD
		, Load (Addr 19) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Sub RegB RegA RegA    {-len Sub 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Load (Addr 20) RegA
		, Push RegA
		, Load (Addr 21) RegA
		, Pop RegB
		, Compute GtE RegB RegA RegA    {-i Lt j-}
		, Branch RegA (Rel 47)
		, Const 22 RegD                 {-Declaration of t(=arr[j])-}
		, Push RegD
		, Load (Addr 18) RegE
		, Load (Addr 21) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 18 RegD                 {-arr[j] = arr[i]-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 21) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 18) RegE
		, Load (Addr 20) RegA
		, Compute Add RegE RegA RegD
		, Load (Deref RegD) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 18 RegD                 {-arr[i] = t-}
		, Load (Deref RegD) RegD        {-Get base addr for array arr-}
		, Load (Addr 20) RegA
		, Compute Add RegA RegD RegD    {-Compute target addr-}
		, Push RegD
		, Load (Addr 22) RegA
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 20 RegD                 {-i = i+1-}
		, Push RegD
		, Load (Addr 20) RegA
		, Push RegA
		, Const 1 RegA
		, Pop RegB
		, Compute Add RegB RegA RegA    {-i Add 1-}
		, Pop RegD
		, Store RegA (Deref RegD)
		, Const 21 RegD                 {-j = j-1-}
		, Push RegD
		, Load (Addr 21) RegA
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
		, EndProg 
		, EndProg
       ]

main = run 1 prog
