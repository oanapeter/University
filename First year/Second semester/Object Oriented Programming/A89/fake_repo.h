#pragma once
#pragma once
#include "repo.h"

class FakeRepo : public Repo
{
public:
	int index;
	FakeRepo(int index);
	void add_dog(const Dog& dog) override;
	int find_dog_by_name(string name) override;
	~FakeRepo();
};
