#include "repo.h"
#include "domain.h"
#include <vector>
#include <fstream>

Repo::Repo()
{

}

Repo::Repo(string file_name)
{
	this->file_name = file_name;
	this->read_from_file();
}

Repo::~Repo()
{
}

int Repo::get_size()
{
	return this->dogs.size();
}

vector<Dog> Repo::get_all()
{
	return this->dogs;
}

void Repo::add_dog(const Dog& dog)
{
	int existing = this->find_dog_by_name(dog.get_name());
	if (existing != -1)
	{
		string error;
		error += string("There is already a dog with this name!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}
	this->dogs.push_back(dog);
	this->write_to_file();
}

int Repo::find_dog_by_name(string name) {

	auto found = find_if(this->dogs.begin(), this->dogs.end(), [name](Dog dog) {return dog.get_name() == name; });
	if (found != this->dogs.end())
		return distance(this->dogs.begin(), found);

	return -1;
}

void Repo::update_dog(int update_index, string new_breed, string new_name, int new_age, string new_photo)
{
	this->dogs.at(update_index).set_breed(new_breed);
	this->dogs.at(update_index).set_name(new_name);
	this->dogs.at(update_index).set_age(new_age);
	this->dogs.at(update_index).set_photo(new_photo);
	this->write_to_file();
}

void Repo::delete_dog(int delete_index) {
	/*if (delete_index == -1)
	{
		string error;
		error += string("There is no dog with this name!\n");
		if (error.size() != 0)
			throw RepoException(error);
	}*/
	this->dogs.erase(this->dogs.begin() + delete_index);
}

Dog Repo::get_dog_by_index(int index)
{
	return this->dogs.at(index);
}

void Repo::read_from_file()
{
	ifstream file(this->file_name.c_str());
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

void Repo::write_to_file()
{
	ofstream file(this->file_name.c_str());
	int index = 0;
	int size = this->dogs.size();
	for (Dog dog : this->dogs)
	{
		file << dog;
		if (index < size - 1)
			file << endl;
		index++;
	}file.close();
}

void Repo::open_file()
{
}

RepoException::RepoException(string message) : message{ message } {}
const char* RepoException::what() const noexcept {
	return message.c_str();
}



