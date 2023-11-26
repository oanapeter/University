#include <qlabel.h>
#include <qpushbutton.h>
#include <qlineedit.h>
#include <qformlayout.h>
#include <qlistwidget.h>
#include <QVBoxLayout>
#include <QFile>
#include <QProcess>
#include <QUrl>
#include <QMessageBox>
#include <QDesktopServices>
#include "GUI.h"

GUI::GUI(Service& service, UserService& user_service, QWidget* parent) : QWidget(parent), service{ service }, user_service{ user_service }
{
	initGUI();
	connect_signals_and_slots();
}

void GUI::initGUI()
{
	auto vertical_layout = new QVBoxLayout{ this };
	vertical_layout->setAlignment(Qt::AlignHCenter);
	auto title = new QLabel{ "Dog Shelter App" };
	auto mode = new QLabel{ "Select your mode:" };
	title->setStyleSheet("Qlabel { color: deeppink;}");
	mode->setStyleSheet("Qlabel { color: deeppink;}");
	vertical_layout->addWidget(title);
	vertical_layout->addWidget(mode);
	auto horizontal_layout = new QHBoxLayout{};
	vertical_layout->addLayout(horizontal_layout);
	admin_button = new QPushButton{ "ADMIN" };
	user_button = new QPushButton{ "USER" };
	horizontal_layout->addWidget(admin_button);
	horizontal_layout->addWidget(user_button);
}

void GUI::connect_signals_and_slots()
{
	QObject::connect(admin_button, &QPushButton::clicked, this, &GUI::show_admin);
	QObject::connect(user_button, &QPushButton::clicked, this, &GUI::show_user);
}

void GUI::show_admin()
{
	AdminGUI* admin_gui = new AdminGUI{ service };
	admin_gui->show();
}


void GUI::show_user()
{
	UserGUI* user_gui = new UserGUI{ user_service };
	user_gui->show();
}

AdminGUI::AdminGUI(Service& service, QWidget* parent) : QWidget(parent), service{ service } {
	init_adminGUI();
	populate_list();
	connect_signals_and_slots();

}

void AdminGUI::init_adminGUI()
{
	auto title = new QLabel{ "ADMIN MODE" };
	title->setStyleSheet("Qlabel { color: purple;}");
	auto layout = new QVBoxLayout{ this };
	auto main_layout = new QHBoxLayout{};
	layout->setAlignment(Qt::AlignHCenter);
	layout->addWidget(title);
	layout->addLayout(main_layout);
	dog_list = new QListWidget{};
	main_layout->addWidget(dog_list);

	auto dog_data_layout = new QVBoxLayout{};
	main_layout->addLayout(dog_data_layout);
	auto dog_data_form = new QFormLayout{};
	dog_data_layout->addLayout(dog_data_form);

	auto breed_label = new QLabel{ "Breed:" };
	breed_label->setStyleSheet("Qlabel { color: purple;}");
	breed_edit = new QLineEdit{};
	breed_label->setBuddy(breed_edit);

	auto name_label = new QLabel{ "Name:" };
	name_label->setStyleSheet("Qlabel { color: purple;}");
	name_edit = new QLineEdit{};
	name_label->setBuddy(name_edit);

	auto age_label = new QLabel{ "Age:" };
	age_label->setStyleSheet("Qlabel { color: purple;}");
	age_edit = new QLineEdit{};
	age_label->setBuddy(age_edit);

	auto photo_label = new QLabel{ "Photo:" };
	photo_label->setStyleSheet("Qlabel { color: purple;}");
	photo_edit = new QLineEdit{};
	photo_label->setBuddy(photo_edit);

	dog_data_form->addRow(breed_label, breed_edit);
	dog_data_form->addRow(name_label, name_edit);
	dog_data_form->addRow(age_label, age_edit);
	dog_data_form->addRow(photo_label, photo_edit);


	auto buttons_layout = new QHBoxLayout{};
	dog_data_layout->addLayout(buttons_layout);
	add_button = new QPushButton{ "ADD" };
	update_button = new QPushButton{ "UPDATE" };
	delete_button = new QPushButton{ "DELETE" };
	buttons_layout->addWidget(add_button);
	buttons_layout->addWidget(update_button);
	buttons_layout->addWidget(delete_button);


}

void AdminGUI::connect_signals_and_slots()
{
	QObject::connect(dog_list, &QListWidget::itemSelectionChanged, this, &AdminGUI::list_changed);
	QObject::connect(add_button, &QPushButton::clicked, this, &AdminGUI::add_dog);
	QObject::connect(update_button, &QPushButton::clicked, this, &AdminGUI::update_dog);
	QObject::connect(delete_button, &QPushButton::clicked, this, &AdminGUI::delete_dog);
}


