#pragma once
#include "repo.h"
#include "CSVRepo.h"
#include "HTMLRepo.h"
#include <vector>

class UserService
{
private:
	Repo& repo;
	Repo* adoption_list;
public:
	UserService(Repo& repo, Repo* adoption_list);
	~UserService();
	void add_dog_to_adoption_list(Dog dog);
	void set_adoption_list_type(string type);
	int find_index_of_dog_by_name(string);
	vector<Dog> get_filtered(string breed, int age);
	vector<Dog> get_all_dogs();
	void open_file();
	vector<Dog> get_all_data();
};
