class Student:
    def __init__(self, student_id, student_name, student_group):
        self.__student_id = student_id
        self.__student_name = student_name
        self.__student_group = student_group

    def get_student_id(self):
        return self.__student_id

    def get_student_name(self):
        return self.__student_name

    def get_student_group(self):
        return self.__student_group

    def set_student_name(self, new_name):
        self.__student_name = new_name

    def set_student_group(self, new_group):
        self.__student_group = new_group

    def __str__(self):
        return f"Student with id: {self.__student_id} and name: {self.__student_name} and group: {self.__student_group}"