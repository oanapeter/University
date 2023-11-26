from src.errors.repo_error import RepoError


class RepoStudent:
    def __init__(self):
        self.__students = {}

    def add_student_repo_student(self, student_to_be_added):
        student_id = student_to_be_added.get_student_id()
        if student_id in self.__students:
            raise RepoError("Existent student!")
        self.__students[student_id] = student_to_be_added

    def get_all_students_repo(self):
        return [value for value in self.__students.values()]

    def get_all_students_repo_keys(self):
        return [key for key in self.__students.keys()]

    def student_size_repo(self):
        return len(self.__students)

    def modify_student_repo(self, student_to_be_modified):
        student_id = student_to_be_modified.get_student_id()
        if student_id not in self.__students:
            raise RepoError("Inexistent student!")
        self.__students[student_id] = student_to_be_modified

    def remove_student_repo(self, student_id_to_remove):
        if student_id_to_remove not in self.__students:
            raise RepoError("Inexistent student!")
        del self.__students[student_id_to_remove]
