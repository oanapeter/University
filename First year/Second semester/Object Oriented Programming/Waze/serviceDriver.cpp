#include "serviceDriver.h"
#include <string>
#include <vector>
#include <sstream>

ServiceDriver::ServiceDriver(RepoDriver repoD)
{
	this->repoDriver = repoD;
}

ServiceDriver::~ServiceDriver()
{
}

void ServiceDriver::addDriver(string name, string status, int latitude, int longitude, int score)
{
	Driver d(name, status, latitude, longitude, score);
	this->repoDriver.addDriver(d);
}

vector<Driver> ServiceDriver::getAll()
{
	return this->repoDriver.getAll();
}

int ServiceDriver::getSize()
{
	return this->repoDriver.getSize();
}

