from src.errors.validation_errors import ValidError


class StudentValidation:
    def __init__(self):
        pass

    @staticmethod
    def validate(student):
        errors = ""
        if student.get_student_id() < 0:
            errors += "Invalid id!"
        if student.get_student_name() == "":
            errors += "Invalid name!"
        if student.get_student_group() < 0:
            errors += "Invalid group!"
        if len(errors) > 0:
            raise ValidError(errors)
