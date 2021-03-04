#include <stdio.h>
#include <stdlib.h>

#include "automate.h"

int is_asynchronus(automate_t* unAutomate) // return 1 if the automate has 1 epsilon or more
{
    return (unAutomate->nbEpsilon != 0);
}
