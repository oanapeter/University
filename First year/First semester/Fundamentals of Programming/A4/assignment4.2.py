def longestCommonSubsequenceOf2wordsNaive(word1, word2, lengthWord1, lengthWord2):

    if lengthWord1==0 or lengthWord2==0:
        return 0
    elif word1[lengthWord1-1]==word2[lengthWord2-1]:
        return 1 + longestCommonSubsequenceOf2wordsNaive(word1, word2, lengthWord1 - 1, lengthWord2 - 1)
    else:
        return max(longestCommonSubsequenceOf2wordsNaive(word1, word2, lengthWord1 - 1, lengthWord2), longestCommonSubsequenceOf2wordsNaive(word1, word2, lengthWord1, lengthWord2 - 1))


def longestCommonSubsequenceOf2wordsDynamic(word1, word2):
    lengthWord1=len(word1)
    lengthWord2=len(word2)
    copyLengthWord1=lengthWord1
    copyLengthWord2=lengthWord2
    intermediateResultsMatrix=[[None] * (lengthWord2 + 1) for i in range(lengthWord1 + 1)]
    for i in range (lengthWord1+1):
        for j in range (lengthWord2+1):
            if i==0 or j==0:
                intermediateResultsMatrix[i][j]=0
            elif word1[i-1]==word2[j-1]:
                intermediateResultsMatrix[i][j]= intermediateResultsMatrix[i - 1][j - 1] + 1
            else:
                intermediateResultsMatrix[i][j]=max(intermediateResultsMatrix[i - 1][j], intermediateResultsMatrix[i][j - 1])
    longest_common_subsequence=""
    while lengthWord1>0 and lengthWord2>0:
        if word1[lengthWord1-1]==word2[lengthWord2-1]:
            longest_common_subsequence=longest_common_subsequence+word1[lengthWord1-1]
            lengthWord1=lengthWord1-1
            lengthWord2=lengthWord2-1
        elif intermediateResultsMatrix[i - 1][j]>intermediateResultsMatrix[i][j - 1]:
            lengthWord1=lengthWord1-1
        else:
            lengthWord2=lengthWord2-1

    longest_common_subsequence=longest_common_subsequence[::-1]
    counterForTableFrame=-1
    print("x"," ",end='')
    print("Ø"," ",end='')
    for letter in word1:
        print(letter, " ", end='')
    print()
    for i in range (copyLengthWord2+1):
        if counterForTableFrame==-1:
            print("Ø"," ",end='')
        elif counterForTableFrame<copyLengthWord2:
            print(word2[counterForTableFrame]," ",end='')
        if counterForTableFrame<copyLengthWord2-1:
            counterForTableFrame=counterForTableFrame+1
        for j in range (copyLengthWord1+1):
            print(intermediateResultsMatrix[j][i], " ", end='')

        print()
    print()
    print("The length of the longest common subsequence of " + str(word1) +" and " + str(word2) + " ,using the Dynamic version, is " + str(intermediateResultsMatrix[copyLengthWord1][copyLengthWord2]))
    print()
    print("One such subsequence is: " +str(longest_common_subsequence))

word1="MNPNQMN"
word2="NQPMNM"
print("The length of the longest common subsequence of " + str(word1) +" and " + str(word2) +" ,using the Naive Implementation, is " + str(longestCommonSubsequenceOf2wordsNaive(word1, word2, len(word1), len(word2))))
print()
print("The Dynamic Programming version stores the intermediate results in a matrix, so the matrix is: ")
print()
longestCommonSubsequenceOf2wordsDynamic(word1, word2)
