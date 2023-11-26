class Assignment:
    def __init__(self, assignment_id, assignment_description, assignment_deadline):
        self.__assignment_id = assignment_id
        self.__assignment_description = assignment_description
        self.__assignment_deadline = assignment_deadline

    def get_assignment_id(self):
        return self.__assignment_id

    def get_assignment_description(self):
        return self.__assignment_description

    def get_assignment_deadline(self):
        return self.__assignment_deadline

    def set_assignment_description(self, new_description):
        self.__assignment_description = new_description

    def set_assignment_deadline(self, new_deadline):
        self.__assignment_deadline = new_deadline

    def __str__(self):
        return f"Assignment with id: {self.__assignment_id} has the description: {self.__assignment_description} and the deadline: {self.__assignment_deadline}"
