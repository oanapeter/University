#pragma once
#include <string>
#include <vector>
using namespace std;

class Driver
{
private:
	string name;
	string status;
	int latitude;
	int longitude;
	int score;
public:
	Driver() {}
	Driver(string, string, int, int, int);
	~Driver() {}
	string getName();
	string getStatus();
	int getLatitude();
	int getLongitude();
	int getScore();
	void setName(string name);
	void setStatus(string status);
	void setLatitude(int latitude);
	void setLongitude(int longitude);
	void setScore(int score);
	string toString();
	vector<string> tokenize(string str, char delimiter);
	friend istream& operator>>(istream& is, Driver& d);
	friend ostream& operator<<(ostream& os, Driver& d);

};
