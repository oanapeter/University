#
# The program's functions are implemented here. There is no user interaction in this file, therefore no input/print statements. Functions here
# communicate via function parameters, the return statement and raising of exceptions. 
#
import random


def remove_spaces_from_command_and_put_all_individual_contains_in_a_list(user_command):
    user_command = user_command.strip().lower()
    list_with_splitted_command = user_command.split()
    return list_with_splitted_command


def generate_ten_sets_of_scores():
    list_of_lists_of_scores = []
    for i in range(10):
        points_for_the_first_problem = random.randint(0, 10)
        points_for_the_second_problem = random.randint(0, 10)
        points_for_the_third_problem = random.randint(0, 10)
        list_of_lists_of_scores.append(create_set_of_scores(points_for_the_first_problem, points_for_the_second_problem, points_for_the_third_problem))
    return list_of_lists_of_scores


def get_p1_from_list(list_of_scores):
    return list_of_scores[0]


def get_p2_from_list(list_of_scores):
    return list_of_scores[1]


def get_p3_from_list(list_of_scores):
    return list_of_scores[2]


def create_list_of_scores_of_p1(list_of_lists_of_scores):
    list_of_scores_of_p1 = []
    for list_of_scores in list_of_lists_of_scores:
        list_of_scores_of_p1.append(get_p1_from_list(list_of_scores))
    return list_of_scores_of_p1


def create_list_of_scores_of_p2(list_of_lists_of_scores):
    list_of_scores_of_p2 = []
    for list_of_scores in list_of_lists_of_scores:
        list_of_scores_of_p2.append(get_p2_from_list(list_of_scores))
    return list_of_scores_of_p2


def create_list_of_scores_of_p3(list_of_lists_of_scores):
    list_of_scores_of_p3 = []
    for list_of_scores in list_of_lists_of_scores:
        list_of_scores_of_p3.append(get_p3_from_list(list_of_scores))
    return list_of_scores_of_p3


def create_set_of_scores(scores_for_the_first_problem, scores_for_the_second_problem, scores_for_the_third_problem):
    return [scores_for_the_first_problem, scores_for_the_second_problem, scores_for_the_third_problem]


def add_scores_in_the_list(command_splitted, list_of_lists_of_scores, backup_of_main_list):
    if len(command_splitted) == 4:
        score_for_the_first_problem = command_splitted[1]
        score_for_the_second_problem = command_splitted[2]
        score_for_the_third_problem = command_splitted[3]
        if score_for_the_first_problem.isnumeric() and score_for_the_second_problem.isnumeric() and score_for_the_third_problem.isnumeric():
            if int(score_for_the_first_problem) < 0 or int(score_for_the_first_problem) > 10 or int(score_for_the_second_problem) < 0 or int(score_for_the_second_problem) > 10 or int(score_for_the_third_problem) < 0 or int(score_for_the_third_problem) > 10:
                raise ValueError("Index out of range.")
            list_of_lists_of_scores.append(create_set_of_scores(int(score_for_the_first_problem), int(score_for_the_second_problem), int(score_for_the_third_problem)))
            add_to_backup(backup_of_main_list, list_of_lists_of_scores)
            return list_of_lists_of_scores
        else:
            raise ValueError("Invalid command.")
    else:
        raise ValueError("Invalid command.")


