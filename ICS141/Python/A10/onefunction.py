def main():
    mylist = eval(input("Enter"))

    missing = [1]

    check = 0
    flag = True
    x = len(missing)-1
    print(len(missing))
    while check <= 9:
        if check in mylist:
            flag = True
        elif check not in mylist:
            flag = False
        if flag == False:
            missing[x] = check + 0
            x+=1
            check+=1
        if flag == True:
            check+=1

    print(missing)
main()
