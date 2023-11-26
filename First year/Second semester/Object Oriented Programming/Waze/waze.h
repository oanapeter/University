#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_waze.h"
#include "serviceReport.h"
#include "serviceDriver.h"


class waze : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    waze(ServiceDriver& serviceDriver, ServiceReport& serviceReport, Driver& driver, QWidget *parent = nullptr);
    ~waze();
    void update() override;
    void addReport();
    void populate();
    void connectSignalsAndSlots();
    void increaseForNorth();
    void decreaseForSouth();
    void decreaseForWest();
    void increaseForEast();
    


private:
    Ui::wazeClass ui;
    ServiceReport& serviceReport;
    ServiceDriver& serviceDriver;
    Driver& driver;
};
