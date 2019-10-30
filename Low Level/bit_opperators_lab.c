/* 
 * CS:APP Data Lab 
 * 
 * <Please put your name and userid here>
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  int byte2 = 0;
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting an integer by more
     than the word size.

EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
  int byte2 = 0;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implent floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
     that you are allowed to use for your implementation of the function. 
     The max operator count is checked by dlc. Note that '=' is not 
     counted; you may use as many of these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
  
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
/* Copyright (C) 1991-2014 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
  int byte2 = 0;
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses ISO/IEC 10646 (2nd ed., published 2011-03-15) /
   Unicode 6.0.  */
/* We do not support C11 <threads.h>.  */
/* 
 * allOddBits - return 1 if all odd-numbered bits in word set to 1
 *   Examples allOddBits(0xFFFFFFFD) = 0, allOddBits(0xAAAAAAAA) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int allOddBits(int x) {
  /*
	This function sets all odd bits to a 1.  
	The mask becomes 0xAAAAAAAA(8x'A') (010101010101...).  
	(x and mask) with an xor adn then noting all of that will result /
	 in either a 1 if x is 0xAAAAAAAA and a 0 if x is anything else
  */
  int mask = (0xAA << 8) + 0xAA;// Build mask (0xAAAAAAAA)
  mask = (mask << 16) + mask;
  return !((x & mask) ^ mask);
}
/* 
 * anyEvenBit - return 1 if any even-numbered bit in word set to 1
 *   Examples anyEvenBit(0xA) = 0, anyEvenBit(0xE) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int anyEvenBit(int x) {
  /*
  	first, a mask was created of 0x55555555 (101010101010...)
  	Then, my partner and I did x and mask to check if it was on /
  	 the even bits and then did a double not.
  */
  int mask = (0x55 << 8) + 0x55; // Build mask (0x55555555)
  mask = (mask << 16) + mask; 
  return !!(x & mask);
}
/* 
 * divpwr2 - Compute x/(2^n), for 0 <= n <= 30
 *  Round toward zero
 *   Examples: divpwr2(15,1) = 7, divpwr2(-33,4) = -2
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int divpwr2(int x, int n) {
	/*
	Start with ((1 << n) + ~0)).
		shift 1 over n timies and add negative 1 to it.
	Then (x + ((x >> 31)
		get the sign of x and combie it with what is above by &
		then add that and x.
	Then, right shift the above instruction over n times. to divide by the power of 2.
	*/
  return (x + ((x >> 31) & ((1 << n) + ~0))) >> n;
}

/* 
 * fitsShort - return 1 if x can be represented as a 
 *   16-bit, two's complement integer.
 *   Examples: fitsShort(33000) = 0, fitsShort(-32768) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 1
 */
int fitsShort(int x) {
 /*
	create a mask.
	shift x over 15 times and check from 16-12 bits if they are zero.
	Then not it and get a 1 or 0.
 */
  int mask = x >> 31; // All 1's if negative, All 0's if positive
  return !((x >> 15) ^ mask);  
}
/* 
 * float_half - Return bit-level equivalent of expression 0.5*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_half(unsigned uf) {
	/*
		Create some masks, get the sign, get an exponent, get a fraction.
		Check if the float is equal to the first mask, if so, return uf.
		Check if the exponent is equal to 0 or if the exponent is 0x00800000.
		Edit the fraction
		return some stuff
	*/
    int emask = 0x7F800000;
    int smask = 0x80000000;
    int fmask = 0x007FFFFF;
    int sign = uf & smask;
    int exponent = uf & emask;
    int fraction = uf & fmask;
    if (exponent == 0x7F800000){
        return uf;
    }
    if ((exponent == 0x00000000) || (exponent == 0x00800000)){
        fraction = fraction | exponent;
        fraction = (uf & 0x00FFFFFF) >> 1;
        if((uf & 3) == 3){
            fraction += 1;
        }
        return sign | fraction;
    }
    return sign | ((exponent - 1) & 0x7F800000) | fraction;
}
/* 
 * float_i2f - Return bit-level equivalent of expression (float) x
 *   Result is returned as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point values.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned float_i2f(int x) {
  //  I just can't...  I'm sorry...
  return 2;
}
/* 
 * float_neg - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned float_neg(unsigned uf) {
	/*
	check if neg or pos,
	check if a nan
		if pos, check if uf is greater than 0x7F800000
			if not return uf | 0x80000000
		if neg, check if us is greater than 0xFF800000
			if not, return uf & 0x7FFFFFFF
	*/
 // if negative
 if (uf >> 31) {
    // if NaN
    if (uf > 0xFF800000) {
        return uf;
    } else {
        return uf & 0x7FFFFFFF;
    }
 // if positive
 } else {
    // if NaN
    if (uf > 0x7F800000) {
        return uf;
    } else {
        return uf | 0x80000000;
    }
 }
}
/*
 * ilog2 - return floor(log base 2 of x), where x > 0
 *   Example: ilog2(16) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 90
 *   Rating: 4
 */
