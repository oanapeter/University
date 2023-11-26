#include "user_service.h"

UserService::UserService(Repo& repo, Repo* adoption_list) : repo{ repo }, adoption_list{ adoption_list } {}

UserService::~UserService()
{

}

void UserService::add_dog_to_adoption_list(Dog dog)
{
    this->adoption_list->add_dog(dog);
    string name = dog.get_name();
    int index = this->repo.find_dog_by_name(name);
    this->repo.delete_dog(index);

}

void UserService::set_adoption_list_type(string type)
{
    if (type == "CSV")
    {
        this->adoption_list = new CSVRepo();
    }
    else if (type == "HTML")
    {
        this->adoption_list = new HTMLRepo();
    }
    //this->adoption_list->open_file();
}

vector<Dog> UserService::get_filtered(string filter_string, int age_filter) {
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

vector<Dog> UserService::get_all_dogs()
{
    return this->adoption_list->get_all();
}


vector<Dog> UserService::get_all_data()
{
	return this->repo.get_all();
}

int UserService::find_index_of_dog_by_name(string name)
{
	return this->repo.find_dog_by_name(name);
}