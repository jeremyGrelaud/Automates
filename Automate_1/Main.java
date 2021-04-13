package Automate_1;

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-8.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-1.txt");
		Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-2.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-3.txt");
		
		Automate determinise = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-1.txt");
		
		Automate copie = new Automate(automate);
		
		//copie.completion();
		//copie.standardisation();
		//copie.est_un_automate_complet();
		System.out.println("");
		copie.afficher_automate(); 
		/*
		copie.est_un_automate_deterministe();
		copie.est_un_automate_complet();
		*/
		System.out.println("");
		copie = copie.determinise();
		copie.afficher_automate();
		
		
		/*
		System.out.println("");
		copie.automate_complementaire();
		copie.afficher_automate();
		*/
		
		/*
		System.out.println(copie.reconnaitre_mot_automate("aba"));
		copie.reconnaitre_plusieurs_mot();
		*/
		
		//determinise.reconnaitre_plusieurs_mot();

		
		//System.out.println(copie.est_standard());
		//copie.afficher_automate();
		
		//System.out.println("");
		//automate.afficher_automate();
		/*marche pas encore*/
		/*
		automate.determinisation(automate);
		automate.afficher_automate();
		*/
		
		
		
		//System.out.println(automate.est_standard());
		
		//automate.standardisation(); 
		
		//automate.afficher_automate();   
		
		//automate.est_un_automate_asynchrone();
		
		//automate.est_un_automate_deterministe();
		
		//automate.est_un_automate_complet();
		
		//copie = copie.completion(); 
		
		//System.out.println("");
		//copie.afficher_automate();
		
			
		//automate.automate_complementaire(automate);
		//automate.afficher_automate();
		
		//System.out.println(automate.reconnaitre("aa", automate));
		
		/*
		System.out.println("determinisation");
		Automate deter = automate.determinisation_et_completion_synchrone(automate);
		deter.afficher_automate(deter);
		*/
		
		//System.out.println(automate.reconnaitre_mot_automate_determinsite("aba"));
		
		//automate.afficher_automate();

	}
	

}
