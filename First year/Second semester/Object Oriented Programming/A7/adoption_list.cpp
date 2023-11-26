#include "adoption_list.h"
#include <fstream>
//#include <Windows.h>

AdoptionList::AdoptionList()
{
}

AdoptionList::AdoptionList(const Service& service, string file_path)
{
	this->service = service;
	this->file_path = file_path;
}

AdoptionList::~AdoptionList()
{
}

CSVAdoptionList::CSVAdoptionList(const Service& service, string file_path)
{
	this->service = service;
	this->file_path = file_path;
}

CSVAdoptionList::~CSVAdoptionList()
{
}

void CSVAdoptionList::write_to_file(const Service& service)
{
	this->service = service;
	ofstream file(this->file_path.c_str());
	vector<Dog> dogs = this->service.get_adoption_list_service();
	file << "Breed,Name,Age,Photo\n";
	for (auto dog : dogs)
	{
		file << dog.get_breed() << "," << dog.get_name() << "," << dog.get_age() << "," << dog.get_photo() << "\n";
	}
	file.close();
}

//void CSVAdoptionList::show_adoption_list()
//{
//	ShellExecuteA(NULL, NULL, "notepad.exe", this->file_path.c_str(), NULL, SW_SHOWMAXIMIZED);
//}

HTMLAdoptionList::HTMLAdoptionList(const Service& service, string file_path)
{
	this->service = service;
	this->file_path = file_path;
}

HTMLAdoptionList::~HTMLAdoptionList()
{
}

void HTMLAdoptionList::write_to_file(const Service& service)
{
	this->service = service;
	ofstream file(this->file_path.c_str());
	vector<Dog> dogs = this->service.get_adoption_list_service();
	file << "<!DOCTYPE html>\n";
	file << "<html>\n";
	file << "<head>\n";
	file << "<title>Adoption List</title>\n";
	file << "</head>\n";
	file << "<body>\n";
	file << "<table border=\"1\">\n";
	file << "<tr>\n";
	file << "<td>Breed</td>\n";
	file << "<td>Name</td>\n";
	file << "<td>Age</td>\n";
	file << "<td>Photo</td>\n";
	file << "</tr>\n";
	for (auto dog : dogs)
	{
		file << "<tr>\n";
		file << "<td>" << dog.get_breed() << "</td>\n";
		file << "<td>" << dog.get_name() << "</td>\n";
		file << "<td>" << dog.get_age() << "</td>\n";
		file << "<td><a href=\"" << dog.get_photo() << "\">Link</a></td>\n";
		file << "</tr>\n";
	}
	file << "</table>\n";
	file << "</body>\n";
	file << "</html>\n";
	file.close();
}

//void HTMLAdoptionList::show_adoption_list()
//{
//	ShellExecuteA(NULL, "open", "adoption_list.html", NULL, NULL, SW_SHOWMAXIMIZED);
//}