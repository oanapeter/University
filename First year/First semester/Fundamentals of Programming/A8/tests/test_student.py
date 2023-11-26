import unittest

from src.errors.repo_error import RepoError
from src.errors.validation_errors import ValidError
from src.repository.repo_assignment import RepoAssignment
from src.repository.repo_grade import RepoGrades
from src.repository.repo_student import RepoStudent
from src.services.service import Service
from src.validations.assignment_validation import AssignmentValidation
from src.validations.assignment_validation_for_modify import AssignmentValidationForModify
from src.validations.student_validation import StudentValidation
from src.validations.student_validation_for_modify import StudentValidationForModify


class TestStudent(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_add_student(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        self.assertEqual(service.students_size_service(), 0)
        student = service.add_student_service(1, "Peter Oana", 915)
        self.assertEqual(service.students_size_service(), 1)
        self.assertEqual(student.get_student_id(), 1)
        self.assertEqual(student.get_student_name(), "Peter Oana")
        self.assertEqual(student.get_student_group(), 915)
        try:
            service.add_student_service(-1, "Peter Oana", 915)
            assert False
        except ValidError as ve:
            self.assertEqual(str(ve), "Invalid id!")
        self.assertEqual(service.students_size_service(), 1)
        try:
            service.add_student_service(1, "Peter Oana", 915)
            assert False
        except RepoError as re:
            self.assertEqual(str(re), "Existent student!")

    def test_remove_student(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        self.assertEqual(service.students_size_service(), 0)
        service.add_student_service(1, "Peter Oana", 915)
        self.assertEqual(service.students_size_service(), 1)
        service.remove_student_service(1)
        self.assertEqual(service.students_size_service(), 0)
        try:
            service.remove_student_service(1)
            assert False
        except RepoError as re:
            self.assertEqual(str(re), "Inexistent student!")

    def test_modify_student(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        student = service.add_student_service(1, "Peter Oana", 915)
        self.assertEqual(service.students_size_service(), 1)
        self.assertEqual(student.get_student_id(), 1)
        self.assertEqual(student.get_student_name(), "Peter Oana")
        student = service.modify_student_service(1, "Peter Miruna", 925)
        self.assertEqual(student.get_student_name(), "Peter Miruna")
        self.assertEqual(student.get_student_group(), 925)
