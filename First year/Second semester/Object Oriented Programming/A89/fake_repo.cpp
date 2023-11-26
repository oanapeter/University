#include "fake_repo.h"

FakeRepo::FakeRepo(int index)
{
	this->index = index;
}

void FakeRepo::add_dog(const Dog& dog)
{
	return;
}

int FakeRepo::find_dog_by_name(string name)
{
	return this->index;
}