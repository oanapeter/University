# Solve the problem from the third set here
def isPerfect(numberToCheck):
    sumofDivisors=0
    for i in range (1,numberToCheck):
        if numberToCheck%i==0:
            sumofDivisors=sumofDivisors+i
    if numberToCheck==sumofDivisors:
        return True
    return False

def largestPerfectNumberSmallerThanAValue(numbergiven):
    while numbergiven>0:
        numbergiven=numbergiven-1
        if isPerfect(numbergiven):
            return numbergiven

    return False

valueGiven=int(input("Enter a value. The program will generate the largest perfect number smaller than the given number: "))
if largestPerfectNumberSmallerThanAValue(valueGiven):
    print("The perfect number is: "+ str(largestPerfectNumberSmallerThanAValue(valueGiven)))
else:
    print("A perfect number doesn't exist")
