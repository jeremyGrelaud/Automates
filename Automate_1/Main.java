package Automate_1;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-8.txt");
		Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-1.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-2.txt");
		
		//automate.afficher_automate(automate);    
		
		//automate.standardisation(automate);
		
		//automate.afficher_automate(automate);   
		
		//automate.est_un_automate_asynchrone(automate);
		
		//automate.est_un_automate_deterministe(automate);
		
		//automate.est_un_automate_complet(automate);
		
		//automate.completion(automate);
		
		//System.out.println("");
		//automate.afficher_automate(automate);
		
		//automate.automate_complementaire(automate);
		//automate.afficher_automate(automate);
		
		//System.out.println(automate.reconnaitre("aa", automate));
		
		/*
		System.out.println("determinisation");
		Automate deter = automate.determinisation_et_completion_synchrone(automate);
		deter.afficher_automate(deter);
		*/
		
		System.out.println(automate.reconnaitre_mot_automate_determinsite("aba"));
		//automate.afficher_automate(automate);

	}
	

}


