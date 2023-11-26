#include "CSVRepo.h"
//#include <Windows.h>
#include <QDesktopServices>
#include <QDir>
#include <QProcess>
#include <QUrl>
#include <fstream>

CSVRepo::CSVRepo() 
{
}

CSVRepo::~CSVRepo()
{
}

void CSVRepo::write_to_file()
{
	ofstream file("adoption_list.csv");
	file << "Breed,Name,Age,Photo\n";
	for (auto dog : this->dogs)
	{
		file << dog.get_breed() << "," << dog.get_name() << "," << dog.get_age() << "," << dog.get_photo() << "\n";
	}
	file.close();
}

void CSVRepo::open_file()
{
	QString filePath = QString::fromStdString("adoption_list.csv");

	QProcess::startDetached("cmd.exe", QStringList() << "/c" << "start" << "\"" << filePath << "\"");
}

