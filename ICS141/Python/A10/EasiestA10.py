def main():
    mylist = eval(input("Enter a list of digits"))
    element = [0,1,2,3,4,5,6,7,8,9]
    print(list(set(element)-set(mylist)))
main()