void AdminGUI::delete_dog()
{
	populate_list();
	string name = name_edit->text().toStdString();
	this->service.delete_dog_service(name);
	populate_list();
	dog_list->setCurrentRow(0);

}

int AdminGUI::get_selected_index()
{
	int index = dog_list->currentRow();
	return index;
}

void AdminGUI::list_changed()
{
	int index = get_selected_index();
	if (index < 0)
		return;
	vector<Dog> dogs = this->service.get_all_data();
	Dog dog = dogs.at(index);
	breed_edit->setText(QString::fromStdString(dog.get_breed()));
	name_edit->setText(QString::fromStdString(dog.get_name()));
	age_edit->setText(QString::number(dog.get_age()));
	photo_edit->setText(QString::fromStdString(dog.get_photo()));
}

void AdminGUI::add_dog()
{
	string breed = breed_edit->text().toStdString();
	string name = name_edit->text().toStdString();
	int age = age_edit->text().toInt();
	string photo = photo_edit->text().toStdString();
	this->service.add_dog_service(breed, name, age, photo);
	populate_list();
	dog_list->setCurrentRow(this->service.get_all_data().size() - 1);
}

void AdminGUI::populate_list()
{
	dog_list->clear();
	vector<Dog> dogs = this->service.get_all_data();
	for (Dog dog : dogs)
	{
		string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
		dog_list->addItem(QString::fromStdString(display));
	}
	dog_list->setCurrentRow(0);
}

void AdminGUI::update_dog()
{
	int index = get_selected_index();
	if (index < 0)
		return;
	string old_name = dog_list->currentItem()->text().split(" | ")[1].split(": ")[1].toStdString();
	string breed = breed_edit->text().toStdString();
	string name = name_edit->text().toStdString();
	int age = age_edit->text().toInt();
	string photo = photo_edit->text().toStdString();
	this->service.update_dog_service(old_name, breed, name, age, photo);
	populate_list();
	dog_list->setCurrentRow(index);
}

UserGUI::UserGUI(UserService& user_service, QWidget* parent) : QWidget(parent), user_service{ user_service } {
	init_userGUI();
	connect_signals_and_slots();
	populate_list();
}

void UserGUI::init_userGUI()
{
	auto main_layout = new QVBoxLayout{ this };
	auto type_layout = new QHBoxLayout{};
	main_layout->addLayout(type_layout);
	auto type_form_layout = new QFormLayout{};
	type_layout->addLayout(type_form_layout);
	auto file_type_label = new QLabel{ "File type in which you want to store the adoption list:" };
	file_type_label->setStyleSheet("Qlabel { color: purple;}");
	auto file_type_edit = new QLineEdit{};
	file_type_label->setBuddy(file_type_edit);
	type_form_layout->addRow(file_type_label, file_type_edit);
	see_file_button = new QPushButton{ "See file" };
	type_layout->addWidget(see_file_button);
	auto main_list_layout = new QHBoxLayout{};
	main_layout->addLayout(main_list_layout);
	auto dog_list_layout = new QVBoxLayout{};
	main_list_layout->addLayout(dog_list_layout);
	user_dog_list = new QListWidget{};
	auto dog_list_label = new QLabel{ "Dogs:" };
	dog_list_label->setStyleSheet("Qlabel { color: purple;}");
	dog_list_layout->addWidget(dog_list_label);
	dog_list_layout->addWidget(user_dog_list);

	auto dog_data_info = new QFormLayout{};

	main_list_layout->addLayout(dog_data_info);

	auto breed_label = new QLabel{ "Breed:" };
	breed_label->setStyleSheet("Qlabel { color: purple;}");
	breed_edit = new QLineEdit{};
	breed_label->setBuddy(breed_edit);

	auto name_label = new QLabel{ "Name:" };
	name_label->setStyleSheet("Qlabel { color: purple;}");
	name_edit = new QLineEdit{};
	name_label->setBuddy(name_edit);

	auto age_label = new QLabel{ "Age:" };
	age_label->setStyleSheet("Qlabel { color: purple;}");
	age_edit = new QLineEdit{};
	age_label->setBuddy(age_edit);

	auto photo_label = new QLabel{ "Photo:" };
	photo_label->setStyleSheet("Qlabel { color: purple;}");
	photo_edit = new QLineEdit{};
	photo_label->setBuddy(photo_edit);

	dog_data_info->addRow(breed_label, breed_edit);
	dog_data_info->addRow(name_label, name_edit);
	dog_data_info->addRow(age_label, age_edit);
	dog_data_info->addRow(photo_label, photo_edit);

	auto adoption_list_layout = new QVBoxLayout{};
	main_list_layout->addLayout(adoption_list_layout);
	auto adoption_list_label = new QLabel{ "Adoption list:" };
	adoption_list_label->setStyleSheet("Qlabel { color: purple;}");
	adoption_list = new QListWidget{};
	adoption_list_layout->addWidget(adoption_list_label);
	adoption_list_layout->addWidget(adoption_list);
	auto buttons_layout = new QHBoxLayout{};
	main_layout->addLayout(buttons_layout);
	adopt_button = new QPushButton{ "Adopt" };
	next_button = new QPushButton{ "Next" };
	filter_button = new QPushButton{ "Filter" };
	buttons_layout->addWidget(adopt_button);
	buttons_layout->addWidget(next_button);
	buttons_layout->addWidget(filter_button);

	auto filter_layout = new QFormLayout{};
	auto breed_filter_label = new QLabel{ "Breed:" };
	breed_filter_label->setStyleSheet("Qlabel { color: purple;}");
	breed_filter_edit = new QLineEdit{};
	breed_filter_label->setBuddy(breed_filter_edit);

	auto age_filter_label = new QLabel{ "Age:" };
	age_filter_label->setStyleSheet("Qlabel { color: purple;}");
	age_filter_edit = new QLineEdit{};
	age_filter_label->setBuddy(age_filter_edit);


	filter_layout->addRow(breed_filter_label, breed_filter_edit);
	filter_layout->addRow(age_filter_label, age_filter_edit);
	main_layout->addLayout(filter_layout);



}

