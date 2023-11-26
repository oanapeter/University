from src.errors.repo_error import RepoError
from src.errors.validation_error import ValidError


class UI:
    def __init__(self, service):
        self.__service = service

    @staticmethod
    def menu():
        print("1.Add a book.")
        print("2.Display the books.")
        print("3.Filter the books.")
        print("4.Undo.")
        print("5.Exit.")

    def add_book_UI(self):
        isbn = int(input("Give book's isbn: "))
        author = input("Give book's author: ")
        title = input("Give book's title: ")
        self.__service.add_book_service(isbn, author, title)
        print("Book added successfully!")

    def print_list_UI(self):
        if self.__service.size_service() == 0:
            raise ValueError("No books!")
        books = self.__service.get_all_books_service()
        if books is not None:
            for book in books:
                print(book)

    def filter_books_by_given_word_UI(self):
        initial_service_size = self.__service.size_service()
        word_for_filtering = input("Give a word for filtering: ")
        self.__service.filter_books_by_given_word(word_for_filtering)
        if initial_service_size == self.__service.size_service():
            raise ValueError("No title found!")
        print("Books with the given word as first word from title were deleted.")

    def undo_books_UI(self):
        books = self.__service.get_all_books_undo_service()
        if len(books) == 0:
            print("No books!")
            return
        for book in books:
            print(book)

    def run(self):
        while True:
            self.menu()
            option = input("Choose your option: ")
            try:
                if option == '1':
                    self.add_book_UI()
                elif option == '2':
                    self.print_list_UI()
                elif option == '3':
                    self.filter_books_by_given_word_UI()
                elif option == '4':
                    self.undo_books_UI()
                elif option == '5':
                    break
                else:
                    print("Bad command!")
            except ValueError as v:
                print("ValueError: ", v)
            except ValidError as ve:
                print("ValidError:", ve)
            except RepoError as re:
                print("RepoError: ", re)
