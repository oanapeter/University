import selectors

from board import Board
from random import choice


class Game:
    def __init__(self):
        self.__board = Board()

    def get_board(self):
        return self.__board

    def human_move(self, row, column):
        if self.__board.get_symbol(row, column) != ' ':
            raise ValueError("Square already taken!")
        else:
            self.__board.place_piece_on_board(row, column, 'X')

    def get_the_empty_square(self):
        for row in [0, 1, 2]:
            for column in [0, 1, 2]:
                if self.__board.get_symbol(row, column) == ' ':
                    row_of_the_free_square = row
                    column_of_the_free_square = column
        return row_of_the_free_square, column_of_the_free_square

    def movement_phase(self, row_of_the_free_square, column_of_the_free_square, row_to_move, column_to_move, symbol):
        self.__board.place_piece_on_board(row_of_the_free_square, column_of_the_free_square, symbol)
        self.__board.place_piece_on_board(row_to_move, column_to_move, ' ')

    def choose_a_square_for_the_computer_to_move(self):
        squares_of_O = []
        for row in [0, 1, 2]:
            for column in [0, 1, 2]:
                if self.__board.get_symbol(row, column) == 'O':
                    squares_of_O.append([row, column])
        which_square_to_move = choice(squares_of_O)
        row_to_move = int(which_square_to_move[0])
        column_to_move = int(which_square_to_move[1])
        return row_to_move, column_to_move

    def computer_move(self, row_where_the_user_placed_the_last_symbol, column_where_the_user_placed_the_last_symbol):
        free_squares = []
        block_user_win_row = []
        can_block_row = False
        for row in [0,1,2]:
            number_of_X_on_row = 0
            if self.__board.get_symbol(row, 0) == 'X':
                number_of_X_on_row += 1
            if self.__board.get_symbol(row, 1) == 'X':
                number_of_X_on_row += 1
            if self.__board.get_symbol(row, 2) == 'X':
                number_of_X_on_row += 1
            if number_of_X_on_row == 2:
                if self.__board.get_symbol(row,0) == ' ':
                    block_user_win_row.append([row, 0])
                    can_block_row = True
                if self.__board.get_symbol(row, 1) == ' ':
                    block_user_win_row.append([row,1])
                    can_block_row = True
                if self.__board.get_symbol(row, 2) == ' ':
                    block_user_win_row.append([row,2])
                    can_block_row = True
        block_user_win_column = []
        can_block_column = False
        for column in [0,1,2]:
            number_of_X_on_column = 0
            if self.__board.get_symbol(0, column) == 'X':
                number_of_X_on_column += 1
            if self.__board.get_symbol(1, column) == 'X':
                number_of_X_on_column += 1
            if self.__board.get_symbol(2, column) == 'X':
                number_of_X_on_column += 1
            if number_of_X_on_column == 2:
                if self.__board.get_symbol(0, column) == ' ':
                    block_user_win_column.append([0, column])
                    can_block_column = True
                if self.__board.get_symbol(1, column) == ' ':
                    block_user_win_column.append([1, column])
                    can_block_column = True
                if self.__board.get_symbol(2, column) == ' ':
                    block_user_win_column.append([2,column])
                    can_block_column = True
        if can_block_row is True:
            positions = choice(block_user_win_row)
        elif can_block_column is True:
            positions = choice(block_user_win_column)
        else:
            if row_where_the_user_placed_the_last_symbol - 1 >= 0:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol - 1,
                                           column_where_the_user_placed_the_last_symbol) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol - 1, column_where_the_user_placed_the_last_symbol])
            if row_where_the_user_placed_the_last_symbol - 1 >= 0 and column_where_the_user_placed_the_last_symbol + 1 <= 2:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol - 1,
                                           column_where_the_user_placed_the_last_symbol + 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol - 1, column_where_the_user_placed_the_last_symbol + 1])
            if column_where_the_user_placed_the_last_symbol + 1 <= 2:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol,
                                           column_where_the_user_placed_the_last_symbol + 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol, column_where_the_user_placed_the_last_symbol + 1])
            if row_where_the_user_placed_the_last_symbol + 1 <= 2 and column_where_the_user_placed_the_last_symbol + 1 <= 2:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol + 1,
                                           column_where_the_user_placed_the_last_symbol + 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol + 1, column_where_the_user_placed_the_last_symbol + 1])
            if row_where_the_user_placed_the_last_symbol + 1 <= 2:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol + 1,
                                           column_where_the_user_placed_the_last_symbol) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol + 1, column_where_the_user_placed_the_last_symbol])
            if row_where_the_user_placed_the_last_symbol + 1 <= 2 and column_where_the_user_placed_the_last_symbol - 1 >= 0:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol + 1,
                                           column_where_the_user_placed_the_last_symbol - 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol + 1, column_where_the_user_placed_the_last_symbol - 1])
            if column_where_the_user_placed_the_last_symbol - 1 >= 0:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol,
                                           column_where_the_user_placed_the_last_symbol - 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol, column_where_the_user_placed_the_last_symbol - 1])
            if row_where_the_user_placed_the_last_symbol - 1 >= 0 and column_where_the_user_placed_the_last_symbol - 1 >= 0:
                if self.__board.get_symbol(row_where_the_user_placed_the_last_symbol - 1,
                                           column_where_the_user_placed_the_last_symbol - 1) == ' ':
                    free_squares.append(
                        [row_where_the_user_placed_the_last_symbol - 1, column_where_the_user_placed_the_last_symbol - 1])
            positions = choice(free_squares)
        row_to_place = int(positions[0])
        column_to_place = int(positions[1])
        self.__board.place_piece_on_board(row_to_place, column_to_place, 'O')

    def check_if_human_won(self):
        for row in [0, 1, 2]:
            if self.__board.get_symbol(row, 0) == 'X' and self.__board.get_symbol(row, 0) == self.__board.get_symbol(row, 1) == self.__board.get_symbol(row, 2):
                return True
        for column in [0, 1, 2]:
            if self.__board.get_symbol(0, column) == 'X' and self.__board.get_symbol(0,column) == self.__board.get_symbol(1, column) == self.__board.get_symbol(2, column):
                return True
        if self.__board.get_symbol(0, 0) == 'X' and self.__board.get_symbol(0, 0) == self.__board.get_symbol(1,1) == self.__board.get_symbol(2, 2):
            return True
        if self.__board.get_symbol(0, 2) == 'X' and self.__board.get_symbol(0, 2) == self.__board.get_symbol(1,1) == self.__board.get_symbol(2, 0):
            return True
        return False

    def check_if_computer_won(self):
        for row in [0, 1, 2]:
            if self.__board.get_symbol(row, 0) == 'O' and self.__board.get_symbol(row, 0) == self.__board.get_symbol(
                    row, 1) == self.__board.get_symbol(row, 2):
                return True
        for column in [0, 1, 2]:
            if self.__board.get_symbol(0, column) == 'O' and self.__board.get_symbol(0,
                                                                                     column) == self.__board.get_symbol(
                1, column) == self.__board.get_symbol(2, column):
                return True
        if self.__board.get_symbol(0, 0) == 'O' and self.__board.get_symbol(0, 0) == self.__board.get_symbol(1,
                                                                                                             1) == self.__board.get_symbol(
            2, 2):
            return True
        if self.__board.get_symbol(0, 2) == 'O' and self.__board.get_symbol(0, 2) == self.__board.get_symbol(1,
                                                                                                             1) == self.__board.get_symbol(
            2, 0):
            return True
        return False
