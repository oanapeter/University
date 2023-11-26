class Question:
    def __init__(self, question_id, question, answer_a, answer_b, answer_c, correct_answer, difficulty_level):
        self.__question_id = question_id
        self.__question = question
        self.__answer_a = answer_a
        self.__answer_b = answer_b
        self.__answer_c = answer_c
        self.__correct_answer = correct_answer
        self.__difficulty_level = difficulty_level

    def get_question_id(self):
        return self.__question_id

    def get_question(self):
        return self.__question

    def get_answer_a(self):
        return self.__answer_a

    def get_answer_b(self):
        return self.__answer_b

    def get_answer_c(self):
        return self.__answer_c

    def get_correct_answer(self):
        return self.__correct_answer

    def get_difficulty_level(self):
        return self.__difficulty_level

    def __str__(self):
        return f"{self.__question_id};{self.__question};{self.__answer_a};{self.__answer_b};{self.__answer_c};{self.__correct_answer};{self.__difficulty_level}"