def insert_scores_at_certain_position(command_splitted, list_of_lists_of_scores, backup_of_main_list):
    if len(command_splitted) == 6:
        score_for_the_first_problem = command_splitted[1]
        score_for_the_second_problem = command_splitted[2]
        score_for_the_third_problem = command_splitted[3]
        position_to_insert_new_set_of_scores = command_splitted[5]
        if score_for_the_first_problem.isnumeric() and score_for_the_second_problem.isnumeric() and score_for_the_third_problem.isnumeric() and position_to_insert_new_set_of_scores.isnumeric():
            if int(score_for_the_first_problem) < 0 or int(score_for_the_first_problem) > 10 or int(score_for_the_second_problem) < 0 or int(score_for_the_second_problem) > 10 or int(score_for_the_third_problem) < 0 or int(score_for_the_third_problem) > 10:
                raise ValueError("Score is not correct!")
            if int(position_to_insert_new_set_of_scores) < 0 or int(position_to_insert_new_set_of_scores) > len(list_of_lists_of_scores):
                raise ValueError("Index out of range!")
            list_of_scores = [int(score_for_the_first_problem), int(score_for_the_second_problem), int(score_for_the_third_problem)]
            list_of_lists_of_scores.append([0, 0, 0])
            for i in range(len(list_of_lists_of_scores) - 1, int(position_to_insert_new_set_of_scores) - 1, -1):
                list_of_lists_of_scores[i] = list_of_lists_of_scores[i - 1]
            list_of_lists_of_scores[int(position_to_insert_new_set_of_scores)] = list_of_scores
            add_to_backup(backup_of_main_list, list_of_lists_of_scores)
            return list_of_lists_of_scores
        else:
            raise ValueError("Invalid command.")
    else:
        raise ValueError("Invalid command.")


def remove_scores_with_certain_criterion(command_splitted, list_of_lists_of_scores, backup_of_main_list):
    if len(command_splitted) == 2:
        participant_to_remove = command_splitted[1]
        if participant_to_remove.isnumeric():
            if int(participant_to_remove) > len(list_of_lists_of_scores) - 1:
                raise ValueError("Index out of range")
            position_from_where_to_remove_the_set_of_scores = int(participant_to_remove)
            list_of_lists_of_scores[position_from_where_to_remove_the_set_of_scores][0] = 0
            list_of_lists_of_scores[position_from_where_to_remove_the_set_of_scores][1] = 0
            list_of_lists_of_scores[position_from_where_to_remove_the_set_of_scores][2] = 0
            add_to_backup(backup_of_main_list, list_of_lists_of_scores)
            return list_of_lists_of_scores
        else:
            raise ValueError("Invalid command.")
    elif len(command_splitted) == 4:
        index_from_where_to_remove_sets_of_scores = command_splitted[1]
        index_to_where_to_remove_sets_of_scores = command_splitted[3]
        if index_from_where_to_remove_sets_of_scores.isnumeric() and index_to_where_to_remove_sets_of_scores.isnumeric():
            if int(index_from_where_to_remove_sets_of_scores) > len(list_of_lists_of_scores) - 1 or int(index_to_where_to_remove_sets_of_scores) > len(list_of_lists_of_scores) - 1 or int(index_from_where_to_remove_sets_of_scores) < 0 or int(index_to_where_to_remove_sets_of_scores) < 0:
                raise ValueError("Index out of range")
            for i in range(int(index_from_where_to_remove_sets_of_scores), int(index_to_where_to_remove_sets_of_scores) + 1):
                list_of_lists_of_scores[i][0] = 0
                list_of_lists_of_scores[i][1] = 0
                list_of_lists_of_scores[i][2] = 0
            add_to_backup(backup_of_main_list, list_of_lists_of_scores)
            return list_of_lists_of_scores
    elif len(command_splitted) == 3:
        operator = command_splitted[1]
        criterion_for_removing = command_splitted[2]
        if operator == '<':
            if criterion_for_removing.isnumeric():
                if int(criterion_for_removing) > 0 and int(criterion_for_removing) <= 10:
                    for i in range(len(list_of_lists_of_scores)):
                        if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] < int(criterion_for_removing) * 3:
                            list_of_lists_of_scores[i][0] = 0
                            list_of_lists_of_scores[i][1] = 0
                            list_of_lists_of_scores[i][2] = 0
                add_to_backup(backup_of_main_list, list_of_lists_of_scores)
                return list_of_lists_of_scores
            else:
                raise ValueError("Criterion out of range!")
        if operator == '>':
            if criterion_for_removing.isnumeric():
                if int(criterion_for_removing) >= 0 and int(criterion_for_removing) < 10:
                    for i in range(len(list_of_lists_of_scores)):
                        if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] > int(criterion_for_removing) * 3:
                            list_of_lists_of_scores[i][0] = 0
                            list_of_lists_of_scores[i][1] = 0
                            list_of_lists_of_scores[i][2] = 0
                add_to_backup(backup_of_main_list, list_of_lists_of_scores)
                return list_of_lists_of_scores
            else:
                raise ValueError("Criterion out of range!")
        if operator == '=':
            if criterion_for_removing.isnumeric():
                if int(criterion_for_removing) >= 0 and int(criterion_for_removing) <= 10:
                    for i in range(len(list_of_lists_of_scores)):
                        if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] == int(criterion_for_removing) * 3:
                            list_of_lists_of_scores[i][0] = 0
                            list_of_lists_of_scores[i][1] = 0
                            list_of_lists_of_scores[i][2] = 0
                add_to_backup(backup_of_main_list, list_of_lists_of_scores)
                return list_of_lists_of_scores
            else:
                raise ValueError("Criterion out of range!")
    else:
        raise ValueError("Invalid command.")


