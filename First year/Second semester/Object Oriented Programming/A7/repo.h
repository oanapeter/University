#pragma once
#include "domain.h"
#include <string.h>
#include <vector>

class Repo {
private:
    vector<Dog> dogs;
public:

    Repo();
    Repo(const Repo& repo);
    Repo& operator = (const Repo& repo);

    void read_from_file(string);
    void write_to_file(string);

    vector<Dog> get_all();

    int get_size_dogs();

    Dog get_dog(int);

    virtual void add_dog(Dog dog);

    virtual int find_dog_by_name(string name);

    void delete_dog(int delete_index);

    void update_dog_repo(int update_index, string new_breed, string new_name, int new_age, string new_photo);

    ~Repo();
};

class FakeRepo : public Repo
{
public:
    int index;
    int find_dog_by_name(string name) override;
    void add_dog(Dog) override;

};

class RepoException : public exception {
private:
    string message;
    public:
        explicit RepoException(string message);
		const char* what() const noexcept override;
};

