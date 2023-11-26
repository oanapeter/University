#pragma once
#include "service.h"

typedef struct {
	Service* service;
}Ui;

Ui* create_ui(Service* service);

void destroy_ui(Ui* ui);

int verify_validity_of_user_input(char type[]);

void print_the_array(Ui* ui);

void print_menu(Ui* ui);