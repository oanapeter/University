#include "StarTableModel.h"
#include <qmessagebox.h>

//StarTableModel::StarTableModel(RepoStar& repo) : repo{ repo } {}

StarTableModel::~StarTableModel()
{
}

int StarTableModel::rowCount(const QModelIndex& parent) const
{
	return this->repo.getAll().size();
}

int StarTableModel::columnCount(const QModelIndex& parent) const
{
	return 5;
}

QVariant StarTableModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();
	vector<Star> stars = this->repo.getAll();
	Star s = stars.at(row);
	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		switch (column)
		{
		case 0:
			return QString::fromStdString(s.getName());
		case 1:
			return QString::fromStdString(s.getConstellation());
		case 2:
			return QString::number(s.getRA());
		case 3:
			return QString::number(s.getDec());
		case 4:
			return QString::number(s.getDiameter());
		default:
			break;
		}
	}
	return QVariant();
}

QVariant StarTableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal) {
			switch (section)
			{
			case 0:
				return QString("Name");
			case 1:
				return QString("Constellation");
			case 2:
				return QString("RA");
			case 3:
				return QString("Dec");
			case 4:
				return QString("Diameter");
			default:
				break;
			}
		}
	}
	return QVariant();
}

bool StarTableModel::setData(const QModelIndex& index, const QVariant& value, int role)
{
	if(role != Qt::EditRole)
		return false;

	int row = index.row();
	int column = index.column();
	vector<Star>& stars = this->repo.getAll();
	Star& current_star = stars.at(row);
	switch (column)
	{
	case 0:
	{	std::string name{ value.toString().toStdString() };
		if (name.empty())
		{
			QMessageBox::critical(nullptr, "Error", "Name cannot be empty.");
			return false;
		}
		current_star.setName(name);
		break;
	}
	case 1:
		break;
	case 2:
	{
		int RA = value.toInt();
		current_star.setRA(RA);
		break;
	}
	case 3:
	{
		int Dec = value.toInt();
		current_star.setDec(Dec);
		break;

	}
	case 4:
	{
		int diameter = value.toInt();
		if (diameter < 10)
		{
			QMessageBox::critical(nullptr, "Error", "Diameter cannot be less than 10.");
			return false;
		}
		current_star.setDiameter(diameter);
		break;
	}
	default:
		break;
	}
	emit dataChanged(index, index);
	return true;
}

Qt::ItemFlags StarTableModel::flags(const QModelIndex& index) const
{
	int column = index.column();
	if (column == 1)
		return Qt::ItemFlags{};
	return Qt::ItemIsSelectable | Qt::ItemIsEditable | Qt::ItemIsEnabled;
}

void StarTableModel::addStar(const Star& star)
{
	int n = this->repo.getAll().size();
	beginInsertRows(QModelIndex{}, n, n);
	this->repo.addStar(star);
	endInsertRows();
}

vector<Star>& StarTableModel::getAll()
{
	return this->repo.getAll();
}
