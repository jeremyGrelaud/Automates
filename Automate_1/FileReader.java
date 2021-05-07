package Automate_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*la classe avec la methode statique permettant de générer un automate par lecture du fichier txt*/
public class FileReader {
 
	//methode de classe
	public static Automate createAutomateObject(String filePath) throws FileNotFoundException {

		File file = new File(filePath); // chemin vers le fichier txt
		Scanner scanner = new Scanner(file); 

		Automate automate = new Automate(); //creation de l'automate à partir du constructeur par défaut
 
		// On initialise les composantes à 0 ou null
		Alphabet alphabet = null;
		int line = 0;
		int transitions = 0;

		while (scanner.hasNextLine()) { // continue de lire tant qu'il y a des lignes
			String content = scanner.nextLine().trim(); // enleve les espaces
			String[] values = content.split(" "); // envoie chaque mot dans un tableau de chaînes de caracs

			// On utilise un switch car les 6 premières lignes du fichier doivent être traitée spécifiquement
			switch (line) {
			case 0: // Ligne 1 = le nbr de lettres de l'alphabet
				alphabet = new Alphabet(Integer.valueOf(content)); // construit l'objet alphabet
				automate.setAlphabet(alphabet);
				break;
			case 1: //le nbr d etats 
				break;
			case 2: // Ligne 3 : le nbr d'entrées et leurs noms
				loadEtats(content, automate, TypeEtat.ENTRY); 
				break;
			case 3: // Ligne 4 : pareil mais avec les sorties
				loadEtats(content, automate, TypeEtat.EXIT); 
				break;
			case 4: // Ligne 5 le nbr de transitions
				transitions = Integer.valueOf(content); // transitions contient le nbr de transitions sous forme d'un integer
				automate.setNbTransitions(transitions);
				break;
			default: // Les lignes suivantes sont toutes des transitions de la forme 1a2 : etat_depart lettre_alphabet etat_arrivee
				String[] word = content.split("[0-9!-@]"); //Si on a '01*9', ça retourne '*' donc la lettre de l'alphabet
			
				//en fait pour la transition epsilon elle est représentée par le vide  ""
				
				values = content.split("[a-zA-Z*]"); // Pour '01*9', ça retourne '01', '9' donc les noms des etats

				// Si l'etat de depart n'existe pas deja dans l'automate
				if (!automate.containsEtats(values[0])) {
					Etat state = new Etat(values[0]);
					automate.getEtats().add(state);
				}

				// Pareil pour l'etat d'arrivee
				
				if (!automate.containsEtats(values[1])) {
					Etat state = new Etat(values[1]);
					automate.getEtats().add(state);
				}

				Etat state = automate.getEtatByNom(values[0]);
				Etat next = automate.getEtatByNom(values[1]);
				
				String transitionWord = String.join("", word);
				state.addTransi(transitionWord, next);

				// quand on trouve des lettres n'etant pas encore dans l'alphabet on les ajoute
				if (!alphabet.getDictionary().contains(transitionWord) && !transitionWord.equals("")) {  //"" la transi epsilon
					alphabet.addWord(transitionWord);
				}

				break;
			}

			line++;
		}
		
		scanner.close();

		return automate;
	}

	/**
	 * Permet de charger l'etat dans l'automate
	 * @param contient un string avec le nbr d'etats et leurs noms
	 * @param automate de type Automate dans lequel on va ajouter les nouveaux etats
	 * @param types d'états : ENTRY, COMMON, EXIT
	 */
	private static void loadEtats(String content, Automate automaton, TypeEtat type) {
		String[] values = content.split(" ");
		int numStates = Integer.valueOf(values[0]); // dans values[0] il y a le nbr d'etats
		// dans les values[i] i>0 les noms des etats

		for (int i = 1; i <= numStates; i++) {
			if (!automaton.containsEtats(values[i])) { //s'il n'existe pas encore
				Etat state = new Etat(values[i]);
				state.addType(type);
				automaton.getEtats().add(state);
			}
			else {
				automaton.getEtatByNom(values[i]).addType(type);
			}
		}
	}
}