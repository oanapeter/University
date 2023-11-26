#include "domain.h"
#include "repo.h"
#include "service.h"
#include <assert.h>
#include <iostream>
#include <string>

using namespace std;

void test_domain() {
	Dog dog1(string("Border Collie"), string("Socks"), 2, string("https://unsplash.com/photos/md2_P9X7t4M"));
	assert(dog1.get_breed() == string("Border Collie"));
	assert(dog1.get_name() == string("Socks"));
	assert(dog1.get_age() == 2);
	assert(dog1.get_photo() == string("https://unsplash.com/photos/md2_P9X7t4M"));
	Dog dog2(string("Golden Retriever"), string("Benji"), 1, string("https://unsplash.com/photos/rFao8fcvTdY"));
	assert(dog2.get_breed() == string("Golden Retriever"));
	assert(dog2.get_name() == string("Benji"));
	assert(dog2.get_age() == 1);
	assert(dog2.get_photo() == string("https://unsplash.com/photos/rFao8fcvTdY"));
	Dog dog3(string("German Shepherd"), string("Lea"), 3, string("https://unsplash.com/photos/i1AS4tkfbj4"));
	assert(dog3.get_breed() == string("German Shepherd"));
	assert(dog3.get_name() == string("Lea"));
	assert(dog3.get_age() == 3);
	assert(dog3.get_photo() == string("https://unsplash.com/photos/i1AS4tkfbj4"));
	Dog dog4(string("Pomeranian"), string("Fluffy"), 1, string("https://unsplash.com/photos/TaxX_Y8lMD0"));
	assert(dog4.get_breed() == string("Pomeranian"));
	assert(dog4.get_name() == string("Fluffy"));
	assert(dog4.get_age() == 1);
	assert(dog4.get_photo() == string("https://unsplash.com/photos/TaxX_Y8lMD0"));
	Dog dog5(string("Husky"), string("Rex"), 5, string("https://unsplash.com/photos/ISahwf9hCNg"));
	assert(dog5.get_breed() == string("Husky"));
	assert(dog5.get_name() == string("Rex"));
	assert(dog5.get_age() == 5);
	assert(dog5.get_photo() == string("https://unsplash.com/photos/ISahwf9hCNg"));
	dog5.set_name(string("North"));
	dog5.set_breed(string("Siberian Husky"));
	dog5.set_age(4);
	dog5.set_photo(string("https://unsplash.com/photos/zc4MEZMdXhc"));
	assert(dog5.get_breed() == string("Siberian Husky"));
	assert(dog5.get_name() == string("North"));
	assert(dog5.get_age() == 4);
	assert(dog5.get_photo() == string("https://unsplash.com/photos/zc4MEZMdXhc"));

}

void test_repo()
{
	Repo repo;
	Dog dog1(string("Border Collie"), string("Socks"), 2, string("https://unsplash.com/photos/md2_P9X7t4M"));
	Dog dog2(string("Golden Retriever"), string("Benji"), 1, string("https://unsplash.com/photos/rFao8fcvTdY"));
	Dog dog3(string("German Shepherd"), string("Lea"), 3, string("https://unsplash.com/photos/i1AS4tkfbj4"));
	Dog dog4(string("Pomeranian"), string("Fluffy"), 1, string("https://unsplash.com/photos/TaxX_Y8lMD0"));
	Dog dog5(string("Husky"), string("Rex"), 5, string("https://unsplash.com/photos/ISahwf9hCNg"));
	repo.add_dog_repo(dog1);
	repo.add_dog_repo(dog2);
	repo.add_dog_repo(dog3);
	repo.add_dog_repo(dog4);
	repo.add_dog_repo(dog5);
	assert(repo.get_size_dogs() == 5);
	assert(repo.get_dog(0).get_breed() == string("Border Collie"));
	assert(repo.get_dog(0).get_name() == string("Socks"));
	assert(repo.get_dog(0).get_age() == 2);
	assert(repo.get_dog(0).get_photo() == string("https://unsplash.com/photos/md2_P9X7t4M"));
	assert(repo.get_dog(1).get_breed() == string("Golden Retriever"));
	assert(repo.get_dog(1).get_name() == string("Benji"));
	assert(repo.get_dog(1).get_age() == 1);
	assert(repo.get_dog(1).get_photo() == string("https://unsplash.com/photos/rFao8fcvTdY"));
	repo.delete_dog_repo(0);
	assert(repo.get_size_dogs() == 4);
	//assert(repo.update_dog_repo(0, Dog(string("Shiba Inu"), string("Butter"), 5, string("https://unsplash.com/photos/odJtBMxGEfk")));



}

void test_service()
{
	Service service;
	service.add_dog_service(string("Border Collie"), string("Oreo"), 4, string("https://unsplash.com/photos/5XwVKkVSxA8"));
	service.add_dog_service(string("Golden Retriever"), string("Fish"), 1, string("https://unsplash.com/photos/FTbC150wV8Q"));
	assert(service.get_all_data_service().get_size() == 2);
	
}

void test_all()
{
	test_domain();
	test_repo();
	test_service();
	cout << "Tests successfully passed!" << endl;
	cout << endl;
}