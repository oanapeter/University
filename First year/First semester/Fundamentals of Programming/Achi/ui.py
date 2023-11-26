from game import Game


class ValidError(Exception):
    pass


class UI:
    def process_user_command(self, user_command):
        if len(user_command) != 3:
            raise ValidError("Invalid command!")
        command_splitted = user_command.strip().split(",")
        row = int(command_splitted[0])
        column = int(command_splitted[1])
        return row, column

    def ui(self):
        game = Game()
        human_move = False
        computer_move = False
        number_of_turns = 0
        is_won = False
        while number_of_turns < 4:
            print(game.get_board())
            human_command = input(">>")
            if human_move is False:
                try:
                    row, column = self.process_user_command(human_command)
                    while row not in [0, 1, 2] or column not in [0, 1, 2]:
                        print("Invalid command!")
                        human_command = input(">>")
                        row, column = self.process_user_command(human_command)
                    row = int(row)
                    column = int(column)
                    game.human_move(row, column)
                    human_move = True
                except ValidError as ve:
                    print("Error: ", str(ve))
                except ValueError as v:
                    print("Error: ", str(v))
                if human_move is True:
                    is_won = game.check_if_human_won()
                if is_won is True:
                    print(game.get_board())
                    print("Congrats! You won!")
                    break
            if computer_move is False and human_move is True:
                game.computer_move(row, column)
                computer_move = True
                is_won = game.check_if_computer_won()
                if is_won is True:
                    print(game.get_board())
                    print("Computer won!")
                    break
            if human_move is True and computer_move is True:
                number_of_turns = number_of_turns + 1
                human_move = False
                computer_move = False
            if number_of_turns == 4:
                print(game.get_board())
        if is_won is False:
            print("Movement phase begins!")
        human_move = False
        computer_move = False
        while is_won is False:
            print(game.get_board())
            human_command = input(">>")
            if human_move is False:
                try:
                    row, column = self.process_user_command(human_command)
                    while row not in [0, 1, 2] or column not in [0, 1, 2]:
                        print("Invalid command!")
                        human_command = input(">>")
                        row, column = self.process_user_command(human_command)
                    row = int(row)
                    column = int(column)
                    row_of_the_free_square, column_of_the_free_square = game.get_the_empty_square()
                    game.movement_phase(row_of_the_free_square, column_of_the_free_square, row, column, 'X')
                    human_move = True
                except ValidError as ve:
                    print("Error: ", str(ve))
                except ValueError as v:
                    print("Error: ", str(v))
                if human_move is True:
                    is_won = game.check_if_human_won()
                if is_won is True:
                    print(game.get_board())
                    print("Congrats! You won!")
                    break
            if computer_move is False and human_move is True:
                row, column = game.choose_a_square_for_the_computer_to_move()
                row_of_the_free_square, column_of_the_free_square = game.get_the_empty_square()
                game.movement_phase(row_of_the_free_square, column_of_the_free_square, row, column, 'O')
                computer_move = True
                is_won = game.check_if_computer_won()
                if is_won is True:
                    print(game.get_board())
                    print("Computer won!")
                    break
            human_move = False
            computer_move = False


ui = UI()
ui.ui()
