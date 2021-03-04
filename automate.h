#ifndef AUTOMATE_H_INCLUDED
#define AUTOMATE_H_INCLUDED

#define DEBUG(nomVariable, variable) printf("%s = %d\n", nomVariable, variable);
#define rtr printf("\n\n");

/// Structures

typedef struct {
    int nbLetter;
    int nbEtat;
    int nbEtatsInit;
    int nbEtatsFin;
    int nbTransitions;
    int nbEpsilon;

    int * alphabet;
    int * etatsInit;
    int * etatsFin;
    int ** transitions;

}automate_t;

/// Prototypes

automate_t * lire_automate_fichier(FILE * fichier);
void afficher_caract_automate(automate_t * unAutomate, char* name);

int is_asynchronus(automate_t* unAutomate);

/// Other
int one_letter(FILE* file);
int number_of_caracters(FILE* fichier);
int ask_path(char* path);

#endif // AUTOMATE_H_INCLUDED
