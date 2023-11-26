from game import Game


class UI:

    def process_user_command(self, user_command):
        user_command = user_command.strip()
        command_splitted = user_command.split(" ", maxsplit=1)
        command = command_splitted[0]
        if len(command_splitted) == 1:
            return command, ""
        positions_on_board = command_splitted[1].split(",")
        for i in range(len(positions_on_board)):
            positions_on_board[i].strip()
        return command, positions_on_board

    def start(self):
        game = Game()
        while True:
            print(game.get_board())
            user_command = input(">")
            command, positions = self.process_user_command(user_command)
            if command == "move" and len(positions) == 2 and positions[0].isnumeric() and positions[1].isnumeric():
                try:
                    row = int(positions[0])
                    column = int(positions[1])
                    game.human_move(row, column)
                except ValueError as ve:
                    print("Error: ", str(ve))
                if game.is_won():
                    print(game.get_board())
                    print("Congratulations! You won ! :)")
                    return
                else:
                    free_positions = game.computer_move()
                    print("Computer moved at " + str(free_positions))
                    if game.is_won():
                        print(game.get_board())
                        print("Computer won! You lost :'( ")
                        return
            elif command == "exit":
                break
            else:
                print("Invalid command!")


if __name__ == "__main__":
    ui = UI()
    ui.start()
