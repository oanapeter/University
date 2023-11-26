from random import choice


class Service:
    def __init__(self, repo, validator):
        self.__repo = repo
        self.__validator = validator

    def add_a_sentence_service(self, sentence):
        self.__validator.validate(sentence)
        self.__repo.add_a_sentence_repo(sentence)

    def choose_a_sentence_service(self):
        sentences = self.__repo.get_all_sentences_repo()
        sentence = choice(sentences)
        return sentence

    def encode_the_sentence(self, list_with_characters_from_sentence):
        first_character_from_word = list_with_characters_from_sentence[0]
        last_character_from_word = list_with_characters_from_sentence[len(list_with_characters_from_sentence) - 1]
        for i in range(len(list_with_characters_from_sentence)):
            if list_with_characters_from_sentence[i] == first_character_from_word:
                list_with_characters_from_sentence[i] = first_character_from_word
        for i in range(len(list_with_characters_from_sentence)):
            if list_with_characters_from_sentence[i] == last_character_from_word:
                list_with_characters_from_sentence[i] = last_character_from_word
        for i in range(len(list_with_characters_from_sentence)):
            if i != 0 and i != len(list_with_characters_from_sentence) - 1 and list_with_characters_from_sentence[
                i] != " " and list_with_characters_from_sentence[i] != first_character_from_word and \
                    list_with_characters_from_sentence[i] != last_character_from_word:
                list_with_characters_from_sentence[i] = '_'
        return list_with_characters_from_sentence

    def put_the_guessed_letters(self, encoded_list_with_characters, not_encoded_list_with_characters, guessed_letter):
        for i in range(len(not_encoded_list_with_characters)):
            if not_encoded_list_with_characters[i] == guessed_letter:
                encoded_list_with_characters[i] = guessed_letter
        return encoded_list_with_characters

    def check_if_won(self, encoded_list_with_characters, not_encoded_list_with_characters):
        ok = 1
        for i in range(len(encoded_list_with_characters)):
            if encoded_list_with_characters[i] != not_encoded_list_with_characters[i]:
                ok = 0
        return ok

    def check_if_the_letter_is_in_the_word(self, guessed_letter, not_encoded_list_with_characters):
        good_guess = False
        if guessed_letter in not_encoded_list_with_characters:
            good_guess = True
        return good_guess

    def return_sentences(self):
        return self.__repo.get_all_sentences_repo()

