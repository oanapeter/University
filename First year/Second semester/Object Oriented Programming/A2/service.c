#include "service.h"
#include <string.h>
#include <stdlib.h>

Service* create_service(Repository* repo, Undo* undo, Undo* redo) {
	Service* new_service = malloc(sizeof(Service));
	if (new_service == NULL)
		return NULL;
	new_service->repo = repo;
	new_service->undo = undo;
	new_service->redo = redo;
	return new_service;
}

void destroy_service(Service* service) {
	free(service->repo);
	free(service);
}

void add_estate_service(Service* service, char address[], char type[], int surface, int price, int do_undo) {
	Estate* new_estate = create_estate(address, type, surface, price);
	add_estate_repo(service->repo, new_estate);
	if (do_undo)
		undo_for_add(service->undo, copy_estate(new_estate));

}

Estate* get_an_estate_service(Service* service, int index) {
	return get_an_estate(service->repo, index);
}

int get_size_of_the_array_service(Service* service) {
	return get_the_array_size(service->repo);
}

void remove_estate_from_array_service(Service* service, int index, int do_undo) {

	Estate* estate = get_an_estate_service(service, index);
	remove_estate_from_the_array(service->repo, index);
	if (do_undo)
		undo_for_delete(service->undo, copy_estate(estate));

}

void get_array_of_estates(Service* service, Estate* array_of_estates[], int size) {

	get_array_of_estates_repo(service->repo, array_of_estates, size);
}

void update_estate_service(Service* service, int index, char type[], int surface, int price, int do_undo) {
	if (do_undo) {
		undo_for_update(service->undo, copy_estate(get_an_estate(service->repo, index)));
	}

	update_estate_repo(service->repo, index, type, surface, price);
}

int check_if_estate_already_exists(Service* service, char address_to_check[]) {
	int size = get_size_of_the_array_service(service);
	for (int i = 0; i < size; i++) {
		Estate* current_estate = get_an_estate_service(service, i);
		if (strcmp(current_estate->address, address_to_check) == 0)
			return i;
	}
	return -1;
}

int search_estate_containing_a_given_string(Service* service, char string_to_search_for[], Estate* estates_that_contain_the_given_string[]) {
	int length_of_array_with_estates_containing_the_string = 0;
	int size = get_size_of_the_array_service(service);
	for (int i = 0; i < size; i++) {
		Estate* current_estate = get_an_estate_service(service, i);
		if (strstr(current_estate->address, string_to_search_for)) {
			estates_that_contain_the_given_string[length_of_array_with_estates_containing_the_string] = current_estate;
			length_of_array_with_estates_containing_the_string++;
		}
	}
	return length_of_array_with_estates_containing_the_string;
}

void sort_estates_containing_the_given_string(int length_of_estates_that_contain_the_given_string, Estate* estates_that_contain_the_given_string[]) {
	for ( int i = 0; i< length_of_estates_that_contain_the_given_string; i++)
		for (int j = i + 1; j < length_of_estates_that_contain_the_given_string; j++)
		{
			struct Estate* auxiliar;
			auxiliar = estates_that_contain_the_given_string[i];
			estates_that_contain_the_given_string[i] = estates_that_contain_the_given_string[j];
			estates_that_contain_the_given_string[j] = auxiliar;
		}
}

void get_array_of_estates_service(Service* service, Estate* array_of_estates[]) {
	int size = get_size_of_the_array_service(service);
	for (int i = 0; i < size; i++)
		array_of_estates[i] = get_an_estate_service(service, i);
}

int estates_of_a_given_type_having_the_surface_greater_than_a_value(Service* service, Estate* array_of_estates[], char type[], int surface) {
	int number_of_estates_that_fulfill_requirements = 0;
	int size_of_all_estates = get_size_of_the_array_service(service);
	for (int i = 0; i < size_of_all_estates; i++) {
		Estate* current_estate = get_an_estate(service->repo, i);
		int surface_of_estate = get_surface(current_estate);
		if (surface_of_estate > surface && strcmp(current_estate->type, type) == 0) {
			array_of_estates[number_of_estates_that_fulfill_requirements] = current_estate;
			number_of_estates_that_fulfill_requirements++;
		}
	}
	return number_of_estates_that_fulfill_requirements;
}

int get_surface_service(Service* service, int index) {
	return get_surface_repo(service->repo, index);
}

int undo(Service* service) {
	if (get_size_undo(service->undo) == 0)
		return 0;
	if (get_id_undo(service->undo) == 1) {
		Estate* estate_to_delete = get_estate_undo(service->undo);
		undo_for_delete(service->redo, copy_estate(estate_to_delete));
		int index = check_if_estate_already_exists(service, estate_to_delete->address);
		remove_estate_from_array_service(service, index, 0);
		destroy_estate(estate_to_delete);
		decrease_size(service->undo);
	}
	else if (get_id_undo(service->undo) == 2) {
		Estate* estate_to_add = get_estate_undo(service->undo);;
		undo_for_add(service->redo, copy_estate(estate_to_add));
		add_estate_service(service, estate_to_add->address, estate_to_add->type, estate_to_add->surface, estate_to_add->price, 0);
		destroy_estate(estate_to_add);
		decrease_size(service->undo);
	}

	else if (get_id_undo(service->undo) == 3) {
		Estate* estate = get_estate_undo(service->undo);
		int index = check_if_estate_already_exists(service, estate->address);
		Estate* current_estate = get_an_estate_service(service, index);
		undo_for_update(service->redo, copy_estate(current_estate));
		update_estate_service(service, index, estate->type, estate->surface, estate->price, 0);
		destroy_estate(estate);
		decrease_size(service->undo);
	}
	return 1;
}


int redo(Service* service) {
	if (service->redo->size == 0)
		return 0;

	else if (get_id_undo(service->redo) == 1) {
		Estate* estate_to_delete = get_estate_undo(service->redo);
		int index = check_if_estate_already_exists(service, estate_to_delete->address);
		remove_estate_from_array_service(service, index, 1);
		destroy_estate(estate_to_delete);
		decrease_size(service->redo);
	}
	else if (get_id_undo(service->redo) == 2) {
		Estate* estate_to_add = get_estate_undo(service->redo);
		printf("%s", estate_to_add->address);
		add_estate_service(service, estate_to_add->address, estate_to_add->type, estate_to_add->surface, estate_to_add->price, 1);
		destroy_estate(estate_to_add);
		decrease_size(service->redo);
	}
	
	else if (get_id_undo(service->redo) == 3) {
		Estate* estate = get_estate_undo(service->redo);
		int index = check_if_estate_already_exists(service, estate->address);
		Estate* current_estate = get_an_estate_service(service, index);
		update_estate_service(service, index, estate->address, estate->type, estate->surface, estate->price, 0);
		destroy_estate(estate);
		decrease_size(service->redo);
	}
	return 1;
}