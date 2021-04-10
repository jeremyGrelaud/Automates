
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Automate automate = FileReader.createAutomateObject("D:/Cours EFREI/Semestre 4/Programmation en Java/Automates/src/B10-8.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Cours EFREI/Semestre 4/Programmation en Java/Automates/src/B10-1.txt");
		Automate automate = FileReader.createAutomateObject("D:/Cours EFREI/Semestre 4/Programmation en Java/Automates/src/B10-2.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Cours EFREI/Semestre 4/Programmation en Java/Automates/src/B10-3.txt");
		
		Automate copie = new Automate(automate);
		
		automate.afficher_automate();
		//System.out.println(automate.est_standard());
		
		//automate.standardisation(); 
		
		//automate.afficher_automate();   
		
		//automate.est_un_automate_asynchrone();
		
		//automate.est_un_automate_deterministe();
		
		//automate.est_un_automate_complet();
		
		copie.completion(); 
		
		System.out.println("");
		copie.afficher_automate();
		
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
