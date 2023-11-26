#pragma once

#include <QMainWindow>
#include "ui_wazeMap.h"
#include "serviceDriver.h"

class wazeMap : public QMainWindow
{
	Q_OBJECT

public:
	wazeMap(ServiceDriver& serviceDriver, QWidget *parent = nullptr);
	~wazeMap();

private:
	Ui::wazeMapClass ui;
	ServiceDriver& serviceDriver;
};
