from hangman.repo_err import RepoError
from hangman.valid_error import ValidError


class UI:
    def __init__(self, service):
        self.__service = service

    @staticmethod
    def menu():
        print()
        print("     Hangman!")
        print("1.Add a sentence.")
        print("2.Play Hangman.")
        print("3.Exit.")

    def add_a_sentence_ui(self):
        sentence = input("Give a sentence: ")
        if sentence in self.__service.return_sentences():
            raise ValueError("Sentence already exists!")
        self.__service.add_a_sentence_service(sentence)

    def play_hangman(self):
        sentence = self.__service.choose_a_sentence_service()
        not_encoded_list_with_characters = list(sentence)
        encoded_list_with_characters = self.__service.encode_the_sentence(list(sentence))
        number_of_wrong_guesses = 0
        hangman = ['h', 'a', 'n', 'g', 'm', 'a', 'n']
        guesses = []
        for letter in encoded_list_with_characters:
            print(letter, end="")
        while True:
            print()
            guessed_letter = input("Guess a letter: ")
            if guessed_letter.isalpha() and len(guessed_letter) == 1:
                good_guess = self.__service.check_if_the_letter_is_in_the_word(guessed_letter,
                                                                               not_encoded_list_with_characters)
                if guessed_letter in guesses:
                    print("You already used the letter.")
                elif not good_guess:
                    print("Oops! Not correct!")
                    number_of_wrong_guesses = number_of_wrong_guesses + 1
                elif good_guess:
                    print("Lucky guess!")
                    guesses.append(guessed_letter)
                    encoded_list_with_characters = self.__service.put_the_guessed_letters(encoded_list_with_characters,
                                                                                          not_encoded_list_with_characters,
                                                                                          guessed_letter)
                for i in range(len(encoded_list_with_characters)):
                    print(encoded_list_with_characters[i], end="")
                if number_of_wrong_guesses != 0:
                    print(" -- ", end="")
                    for i in range(number_of_wrong_guesses):
                        print(str(hangman[i]), end="")

                if number_of_wrong_guesses == 7:
                    print()
                    print("Game over! You were hanged!")
                    break
                else:
                    ok = self.__service.check_if_won(encoded_list_with_characters, not_encoded_list_with_characters)
                    if ok == 1:
                        print()
                        print("You won!")
                        break
            else:
                print("Please give a valid input.")

    def start(self):
        while True:
            self.menu()
            option = input("Choose your option: ")
            try:
                if option == '1':
                    self.add_a_sentence_ui()
                elif option == '2':
                    self.play_hangman()
                elif option == '3':
                    break
                else:
                    print("Invalid command.")
            except ValidError as ve:
                print("Error: ", str(ve))
            except RepoError as r:
                print("Error: ", str(r))
