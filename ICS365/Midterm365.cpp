#include<stdio.h>
#include <stdio.h>


int main(){
	
	int newCube,entered;
	
	printf("Enter in an integer to be cubed:\n");
	scanf("%d",&entered);
		
	doingTheMath(entered);

	return 0;
}
int doingTheMath(int entered){
	int number;
	number = (entered * entered * entered);
	
	
}
