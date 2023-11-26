from src.errors.validation_error import ValidError


class BookValidation:

    def __init__(self):
        pass

    @staticmethod
    def validate(book):
        errors = ""
        if book.get_book_isbn() < 0:
            errors += "Invalid isbn!"
        if book.get_book_author() == "":
            errors += "Invalid author!"
        if book.get_book_title() == "":
            errors += "Invalid title!"
        if len(errors) > 0:
            raise ValidError(errors)
