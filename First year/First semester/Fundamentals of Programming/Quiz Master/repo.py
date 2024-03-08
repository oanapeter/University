from domain import Question


class Repo:
    def __init__(self):
        self._questions = []

    def get_all_questions_repo(self):
        return self._questions

    def add_question_repo(self, question):
        self._questions.append(question)

    def create_file(self, file, quiz):
        with open(file, "wt") as fila:
            for question in quiz:
                fila.write(str(question) + '\n')
        fila.close()

    def start_quiz_repo(self, file):
        quiz = []
        with open(file, "rt") as fila:
            lines = fila.readlines()
            for line in lines:
                if line != "":
                    parts = line.split(';')
                    id = parts[0].strip()
                    text = parts[1].strip()
                    a = parts[2].strip()
                    b = parts[3].strip()
                    c = parts[4].strip()
                    correct = parts[5].strip()
                    difficulty = parts[6].strip()
                    question = Question(id, text, a, b, c, correct, difficulty)
                    quiz.append(question)
        fila.close()
        with open(file, "wt") as fila:
            for question in quiz:
                fila.write(str(question) + '\n')
        fila.close()
        return quiz