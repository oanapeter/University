#include "validator.h"

ValidationException::ValidationException(string message) : message{ message } {}

const char* ValidationException::what() const noexcept {
	return message.c_str();
}

DogValidator::DogValidator()
{

}

bool DogValidator::validate_string(string input)
{
	for (char i : input)
		if (isdigit(i) != false)
			return false;
	return true;
}

void DogValidator::validate_breed(string breed)
{
	string errors;
	if (validate_string(breed) == false)
		errors += string("Invalid input!");
	if (breed.size() == 0)
		errors += string("Breed cannot be empty!");
	if (errors.size() > 0)
		throw ValidationException(errors);

}

void DogValidator::validate_name(string name)
{
	string errors;
	if (validate_string(name) == false)
		errors += string("Invalid input!");
	if (name.size() == 0)
		errors += string("Name cannot be empty!");
	if (errors.size() > 0)
		throw ValidationException(errors);
}

void DogValidator::validate_age_string(string age)
{
	string errors;
	if (age.size() == 0)
		errors += string("Age cannot be empty!");
	if (age.find_first_not_of("123456789") != std::string::npos)
		errors += string("Age must be a number!");
	if (errors.size() > 0)
		throw ValidationException(errors);
}

void DogValidator::validate_age(int age)
{
	string errors;
	if (age < 0)
		errors += string("Age cannot be negative!");
	if (errors.size() > 0)
		throw ValidationException(errors);
}

void DogValidator::validate_photo(string photo)
{
	string errors;
	if (photo.size() == 0)
		errors += string("Photo link cannot be empty!");
	if (photo.find("https") == std::string::npos)
		errors += string("Photo link not valid!");
	if (errors.size() > 0)
		throw ValidationException(errors);
}

DogValidator::~DogValidator()
{
}
