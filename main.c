#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "automate.h"


int main()
{
    printf("---- Automate Analyser - Groupe XX ----\n");
    char path[] = ""; /// B10-8.txt

    if(ask_path(path) != 0){
        exit(0);
    }

    FILE* fichier = NULL;
    fichier = fopen(path, "r");
    rewind(fichier); // pour s'assurer que le fichier est bien au début

    automate_t * monAutomate = NULL;
    monAutomate = lire_automate_fichier(fichier);

    afficher_caract_automate(monAutomate, path);

    int a = is_asynchronus(monAutomate);


    fclose(fichier);

    return 0;
}



