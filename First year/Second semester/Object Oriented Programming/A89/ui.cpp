//#include "ui.h"
//#include "service.h"
//#include "validator.h"
//#include <string>
//#include <fstream>
////#include <Windows.h>
//#include <iostream>
//
//using namespace std;
//
//Ui::Ui(Service& service, UserService& user_service, DogValidator& validator) : service{ service }, user_service{ user_service }, validator{ validator }
//{
//
//
//}
//
//
//void Ui::add_dog_ui() {
//    cout << "Add a new dog" << endl;
//    string breed;
//    string name;
//    string age_string;
//    string photo;
//    int age;
//    while (true)
//    {
//        try {
//            cout << "Enter the breed: ";
//            getline(cin, breed);
//            this->validator.validate_breed(breed);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//
//    }
//    while (true)
//    {
//        try {
//            cout << "Enter the name: ";
//            getline(cin, name);
//            this->validator.validate_name(name);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    while (true)
//    {
//        try {
//            cout << "Enter the age: ";
//            getline(cin, age_string);
//            this->validator.validate_age_string(age_string);
//            age = stoi(age_string);
//            this->validator.validate_age(age);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    while (true)
//    {
//        try {
//            cout << "Enter the photo link: ";
//            getline(cin, photo);
//            this->validator.validate_photo(photo);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//
//        }
//
//    }
//    this->service.add_dog_service(breed, name, age, photo);
//    cout << "Dog successfully added!" << endl;
//
//}
//
//void Ui::delete_dog_ui() {
//    cout << "Delete a dog" << endl;
//    string name;
//    while (true)
//    {
//        try {
//            cout << "Enter the name: ";
//            getline(cin, name);
//            this->validator.validate_name(name);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    this->service.delete_dog_service(name);
//    cout << "Dog successfully deleted!" << endl;
//}
//
//void Ui::update_dog_ui() {
//    cout << "Update a dog" << endl;
//    string old_name;
//    string new_breed;
//    string new_name;
//    string new_age_string;
//    string new_photo;
//    int new_age;
//    while (true)
//    {
//        try {
//            cout << "Enter the old name: ";
//            getline(cin, old_name);
//            this->validator.validate_name(old_name);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what();
//        }
//    }
//    while (true)
//    {
//        try {
//            cout << "Enter the new breed: ";
//            getline(cin, new_breed);
//            this->validator.validate_breed(new_breed);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//
//    while (true)
//    {
//        try {
//            cout << "Enter the new name: ";
//            getline(cin, new_name);
//            this->validator.validate_name(new_name);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    while (true)
//    {
//        try {
//            cout << "Enter the new age: ";
//            getline(cin, new_age_string);
//            this->validator.validate_age_string(new_age_string);
//            new_age = stoi(new_age_string);
//            this->validator.validate_age(new_age);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    while (true)
//    {
//        try
//        {
//            cout << "Enter the new photo link: ";
//            getline(cin, new_photo);
//            this->validator.validate_photo(new_photo);
//            break;
//        }
//        catch (ValidationException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//    this->service.update_dog_service(old_name, new_breed, new_name, new_age, new_photo);
//    cout << "Dog successfully updated!" << endl;
//
//
//}
//
//void Ui::print_all_dogs()
//{
//
//    for (Dog dog : this->service.get_all_data())
//    {
//        cout << dog.to_string() << endl;
//    }
//}
//
//void Ui::list_all_dogs_for_user()
//{
//    vector<Dog> dogs = this->service.get_all_data();
//    bool done = false;
//    int index = 0;
//    int size = dogs.size();
//    while (!done)
//    {
//        if (this->service.get_size_service() == 0)
//        {
//            string error;
//            error += string("The database is empty!");
//            if (error.size() != 0)
//                throw RepoException(error);
//
//        }
//        if (index == size)
//            index = 0;
//        cout << dogs.at(index).to_string() << endl;
//        cout << "Adopt? [Yes / No / exit]" << endl;
//        string link = string("start ").append(dogs.at(index).get_photo());
//        system(link.c_str());
//        string option;
//        getline(cin, option);
//        if (option == "Yes")
//        {
//            Dog dog = dogs.at(index);
//            this->user_service.add_dog_to_adoption_list(dog);
//            size--;
//            index++;
//        }
//        else if (option == "No")
//            index++;
//        else if (option == "exit")
//            done = true;
//        else
//            cout << "Invalid option!";
//        if (this->service.get_size_service() == 0)
//            done = true;
//    }
//}
//
//void Ui::list_filtered_dogs()
//{
//    string breed_filter;
//    string age_filter_string;
//    int age_filter;
//    cout << "Enter the breed: ";
//    getline(cin, breed_filter);
//    if (!this->validator.validate_string(breed_filter))
//    {
//        string error;
//        error += string("Breed not valid!");
//        if (error.size() != 0)
//            throw ValidationException(error);
//
//    }
//    cout << "Enter the age: ";
//    getline(cin, age_filter_string);
//    this->validator.validate_age_string(age_filter_string);
//    age_filter = stoi(age_filter_string);
//    this->validator.validate_age(age_filter);
//    vector<Dog> valid_dogs = this->user_service.get_filtered(string(breed_filter), age_filter);
//    if (valid_dogs.size() == 0)
//    {
//        string error;
//        error += string("No corresponding dogs!");
//        if (error.size() != 0)
//            throw RepoException(error);
//    }
//    string option;
//    bool done = false;
//    int index = 0;
//    while (!done)
//    {
//        if (valid_dogs.size() == 0)
//        {
//            string error;
//            error += string("The list of dogs is empty!");
//            if (error.size() != 0)
//                throw RepoException(error);
//        }
//        if (index == valid_dogs.size())
//            index = 0;
//        cout << valid_dogs.at(index).to_string() << endl;
//        cout << "Adopt? [Yes / No / exit]" << endl;
//        string link = string("start ").append(valid_dogs.at(index).get_photo());
//        system(link.c_str());
//        getline(cin, option);
//        if (option == "Yes") {
//            Dog dog = valid_dogs.at(index);
//            this->user_service.add_dog_to_adoption_list(dog);
//            index++;
//        }
//        else if (option == "No") {
//            index++;
//        }
//        else if (option == "exit")
//            done = true;
//    }
//}
//
//void Ui::list_adoption_list() {
//    vector<Dog> adoptions = this->user_service.get_all_dogs();
//    int index = 0;
//    if (adoptions.size() == 0)
//        throw "The list is empty!";
//    for (Dog dog : adoptions) {
//        cout << index + 1 << ". " << adoptions.at(index).to_string() << endl;
//        index++;
//    }
//}
//
//void Ui::print_administrator_menu() {
//    cout << "ADMINISTRATOR MENU: " << endl;
//    cout << "1. List all the dogs from the shelter." << endl;
//    cout << "2. Add a new dog." << endl;
//    cout << "3. Delete a dog." << endl;
//    cout << "4. Update a dog." << endl;
//    cout << "x. Exit" << endl;
//    cout << "Choose an option: ";
//}
//
//void Ui::print_user_menu() {
//    cout << "USER MENU: " << endl;
//    cout << "1. See all the dogs in the database, one by one." << endl;
//    cout << "2. See all the dogs of a given breed, having an age less than a given number." << endl;
//    cout << "3. See the adoption list." << endl;
//    cout << "4. Show adoption list in file." << endl;
//    cout << "x. Exit." << endl;
//    cout << "Choose an option: ";
//}
//
//void Ui::administrator_mode() {
//    cout << "~You entered the administrator mode~" << endl;
//    bool done = false;
//    string print_all_dogs;
//    print_all_dogs = "1";
//    string add_dog;
//    add_dog = "2";
//    string delete_dog;
//    delete_dog = "3";
//    string update_dog;
//    update_dog = "4";
//    while (!done) {
//        try {
//            print_administrator_menu();
//            string option;
//            getline(cin, option);
//            if (option == "x") {
//                done = true;
//            }
//            else if (option == print_all_dogs)
//                this->print_all_dogs();
//            else if (option == add_dog)
//                this->add_dog_ui();
//            else if (option == delete_dog)
//                this->delete_dog_ui();
//            else if (option == update_dog)
//                this->update_dog_ui();
//            else
//                cout << "Bad input!" << endl;
//        }
//        catch (ValidationException exception) {
//            cout << exception.what() << endl;
//        }
//        catch (RepoException exception)
//        {
//            cout << exception.what() << endl;
//        }
//    }
//}
//
//void Ui::populate_list()
//{
//    this->service.read_from_file();
//}
//
//void Ui::user_mode() {
//    cout << "~You entered the user mode~" << endl;
//    bool done = false;
//    string list_all_dogs;
//    list_all_dogs = "1";
//    string list_filtered_dogs;
//    list_filtered_dogs = "2";
//    string list_adoption_list;
//    list_adoption_list = "3";
//    string show_adoption_list = "4";
//    cout << "Enter the type of file: ";
//    string type;
//    getline(cin, type);
//    /*if (type == "CSV" || type == "HTML")
//        this->user_service.set_adoption_list_type(type);
//    else
//        cout << "Bad input!" << endl;*/
//
//    while (!done) {
//        try {
//            print_user_menu();
//            string option;
//            getline(cin, option);
//            if (option == "x") {
//                done = true;
//            }
//            else if (option == list_all_dogs)
//                this->list_all_dogs_for_user();
//            else if (option == list_filtered_dogs)
//                this->list_filtered_dogs();
//            else if (option == list_adoption_list)
//                this->list_adoption_list();
//            else if (option == show_adoption_list)
//            {
//                this->user_service.open_file();
//            }
//            else
//                cout << "Bad input!" << endl;
//        }
//        catch (ValidationException exception) {
//            cout << exception.what() << endl;
//        }
//        catch (RepoException exception)
//        {
//            cout << exception.what() << endl;
//
//        }
//    }
//}
//
//void Ui::print_file_choice_menu()
//{
//    cout << "Choose the file type: " << endl;
//    cout << "1. CSV" << endl;
//    cout << "2. HTML" << endl;
//    cout << "Choose an option: ";
//}
//
//void Ui::print_menu() {
//    cout << "MENU:" << endl;
//    cout << "1. Administrator mode." << endl;
//    cout << "2. User mode." << endl;
//    cout << "x. Exit." << endl;
//    cout << "Choose an option: ";
//}
//
//void Ui::start() {
//    cout << "~Keep calm and adopt a pet~" << endl;
//    this->populate_list();
//    bool done = false;
//    string admin_mode;
//    admin_mode = "1";
//    string usermode;
//    usermode = "2";
//    int choice_for_file = -1;
//
//    while (!done) {
//
//        print_menu();
//        string option;
//        getline(cin, option);
//        if (option == admin_mode)
//            administrator_mode();
//        else if (option == usermode)
//            user_mode();
//        else if (option == "x") {
//            done = true;
//        }
//        else
//            cout << "Bad input!" << endl;
//    }
//
//
//}
//
//Ui::~Ui()
//{
//
//}