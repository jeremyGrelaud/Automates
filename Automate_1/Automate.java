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
		return alphabet;
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public List<Etat> getEtats() {
		return this.etats;
	}

	public void setEtats(List<Etat> etats) {
		this.etats = etats;
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
	
	//return true si le mot est reconnu par l'automate
	/*
	private boolean reconnaitre(String mot, Automate automate) {
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				//on commence à essayer de lire
				int i=0;
				boolean continuer = true;
				Etat new_entry = new Etat(etat);
				while(continuer && i<mot.length()) {
					if(new_entry.getTransi().get(String.valueOf(mot.charAt(i)))!=null) {
						//new_entry = new_entry.getTransi().get(String.valueOf(mot.charAt(i)));
						//il faudrait faire une fc récursive qui appel avec tous les états terminaux de la liste
						//puis quand ça arrive à la fin du mot faut regarder si c'est un état de sortie
						i++;
					}
					else {
						continuer = false;
					}
				}
				
			}
		} 
		//non reconnu
		return false; 
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
