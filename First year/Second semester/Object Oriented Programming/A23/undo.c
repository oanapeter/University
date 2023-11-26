#include "undo.h"
#include <stdlib.h>


// Id for undo:
// 1 - undo for add 
// 2 - undo for delete 
// 3 - undo for update 

Undo* create_undo() {
	Undo* new_undo = malloc(sizeof(Undo));
	if (new_undo == NULL)
		return NULL;
	new_undo->size = 0;
	return new_undo;
}

void destroy_undo(Undo* undo) {
	for (int index = 0; index < undo->size; index++)
		destroy_estate(undo->array_of_estates[index]);
	free(undo);
}

int get_size_undo(Undo* undo) {
	return undo->size;
}

void undo_for_add(Undo* undo, Estate* estate) {
	undo->array_of_estates[undo->size] = estate;
	undo->array_of_estates[undo->size]->id = 1;
	undo->size++;
}

int get_id_undo(Undo* undo) {
	Estate* estate = undo->array_of_estates[undo->size - 1];
	return get_id(estate);
}

Estate* get_estate_undo(Undo* undo) {
	return undo->array_of_estates[undo->size - 1];
}

void decrease_size(Undo* undo) {
	undo->size--;
}

void undo_for_delete(Undo* undo, Estate* estate) {
	undo->array_of_estates[undo->size] = estate;
	undo->array_of_estates[undo->size]->id = 2;
	undo->size++;
}


void undo_for_update(Undo* undo, Estate* estate) {
	undo->array_of_estates[undo->size] = estate;
	undo->array_of_estates[undo->size]->id = 3;
	undo->size++;
}