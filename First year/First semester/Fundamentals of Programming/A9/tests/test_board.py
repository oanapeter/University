import unittest

from board import Board


class TestBoard(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_move(self):
        board = Board()
        board.move('X', 1,1)
        try:
            board.move('X', 10, 10)
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Move outside the board!")
        try:
            board.move('X', 1, 1)
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Square already taken!")

    def test_empty_squares(self):
        board = Board()
        self.assertEqual(board.count_the_empty_squares(), 36)
        board.move('X', 0, 0)
        self.assertEqual(board.count_the_empty_squares(), 32)
        board.move('0', 4, 4)
        self.assertEqual(board.count_the_empty_squares(), 23)

    def test_get_symbol(self):
        board = Board()
        board.move('X', 1, 1)
        self.assertEqual(board.get_symbol(1, 1), 'X')
        board.move('0', 5, 5)
        self.assertEqual(board.get_symbol(5, 5), '0')