class UI:
    def __init__(self, service):
        self.__service = service

    def add_question_ui(self, arguments):
        parts = arguments.split(';')
        if len(parts) != 7:
            print("Invalid format!")
            return
        self.__service.add_question_service(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6])

    def create_quiz_ui(self, arguments, file_list):
        self.__service.create_quiz_service(arguments, file_list)

    def start_quiz(self, file, file_list):
        if file not in file_list:
            print('Invalid file')
            return
        quiz = self.__service.start_quiz_service(file)
        points = 0
        for question in quiz:
            print(question.get_question())
            print(question.get_answer_a())
            print(question.get_answer_b())
            print(question.get_answer_c())
            answer = input('>')
            if answer == question.get_correct_answer():
                print('Correct')
                if question.get_difficulty_level() == 'hard':
                    points += 3
                elif question.get_difficulty_level() == 'medium':
                    points += 2
                else:
                    points += 1
            else:
                print('Incorrect')
        print('Result: '+ str(points))

    def menu(self):
        print("     Quiz Master!")
        print("Start a quiz by typing 'start quiz1.txt'!")
        file_list = []
        while True:
            user_command = input(">>")
            command = user_command.strip().split(' ', maxsplit=1)
            command1 = command[0]
            if command1 == "add":
                arguments = command[1].strip()
                self.add_question_ui(arguments)
            if command1 == "create":
                arguments = command[1]
                self.create_quiz_ui(arguments, file_list)
            if command1 == "start":
                file = command[1]
                file_list.append(file)
                self.start_quiz(file, file_list)
