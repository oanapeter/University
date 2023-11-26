class Book:
    def __init__(self, isbn, author, title):
        self.__isbn = isbn
        self.__author = author
        self.__title = title

    def get_book_isbn(self):
        return self.__isbn

    def get_book_author(self):
        return self.__author

    def get_book_title(self):
        return self.__title
    
    def __str__(self):
        return f"{self.__isbn},{self.__author},{self.__title}"

