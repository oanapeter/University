#include <Windows.h>
//#include <shellapi.h>
#include "HTMLRepo.h"
#include "repo.h"
#include <QDesktopServices>
#include <QUrl>
#include <QProcess>
#include <fstream>
#include <QMessageBox>
#include <QDir>
#include <QFileInfo>

HTMLRepo::HTMLRepo() 
{
}

HTMLRepo::~HTMLRepo()
{
}

void HTMLRepo::write_to_file()
{
	ofstream file("adoption_list.html");
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
	for (auto dog : this->dogs)
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

void HTMLRepo::open_file()
{
	QDesktopServices::openUrl(QUrl::fromLocalFile("adoption_list.html"));
}