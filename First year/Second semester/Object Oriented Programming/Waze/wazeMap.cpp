#include "wazeMap.h"
#include <QGraphicsView>
#include <QGraphicsScene>
#include <QGraphicsRectItem>

wazeMap::wazeMap(ServiceDriver& serviceDriver, QWidget *parent)
	: QMainWindow(parent), serviceDriver{ serviceDriver }
{
	ui.setupUi(this);
	vector<Driver> drivers = this->serviceDriver.getAll();
	QGraphicsView* graphicsView = new QGraphicsView(this);
	QGraphicsScene* scene = new QGraphicsScene(this);
	int x = 0;
	int y = 0;
	int offset = 0;
	for (auto d : drivers) {
		QGraphicsRectItem* rectangle = new QGraphicsRectItem();
		int latitude = d.getLatitude();
		int longitude = d.getLongitude();
		double width = 100;  
		double height = 50; 
		rectangle->setRect(longitude + offset, latitude + offset, width, height);
		QGraphicsTextItem* text = new QGraphicsTextItem();
		text->setPlainText(QString::fromStdString(d.getName())+ " " + QString::number(d.getLatitude()) + " " + QString::number(d.getLongitude()));
		QPointF textPosition(longitude + offset + 2, latitude + offset + 2); 
		text->setPos(textPosition);
		QBrush rectangleBrush(Qt::blue);
		rectangle->setBrush(rectangleBrush);
		scene->addItem(rectangle);
		scene->addItem(text);
		offset += 125; 
	}
	graphicsView->setScene(scene);
	setCentralWidget(graphicsView);
	QBrush viewBrush(Qt::green);
	graphicsView->setBackgroundBrush(viewBrush);
	
}

wazeMap::~wazeMap()
{}
