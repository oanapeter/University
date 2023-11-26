#include "repo.h"
#include "domain.h"
#include <string>
#include <fstream>
#include <algorithm>

Repo::Repo()
{
	
}

Repo::Repo(const Repo& repo)
{
	this->dogs = vector<Dog>(repo.dogs);
}

Repo& Repo::operator=(const Repo& repo)
{
	this->dogs = repo.dogs;
	return *this;
}

void Repo::read_from_file(string file_path)
{
	ifstream file(file_path.c_str());
	if (file.peek() == EOF)
	{
		file.close();
		return;
	}
	do
	{
		Dog new_dog;
		file >> new_dog;
		this->dogs.push_back(new_dog);
	} while (!file.eof());
	file.close();
}

void Repo::write_to_file(string file_path)
{
	ofstream file(file_path.c_str());
	for (Dog dog : this->dogs)
	{
		file << dog;
	}file.close();
}



vector<Dog> Repo::get_all()
{
	vector<Dog> all_dogs(this->dogs.size());
	copy(this->dogs.begin(), this->dogs.end(), all_dogs.begin());
	return all_dogs;
	
}

int Repo::get_size_dogs() {
	if (this->dogs.size() == 0)
	{
		string error;
		error += string("There are no dogs in the repository!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}
	return this->dogs.size();
}

Dog Repo::get_dog(int index)
{
	return this->dogs.at(index);
}

void Repo::add_dog(Dog dog) {
	int existing = this->find_dog_by_name(dog.get_name());
	if (existing != -1)
	{
		string error;
		error += string("There is already a dog with this name!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}
	this->dogs.push_back(dog);
}

int Repo::find_dog_by_name(string name) {

	auto found = find_if(this->dogs.begin(), this->dogs.end(), [name](Dog dog) {return dog.get_name() == name; });
	if (found != this->dogs.end())
		return distance(this->dogs.begin(), found);

	return -1;
}

void Repo::delete_dog(int delete_index) {
	if (delete_index == -1)
	{
		string error;
		error += string("There is no dog with this name!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}
	this->dogs.erase(this->dogs.begin() + delete_index);
}

void Repo::update_dog_repo(int update_index, string new_breed, string new_name, int new_age, string new_photo) {
	if (update_index == -1)
	{
		string error;
		error += string("There is no dog with this name!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}
	this->dogs.at(update_index).set_breed(new_breed);
	this->dogs.at(update_index).set_name(new_name);
	this->dogs.at(update_index).set_age(new_age);
	this->dogs.at(update_index).set_photo(new_photo);
}


RepoException::RepoException(string message) : message{ message } {}
const char* RepoException::what() const noexcept {
	return message.c_str();
}

Repo::~Repo()
{

}



int FakeRepo::find_dog_by_name(string name) {
	return this->index;
}

void FakeRepo::add_dog(Dog dog)
{
	return;
}