#
# This is the program's UI module. The user interface and all interaction with the user (print and input statements) are found here
#
from functions import *


def menu():

    backup_of_main_list = []
    list_of_lists_of_scores = generate_ten_sets_of_scores()
    add_to_backup(backup_of_main_list, list_of_lists_of_scores)
    list_for_listing_with_certain_criterion = []

    while True:
        user_command = input(">")
        command_splitted = remove_spaces_from_command_and_put_all_individual_contains_in_a_list(user_command)

        if command_splitted[0] == 'add':
            try:
                print(add_scores_in_the_list(command_splitted, list_of_lists_of_scores, backup_of_main_list))
            except ValueError as ve:
                print("Error: ", ve)

        elif command_splitted[0] == 'insert':
            try:
                print(insert_scores_at_certain_position(command_splitted, list_of_lists_of_scores, backup_of_main_list))
            except ValueError as ve:
                print("Error: ", ve)

        elif command_splitted[0] == 'remove':
            try:
                print(remove_scores_with_certain_criterion(command_splitted, list_of_lists_of_scores, backup_of_main_list))
            except ValueError as ve:
                print("Error: ", ve)

        elif command_splitted[0] == 'replace':
            try:
                print(replace_problem_with_another_score(command_splitted, list_of_lists_of_scores, backup_of_main_list))
            except ValueError as ve:
                print("Error: ", ve)

        elif command_splitted[0] == 'list':
            try:
                print(list_a_list_with_certain_criterion(command_splitted, list_for_listing_with_certain_criterion, list_of_lists_of_scores))
            except ValueError as ve:
                print("Error: ", ve)

        elif command_splitted[0] == 'top':
            try:
                print(top_of_participants_with_certain_criterion(command_splitted, list_of_lists_of_scores))
            except ValueError as ve:
                print("Error: ", ve)

        elif user_command == 'undo':
            list_of_lists_of_scores = undo_the_last_iteration(backup_of_main_list, list_of_lists_of_scores)
            print(list_of_lists_of_scores)
        else:
            print("Invalid command.")
