#include "ui.h"
#include <iostream>

using namespace std;

Ui::Ui()
{

}

bool Ui::validate_string(string input) {
    for (int i = 0; i < input.length(); i++)
        if (isdigit(input[i]) != false)
            return false;
    return true;
}

void Ui::add_dog_ui() {
    cout << "Add a new dog" << endl;
    string breed;
    string name;
    string age_string;
    string photo;
    cout << "Enter the breed: ";
    getline(cin, breed);
    if ((!validate_string(breed)) || (breed.length() == 0))
        throw "Breed input not valid!";
    cout << "Enter the name: ";
    getline(cin, name);
    if (!validate_string(name) || name.length() == 0)
        throw "Name input not valid";
    cout << "Enter the age: ";
    getline(cin, age_string);
    int age;
    if (!validate_string(age_string) && age_string.length() != 0)
        age = stoi(age_string);
    else
        throw "Age input not valid!";
    if (age < 0)
        throw "Age input not valid!";
    cout << "Enter the photo link: ";
    getline(cin, photo);
    if (photo.length() == 0 || photo.find("https") == string::npos)
        throw "Photo link not valid!";
    bool added = this->service.add_dog_service(breed, name, age, photo);
    if (added == false)
        throw "This dog already exists!";
    else if (added == true)
        cout << "Dog added successfully!" << endl;
}

void Ui::delete_dog_ui() {
    cout << "Delete a dog" << endl;
    string name;
    cout << "Enter the name: ";
    getline(cin, name);
    if (!validate_string(name) || name.length() == 0)
        throw "Name input not valid!";
    bool deleted = this->service.delete_dog_service(name);
    if (deleted == true)
        cout << "Dog successfully deleted!" << endl;
    else if (deleted == false)
        throw "The dog does not exist!";
}

void Ui::update_dog_ui() {
    cout << "Update a dog" << endl;
    string old_name;
    string new_breed;
    string new_name;
    string new_age_string;
    string new_photo;
    cout << "Enter the old name: ";
    getline(cin, old_name);
    if (!validate_string(old_name) || old_name.length() == 0)
        throw "Name input not valid!";
    cout << "Enter the new breed: ";
    getline(cin, new_breed);
    if (!validate_string(new_breed) || new_breed.length() == 0)
        throw "Breed input not valid!";
    cout << "Enter the name: ";
    getline(cin, new_name);
    if (!validate_string(new_name) || new_name.length() == 0)
        throw "Name input not valid!";
    cout << "Enter the age: ";
    getline(cin, new_age_string);
    int new_age;
    if (!validate_string(new_age_string) && new_age_string.length() != 0)
        new_age = stoi(new_age_string);
    else
        throw "Age input not valid!";
    if (new_age < 0)
        throw "Age input not valid!";
    cout << "Enter the photo link: ";
    getline(cin, new_photo);
    if (new_photo.length() == 0 || new_photo.find("https") == string::npos)
        throw "Photo link not valid!";
    bool updated = this->service.update_dog_service(old_name, new_breed, new_name, new_age, new_photo);
    if (updated == false)
        throw "The dog does not exist!";
    else if (updated == true)
        cout << "Dog updated successfully!" << endl;
}

void Ui::print_all_dogs()
{
   /* vector<Dog> dogs = this->service.get_all_data_service();
    int size = dogs.size();*/
    for (Dog dog : this->service.get_all_data_service())
    {
        cout << dog.to_string() << endl;
    }
}

void Ui::list_all_dogs_for_user()
{
    vector<Dog> dogs = this->service.get_all_data_service();
    bool done = false;
    int index = 0;
    int size = dogs.size();
    if (size == 0)
        throw "No dogs in the database!";
    while (!done)
    {
        if (index == size)
            index = 0;
        cout << dogs.at(index).to_string() << endl;
        cout << "Adopt? [Yes / No / exit]" << endl;
        string link = string("start ").append(dogs.at(index).get_photo());
        system(link.c_str());
        string option;
        getline(cin, option);
        if (option == "Yes")
        {
            Dog dog = dogs.at(index);
            this->service.add_dog_in_adoption_list_service(dog);
            size--;
            index++;
        }
        else if (option == "No")
            index++;
        else if (option == "exit")
            done = true;
    }
}

