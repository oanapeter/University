#pragma once
#include "report.h"
#include <vector>
#include <string>
#include <fstream>
using namespace std;

class RepoReport
{
private:
	vector<Report> reports;
	string filename;
public:
	RepoReport(string filename);
	~RepoReport();
	RepoReport() {}
	void loadFromFile();
	void saveToFile();
	vector<Report> getAll();
	int getSize();
	int findReportByDescription(string description);
	void addReport(Report r);
};