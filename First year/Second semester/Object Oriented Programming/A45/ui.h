#pragma once
#include "service.h"


class Ui {
private:
    Service service;
public:
    Ui();

    static bool validate_string(string input);

    void add_dog_ui();

    void delete_dog_ui();

    void update_dog_ui();

    void list_all();

    void print_all_dogs();

    void list_all_dogs_for_user();

    void list_all_user();

    void list_filtered_user();

    void list_filtered_dogs();

    void list_adoption_list();

    static void print_administrator_menu();

    static void print_user_menu();

    void administrator_mode();

    void user_mode();

    static void print_menu();

    void start();

    ~Ui();
};