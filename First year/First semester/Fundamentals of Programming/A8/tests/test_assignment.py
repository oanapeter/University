import unittest
from datetime import date

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


class TestAssignment(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_add_assignment(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        self.assertEqual(service.assignments_size_service(), 0)
        assignment = service.add_assignment_service(1, "A1", date(2022, 10, 10))
        self.assertEqual(assignment.get_assignment_id(), 1)
        self.assertEqual(assignment.get_assignment_description(), "A1")
        self.assertEqual(assignment.get_assignment_deadline(), date(2022, 10, 10))
        try:
            service.add_assignment_service(-1, "A1", date(2022, 10, 10))
            assert False
        except ValidError as ve:
            self.assertEqual(str(ve), "Invalid id!")
        self.assertEqual(service.assignments_size_service(), 1)
        try:
            service.add_assignment_service(1, "A1", date(2022, 10, 10))
            assert False
        except RepoError as ve:
            self.assertEqual(str(ve), "Existent assignment!")

    def test_remove_assignment(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        self.assertEqual(service.assignments_size_service(), 0)
        service.add_assignment_service(1, "A1", date(2022, 10, 10))
        self.assertEqual(service.assignments_size_service(), 1)
        service.remove_assignment_service(1)
        self.assertEqual(service.assignments_size_service(), 0)
        try:
            service.remove_assignment_service(1)
            assert False
        except RepoError as re:
            self.assertEqual(str(re), "Inexistent assignment!")

    def test_modify_assignment(self):
        validation_student = StudentValidation()
        validation_assignment = AssignmentValidation()
        validation_student_for_modify = StudentValidationForModify()
        validation_assignment_for_modify = AssignmentValidationForModify()
        repo_student = RepoStudent()
        repo_assignment = RepoAssignment()
        repo_grades = RepoGrades()
        service = Service(repo_student, repo_assignment, repo_grades, validation_student, validation_student_for_modify,
                          validation_assignment, validation_assignment_for_modify)
        assignment = service.add_assignment_service(1, "A1", date(2022, 10, 10))
        self.assertEqual(service.assignments_size_service(), 1)
        self.assertEqual(assignment.get_assignment_id(), 1)
        self.assertEqual(assignment.get_assignment_description(), "A1")
        self.assertEqual(assignment.get_assignment_deadline(), date(2022, 10, 10))
        assignment = service.modify_assignment_service(1, "Assignment1", date(2022, 11, 11))
        self.assertEqual(assignment.get_assignment_description(), "Assignment1")
        self.assertEqual(assignment.get_assignment_deadline(), date(2022, 11, 11))
