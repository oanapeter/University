#pragma once
#include <string>
#include "domain.h"
using namespace std;

class Dog {
private:
    string breed;
    string name;
    int age;
    string photo;
public:

    Dog();

    Dog(string ,string ,int ,string);

    string get_breed();

    string get_name();

    int get_age();

    string get_photo();

    void set_breed(string new_breed);

    void set_name(string new_name);

    void set_age(int new_age);

    void set_photo(string new_photo);

    void operator = (const Dog& dog);

    ~Dog();

    string to_string() const;
};