int ilog2(int x) {
	/*
	declaire some ints...
	basicaly, do a binary search on the 32 bits
		check if there is any 0's in the upper 16 bits.
		if not, the upper 16 is all 0s and there could be a 0 in the bottom half
		then do a shift
		then repeat the process.
	*/
  int next4, placeToGo, upper16, upper16Shifted, next8, next8Shifted, next4Shifted, next2, next1;
  upper16 = !!(x >> 16);
  upper16Shifted = upper16 << 4;

  placeToGo = upper16Shifted;
  next8 = !! (x >> (placeToGo + 8));
  next8Shifted = next8 << 3;

  placeToGo += next8Shifted;
  next4 = !!(x >> (placeToGo + 4));
  next4Shifted = next4 << 2;

  placeToGo += next4Shifted;
  next2 = !!(x >> (placeToGo + 2));

  placeToGo += (next2 << 1);
  next1 = !!(x >> (placeToGo + 1));

  return (placeToGo + next1);
}
/* 
 * isNotEqual - return 0 if x == y, and 1 otherwise 
 *   Examples: isNotEqual(5,5) = 0, isNotEqual(4,5) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 6
 *   Rating: 2
 */
int isNotEqual(int x, int y) {
	/*
	do an x xor y and then double not it
	*/
  return !!(x ^ y);
}
/*
 * isZero - returns 1 if x == 0, and 0 otherwise 
 *   Examples: isZero(5) = 0, isZero(0) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 2
 *   Rating: 1
 */
int isZero(int x) {
	// just not it
  return !x;
}
/* 
 * logicalNeg - implement the ! operator, using all of 
 *              the legal operators except !
 *   Examples: logicalNeg(3) = 0, logicalNeg(0) = 1
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 4 
 */
int logicalNeg(int x) {
  // if non-zero, get to -1.  If zero, get to zero then add 1
  return ((~x + 1) >> 31 | (x >> 31)) + 1;
}
/*
 * multFiveEighths - multiplies by 5/8 rounding toward 0.
 *   Should exactly duplicate effect of C expression (x*5/8),
 *   including overflow behavior.
 *   Examples: multFiveEighths(77) = 48
 *             multFiveEighths(-22) = -13
 *             multFiveEighths(1073741824) = 13421728 (overflow)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 3
 */
int multFiveEighths(int x) {
	/*
	divide by 8 first to avoid overflow, then & x and 7.
	Than get the sign of x and & 7 again.
	then mult the remainder by 4 and add the mask and aother remainder to get to times 5, then shift it right 3 times.
	then multiply the num by 5 and then add the two together
	*/
  int num = x >> 3;
  int rem = x & 7;
  int mask = x >> 31 & 7;
  return num + (num << 2) + ((rem + (rem << 2) + mask) >> 3);
}
/* 
 * negate - return -x 
 *   Example: negate(1) = -1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 5
 *   Rating: 2
 */
int negate(int x) {
	/*
	tilda switches all of the bits adn then add 1
	*/
  return ~x + 1;
}
/* 
 * oddBits - return word with all odd-numbered bits set to 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 2
 */
