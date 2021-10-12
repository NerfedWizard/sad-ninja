def main():
    mylist = eval(input("Enter some numbers"))
    if len(mylist) == 0:
        print("No data in list")
    findMissing(mylist)
    print(findMissing(missing))
    

def findMissing(mylist):
    element = 0
    missing = []
    search(mylist,element)
   # print(mylist)
    
    
def search(mylist,element):
    print(element)
    print(mylist)
    count = 0
    while count <= 9:
        if element in mylist:
            True
        else:
            False
        if False:
            count+=1
            return missing + element
        if True:
            count+=1
            
            
    
        
    
main()
