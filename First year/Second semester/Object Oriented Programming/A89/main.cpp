#include "a89.h"
#include "service.h"
#include "repo.h"
#include "GUI.h"
#include "service.h"
#include <qapplication.h>
#include <qpushbutton.h>
#include <QtWidgets/QApplication>


int main(int argc, char* argv[])
{
    QApplication a(argc, argv);
    QFont font{ "Helvetica", 16 };
    a.setFont(font);
    QPalette pal = a.palette();
    pal.setColor(QPalette::Window, QColorConstants::Svg::blueviolet);
    a.setPalette(pal);
    /*vector<Dog> dogs;
    dogs.reserve(15);*/
    string file_name = "dogs.txt";
    Repo repo{ "dogs.txt" };
    Repo* adoption_list = new Repo{ "" };
    Service service{ repo };
    UserService user_service{ repo, adoption_list };
    GUI gui{ service, user_service };
    gui.show();
    return QApplication::exec();


}
