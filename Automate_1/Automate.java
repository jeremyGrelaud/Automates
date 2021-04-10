<<<<<<< Updated upstream
package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* modèle fichier à lire
2    = nbr lettres
5    = nbr etats
2 0 4   = nbr etats initiaux et sa liste
2 0 3   = nbr etats finaux et sa liste
10      = nbr transitions
0a1     = et toutes les transi
0a2
0b1
0b2
1a2
2*1
3a1
3b2
4b3
4a4
 */

public class Automate {
	private List<Etat> etats; /*liste des différents etats de l'automate*/
	private Alphabet alphabet; /*alphabet sous forme de liste de String*/
	private int nbTransitions;
	
	public Automate() {
		this.etats = new ArrayList<Etat>();
		this.alphabet = new Alphabet(0);
		this.nbTransitions = 0;
		//quand on initialise on sait pas encore on set plus tard en lisant le fichier
	}
	public Automate(Automate automate) { //constructeur de copie
		this.etats = automate.getEtats();
		this.alphabet = new Alphabet(automate.getAlphabet());
		this.nbTransitions = automate.getNbTransitions();
	}
	
	
	public int getNbTransitions() {
		return nbTransitions;
	}

	public void setNbTransitions(int nbTransitions) {
		this.nbTransitions = nbTransitions;
	}

	public Alphabet getAlphabet() {
		return new Alphabet(this.alphabet);
	}

	public void setAlphabet(Alphabet alphabet) {
		Alphabet nouveau = new Alphabet(this.alphabet);
		this.alphabet = nouveau;
	}
	/*
	public List<Etat> getEtats() {
		List<Etat> nouveau = new ArrayList<Etat>();
		for(int i=0; i<this.etats.size();i++) {
			nouveau.add(this.etats.get(i));
		}
		return nouveau;
	}
	*/
	
	public List<Etat> getEtats() {
		return this.etats;
	}

	public void setEtats(List<Etat> etats) {
		List<Etat> nouveau = new ArrayList<Etat>();
		for(int i=0; i<this.etats.size();i++) {
			nouveau.add(this.etats.get(i));
		}
		this.etats = nouveau;
	}

	public boolean containsEtats(String la_chaine) {  //faut check les id des etats de la liste et voir si c'est dedans
		//for(int i=0;i<etats.size() && i<getNbTransitions()  ;i++) { //ca devrait être le nbr de transis associées à cet état
		//	if(etats.get(i).getNom().equals(la_chaine)) {
		//		return true;
		//	}
		//}
		for (Etat etats : getEtats()){
			if(etats.getNom().equals(la_chaine)) {
				return true;
			}
		}
		return false; 
	}

	
	public Etat getEtatByNom(String id) { //on veut recup un etat specifique de l automate avec son id
		//on parcours la liste jusqu'à trouver l'etat correpsondant
		if(this.containsEtats(id)) {
			for(int i=0;i<this.getEtats().size();i++) {
				if(etats.get(i).getNom().equals(id)) {
					return etats.get(i);
				}
			}
			
		}
			return null;
	}
	/* version avec la boucle foreach
	
    public Etat getEtatByNom(String id) {
        for (Etat state : this.etats) {
            if (state.getNom().equals(id)) {
                return state;
            }
        }
        
        return null; // if the state is not present in the automaton
    }*/
	
	public void afficher_automate(Automate automate) {
		
		/*
		System.out.println(automate.getAlphabet().getDictionary().get(2));
		Etat new_etat = automate.getEtatByNom("2");
		new_etat.addTransi("*", new_etat);
		new_etat.getTransi().get("*").get(0).getNom(); 
		System.out.println();
		*/
		//2*1 normalement
		//c'est chelou les transis epsilon on pas l'air d'être accessible
		
		
		
		System.out.print("L'alphabet : ");
		for(int i=0; i<automate.alphabet.getDictionary().size();i++) {
				System.out.print(automate.alphabet.getDictionary().get(i)+" ");
		} 
		
		System.out.println("");
		
		System.out.print("Les etats : ");
		for(int i=0; i<automate.etats.size();i++) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
		} 
		
		
		System.out.println("");
		
