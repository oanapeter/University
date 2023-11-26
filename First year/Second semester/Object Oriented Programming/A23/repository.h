#pragma once
#include "estate.h"

typedef void* TElem;

typedef struct
{
	TElem* array_of_estates;
	int size, capacity;
}Repository;

Repository* create_repo();

void destroy_repo(Repository* repo);

void add_estate_repo(Repository* repo, Estate* estate_to_be_added);

Estate* get_an_estate(Repository* repo, int index);

int get_the_array_size(Repository* repo);

int get_surface_repo(Repository* repo, int index);

void update_estate_repo(Repository* repo, int index_of_estate, char type[], int surface, int price);

