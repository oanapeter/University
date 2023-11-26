#pragma once
#include "service.h"
#include "validator.h"
#include "adoption_list.h"


class Ui {
private:
    Service service;
    DogValidator validator;
public:
    Ui();

    void add_dog_ui();

    void delete_dog_ui();

    void update_dog_ui();

    void print_all_dogs();

    void list_all_dogs_for_user();

    void list_filtered_dogs();

    void list_adoption_list();

    void print_administrator_menu();

    void print_user_menu();

    void print_file_choice_menu();

    void administrator_mode();

    void user_mode(AdoptionList*);

    static void print_menu();

    void start();

    ~Ui();
};