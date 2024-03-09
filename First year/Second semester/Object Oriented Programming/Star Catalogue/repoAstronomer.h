#pragma once
#include "astronomer.h"
#include "star.h"
#include <vector>
#include <string>
#include <fstream>
#include <algorithm>

class RepoAstronomer
{
private:
	vector<Astronomer> astronomers;
	string filename;
public:
	RepoAstronomer(vector<Astronomer>& astronomers,string filename) : astronomers{ astronomers }, filename{ filename } {}
	~RepoAstronomer();
	vector<Astronomer>&  getAll();
	void loadFromFile();
};
