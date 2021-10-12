def search (mylist, missing):
    print(mylist)
    print(missing)
    element = 0
    while element <= 10:
        if element in mylist:
            element += 1
        elif element not in mylist:
            missing = element
            element+=1
            
    return findMissing(missing) 
        
def findMissing(mylist):
    missing = []
    search(mylist, missing)
    print(missing)
def main():
    try:
        print("Press enter to quit")
        mylist = eval(input("Enter any list of 0 or digits in the form [digit, digit,...]"))
        findMissing(mylist)




    except SyntaxError:
        return print("Thank You")


main()
