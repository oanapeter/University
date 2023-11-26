#pragma once
#include "domain.h"
template <typename T>
class DynamicArray {
private:
    T* elements;
    int capacity;
    int size;
    void resize();

public:

    DynamicArray();

    DynamicArray(const DynamicArray<T>& dynamic_array);

    void add_element(T element);

    void delete_element(int delete_index);

    void update_element(int update_index, T element);

    //T* get_elements();

    T& get_element(int index) const;

    int get_capacity() const;

    int get_size() const;

    ~DynamicArray();
};

template <typename T>
DynamicArray<T>::DynamicArray() {
    this->capacity = 10;
    this->size = 0;
    this->elements = new T[capacity];

}

template <typename T>
void DynamicArray<T>::add_element(T element) {
    if (this->capacity == this->size)
        this->resize();
    this->elements[this->size] = element;
    this->size++;
}

template<typename T>
DynamicArray<T>::DynamicArray(const DynamicArray<T>&  dynamic_array)
{
    this->capacity = dynamic_array.capacity;
    this->size = dynamic_array.size;

    this->elements = new T[this->capacity];

    if (this->elements == nullptr)
        return;

    for (int i = 0; i < this->get_size(); i++)
        this->elements[i] = dynamic_array.get_element(i);
}

template <typename T>
void DynamicArray<T>::resize() {
    auto* new_elements = new T[this->capacity * 2];
    int index;
    for (index = 0; index < this->size; index++) {
        new_elements[index] = this->elements[index];
    }
    delete[] this->elements;
    this->elements = new_elements;
    this->capacity *= 2;
}

template <typename T>
void DynamicArray<T>::delete_element(int delete_index) {
    int index;
    for (index = delete_index; index < this->size - 1; index++) {
        this->elements[index] = this->elements[index + 1];
    }
    this->size--;
}

template <typename T>
void DynamicArray<T>::update_element(int update_index, T update_element) {
    this->elements[update_index] = update_element;
}

//template <typename T>
//T* DynamicArray<T>::get_elements() {
//    return this->elements;
//}

template <typename T>
T& DynamicArray<T>::get_element(int index) const
{
    return this->elements[index];
}

template <typename T>
int DynamicArray<T>::get_capacity() const {
    return this->capacity;
}

template <typename T>
int DynamicArray<T>::get_size() const {
    return this->size;
}

template <typename T>
DynamicArray<T>::~DynamicArray() {
    delete[] this->elements;

}