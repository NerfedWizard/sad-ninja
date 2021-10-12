def fun1(a,b):
    print("line 1", a)
    a = 3
    b[0] = 4
    return a + 22
def fun2():
    a = 12
    b = 2
    c = [7,8,9]
    d = fun1(a,c)
    print("line 2:",a)
    print("line 3:",b)
    print("line 4:",c)
    print("line 5:",d)

fun2()
