from src.repository import repo_student
from src.repository.repo_assignment import RepoAssignment
from src.repository.repo_grade import RepoGrades
from src.repository.repo_student import RepoStudent
from src.services.service import Service
from src.ui.ui import UI
from src.validations.assignment_validation import AssignmentValidation
from src.validations.assignment_validation_for_modify import AssignmentValidationForModify
from src.validations.student_validation import StudentValidation
from src.validations.student_validation_for_modify import StudentValidationForModify


def main():
    repo_student = RepoStudent()
    repo_assignment = RepoAssignment()
    repo_grades = RepoGrades()
    validate_student = StudentValidation()
    validate_student_for_modify = StudentValidationForModify()
    validate_assignment = AssignmentValidation()
    validate_assignment_for_modify = AssignmentValidationForModify()
    service = Service(repo_student, repo_assignment, repo_grades, validate_student, validate_student_for_modify, validate_assignment, validate_assignment_for_modify)
    service.generate_students()
    service.generate_assignments()
    ui = UI(service)
    ui.start()


main()
