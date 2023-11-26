#include "repository.h"
#include <stdlib.h>

Repository* create_repo() {
    Repository* newRepo = malloc(sizeof(Repository));
    if (newRepo == NULL)
        return NULL;
    newRepo->capacity = 5;
    newRepo->array_of_estates = malloc(newRepo->capacity * sizeof(TElem));
    newRepo->size = 0;
    return newRepo;
}

void destroy_repo(Repository* repo) {
    if (repo == NULL)
        return;
    for (int index = 0; index < repo->size; index++)
        destroy_estate(repo->array_of_estates[index]);
    free(repo);
}

void resize_the_array_of_estates(Repository* repo) {
    repo->capacity *= 2;
    TElem* aux = realloc(repo->array_of_estates, sizeof(TElem) * repo->capacity);
    repo->array_of_estates = aux;
}

void add_estate_repo(Repository* repo, Estate* estate_to_be_added) {
    if (repo->size == repo->capacity)
        resize_the_array_of_estates(repo);
    repo->array_of_estates[repo->size] = estate_to_be_added;
    repo->size++;
}

Estate* get_an_estate(Repository* repo, int index) {
    return repo->array_of_estates[index];
}


int get_the_array_size(Repository* repo) {
    return repo->size;
}


void remove_estate_from_the_array(Repository* repo, int index) {
    Estate* estate = malloc(sizeof(Estate));
    if (estate == NULL)
        return NULL;
    estate = repo->array_of_estates[index];
    repo->array_of_estates[index] = repo->array_of_estates[repo->size - 1];
    repo->array_of_estates[repo->size] = estate;
    repo->size--;
}

void update_estate_repo(Repository* repo, int index_of_estate, char type[], int surface, int price) {
    update_estate(repo->array_of_estates[index_of_estate], type, surface, price);
}

void get_array_of_estates_repo(Repository* repo, Estate* array_of_estates[], int size) {
    for (int index = 0; index < size; index++)
    {
        array_of_estates[index] = get_an_estate(repo, index);
    }
}

int get_surface_repo(Repository* repo, int index) {
    return get_surface(repo->array_of_estates[index]);
}