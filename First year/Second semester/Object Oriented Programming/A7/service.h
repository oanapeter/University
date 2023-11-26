#pragma once
#include "repo.h"
#include <vector>


class Service {
private:
    Repo& repo;
    Repo adoption_list;
public:
    Service(Repo&);

    Service();

    vector<Dog> get_all_data();

    int get_size_service();

    void add_dog_service(string breed, string name, int age, string photo);

    void delete_dog_service(string name);

    void update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo);

    vector<Dog> get_adoption_list_service();

    void add_dog_in_adoption_list_service(Dog dog);

    vector<Dog> get_filtered(string filter_string, int age_filter);

    void read_from_file();

    void write_to_file();

    ~Service();
};
