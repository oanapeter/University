#pragma once
#include "domain.h"
using namespace std;
class ValidationException : public std::exception {
private:
	string message;

public:
	explicit ValidationException(string message);

	const char* what() const noexcept override;
};

class DogValidator {
public:
	DogValidator();

	bool validate_string(string input);

	void validate_breed(string breed);

	void validate_name(string name);

	void validate_age_string(string age);

	void validate_age(int age);

	void validate_photo(string photo);

	~DogValidator();

};