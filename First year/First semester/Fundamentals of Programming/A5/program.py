#
# Write the implementation for A5 in this file
#

import math
import random

#
# Write below this comment
# Functions to deal with complex numbers -- list representation
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#
def generateComplexNumber():
    realPart=random.randint(1,30)
    imaginaryPart=random.randint(1,30)
    complexNumber=complex(realPart,imaginaryPart)
    return complexNumber

def convertComplexNumberToString(complexNumber):
    return f"{int(complexNumber.real)}+{int(complexNumber.imag)}i"

def getTheRealAndTheImaginaryPartFromComplexNumber(stringOfTheComplexNumber):
    realPart=0
    imaginaryPart=0
    counterInString=0

    while stringOfTheComplexNumber[counterInString]!='+':
        realPart=realPart*10+int(stringOfTheComplexNumber[counterInString])
        counterInString=counterInString+1

    counterInString=counterInString+1

    while stringOfTheComplexNumber[counterInString]!='i':
        imaginaryPart=imaginaryPart*10+int(stringOfTheComplexNumber[counterInString])
        counterInString=counterInString+1

    complexNumber=complex(realPart,imaginaryPart)
    return complexNumber

def addNumberInListOfComplexNumbers(complexNumber, listOfComplexNumbers):
    listOfComplexNumbers.append(convertComplexNumberToString(complexNumber))
    return listOfComplexNumbers
#
# Write below this comment
# Functions to deal with complex numbers -- dict representation
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#
def addNumberInDictionaryOfComplexNumbers(complexNumber, dictionaryOfComplexNumbers, key):
    dictionaryOfComplexNumbers[key]=convertComplexNumberToString(complexNumber)
    return dictionaryOfComplexNumbers
#
# Write below this comment
# Functions that deal with subarray/subsequence properties
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#
def createListWithTheModulusOfEachComplexNumber(listOfComplexNumbers):
    listWithTheModulusOfEachComplexNumber=[]
    for i in range (len(listOfComplexNumbers)):
        complexNumber=getTheRealAndTheImaginaryPartFromComplexNumber(listOfComplexNumbers[i])
        realPart=int(complexNumber.real)
        imaginaryPart=int(complexNumber.imag)
        listWithTheModulusOfEachComplexNumber.append(math.sqrt(realPart*realPart+imaginaryPart*imaginaryPart))

    return listWithTheModulusOfEachComplexNumber
def createListWithEachNumbersRealPart(listOfComplexNumbers):
    listWithTheRealPartsOfNumbers=[]
    for i in range (len(listOfComplexNumbers)):
        complexNumber=getTheRealAndTheImaginaryPartFromComplexNumber(listOfComplexNumbers[i])
        realPart=int(complexNumber.real)
        listWithTheRealPartsOfNumbers.append(realPart)

    return listWithTheRealPartsOfNumbers


def longestSubarrayOfComplexNumbersHavingIncreasingModulus(listOfComplexNumbers):
    listWithTheModulusOfEachComplexNumber=createListWithTheModulusOfEachComplexNumber(listOfComplexNumbers)
    resultSubarray=[]
    maximumLengthOfIncreasingModulus=1
    currentLength=1
    for i in range (1,len(listWithTheModulusOfEachComplexNumber)):
        if listWithTheModulusOfEachComplexNumber[i]>listWithTheModulusOfEachComplexNumber[i-1]:
            currentLength=currentLength+1
        else:
            if currentLength>maximumLengthOfIncreasingModulus:
                maximumLengthOfIncreasingModulus=currentLength
                indexOfWhereTheLongestIncreasingSubarrayStops=i-1
            currentLength=1
    if currentLength>maximumLengthOfIncreasingModulus:
        maximumLengthOfIncreasingModulus=currentLength
        indexOfWhereTheLongestIncreasingSubarrayStops = len(resultSubarray)-1

    for i in range(indexOfWhereTheLongestIncreasingSubarrayStops-maximumLengthOfIncreasingModulus+1, indexOfWhereTheLongestIncreasingSubarrayStops+1):
        resultSubarray.append(listWithTheModulusOfEachComplexNumber[i])
    return resultSubarray

