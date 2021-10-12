# Loel Nelson
# A Program That Takes 2 Files From the User
# Reads in the Data From Each File
# Makes a List of Each File
# Then Merges the 2 Files into A Master List
# Then Creates a New File of the Merged Lists
def A7():
    
#Ask For File Input
    try:
        file1name = input("Enter First File Name:")
        infile1 = open(file1name, "r")

        #Ask For File2 Input
        try:
            file2name = input("Enter Second File Name:")
            infile2 = open(file2name, "r")
            

#Part 1:    #Compiling Data From File1 into List1

            list1 = []
            linelist = [1]
            lineinput = infile1.readline()
            while lineinput != "\n" and len(linelist) != 0:
                linelist = lineinput.split()
                list1 = list1 + linelist
                lineinput = infile1.readline()

#Part 2:    #Compiling Data From File2 into List2
            list2 = []
            linelist = [1]
            lineinput = infile2.readline()
            while lineinput != "\n" and len(linelist) != 0:
                linelist = lineinput.split()
                list2 = list2 + linelist
                lineinput = infile2.readline()

#Part 3:    #Merging List1 and List2 into Master List
            masterlist = []
            mastercount = 0
            file1count = 0
            file2count = 0
            while mastercount < len(list1) + len(list2):
                if file1count < len(list1):
                    masterlist += [list1[file1count]]
                    file1count += 1
                if file2count < len(list2):
                    masterlist += [list2[file2count]]
                    file2count += 1
                mastercount += 1

#Part 4:    #Writes Master List to File
                  #Using File1 Name + File2 Name
                  #As The New Name For Merged List File
            outfile = open(file1name + file2name + ".txt","w")
            mastercount = 0
            while mastercount < len(masterlist):
                outfile.write(masterlist[mastercount])
                outfile.write("\n")
                mastercount += 1
            outfile.close()
            
        except FileNotFoundError:
            print("Not a Known File")
    except FileNotFoundError:
        print("Not a Known File")

A7()

