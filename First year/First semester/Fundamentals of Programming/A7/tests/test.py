from src.errors.repo_error import RepoError
from src.errors.validation_error import ValidError
from src.repo.repo import Repo
from src.services.service import Service
from src.validations.validation import BookValidation


class Test:
    def run_all(self):
        self.test_add()

    def test_add(self):
        validation_book = BookValidation()
        repo = Repo()
        service = Service(validation_book, repo)
        assert service.size_service() == 0
        isbn = 1
        author = "Mihai Eminescu"
        title = "Poezii"
        service.add_book_service(isbn, author, title)
        assert service.size_service() == 1
        try:
            isbn = 1
            author = "Mihai Eminescu"
            title = "Poezii"
            service.add_book_service(isbn, author, title)
            assert False
        except RepoError as re:
            assert str(re) == "Existent book!"
        assert service.size_service() == 1
        try:
            isbn = -1
            author = "Mihai Eminescu"
            title = "Poezii"
            service.add_book_service(isbn, author, title)
            assert False
        except ValidError as ve:
            assert str(ve) == "Invalid isbn!"
        assert service.size_service() == 1
        try:
            isbn = 12
            author = ""
            title = "Poezii"
            service.add_book_service(isbn, author, title)
            assert False
        except ValidError as ve:
            assert str(ve) == "Invalid author!"
        assert service.size_service() == 1
        try:
            isbn = 12
            author = "Mircea Eliade"
            title = ""
            service.add_book_service(isbn, author, title)
            assert False
        except ValidError as ve:
            assert str(ve) == "Invalid title!"
        assert service.size_service() == 1
