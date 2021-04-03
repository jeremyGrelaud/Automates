package Automate_1;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-8.txt");
		
		automate.afficher_automate(automate);   
		/*
		Etat new_etat = automate.getEtatByNom("1");
		
		System.out.println(new_etat.getTransi().get("a").get(0).getNom());
		System.out.println(automate.getNbTransitions());
		
		Etat new_etat2 = automate.getEtatByNom("0");
		List<Etat> liste = new_etat2.getTransi().get("a");
		for(int i=0; i<liste.size();i++) {
			System.out.println(liste.get(i).getNom());
		}
		List<Etat> liste2 = new_etat2.getTransi().get("b");
		for(int i=0; i<liste2.size();i++) {
			System.out.println(liste2.get(i).getNom());
		}
		*/

	}
	

}


