#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_starcatalogue.h"
#include "StarTableModel.h"
#include "astronomer.h"


class starcatalogue : public QMainWindow
{
    Q_OBJECT

public:
    starcatalogue(StarTableModel* model, Astronomer& astronomer, QWidget *parent = nullptr);
    ~starcatalogue() override;
    void connectSignalsAndSlots();
    void addStar(); 
    void checkBoxShowOnlyConstellation();

private:
    Ui::starcatalogueClass ui;
    StarTableModel* model;
    Astronomer& astronomer;

};
