#include <utility>
#include "domain.h"

Dog::Dog()
{
    this->breed = "";
    this->name = "";
    this->age = 0;
    this->photo = "";
}

Dog::Dog(string breed, string name, int age, string photo) {
    /*if (age < 0) {
        throw "Age can't be smaller than 0!";
    }
    this->breed = std::move(breed);
    this->name = std::move(name);
    this->age = age;
    this->photo = std::move(photo);*/
    this->breed = breed;
    this->name = name;
    this->age = age;
    this->photo = photo;
}

string Dog::get_breed() {
    return this->breed;
}

string Dog::get_name() {
    return this->name;
}

int Dog::get_age() {
    return this->age;
}

string Dog::get_photo() {
    return this->photo;
}

void Dog::operator = (const Dog& dog)
{
    this->breed = dog.breed;
    this->name = dog.name;
    this->age = dog.age;
    this->photo = dog.photo;
}

void Dog::set_breed(string new_breed)
{
    this->breed = new_breed;
}

void Dog::set_name(string new_name)
{
    this->name = new_name;
}

void Dog::set_age(int new_age)
{
    this->age = new_age;
}

void Dog::set_photo(string new_photo)
{
    this->photo = new_photo;
}

Dog::~Dog()
{

}

string Dog::to_string() const {
    auto str_age = std::to_string(this->age);
    return "Breed: " + this->breed + " | Name: " + this->name + " | Age: " + str_age + " | Photo link: " + this->photo;
}
