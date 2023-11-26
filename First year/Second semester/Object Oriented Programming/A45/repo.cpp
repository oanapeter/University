#include "repo.h"
#include "domain.h"
#include <string>

Repo::Repo() 
{

}


DynamicArray<Dog> Repo::get_all_dogs_repo() {
	return this->dynamic_array;
}

int Repo::get_size_dogs() {
	return this->dynamic_array.get_size();
}

Dog& Repo::get_dog(int index)
{
	return this->dynamic_array.get_element(index);
}

int Repo::get_capacity() {
	return this->dynamic_array.get_capacity();
}

void Repo::add_dog_repo(Dog dog) {
	this->dynamic_array.add_element(dog);
}

int Repo::find_dog_by_name(string name) {
	int searched_index = -1;
	int index = 0;
	int length = this->get_size_dogs();
	while (index < length && searched_index == -1) {
		Dog current_dog = this->dynamic_array.get_element(index);
		string other_name = current_dog.get_name();
		if (other_name == name)
			searched_index = index;
		index++;
	}
	return searched_index;
}

void Repo::delete_dog_repo(int delete_index) {
	this->dynamic_array.delete_element(delete_index);
}

void Repo::update_dog_repo(int update_index, Dog new_dog) {
	this->dynamic_array.update_element(update_index, new_dog);
}

DynamicArray<Dog> Repo::get_adoption_list() {
	return this->adoption_list;
}

int Repo::get_size_adoption_list() {
	return this->adoption_list.get_size();
}

void Repo::add_dog_in_adoption_list_repo(Dog dog) {
	this->adoption_list.add_element(dog);
}

Repo::~Repo()
{

}