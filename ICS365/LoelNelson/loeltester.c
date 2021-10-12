#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main() {
	int seq[100];
	int counter, size, second, pivot;
	
	printf("How many integers do you want to enter: \n");
	scanf("%d",&size);
	
	printf("Enter the numbers you wish to add: \n");
	
	for (counter = 0; counter < size; counter++){
		scanf("%d", &seq[counter]);
	}
	pivot = split(size);
	second = pivot + 1;
	if(checker(second, pivot, seq) == 1){
		printf("Y \n");
	}
	else{
		printf("N \n");
	}
	return 0;
}	



int split(int size){
	int pivot;
	
	
	if(size % 2 == 0 && size != 2){
		pivot = (size/2);
	}
	if(size % 2 != 0){
			pivot = (((size + 1) /2) - 1);
	}
	else{
		return 0;		
	}
		
	return pivot;
}



int checker(int second, int pivot, int seq[]){
	int counter = 0;
 	int flag = 0;
 	if (pivot == 0){
 		pivot = second;
	 }
	for(counter = 0; counter < pivot; counter++){
		if (seq[counter] == seq[second]){
			second++;
			flag = 1;	
		}
		else{
			flag = 0;
		}
	}
	return flag;	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	int sequence [10];
	
//	printf("Type the sequence you would liked Scanned (type a letter when finished):  ");
//	for (n = 0; n < 100; n++){
//		scanf("%d[^\n]",&seq[n]);
//		size = n;
//	}
//	if(size%2 == 0){
//		
//	}
//	else{
//		
//	}
////	fgets(seq, sizeof(seq), stdin);
////	scanf("%d[^\n]",&sequence);
//////	int i = 0;
//////	 for (int i = 0; i < 10; i++){	
//////	          scanf("%d", &sequence[i]);
//////	}
////	int i=0;
////	n = (int)strol(seq,NULL,10);
//	printf("The sequence you typed: %d" , seq[3]);
//	
////        for (i = 0; i < 10; i++){
////		
////            printf( "%d",sequence) ;
////			}
//////	sequenceScanner(sequence);
//	return 0;

//int sequenceScanner(sequence){
//	if ()
//
//	sequenceChecker(sequence);
//}
//int sequenceChecker(sequence){
//		
//	
//}
