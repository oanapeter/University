from src.domain.book import Book


class Service:
    def __init__(self, validation_book, repo):
        self.__validation_book = validation_book
        self.__repo = repo

    def add_book_service(self, isbn, author, title):
        book = Book(isbn, author, title)
        self.__validation_book.validate(book)
        self.__repo.add_book_repo(book)

    def generate_books(self):
        self.add_book_service(1, "Mihai Eminescu", "Poezii")
        self.add_book_service(2, "Mihail Sadoveanu", "Baltagul")
        self.add_book_service(3, "I. L. Caragiale", "O scrisoare pierduta")
        self.add_book_service(4, "Ioan Slavici", "Moara cu noroc")
        self.add_book_service(5, "Ion Creanga", "Plumb din copilarie")
        self.add_book_service(6, "Ion Barbu", "Riga Crypto si Lapona Enigel")
        self.add_book_service(7, "Liviu Rebreanu", "Ion")
        self.add_book_service(8, "Marin Preda", "Morometii")
        self.add_book_service(9, "George Bacovia", "Plumb")
        self.add_book_service(10, "Tudor Arghezi", "Testament")

    def get_all_books_service(self):
        return self.__repo.get_all_books_repo()

    def size_service(self):
        return self.__repo.size()

    def filter_books_by_given_word(self, word_for_filtering):
        for book in self.__repo.get_all_books_repo():
            title = book.get_book_title()
            tokens = title.strip().split()
            first_word_of_title = tokens[0].strip().lower()
            if first_word_of_title == word_for_filtering:
                self.__repo.remove_book_repo(book.get_book_isbn())

    def get_all_books_undo_service(self):
        return self.__repo.get_all_books_undo_repo()
