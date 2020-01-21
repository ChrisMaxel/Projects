from os import listdir
from os.path import isfile, join

file = open("ImageLinks.txt","a+")

def getNames():
    directory = "Messenger"
    mypath = "X:\Linux\SYS390\SYS390_FinalProject\imagesForSRS\Wireframes\Wireframes\\" + directory
    onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
    # print(onlyfiles)
    
    for i in range(0,len(onlyfiles),1):
        md = "![Figure ["
        md += onlyfiles[i][:-4]
        md += "]: "
        md += onlyfiles[i][:-4]
        md += "](https://raw.githubusercontent.com/ChrisMaxel/SYS390_FinalProject/master/imagesForSRS/Wireframes\Wireframes\\"
        md += directory
        md += "/"
        md += onlyfiles[i]
        #md += "\n"
        print (md,"\n")
        file.write(md)
        
def main():
    getNames()
    



main()
file.close()
