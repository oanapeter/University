# Solve the problem from the second set here
def determinePalindrome(numberToReverse):
    palindrome=0
    while numberToReverse>0:
        palindrome=palindrome*10+numberToReverse%10
        numberToReverse=numberToReverse//10
    return palindrome

valueToReverse=int(input("Enter a value to determine its palindrome: "))
print("The palindrome is: " + str(determinePalindrome(valueToReverse)))