def replace_problem_with_another_score(command_splitted, list_of_lists_of_scores, backup_of_main_list):
    if len(command_splitted) == 5:
        participant_to_replace_the_score_for = command_splitted[1]
        problem_to_replace_score = command_splitted[2]
        new_score_for_the_problem = command_splitted[4]
        if participant_to_replace_the_score_for.isnumeric() and new_score_for_the_problem.isnumeric():
            if int(participant_to_replace_the_score_for) < 0 or int(participant_to_replace_the_score_for) > len(list_of_lists_of_scores):
                raise ValueError("Participant not found!")
            elif problem_to_replace_score not in ['p1', 'p2', 'p3']:
                raise ValueError("Problem not found!")
            elif int(new_score_for_the_problem) not in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
                raise ValueError("Score is not correct!")
            participant_to_replace_the_score_for = int(command_splitted[1])
            problem_to_replace_score = command_splitted[2]
            new_score_for_the_problem = int(command_splitted[4])
            if problem_to_replace_score == 'p1':
                list_of_lists_of_scores[participant_to_replace_the_score_for][0] = new_score_for_the_problem
            elif problem_to_replace_score == 'p2':
                list_of_lists_of_scores[participant_to_replace_the_score_for][1] = new_score_for_the_problem
            elif problem_to_replace_score == 'p3':
                list_of_lists_of_scores[participant_to_replace_the_score_for][2] = new_score_for_the_problem
            add_to_backup(backup_of_main_list, list_of_lists_of_scores)
            return list_of_lists_of_scores
        else:
            raise ValueError("Invalid command.")
    else:
        raise ValueError("Invalid command.")


def list_a_list_with_certain_criterion(command_splitted, list_for_listing_with_certain_criterion, list_of_lists_of_scores):
    if len(command_splitted) == 1:
        return list_of_lists_of_scores
    elif len(command_splitted) == 3:
        operator = command_splitted[1]
        criterion_for_listing = int(command_splitted[2]) * 3
        if operator == '<':
            if criterion_for_listing < 0 or criterion_for_listing > 30:
                raise ValueError("Criterion out of range!")
            else:
                list_for_listing_with_certain_criterion = []
                for i in range(len(list_of_lists_of_scores)):
                    if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] < criterion_for_listing:
                        list_for_listing_with_certain_criterion.append(list_of_lists_of_scores[i])
        if operator == '>':
            if criterion_for_listing < 0 or criterion_for_listing > 30:
                raise ValueError("Criterion out of range!")
            else:
                list_for_listing_with_certain_criterion = []
                for i in range(len(list_of_lists_of_scores)):
                    if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] > criterion_for_listing:
                        list_for_listing_with_certain_criterion.append(list_of_lists_of_scores[i])
        if operator == '=':
            if criterion_for_listing < 0 or criterion_for_listing > 30:
                raise ValueError("Criterion out of range!")
            else:
                list_for_listing_with_certain_criterion = []
                for i in range(len(list_of_lists_of_scores)):
                    if list_of_lists_of_scores[i][0] + list_of_lists_of_scores[i][1] + list_of_lists_of_scores[i][2] == criterion_for_listing:
                        list_for_listing_with_certain_criterion.append(list_of_lists_of_scores[i])
        return list_for_listing_with_certain_criterion
    elif len(command_splitted) == 2 and command_splitted[1] == 'sorted':
        for i in range(len(list_of_lists_of_scores)):
            for j in range(0, len(list_of_lists_of_scores) - i - 1):
                if list_of_lists_of_scores[j][0] + list_of_lists_of_scores[j][1] + list_of_lists_of_scores[j][2] < list_of_lists_of_scores[j + 1][0] + list_of_lists_of_scores[j + 1][1] + list_of_lists_of_scores[j + 1][2]:
                    list_of_lists_of_scores[j], list_of_lists_of_scores[j + 1] = list_of_lists_of_scores[j + 1], list_of_lists_of_scores[j]
        return list_of_lists_of_scores
    else:
        raise ValueError("Invalid command")


