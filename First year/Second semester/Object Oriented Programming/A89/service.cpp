#include "service.h"
#include "domain.h"
#include <string>
using namespace std;

Service::Service(Repo& repo) : repo{ repo } {}

vector<Dog> Service::get_all_data()
{
	return this->repo.get_all();
}

int Service::get_size_service()
{
	return this->repo.get_size();
}

void Service::add_dog_service(string breed, string name, int age, string photo)
{
	Dog dog{ breed, name, age, photo };
	this->repo.add_dog(dog);
}


void Service::delete_dog_service(string name)
{
	int index = this->repo.find_dog_by_name(name);
	this->repo.delete_dog(index);
}

void Service::update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo)
{
	int index = this->repo.find_dog_by_name(old_name);
	this->repo.update_dog(index, new_breed, new_name, new_age, new_photo);
}

void Service::read_from_file()
{
	this->repo.read_from_file();
}

Service::~Service()
{

}