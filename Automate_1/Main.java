package Automate_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {

		/*
		String path = getChemin("B10-10");
		
		Automate automate = FileReader.createAutomateObject(path);*/
	
		
		try { // Ajoute un try + catch

            PrintStream console = System.out; // Ajoute �a 

            PrintStream fileOut = new PrintStream("src/Automate_1/traces/trace1.txt"); // Et �a

            System.setOut(fileOut); // Et �a

            // TON CODE 
            //Execution();
            Trace();

            System.setOut(console); // Et �a � la fin

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		
		
		
		
		
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
			
			System.out.println("Fin du programme");
			
			
			sc.close();
			
		}
		
		private static void Trace() {
			
			Scanner sc = new Scanner(System.in);
			int mot;
			Automate automate = null;
			
			System.out.println("Veuillez ecrire le numero de l'automate :");
			System.out.println("(un chiffre de 1 a 45) ");
			mot = sc.nextInt();	
			String path = getChemin("#"+mot);
			try {
				automate = FileReader.createAutomateObject(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Suite d'op�rations
			System.out.println("Automate choisi : ");
			automate.afficher_automate();
			
			//Test pr�sence epsilons
			automate.est_un_automate_asynchrone();
			
			//determinisation et completion si n�cessaire
			if(!automate.est_un_automate_deterministe()) {
				if(automate.est_un_automate_asynchrone()) {
					automate = automate.elimination_epsilon();
					automate = automate.determinise();
				}
				else {
					automate = automate.determinise();
				}
				
			}
			else {
				System.out.println("Automate d�j� d�terministe");
			}
			System.out.println("Automate determinis�");
			automate.afficher_automate();
			
			if(!automate.est_un_automate_complet()) {
				automate.completion();
			}
			else {
				System.out.println("Automate d�j� complet");
			}
			System.out.println("Automate complet : ");
			automate.afficher_automate();
			
			automate = automate.minimisation();
			
			//automate.reconnaitre_plusieurs_mot();
			System.out.println("Reconaissance du mot ab: ");
			if(automate.reconnaitre_mot_automate("ab")) {
				System.out.println("Mot reconnu");
			}
			else {
				System.out.println("Mot non reconnu");
			}
			
			automate.automate_complementaire();
			System.out.println("Automate complementaire");
			automate.afficher_automate();
			
			System.out.println("Reconaissance du mot ab: ");
			if(automate.reconnaitre_mot_automate("ab")) {
				System.out.println("Mot reconnu");
			}
			else {
				System.out.println("Mot non reconnu");
			}
			
			
		}
			
	

}
