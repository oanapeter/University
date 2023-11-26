#include "ui.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

Ui* create_ui(Service* service) {
	Ui* new_ui = malloc(sizeof(Ui));
	if (new_ui == NULL) {
		return NULL;
	}
	new_ui->service = service;
	return new_ui;
}

void destroy_ui(Ui* ui) {
	free(ui->service);
	free(ui);
}

int verify_validity_of_user_input(char type[]) {
	if (strcmp(type, "house") == 0 || strcmp(type, "penthouse") == 0 || strcmp(type, "apartment") == 0)
		return 1;
	return 0;
}

void display_estates_with_given_property(Ui* ui, int size, Estate* estates_with_given_property[]) {
	int size_of_array = get_size_of_the_array_service(ui->service);
	Estate* array_of_estates[100];
	get_array_of_estates_service(ui->service, array_of_estates, size_of_array);
	for (int index = 0; index < size; index++) {
		printf("Address: %s\nType: %s\nSurface: %d\nPrice: %d\n", array_of_estates[index]->address, array_of_estates[index]->type, array_of_estates[index]->surface, array_of_estates[index]->price);
	}
	printf("\n");
}

void print_the_array(Ui* ui) {
	int size = get_size_of_the_array_service(ui->service);
	for (int i = 0; i < size; i++) {
		Estate* current_estate = get_an_estate_service(ui->service, i);
		printf("%d. Address: %s\nType: %s\nSurface: %d\nPrice: %d\n",i+1 , current_estate->address, current_estate->type, current_estate->surface, current_estate->price);

	}
	printf("\n");
}



void add_estates(Ui* ui) {
	add_estate_service(ui->service, "Zorilor 30", "house", 50, 300000);
	add_estate_service(ui->service, "Malinilor 1", "apartment", 30, 80000);
	add_estate_service(ui->service, "Florilor 70", "penthouse", 100, 500000);
	add_estate_service(ui->service, "Memorandumului 3", "apartment", 40, 100000);
	add_estate_service(ui->service, "Zorilor 50", "house", 70, 400000);
	add_estate_service(ui->service, "Bistritei 2", "house", 150, 900000);
	add_estate_service(ui->service, "Zorilor 5", "house", 80, 700000);
	add_estate_service(ui->service, "Gradinilor 45", "house", 90, 700000);
	add_estate_service(ui->service, "Manastur 4", "apartment", 40, 60000);
	add_estate_service(ui->service, "Florilor 1", "penthouse", 90, 600000);


}

void tests(Ui* ui) {
	add_estate_service(ui->service, "Soarelui 5", "apartment", 60, 400000);
	assert(get_size_of_the_array_service(ui->service) == 11);
	remove_estate_from_array_service(ui->service, "Zorilor 5");
	assert(get_size_of_the_array_service(ui->service) == 10);
	//assert(get_surface_service(ui->service, 0) == 50);
	printf("Tests done!\n");
}

void add_estate_ui(Ui* ui) {
	char address[50], type[50];
	int surface, price;
	printf("Enter the address: ");
	gets(address);
	gets(address);
	int check_if_added = check_if_estate_already_exists(ui->service, address);
	if (check_if_added == -1)
	{
		printf("Enter the type: ");
		scanf("%s", type);
		int is_user_input_ok = verify_validity_of_user_input(type);
		if (is_user_input_ok == 1)
		{
			printf("Enter the surface: ");
			scanf("%d", &surface);
			printf("Enter the price: ");
			scanf("%d", &price);
			add_estate_service(ui->service, address, type, surface, price);
		}
		else
			printf("Invalid type!\n");
	}
	else
		printf("Estate already exists!\n");

}

