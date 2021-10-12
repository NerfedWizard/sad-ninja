
#include<stdio.h> 
#include<stdlib.h> 
/* Author Loel Nelson */
/* Link list node */
struct Node 
{ 
    char data; 
    struct Node* next; 
}; 
  
// Function to insert a given node in a sorted linked list 
void sortedInsert(struct Node**, struct Node*); 
  
// function to sort a singly linked list using insertion sort 
void insertionSort(struct Node **head_ref) 
{ 
    // Initialize sorted linked list 
    struct Node *sorted = NULL; 
  
    // Traverse the given linked list and insert every 
    // node to sorted 
    struct Node *current = *head_ref; 
    while (current != NULL) 
    { 
        // Store next for next iteration 
        struct Node *next = current->next; 
  
        // insert current in sorted linked list 
        sortedInsert(&sorted, current); 
  
        // Update current 
        current = next; 
    } 
  
    // Update head_ref to point to sorted linked list 
    *head_ref = sorted; 
} 
  
/* function to insert a new_node in a list. Note that this 
  function expects a pointer to head_ref as this can modify the 
  head of the input linked list (similar to push())*/
void sortedInsert(struct Node** head_ref, struct Node* new_node) 
{ 
    struct Node* current; 
    /* Special case for the head end */
    if (*head_ref == NULL || (*head_ref)->data >= new_node->data) 
    { 
        new_node->next = *head_ref; 
        *head_ref = new_node; 
    } 
    else
    { 
        /* Locate the node before the point of insertion */
        current = *head_ref; 
        while (current->next!=NULL && 
               current->next->data < new_node->data) 
        { 
            current = current->next; 
        } 
        new_node->next = current->next; 
        current->next = new_node; 
    } 
} 
/* BELOW FUNCTIONS ARE JUST UTILITY TO TEST sortedInsert */
  
/* Function to print linked list */
void printList(struct Node *head) 
{ 
    struct Node *temp = head; 
    while(temp != NULL) 
    { 
        printf("%c  ", temp->data); 
        temp = temp->next; 
    } 
} 
/* A utility function to insert a node at the beginning of linked list */
void push(struct Node** head_ref, char new_data) 
{ 
    /* allocate node */
    struct Node* new_node = malloc(sizeof(new_node)); 
  
    /* put in the data  */
    new_node->data  = new_data; 
  
    /* link the old list off the new node */
    new_node->next = (*head_ref); 
  
    /* move the head to point to the new node */
    (*head_ref)    = new_node; 
} 
int empty(struct Node *head)
{
	struct Node *temp = head;
	if(temp == NULL){
		printf("The list is emtpy");
		return 0;
	
	}
	if(temp != NULL){
	
		return 1;
	}
	
}
//// Driver program to test above functions 
int main() 
{ 
	char * temp;
	char ch [2];
	int option;
    struct Node *a = NULL; 
    while(1){
	
     	printf("\nChoose an option: (1)Insert-(2)Print-(3)Remove/Clear-(4)Exit: ");
     if (scanf("%d", &option) != 1) {
        printf(" *Error: Invalid input. Try again.\n");
        scanf("%s", &temp); /*clear input buffer */
        continue;
     }   
     switch(option){
     	
     	case 1:
     		printf("\nEnter character into list: ");
     		scanf("%s", ch);
     		push(&a, *ch);
     		printf("\nPress any key to continue....\n");
     		getch();
     		break;
     	case 2:
     		insertionSort(&a);
			printList(a);
		 break;
		 	
     	case 3:
		 free(a);
		 a = NULL;
		 printf("\nThe list has been cleared.....\n");
		 
		 break;	
 		case 4:
			if(empty(a) == 1){
				printf("Empty the memory first");
			}
			else{
				exit(0);
			}
 			
			 
 			
	 }
}  
    return 0; 
} 