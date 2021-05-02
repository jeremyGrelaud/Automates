<<<<<<< Updated upstream
<<<<<<< Updated upstream
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


=======
package Automate_1;

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-8.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-1.txt");
		Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-2.txt");
		//Automate automate = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-3.txt");
		
		Automate determinise = FileReader.createAutomateObject("D:/Efrei/L2/java/workspace/Automate/src/Automate_1/B10-1.txt");
		
		//Automate copie = new Automate(automate);
		
		automate.afficher_automate();
		/*marche pas encore*/
		automate.determinisation(automate);
		automate.afficher_automate();
		
		
		
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
>>>>>>> Stashed changes
=======
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

            PrintStream console = System.out; // Ajoute ça 

            PrintStream fileOut = new PrintStream("src/Automate_1/traces/trace14.txt"); // Et ça

            System.setOut(fileOut); // Et ça

            // TON CODE 
            //Execution();
            Trace();

            System.setOut(console); // Et ça à la fin

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
				System.out.println("(un chiffre de 1 a 44) ");
				mot = sc.nextInt();
				
				
				
				while(mot<1 || mot>44) {
					System.out.println("Attention :");
					System.out.println("(un chiffre de 1 a 44 ");
					mot = sc.nextInt();
				}
				
				
				String path = getChemin("A8-"+mot);
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
			
			System.out.println("Le programme se deroule selon le plan :");
			System.out.println("affichage, test presence epsilon, determinisation puis completion, minimisation, ");
			System.out.println("reconaissance de mots, complementarisation, reconaissance de mots");
			Scanner sc = new Scanner(System.in);
			int mot;
			Automate automate = null;
			
			System.out.println("Veuillez ecrire le numero de l'automate :");
			System.out.println("(un chiffre de 1 a 44) ");
			mot = sc.nextInt();	
			String path = getChemin("A8-"+mot);
			try {
				automate = FileReader.createAutomateObject(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Suite d'opérations
			System.out.println("");
			System.out.println("Automate choisi : ");
			automate.afficher_automate();
			
			//Test présence epsilons
			System.out.println("");
			automate.est_un_automate_asynchrone();
			
			//determinisation et completion si nécessaire
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
				System.out.println("Automate déjà déterministe");
			}
			System.out.println("");
			System.out.println("Automate determinisé");
			automate.afficher_automate();
			
			if(!automate.est_un_automate_complet()) {
				automate.completion();
			}
			else {
				System.out.println("Automate déjà complet");
			}
			System.out.println("");
			System.out.println("Automate complet : ");
			automate.afficher_automate();
			
			System.out.println("Processus de minimisation :");
			automate = automate.minimisation();
			
			//automate.reconnaitre_plusieurs_mot();
			System.out.println("");
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
			
			System.out.println("");
			System.out.println("Reconaissance du mot ab: ");
			if(automate.reconnaitre_mot_automate("ab")) {
				System.out.println("Mot reconnu");
			}
			else {
				System.out.println("Mot non reconnu");
			}
			
			
		}
			
	

}
>>>>>>> Stashed changes