void Ui::list_filtered_dogs()
{
    string breed_filter;
    string age_filter_string;
    int age_filter;
    cout << "Enter the breed: ";
    getline(cin, breed_filter);
    if (!validate_string(breed_filter))
        throw "Input not valid!";
    cout << "Enter the age: ";
    getline(cin, age_filter_string);
    if (!validate_string(age_filter_string) && age_filter_string.length() != 0)
        age_filter = stoi(age_filter_string);
    if (age_filter < 0)
        throw "Age cannot be smaller than 0!";
    vector<Dog> valid_dogs = this->service.get_filtered(string(breed_filter), age_filter);
    if (valid_dogs.size() == 0)
        throw "No dogs corresponding!";
    string option;
    bool done = false;
    int index = 0;
    while (!done) {
        if (valid_dogs.size() == 0)
            throw "No dogs remained!";
        if (index == valid_dogs.size())
            index = 0;
        cout << valid_dogs.at(index).to_string() << endl;
        cout << "Adopt? [Yes / No / exit]" << endl;
        string link = string("start ").append(valid_dogs.at(index).get_photo());
        system(link.c_str());
        getline(cin, option);
        if (option == "Yes") {
            Dog dog = valid_dogs.at(index);
            this->service.add_dog_in_adoption_list_service(dog);
            index++;
        }
        else if (option == "No") {
            index++;
        }
        else if (option == "exit")
            done = true;
    }
}

void Ui::list_adoption_list() {
    //Dog* adoptions = this->user_service.get_all_user_service();
    vector<Dog> adoptions = this->service.get_adoption_list_service();
    int index = 0;
    /*int size = this->service.get_size_adoption_list();
    int length = adoptions.size();*/
    if (adoptions.size() == 0)
        throw "The list is empty!";
    for (Dog dog : this->service.get_adoption_list_service()) {
        cout << index + 1 << ". " << adoptions.at(index).to_string() << endl;
        index++;
}
}

void Ui::print_administrator_menu() {
    cout << "ADMINISTRATOR MENU: " << endl;
    cout << "1. List all the dogs from the shelter." << endl;
    cout << "2. Add a new dog." << endl;
    cout << "3. Delete a dog." << endl;
    cout << "4. Update a dog." << endl;
    cout << "x. Exit" << endl;
    cout << "Choose an option: ";
}

void Ui::print_user_menu() {
    cout << "USER MENU: " << endl;
    cout << "1. See all the dogs in the database, one by one." << endl;
    cout << "2. See all the dogs of a given breed, having an age less than a given number." << endl;
    cout << "3. See the adoption list." << endl;
    cout << "x. Exit." << endl;
    cout << "Choose an option: ";
}

void Ui::administrator_mode() {
    cout << "~You entered the administrator mode~" << endl;
    bool done = false;
    string print_all_dogs;
    print_all_dogs = "1";
    string add_dog;
    add_dog = "2";
    string delete_dog;
    delete_dog = "3";
    string update_dog;
    update_dog = "4";
    while (!done) {
        try {
            print_administrator_menu();
            string option;
            getline(cin, option);
            if (option == "x") {
                done = true;
            }
            else if (option == print_all_dogs)
                this->print_all_dogs();
            else if (option == add_dog)
                this->add_dog_ui();
            else if (option == delete_dog)
                this->delete_dog_ui();
            else if (option == update_dog)
                this->update_dog_ui();
            else
                cout << "Bad input!" << endl;
        }
        catch (const char* message) {
            cout << message << endl;
        }
        catch (const exception& exception) {
            cerr << exception.what();
            cout << endl;
        }
    }
}

void Ui::user_mode() {
    cout << "~You entered the user mode~" << endl;
    bool done = false;
    string list_all_dogs;
    list_all_dogs = "1";
    string list_filtered_dogs;
    list_filtered_dogs = "2";
    string list_adoption_list;
    list_adoption_list = "3";
    while (!done) {
        try {
            print_user_menu();
            string option;
            getline(cin, option);
            if (option == "x") {
                done = true;
            }
            else if (option == list_all_dogs)
                //this->list_all_user();
                this->list_all_dogs_for_user();
            else if (option == list_filtered_dogs)
                //this->list_filtered_user();
                this->list_filtered_dogs();
            else if (option == list_adoption_list)
                this->list_adoption_list();
            else
                cout << "Bad input!" << endl;
        }
        catch (const char* message) {
            cout << message << endl;
        }
        catch (const exception& exception) {
            cerr << exception.what();
            cout << endl;
        }
    }
}

void Ui::print_menu() {
    cout << "MENU:" << endl;
    cout << "1. Administrator mode." << endl;
    cout << "2. User mode." << endl;
    cout << "x. Exit." << endl;
    cout << "Choose an option: ";
}

void Ui::start() {
    cout << "~Keep calm and adopt a pet~" << endl;
    this->service.add_entries();
    bool done = false;
    string admin_mode;
    admin_mode = "1";
    string usermode;
    usermode = "2";
    while (!done) {
        print_menu();
        string option;
        getline(cin, option);
        if (option == admin_mode)
            administrator_mode();
        else if (option == usermode)
            user_mode();
        else if (option == "x") {
            done = true;
        }
        else
            cout << "Bad input!" << endl;
    }


}

Ui::~Ui()
{

}