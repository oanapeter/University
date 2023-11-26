#pragma once
#include <vector>
#include "domain.h"

class Repo
{
protected:
	string file_name;
	vector<Dog> dogs;
public:

	Repo();
	Repo(string file_name);
	~Repo();
	int get_size();
	vector<Dog> get_all();
	virtual void add_dog(const Dog& dog);
	virtual int find_dog_by_name(string name);
	void update_dog(int update_index, string new_breed, string new_name, int new_age, string new_photo);
	void delete_dog(int delete_index);
	Dog get_dog_by_index(int index);
	void read_from_file();
	virtual void write_to_file();
	virtual void open_file();
};

class RepoException : public exception
{
private:
	string message;
public:
	explicit RepoException(string message);
	const char* what() const noexcept override;
};

