from src.repo.repo import Repo
from src.repo.repo_binaryfile import RepoBinaryFile
from src.repo.repo_textfile import RepoTextFile
from src.services.service import Service
from src.tests.test import Test
from src.ui.UI import UI
from src.validations.validation import BookValidation


def start():
    test = Test()
    test.run_all()
    validation_book = BookValidation()
    repo_type = input("Choose the type of the repository (memory/text/binary): ")
    if repo_type == "memory":
        repo = Repo()
    if repo_type == "text":
        repo = RepoTextFile("books.txt")
    if repo_type == "binary":
        repo = RepoBinaryFile("books.bin")
    service = Service(validation_book, repo)
    service.generate_books()
    ui = UI(service)
    ui.run()


start()
