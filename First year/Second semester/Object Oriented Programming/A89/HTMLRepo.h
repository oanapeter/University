#pragma once
#include "repo.h"

class HTMLRepo : public Repo
{
public:
	HTMLRepo();
	~HTMLRepo();
	void write_to_file() override;
	void open_file() override;
};