		System.out.print("Les etats intiaux : ");
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.ENTRY)) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
			}
		} 
		
		System.out.println("");
		
		System.out.print("Les etats finaux : ");
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.EXIT)) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
			}
		}
		
		//afficher la table de transi x)
		System.out.println("");
		
		System.out.print(getNbTransitions() + " transitions :");
		System.out.println("");
		/*
	    for (Etat etat : this.etats) {
	    	for(int k=0;k<etat.getTransi().size();k++) { //le nbr de key pour cet etat
	    		//System.out.println(etat.getTransi().size());
	    		//System.out.println(automate.getAlphabet().getDictionary().get(k));
	    		System.out.println(automate.getAlphabet().getDictionary().get(k));
	    		for(int j=0; j<etat.getTransi().get(automate.getAlphabet().getDictionary().get(k)).size();j++) {
	    			
	    			System.out.print("["+etat.getNom()+"] - [");
	    			System.out.print(automate.getAlphabet().getDictionary().get(k)+"] - [");
	    			System.out.print(etat.getTransi().get(getAlphabet().getDictionary().get(k)).get(j).getNom()+"]");
	    			System.out.println("");
	    		}
	    		
	    	} 
        }
	    */
		 for (Etat etat : this.etats) {
		    for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
		    	System.out.println(etat.getNom()+" " +clef+  " " +Arrays.toString(etat.getTransi().get(clef).toArray()));
		    }
		    		
		    
	     }

	}
	
	/*** STANDARDISATION*/
	public boolean est_standard(Automate automate) { //il faut une seule entrée et aucune transi vers cette entrée
		int compteur = 0;
		Etat new_etat = null;
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				compteur++;
				new_etat = new Etat(etat);
			}
		} 
		
		if(compteur!=1) {
			return false;
		}
		else {
			//vérifier qu'aucune transi ne mène à cet entrée
			String id = new_etat.getNom();
			//aucune transi ne doit avoir comme etat d'arrivée un etat.getNom() == id
			
			for(Etat etat : this.etats) {
				for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
					
					if(Arrays.toString(etat.getTransi().get(clef).toArray()).contains(id)) {
						return false;
					}
			    }
			}
			//à la fin de la boucle si rien a étét return alors l'automate est standard
			return true;
		}
	}
	
	public Automate standardisation(Automate automate) {
		
		if(est_standard(automate)) {
			System.out.println("Votre automate est deja standard ! ");
			return automate;
		}
		else {
			//faire une fonction qui recup tous les etats de TypeEtat ENTRY
			List<Etat> etats_entree = get_toutes_entree(automate);
			
			//on créer un nouvel etat d'entrée et on va copier les transitions des anciennes entrées dedans
			Etat nouvelle_entree = new Etat("i");
			
			for(Etat etat : etats_entree) {
				for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
					for(int i=0;i<etat.getTransi().get(clef).size();i++){
						nouvelle_entree.addTransi( clef, etat.getTransi().get(clef).get(i));
					}
				}
			}
			
			//suppr le type ENTRY des anciennes
			for(Etat etat : etats_entree) {
				for(int i=0;i<etat.getTypes().size();i++) {
					if(etat.getTypes().get(i) == TypeEtat.ENTRY) {
						etat.getTypes().remove(i);
					}
				}		
			}
					
			//ajouter la nouvelle entree a l automate
			nouvelle_entree.addType(TypeEtat.ENTRY);
			automate.etats.add(nouvelle_entree);
			
			
			return automate;
			
		}
		
	}
	
	private List<Etat> get_toutes_entree(Automate automate) {
		List<Etat> liste = new ArrayList<Etat>();
		
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				liste.add(etat);
			}
		} 
		return liste;
	}
	
	
	/*** DETERMINISATION*/
	public boolean est_un_automate_asynchrone(Automate automate) {
		int compteur=0;
		String epsilon = "*";
		
		for(Etat etat : this.etats) {
			for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
				
				if(clef == "") {
					System.out.println("\nL'automate est asynchrone car :\n"); //va être affiché plusieurs fois
					System.out.println(etat.getNom() +" - "+ epsilon+" - "+etat.getTransi().get(clef).toString());
					compteur++;
					
				}
			} 
		}
		if(compteur>0) {
			return true;
		}
		else {
			System.out.println("L'automate n'est pas asynchrone");
			return false;
		}
		
	}
	
	public boolean est_un_automate_deterministe(Automate automate) { //1 seule entrée et liste pour une clef = taille 1 pas plus
		
		int compteur_entrée = 0;
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.ENTRY)) {
				compteur_entrée++;
			}
		} 
		
		if(compteur_entrée>1) {
			System.out.println("\nNon déterministe car plus d'une entrée");
			return false;
		}
		
		for(Etat etat : this.etats) {
			for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
				if(etat.getTransi().get(clef).size()>1) {
					System.out.println("\nNon déterministe car on a la transi : "+etat.getNom()+"-"+clef+"-"+etat.getTransi().get(clef).toString());
					return false;
				}
			} 
		}
		System.out.println("\nAutomate déterministe");
		return true;
	}
	
	
	/*
	public Automate determinisation(Automate automate) {
		Automate automate_deter_async = new Automate();
		
		if(est_un_automate_asynchrone(automate)) {
			 automate_deter_async = determinisation_et_completion_asynchrone(automate);
		}
		else {
			if(est_un_automate_deterministe(automate)) {
				if(est_un_automate_complet(automate)) {
					 automate_deter_async = new Automate(automate);
				}
				else {
					 automate_deter_async = completion(automate);
				}
			}
			else {
				 automate_deter_async = determinisation_et_completion_synchrone (automate);
			}
		}
		
		afficher_automate_deterministe_complet(automate_deter_async);

	}
	*/
	
	public Automate determinisation(Automate automate) {
		Automate automate_deter_async;
		
		
		if(est_un_automate_asynchrone(automate)) {
			System.out.println("deter asynchrone pas encore faite");
			return null;
		}
		else {
			if(est_un_automate_deterministe(automate)) {
				if(est_un_automate_complet(automate)) {
					 automate_deter_async = new Automate(automate);
				}
				else {
					 automate_deter_async = completion(automate);
				}
			}
			else {
				 automate_deter_async = determinisation_et_completion_synchrone (automate);
			}
		}
		
		return automate_deter_async;
	}
	
	public Automate determinisation_et_completion_synchrone(Automate automate) {
		//faire une copie défensive
		Automate ancien_automate = new Automate(this);
		
		Automate automate_deter_sync = new Automate();
		automate_deter_sync.setAlphabet(ancien_automate.getAlphabet());
		List<Etat> liste_entrees = new ArrayList<Etat>();
		
		for(Etat etat : ancien_automate.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				liste_entrees.add(etat);
			}
		}
		//s'il y a plusieurs entrées on va devoir les fusionner en une nouvelle entrée
		Etat new_entry = new Etat(liste_entrees.get(0));
		
		for(int i=1;i<liste_entrees.size();i++) {
			new_entry.getTypes().addAll(liste_entrees.get(i).getTypes());
			for(String clef : liste_entrees.get(i).getTransi().keySet()) { //pour toutes les clefs
				for(int j=0;j<liste_entrees.get(i).getTransi().get(clef).size();j++){
					new_entry.addTransi( clef, liste_entrees.get(i).getTransi().get(clef).get(j));
				}
			}
			String ancien_nom1 = new_entry.getNom();
			String ancien_nom2 = liste_entrees.get(i).getNom();
			new_entry.setNom(new_entry.getNom() + liste_entrees.get(i).getNom());
			//il faudrait aussi changer les noms des transis associées
			
			/***FONCTION FUSION*/
			for(Etat etat : ancien_automate.etats) {
				if(etat.getNom()==ancien_nom1) {
					etat.setNom(new_entry.getNom());
				}
				if(etat.getNom()==ancien_nom2) {
					etat.setNom(new_entry.getNom());
				}
				
				for(String clef : etat.getTransi().keySet() ) {
					for(int j=0;j<etat.getTransi().get(clef).size();j++){
						if(etat.getTransi().get(clef).get(j).getNom()==ancien_nom1) {
							etat.getTransi().get(clef).get(j).setNom(new_entry.getNom());
						}
						if(etat.getTransi().get(clef).get(j).getNom()==ancien_nom2) {
							etat.getTransi().get(clef).get(j).setNom(new_entry.getNom());
						}
					}
				}
			}
			/***FIN FUSION*/
		}
		
		automate_deter_sync.getEtats().add(new_entry);
		
		/*Check pour la transi de notre etat si les etats existent déjà***/
		//les fusionner si besoin et rajouter les nouveaux etats
		//continuer la boucle do while tant qu'on ajoute un nouvel etat
		
		/*
		for(Etat etat : ancien_automate.etats) {
			if(etat.getNom()!= new_entry.getNom()) {
				automate_deter_sync.getEtats().add(etat);
			}
			
		}
		*/
		boolean continuer = true;
		do {
			int ancienne_taille = automate_deter_sync.getEtats().size();
			for(String clef : new_entry.getTransi().keySet() ) {
				if(new_entry.getTransi().get(clef).size()>1) {
					//fusion faudrait fusionner 2 à 2 et rappeler ça jusqu'à ce que la taille de la liste = 1
					Etat nouvel_etat;
					do {
					
						Etat etat1 = new_entry.getTransi().get(clef).get(0);
						Etat etat2 = new_entry.getTransi().get(clef).get(1);
						nouvel_etat = fusion(etat1, etat2);
						new_entry.getTransi().get(clef).remove(0);
						
					}while(new_entry.getTransi().get(clef).size()!=1);
				
					automate_deter_sync.getEtats().add(nouvel_etat);
				}
				else if (new_entry.getTransi().get(clef).size()==1){
					automate_deter_sync.getEtats().add(new_entry.getTransi().get(clef).get(0));
				}
			}
			
			if(automate_deter_sync.getEtats().size()==ancienne_taille) {
				continuer = false;
			}
			
		}while(continuer);

		//on a l'entrée maintenant on va générer les nouvelles transitions
		
		//on regarde les etats d'arrivé qu'on a pour notre entrée
		//si ces etats ne sont pas dans notre liste d'état alors on créer de nouveaux états
		//on récup ses transis avec l'ancien automate
		//on ajoute à notre table
		//et nouveau tour de boucle on regarde s'il y a des nouveaux états 
		//etc
		
		
		//compléter avant de renvoyer 
		return automate_deter_sync;
	}
	
	private Etat fusion(Etat state1, Etat state2) {
		// TODO Auto-generated method stub
		return null;
	}
	/*** COMPLETION*/
	public boolean est_un_automate_complet(Automate automate) {
		if((automate.est_un_automate_asynchrone(automate)==false) && (automate.est_un_automate_deterministe(automate)==true)) {
			//check si chaque etat possède le bon nbr de clefs
			int compteur=0;
			for(Etat etat : this.etats) {
				for(String clef : etat.getTransi().keySet()) { 
						compteur++;
					}
					if(compteur!=automate.getAlphabet().getDictionary().size()) {
						System.out.println("\nAutomate non complet car "+etat.getNom()+"n'a pas de transi pour toutes les lettres de l'alphabet");
						
						return false;
					}
					compteur=0;
			}
			
			System.out.println("\nAutomate complet");
			return true;
		}
		else {
			System.out.println("\nVotre automate n'est pas synchrone et déterministe il ne peut pas être complet");
			return false;
		}
	}
	
	public Automate completion(Automate automate) {
		
			//créer un etat poubelle
			Etat p = new Etat("p");
			List<String> liste = automate.getAlphabet().getDictionary();
			for(int i=0; i<liste.size();i++) {
				p.addTransi(liste.get(i), p);
				this.nbTransitions = automate.getNbTransitions()+1;
			}
			//on rajoute le nouvel etat p à l'automate
			automate.etats.add(p);
				
			//rajouter les transitions manquantes
			for(Etat etat : this.etats) {
				if(etat.getTransi().size()!=liste.size()) { //nous renvoie le nbr de clef pour cet etat
					//on rajoute 
					for(int i=0;i<liste.size();i++) {
						if(!etat.getTransi().containsKey(liste.get(i))) { //check si la transi n'existe pas
							etat.addTransi(liste.get(i), p);
							this.nbTransitions = automate.getNbTransitions()+1;
						}
					}
					
				}
			}
			return automate;
		}
	
	
	/***MINIMISATION*/
	//doit afficher si c'était déjà minimal
	//on minimise un automate synchrone, déterministe, complet
	
	/***RECONAISSANCE DES MOTS*/
	/*
	public void lire_mot(String mot) {
		while(mot.length()!=0) {
			reconnaitre(mot,this);
			lire_mot(mot);
		}
	}
	*/
	
	
	
	public boolean reconnaitre_mot_automate_determinsite ( String mot ) {
		
		if(!this.est_un_automate_deterministe(this)) {
			System.out.println("Impossible de vérifier votre automate n'est pas déterminsite");
			return false;
		}
		else {	
			Etat etat_courant = null; /*etat de départ de l'algo*/
			for(Etat etat : this.etats) {
				if(etat.getTypes().contains(TypeEtat.ENTRY)) {
					 etat_courant = etat;
				}
			}
		
			/*il pourrait y avoir plusieurs entrées si pas déterministe*/
			
			/*si mot vide*/
			if(mot.equals("")) {
				if(etat_courant.getTypes().contains(TypeEtat.EXIT)) {
					return true;
				}
				else {
					return false;
				}
			}
						
			int index_mot=0;
			String symbole_courant = String.valueOf(mot.charAt(index_mot));
			if(existe_transi(etat_courant, symbole_courant)) {
				etat_courant = etat_courant.getTransi().get(symbole_courant).get(0);
			}
			else {
				return false;
			}
		
			do {
	
				/*si on est à la fin du mot et que la lettre correspond à la denrière lettre du mot et qu'on est sur une sortie c bon*/
				if(index_mot==(mot.length()-1) && symbole_courant.equals(String.valueOf(mot.charAt(mot.length()-1)))){
					if(etat_courant.getTypes().contains(TypeEtat.EXIT)) {
						return true;
					}
					
					else {
						return false;
					}
					
				}
				String symbole_futur = String.valueOf(mot.charAt(index_mot+1));
				if(existe_transi(etat_courant, symbole_futur)) {
					/*il peut y avoir plusieurs etats cibles si non deterministe*/
					Etat etat_cible = etat_courant.getTransi().get(symbole_futur).get(0);
					etat_courant = etat_cible;
					index_mot++;
					symbole_courant = String.valueOf(mot.charAt(index_mot));
				}
				else {
					return false;
				}
				
				
			}while(index_mot<mot.length());
			
			/*si tjrs pas reconnu alors false*/
			return false;
		}
		
	}
	
	private boolean existe_transi(Etat etat_courant, String symbole_courant) {
		if(etat_courant.getTransi().get(symbole_courant)== null) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	//return true si le mot est reconnu par l'automate
	
	/*
	public boolean reconnaitre(String mot, Automate automate) {
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				//on commence à essayer de lire
				int i=0;
				boolean continuer = true;
				Etat new_entry = new Etat(etat);
				while(continuer && i<mot.length()) {
					if(new_entry.getTransi().get(String.valueOf(mot.charAt(i)))!=null) {
						for(int j=0; j<new_entry.getTransi().get(String.valueOf(mot.charAt(i))).size();j++) {
							reconnaitre(String.valueOf(mot.substring(1)), new_entry.getTransi().get(String.valueOf(mot.charAt(i))).get(i));
						}
						//new_entry = new_entry.getTransi().get(String.valueOf(mot.charAt(i)));
						//il faudrait faire une fc récursive qui appel avec tous les états terminaux de la liste
						//puis quand ça arrive à la fin du mot faut regarder si c'est un état de sortie
						i++;
					}
					else {
						continuer = false;
					}
				}
				if(continuer==true && new_entry.getTypes().contains(TypeEtat.EXIT)) {
					return true;
				}
				
			}
		} 
		//non reconnu
		return false; 
	}
	*/
	
	

	/* ca marche pas
	private void reconnaitre(String mot, Etat new_entry) {
		int i=0;
		boolean continuer = true;
		while(continuer && i<mot.length()) {
			if(new_entry.getTransi().get(String.valueOf(mot.charAt(i)))!=null) {
				for(int j=0; j<new_entry.getTransi().get(String.valueOf(mot.charAt(i))).size();j++) {
					reconnaitre(String.valueOf(mot.substring(1)), new_entry.getTransi().get(String.valueOf(mot.charAt(i))).get(i));
				}
				i++;
			}
			else {
				continuer = false;
			}
		}
		
	}
	*/
	/***LANGAGE COMPLEMENTAIRE*/
	/*
	public void construction_complementaire_reconaissance(Automate automate) {
		Automate automate_complementaire = automate.automate_complementaire(automate);
		automate_complementaire.afficher_automate(automate_complementaire);
		//
		//
		//lecture
		//
		//	
	}
	*/
	
	//il faudrait indiquer à partir de quel type d'automate on obtient le compélmentaire : cad s'il était minimal ou non
	public Automate automate_complementaire(Automate automate) {
		if(!automate.est_un_automate_deterministe(automate) && !automate.est_un_automate_complet(automate)) {
			System.out.println("Impossible d'avoir le complémentaire l'automate n'est pas déterminsite et compelt");
			System.out.println("Automate renvoyé non modifié");
			return automate;
		}
		else {
			Automate automate_complementaire = new Automate(automate);
			List<Etat> liste_anciennes_sorties = new ArrayList<Etat>();
			List<Etat> liste_anciennes_non_sorties = new ArrayList<Etat>();
			
			for(Etat etat : this.etats) {
				if(etat.getTypes().contains(TypeEtat.EXIT)) {
					liste_anciennes_sorties.add(etat);
				}
				else {
					liste_anciennes_non_sorties.add(etat);
				}
			}
			
			for(Etat etat : liste_anciennes_sorties ) {
				for(int i=0;i<etat.getTypes().size();i++) {
					if(etat.getTypes().get(i)==TypeEtat.EXIT) {
						etat.getTypes().remove(i);
					}	
				}
			}
			
			for(Etat etat : liste_anciennes_non_sorties ) {
				etat.getTypes().add(TypeEtat.EXIT);
			}
			
			
			System.out.println("Automate complémentaire créé");
			return automate_complementaire;
		}
		
	}
}
=======
package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* mod�le fichier � lire
2    = nbr lettres
5    = nbr etats
2 0 4   = nbr etats initiaux et sa liste
2 0 3   = nbr etats finaux et sa liste
10      = nbr transitions
0a1     = et toutes les transi
0a2
0b1
0b2
1a2
2*1
3a1
3b2
4b3
4a4
 */

