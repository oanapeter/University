import unittest

from board import Board


class Tests(unittest.TestCase):
    def setUp(self) -> None:
        print("Set up")

    def tearDown(self) -> None:
        print("Tear down")

    def test_board(self):
        board = Board()
        board.move(0, 0, 'X')
        self.assertEqual(board.get_symbol(0, 0), 'X')
        self.assertEqual(board.count_the_empty_squares(), 35)
        try:
            board.move(0, 0, '0')
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Square already taken!")

        try:
            board.move(10,10,'X')
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Move outside the board!")