void UserGUI::populate_list()
{
	user_dog_list->clear();
	vector<Dog> dogs;
	dogs = this->user_service.get_all_data();
	if (breed_filter_edit->text().toStdString().size() == 0 && age_filter_edit->text().toStdString().size() ==0 )
	{
		for (Dog dog : dogs)
		{
			string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
			user_dog_list->addItem(QString::fromStdString(display));
		}
	}
	else
	{
		string breed = breed_filter_edit->text().toStdString();
		int age = age_filter_edit->text().toInt();
		for (Dog dog : dogs)
		{
			if (dog.get_breed() == breed && dog.get_age() < age)
			{
				string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
				user_dog_list->addItem(QString::fromStdString(display));
			}
		}
	}
	if (user_dog_list->count() == 0)
	{
		vector<Dog> dogs = this->user_service.get_all_data();
		for (Dog dog : dogs)
		{
			string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
			user_dog_list->addItem(QString::fromStdString(display));
		}
	}
	user_dog_list->setCurrentRow(0);
}

void UserGUI::populate_adoption_list()
{
	adoption_list->clear();
	vector<Dog> dog_adoption_list = this->user_service.get_all_dogs();
	for (Dog dog : dog_adoption_list)
	{
		string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
		adoption_list->addItem(QString::fromStdString(display));
	}
}

void UserGUI::list_selection_changed()
{

	int index = get_selected_index();
	if (index < 0)
		return;
	//string dog = user_dog_list->currentItem()->text().toStdString();
	vector<Dog> dogs = this->user_service.get_all_data();
	Dog dog = dogs.at(index);
	breed_edit->setText(QString::fromStdString(dog.get_breed()));
	name_edit->setText(QString::fromStdString(dog.get_name()));
	age_edit->setText(QString::fromStdString(to_string(dog.get_age())));
	photo_edit->setText(QString::fromStdString(dog.get_photo()));
}

int UserGUI::get_selected_index()
{
	int index = user_dog_list->currentRow();
	return index;
}

void UserGUI::adopt_dog()
{

	//vector<Dog> dogs = this->user_service.get_all_data();
	//int index = get_selected_index();
	//Dog dog = dogs.at(index);
	/*this->user_service.add_dog_to_adoption_list(dog);
	populate_list();
	populate_adoption_list();
	user_dog_list->setCurrentRow(index);*/
	//string breed = breed_edit->text().toStdString();
	//populate_list();
	string name = name_edit->text().toStdString();
	vector<Dog> dogs = this->user_service.get_all_data();
	int index = 0;
	auto found = find_if(dogs.begin(), dogs.end(), [name](Dog dog) {return dog.get_name() == name; });
	if (found != dogs.end())
		index = distance(dogs.begin(), found);
	//int index = this->user_service.find_index_of_dog_by_name(name);
	Dog dog = dogs.at(index);
	this->user_service.add_dog_to_adoption_list(dog);
	populate_list();
	populate_adoption_list();
	//user_dog_list->setCurrentRow(index);


}

