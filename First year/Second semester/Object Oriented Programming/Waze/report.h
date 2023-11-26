#pragma once
#include <string>
#include <vector>
using namespace std;

class Report
{
private:
	string description;
	string reporter;
	int latitude;
	int longitude;
	bool validated;
public:
	Report() {}
	Report(string, string, int, int, bool);
	~Report() {}
	string getDescription();
	string getReporter();
	int getLatitude();
	int getLongitude();
	bool getStatus();
	void setDescription(string description);
	void setReporter(string reporter);
	void setLatitude(int latitude);
	void setLongitude(int longitude);
	void setStatus(bool status);
	string toString();
	vector<string> tokenize(string str, char delimiter);
	friend istream& operator>>(istream& is, Report& r);
	friend ostream& operator<<(ostream& os, Report& r);
};
