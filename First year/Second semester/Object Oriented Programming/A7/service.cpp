#include "service.h"
#include "repo.h"
#include <cassert>


//Service::Service(Repo& repo)
//{
//    this->repo = repo;
//}
//
//Service::Service(Repo& repo, Repo& adoption_list)
//{
//	this->repo = repo;
//	this->adoption_list = adoption_list;
//}

Service::Service(Repo& repo)
{
    this->repo = repo;
}

Service::Service()
{
	
}


int Service::get_size_service() {
    return this->repo.get_size_dogs();
}

vector<Dog> Service::get_all_data()
{
    return this->repo.get_all();
}


void Service::add_dog_service(string breed, string name, int age, string photo) {
    this->repo.add_dog(Dog(breed, name, age, photo));
    
}

void Service::delete_dog_service(string name) {
    int delete_index = this->repo.find_dog_by_name(name);
    this->repo.delete_dog(delete_index);

}

void Service::update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo) {
    int update_index = this->repo.find_dog_by_name(old_name);
    this->repo.update_dog_repo(update_index, new_breed, new_name, new_age, new_photo);
  
}


vector<Dog> Service::get_adoption_list_service() {
    return this->adoption_list.get_all();
}


void Service::add_dog_in_adoption_list_service(Dog dog) {
    this->adoption_list.add_dog(dog);
    string name = dog.get_name();
    int delete_index = this->repo.find_dog_by_name(name);
    this->repo.delete_dog(delete_index);
}

vector<Dog> Service::get_filtered(string filter_string, int age_filter) {
    int index;
    int length = this->repo.get_size_dogs();
    vector<Dog> valid_dogs;
    if (filter_string[0] == '\0')
    {


        for (Dog dog : this->repo.get_all())
        {
            if (age_filter > dog.get_age())
            {
                valid_dogs.push_back(dog);
            }
        }
    }
    else
    {
        for (Dog dog : this->repo.get_all())
        {
            if (filter_string == dog.get_breed() && age_filter > dog.get_age())
            {
                valid_dogs.push_back(dog);
            }
        }
    }
    return valid_dogs;
}

void Service::read_from_file()
{
	string data = "dogs.txt";
	this->repo.read_from_file(data);
}

void Service::write_to_file()
{
	string data = "dogs.txt";
	this->repo.write_to_file(data);
}

Service::~Service()
{

}

