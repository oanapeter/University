class Board:
    def __init__(self):
        self.__board = []
        for row in [0,1,2]:
            self.__board.append([' ', ' ', ' '])


    def place_piece_on_board(self, row, column, symbol):
        self.__board[row][column] = symbol

    def get_symbol(self, row, column):
        return self.__board[row][column]

    def __str__(self):
        result = ""
        board = self.__board
        result += "+-----+-----+-----+"
        result += '\n'
        for row in [0,1,2]:
            result += "|  " + board[row][0] + "  |  " + board[row][1] + "  |  " + board[row][2] + "  |  " + "\n"
            result += "+-----+-----+-----+"
            result += "\n"
        return result
