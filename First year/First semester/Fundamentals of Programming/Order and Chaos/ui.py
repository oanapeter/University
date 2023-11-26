from game import Game


class UI:
    def process_user_command(self, user_command):
        usercommand = user_command.strip().split(',')
        row = usercommand[0]
        column = usercommand[1]
        symbol = usercommand[2]
        return row, column, symbol

    def play(self):
        game = Game()
        human_move = False
        print("---------------ORDER AND CHAOS---------------")
        print("The commands are in form 'row,column,symbol'")
        while not human_move:
            print(game.get_board())
            human_command = input(">>")
            row, column, symbol = self.process_user_command(human_command)
            while row not in ['0', '1', '2', '3', '4', '5'] or column not in ['0', '1', '2', '3', '4',
                                                                              '5'] or symbol != 'X' and symbol != '0':
                print("Invalid command.")
                print(game.get_board())
                human_command = input(">>")
                row, column, symbol = self.process_user_command(human_command)
            try:
                row = int(row)
                column = int(column)
                game.human_move(row, column, symbol)
                human_move = True
            except ValueError as ve:
                print("Error: ", str(ve))
            if human_move is True:
                if game.check_if_order_won_game() is True:
                    print(game.get_board())
                    print("Order won!")
                    break
                free_positions, symbol = game.computer_move()
                human_move = False
                if game.check_if_chaos_won_game():
                    print(game.get_board())
                    print("Chaos won!")
                    break


if __name__ == "__main__":
    ui = UI()
    ui.play()
