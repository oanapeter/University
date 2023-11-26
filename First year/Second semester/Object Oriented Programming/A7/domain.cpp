#include "domain.h"
//#include <Windows.h>
//#include <shellapi.h>
#include <iostream>
#include <vector>
#include <string>
#include <sstream>

Dog::Dog()
{
    this->breed = "";
    this->name = "";
    this->age = 0;
    this->photo = "";
}

Dog::Dog(string breed, string name, int age, string photo) {
    this->breed = breed;
    this->name = name;
    this->age = age;
    this->photo = photo;
}

string Dog::get_breed() const{
    return this->breed;
}

string Dog::get_name() const{
    return this->name;
}

int Dog::get_age() const{
    return this->age;
}

string Dog::get_photo() const{
    return this->photo;
}

//void Dog::operator = (const Dog& dog)
//{
//    this->breed = dog.breed;
//    this->name = dog.name;
//    this->age = dog.age;
//    this->photo = dog.photo;
//}

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

string Dog::to_string(){
    auto str_age = std::to_string(this->age);
    return "Breed: " + this->breed + " | Name: " + this->name + " | Age: " + str_age + " | Photo link: " + this->photo;
}

string Dog::to_string_file() {
	auto str_age = std::to_string(this->age);
	return this->breed + "," + this->name + "," + str_age + "," + this->photo;
}

bool Dog::operator==(const Dog& dog) {
	if (this->get_breed() == dog.breed && this->get_name() == dog.name && this->get_age() == dog.age && this->get_photo() == dog.photo)
		return true;
    return false;
}

vector<string> tokenize(string str, char delimiter)
{
	vector<string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

bool Dog::operator!=(const Dog& dog) {
	return !this->operator==(dog);
}

//void Dog::play()
//{
//	ShellExecuteA(NULL, NULL, "chrome.exe", this->get_photo().c_str(), NULL, SW_SHOWMAXIMIZED);
//}

istream& operator>>(istream& is, Dog& dog)
{
	string line;
	getline(is, line);
	if (line.empty()) 
		return is;
	vector<string> tokens = tokenize(line, ',');
	dog.set_breed(tokens[0]);
	dog.set_name(tokens[1]);
	dog.set_age(stoi(tokens[2]));
	dog.set_photo(tokens[3]);
	return is;
}

ostream& operator<<(ostream& os, const Dog& dog)
{
	os << dog.get_breed() << "," << dog.get_name() << "," << dog.get_age() << "," << dog.get_photo() << "\n";
    return os;
}

