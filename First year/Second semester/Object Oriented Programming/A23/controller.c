#include <stdio.h>
#include <stdlib.h>

#include "repository.h"
#include "service.h"
#include "ui.h"

int main()
{
	Repository* repo = create_repo();
	Undo* undo = create_undo();
	Undo* redo = create_undo();
	Service* service = create_service(repo, undo, redo);
	Ui* ui = create_ui(service);
	add_estates(ui);
	print_menu(ui);
	tests(ui);

	return 0;
}


