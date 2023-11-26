#include "waze.h"
#include "serviceReport.h"
#include "serviceDriver.h"
#include "repoDriver.h"
#include "repoReport.h"
#include "wazeMap.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoDriver repoDriver{"drivers.txt"};
    repoDriver.loadFromFile();
    RepoReport repoReport{"reports.txt"};
    repoReport.loadFromFile();
    ServiceDriver serviceDriver{repoDriver};
    ServiceReport serviceReport{repoReport};
    vector<waze*> windows;
    for (auto d : serviceDriver.getAll())
    {
		waze* w = new waze{ serviceDriver, serviceReport, d };
		windows.push_back(w);
		w->show();
	}

    wazeMap map = wazeMap{ serviceDriver};
    map.show();

    return a.exec();
}
