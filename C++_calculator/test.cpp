// Your name here: Chris Maxel

#include <iostream>

// #include the Calculator header file below

#include "Calculator.h"

using namespace std;

int main() {
    // Your code here. Create a calculator object and use it to demonstrate
    // that your Calculator class code works correctly.
	Calculator chrisSwag = Calculator();
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.setValue(20);
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.clear();
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.add(5);
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.subtract(3);
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.multiplyBy(2);
	cout<<chrisSwag.getValue()<<endl;
	chrisSwag.divideBy(2);
	cout<<chrisSwag.getValue()<<endl;




	
	
    return 0;
}
