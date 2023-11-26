#pragma once
#include "repo.h"
#include <vector>

class Service {
private:
    Repo repo;
public:

    Service();

    vector<Dog> get_all_data_service();

    void add_entries();

    int get_size_service();

    bool add_dog_service(string breed, string name, int age, string photo);

    bool delete_dog_service(string name);

    bool update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo);

    vector<Dog> get_adoption_list_service();

    int get_size_adoption_list();

    void add_dog_in_adoption_list_service(Dog dog);

    vector<Dog> get_filtered(string filter_string, int age_filter);

    ~Service();
};
