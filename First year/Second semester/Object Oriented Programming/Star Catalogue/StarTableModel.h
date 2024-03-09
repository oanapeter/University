#pragma once
#include <qabstractitemmodel.h>
#include "repoStar.h"
class StarTableModel : public QAbstractTableModel
{
private:
	RepoStar& repo;
public:

	StarTableModel(RepoStar& repo) : repo{ repo } {};
	~StarTableModel();
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
	QVariant headerData(int section, Qt::Orientation orientation, int role) const ;
	bool setData(const QModelIndex& index, const QVariant& value, int role = Qt::EditRole);
	Qt::ItemFlags flags(const QModelIndex& index) const;
	void addStar(const Star& star);
	vector<Star>& getAll();
};

