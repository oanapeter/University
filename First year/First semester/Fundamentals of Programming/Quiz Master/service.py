
from random import choice

from domain import Question


class Service:
    def __init__(self, repo, validator):
        self.__repo = repo
        self.__validator = validator

    def add_question_service(self, question_id, question, answer_a, answer_b, answer_c, correct_answer, difficulty_level):
        question = Question(question_id, question, answer_a, answer_b, answer_c, correct_answer, difficulty_level)
        self.__validator.validate(question)
        self.__repo.add_question_repo(question)

    def start_quiz_service(self, file):
        return self.__repo.start_quiz_repo(file)

    def create_quiz_service(self, arguments, file_list):
        arguments1 = arguments.split(' ')
        if len(arguments1) != 3:
            print('Incorrect format')
            return
        difficulty_level = arguments1[0]
        if difficulty_level != 'easy' and difficulty_level != 'medium' and difficulty_level != 'hard':
            print('Incorrect difficulty')
            return
        number_of_questions = int(arguments1[1])
        try:
            value = int(number_of_questions)
        except ValueError:
            print('Question number is not an int')
        file = arguments1[2]
        if '.txt' not in file:
            print('.txt needed')
            return
        file_list.append(file)
        questions = self.__repo.get_all_questions_repo()
        quiz = []
        number_d = 0
        for question in questions:
            if question.get_difficulty_level() == difficulty_level:
                number_d += 1
        if number_of_questions % 2 == 0:
            number = number_of_questions // 2
        else:
            number = number_of_questions // 2 + 1
        if number_d < number or number_of_questions > len(questions):
            return 'Invalid number of questions'
        for i in range(0, number):
            for question in questions:
                if question.get_difficulty_level() == difficulty_level:
                    quiz.append(question)
                    questions.remove(question)
                    break
        for i in range(number, number_of_questions):
            question = choice(questions)
            quiz.append(question)
            questions.remove(question)
        self.__repo.create_file(file, quiz)
        return 'Quiz created successfully'




