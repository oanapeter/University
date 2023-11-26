from src.errors.repo_error import RepoError


class Repo:
    def __init__(self):
        self.__books = {}
        self.__history = []

    def add_book_repo(self, book_to_be_added):
        isbn_book = book_to_be_added.get_book_isbn()
        if isbn_book in self.__books:
            raise RepoError("Existent book!")
        self.__books[isbn_book] = book_to_be_added
        list_of_dictionaries = []
        for book in self.__books:
            list_of_dictionaries.append(self.__books[book])
        self.__history.append(list_of_dictionaries)
        return book_to_be_added

    def remove_book_repo(self, isbn):
        if isbn not in self.__books:
            raise RepoError("Inexistent book!")
        del self.__books[isbn]
        list_of_dictionaries = []
        for book in self.__books:
            list_of_dictionaries.append(self.__books[book])
        self.__history.append(list_of_dictionaries)

    def get_all_books_undo_repo(self):
        self.__history.pop()
        list_of_dictionaries = []
        for book in self.__history[len(self.__history) - 1]:
            list_of_dictionaries.append(book)
        return self.__books

    def modify_book(self, new_book):
        isbn = new_book.get_book_isbn()
        if isbn not in self.__books:
            raise RepoError("Inexistent book!")
        self.__books[isbn] = new_book

    def search_book_by_isbn(self, isbn):
        if isbn not in self.__books:
            raise RepoError("Inexistent book!")
        return self.__books[isbn]

    def size(self):
        return len(self.__books)

    def get_all_books_repo(self):
        return [x for x in self.__books.values()]

    def clean_memory(self):
        self.__books = {}
        return self.__books
