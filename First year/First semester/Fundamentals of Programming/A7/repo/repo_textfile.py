from src.domain.book import Book
from src.repo.repo import Repo


class RepoTextFile(Repo):
    def __init__(self, file_path):
        self.__file_path = file_path
        super().__init__()
        self.clean_memory()
        self._write_data_to_file()
        self._read_data_from_file()

    def _read_data_from_file(self):
        self._books = self.clean_memory()
        with open(self.__file_path, "rt") as f:
            lines = f.readlines()
            f.close()
            for line in lines:
                if line != "":
                    line = line.strip()
                    parts = line.split(",")
                    isbn = int(parts[0].strip())
                    author = parts[1].strip()
                    title = parts[2].strip()
                    book = Book(isbn, author, title)
                    self._books[isbn] = book

    def _write_data_to_file(self):
        with open(self.__file_path, "wt") as f:
            for book in self.get_all_books_repo():
                f.write(str(book) + "\n")
            f.close()

    def add_book_repo(self, book_to_be_added):
        self._read_data_from_file()
        book = super().add_book_repo(book_to_be_added)
        self._write_data_to_file()
        return book

    def remove_book_repo(self, isbn):
        self._read_data_from_file()
        book = super().remove_book_repo(isbn)
        self._write_data_to_file()
        return book

    def modify_book(self, new_book):
        self._read_data_from_file()
        book = super().modify_book(new_book)
        self._write_data_to_file()
        return book

    def search_book_by_isbn(self, isbn):
        self._read_data_from_file()
        book = super().search_book_by_isbn(isbn)
        return book

    def get_all_books_repo(self):
        return super().get_all_books_repo()

    def size(self):
        return super().size()

    def get_all_books_undo_repotext(self):
        books = super().get_all_books_undo_repo()
        self._write_data_to_file()
        return books
