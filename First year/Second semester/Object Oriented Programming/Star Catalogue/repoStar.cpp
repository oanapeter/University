#include "repoStar.h"
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>
using namespace std;


RepoStar::~RepoStar()
{}

vector<Star>& RepoStar::getAll()
{	
	sort(stars.begin(), stars.end(), [](Star& s1, Star& s2) {return s1.getConstellation() < s2.getConstellation(); });
		return this->stars;
}

//vector<Star> RepoStar::getStarsSortedByConstellation()
//{
//	vector<Star> sortedStars;
//	sort(sortedStars.begin(), sortedStars.end(), [](Star& s1, Star& s2) {return s1.getConstellation() < s2.getConstellation(); });
//	return sortedStars;
//}

void RepoStar::saveToFile()
{
	ofstream f(this->filename);
	for (auto s : this->stars)
		f << s;
	f.close();
}

void RepoStar::addStar(Star star)
{
	int index = findStarByName(star.getName());
	if (star.getName() == "")
		throw "Star's name cannot be empty!";
	if (index != -1)
		throw "Star already in the catalogue!";
	if(star.getName().size() == 0)
		throw "Star's name cannot be empty!";
	if(star.getDiameter() < 10)
		throw "Star's diameter cannot be less than 10!";
	this->stars.push_back(star);
	saveToFile();
}

int RepoStar::findStarByName(string name)
{
	int index = -1;
	vector<Star>::iterator it;
	it = find_if(this->stars.begin(), this->stars.end(), [&name](Star& star) {return name == star.getName(); });
	if (it != this->stars.end()) {
		index = distance(this->stars.begin(), it);
	}
	return index;
}

void RepoStar::loadFromFile()
{
	ifstream f(this->filename);
	Star s;
	while (f >> s)
		this->stars.push_back(s);
	f.close();
}

