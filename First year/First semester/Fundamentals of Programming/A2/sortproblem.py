import random
def generateNumbers():
    listLength=int(input("How many elements the list should have: "))
    randomNumbers=[]
    for i in range (0,listLength):
        randomNumbers.append(random.randint(0,100))
    return randomNumbers


def cocktailSort(listToSort, step):
    listlength=len(listToSort)
    swapped = True
    start=0
    currentStep=0
    end=listlength-1
    print(listToSort)
    while swapped==True:
        swapped=False
        for i in range (start,end):
            if listToSort[i]>listToSort[i + 1]:
                listToSort[i], listToSort[i + 1]= listToSort[i + 1], listToSort[i]
                swapped=True
                currentStep = currentStep + 1
                if currentStep == step:
                    print(listToSort)
                    currentStep = 0
        if swapped==False:
            break
    end=end-1
    for i in range (end-1,start-1,-1):
        if listToSort[i]>listToSort[i + 1]:
            listToSort[i], listToSort[i + 1]= listToSort[i + 1], listToSort[i]
            swapped=True
            currentStep = currentStep + 1
            if currentStep == step:
                print(listToSort)
                currentStep = 0
    start=start+1

    return listToSort
    print(listToSort)

def gnomeSort(listToSort,step):
    listlength=len(listToSort)
    i=0
    print(listToSort)
    currentStep=0
    while i<listlength:
        if i==0:
            i=i+1
        if listToSort[i]>=listToSort[i-1]:
            i=i+1
        else:
            listToSort[i],listToSort[i-1]=listToSort[i-1],listToSort[i]
            i=i-1
            currentStep=currentStep+1
            if currentStep==step:
                print(listToSort)
                currentStep=0
    return listToSort

def menu():
    listofRandomNumbers=[]
    listofRandomNumbers=generateNumbers()
    while True:
        print("1.Print the list with random numbers.")
        print("2.Sort the list using Cocktail Sort.")
        print("3.Sort the list using Gnome Sort.")
        print("4.Exit.")
        opt=input("Choose your option: ")
        if opt=="1":
            print(listofRandomNumbers)
        elif opt=="2":
            step=int(input("How many steps do you want: "))
            print(cocktailSort(listofRandomNumbers,step))
        elif opt=="3":
            step=int(input("How many steps do you want: "))
            print(gnomeSort(listofRandomNumbers,step))
        elif opt=="4":
            break
        else:
            print("Oops! Invalid number.")

menu()