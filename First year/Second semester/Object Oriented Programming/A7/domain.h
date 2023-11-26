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

    Dog(string, string, int, string);

    string get_breed() const;

    string get_name() const;

    int get_age() const;

    string get_photo() const;

    void set_breed(string new_breed);

    void set_name(string new_name);

    void set_age(int new_age);

    void set_photo(string new_photo);

    void play();

    bool operator == (const Dog& dog);

    bool operator != (const Dog& dog);

    friend istream& operator>>(istream& is, Dog& dog);

    friend ostream& operator<<(ostream& os, const Dog& dog);

    ~Dog();

    string to_string();

    string to_string_file();
};