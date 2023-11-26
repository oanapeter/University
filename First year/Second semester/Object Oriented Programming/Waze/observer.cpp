#include "observer.h"

#include <algorithm>
void Subject::addObserver(Observer* o)
{
    this->observers.push_back(o);
}



void Subject::notify()
{
    for (Observer* o : observers)
        o->update();
}