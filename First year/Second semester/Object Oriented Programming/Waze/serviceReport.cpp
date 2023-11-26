#include "serviceReport.h"

ServiceReport::ServiceReport(RepoReport repoR)
{
	this->repoReport = repoR;
}

ServiceReport::~ServiceReport()
{
}

void ServiceReport::addReport(string description, string reporterName, int latitude, int longitude, bool validated)
{
	Report r{ description, reporterName, latitude, longitude, validated };
	this->repoReport.addReport(r);
	this->notify();
}

vector<Report> ServiceReport::getAll()
{
	return this->repoReport.getAll();
}

int ServiceReport::getSize()
{
	return this->repoReport.getSize();
}

