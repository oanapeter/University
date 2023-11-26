#pragma once
#include "repo.h"

class CSVRepo : public Repo
{
public:
	CSVRepo();
	~CSVRepo();
	void write_to_file() override;
	void open_file() override;
};
