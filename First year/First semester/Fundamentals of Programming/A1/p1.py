# Solve the problem from the first set here
import math
def isPrime(numbertoCheck):
    if numbertoCheck<=1:
        return False

    for i in range (2,int(math.sqrt(numbertoCheck)+1)):
        if numbertoCheck%i==0:
            return False
    return True

def firstPrimeLargerThan(givennumber):
    found=False
    currentnumber=givennumber
    while not found:
        currentnumber= currentnumber + 1
        if isPrime(currentnumber):
            found=True

    return currentnumber

givennumber=int(input("Enter a value. The program will generate the first prime number larger than the given value: "))
print("The number is " + str(firstPrimeLargerThan(givennumber)))
