package Automate_1;

import java.util.ArrayList;
import java.util.List;

public class Alphabet{
	
	private List<String> dictionary; //c'est une suite de caractères correspondant aux lettres de l'alphabet
	//une liste de string correspondant aux différentes lettres de l'alphabet
	
	public Alphabet(Integer taille) {
		
		this.dictionary = new ArrayList<String>(taille); //taille initiale
	}
	
	public Alphabet(Alphabet alphabet) {
		
		this.dictionary = alphabet.getDictionary(); //taille initiale
	}
 

	public List<String> getDictionary() { //renvoie l alphabet sous forme de liste de string
		return this.dictionary;
	}


	public void addWord(String transitionWord) {
		this.dictionary.add(transitionWord); //on ajoute un nouveau string
		
	}
}