void print_menu(Ui* ui) {


	printf("Estates that already exist: ");
	printf("\n");
	print_the_array(ui);
	while (1)
	{
		char option[10];
		int option2;
		printf("a. Add, delete or update an estate.\n");
		printf("b. Display all estates whose address contains a given string.\n");
		printf("c. See all estates of a given type, having the surface greater than a user provided value.\n");
		printf("d. Undo.\n");
		printf("e. Redo.\n");
		printf("Choose your option: ");
		scanf("%s", &option);
		if (strcmp(option, "a") == 0)
		{
			printf("1. Add.\n");
			printf("2. Delete.\n");
			printf("3. Update.\n");
			printf("Choose your option: ");
			scanf("%d", &option2);
			if (option2 == 1) {
				add_estate_ui(ui);

			}
			else if (option2 == 2) {
				char address[50];
				printf("Enter the address of the estate that you want to remove: ");
				gets(address);
				gets(address);
				int does_estate_exist = check_if_estate_already_exists(ui->service, address);
				if (does_estate_exist == -1)
					printf("Estate does not exist.\n");
				else {
					remove_estate_from_array_service(ui->service, does_estate_exist);
					printf("Estate deleted successfully.\n");
					print_the_array(ui);
				}


			}
			else if (option2 == 3) {
				char address[50];
				printf("Enter the estate that you want to update: ");
				gets(address);
				gets(address);
				int does_estate_exist = check_if_estate_already_exists(ui->service, address);
				if (does_estate_exist == -1)
					printf("Estate does not exist.\n");
				else {
					char new_type[15];
					int new_surface, new_price;
					printf("Enter the new type: ");
					scanf("%s", new_type);
					if (strcmp(new_type, "house") == 0 || strcmp(new_type, "apartment") == 0 || strcmp(new_type, "penthouse") == 0)
					{
						printf("Enter the new surface: ");
						scanf("%d", &new_surface);
						printf("Enter the new price: ");
						scanf("%d", &new_price);
						update_estate_service(ui->service, does_estate_exist, new_type, new_surface, new_price);
						printf("Estate updated successfully.\n");
						print_the_array(ui);
					}
					else
						printf("Invalid input.");
					
				}
			}
			else {
				printf("Invalid option.\n");
			}
		}
		else if (strcmp(option, "b") == 0)
		{
			char string[50];
			printf("Enter the string: ");
			gets(string);
			gets(string);
			if (strcmp(string, "") == 0)
			{
				Estate* array_of_estates[100];
				get_array_of_estates(ui->service, array_of_estates);
				sort_estates_containing_the_given_string(get_size_of_the_array_service(ui->service), array_of_estates);
				display_estates_with_given_property(ui, get_size_of_the_array_service(ui->service), array_of_estates);
			}
			else {
				struct Estate* estates_that_contain_the_given_string[100];
				int size_of_array = search_estate_containing_a_given_string(ui->service, string, estates_that_contain_the_given_string);
				if (size_of_array == 0)
					printf("There are no estates that contain the given string.\n");
				else {
					sort_estates_containing_the_given_string(size_of_array, estates_that_contain_the_given_string);
					display_estates_with_given_property(ui, size_of_array, estates_that_contain_the_given_string);

				}

			}

		}
		else if (strcmp(option, "c") == 0)
		{
			char type[15];
			int surface;
			printf("Enter the type: ");
			scanf("%s", type);
			if (strcmp(type, "house") == 0 || strcmp(type, "apartment") == 0 || strcmp(type, "penthouse") == 0)
			{
				printf("Enter the surface: ");
				scanf("%d", &surface);
				Estate* array_of_estates[100];
				int size_of_array = estates_of_a_given_type_having_the_surface_greater_than_a_value(ui->service, array_of_estates, type, surface);
				display_estates_with_given_property(ui, size_of_array, array_of_estates);
			}
			else
				printf("Invalid input.\n");
		}
		else if (strcmp(option, "d") == 0) {
			int is_it_possible_to_undo = undo(ui->service);
			if (is_it_possible_to_undo == 0)
				printf("It is not possible to undo.\n");
			else {
				printf("Undo succesfull.\n");
				print_the_array(ui);
			}
		}
		else if (strcmp(option, "e") == 0) {
			int is_it_possible_to_redo = redo(ui->service);
			if (is_it_possible_to_redo == 0)
				printf("It is not possible to redo.\n");
			else {
				printf("Redo succesfull.\n");
				print_the_array(ui);
			}
		}
		/*else if (strcmp(option, "f"))
		{
			add_estate_service(ui->service, "Alexandru Vlahuta 5", "house", 100, 6000000);
			assert(get_address(ui->service, 0) == "Zorilor 30");
			assert(get_type(ui->service) )
			}*/

	}
	char stop[100];
	scanf("%s", stop);
}