class Grade:
    def __init__(self, assignment_id, student_id, grade_value):
        self.__assignment_id = assignment_id
        self.__student_id = student_id
        self.__grade_value = grade_value

    def get_assignment_id_from_grade(self):
        return self.__assignment_id

    def get_student_id_from_grade(self):
        return self.__student_id

    def get_grade_value(self):
        return self.__grade_value

    def set_student_id_for_grade(self, new_student_id):
        self.__student_id = new_student_id

    def set_grade_value(self, new_grade):
        self.__grade_value = new_grade

    def __repr__(self):
        return str(self)

    def __str__(self):
        return f"Assignment with id: {self.__assignment_id} done by student with the id : {self.__student_id} was graded: {self.__grade_value}/10"
