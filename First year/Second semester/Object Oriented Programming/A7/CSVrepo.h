#pragma once
#include "service.h"

class AdoptionList {
private:
	string file_path;
	Service service;
public:
	AdoptionList();
	AdoptionList(const Service& service, string file_path);
	~AdoptionList();
	virtual void write_to_file(const Service& service) = 0;
	//virtual void show_adoption_list() = 0;

};

class CSVAdoptionList : public AdoptionList {
private:
	string file_path;
	Service service;
public:
	CSVAdoptionList(const Service& service, string file_path);
	~CSVAdoptionList();
	void write_to_file(const Service& service) override;
	//void show_adoption_list() override;
};

class HTMLAdoptionList : public AdoptionList {
private:
	string file_path;
	Service service;

public:
	HTMLAdoptionList(const Service& service, string file_path);
	~HTMLAdoptionList();
	void write_to_file(const Service& service) override;
	//void show_adoption_list() override;

};

