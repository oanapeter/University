#include "astronomer.h"
#include <string>
#include <vector>
#include <iostream>
#include <sstream>
using namespace std;

Astronomer::~Astronomer()
{}

string Astronomer::getName()
{
	return this->name;
}

string Astronomer::getAstronomerConstellation()
{
	return this->constellation;
}

vector<string> Astronomer::tokenize(string str, char delimiter)
{
	vector<string> result;
	stringstream ss(str);
	string token;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

bool Astronomer::operator==(const Astronomer& a)
{
	return this->name == a.name;
}

ostream& operator<<(ostream& os, const Astronomer& a)
{
	os << a.name << "," << a.constellation << endl;
	return os;
}

istream& operator>>(istream& is, Astronomer& a)
{
	string line;
	getline(is, line);
	vector<string> tokens = a.tokenize(line, ',');
	if (tokens.size() != 2)
		return is;
	a.name = tokens[0];
	a.constellation = tokens[1];
	return is;
}