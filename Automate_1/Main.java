package Automate_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {

		/*
		String path = getChemin("B10-10");
		
		Automate automate = FileReader.createAutomateObject(path);*/
	
		Execution();
		
		
		
		//Automate automate = choisirAutomate();
		//Automate copie = new Automate(automate);
		
	
		
		
		
		//copie.completion();
		//copie.standardisation();
		//copie.est_un_automate_complet();
		//System.out.println("");
		//copie.afficher_automate(); 
		
		
		/*
		copie = copie.elimination_epsilon();
		copie.afficher_automate();	
		copie =  copie.determinise();
		copie.completion();
		copie.afficher_automate();*/
		
		//copie = copie.minimisation();
		//copie.afficher_automate();
		
		//copie.programme();
		

	}

		//renvoi le path de l'automate choisi en entrant son nom
		private static String getChemin(String automate_choisi) {
		
		
		File root = null;
		
		try {
			root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String chemin = root.toString();
		chemin = chemin.replaceAll("bin", "");
	
		String path = chemin + "src/Automate_1/files/" + automate_choisi +".txt";
		
		return path;
		
	}
		
		private static Automate choisirAutomate() {
			
			Scanner sc = new Scanner(System.in);
			int mot;
			Automate automate = null;
			
				System.out.println("Veuillez ecrire le numero de l'automate :");
				System.out.println("(un chiffre de 1 a 45) ");
				mot = sc.nextInt();
				
				
				
				while(mot<1 || mot>45) {
					System.out.println("Attention :");
					System.out.println("(un chiffre de 1 a 45 ");
					mot = sc.nextInt();
				}
				
				
				String path = getChemin("#"+mot);
				try {
					automate = FileReader.createAutomateObject(path);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			//sc.close();
			return automate;
		}
		
		
		private static void Execution() {
			
			Scanner sc = new Scanner(System.in);
			String mot = "";
			
			do {
				Automate automate = choisirAutomate();
				automate.programme();
			
				
				System.out.println("Si vous voulez quitter tapez EXIT");
				System.out.println("Pour continuer tapez autre chose");
				mot = sc.nextLine();
				
				
			}while(!mot.equals("EXIT"));
			
			
			sc.close();
			
		}
	

}
