import random
import timeit
def generateNumbers(listLength):
    randomNumbers=[]
    for i in range (0,listLength):
        randomNumbers.append(random.randint(0,10000))
    return randomNumbers


def cocktailSort(listToSort):
    listlength=len(listToSort)
    swapped = True
    start=0
    end=listlength-1
    print(listToSort)
    while swapped==True:
        swapped=False
        for i in range (start,end):
            if listToSort[i]>listToSort[i + 1]:
                listToSort[i], listToSort[i + 1]= listToSort[i + 1], listToSort[i]
                swapped=True
        if swapped==False:
            break
    end=end-1
    for i in range (end-1,start-1,-1):
        if listToSort[i]>listToSort[i + 1]:
            listToSort[i], listToSort[i + 1]= listToSort[i + 1], listToSort[i]
            swapped=True
    start=start+1

    return listToSort


def gnomeSort(listToSort):
    listlength=len(listToSort)
    i=0
    print(listToSort)
    while i<listlength:
        if i==0:
            i=i+1
        if listToSort[i]>=listToSort[i-1]:
            i=i+1
        else:
            listToSort[i],listToSort[i-1]=listToSort[i-1],listToSort[i]
            i=i-1
    return listToSort

def cocktailComplexity(List):
    cocktailstart=timeit.default_timer()
    cocktailSort(List)
    cocktailend=timeit.default_timer()

    return cocktailend-cocktailstart


def gnomeComplexity(List):
    gnomestart=timeit.default_timer()
    gnomeSort(List)
    gnomeend=timeit.default_timer()

    return gnomeend-gnomestart

def menu():
    numberofElements = int(input("Enter a large value for the lists: "))
    list_with_initial_value = generateNumbers(numberofElements)
    list_with_2times_initial_value = generateNumbers(numberofElements * 2)
    list_with_4times_initial_value = generateNumbers(numberofElements * 4)
    list_with_8times_initial_value = generateNumbers(numberofElements * 8)
    list_with_16times_initial_value = generateNumbers(numberofElements * 16)
    while True:
        print("1.Print the 5 lists with random numbers.")
        print("2.Sort the lists using Cocktail Sort.")
        print("3.Sort the lists using Gnome Sort.")
        print("4.Best case complexity.")
        print("5.Average case complexity.")
        print("6.Worst case complexity.")
        print("7.Exit.")
        option=input("Choose your option: ")
        if option== "1":
            print(list_with_initial_value)
            print(list_with_2times_initial_value)
            print(list_with_4times_initial_value)
            print(list_with_8times_initial_value)
            print(list_with_16times_initial_value)
        elif option== "2":
            print(cocktailSort(list_with_initial_value))
            print(cocktailSort(list_with_2times_initial_value))
            print(cocktailSort(list_with_4times_initial_value))
            print(cocktailSort(list_with_8times_initial_value))
            print(cocktailSort(list_with_16times_initial_value))
        elif option== "3":
            print(gnomeSort(list_with_initial_value))
            print(gnomeSort(list_with_2times_initial_value))
            print(gnomeSort(list_with_4times_initial_value))
            print(gnomeSort(list_with_8times_initial_value))
            print(gnomeSort(list_with_16times_initial_value))
        elif option== "4":
            list_with_initial_value.sort()
            list_with_2times_initial_value.sort()
            list_with_4times_initial_value.sort()
            list_with_8times_initial_value.sort()
            list_with_16times_initial_value.sort()
            print("Best case for Cocktail Sort:")
            print(cocktailComplexity(list_with_initial_value))
            print(cocktailComplexity(list_with_2times_initial_value))
            print(cocktailComplexity(list_with_4times_initial_value))
            print(cocktailComplexity(list_with_8times_initial_value))
            print(cocktailComplexity(list_with_16times_initial_value))
            print("Best case for Gnome Sort:")
            print(gnomeComplexity(list_with_initial_value))
            print(gnomeComplexity(list_with_2times_initial_value))
            print(gnomeComplexity(list_with_4times_initial_value))
            print(gnomeComplexity(list_with_8times_initial_value))
            print(gnomeComplexity(list_with_16times_initial_value))
        elif option== "5":
            print("Average case for Cocktail Sort:")
            print(cocktailComplexity(list_with_initial_value))
            print(cocktailComplexity(list_with_2times_initial_value))
            print(cocktailComplexity(list_with_4times_initial_value))
            print(cocktailComplexity(list_with_8times_initial_value))
            print(cocktailComplexity(list_with_16times_initial_value))
            print("Average case for Gnome Sort:")
            print(gnomeComplexity(list_with_initial_value))
            print(gnomeComplexity(list_with_2times_initial_value))
            print(gnomeComplexity(list_with_4times_initial_value))
            print(gnomeComplexity(list_with_8times_initial_value))
            print(gnomeComplexity(list_with_16times_initial_value))
        elif option== "6":
            list_with_initial_value.sort(reverse=True)
            list_with_2times_initial_value.sort(reverse=True)
            list_with_4times_initial_value.sort(reverse=True)
            list_with_8times_initial_value.sort(reverse=True)
            list_with_16times_initial_value.sort(reverse=True)
            print("Worst case for Cocktail Sort:")
            print(cocktailComplexity(list_with_initial_value))
            print(cocktailComplexity(list_with_2times_initial_value))
            print(cocktailComplexity(list_with_4times_initial_value))
            print(cocktailComplexity(list_with_8times_initial_value))
            print(cocktailComplexity(list_with_16times_initial_value))
            print("Worst case for Gnome Sort:")
            print(gnomeComplexity(list_with_initial_value))
            print(gnomeComplexity(list_with_2times_initial_value))
            print(gnomeComplexity(list_with_4times_initial_value))
            print(gnomeComplexity(list_with_8times_initial_value))
            print(gnomeComplexity(list_with_16times_initial_value))

        elif option== "7":
            break
        else:
            print("Oops! Invalid number.")

menu()