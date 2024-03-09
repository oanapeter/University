#include "star.h"
#include <string>
#include <vector>
#include <iostream>
#include <sstream>
using namespace std;

Star::Star(string name, string constellation, int RA, int Dec, int diameter)
{
	this->name = name;
	this->constellation = constellation;
	this->RA = RA;
	this->Dec = Dec;
	this->diameter = diameter;
}

Star::Star()
{
	this->name = "";
	this->constellation = "";
	this->RA = 0;
	this->Dec = 0;
	this->diameter = 0;
}

Star::~Star()
{}

string Star::getName()
{
	return this->name;
}

string Star::getConstellation() const
{
	return this->constellation;
}

int Star::getRA()
{
	return this->RA;
}

int Star::getDec()
{
	return this->Dec;
}

int Star::getDiameter()
{
	return this->diameter;
}

void Star::setName(string name)
{
	this->name = name;
}



void Star::setRA(int RA)
{
	this->RA = RA;
}

void Star::setDec(int Dec)
{
	this->Dec = Dec;
}

void Star::setDiameter(int diameter)
{
	this->diameter = diameter;
}	

vector<string> Star::tokenize(string str, char delimiter)
{
	vector<string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

bool Star::operator==(const Star& s)
{
	return this->name == s.name;
}


ostream& operator<<(ostream& os, const Star& s)
{
	os << s.name << "," << s.constellation << "," << s.RA << "," << s.Dec << "," << s.diameter << endl;
	return os;
}

istream& operator>>(istream& is, Star& s)
{
	string line;
	getline(is, line);
	vector<string> tokens = s.tokenize(line, ',');
	if (tokens.size() != 5)
		return is;
	s.name = tokens[0];
	s.constellation = tokens[1];
	s.RA = stoi(tokens[2]);
	s.Dec = stoi(tokens[3]);
	s.diameter = stoi(tokens[4]);
	return is;
}


