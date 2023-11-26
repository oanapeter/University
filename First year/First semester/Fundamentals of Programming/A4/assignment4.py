def generateValidParenthesesRecursive(stringOfParentheses, numberOfOpenParentheses, currentPosition, numberOfClosedParentheses, MaximumParenthesesNumber):
    if numberOfClosedParentheses==MaximumParenthesesNumber//2:
        for i in stringOfParentheses:
            print(i,end='')
        print()
        return
    else:
        if numberOfClosedParentheses<numberOfOpenParentheses:
            stringOfParentheses[currentPosition]= ')'
            generateValidParenthesesRecursive(stringOfParentheses, numberOfOpenParentheses, currentPosition + 1, numberOfClosedParentheses + 1, MaximumParenthesesNumber)
        if numberOfOpenParentheses<MaximumParenthesesNumber//2:
            stringOfParentheses[currentPosition]= '('
            generateValidParenthesesRecursive(stringOfParentheses, numberOfOpenParentheses + 1, currentPosition + 1, numberOfClosedParentheses, MaximumParenthesesNumber)


def generateValidParenthesesIterative(MaximumParenthesesNumber):
    validParenthesesResult=[]
    numberusedforpairs= MaximumParenthesesNumber // 2
    stack=[("(",numberusedforpairs-1,numberusedforpairs)]
    while stack:
        element=stack.pop()
        parenthesis=element[0]
        remainingopenParantheses=element[1]
        remainingclosedParantheses=element[2]
        if remainingopenParantheses==0 and remainingclosedParantheses==0:
            validParenthesesResult.append(parenthesis)
        else:
            if remainingopenParantheses!=0:
                stack.append([parenthesis +"(", remainingopenParantheses - 1, remainingclosedParantheses])
            if remainingopenParantheses<remainingclosedParantheses:
                stack.append([parenthesis +")", remainingopenParantheses, remainingclosedParantheses - 1])
    return validParenthesesResult


numberofParentheses=int(input("How many parantheses? "))

if numberofParentheses%2==1:
    numberofParentheses= numberofParentheses - 1
parenthesesString= [""] * numberofParentheses
generateValidParenthesesRecursive(parenthesesString, 0, 0, 0, numberofParentheses)
print(generateValidParenthesesIterative(numberofParentheses))




