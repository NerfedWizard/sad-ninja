#include <stdio.h>

int i = 100; 
void g(void) 
{ 
 printf("%d\n", i); 
 i++; 
 printf("%d\n", i); 
} 

int f(void) 
{ 
  int i=200; 
  printf("%d\n", i); 
  g(); 
  printf("%d\n", i); 
  return(i); 
} 

int main(int argc,char **argv) 
{ 
  printf("%d\n", f()); 
  printf("%d\n", i);
}



