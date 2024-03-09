#include "repoAstronomer.h"
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
using namespace std;


RepoAstronomer::~RepoAstronomer()
{}

vector<Astronomer>& RepoAstronomer::getAll()
{
	return this->astronomers;
}

void RepoAstronomer::loadFromFile()
{
	ifstream f(this->filename);
	Astronomer a;
	while (f >> a)
		this->astronomers.push_back(a);
	f.close();
}