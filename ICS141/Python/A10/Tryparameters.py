def main():
#Does First
    mylist=eval(input("enter"))

#Brings mylist over to findmissing
    findmissing(mylist)

def findmissing(mylist):

#Element doesnt get used
    element=0
    missing=1

#mylist is pushed over to search in first spot and missing in 2nd
    search(mylist,missing)

#Does this last
    print(mylist)

def search(element,missing):
#mylist is now called element for this function

#Does this second
    print(element)

#Does this 3rd
    print(missing)
main()
