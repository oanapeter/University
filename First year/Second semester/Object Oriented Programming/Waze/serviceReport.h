#pragma once
#include "repoReport.h"
#include "observer.h"

class ServiceReport : public Subject
{
private:
	RepoReport repoReport;
public:
	ServiceReport(RepoReport repo);
	~ServiceReport();
	ServiceReport() {}
	void addReport(string description, string, int, int, bool);
	vector<Report> getAll();
	int getSize();
	

};
