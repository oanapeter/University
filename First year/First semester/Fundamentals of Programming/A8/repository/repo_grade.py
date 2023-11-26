from src.errors.repo_error import RepoError


class RepoGrades:
    def __init__(self):
        self.__grades = []
        self.__ids_of_the_ungraded_assignments = []

    def give_assignment_to_a_student_repo(self, assignment_to_give):
        self.__grades.append(assignment_to_give)

    def get_all_grades_repo(self):
        return self.__grades

    def grades_size_repo(self):
        return len(self.__grades)

    def get_the_id_of_the_ungraded_assignments_of_the_student_repo(self, id_assignment):
        self.__ids_of_the_ungraded_assignments.append(id_assignment)

    def grade_the_assignment_repo(self, grade):
        assignment_id = grade.get_assignment_id_from_grade()
        student_id = grade.get_student_id_from_grade()
        grade_value = grade.get_grade_value()
        for grade in self.__grades:
            if grade.get_assignment_id_from_grade() == assignment_id and grade.get_student_id_from_grade() == student_id and grade.get_grade_value() == 0:
                grade.set_grade_value(grade_value)

    def remove_assignments_for_the_student_repo(self, student_id):
        for grade in self.__grades:
            if grade.get_student_id_from_grade() == student_id:
                self.__grades.remove(grade)

    def remove_grades_for_the_assignment_repo(self, assignment_id):
        for grade in self.__grades:
            if grade.get_assignment_id_from_grade() == assignment_id:
                    self.__grades.remove(grade)


    def get_the_grades_for_the_assignment_repo(self, grades_for_the_assignment):
        return [grade for grade in grades_for_the_assignment]

