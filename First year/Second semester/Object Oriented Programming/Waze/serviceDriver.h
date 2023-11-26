#pragma once
#include "repoDriver.h"
#include <string>
#include <vector>
#include <fstream>
using namespace std;

class ServiceDriver
{
private:
	RepoDriver repoDriver;
public:
	ServiceDriver(RepoDriver repo);
	~ServiceDriver();
	void addDriver(string name, string status, int latitude, int longitude, int score);
	//void updateDriver(string name, string status, int latitude, int longitude, int score);	
	//void deleteDriver(string name);
	vector<Driver> getAll();
	int getSize();
	//int findDriverByName(string name);

};
