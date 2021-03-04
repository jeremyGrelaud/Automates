#include <stdio.h>
#include <stdlib.h>

#include "automate.h"

automate_t * lire_automate_fichier(FILE * fichier)
{
    automate_t * unAutomate = NULL;
    unAutomate = malloc(sizeof(automate_t));

    unAutomate->nbLetter = 0;
    unAutomate->nbEtat = 0;
    unAutomate->nbEtatsInit = 0;
    unAutomate->nbEtatsFin = 0;
    unAutomate->nbTransitions = 0;
    unAutomate->nbEpsilon = 0;

    fscanf(fichier, "%d\n%d", &unAutomate->nbLetter, &unAutomate->nbEtat);

    unAutomate->alphabet = malloc(unAutomate->nbLetter * sizeof(int));
    unAutomate->transitions = (int**) malloc(unAutomate->nbEtat* sizeof(int*));
    for(int i=0 ; i<unAutomate->nbEtat ; i++){
        unAutomate->transitions[i] = (int*) malloc(unAutomate->nbLetter* sizeof(int));

        for(int j=0 ; j<unAutomate->nbLetter ; j++){
                unAutomate->transitions[i][j] = -1; // pour simuler une case vide
        }
    }

    for(int i=0 ; i<unAutomate->nbLetter ; i++){
        unAutomate->alphabet[i] = 97+i;
    }

    int actualCaract = 0;

    fscanf(fichier, "%d", &unAutomate->nbEtatsInit);
    unAutomate->etatsInit = malloc(unAutomate->nbEtatsInit* sizeof(int));
    for(int i=0 ; i<unAutomate->nbEtatsInit ; i++){
        fscanf(fichier, " %d", &actualCaract);
        unAutomate->etatsInit[i] = actualCaract;
    }

    fscanf(fichier, "%d", &unAutomate->nbEtatsFin);
    unAutomate->etatsFin = malloc(unAutomate->nbEtatsFin* sizeof(int));
    for(int i=0 ; i<unAutomate->nbEtatsFin ; i++){
        fscanf(fichier, " %d", &actualCaract);
        unAutomate->etatsFin[i] = actualCaract;
    }

    fscanf(fichier, "%d\n", &unAutomate->nbTransitions);

    int etatDepart = -1;
    char lettre = -1;
    int etatArrive = -1;

    /// int previousDepart = -1, previousLetter = -1, nbSame = 0;

    for(int i=0 ; i<unAutomate->nbTransitions ; i++){
        fscanf(fichier, "%d%c%d\n", &etatDepart, &lettre, &etatArrive);

        if(lettre == 42){
            unAutomate->nbEpsilon++;
            for(int j=0 ; j<unAutomate->nbLetter ; j++){
                unAutomate->transitions[etatDepart][j] = etatArrive;
            }
        }
        else{
            unAutomate->transitions[etatDepart][lettre-97] = etatArrive;
        }
    }

    return unAutomate;

}


void afficher_caract_automate(automate_t * unAutomate, char* name)
{
    char newName[] = "";
    strcpy(newName, name);
    newName[strlen(name) - 4] = '\0'; // pour enlever le ".txt" du nom
    printf("\t--- Automate %s ---\n", newName);

    printf("\n%d symboles dans l'alphabet\nA = { ", unAutomate->nbLetter);
    for(int i=0 ; i < unAutomate->nbLetter ; i++){
        if(i != unAutomate->nbLetter-1){
            printf("%c , ", unAutomate->alphabet[i]);
        }
        else{
            printf("%c }", unAutomate->alphabet[i]);
        }
    }

    /*
    printf("\n%d etats\nQ = { ", nbEtat);
    for(int i=0 ; i<nbEtat ; i++){
        if(i != nbEtat-1) { printf("%d , ", unAutomate->allEtats[i].nomEtat); }
        else{ printf("%d }", unAutomate->allEtats[i].nomEtat); }
    }
    */

    printf("\n%d etats initiaux\nI = { ", unAutomate->nbEtatsInit);
    for(int i=0 ; i < unAutomate->nbEtatsInit ; i++){
        if(i != unAutomate->nbEtatsInit-1){
            printf("%d , ", unAutomate->etatsInit[i]);
        }
        else{
            printf("%d }", unAutomate->etatsInit[i]);
        }
    }

    printf("\n%d etats terminaux\nI = { ", unAutomate->nbEtatsFin);
    for(int i=0 ; i < unAutomate->nbEtatsFin ; i++){
        if(i != unAutomate->nbEtatsFin-1){
            printf("%d , ", unAutomate->etatsFin[i]);
        }
        else{
            printf("%d }", unAutomate->etatsFin[i]);
        }
    }

    printf("\n%d transitions", unAutomate->nbTransitions);
    if(unAutomate->nbEpsilon != 0){
        printf("(dont %d epsilon)", unAutomate->nbEpsilon);
    }

    printf("\n\n");

    printf("  | ");
    for(int i=0 ; i<unAutomate->nbLetter ; i++){ printf("%c ", 97+i); }
    printf("\n---");
    for(int i=0 ; i<unAutomate->nbLetter ; i++){ printf("--"); }
    printf("-\n");

    for(int i=0 ; i<unAutomate->nbEtat ; i++)
    {
        printf("%d | ", i);
        for(int j=0 ; j<unAutomate->nbLetter ; j++){
            printf("%d ", unAutomate->transitions[i][j]);
        }
        printf("\n");
    }

}
