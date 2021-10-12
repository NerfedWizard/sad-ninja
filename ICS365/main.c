#include <stdio.h>


void fun(int*,int*);
int main(){
	int i = 6, j = 2;
	fun(&i, &j);
	printf("%d, %d \n", i, j);
	getchar();;
}
void fun(int*i,int*j){
	*i = *i**i;
	*j = *j**j;
}
