from hangman.valid_error import ValidError


class Validate:
    @staticmethod
    def validate(sentence):
        errors = ""

        parts = sentence.strip().split()
        if len(parts) < 1:
            errors += "Sentence too short!"
        for i in range(len(parts)):
            if len(parts[i]) < 3:
                errors += "Words too short!"
        if len(errors) > 0:
            raise ValidError(errors)