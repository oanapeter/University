#include "repoDriver.h"
#include <vector>
#include <string>
#include <fstream>

RepoDriver::RepoDriver(string filename)
{
	this->filename = filename;
}

RepoDriver::RepoDriver()
{
	//this->filename = "";
}

RepoDriver::~RepoDriver()
{
}

void RepoDriver::loadFromFile()
{
	ifstream f(this->filename);
	Driver driver;
	while (f >> driver)
	{
		this->drivers.push_back(driver);
	}
	f.close();
}

void RepoDriver::saveToFile()
{
	ofstream f(this->filename);
	for (auto d : this->drivers)
	{
		f << d;
	}
	f.close();
}

vector<Driver> RepoDriver::getAll()
{
	return this->drivers;
}

int RepoDriver::getSize()
{
	return this->drivers.size();
}

int RepoDriver::findDriverByName(string name)
{
	for (int i = 0; i < this->getSize(); i++)
	{
		if (this->drivers[i].getName() == name)
			return i;
	}
	return -1;
}

void RepoDriver::addDriver(Driver d)
{
	this->drivers.push_back(d);
	this->saveToFile();
}

