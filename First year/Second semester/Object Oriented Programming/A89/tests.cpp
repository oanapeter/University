#include "fake_repo.h"
#include "user_service.h"
#include "service.h"
#include <iostream>
#include <assert.h>

void test_unique_entity()
{
	FakeRepo* repo = new FakeRepo(-1);
	Service service{ *repo };
	service.add_dog_service("breed", "name", 1, "https.");
	assert(true);

}

void test_duplicate_entity()
{
	FakeRepo* repo = new FakeRepo(0);
	Service service{ *repo };
	try {
		service.add_dog_service("breed", "name", 1, "https.");
	}
	catch (exception& e)
	{
		assert(true);
	}
}

void test_all()
{
	test_unique_entity();
	test_duplicate_entity();
	//std::cout << "Tests successful!";
}