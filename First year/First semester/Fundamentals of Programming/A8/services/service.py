from datetime import date

from src.domain.assignment import Assignment
from src.domain.grade import Grade
from src.domain.student import Student


class Service:
    def __init__(self, repo_student, repo_assignment, repo_grades, validate_student, validate_student_for_modify,
                 validate_assignment, validate_assignment_to_modify):
        self.__repo_student = repo_student
        self.__repo_assignment = repo_assignment
        self.__repo_grades = repo_grades
        self.__validate_student = validate_student
        self.__validate_student_for_modify = validate_student_for_modify
        self.__validate_assignment = validate_assignment
        self.__validate_assignment_to_modify = validate_assignment_to_modify

    def add_student_service(self, student_id, student_name, student_group):
        student = Student(student_id, student_name, student_group)
        self.__validate_student.validate(student)
        self.__repo_student.add_student_repo_student(student)
        return student

    def generate_students(self):
        self.add_student_service(1, "Pop Mihai", 911)
        self.add_student_service(2, "Mitrea Ioan", 918)
        self.add_student_service(3, "Petre Emanuel", 928)
        self.add_student_service(4, "Popescu Eugen", 913)
        self.add_student_service(5, "Olteanu Daniel", 933)
        self.add_student_service(6, "Dobrescu Marta", 911)
        self.add_student_service(7, "Mihai Andrei", 915)
        self.add_student_service(8, "Peter Oana", 915)
        self.add_student_service(9, "Oniga Lucian", 924)
        self.add_student_service(10, "Pasca Stefan", 925)

    def get_all_students_service(self):
        return self.__repo_student.get_all_students_repo()

    def get_all_students_service_keys(self):
        return self.__repo_student.get_all_students_repo_keys()

    def get_all_grades_service(self):
        return self.__repo_grades.get_all_grades_repo()

    def students_size_service(self):
        return self.__repo_student.student_size_repo()

    def modify_student_service(self, student_id_to_modify, new_student_name, new_student_group):
        student = Student(student_id_to_modify, new_student_name, new_student_group)
        self.__validate_student_for_modify.validate_for_modify(student)
        self.__repo_student.modify_student_repo(student)
        return student

    def remove_student_service(self, student_id_to_remove):
        self.__repo_student.remove_student_repo(student_id_to_remove)

    def add_assignment_service(self, assignment_id, assignment_description, assignment_deadline):
        assignment = Assignment(assignment_id, assignment_description, assignment_deadline)
        self.__validate_assignment.validate(assignment)
        self.__repo_assignment.add_assignment_repo_assignment(assignment)
        return assignment

    def generate_assignments(self):
        self.add_assignment_service(1, "A1", date(2022, 10, 10))
        self.add_assignment_service(2, "A2", date(2022, 10, 17))
        self.add_assignment_service(3, "A3", date(2022, 10, 21))
        self.add_assignment_service(4, "A4", date(2022, 10, 31))
        self.add_assignment_service(5, "A5", date(2022, 11, 10))
        self.add_assignment_service(6, "A6", date(2022, 11, 17))
        self.add_assignment_service(7, "A7", date(2022, 11, 21))
        self.add_assignment_service(8, "A8", date(2022, 11, 30))
        self.add_assignment_service(9, "A9", date(2022, 12, 10))
        self.add_assignment_service(10, "A10", date(2022, 12, 17))

    def get_all_assignments_service(self):
        return self.__repo_assignment.get_all_assignments_repo()

    def get_all_assignments_service_keys(self):
        return self.__repo_assignment.get_all_assignments_repo_keys()

    def assignments_size_service(self):
        return self.__repo_assignment.assignments_size_repo()

    def modify_assignment_service(self, assignment_id_to_modify, new_assignment_description, new_assignment_deadline):
        assignment = Assignment(assignment_id_to_modify, new_assignment_description, new_assignment_deadline)
        self.__validate_assignment_to_modify.validate_for_modify(assignment)
        self.__repo_assignment.modify_assignment_repo_assignment(assignment)
        return assignment

    def remove_assignment_service(self, assignment_id_to_remove):
        self.__repo_assignment.remove_assignment_repo(assignment_id_to_remove)

    def give_assignment_to_a_student_service(self, assignment_id_to_give, student_id_to_give_assignment):
        grade = Grade(assignment_id_to_give, student_id_to_give_assignment, 0)
        self.__repo_grades.give_assignment_to_a_student_repo(grade)

    def give_assignment_to_a_group_of_students_service(self, assignment_id_to_give, group_to_give_the_assignment):
        for student in self.__repo_student.get_all_students_repo():
            student_group = student.get_student_group()
            if student_group == group_to_give_the_assignment:
                student_id = student.get_student_id()
                grade = Grade(assignment_id_to_give, student_id, 0)
                self.__repo_grades.give_assignment_to_a_student_repo(grade)

    def get_the_id_of_the_ungraded_assignments_of_the_student_service(self, id_of_student_to_grade):
        ungraded_assignments_of_the_student = []
        for grade in self.__repo_grades.get_all_grades_repo():
            grade_value = grade.get_grade_value()
            id_student = grade.get_student_id_from_grade()
            if id_student == id_of_student_to_grade and grade_value == 0:
                id_assignment = grade.get_assignment_id_from_grade()
                ungraded_assignments_of_the_student.append(id_assignment)
        return ungraded_assignments_of_the_student

    def grade_the_assignment_service(self, id_of_assignment_to_grade, id_of_student_to_grade, grade_of_the_assignment):
        grade = Grade(id_of_assignment_to_grade, id_of_student_to_grade, grade_of_the_assignment)
        self.__repo_grades.grade_the_assignment_repo(grade)

    def remove_assignments_for_the_student_service(self, student_id_to_remove):
        for grade in self.__repo_grades.get_all_grades_repo():
            student_id = grade.get_student_id_from_grade()
            student_id = int(student_id)
            if student_id == student_id_to_remove:
                self.__repo_grades.remove_assignments_for_the_student_repo(student_id)

    def remove_grades_for_assignment_service(self, assignment_id_to_remove):
        assignment_id_to_remove = int(assignment_id_to_remove)
        for grade in self.__repo_grades.get_all_grades_repo():
            assignment_id = grade.get_assignment_id_from_grade()
            assignment_id = int(assignment_id)
            if assignment_id == assignment_id_to_remove:
                self.__repo_grades.remove_grades_for_the_assignment_repo(assignment_id)

    def assignments_ordered_descending_by_grade_service(self, assignment_id):
        grades_for_the_assignment = []
        for grade in self.__repo_grades.get_all_grades_repo():
            assignment_id_from_grade = grade.get_assignment_id_from_grade()
            if assignment_id_from_grade == assignment_id:
                grades_for_the_assignment.append(grade)
        for i in range(len(grades_for_the_assignment)):
            for j in range(0, len(grades_for_the_assignment) - i - 1):
                if grades_for_the_assignment[j].get_grade_value() < grades_for_the_assignment[j + 1].get_grade_value():
                    auxiliary = grades_for_the_assignment[j]
                    grades_for_the_assignment[j] = grades_for_the_assignment[j + 1]
                    grades_for_the_assignment[j + 1] = auxiliary
        return [grade for grade in grades_for_the_assignment]

    def students_who_are_late_in_handing_assignments_service(self):
        students_late_in_handing_assignments = []
        for grade in self.__repo_grades.get_all_grades_repo():
            grade_value = grade.get_grade_value()
            assignment_id_from_grade = grade.get_assignment_id_from_grade()
            student_id_from_grade = grade.get_student_id_from_grade()
            for assignment in self.__repo_assignment.get_all_assignments_repo():
                assignment_id_from_assignment = assignment.get_assignment_id()
                assignment_deadline = assignment.get_assignment_deadline()
                if assignment_id_from_grade == assignment_id_from_assignment and grade_value == 0 and assignment_deadline < date.today():
                    students_late_in_handing_assignments.append(student_id_from_grade)
        return students_late_in_handing_assignments

    def students_with_the_best_school_situation_service(self):
        students = []
        for student in self.__repo_student.get_all_students_repo():
            sum_of_grades = 0
            number_of_grades = 0
            student_id = int(student.get_student_id())
            for grade in self.__repo_grades.get_all_grades_repo():
                student_id_from_grade = int(grade.get_student_id_from_grade())
                if student_id == student_id_from_grade:
                    grade_value = int(grade.get_grade_value())
                    sum_of_grades = sum_of_grades + grade_value
                    number_of_grades = number_of_grades + 1
            if number_of_grades != 0:
                average_grade = float(sum_of_grades / number_of_grades)
                student_and_grades_sum = [student_id, average_grade]
                students.append(student_and_grades_sum)
        students = sorted(students, key=lambda x: x[1], reverse=True)
        return students