void UserGUI::show_file()
{

	string file_type = file_type_edit->text().toStdString();
	this->user_service.set_adoption_list_type(file_type);
	if (file_type == "CSV")
	{
		string file_path = "C:\\Users\\40752\\source\\repos\\a7\\a7\\adoption_list.csv";
		QFile file(QString::fromStdString(file_path));

		if (file.exists())
		{
			if (file.open(QIODevice::ReadOnly))
			{
				QTextStream in(&file);
				QString file_contents = in.readAll();
				file.close();

				// Display file contents in a message box
				QMessageBox::information(this, "CSV File Contents", file_contents);
			}
			else
			{
				QMessageBox::critical(this, "Error", "Failed to open the CSV file for reading.");
			}
		}
		else
		{
			QMessageBox::critical(this, "Error", "The CSV file does not exist.");
		}
	}
	else if (file_type == "HTML")
	{
		string file_path = "C:\\Users\\40752\\source\\repos\\a7\\a7\\adoption_list.html";
		QDesktopServices::openUrl(QUrl::fromLocalFile(QString::fromStdString(file_path)));
	}
	else
	{
		QMessageBox::warning(this, "Warning", "Invalid file type entered.");
	}
}


void UserGUI::show_next()
{
	static int index = user_dog_list->currentRow();
	vector<Dog> dogs = this->user_service.get_all_data();

	if (index < dogs.size())
	{
		breed_edit->setText(QString::fromStdString(dogs.at(index).get_breed()));
		name_edit->setText(QString::fromStdString(dogs.at(index).get_name()));
		age_edit->setText(QString::number(dogs.at(index).get_age()));
		photo_edit->setText(QString::fromStdString(dogs.at(index).get_photo()));
		index++;
	}
	else if(index == dogs.size()){
		index = 0;
	}
	user_dog_list->setCurrentRow(index);
}


void UserGUI::filter_dogs()
{

	string breed = breed_filter_edit->text().toStdString();
	int age = age_filter_edit->text().toInt();
	vector<Dog> filtered_dogs = this->user_service.get_filtered(breed, age);
	user_dog_list->clear();
	bool ok = false;
	for (Dog dog : filtered_dogs)
	{
		string display = "Breed: " + dog.get_breed() + " | Name: " + dog.get_name();
		user_dog_list->addItem(QString::fromStdString(display));
	}
	//if (filtered_dogs.size() > 0 && )
	//{
	//	user_dog_list->setCurrentRow(0);
	//	breed_edit->setText(QString::fromStdString(filtered_dogs.at(0).get_breed()));
	//	name_edit->setText(QString::fromStdString(filtered_dogs.at(0).get_name()));
	//	age_edit->setText(QString::number(filtered_dogs.at(0).get_age()));
	//	photo_edit->setText(QString::fromStdString(filtered_dogs.at(0).get_photo()));

	//}
	QObject::connect(user_dog_list, &QListWidget::itemSelectionChanged, this, &UserGUI::list_selection_changed_filter);

}

void UserGUI::list_selection_changed_filter()
{
	int index = get_selected_index();
	if (index < 0)
		return;
	vector<Dog> dogs = this->user_service.get_filtered(breed_filter_edit->text().toStdString(), age_filter_edit->text().toInt());
	Dog dog = dogs.at(index);
	breed_edit->setText(QString::fromStdString(dog.get_breed()));
	name_edit->setText(QString::fromStdString(dog.get_name()));
	age_edit->setText(QString::fromStdString(to_string(dog.get_age())));
	photo_edit->setText(QString::fromStdString(dog.get_photo()));
}



AdminGUI::~AdminGUI()
{
}

GUI::~GUI()
{
}

UserGUI::~UserGUI()
{
}

void UserGUI::connect_signals_and_slots()
{
	QObject::connect(user_dog_list, &QListWidget::itemSelectionChanged, this, &UserGUI::list_selection_changed);
	QObject::connect(adopt_button, &QPushButton::clicked, this, &UserGUI::adopt_dog);
	QObject::connect(next_button, &QPushButton::clicked, this, &UserGUI::show_next);
	QObject::connect(filter_button, &QPushButton::clicked, this, &UserGUI::filter_dogs);
	QObject::connect(see_file_button, &QPushButton::clicked, this, &UserGUI::show_file);
	//QObject::connect(filter_button, &QPushButton::clicked, this, &UserGUI::populate_list);
}


