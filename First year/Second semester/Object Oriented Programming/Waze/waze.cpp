#include "waze.h"
#include <qmessagebox.h>

waze::waze(ServiceDriver& serviceDriver, ServiceReport& serviceReport, Driver& driver, QWidget *parent)
    : QMainWindow(parent), serviceDriver{ serviceDriver }, serviceReport{ serviceReport }, driver{ driver }
{
    ui.setupUi(this);
    string name = driver.getName();
    serviceReport.addObserver(this);
    this->setWindowTitle(QString::fromStdString(name));
    this->ui.statusLineEdit->setText(QString::fromStdString(driver.getStatus()));
    this->ui.latitudeLineEdit->setText(QString::fromStdString(to_string(driver.getLatitude())));
    this->ui.longitudeLineEdit->setText(QString::fromStdString(to_string(driver.getLongitude())));
    this->ui.scoreLineEdit->setText(QString::fromStdString(to_string(driver.getScore())));
    if (driver.getStatus() == "baby")
        setStyleSheet("background-color: pink;");
    else if (driver.getStatus() == "grown-up")
        setStyleSheet("background-color: yellow;");
    else
        setStyleSheet("background-color: grey;");
    this->connectSignalsAndSlots();
    this->populate();
}

waze::~waze()
{}

void waze::update()
{
	this->populate();
}

void waze::populate()
{
    vector<Report> reports = this->serviceReport.getAll();
    this->ui.driversReportsListWidget->clear();
    for (auto r : reports)
    {
        int x1 = this->driver.getLatitude();
        int y1 = this->driver.getLongitude();
        int x2 = r.getLatitude();
        int y2 = r.getLongitude();
        double distance = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
        if (distance <= 10)
        {
            string item = r.toString();
			this->ui.driversReportsListWidget->addItem(QString::fromStdString(item));
		}
    }
    ui.driversReportsListWidget->setCurrentRow(0);

}

void waze::addReport()
{
    string description = this->ui.reportDescriptionLineEdit->text().toStdString();
    //string name = driver.getName();
    QString title = this->windowTitle();
    string name = title.toStdString();
	int latitude = stoi(this->ui.reportLatitudeLineEdit->text().toStdString());
	int longitude = stoi(this->ui.reportLongitudeLineEdit->text().toStdString());
    int driverLatitude = this->driver.getLatitude();
    int driverLongitude = this->driver.getLongitude();
    double distance = sqrt(pow(latitude - driverLatitude, 2) + pow(longitude - driverLongitude, 2));
    if (distance > 20)
    {
		QMessageBox::critical(this, "Error", "The report is too far away from the driver!");
		return;
	}
	bool validated = false;
    try
    {
        this->serviceReport.addReport(description, name, latitude, longitude, validated);
    }
    catch (const char* msg)
    {
        QMessageBox::critical(this, "Error", msg);
    }
	this->populate();
}

void waze::connectSignalsAndSlots()
{
	QObject::connect(this->ui.addPushButton, &QPushButton::clicked, this, &waze::addReport);
	QObject::connect(this->ui.northPushButton, &QPushButton::clicked, this, &waze::increaseForNorth);
	QObject::connect(this->ui.southPushButton, &QPushButton::clicked, this, &waze::decreaseForSouth);
	QObject::connect(this->ui.westPushButton, &QPushButton::clicked, this, &waze::decreaseForWest);
	QObject::connect(this->ui.eastPushButton, &QPushButton::clicked, this, &waze::increaseForEast);
}

void waze::increaseForNorth()
{
	int latitude = this->driver.getLatitude();
	latitude+=1;
	this->driver.setLatitude(latitude);
	this->ui.latitudeLineEdit->setText(QString::fromStdString(to_string(latitude)));
	this->populate();
}

void waze::decreaseForSouth()
{
	int latitude = this->driver.getLatitude();
	latitude-=1;
	this->driver.setLatitude(latitude);
	this->ui.latitudeLineEdit->setText(QString::fromStdString(to_string(latitude)));
	this->populate();
}

void waze::decreaseForWest()
{
	int longitude = this->driver.getLongitude();
	longitude-=1;
	this->driver.setLongitude(longitude);
	this->ui.longitudeLineEdit->setText(QString::fromStdString(to_string(longitude)));
	this->populate();
}

void waze::increaseForEast()
{
	int longitude = this->driver.getLongitude();
    longitude += 1;
	this->driver.setLongitude(longitude);
	this->ui.longitudeLineEdit->setText(QString::fromStdString(to_string(longitude)));
	this->populate();
}


