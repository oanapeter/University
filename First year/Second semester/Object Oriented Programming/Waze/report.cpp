#include "report.h"
#include <string>
#include <vector>
#include <sstream>

Report::Report(string description, string reporter, int latitude, int longitude, bool validated)
{
	this->description = description;
	this->reporter = reporter;
	this->latitude = latitude;
	this->longitude = longitude;
	this->validated = validated;
}

string Report::getDescription()
{
	return description;
}

string Report::getReporter()
{
	return reporter;
}

int Report::getLatitude()
{
	return latitude;
}

int Report::getLongitude()
{
	return longitude;
}

bool Report::getStatus()
{
	return validated;
}

void Report::setDescription(string description)
{
	this->description = description;
}

void Report::setReporter(string reporter)
{
	this->reporter = reporter;
}

void Report::setLatitude(int latitude)
{
	this->latitude = latitude;
}

void Report::setLongitude(int longitude)
{
	this->longitude = longitude;
}

void Report::setStatus(bool status)
{
	this->validated = status;
}

string Report::toString()
{
	string string_valid;
	if (validated == true)
		string_valid = "true";
	else
		string_valid = "false";
	return this->description + " | " + this->reporter + " | " + to_string(this->latitude) + " | " + to_string(this->longitude) + " | " + string_valid;
}

vector<string> Report::tokenize(string str, char delimiter)
{
	vector<string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

istream& operator>>(istream& is, Report& r)
{
	string line;
	getline(is, line);
	vector<string> tokens = r.tokenize(line, ',');
	if (tokens.size() != 5)
		return is;
	r.description = tokens[0];
	r.reporter = tokens[1];
	r.latitude = stoi(tokens[2]);
	r.longitude = stoi(tokens[3]);
	if (tokens[4] == "true")
		r.validated = true;
	else
		r.validated = false;
	return is;
}

ostream& operator<<(ostream& os, Report& r)
{
	os << r.description << "," << r.reporter << "," << r.latitude << "," << r.longitude << "," << r.validated << "\n";
	return os;
}