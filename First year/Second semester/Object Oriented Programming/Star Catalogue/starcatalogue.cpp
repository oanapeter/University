#include "starcatalogue.h"
#include <QMessageBox>
#include "ui_starcatalogue.h"
#include <fstream>
#include <string>

starcatalogue::starcatalogue(StarTableModel* model, Astronomer& astronomer, QWidget *parent) : model{ model }, astronomer{ astronomer }, QMainWindow(parent)
{
    ui.setupUi(this);
    string title = "Astronomer " + astronomer.getName();
    this->ui.constellationLineEdit->setText(QString::fromStdString(astronomer.getAstronomerConstellation()));
    this->setWindowTitle(QString::fromStdString(title));
    ui.starsTableView->horizontalHeader()->setSectionResizeMode(QHeaderView::Stretch);
    this->ui.starsTableView->setModel(model);
    this->connectSignalsAndSlots();



}

starcatalogue::~starcatalogue()
{}

void starcatalogue::connectSignalsAndSlots()
{
	QObject::connect(this->ui.pushButton, &QPushButton::clicked, this, &starcatalogue::addStar);
    QObject::connect(this->ui.checkBox, &QCheckBox::clicked, this, &starcatalogue::checkBoxShowOnlyConstellation);
}

void starcatalogue::addStar()
{
    string name = ui.nameLineEdit->text().toStdString();
    string constellation = ui.constellationLineEdit->text().toStdString();
    int RA = ui.RAlineEdit->text().toInt();
    int dec = ui.decLineEdit->text().toInt();
    int diameter = ui.diameterLineEdit->text().toInt();
    Star s( name, constellation, RA, dec, diameter );
    try
    {
        model->addStar(s);
    }
    catch (const char* msg)
    {
		QMessageBox::critical(this, "Error", msg);
	}
}

void starcatalogue::checkBoxShowOnlyConstellation()
{
    vector<Star>& stars = this->model->getAll();
    string astronomerConstellation = ui.constellationLineEdit->text().toStdString();

    if (ui.checkBox->isChecked())
    {
        for (int i = 0; i < stars.size(); i++)
        {
            if (stars[i].getConstellation() != astronomerConstellation)
            {
                this->ui.starsTableView->hideRow(i);
            }
            else
            {
                this->ui.starsTableView->showRow(i);
            }
        }
    }
    else
    {
        for (int i = 0; i < stars.size(); i++)
        {
            this->ui.starsTableView->showRow(i);
        }
    }
}



