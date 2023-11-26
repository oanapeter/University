#pragma once
#include "repo.h"
#include <string.h>
using namespace std;

class Service
{
private:
    Repo& repo;
public:
    explicit Service(Repo& repo);

    vector<Dog> get_all_data();

    int get_size_service();

    void add_dog_service(string breed, string name, int age, string photo);

    void delete_dog_service(string name);

    void update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo);

    int find_dog_by_name(string name);

    void read_from_file();

    ~Service();
};
