## Loel Nelson
## Takes a list of integers from the user input
## and checks if the list entered in has digits 0-9
## if a digit 0-9 is missing from users list
## the program puts the missing digit(s)
## into a list and prints the results back to user

#  Input a list of digits of any length from user
def main():
    mylist = eval(input("Enter:"))
    finalList = findMissing(mylist)
# Prints final list of the missing digit(s) from the user's list
    print(finalList)

def findMissing(List):
# The first element to check for is 0 
    element = 0
# Storing results from search in the missing list
    missing = search(List,element)
# Returns the missing digits to main to print for user
    return missing

def search(List,element):
    mylist = []
# Checking the User's list for digits 0-9
# If a digit 0-9 is missing from User's list
# Search puts missing digit into mylist
    while element <= 9:
        if element in List:
            while element in List and element <=9:
                element +=1
        elif element not in List:
            while element not in List and element <=9:
                mylist+= [element]
                element+=1
        elif element == 10:
            element = 0
# After populating mylist with missing digit(s)
# search returns results to findMissing
    return mylist
    
main()
    
