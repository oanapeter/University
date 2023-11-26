from src.errors.validation_errors import ValidError


class AssignmentValidationForModify:
    def __init__(self):
        pass

    @staticmethod
    def validate_for_modify(assignment):
        errors = ""
        if assignment.get_assignment_description() == "":
            errors += "Invalid description!"
        if len(errors) > 0:
            raise ValidError(errors)
