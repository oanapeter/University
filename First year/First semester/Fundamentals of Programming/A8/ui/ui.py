from datetime import date

from src.errors.repo_error import RepoError


class UI:

    def __init__(self, service):
        self.__service = service

    @staticmethod
    def print_menu():
        print()
        print("         Student Lab Assignment App")
        print()
        print("1.Add")
        print("2.Remove")
        print("3.Update")
        print("4.List")
        print("5.Give an assignment for a student or a group of students")
        print("6.Grade a student for a given assignment")
        print("7.Statistics")
        print("x.Exit")

    @staticmethod
    def print_menu_add():
        print("1.Add a student")
        print("2.Add an assignment")

    @staticmethod
    def print_menu_remove():
        print("1.Remove a student")
        print("2.Remove an assignment")

    @staticmethod
    def print_menu_modify():
        print("1.Modify a student")
        print("2.Modify an assignment")

    @staticmethod
    def print_menu_list():
        print("1.List students")
        print("2.List assignments")

    @staticmethod
    def print_menu_statistics():
        print("1.All students who received a given assignment, ordered descending by grade.")
        print("2.All students who are late in handing in at least one assignment.")
        print(
            "3.Students with the best school situation, sorted in descending order of the average grade received for all graded assignments.")

    @staticmethod
    def print_menu_student_or_group_of_students():
        print("1.Give an assignment to a student.")
        print("2.Give an assignment to a group of students.")

    def add_student_ui(self):
        student_id = int(input("Give student id: "))
        student_name = input("Give student name: ")
        student_group = int(input("Give student group: "))
        self.__service.add_student_service(student_id, student_name, student_group)
        print("Student added successfully!")

    def display_students_ui(self):
        if self.__service.students_size_service() == 0:
            raise ValueError("No students!")
        students = self.__service.get_all_students_service()
        if students is not None:
            for student in students:
                print(student)

    def modify_student_ui(self):
        student_id_to_modify = int(input("Give the id of the student that you want to modify: "))
        new_student_name = input("Give the new name of the student: ")
        new_student_group = int(input("Give the new group of the student: "))
        self.__service.modify_student_service(student_id_to_modify, new_student_name, new_student_group)
        print("Student was modified successfully!")

    def remove_student_ui(self):
        initial_student_service_size = self.__service.students_size_service()
        student_id_to_remove = int(input("Give the id of the student that you want to remove: "))
        self.__service.remove_student_service(student_id_to_remove)
        if initial_student_service_size == self.__service.students_size_service:
            print("Id not found!")
        self.__service.remove_assignments_for_the_student_service(student_id_to_remove)
        print("Student with id " + str(student_id_to_remove) + " was removed.")

    def add_assignment_ui(self):
        assignment_id = int(input("Give assignment id: "))
        assignment_description = input("Give assignment description: ")
        assignment_year_for_deadline = int(input("Give year for assignment deadline: "))
        assignment_month_for_deadline = int(input("Give month for assignment deadline: "))
        assignment_day_for_deadline = int(input("Give day for assignment deadline: "))
        assignment_deadline = date(assignment_year_for_deadline, assignment_month_for_deadline,
                                   assignment_day_for_deadline)
        self.__service.add_assignment_service(assignment_id, assignment_description, assignment_deadline)
        print("Assignment added successfully!")

    def display_assignments_ui(self):
        if self.__service.assignments_size_service() == 0:
            raise ValueError("No assignments!")
        assignments = self.__service.get_all_assignments_service()
        if assignments is not None:
            for assignment in assignments:
                print(assignment)

    def modify_assignment_ui(self):
        assignment_id_to_modify = int(input("Give the id of the assignment that you want to modify: "))
        new_assignment_description = input("Give assignment description: ")
        new_assignment_year_for_deadline = int(input("Give new year for assignment deadline: "))
        new_assignment_month_for_deadline = int(input("Give new month for assignment deadline: "))
        new_assignment_day_for_deadline = int(input("Give new day for assignment deadline: "))
        new_assignment_deadline = date(new_assignment_year_for_deadline, new_assignment_month_for_deadline,
                                       new_assignment_day_for_deadline)
        self.__service.modify_assignment_service(assignment_id_to_modify, new_assignment_description,
                                                 new_assignment_deadline)
        print("Assignment was modified successfully!")

    def remove_assignment_ui(self):
        initial_assignment_service_size = self.__service.assignments_size_service()
        assignment_id_to_remove = int(input("Give the id of the assignment that you want to remove: "))
        self.__service.remove_assignment_service(assignment_id_to_remove)
        if initial_assignment_service_size == self.__service.assignments_size_service:
            print("Id not found!")
        self.__service.remove_grades_for_assignment_service(assignment_id_to_remove)
        print("Assignment with id " + str(assignment_id_to_remove) + " was removed.")

    def give_assignment_to_a_student_ui(self):
        assignment_id_to_give = int(input("Give the id of the assignment that you want to give: "))
        if assignment_id_to_give not in self.__service.get_all_assignments_service_keys():
            print("This assignment doesn't exist!")
        else:
            student_id_to_give_assignment = int(
                input("Give the id of the student that you want to give an assignment: "))
            if student_id_to_give_assignment not in self.__service.get_all_students_service_keys():
                print("The student doesn't exist!")
            else:
                check_if_the_assignment_is_not_already_given_to_that_student = True
                for grade in self.__service.get_all_grades_service():
                    assignment_id_from_grade = grade.get_assignment_id_from_grade()
                    student_id_from_grade = grade.get_student_id_from_grade()
                    if student_id_to_give_assignment == student_id_from_grade and assignment_id_from_grade == assignment_id_to_give:
                        check_if_the_assignment_is_not_already_given_to_that_student = False
                if check_if_the_assignment_is_not_already_given_to_that_student:
                    self.__service.give_assignment_to_a_student_service(assignment_id_to_give,
                                                                        student_id_to_give_assignment)
                    print("Assignment with id: " + str(
                        assignment_id_to_give) + " was given to the student with the id: " + str(
                        student_id_to_give_assignment))
                else:
                    print("Assignment is already given to the student!")

    def display_grades_ui(self):
        grades = self.__service.get_all_grades_service()
        for grade in grades:
            print(grade)

    def give_assignment_to_a_group_of_students(self):
        assignment_id_to_give = int(input("Give the id of the assignment that you want to give: "))
        if assignment_id_to_give not in self.__service.get_all_assignments_service_keys():
            print("This assignment doesn't exist!")
        else:
            group_to_give_the_assignment = int(input("Give the group of students that will receive the assignment: "))
            check_if_the_assignment_was_already_given_to_the_group = False
            for student in self.__service.get_all_students_service():
                if group_to_give_the_assignment == student.get_student_group():
                    check_if_the_assignment_was_already_given_to_the_group = True
            if check_if_the_assignment_was_already_given_to_the_group:
                self.__service.give_assignment_to_a_group_of_students_service(assignment_id_to_give,
                                                                              group_to_give_the_assignment)
                print("Assignment with id: " + str(assignment_id_to_give) + " was given to group: " + str(
                    group_to_give_the_assignment))
            elif not check_if_the_assignment_was_already_given_to_the_group:
                print("The group doesn't exist.")

    def grade_student_for_an_assignment_ui(self):
        id_of_student_to_grade = int(input("Give the id of the student that you want to grade: "))
        ungraded_assignments_of_the_student = self.__service.get_the_id_of_the_ungraded_assignments_of_the_student_service(
            id_of_student_to_grade)
        if len(ungraded_assignments_of_the_student) == 0:
            print("No assignments to grade for the student!")
        else:
            for grade in ungraded_assignments_of_the_student:
                print("Ungraded assignment id: " + str(grade))
            id_of_assignment_to_grade = int(input("Give the id of the assignment that you want to grade: "))
            if id_of_assignment_to_grade not in ungraded_assignments_of_the_student:
                print("Incorrect id!")
            else:
                grade_of_the_assignment = int(input("Give the grade for the assignment: "))
                self.__service.grade_the_assignment_service(id_of_assignment_to_grade, id_of_student_to_grade,
                                                            grade_of_the_assignment)

    def assignments_ordered_descending_by_grade_ui(self):
        assignment_id = int(input("Give the id of the assignment to see it's grades in descending order: "))
        list_of_assignments = self.__service.assignments_ordered_descending_by_grade_service(assignment_id)
        for assignment in list_of_assignments:
            print(assignment)

    def students_who_are_late_in_handing_assignments_ui(self):
        list_of_students_who_are_late_in_handing_assignments = self.__service.students_who_are_late_in_handing_assignments_service()
        if len(list_of_students_who_are_late_in_handing_assignments) == 0:
            print("There are no students who are late in handing assignments!")
        else:
            for assignment in list_of_students_who_are_late_in_handing_assignments:
                print("Student id late in handing an assignment: " + str(assignment))

    def students_with_the_best_school_situation_ui(self):
        list_with_students_with_best_school_situation = self.__service.students_with_the_best_school_situation_service()
        for student in list_with_students_with_best_school_situation:
            print(f"Student with id: {student[0]} has the average grade {student[1]}")

    def start(self):
        while True:
            self.print_menu()
            add_a_student_or_an_assignment = "1"
            add_a_student = "1"
            add_an_assignment = "2"
            remove_a_student_or_an_assignment = "2"
            remove_a_student = "1"
            remove_an_assignment = "2"
            modify_a_student_or_an_assignment = "3"
            modify_a_student = "1"
            modify_an_assignment = "2"
            list_students_or_assignments = "4"
            list_students = "1"
            list_assignments = "2"
            give_an_assignment_to_a_student_or_a_group = "5"
            give_assignment_to_a_student = "1"
            give_assignment_to_a_group = "2"
            grade_student_for_an_assignment = "6"
            statistics = "7"
            assignments_ordered_descending_by_grade = "1"
            students_who_are_late_for_assignments = "2"
            students_with_best_school_situation = "3"
            break_program = "x"
            option1 = input(">>")
            try:
                if option1 == add_a_student_or_an_assignment:
                    self.print_menu_add()
                    option2 = input(">>")
                    if option2 == add_a_student:
                        self.add_student_ui()
                    elif option2 == add_an_assignment:
                        self.add_assignment_ui()
                elif option1 == remove_a_student_or_an_assignment:
                    self.print_menu_remove()
                    option2 = input(">>")
                    if option2 == remove_a_student:
                        self.remove_student_ui()
                    elif option2 == remove_an_assignment:
                        self.remove_assignment_ui()
                elif option1 == modify_a_student_or_an_assignment:
                    self.print_menu_modify()
                    option2 = input(">>")
                    if option2 == modify_a_student:
                        self.modify_student_ui()
                    elif option2 == modify_an_assignment:
                        self.modify_assignment_ui()
                elif option1 == list_students_or_assignments:
                    self.print_menu_list()
                    option2 = input(">>")
                    if option2 == list_students:
                        self.display_students_ui()
                    elif option2 == list_assignments:
                        self.display_assignments_ui()
                elif option1 == give_an_assignment_to_a_student_or_a_group:
                    self.print_menu_student_or_group_of_students()
                    option2 = input(">>")
                    if option2 == give_assignment_to_a_student:
                        self.give_assignment_to_a_student_ui()
                        self.display_grades_ui()
                    elif option2 == give_assignment_to_a_group:
                        self.give_assignment_to_a_group_of_students()
                elif option1 == grade_student_for_an_assignment:
                    self.grade_student_for_an_assignment_ui()
                elif option1 == statistics:
                    self.print_menu_statistics()
                    option2 = input(">>")
                    if option2 == assignments_ordered_descending_by_grade:
                        self.assignments_ordered_descending_by_grade_ui()
                    elif option2 == students_who_are_late_for_assignments:
                        self.students_who_are_late_in_handing_assignments_ui()
                    elif option2 == students_with_best_school_situation:
                        self.students_with_the_best_school_situation_ui()
                elif option1 == break_program:
                    break
                else:
                    print("Invalid option!")
            except ValueError as ve:
                print("Error: ", ve)
            except RepoError as re:
                print("Error:", re)
