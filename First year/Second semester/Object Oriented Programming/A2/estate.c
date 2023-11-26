#include "estate.h"
#include <stdio.h>
#include <stdlib.h>

Estate* create_estate(char* address, char* type, int surface, int price)
{
	Estate* newEstate = malloc(sizeof(Estate));
	newEstate->address = malloc((strlen(address) + 1) * sizeof(char));
	newEstate->type = malloc((strlen(type) + 1) * sizeof(char));
	strcpy(newEstate->address, address);
	strcpy(newEstate->type, type);
	newEstate->surface = surface;
	newEstate->price = price;
	return newEstate;

}

void destroy_estate(Estate* estate)
{
	free(estate->address);
	free(estate->type);
	free(estate);
}

char* get_address(Estate* estate)
{
	return estate->address;
}

char* get_type(Estate* estate)
{
	return estate->type;
}

int get_surface(Estate* estate)
{
	return estate->surface;
}

int get_price(Estate* estate)
{
	return estate->price;
}

void update_estate(Estate* estate, char type[], int surface, int price)
{
	estate->type = type;
	estate->surface = surface;
	estate->price = price;
}

Estate* copy_estate(Estate* estate) {
	return create_estate(estate->address, estate->type, estate->surface, estate->price);
}

int get_id(Estate* estate) {
	return estate->id;
}

char* to_string(Estate* estate)
{
	char string[40];
	sprintf(string, "Address: %s\nTypr: %s\nSurface: %d\nPrice: %d", estate->address, estate->type, estate->surface, estate->price);

}