def longestIncreasingSubsequenceWhenConsideringEachNumbersRealPart(listOfComplexNumbers, numberOfComplexNumbersInList):
    listOfIntermediateResultsLists = [[] for i in range(numberOfComplexNumbersInList)]
    listOfIntermediateResultsLists[0].append(listOfComplexNumbers[0])
    for i in range(1, numberOfComplexNumbersInList):
        for j in range(i):
            if listOfComplexNumbers[i] > listOfComplexNumbers[j] and (len(listOfIntermediateResultsLists[i]) < len(listOfIntermediateResultsLists[j]) + 1):
                listOfIntermediateResultsLists[i] = listOfIntermediateResultsLists[j].copy()
        listOfIntermediateResultsLists[i].append(listOfComplexNumbers[i])
    currentMaximumLengthOfLongestIncreasingSubsequenceWhenConsideringRealPart = listOfIntermediateResultsLists[0]

    for intermediateResultList in listOfIntermediateResultsLists:
        if len(intermediateResultList) > len(currentMaximumLengthOfLongestIncreasingSubsequenceWhenConsideringRealPart):
            currentMaximumLengthOfLongestIncreasingSubsequenceWhenConsideringRealPart = intermediateResultList
    return currentMaximumLengthOfLongestIncreasingSubsequenceWhenConsideringRealPart

#
# Write below this comment
# UI section
# Write all functions that have input or print statements here
# Ideally, this section should not contain any calculations relevant to program functionalities
#

def readComplexNumberFromConsole():
    complexNumberToString = str(input("Enter a new complex number (in form a+bi): "))
    complexNumber = getTheRealAndTheImaginaryPartFromComplexNumber(complexNumberToString)
    return complexNumber

def writeComplexNumberInConsole(complexNumber):
    print(convertComplexNumberToString(complexNumber))

def menu():
    numberOfComplexNumbers=10
    listOfComplexNumbers=[]
    dictionaryOfComplexNumbers={}
    increasingSubarrayOfModulusResult=[]
    increasingSubsequenceOfRealPartsOfNumbers=[]
    for i in range(10):
        complexNumber=generateComplexNumber()
        listOfComplexNumbers=addNumberInListOfComplexNumbers(complexNumber, listOfComplexNumbers)
        dictionaryOfComplexNumbers=addNumberInDictionaryOfComplexNumbers(complexNumber, dictionaryOfComplexNumbers, len(dictionaryOfComplexNumbers))
    while True:
        print()
        print("Options: ")
        print("1.Print the list of complex numbers.")
        print("2.Print the dictionary of complex numbers.")
        print("3.Add a new complex number.")
        print("4.Print the length and the elements of the longest subarray of numbers having increasing modulus.")
        print("5.Print the length and the elements of the longest increasing subsequence, when considering each number's real part.")
        print("6.Exit.")
        option=int(input("Choose your option: "))
        print()
        if option==1:
            print("The list of complex numbers is: ")
            print(listOfComplexNumbers)
        if option== 2:
            dictionaryOfComplexNumbers=addNumberInDictionaryOfComplexNumbers(complexNumber,dictionaryOfComplexNumbers,len(dictionaryOfComplexNumbers))
            print("The dictionary of complex numbers is: ")
            print(dictionaryOfComplexNumbers)
        if option ==3:
            complexNumber = readComplexNumberFromConsole()
            numberOfComplexNumbers=numberOfComplexNumbers+1
            listOfComplexNumbers = addNumberInListOfComplexNumbers(complexNumber, listOfComplexNumbers)
            dictionaryOfComplexNumbers = addNumberInDictionaryOfComplexNumbers(complexNumber,dictionaryOfComplexNumbers,len(dictionaryOfComplexNumbers))
        if option==4:
            listWithTheModulus = createListWithTheModulusOfEachComplexNumber(listOfComplexNumbers)
            increasingSubarrayOfModulusResult = longestSubarrayOfComplexNumbersHavingIncreasingModulus(listOfComplexNumbers)
            print("The length of the longest subarray of numbers having increasing modulus is: ")
            print(len(increasingSubarrayOfModulusResult))
            print("The subarray is: ")
            print(increasingSubarrayOfModulusResult)
            print()
        if option==5:
            listWithTheRealPartsOfNumbers=createListWithEachNumbersRealPart(listOfComplexNumbers)
            increasingSubsequenceOfRealPartsOfNumbers=longestIncreasingSubsequenceWhenConsideringEachNumbersRealPart(listWithTheRealPartsOfNumbers, numberOfComplexNumbers)
            print("The length of the longest increasing subsequence when considering each number's real part is: ")
            print(len(increasingSubsequenceOfRealPartsOfNumbers))
            print("The subsequence is: ")
            print(increasingSubsequenceOfRealPartsOfNumbers)

        if option==6:
            break


if __name__ == "__main__":
    menu()

