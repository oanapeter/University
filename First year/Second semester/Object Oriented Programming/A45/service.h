#pragma once
#include "repo.h"

class Service {
private:
    Repo repo;
public:

    Service();

    DynamicArray<Dog> get_all_data_service();

    void add_entries();

    int get_size_service();

    int get_capacity_service();

    bool add_dog_service(string breed, string name, int age, string photo);

    bool delete_dog_service(string name);

    bool update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo);

    DynamicArray<Dog> get_adoption_list_service();

    int get_size_adoption_list();

    void add_dog_in_adoption_list_service(Dog dog);

    DynamicArray<Dog> get_filtered(string filter_string, int age_filter);

    ~Service();
};
