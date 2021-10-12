## Loel Nelson
def main():
    try:
        mylist = eval(input("Enter:"))
        final = findMissing(mylist)
        print(final)
    except NameError:
        print("Must be an Integer")
        return main()
    except TypeError:
        print("Must Use [] for list")
        return main()
def findMissing(mylist):
    element = 0
    missing = search(mylist,element)
    return missing

def search(List,element):
    missing = []
    while element <= 9:
        if element in List:
            while element in List and element <=9:
                element +=1
        elif element not in List:
            while element not in List and element <=9:
                missing+= [element]
                element+=1
        elif element == 10:
            element = 0
    return missing
    
main()
    
