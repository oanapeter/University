#pragma once
#include "dynamic_array.h"

class Repo {
private:
    DynamicArray<Dog> dynamic_array;
    DynamicArray<Dog> adoption_list;
public:

    Repo();

    DynamicArray<Dog> get_all_dogs_repo();

    int get_size_dogs();

    DynamicArray<Dog> get_adoption_list();

    int get_size_adoption_list();

    Dog& get_dog(int index);

    int get_capacity();

    void add_dog_repo(Dog dog);

    void add_dog_in_adoption_list_repo(Dog dog);

    int find_dog_by_name(string name);

    void delete_dog_repo(int delete_index);

    void update_dog_repo(int update_index, Dog new_dog);

    ~Repo();
};