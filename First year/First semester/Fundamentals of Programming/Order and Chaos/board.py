class Board:
    def __init__(self):
        self.__board = []
        for i in [0, 1, 2, 3, 4, 5]:
            self.__board.append([' ', ' ', ' ', ' ', ' ', ' '])

    def get_symbol(self, row, column):
        symbol = self.__board[row][column]
        if symbol != ' ':
            return symbol
        else:
            return None

    def check_if_order_won(self):
        for i in [0, 1, 2, 3, 4, 5]:
            if self.__board[i][0] != " " and self.__board[i][1] != " ":
                if self.__board[i][0] == self.__board[i][1] == self.__board[i][2] == \
                        self.__board[i][3] == self.__board[i][4] or self.__board[i][1] == self.__board[i][2] == \
                        self.__board[i][3] == self.__board[i][4] == self.__board[i][5]:
                    return True
        for j in [0, 1, 2, 3, 4, 5]:
            if self.__board[0][j] != " " and self.__board[1][j] != " ":
                if self.__board[0][j] == self.__board[1][j] == \
                        self.__board[2][j] == self.__board[3][j] == self.__board[4][j] or self.__board[1][j] == \
                        self.__board[2][j] == self.__board[3][j] == self.__board[4][j] == self.__board[5][j]:
                    return True
        if self.__board[0][0] != " " and self.__board[5][5] != " ":
            if self.__board[0][0] == self.__board[1][1] == \
                    self.__board[2][2] == self.__board[3][3] == self.__board[4][4] or self.__board[1][1] == \
                    self.__board[2][
                        2] == self.__board[3][3] == self.__board[4][4] == self.__board[5][5]:
                return True
        if self.__board[1][0] != " " and self.__board[0][1] != " ":
            if self.__board[1][0] == self.__board[2][1] == \
                    self.__board[3][2] == self.__board[4][3] == self.__board[5][4] or self.__board[0][1] == \
                    self.__board[1][
                        2] == self.__board[2][3] == self.__board[3][4] == self.__board[4][5]:
                return True
        return False

    def check_if_chaos_won(self):
        if self.count_the_empty_squares() == 0:
            return True
        else:
            return False

    def move(self, row, column, symbol):
        if row not in [0, 1, 2, 3, 4, 5] or column not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("Move outside the board!")
        if self.get_symbol(row, column) is not None:
            raise ValueError("Square already taken!")
        self.__board[row][column] = symbol

    def count_the_empty_squares(self):
        number_of_empty_squares = 0
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board[row][column] == ' ':
                    number_of_empty_squares = number_of_empty_squares + 1
        return number_of_empty_squares

    def __str__(self):
        result = ""
        board = self.__board
        result += "   +-----+-----+-----+-----+-----+-----+"
        result += "\n"
        for row in [0, 1, 2, 3, 4, 5]:
            result += "   |  " + board[row][0] + "  |  " + board[row][1] + "  |  " + \
                      board[row][
                          2] + "  |  " + board[row][3] + "  |  " + board[row][4] + "  |  " + board[row][
                          5] + "  |  " + "\n"
            result += "   +-----+-----+-----+-----+-----+-----+"
            result += "\n"
        return result
