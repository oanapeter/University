#pragma once
#include "repository.h"
#include "undo.h"

typedef struct {
	Repository* repo;
	Undo* undo;
	Undo* redo;
}Service;

Service* create_service(Repository*);

void destroy_service(Service* service);

void add_estate_service(Service* service, char address[], char type[], int surface, int price);

Estate* get_an_estate_service(Service* service, int index);

int get_size_of_the_array_service(Service* service);

void remove_estate_from_array_service(Service* service, int index);

void update_estate_service(Service* service, int index, char type[], int surface, int price);

int check_if_estate_already_exists(Service* service, char address[]);

int search_estate_containing_a_given_string(Service* service, char string_to_search_for[], Estate* estates_that_contain_the_given_string[]);

void sort_estates_containing_the_given_string(int length_of_estates_that_contain_the_given_string, Estate* estates_that_contain_the_given_string[]);

void get_array_of_estates(Service* service, Estate* array_of_estates[]);

int estates_of_a_given_type_having_the_surface_greater_than_a_value(Service* service, Estate* array_of_estates[], char type[], int surface);

int get_surface_service(Service* service, int index);

int undo(Service* service);

int redo(Service* service);



