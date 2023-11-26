#include "repoReport.h"
#include <string>
#include <fstream>
#include <iostream>
using namespace std;

RepoReport::RepoReport(string filename)
{
	this->filename = filename;
}

RepoReport::~RepoReport()
{
}

void RepoReport::loadFromFile()
{
	ifstream f(this->filename);
	Report report;
	while (f >> report)
	{
		this->reports.push_back(report);
	}
	f.close();
}

void RepoReport::saveToFile()
{
	ofstream f(this->filename);
	for (auto r : this->reports)
	{
		f << r;
	}
	f.close();
}

vector<Report> RepoReport::getAll()
{
	return this->reports;
}

int RepoReport::getSize()
{
	return this->reports.size();
}

int RepoReport::findReportByDescription(string description)
{
	for (int i = 0; i < this->getSize(); i++)
	{
		if (this->reports[i].getDescription() == description)
			return i;
	}
	return -1;
}

void RepoReport::addReport(Report r)
{
	if (r.getDescription().empty())
		throw "Description cannot be empty!";
	this->reports.push_back(r);
	this->saveToFile();
}
