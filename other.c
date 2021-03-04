#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "automate.h"


int one_letter(FILE* file)  // return 1 if there s only one type of char; return 0 else
{
    char fst_letter;
    char letter;
    int alpha = 1;
    fscanf(file,"%c",&fst_letter);
    do {
        alpha = fscanf(file, "%c", &letter);
        if(alpha != -1 && letter != fst_letter)
            return 0;
    }while(alpha != -1);

    return 1;
}

int number_of_caracters(FILE* fichier)
{
    rewind(fichier); // rewinds the file in case of previous reading
    int compteur = 0;
    while(fgetc(fichier) != EOF) { compteur++; }
    rewind(fichier); // rewinds the file in case of next reading
    return compteur;
}

int ask_path(char* path)
{
    int continuer, size;
    do{
        continuer = 0;
        printf("Write the path of your file : \n");
        scanf("%s", path);
        size = strlen(path);

        if(path[size-1] != 't' || path[size-2] != 'x' || path[size-3] != 't' || path[size-4] != '.')
        {
            printf("Error ! You don't have seize a \".txt\" file.\n");
            continuer = 1;
        }
        else
        {
            FILE* test = NULL;
            test = fopen(path, "r");
            if(test == NULL)
            {
                printf("Error ! It seems the path is invalid. Try again ...\n");
                continuer = 1;
            }
            else
            {
                if(number_of_caracters(test) == 0){
                    printf("It seems this file do exists, but is empty. Try again ...\n");
                    continuer = 1;
                }
                else if(one_letter(test) == 1){
                    printf("It seems this file do exists, but only contains one different letter. It can't work. Try again ...\n");
                    continuer = 1;
                }
                rewind(test);
                fclose(test);
            }
        }
    }while(continuer);

    return continuer;

}
