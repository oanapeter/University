#pragma once
#include "driver.h"
#include <vector>
#include <string>
#include <fstream>
using namespace std;

class RepoDriver
{
private:
	vector<Driver> drivers;
	string filename;
public:
	RepoDriver(string filename);
	RepoDriver();
	~RepoDriver();
	void loadFromFile();
	void saveToFile();
	vector<Driver> getAll();
	int getSize();
	int findDriverByName(string name);
	void addDriver(Driver d);
	

};