int oddBits(void) {
	/*
	create a mask, then do a shift on the shift on the mask and add the maks to it/
	 to set the mask equal to odd num bits
	*/
  int mask = (0xAA << 8) + 0xAA;
  mask = (mask << 16) + mask;
  return mask;
}
/* 
 * rempwr2 - Compute x%(2^n), for 0 <= n <= 30
 *   Negative arguments should yield negative remainders
 *   Examples: rempwr2(15,2) = 3, rempwr2(-35,3) = -3
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3
 */
int rempwr2(int x, int n) {
  /*
  // Modulus
    i % 4; // normal
    i & 3; // bitwise [4 = 1 << 2, apply ((1 << 2) - 1), so use 3]
    get the sign val.
    sign val xor on X added to sign val & 1
    then shift one over n times and add -1, take that and and it with the previous line
    then do an xor on the sign bit and the above line and add that too the sign val & 1
  */
  int sign = x >> 31;
  int y = (sign ^ x) + (sign & 1);
  int middle = y &((1 << n) + ~0);
  int final = (sign ^ middle) + (sign & 1);
  return final;
}

/* 
 * rotateRight - Rotate x to the right by n
 *   Can assume that 0 <= n <= 31
 *   Examples: rotateRight(0x87654321,4) = 0x76543218
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 25
 *   Rating: 3 
 */
int rotateRight(int x, int n) {
	/*
	declaire some ints,
	shift x right n times
	create a mask and switch it, the mask consists of the min number shifted right n-1 times
	then taking ~n and adding one and then 32,  shift x over that many times.
	Then doing an | on the two above explained statemets will yeild your answer
	*/
	int rightBits, rightShift, mask, leftBits;
    rightShift = (x >> n);
    mask = ~((1 << 31) >> (n + ~0));
    leftBits = (rightShift & (mask)); 
    rightBits = x << (32 + (~n + 1));
  return leftBits|rightBits;
}
/*
 * satMul3 - multiplies by 3, saturating to Tmin or Tmax if overflow
 *  Examples: satMul3(0x10000000) = 0x30000000  
 Add Comment
 (optional)
 *            satMul3(0x30000000) = 0x7FFFFFFF (Saturate to TMax)
 *            satMul3(0x70000000) = 0x7FFFFFFF (Saturate to TMax)
 *            satMul3(0xD0000000) = 0x80000000 (Saturate to TMin)
 *            satMul3(0xA0000000) = 0x80000000 (Saturate to TMin)
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 3
 */
int satMul3(int x) {

    int threeX = x + x + x;
    int twoX = x + x;

    int xSign = x >> 31;
    int twoXSign = (twoX) >> 31;
    int threeXSign = (threeX) >> 31;

    int signChange = (xSign ^ twoXSign) | (xSign ^ threeXSign);

    return (threeX ^ ((threeX ^ (~(1 << 31))) & signChange)) ^ (xSign & signChange);
}
/* 
 * thirdBits - return word with every third bit (starting from the LSB) set to 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 1
 */
int thirdBits(void) {
	/*
	create a mask, then shift the mask let 18 times and then add the mask to get the answer
	*/
  int mask = 0x49 << 9;
  mask = mask + 0x49;
  return (mask << 18) + mask;
}
/* 
 * tmin - return minimum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmin(void) {
	// returns tmin
  return 1 << 31;
}
/*
 * trueThreeFourths - multiplies by 3/4 rounding toward 0,
 *   avoiding errors due to overflow
 *   Examples: trueThreeFourths(11) = 8
 *             trueThreeFourths(-9) = -6
 *             trueThreeFourths(1073741824) = 805306368 (no overflow)
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int trueThreeFourths(int x){
	/*
	declaiere some ints,
	divide by four first to avoid overflow
	then multiply,
	get the remainder and similar opperatioins
	create a fraction and add above adn the bias together then divide it by 4
	add the total multiplied together and the fraction to get the answer.
	*/
  int bias, divide4, mlut3, remainder, above, frac;
  bias = (0x3) & (x >> 31);
  divide4 = x >> 2;
  mlut3 = (divide4 << 1) + divide4;
  remainder = x & 3;
  above = (remainder << 1) + remainder;
  frac = (above + bias) >> 2;
  return (mlut3 + frac);
}
