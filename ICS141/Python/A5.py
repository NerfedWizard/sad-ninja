def A5():
    import shutil
    import os
    myfile = input("Enter the filename to be copied:")

    x = eval(input("How many copies:"))
    
    for i in range(x):
        shutil.copy2(myfile,"C:/Users/Loel/Desktop/Folder")
 
            
    myfile.close()    
A5()
    

    
