#pragma once
#include "estate.h"

typedef struct {
	int size;
	Estate* array_of_estates[100];
}Undo;

Undo* create_undo();

void destroy_undo();

int get_size_undo();

void undo_for_add(Undo* undo, Estate* estate);

void undo_for_delete(Undo* undo, Estate* estate);

void undo_for_update(Undo* undo, Estate* estate);

int get_id_undo(Undo* undo);

Estate* get_estate_undo(Undo* undo);

void decrease_size(Undo* undo);
