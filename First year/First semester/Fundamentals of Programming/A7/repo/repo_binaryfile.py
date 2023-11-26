import pickle

from src.repo.repo import Repo


class RepoBinaryFile(Repo):
    def __init__(self, file_path):
        self.__file_path = file_path
        super().__init__()
        self._write_data_to_file()
        self._read_data_from_file()

    def _write_data_to_file(self):
        with open(self.__file_path, "wb") as f:
            pickle.dump(super().get_all_books_repo(), f)
            f.close()

    def _read_data_from_file(self):
        with open(self.__file_path, "rb") as f:
            books_dictionary = pickle.load(f)
            f.close()
        for book in books_dictionary:
            super().add_book_repo(book)

    def add_book_repo(self, book_to_be_added):
        book = super().add_book_repo(book_to_be_added)
        self._write_data_to_file()
        return book

    def remove_book_repo(self, isbn):
        book = super().remove_book_repo(isbn)
        self._write_data_to_file()
        return book

    def modify_book(self, new_book):
        book = super().modify_book(new_book)
        self._write_data_to_file()
        return book

    def search_book_by_isbn(self, isbn):
        book = super().search_book_by_isbn(isbn)
        return book

    def get_all_books_undo_repobinary(self):
        book = super().get_all_books_undo_repo()
        self._write_data_to_file()
        return book

    def get_all_books_repo(self):
        return super().get_all_books_repo()

    def size(self):
        return super().size()
