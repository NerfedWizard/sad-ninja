#include<stdio.h>



int main(){
	
	int newCube,entered;
	
	printf("Enter in an integer to be cubed:\n");
	scanf("%d",&entered);
		
    newCube = doingTheMath(entered);
	printf("The integer %d",entered);
	printf(" cubed is %d", newCube);
	return 0;
}
int doingTheMath(int entered){
	int number;
	number = (entered * entered * entered);
	
	return number;
}
