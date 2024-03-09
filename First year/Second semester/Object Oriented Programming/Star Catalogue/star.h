#pragma once
#include <string>
#include <vector>
#include <iostream>
using namespace std;

class Star
{
private:
	string name;
	string constellation;
	int RA;
	int Dec;
	int diameter;
public:
	Star(string, string, int, int, int);
	Star();
	~Star();
	string getName();
	string getConstellation() const;
	int getRA();
	int getDec();
	int getDiameter();
	void setName(string);
	//void setConstellation(string);
	void setRA(int);
	void setDec(int);
	void setDiameter(int);
	friend ostream& operator<<(ostream&, const Star&);
	friend istream& operator>>(istream&, Star&);
	vector<string> tokenize(string, char);
	bool operator==(const Star&);
};