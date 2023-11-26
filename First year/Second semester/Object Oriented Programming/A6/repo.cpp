#include "repo.h"
#include "domain.h"
#include <string>

Repo::Repo()
{

}


vector<Dog> Repo::get_all_dogs_repo() {
	return this->dogs;
}

int Repo::get_size_dogs() {
	return this->dogs.size();
}

Dog& Repo::get_dog(int index)
{
	return this->dogs.at(index);
}

void Repo::add_dog_repo(Dog dog) {
	this->dogs.push_back(dog);
}

int Repo::find_dog_by_name(string name) {

	auto found = find_if(this->dogs.begin(), this->dogs.end(), [name](Dog dog) {return dog.get_name() == name; });
	if (found != this->dogs.end())
		return distance(this->dogs.begin(), found);
	
	return -1;
}

void Repo::delete_dog_repo(int delete_index) {
	this->dogs.erase(this->dogs.begin() + delete_index);
}

void Repo::update_dog_repo(int update_index, string new_breed, string new_name, int new_age, string new_photo) {
	
	this->dogs.at(update_index).set_breed(new_breed);
	this->dogs.at(update_index).set_name(new_name);
	this->dogs.at(update_index).set_age(new_age);
	this->dogs.at(update_index).set_photo(new_photo);
}

vector<Dog> Repo::get_adoption_list() {
	return this->adoption_list;
}

int Repo::get_size_adoption_list() {
	return this->adoption_list.size();
}

void Repo::add_dog_in_adoption_list_repo(Dog dog) {
	this->adoption_list.push_back(dog);
}

Repo::~Repo()
{

}