public class Automate {
	private List<Etat> etats; /*liste des diff�rents etats de l'automate*/
	private Alphabet alphabet; /*alphabet sous forme de liste de String*/
	private int nbTransitions;
	
	public Automate() {
		this.etats = new ArrayList<Etat>();
		this.alphabet = new Alphabet(0);
		this.nbTransitions = 0;
		//quand on initialise on sait pas encore on set plus tard en lisant le fichier
	}
	public Automate(final Automate automate) { //constructeur de copie
		this();
		this.etats = automate.getEtats();
		
		/*
		List<Etat> nouveau = new ArrayList<Etat>();
			for(int i=0; i<automate.etats.size();i++) {
				Etat new_etat = new Etat(automate.etats.get(i));
				nouveau.add(new_etat);
			}
		this.etats = nouveau;
		*/
		

		this.alphabet = automate.getAlphabet();
		this.nbTransitions = automate.getNbTransitions();
		

		
	}
	
	
	public int getNbTransitions() {
		return nbTransitions;
	}

	public void setNbTransitions(int nbTransitions) {
		this.nbTransitions = nbTransitions;
	}

	public Alphabet getAlphabet() {
		return new Alphabet(this.alphabet);
	}

	public void setAlphabet(final Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	/*
	public List<Etat> getEtats() {
		List<Etat> nouveau = new ArrayList<Etat>();
		for(int i=0; i<this.etats.size();i++) {
			nouveau.add(this.etats.get(i));
		}
		return nouveau;
	}*/
	
	
	public List<Etat> getEtats() {
		
		/*
		List<Etat> nouveau = new ArrayList<Etat>();
		for(int i=0; i<this.etats.size();i++) {
			Etat new_etat = new Etat(this.etats.get(i));
			nouveau.add(new_etat);
		}
		return nouveau;
		*/
		
		return this.etats;
	}

	public void setEtats(final List<Etat> etats) {
		List<Etat> nouveau = new ArrayList<Etat>();
		for(int i=0; i<this.etats.size();i++) {
			nouveau.add(this.etats.get(i));
		}
		this.etats = nouveau;
	}

	public boolean containsEtats(final String la_chaine) {  //faut check les id des etats de la liste et voir si c'est dedans
		//for(int i=0;i<etats.size() && i<getNbTransitions()  ;i++) { //ca devrait �tre le nbr de transis associ�es � cet �tat
		//	if(etats.get(i).getNom().equals(la_chaine)) {
		//		return true;
		//	}
		//}
		for (Etat etats : getEtats()){
			if(etats.getNom().equals(la_chaine)) {
				return true;
			}
		}
		return false; 
	}

	
	public Etat getEtatByNom(final String id) { //on veut recup un etat specifique de l automate avec son id
		//on parcours la liste jusqu'� trouver l'etat correspondant
		
		if(this.containsEtats(id)) {
			for(int i=0;i<this.getEtats().size();i++) {
				
				if(etats.get(i).getNom().equals(id)) {
					return etats.get(i);
				}
			}
			
		}
		return null;
	}
	/* version avec la boucle foreach
	
    public Etat getEtatByNom(String id) {
        for (Etat state : this.etats) {
            if (state.getNom().equals(id)) {
                return state;
            }
        }
        
        return null; // if the state is not present in the automaton
    }*/
	
	public void afficher_automate() {
		
		/*
		System.out.println(automate.getAlphabet().getDictionary().get(2));
		Etat new_etat = automate.getEtatByNom("2");
		new_etat.addTransi("*", new_etat);
		new_etat.getTransi().get("*").get(0).getNom(); 
		System.out.println();
		*/
		//2*1 normalement
		//c'est chelou les transis epsilon on pas l'air d'�tre accessible
		
		Automate automate = new Automate(this);
		
		System.out.print("L'alphabet : ");
		for(int i=0; i<automate.alphabet.getDictionary().size();i++) {
				System.out.print(automate.alphabet.getDictionary().get(i)+" ");
		} 
		
		System.out.println("");
		
		System.out.print("Les etats : ");
		for(int i=0; i<automate.etats.size();i++) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
		} 
		
		
		System.out.println("");
		
		System.out.print("Les etats initiaux : ");
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.ENTRY)) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
			}
		} 
		
		System.out.println("");
		
		System.out.print("Les etats finaux : ");
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.EXIT)) {
				System.out.print(automate.getEtats().get(i).getNom()+" ");
			}
		}
		
		//afficher la table de transi x)
		System.out.println("");
		
		System.out.print(getNbTransitions() + " transitions :");
		System.out.println("");
		/*
	    for (Etat etat : this.etats) {
	    	for(int k=0;k<etat.getTransi().size();k++) { //le nbr de key pour cet etat
	    		//System.out.println(etat.getTransi().size());
	    		//System.out.println(automate.getAlphabet().getDictionary().get(k));
	    		System.out.println(automate.getAlphabet().getDictionary().get(k));
	    		for(int j=0; j<etat.getTransi().get(automate.getAlphabet().getDictionary().get(k)).size();j++) {
	    			
	    			System.out.print("["+etat.getNom()+"] - [");
	    			System.out.print(automate.getAlphabet().getDictionary().get(k)+"] - [");
	    			System.out.print(etat.getTransi().get(getAlphabet().getDictionary().get(k)).get(j).getNom()+"]");
	    			System.out.println("");
	    		}
	    		
	    	} 
        }
	    */
		 for (Etat etat : this.etats) {
		    for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
		    	System.out.println(etat.getNom()+" " +clef+  " " +Arrays.toString(etat.getTransi().get(clef).toArray()));
		    }
		    		
		    
	     }

	}
	
	/*** STANDARDISATION*/
	public boolean est_standard() { //il faut une seule entr�e et aucune transi vers cette entr�e
		
		Automate automate = new Automate(this);
		int compteur = 0;
		Etat new_etat = null;
		for(Etat etat : automate.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				compteur++;
				new_etat = new Etat(etat);
			}
		} 
		
		if(compteur!=1) {
			return false;
		}
		else {
			//v�rifier qu'aucune transi ne m�ne � cet entr�e
			String id = new_etat.getNom();
			//aucune transi ne doit avoir comme etat d'arriv�e un etat.getNom() == id
			
			for(Etat etat : automate.etats) {
				for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
					
					if(Arrays.toString(etat.getTransi().get(clef).toArray()).contains(id)) {
						return false;
					}
			    }
			}
			//� la fin de la boucle si rien a �t�t return alors l'automate est standard
			return true;
		}
	}
	
	public Automate standardisation() {
		
		Automate automate = new Automate(this);
		if(est_standard()) {
			System.out.println("Votre automate est deja standard ! ");
			return automate;
		}
		else {
			System.out.println("Standardisation !");
			
			//faire une fonction qui recup tous les etats de TypeEtat ENTRY
			List<Etat> etats_entree = automate.get_toutes_entree();
			
			//on cr�er un nouvel etat d'entr�e et on va copier les transitions des anciennes entr�es dedans
			Etat nouvelle_entree = new Etat("i");
			
			for(Etat etat : etats_entree) {
				for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
					for(int i=0;i<etat.getTransi().get(clef).size();i++){
						nouvelle_entree.addTransi( clef, etat.getTransi().get(clef).get(i));
					}
				}
			}
			
			//suppr le type ENTRY des anciennes
			for(Etat etat : etats_entree) {
				for(int i=0;i<etat.getTypes().size();i++) {
					if(etat.getTypes().get(i) == TypeEtat.ENTRY) {
						etat.getTypes().remove(i);
					}
				}		
			}
					
			//ajouter la nouvelle entree a l automate
			nouvelle_entree.addType(TypeEtat.ENTRY);
			automate.etats.add(nouvelle_entree);
			
			
			return automate;
			
		}
		
	}
	
	private List<Etat> get_toutes_entree() {
		List<Etat> liste = new ArrayList<Etat>();
		Automate automate = new Automate(this);
		
		for(Etat etat : automate.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				liste.add(etat);
			}
		} 
		return liste;
	}
	
	
	/*** DETERMINISATION*/
	public boolean est_un_automate_asynchrone() {
		
		Automate automate = new Automate(this);
		int compteur=0;
		String epsilon = "*";
		
		for(Etat etat : automate.etats) {
			for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
				
				if(clef == "") {
					System.out.println("\nL'automate est asynchrone car :\n"); //va �tre affich� plusieurs fois
					System.out.println(etat.getNom() +" - "+ epsilon+" - "+etat.getTransi().get(clef).toString());
					compteur++;
					
				}
			} 
		}
		if(compteur>0) {
			return true;
		}
		else {
			System.out.println("L'automate n'est pas asynchrone");
			return false;
		}
		
	}
	
	public boolean est_un_automate_deterministe() { //1 seule entr�e et liste pour une clef = taille 1 pas plus
		
		Automate automate = new Automate(this);
		int compteur_entree = 0;
		for(int i=0; i<automate.etats.size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.ENTRY)) {
				compteur_entree++;
			}
		} 
		
		if(compteur_entree>1) {
			System.out.println("\nNon d�terministe car plus d'une entr�e");
			return false;
		}
		
		for(Etat etat : this.etats) {
			for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
				if(etat.getTransi().get(clef).size()>1) {
					System.out.println("\nNon d�terministe car on a la transi : "+etat.getNom()+"-"+clef+"-"+etat.getTransi().get(clef).toString());
					return false;
				}
			} 
		}
		System.out.println("\nL'automate est d�terministe");
		return true;
	}
	
	
	/*
	public Automate determinisation(Automate automate) {
		Automate automate_deter_async = new Automate();
		
		if(est_un_automate_asynchrone(automate)) {
			 automate_deter_async = determinisation_et_completion_asynchrone(automate);
		}
		else {
			if(est_un_automate_deterministe(automate)) {
				if(est_un_automate_complet(automate)) {
					 automate_deter_async = new Automate(automate);
				}
				else {
					 automate_deter_async = completion(automate);
				}
			}
			else {
				 automate_deter_async = determinisation_et_completion_synchrone (automate);
			}
		}
		
		afficher_automate_deterministe_complet(automate_deter_async);
	}
	*/
	
	public Automate determinisation(Automate automate) {
		Automate automate_deter_async;
		
		
		if(est_un_automate_asynchrone()) {
			System.out.println("deter asynchrone pas encore faite");
			return null;
		}
		else {
			if(est_un_automate_deterministe()) {
				if(est_un_automate_complet()) {
					 automate_deter_async = new Automate(automate);
				}
				else {
					 automate_deter_async = completion();
				}
			}
			else {
				 automate_deter_async = determinisation_et_completion_synchrone (automate);
			}
		}
		
		return automate_deter_async;
	}
	
	public Automate determinisation_et_completion_synchrone(Automate automate) {
		//faire une copie d�fensive
		Automate ancien_automate = new Automate(this);
		
		Automate automate_deter_sync = new Automate();
		automate_deter_sync.setAlphabet(ancien_automate.getAlphabet());
		List<Etat> liste_entrees = new ArrayList<Etat>();
		
		for(Etat etat : ancien_automate.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				liste_entrees.add(etat);
			}
		}
		//s'il y a plusieurs entr�es on va devoir les fusionner en une nouvelle entr�e
		Etat new_entry = new Etat(liste_entrees.get(0));
		
		for(int i=1;i<liste_entrees.size();i++) {
			new_entry.getTypes().addAll(liste_entrees.get(i).getTypes());
			for(String clef : liste_entrees.get(i).getTransi().keySet()) { //pour toutes les clefs
				for(int j=0;j<liste_entrees.get(i).getTransi().get(clef).size();j++){
					new_entry.addTransi( clef, liste_entrees.get(i).getTransi().get(clef).get(j));
				}
			}
			String ancien_nom1 = new_entry.getNom();
			String ancien_nom2 = liste_entrees.get(i).getNom();
			new_entry.setNom(new_entry.getNom() + liste_entrees.get(i).getNom());
			//il faudrait aussi changer les noms des transis associ�es
			
			/***FONCTION FUSION*/
			for(Etat etat : ancien_automate.etats) {
				if(etat.getNom()==ancien_nom1) {
					etat.setNom(new_entry.getNom());
				}
				if(etat.getNom()==ancien_nom2) {
					etat.setNom(new_entry.getNom());
				}
				
				for(String clef : etat.getTransi().keySet() ) {
					for(int j=0;j<etat.getTransi().get(clef).size();j++){
						if(etat.getTransi().get(clef).get(j).getNom()==ancien_nom1) {
							etat.getTransi().get(clef).get(j).setNom(new_entry.getNom());
						}
						if(etat.getTransi().get(clef).get(j).getNom()==ancien_nom2) {
							etat.getTransi().get(clef).get(j).setNom(new_entry.getNom());
						}
					}
				}
			}
			/***FIN FUSION*/
		}
		
		automate_deter_sync.getEtats().add(new_entry);
		
		/*Check pour la transi de notre etat si les etats existent deja***/
		//les fusionner si besoin et rajouter les nouveaux etats
		//continuer la boucle do while tant qu'on ajoute un nouvel etat
		
		/*
		for(Etat etat : ancien_automate.etats) {
			if(etat.getNom()!= new_entry.getNom()) {
				automate_deter_sync.getEtats().add(etat);
			}
			
		}
		*/
		boolean continuer = true;
		do {
			int ancienne_taille = automate_deter_sync.getEtats().size();
			for(String clef : new_entry.getTransi().keySet() ) {
				if(new_entry.getTransi().get(clef).size()>1) {
					//fusion faudrait fusionner 2 a 2 et rappeler �a jusqu'a ce que la taille de la liste = 1
					Etat nouvel_etat;
					do {
					
						Etat etat1 = new_entry.getTransi().get(clef).get(0);
						Etat etat2 = new_entry.getTransi().get(clef).get(1);
						
						nouvel_etat = fusion(etat1, etat2);
						new_entry.getTransi().get(clef).remove(0);
						
					}while(new_entry.getTransi().get(clef).size()!=1);
					
					
					automate_deter_sync.getEtats().add(nouvel_etat);
					
				}
				else if (new_entry.getTransi().get(clef).size()==1){
					automate_deter_sync.getEtats().add(new_entry.getTransi().get(clef).get(0));
				}
			}
			
			if(automate_deter_sync.getEtats().size()==ancienne_taille) {
				continuer = false;
			}
			
		}while(continuer);

		//on a l'entr�e maintenant on va g�n�rer les nouvelles transitions
		
		//on regarde les etats d'arriv� qu'on a pour notre entr�e
		//si ces etats ne sont pas dans notre liste d'�tat alors on cr�er de nouveaux �tats
		//on r�cup ses transis avec l'ancien automate
		//on ajoute � notre table
		//et nouveau tour de boucle on regarde s'il y a des nouveaux �tats 
		//etc
		
		
		//compl�ter avant de renvoyer 
		return automate_deter_sync;
	}
	
	private Etat fusion(Etat state1, Etat state2) {
		Etat new_etat = new Etat(state1);
		
		//on regarde si state2 contient un type que state1 n'a pas
		for(int i=0;i<state2.getTypes().size();i++) {
			if(!state1.getTypes().contains(state2.getTypes().get(i))) {
				new_etat.getTypes().add(state2.getTypes().get(i));
			}
		}
		
		//on fusionne les noms
		new_etat.setNom(state1.getNom()+state2.getNom());
		
		//mtn s'occupper des transitions
		
		for(String clef : new_etat.getTransi().keySet() ) {
			if(state2.getTransi().get(clef)!=null) {
				for(int i=0;i<state2.getTransi().get(clef).size();i++) {
					new_etat.getTransi().get(clef).add(state2.getTransi().get(clef).get(i));
				}
			}
		}
		
		//mtn il faut changer les noms dans la table de transi
		// tous les etat1.getNom() et etat2.getNom()
		//doivent devenir le nouveau nom new_etat.getNom()
		
		
		return new_etat;
	}
	/*** COMPLETION*/
	public boolean est_un_automate_complet() {
		Automate automate = new Automate(this);
		if((automate.est_un_automate_asynchrone()==false) && (automate.est_un_automate_deterministe()==true)) {
			//check si chaque etat poss�de le bon nbr de clefs
			int compteur=0;
			for(Etat etat : this.etats) {
				for(String clef : etat.getTransi().keySet()) { 
						compteur++;
					}
					if(compteur!=automate.getAlphabet().getDictionary().size()) {
						System.out.println("\nAutomate non complet car "+etat.getNom()+" n'a pas de transi pour toutes les lettres de l'alphabet");
						
						return false;
					}
					compteur=0;
			}
			
			System.out.println("\nAutomate complet");
			return true;
		}
		else {
			System.out.println("\nVotre automate n'est pas synchrone et d�terministe il ne peut pas �tre complet");
			return false;
		}
	}
	
	public Automate completion() {
			
		Automate automate = new Automate(this);
		//cr�er un etat poubelle
		Etat p = new Etat("p");
		List<String> liste = automate.getAlphabet().getDictionary();
		for(int i=0; i<liste.size();i++) {
			p.addTransi(liste.get(i), p);
			automate.setNbTransitions(automate.getNbTransitions()+1);
		}
		//on rajoute le nouvel etat p � l'automate
		automate.etats.add(p);
			
		//rajouter les transitions manquantes
		for(Etat etat : this.etats) {
			if(etat.getTransi().size()!=liste.size()) { //nous renvoie le nbr de clef pour cet etat
				//on rajoute 
				for(int i=0;i<liste.size();i++) {
					if(!etat.getTransi().containsKey(liste.get(i))) { //check si la transi n'existe pas
						etat.addTransi(liste.get(i), p);
						automate.setNbTransitions(automate.getNbTransitions()+1);
					}
				}
				
			}
		}
		return automate;
	}
	
	
	/***MINIMISATION*/
	//doit afficher si c'�tait d�j� minimal
	//on minimise un automate synchrone, d�terministe, complet
	
	/***RECONAISSANCE DES MOTS*/
	/*
	public void lire_mot(String mot) {
		while(mot.length()!=0) {
			reconnaitre(mot,this);
			lire_mot(mot);
		}
	}
	*/
	
	
	
	public boolean reconnaitre_mot_automate_determinsite ( String mot ) {
		
		if(!this.est_un_automate_deterministe()) {
			System.out.println("Impossible de v�rifier votre automate n'est pas d�terminsite");
			return false;
		}
		else {	
			Etat etat_courant = null; /*etat de d�part de l'algo*/
			for(Etat etat : this.etats) {
				if(etat.getTypes().contains(TypeEtat.ENTRY)) {
					 etat_courant = etat;
				}
			}
			/*il faudrait un appel recursif avec toutes les entrées*/
		
			/*il pourrait y avoir plusieurs entr�es si pas d�terministe*/
			
			/*si mot vide*/
			if(mot.equals("")) {
				if(etat_courant.getTypes().contains(TypeEtat.EXIT)) {
					return true;
				}
				else {
					return false;
				}
			}
						
			int index_mot=0;
			String symbole_courant = String.valueOf(mot.charAt(index_mot));
			if(existe_transi(etat_courant, symbole_courant)) {
				etat_courant = etat_courant.getTransi().get(symbole_courant).get(0);
			}
			else {
				return false;
			}
		
			do {
	
				/*si on est � la fin du mot et que la lettre correspond � la denri�re lettre du mot et qu'on est sur une sortie c bon*/
				if(index_mot==(mot.length()-1) && symbole_courant.equals(String.valueOf(mot.charAt(mot.length()-1)))){
					if(etat_courant.getTypes().contains(TypeEtat.EXIT)) {
						return true;
					}
					
					else {
						return false;
					}
					
				}
				String symbole_futur = String.valueOf(mot.charAt(index_mot+1));
				if(existe_transi(etat_courant, symbole_futur)) {
					/*il peut y avoir plusieurs etats cibles si non deterministe*/
					Etat etat_cible = etat_courant.getTransi().get(symbole_futur).get(0);
					/*faudrait un appel récursif avec tous les états*/
					etat_courant = etat_cible;
					index_mot++;
					symbole_courant = String.valueOf(mot.charAt(index_mot));
				}
				else {
					return false;
				}
				
				
			}while(index_mot<mot.length());
			
			/*si tjrs pas reconnu alors false*/
			return false;
		}
		
	}
	
	private boolean existe_transi(Etat etat_courant, String symbole_courant) {
		if(etat_courant.getTransi().get(symbole_courant)== null) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	//return true si le mot est reconnu par l'automate
	
	/*
	public boolean reconnaitre(String mot, Automate automate) {
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				//on commence � essayer de lire
				int i=0;
				boolean continuer = true;
				Etat new_entry = new Etat(etat);
				while(continuer && i<mot.length()) {
					if(new_entry.getTransi().get(String.valueOf(mot.charAt(i)))!=null) {
						for(int j=0; j<new_entry.getTransi().get(String.valueOf(mot.charAt(i))).size();j++) {
							reconnaitre(String.valueOf(mot.substring(1)), new_entry.getTransi().get(String.valueOf(mot.charAt(i))).get(i));
						}
						//new_entry = new_entry.getTransi().get(String.valueOf(mot.charAt(i)));
						//il faudrait faire une fc r�cursive qui appel avec tous les �tats terminaux de la liste
						//puis quand �a arrive � la fin du mot faut regarder si c'est un �tat de sortie
						i++;
					}
					else {
						continuer = false;
					}
				}
				if(continuer==true && new_entry.getTypes().contains(TypeEtat.EXIT)) {
					return true;
				}
				
			}
		} 
		//non reconnu
		return false; 
	}
	*/
	
	

	/* ca marche pas
	private void reconnaitre(String mot, Etat new_entry) {
		int i=0;
		boolean continuer = true;
		while(continuer && i<mot.length()) {
			if(new_entry.getTransi().get(String.valueOf(mot.charAt(i)))!=null) {
				for(int j=0; j<new_entry.getTransi().get(String.valueOf(mot.charAt(i))).size();j++) {
					reconnaitre(String.valueOf(mot.substring(1)), new_entry.getTransi().get(String.valueOf(mot.charAt(i))).get(i));
				}
				i++;
			}
			else {
				continuer = false;
			}
		}
		
	}
	*/
	/***LANGAGE COMPLEMENTAIRE*/
	/*
	public void construction_complementaire_reconaissance(Automate automate) {
		Automate automate_complementaire = automate.automate_complementaire(automate);
		automate_complementaire.afficher_automate(automate_complementaire);
		//
		//
		//lecture
		//
		//	
	}
	*/
	
	//il faudrait indiquer � partir de quel type d'automate on obtient le comp�lmentaire : cad s'il �tait minimal ou non
	public Automate automate_complementaire(Automate automate) {
		if(!automate.est_un_automate_deterministe() && !automate.est_un_automate_complet()) {
			System.out.println("Impossible d'avoir le compl�mentaire l'automate n'est pas d�terminsite et compelt");
			System.out.println("Automate renvoy� non modifi�");
			return automate;
		}
		else {
			Automate automate_complementaire = new Automate(automate);
			List<Etat> liste_anciennes_sorties = new ArrayList<Etat>();
			List<Etat> liste_anciennes_non_sorties = new ArrayList<Etat>();
			
			for(Etat etat : this.etats) {
				if(etat.getTypes().contains(TypeEtat.EXIT)) {
					liste_anciennes_sorties.add(etat);
				}
				else {
					liste_anciennes_non_sorties.add(etat);
				}
			}
			
			for(Etat etat : liste_anciennes_sorties ) {
				for(int i=0;i<etat.getTypes().size();i++) {
					if(etat.getTypes().get(i)==TypeEtat.EXIT) {
						etat.getTypes().remove(i);
					}	
				}
			}
			
			for(Etat etat : liste_anciennes_non_sorties ) {
				etat.getTypes().add(TypeEtat.EXIT);
			}
			
			
			System.out.println("Automate compl�mentaire cr��");
			return automate_complementaire;
		}
		
	}
}
>>>>>>> Stashed changes
