#pragma once
#include <qwidget.h>
#include "service.h"
#include "user_service.h"
#include <qlistwidget.h>
#include <qpushbutton.h>

class GUI : public QWidget {
private:
	Service& service;
	UserService& user_service;
	QPushButton* admin_button;
	QPushButton* user_button;
public:
	GUI(Service& service,UserService& user_service, QWidget* parent = 0);
	~GUI();
	void show_admin();
	void show_user();
	void initGUI();
	void connect_signals_and_slots();

};

class AdminGUI : public QWidget {
private:
	Service& service;
	QListWidget* dog_list;
	QLineEdit* breed_edit;
	QLineEdit* name_edit;
	QLineEdit* age_edit;
	QLineEdit* photo_edit;
	QPushButton* add_button;
	QPushButton* delete_button;
	QPushButton* update_button;
	int get_selected_index();
	void list_changed();

public:
	explicit AdminGUI(Service& service, QWidget* parent = 0);
	~AdminGUI();
	void init_adminGUI();
	void connect_signals_and_slots();
	void populate_list();
	void add_dog();
	void delete_dog();
	void update_dog();

};

class UserGUI : public QWidget {
private:
	UserService& user_service;
	QPushButton* see_file_button;
	QListWidget* user_dog_list;
	QListWidget* adoption_list;
	QLineEdit* breed_edit;
	QLineEdit* name_edit;
	QLineEdit* age_edit;
	QLineEdit* photo_edit;
	QPushButton* adopt_button;
	QPushButton* filter_button;
	QPushButton* next_button;
	QLineEdit* breed_filter_edit;
	QLineEdit* age_filter_edit;
	
public:
	QLineEdit* file_type_edit;
	explicit UserGUI(UserService& user_service, QWidget* parent = 0);
	~UserGUI();
	void init_userGUI();
	void connect_signals_and_slots();
	void populate_list();
	void populate_adoption_list();
	void list_selection_changed();
	void list_selection_changed_filter();
	int get_selected_index();
	void show_next();
	void show_file();
	void adopt_dog();
	void filter_dogs();
	void adopt_dog_after_filter();
};



