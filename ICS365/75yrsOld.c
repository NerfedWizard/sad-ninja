	#include <stdio.h>
	#include <stdlib.h>
	
	/* run this program using the console pauser or add your own getch, system("pause") or input loop */
	
	int main() {
		#define AGE 75
		int yrborn;
		printf("Calculator for giving the Year you turn 75 \n");
		printf("\nWhat year were you born? ");
		scanf("%d", &yrborn);
	
		mathForYear(yrborn);		
	
		return 0;
	}
	int mathForYear(int yrborn){
		int yearSeventyFive;
	
			if((2019 - yrborn)>= AGE){
				printf("\nYou are Old as Dirt already!!! \n");
				return 0;
		}
		
		
		yearSeventyFive = (yrborn + AGE);	
		printf("\nYou will be 75 yrs old in the year:  %d ",yearSeventyFive);
	}
