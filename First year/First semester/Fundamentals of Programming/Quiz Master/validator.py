class Validator:
    @staticmethod
    def validate(question):
        errors = ''
        difficulty = question.get_difficulty_level()
        if difficulty != 'easy' and difficulty != 'medium' and difficulty!='hard':
            errors += 'Incorrect difficulty'
        if len(errors) !=0:
            raise ValidationError(errors)


class ValidationError(Exception):
    pass