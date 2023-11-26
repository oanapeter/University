from board import Board
from random import choice


class Game:
    def __init__(self):
        self.__board = Board()

    def get_board(self):
        return self.__board

    def human_move(self, row, column):
        self.__board.move('X', row, column)

    def computer_move(self):
        free_positions = []
        position = []
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board.get_symbol(row, column) is None:
                    free_positions.append((row, column))

        for row in [1, 2, 3, 4]:
            for column in [1, 2, 3, 4]:
                if self.__board.get_symbol(row, column) is None and self.__board.get_symbol(row - 1, column) is None and self.__board.get_symbol(row - 1, column - 1) is None and self.__board.get_symbol(row,column - 1) is None and self.__board.get_symbol(row + 1, column - 1) is None and self.__board.get_symbol(row + 1,column + 1) is None and self.__board.get_symbol(row - 1, column + 1) is None:
                    position.append((row, column))
                break

        if len(position) == 0:
            position = choice(free_positions)
            self.__board.move('0', int(position[0]), int(position[1]))
            return position
        else:
            choose_position = choice(position)
            self.__board.move('O', int(choose_position[0]), int(choose_position[1]))
            return choose_position

    def is_won(self):
        if self.__board.count_the_empty_squares() == 0:
            return True
        return False
