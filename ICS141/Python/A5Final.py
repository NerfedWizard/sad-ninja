#Loel Nelson
#A program that makes copies of entered file name
#Complex files don't work only text files


def A5():
    infilename = input('Enter the filename to be copied:')
    infile = open(infilename, "r")
    number = input("How many copies:")
    indata = infile.read()
    x = int(number)

    for i in range(1,x+1):
        outfile = open("Copy " + str(i) + " of "+ infilename + ".py", "w")
        outfile.write(indata)
        outfile.close()

    infile.close()
A5()
