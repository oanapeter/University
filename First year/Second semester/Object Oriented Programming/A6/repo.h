#pragma once
#include "domain.h"
#include <string.h>
#include <vector>

class Repo {
private:
    vector<Dog> dogs;
    vector<Dog> adoption_list;
public:

    Repo();

    vector<Dog> get_all_dogs_repo();

    int get_size_dogs();

    vector<Dog> get_adoption_list();

    int get_size_adoption_list();

    Dog& get_dog(int index);

    void add_dog_repo(Dog dog);

    void add_dog_in_adoption_list_repo(Dog dog);

    int find_dog_by_name(string name);

    void delete_dog_repo(int delete_index);

    void update_dog_repo(int update_index, string new_breed, string new_name, int new_age, string new_photo);

    ~Repo();
};