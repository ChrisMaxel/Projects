/**
 * Calculator.cpp: a program that contains a calculator class and a main
  * function to use and test the function.
  *
  * For each function in the class declaration, first change the ';' to {}, then
  * fill in the body of the function to make the class work.
  */

#include "Calculator.h"

using namespace std;

/**
 *  This class represents a simple calculator that contains a single
 *  integer accumulator.  It provides basic methods to set/get/clear the
 *  accumulator's value, as well as to add, subtract, multiply, and
 *  divide.  All operations leave their results in the accumulator for
 *  further manipulation.
 *
 *  @author  Stephen Edwards, modified by Stefan Brandle
 *  @version 2009.09.10
 */

/**
 * Initializes a new Calculator object with zero in the accumulator.
 */
Calculator::Calculator() {
    value = 0;
}

/**
 * Does any final cleanup for the object (e.g., returning memory to the memory manager) before object is destroyed.
 */
Calculator::~Calculator() {
    // Nothing to be done here. This is just a placeholder.
}

/**
 * Retrieve the current value stored in the accumulator.
 * @return the accumulator's value
 */
// write the getValue function below
int Calculator::getValue() const {
	return value;
}

/**
 * Sets the value of the accumulator to a new value.
 * @param val the new accumulator value
 */
// write the setValue function below
void Calculator::setValue(int newVal){
	value = newVal;
}

/**
 * Clears the accumulator by setting its value to zero.
 */
// write the clear function below
void Calculator::clear(){
	value = 0;
}

/**
 * Update the accumulator by adding a number to
 * @param val the amount to add
 */
// write the add function below
void Calculator::add(int number){
	value += number;
}

/**
 * Update the accumulator by subtracting a number from it.
 * @param val the amount to subtract
 */
// write the subtract function below Calculator::subtract(number){
void Calculator::subtract(int number){
	value -= number;
}

/**
 * Update the accumulator by multiplying it by a specified amount.
 * @param val the amount to multiply by
 */
// write the multiplyBy function below
void Calculator::multiplyBy(int number){
	value *= number;
}

/**
 * Update the accumulator by dividing it by a specified amount.
 * @param val the amount to divide by
 */
// write the divideBy function below
void Calculator::divideBy(int number){
	value /= number;
}

