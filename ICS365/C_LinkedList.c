#include<stdio.h>
#include<string.h>
#include<stdlib.h>

// structure to create a new node
struct character{
    char lower_char; // char variable
    struct character *next; // next pointer
};

// set head to null
struct character *head=NULL;

// function to insert a character in the linked list
void insert_char(char *c)
    {
    // dynamically create a newnode for the insertion   
    struct character *new_node=(struct character*)malloc(sizeof(struct character));
    new_node->lower_char=*c; // inserting the character

    // initially if head is null
    if(head==NULL){
        head=new_node;
    }

    // if already linked list has elements
    else{
        struct character *temp =head; // temp variable to store head
        struct character *prev=head; // prev stores head

        // temp stores the pointer the value > char ascii value
        while(temp!=NULL)
        {
            if(temp->lower_char > *c)
            {
                break;
            }
            else
            {
                prev=temp;
                temp=temp->next;
            }
        }

        // if temp is head
        if(temp==head){
            new_node->next=head;
            head=new_node;
        }
        // if temp is not head
        else{
            prev->next=new_node;
            new_node->next=temp;
        }
    }
}

void print_list()
{
    // set head to a temp variable
    struct character *temp=head;
    if(head==NULL){
        printf("Emptylist\n");
    }
    while(temp!=NULL){
        printf("%c ",temp->lower_char);
        temp=temp->next;
    }
    printf("\n");
}
void remove_list(){
    // make head as null to remove the linked list
    printf("Linked list is now empty\n");
    head=NULL;
    free(head); // frees the head
}

int main()
{
    // let store the all possible inputs into char arrays using pointer   
    char *insert="insert";
    char *remove="remove";
    char *print="print";
    char *exit="exit";
    printf("insert, print, remove, exit\n");

    // infinite loop until the user wants to quit
    while(1)
    {
        char *str; // initialize the char pointer
        printf("> ");
        scanf("%s",str);
        // if the input is insert
        if(!strcmp(str,insert))
        {
            char c;
            printf("enter a char: \n");
            getchar();
            scanf("%c",&c); // take user input of the character
            insert_char(&c);

        }
       
        // if the input is print
        else if(!strcmp(str,print))
        {
            print_list();

        }
        // if the input is remove
        else if(!strcmp(str,remove))
        {
            remove_list();

        }

        // if the input is exit
        else if(!strcmp(str,exit))
        {
            if(head!=NULL){
                printf("First call the remove \n");
            }
            else{
                break;
            }

        }
        else{
            printf("Error: unknown request'%s'\n",str);
        }
    }
return 0;
}
