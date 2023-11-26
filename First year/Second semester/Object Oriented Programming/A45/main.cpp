#include <stdlib.h>
#include "dynamic_array.h"
#include "repo.h"
#include "service.h"
#include "ui.h"
#include <crtdbg.h>
#include "tests.h"

void run_program()
{
    Ui ui;
    ui.start();
}

int main()
{
    test_all();
    run_program();

    _CrtDumpMemoryLeaks();
    system("pause");


    return 0;

}