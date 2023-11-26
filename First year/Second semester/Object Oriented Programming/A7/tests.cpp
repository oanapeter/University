#include "domain.h"
#include "service.h"
#include "repo.h"
#include <assert.h>


void test_service_unique_entity()
{
    FakeRepo fake_repo;
    Service service(fake_repo);
    fake_repo.index = -1;
    service.add_dog_service("breed", "name", 1, "https");
    assert(true);
}

void test_service_duplicate_entity()
{
    FakeRepo fake_repo;
    Service service(fake_repo);
    fake_repo.index = 0;
    try {
        service.add_dog_service("breed", "name", 1, "https");
    }
    catch (exception& e)
    {
        assert(true);
    }
    //assert(false);
}

void test_all()
{
    test_service_unique_entity();
	test_service_duplicate_entity();
}