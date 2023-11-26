from src.errors.repo_error import RepoError


class RepoAssignment:
    def __init__(self):
        self.__assignments = {}

    def add_assignment_repo_assignment(self, assignment_to_be_added):
        assignment_id = assignment_to_be_added.get_assignment_id()
        if assignment_id in self.__assignments:
            raise RepoError("Existent assignment!")
        self.__assignments[assignment_id] = assignment_to_be_added

    def get_all_assignments_repo(self):
        return [value for value in self.__assignments.values()]

    def get_all_assignments_repo_keys(self):
        return [key for key in self.__assignments.keys()]

    def assignments_size_repo(self):
        return len(self.__assignments)

    def modify_assignment_repo_assignment(self, assignment_to_be_modified):
        assignment_id = assignment_to_be_modified.get_assignment_id()
        if assignment_id not in self.__assignments:
            raise RepoError("Inexistent assignment!")
        self.__assignments[assignment_id] = assignment_to_be_modified

    def remove_assignment_repo(self, assignment_id_to_remove):
        if assignment_id_to_remove not in self.__assignments:
            raise RepoError("Inexistent assignment!")
        del self.__assignments[assignment_id_to_remove]
