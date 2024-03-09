#pragma once
#include <string>
#include <vector>
#include <iostream>
using namespace std;

class Astronomer
{
private:
	string name;
	string constellation;
public:
	Astronomer(string& name, string& constellation) : name{ name }, constellation{ constellation } {}
	Astronomer() {}
	~Astronomer();
	string getName();
	string getAstronomerConstellation();
	friend ostream& operator<<(ostream&, const Astronomer&);
	friend istream& operator>>(istream&, Astronomer&);
	vector<string> tokenize(string, char);
	bool operator==(const Astronomer&);
};
