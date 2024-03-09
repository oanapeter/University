#pragma once
#include "star.h"
#include <vector>
#include <string>
#include <fstream>
#include <algorithm>
using namespace std;

class RepoStar
{
private:
	vector<Star> stars;
	string filename;
public:
	RepoStar(vector<Star>& stars, string filename) : stars{ stars }, filename{ filename } {}
	~RepoStar();
	vector<Star>&  getAll();
	//vector<Star> getStarsSortedByConstellation();
	void saveToFile();
	void addStar(Star);
	int findStarByName(string);
	void loadFromFile();
};
