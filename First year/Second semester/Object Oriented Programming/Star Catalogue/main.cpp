#include "starcatalogue.h"
#include <QtWidgets/QApplication>
#include "repoStar.h"
#include "repoAstronomer.h"
#include "StarTableModel.h"
#include "starcatalogue.h"
#include "astronomer.h"


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    vector<Astronomer> astronomers;
    astronomers.reserve(10);
    RepoAstronomer repoAstronomer{ astronomers, "astronomers.txt" };
    repoAstronomer.loadFromFile();

    vector<Star> stars;
    stars.reserve(10);
    RepoStar repoStar{ stars, "stars.txt" };
    repoStar.loadFromFile();


    StarTableModel* model = new StarTableModel{ repoStar };
    vector<starcatalogue*> windows;
    for (auto astronomer : repoAstronomer.getAll())
    {
		starcatalogue* w = new starcatalogue{ model, astronomer };
		w->show();
		windows.push_back(w);
	}

    return a.exec();
}
