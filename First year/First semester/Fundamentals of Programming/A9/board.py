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

    def move(self, symbol, row, column):
        if row not in [0, 1, 2, 3, 4, 5] or column not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("Move outside the board!")
        if self.get_symbol(row, column) is not None:
            raise ValueError("Square already taken!")
        if self.get_symbol(row, column) == '■':
            raise ValueError("You can't move in the blocked squares!")
        self.__board[row][column] = symbol
        if row - 1 >= 0 and self.__board[row - 1][column] == ' ':
            self.__board[row - 1][column] = '■'
        if row - 1 >= 0 and column - 1 >= 0 and self.__board[row - 1][column - 1] == ' ':
            self.__board[row - 1][column - 1] = '■'
        if column - 1 >= 0 and self.__board[row][column - 1] == ' ':
            self.__board[row][column - 1] = '■'
        if row + 1 <= 5 and column - 1 >= 0 and self.__board[row + 1][column - 1] == ' ':
            self.__board[row + 1][column - 1] = '■'
        if row + 1 <= 5 and self.__board[row + 1][column] == ' ':
            self.__board[row + 1][column] = '■'
        if row + 1 <= 5 and column + 1 <= 5 and self.__board[row + 1][column + 1] == ' ':
            self.__board[row + 1][column + 1] = '■'
        if column + 1 <= 5 and self.__board[row][column + 1] == ' ':
            self.__board[row][column + 1] = '■'
        if row - 1 >= 0 and column + 1 <= 5 and self.__board[row - 1][column + 1] == ' ':
            self.__board[row - 1][column + 1] = '■'

    def count_the_empty_squares(self):
        number_of_empty_squares = 0
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board[row][column] == ' ':
                    number_of_empty_squares = number_of_empty_squares + 1
        return number_of_empty_squares

    def __str__(self):
        result = ""
        game_board = self.__board
        result += "      0     1     2     3     4     5"
        result += "\n"
        result += "   +-----+-----+-----+-----+-----+-----+"
        result += "\n"
        for row in [0, 1, 2, 3, 4, 5]:
            result += str(row) + "  |  " + game_board[row][0] + "  |  " + game_board[row][1] + "  |  " + \
                      game_board[row][
                          2] + "  |  " + game_board[row][3] + "  |  " + game_board[row][4] + "  |  " + game_board[row][
                          5] + "  |  " + "\n"
            result += "   +-----+-----+-----+-----+-----+-----+"
            result += "\n"
        return result
