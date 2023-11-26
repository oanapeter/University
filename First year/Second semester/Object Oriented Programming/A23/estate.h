#pragma once

typedef struct {
	char* address;
	char* type;
	int surface;
	int id;
	int price;
}Estate;

Estate* create_estate(char* address, char* type, int surface, int price);

void destroy_estate(Estate* estate);

char* get_address(Estate*);

char* get_type(Estate*);

int get_surface(Estate*);

int get_price(Estate*);

char* to_string(Estate*);

void update_estate(Estate* estate, char type[], int surface, int price);

Estate* copy_estate(Estate* estate);
