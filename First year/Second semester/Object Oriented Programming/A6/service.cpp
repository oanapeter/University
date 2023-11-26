#include "service.h"
#include "repo.h"

Service::Service()
{
}

int Service::get_size_service() {
    return this->repo.get_size_dogs();
}

vector<Dog> Service::get_all_data_service()
{
    return this->repo.get_all_dogs_repo();
}


void Service::add_entries()
{
    Dog dog1(string("Border Collie"), string("Socks"), 2, string("https://unsplash.com/photos/md2_P9X7t4M"));
    this->repo.add_dog_repo(dog1);
    Dog dog2(string("Golden Retriever"), string("Benji"), 1, string("https://unsplash.com/photos/rFao8fcvTdY"));
    this->repo.add_dog_repo(dog2);
    Dog dog3(string("German Shepherd"), string("Lea"), 3, string("https://unsplash.com/photos/i1AS4tkfbj4"));
    this->repo.add_dog_repo(dog3);
    Dog dog4(string("Pomeranian"), string("Fluffy"), 1, string("https://unsplash.com/photos/TaxX_Y8lMD0"));
    this->repo.add_dog_repo(dog4);
    Dog dog5(string("Husky"), string("Rex"), 5, string("https://unsplash.com/photos/ISahwf9hCNg"));
    this->repo.add_dog_repo(dog5);
    Dog dog6(string("Corgi"), string("Ginny"), 3, string("https://unsplash.com/photos/1QsQRkxnU6I"));
    this->repo.add_dog_repo(dog6);
    Dog dog7(string("German Shepherd"), string("Giorgia"), 2, string("https://unsplash.com/photos/IqF8B95ZFfs"));
    this->repo.add_dog_repo(dog7);
    Dog dog8(string("Poodle"), string("Indiana"), 1, string("https://unsplash.com/photos/1OnbCf7AOdY"));
    this->repo.add_dog_repo(dog8);
    Dog dog9(string("Greyhound"), string("Flash"), 2, string("https://unsplash.com/photos/Im8Vnagv3r8"));
    this->repo.add_dog_repo(dog9);
    Dog dog10(string("Pug"), string("Pudding"), 1, string("https://unsplash.com/photos/2s2TPxBIxGc"));
    this->repo.add_dog_repo(dog10);
}

bool Service::add_dog_service(string breed, string name, int age, string photo) {
    int index;
    //int length = this->repo.get_size_dogs();
    for (Dog dog : this->repo.get_all_dogs_repo()) {
        string other_name = dog.get_name();
        if (other_name == name)
            return false;
    }

    this->repo.add_dog_repo(Dog(breed, name, age, photo));
    return true;
}

bool Service::delete_dog_service(string name) {
    int delete_index = this->repo.find_dog_by_name(name);
    if (delete_index == -1)
        return false;
    this->repo.delete_dog_repo(delete_index);
    return true;
    
}

bool Service::update_dog_service(string old_name, string new_breed, string new_name, int new_age, string new_photo) {
    int update_index = this->repo.find_dog_by_name(old_name);
    if (update_index == -1)
        return false;
    //Dog new_dog(new_breed, new_name, new_age, new_photo);
    this->repo.update_dog_repo(update_index, new_breed, new_name, new_age, new_photo);
    return true;
}


vector<Dog> Service::get_adoption_list_service() {
    return this->repo.get_adoption_list();
}

int Service::get_size_adoption_list() {
    return this->repo.get_size_adoption_list();
}


void Service::add_dog_in_adoption_list_service(Dog dog) {
    this->repo.add_dog_in_adoption_list_repo(dog);
    string name = dog.get_name();
    int delete_index = this->repo.find_dog_by_name(name);
    this->repo.delete_dog_repo(delete_index);
}

vector<Dog> Service::get_filtered(string filter_string, int age_filter) {
    int index;
    //int counter = 0;
    int length = this->repo.get_size_dogs();
    vector<Dog> valid_dogs;
    if (filter_string[0] == '\0')
    {
     

        for (Dog dog : this->repo.get_all_dogs_repo())
        {
            if (age_filter > dog.get_age())
            {
				valid_dogs.push_back(dog);
			}
		}
    }
    else
    {
        for (Dog dog : this->repo.get_all_dogs_repo())
        {
            if (filter_string == dog.get_breed() && age_filter > dog.get_age())
            {
				valid_dogs.push_back(dog);
			}
		}
    }
    return valid_dogs;
}

Service::~Service()
{

}