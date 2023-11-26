from src.errors.validation_errors import ValidError


class AssignmentValidation:
    def __init__(self):
        pass

    @staticmethod
    def validate(assignment):
        errors = ""
        if assignment.get_assignment_id() < 0:
            errors += "Invalid id!"
        if assignment.get_assignment_description() == "":
            errors += "Invalid description!"
        if len(errors) > 0:
            raise ValidError(errors)
