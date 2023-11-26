from board import Board
from random import choice


class Game:
    def __init__(self):
        self.__board = Board()

    def get_board(self):
        return self.__board

    def human_move(self, row, column, symbol):
        if row not in [0, 1, 2, 3, 4, 5] or column not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("Move outside the board!")
        if self.__board.get_symbol(row, column) is not None:
            raise ValueError("Square already taken!")
        return self.__board.move(row, column, symbol)

    def computer_move(self):
        free_positions = []
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board.get_symbol(row, column) is None:
                    free_positions.append((row, column))
        positions = choice(free_positions)
        symbols = ['X','0']
        symbol = choice(symbols)
        self.__board.move(int(positions[0]), int(positions[1]), symbol)
        return positions, symbol

    def check_if_order_won_game(self):
        return self.__board.check_if_order_won()

    def check_if_chaos_won_game(self):
        return self.__board.check_if_chaos_won()