def top_of_participants_with_certain_criterion(command_splitted, list_of_lists_of_scores):
    if len(command_splitted) == 2:
        top_of_participants = []
        number_of_participants_in_top = command_splitted[1]
        for i in range(len(list_of_lists_of_scores)):
            for j in range(0, len(list_of_lists_of_scores) - i - 1):
                if list_of_lists_of_scores[j][0] + list_of_lists_of_scores[j][1] + list_of_lists_of_scores[j][2] < list_of_lists_of_scores[j + 1][0] + list_of_lists_of_scores[j + 1][1] + list_of_lists_of_scores[j + 1][2]:
                    list_of_lists_of_scores[j], list_of_lists_of_scores[j + 1] = list_of_lists_of_scores[j + 1], list_of_lists_of_scores[j]
        if number_of_participants_in_top.isnumeric():
            for i in range(0, int(number_of_participants_in_top)):
                top_of_participants.append(list_of_lists_of_scores[i])
            return top_of_participants
        else:
            raise ValueError("Invalid command.")
    elif len(command_splitted) == 3:
        how_many_scores_in_top = command_splitted[1]
        problem_for_the_top = command_splitted[2]
        scores_for_the_problem = []
        list_of_scores_of_p1 = create_list_of_scores_of_p1(list_of_lists_of_scores)
        list_of_scores_of_p2 = create_list_of_scores_of_p2(list_of_lists_of_scores)
        list_of_scores_of_p3 = create_list_of_scores_of_p3(list_of_lists_of_scores)
        list_of_scores_of_p1.sort(reverse=True)
        list_of_scores_of_p2.sort(reverse=True)
        list_of_scores_of_p3.sort(reverse=True)
        if how_many_scores_in_top.isnumeric():
            if problem_for_the_top == 'p1':
                for i in range(int(how_many_scores_in_top)):
                    scores_for_the_problem.append(list_of_scores_of_p1[i])
            elif problem_for_the_top == 'p2':
                for i in range(int(how_many_scores_in_top)):
                    scores_for_the_problem.append(list_of_scores_of_p2[i])
            elif problem_for_the_top == 'p3':
                for i in range(int(how_many_scores_in_top)):
                    scores_for_the_problem.append(list_of_scores_of_p3[i])
            return scores_for_the_problem
        else:
            raise ValueError("Invalid command.")
    else:
        raise ValueError("Invalid command.")


def undo_the_last_iteration(backup_of_main_list, list_of_lists_of_scores):
    list_of_lists_of_scores = undo(backup_of_main_list)
    delete_backup(backup_of_main_list)
    return list_of_lists_of_scores


def add_to_backup(backup, list):
    auxiliary_list = []
    for sublist in list.copy():
        auxiliary_list.append(sublist.copy())
    backup.append(auxiliary_list)


def undo(backup):
    index = len(backup) - 2
    auxiliary_list = []
    for sublist in backup[index].copy():
        auxiliary_list.append(sublist.copy())
    return auxiliary_list


def delete_backup(backup):
    index = len(backup) - 2
    del backup[index + 1]
