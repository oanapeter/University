#include "driver.h"
#include <string>
#include <vector>
#include <sstream>
using namespace std;

Driver::Driver(string name, string status, int latitude, int longitude, int score)
{
	this->name = name;
	this->status = status;
	this->latitude = latitude;
	this->longitude = longitude;
	this->score = score;
}

string Driver::getName()
{
	return name;
}

string Driver::getStatus()
{
	return status;
}

int Driver::getLatitude()
{
	return latitude;
}

int Driver::getLongitude()
{
	return longitude;
}

int Driver::getScore()
{
	return score;
}

void Driver::setName(string name)
{
	this->name = name;
}

void Driver::setStatus(string status)
{
	this->status = status;
}

void Driver::setLatitude(int latitude)
{
	this->latitude = latitude;
}

void Driver::setLongitude(int longitude)
{
	this->longitude = longitude;
}

void Driver::setScore(int score)
{
	this->score = score;
}

string Driver::toString()
{
	return "Status: " + this->status + " Latitude: "+ to_string(latitude) + " Longitude: " + to_string(longitude) + " Score: " + to_string(score);
}


vector<string> Driver::tokenize(string str, char delimiter)
{
	vector<string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}


istream& operator>>(istream& is, Driver& d)
{
	string line;
	getline(is, line);
	vector<string> tokens = d.tokenize(line, ',');
	if (tokens.size() != 5)
		return is;
	d.name = tokens[0];
	d.status = tokens[1];
	d.latitude = stoi(tokens[2]);
	d.longitude = stoi(tokens[3]);
	d.score = stoi(tokens[4]);
	return is;
}

ostream& operator<<(ostream& os, Driver& d)
{
	os << d.name << "," << d.status << "," << d.latitude << "," << d.longitude << "," << d.score << endl;
	return os;
}