package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

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
		//this();
		//this.etats = automate.getEtats();
		
		
		List<Etat> nouveau = new ArrayList<Etat>();
			for(int i=0; i<automate.etats.size();i++) {
				Etat new_etat = new Etat(automate.etats.get(i));
				nouveau.add(new_etat);
			}
		this.etats = nouveau;
		
		

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

		 for (Etat etat : this.etats) {
		    for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
		    	if(clef.equals("")) {
		    		System.out.println(etat.getNom()+" * " +Arrays.toString(etat.getTransi().get(clef).toArray()));
		    	}
		    	else {
		    		System.out.println(etat.getNom()+" " +clef+  " " +Arrays.toString(etat.getTransi().get(clef).toArray()));
		    	}
		    	
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
	
	public void standardisation() {
		
		Automate automate = new Automate(this);
		if(est_standard()) {
			System.out.println("Votre automate est deja standard ! ");
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
			for(int i=0; i<automate.getEtats().size();i++) {
				this.etats.get(i).getTypes().remove(TypeEtat.ENTRY);
			}
				
			//ajouter la nouvelle entree a l automate
			nouvelle_entree.addType(TypeEtat.ENTRY);
			this.etats.add(nouvelle_entree);
			
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
			System.out.println("\nNon deterministe car plus d'une entree");
			return false;
		}
		
		for(Etat etat : this.etats) {
			for(String clef : etat.getTransi().keySet()) { //string avec tt les clefs
				if(etat.getTransi().get(clef).size()>1) {
					System.out.println("\nNon deterministe car on a la transi : "+etat.getNom()+"-"+clef+"-"+etat.getTransi().get(clef).toString());
					return false;
				}
			} 
		}
		System.out.println("\nL'automate est deterministe");
		return true;
	}
	
	/*** COMPLETION*/
	public boolean est_un_automate_complet() {
		Automate automate = new Automate(this);
		if((automate.est_un_automate_asynchrone()==false)) { //&& (automate.est_un_automate_deterministe()==true)) {
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
	
	public void completion() {
			
		Automate automate = new Automate(this);
		//cr�er un etat poubelle
		Etat p = new Etat("p");
		List<String> liste = automate.getAlphabet().getDictionary();
		for(int i=0; i<liste.size();i++) {
			p.addTransi(liste.get(i), p);
			//automate.setNbTransitions(automate.getNbTransitions()+1);
			this.setNbTransitions(this.getNbTransitions()+1);
		}
		//on rajoute le nouvel etat p � l'automate
		//automate.etats.add(p);
		this.etats.add(p);
			
		//rajouter les transitions manquantes
		for(Etat etat : this.etats) {
			if(etat.getTransi().size()!=liste.size()) { //nous renvoie le nbr de clef pour cet etat
				//on rajoute 
				for(int i=0;i<liste.size();i++) {
					if(!etat.getTransi().containsKey(liste.get(i))) { //check si la transi n'existe pas
						etat.addTransi(liste.get(i), p);
						//automate.setNbTransitions(automate.getNbTransitions()+1);
						this.setNbTransitions(this.getNbTransitions()+1);
					}
				}
				
			}
		}
		//return automate;
	}
	
	
	/*RECONAISSANCE POUR NON DETERMINISTE*/
	private boolean reconaissance_etat(Etat etat_courant, String mot) {
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
			/*il faut appeler une fc pour chaque etats d'arrivée  */
			List<Boolean> liste = new ArrayList<Boolean>();
			for(Etat etat_courant1 : etat_courant.getTransi().get(symbole_courant)) {
				liste.add(this.reconaissance_part2(etat_courant1, mot, symbole_courant));
			}
			if(liste.contains(true)) {
				return true;
			}
			else {
				return false;
			}

		}
		else {
			return false;
		}
		
	
	
		
	}
	private boolean reconaissance_part2(Etat etat_courant, String mot, String symbole_courant) {
		int index_mot=0;
		do {

			/*si on est a la fin du mot et que la lettre correspond a la denriere lettre du mot et qu'on est sur une sortie c bon*/
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
	public boolean reconnaitre_mot_automate(String mot) {
		
		List<Etat> etats_entree = new ArrayList<Etat>();
		for(Etat etat : this.etats) {
			if(etat.getTypes().contains(TypeEtat.ENTRY)) {
				 etats_entree.add(etat);
			}
		}
		/* pour chaque entree dans la liste on va appliquer l'algo*/
		List<Boolean> liste = new ArrayList<Boolean>();
		for(Etat etat_courant : etats_entree) {
			liste.add(this.reconaissance_etat(etat_courant, mot));
		}
		/*si c'est reconnu en passant par un des etats de depart on renvoie true*/
		if(liste.contains(true)) {
			return true;
		}
		else {
			return false;
		}
}
	/*FIN RECONAISSANCE POUR NON DETERMINISTE*/
		
	public boolean reconnaitre_mot_automate_determinsite ( String mot ) {
		
		if(!this.est_un_automate_deterministe()) {
			System.out.println("Impossible de verifier votre automate n'est pas determinsite");
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
	
	//POUR TAPER PLUSIEURS MOTS A LA SUITE
	public void reconnaitre_plusieurs_mot() {
		Scanner sc = new Scanner(System.in);
		String mot = "";
		
		System.out.println("Pour arrêter la saisie de mots taper QUIT");
		
		do {
			System.out.println("Veuillez saisir un mot :");
			mot = sc.nextLine();
			if(!mot.equals("QUIT")) {
				if(this.est_un_automate_deterministe()) {
					if(this.reconnaitre_mot_automate_determinsite(mot)) {
						System.out.println("Le mot "+mot+" est reconnu par l'automate");
					}
					else {
						System.out.println("Le mot "+mot+" n'est pas reconnu par l'automate");
					}
				}
				else {
					//METHODE POUR LES NON DETERMINISTE
					if(this.reconnaitre_mot_automate(mot)) {
						System.out.println("Le mot "+mot+" est reconnu par l'automate");
					}
					else {
						System.out.println("Le mot "+mot+" n'est pas reconnu par l'automate");
					}
				}
			}
		}while(!mot.equals("QUIT"));
		//fin
		System.out.println("Fin de la reconaissance des mots");
		sc.close();
	}

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
	public Automate automate_complementaire() {
		
		Automate automate = new Automate(this);
		
		if(!automate.est_un_automate_deterministe() && !automate.est_un_automate_complet()) {
			System.out.println("Impossible d'avoir le compl�mentaire l'automate n'est pas d�terminsite et compelt");
			System.out.println("Automate renvoye non modifie");
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
			
			
			System.out.println("Automate complementaire cree");
			return automate_complementaire;
		}
		
	}
	
	
	/*DETERMINISATION*/
	public Automate determinisation(Automate automate) {
		Automate automate_deter_async = new Automate(automate);
		
		
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
					// automate_deter_async = completion();
					 automate_deter_async.completion();
				}
			}
			else {
				 automate_deter_async = automate_deter_async.determinise();
			}
		}
		
		return automate_deter_async;
	}
	/**
	 * Cree l'automate determinise
	 * @return l'automate determinise
	 */
	public Automate determinise(){
		
		if(this.est_un_automate_deterministe()) {
			return this;
		}
		else {

			//faire une copie defensive
			Automate ancien_automate = new Automate(this);
			
			Automate automate_deter_sync = new Automate();
			automate_deter_sync.setAlphabet(ancien_automate.getAlphabet());
			
			List<Etat> liste_entrees = this.get_toutes_entree();
		
			Etat new_entry = merge(liste_entrees);
		
			/*il faut supprimer les etats en double dans ancien automate*/
			
			/*On va créer une pile avec au départ juste l'état initial dedans*/
			/*puis dans un tant que pile n'est pas vide à chaque fois on va regarder si de nouveaux etats apparaissent*/
			/*on aura peut être besoin d'une liste de string pour les noms des etats deja apparus*/
			List<Etat> liste_nouveaux_etats_determinse = new ArrayList<Etat>();
			/*on ajoutera ensuite tous les états de cette liste à l'automate*/
			//liste_nouveaux_etats_determinse.add(new_entry);
			
			Stack<Etat> pile = new Stack<Etat>();
			pile.push(new_entry);
			
			while(!pile.empty()){
				Etat etat_courant = pile.pop();
				
				//il arrive pas à savoir que les etats sont deja contenus dans la liste car ils on pas la même référence
				
				boolean contenir = false;
				for(Etat etat : liste_nouveaux_etats_determinse) {
					if(etat.getNom().equals(etat_courant.getNom())) {
						contenir = true;
					}
				}
				if(!contenir) {
				
					/*on regarde les transis*/
					for(String clef : etat_courant.getTransi().keySet()) {
		
						if((etat_courant.getTransi().get(clef).size()>1)){
							/*on appelle la fonction merge*/
							Etat nouvel_etat = merge(etat_courant.getTransi().get(clef));
							etat_courant.getTransi().get(clef).clear();
							etat_courant.getTransi().get(clef).add(nouvel_etat);
							
							if(!liste_nouveaux_etats_determinse.contains(nouvel_etat)) {
								pile.push(nouvel_etat);
							}

						}
						
						else if((etat_courant.getTransi().get(clef).size()==1) && (!liste_nouveaux_etats_determinse.contains(etat_courant.getTransi().get(clef).get(0)))) {
							/*alors on ajoute cet etat à la liste et à la pile*/
							Etat nouvel_etat = etat_courant.getTransi().get(clef).get(0);
							if(!liste_nouveaux_etats_determinse.contains(nouvel_etat)) {
								pile.push(nouvel_etat);
							}
						}

						automate_deter_sync.nbTransitions = automate_deter_sync.getNbTransitions()+1;
						/*sinon si c'est une liste vide on fait rien*/
					}
					liste_nouveaux_etats_determinse.add(etat_courant);
				}
			}
			
			for(Etat etat : liste_nouveaux_etats_determinse ) {
				if(etat.getTypes().contains(TypeEtat.ENTRY) && !etat.getNom().equals(new_entry.getNom())) {
					etat.getTypes().remove(TypeEtat.ENTRY);
				}
			}
				
			for(Etat etat : liste_nouveaux_etats_determinse ) {
				if(!automate_deter_sync.containsEtats(etat.getNom())) {
					automate_deter_sync.etats.add(etat);
				}
			}
			return automate_deter_sync;
		}
	}
	
	protected Etat merge(List<Etat> liste) {
		
		if(liste.size()==0) {
			return null;
		}
		else if(liste.size()==1) {
			return liste.get(0);
		}
		
		/*sinon il faut fusionner tous les etats*/
		
		Etat new_etat = liste.get(0).copieEtat(); //on veut une copie pour ne pas modifier l'etat initial
		
		for(int i=1; i<liste.size(); i++) {
			Etat prochain_etat = liste.get(i).copieEtat();
			
			//si egaux on ne fusionne pas
			if(prochain_etat.equals(new_etat)) {
				continue;
			}
			
			//sinon on fusionne les 2 ensemble
			new_etat.mergeWith(prochain_etat);
		}
		
		return new_etat;
	}
	
	/***EPSILON*/
	//on va enlever tt les transis epsilon et les remplacer
	public Automate elimination_epsilon() {
		Automate automate = new Automate(this);
		
		if(!automate.est_un_automate_asynchrone()) {
			return automate;
		}
		else {
			Automate nouvel_automate_synchrone = new Automate();
			nouvel_automate_synchrone.setAlphabet(automate.getAlphabet());
			List<Etat> liste_nouveaux_etats = new ArrayList<Etat>();
			
			
			for(Etat etat : automate.getEtats()) {
					//on créer un nouvel etat en mergeant la liste des etats destinations possibles
					List<Etat> liste = new ArrayList<Etat>();
				
					
					//ex cloture epsilon de 1 : ensemble des etats ou on peut aller en partant de 1 et en passant par des transi espilon
					
					List<Etat> liste2 = cloture(etat);
					liste.addAll(liste2);
					Etat new_etat = new Etat(merge(liste));
					new_etat.setNom(etat.getNom());
				
					liste_nouveaux_etats.add(new_etat);
					
			}
				
			
			
			for(Etat etat : liste_nouveaux_etats) {
				
				
				/*for(String clef : etat.getTransi().keySet()) {
					for(Etat etat2 : etat.getTransi().get(clef)) {
						Map<String, List<Etat>> map = new HashMap<String, List<Etat>>();
						for(String clef2 : etat2.getTransi().keySet()) {
							
							List<Etat> new_transi = new ArrayList<Etat>();
							String nom = etat2.getNom();
							//on veut relier les transi de l'etat2 à etat correspondant
							
							for(int i=0;i<liste_nouveaux_etats.size();i++ ) {
								if(liste_nouveaux_etats.get(i).getNom().equals(nom)) {
									if((liste_nouveaux_etats.get(i).getTransi().get(clef)!=null) ){
										new_transi = (liste_nouveaux_etats.get(i).getTransi().get(clef));
									}
								}
							}
							
							map.put(clef, new_transi);
							
							
						}
						etat2.setTransi(map);
					}
				}*/
				
				etat.getTransi().remove("");
				
			}
			
			
			for(Etat etat : liste_nouveaux_etats) {
				for(String clef : etat.getTransi().keySet()) {
					for(Etat etat_destination : etat.getTransi().get(clef)) {
						//on remplace les etat_destination par leur etat correspondant de la liste principale
						for(Etat etat_de_base : liste_nouveaux_etats) {
							if(etat_destination.equals(etat_de_base)) {
								etat_destination.setTransi(etat_de_base.getTransi());
								etat_destination.setTypes(etat_de_base.getTypes());
							}
						}
					}
				}
			}
			
			
			
			
			
			for(Etat etat : liste_nouveaux_etats) {
				for(String clef : etat.getTransi().keySet()) {
					
					nouvel_automate_synchrone.nbTransitions = nouvel_automate_synchrone.getNbTransitions()+1;
				}
				nouvel_automate_synchrone.etats.add(etat);
				
			}
			
		
			
			return nouvel_automate_synchrone;
		}
	
	}
	
	
	//on veut retourner une liste de tous les etats atteignables en passant par des transi epsilon
	private List<Etat> cloture(Etat etat) {
		List<Etat> liste2 = new ArrayList<Etat>();
		liste2.add(etat);
		Stack<Etat> pile = new Stack<Etat>();
		pile.push(etat);
		
		while(!pile.isEmpty()) {
			etat = pile.pop();
			if(etat.getTransi().get("")!=null) {
				liste2.addAll(etat.getTransi().get(""));
				for(Etat etat_liste : etat.getTransi().get("")) {
					pile.push(etat_liste);
				}
				
				
			}
	
		}
		
		return liste2;
	}
	
	public Automate determinisation_et_completion_asynchrone() {
		Automate automate = new Automate(this);
		Automate nouvel_automate = new Automate();
		
		if(automate.est_un_automate_asynchrone()) {
			nouvel_automate = automate.elimination_epsilon();
			
			nouvel_automate = nouvel_automate.determinise();
			nouvel_automate.completion();
			
			return nouvel_automate;
		}
		else {
			nouvel_automate = automate.determinise();
			nouvel_automate.completion();
			
			return nouvel_automate;
		}
		
	}



	
	/***MINIMISATION*/
	//doit afficher si c'etait deja minimal
	//on minimise un automate synchrone, deterministe, complet

	public Automate minimisation() {
		Automate minimise = new Automate();
		Automate automate_a_minimiser = new Automate(this);
		
		if(!(automate_a_minimiser.est_un_automate_deterministe() && automate_a_minimiser.est_un_automate_complet())) {
			automate_a_minimiser = automate_a_minimiser.determinise();
			automate_a_minimiser.completion();
		}
		
		minimise = automate_a_minimiser.minimiser();
		System.out.println("Voici l'automate minimal : ");
		minimise.afficher_automate();
		return minimise;
	}
	
	//WORK IN PROGRESS
	private Automate minimiser() {
		boolean change = true;
		Automate automate_min = new Automate();
		Automate automate = new Automate(this);
		
		//initialisation 
		int taille = automate.getEtats().size();
		int pi_num = 2;
		int[] pi_tab = new int[taille];
		for(int i=0; i<automate.getEtats().size();i++) {
			if(automate.getEtats().get(i).getTypes().contains(TypeEtat.EXIT)) {
				pi_tab[i] = 2;
			}
			else {
				pi_tab[i] = 1;
			}	
		}
		
		/*
		for(int i=0; i<automate.getEtats().size();i++) {
			System.out.println(pi_tab[i]);
		}
		System.out.println("");
		*/
		
	
		
		do {
			//construction signature
			int nbr_colonnes =automate.getAlphabet().getDictionary().size()+2;
			int[][] signature = new int[automate.getEtats().size()][nbr_colonnes];
			//int[][] ancien_signature = new int[automate.getEtats().size()][nbr_colonnes];
			for(int cmpt=0; cmpt<automate.getEtats().size();cmpt++) {
				signature[cmpt][0] = cmpt;
			}
			
			//int j =1;
			//while(j<nbr_colonnes) {
				for(int i=0; i<automate.getEtats().size();i++) {
					
					int j=1;
					//on regarde à quel groupe de la partition l'etat lui meme appartient
					
					
					signature[i][j] = pi_tab[i];
					j++;
					
					for(String clef : automate.getEtats().get(i).getTransi().keySet()) {
						Etat destination = automate.getEtats().get(i).getTransi().get(clef).get(0);
						
						//on regarde à quel groupe de la partition destination appartient
						int pos=0;
						for(int k=0;k<automate.getEtats().size();k++) {
							if(automate.getEtats().get(k).getNom().equals(destination.getNom())) {
								pos=k;
							}
						}
						
						
						
						//if(j<nbr_colonnes) {
							//signature[i][j] = pi_tab[pos];
							signature[i][j] = pi_tab[pos];
						//}
							j++;
						
					}
					
					
				}
				
			/*
			System.out.println("SIGNATURE :");
			for(int i=0; i<taille;i++) {
				for(int z=0;z<nbr_colonnes;z++) {
					System.out.print(signature[i][z]);
				}
				System.out.println("");
			}
			System.out.println("");*/
				
			/*
			for(int i=0; i<taille;i++) {
				for(int z=0;z<nbr_colonnes;z++) {
					ancien_signature[i][z] =signature[i][z];
				}
			}*/
			
			//Tri radix
			int[] tab_trie = new int[taille];
			
			for(int jj=nbr_colonnes-1;jj>0;jj--) {
				boolean trie=false;
				int compteur =0;
				do {
					int pos_min=compteur;
					for(int ii=compteur;ii<automate.getEtats().size();ii++) {
						//faut ordonner dans l'odre croissant les lignes en fc des elements de la colonne jj actuelle
						if(signature[ii][jj] < signature[pos_min][jj]) {
							pos_min=ii;
						}
					}
					//on decale toutes les lignes on met le plus petit devant
					
					
						// Decalage
						int[] tmp = signature[pos_min]; 
						
						for(int z=pos_min; z >compteur; z--) {
							signature[z] = signature[z-1];
						}
						signature[compteur] = tmp; 
						compteur++;
						//le plus petit est maintenant au début
						
						/*
						System.out.println("SIGNATURE :");
						for(int i=0; i<taille;i++) {
							for(int z=0;z<nbr_colonnes;z++) {
								System.out.print(signature[i][z]);
							}
							System.out.println("");
						}
						System.out.println("");*/
					
					
					if(compteur == automate.getEtats().size()) {
						trie=true;
					}
					
				}while(trie==false);
				
			}
			
			//une fois le tri finis
			for(int i=0; i<taille;i++) {
				tab_trie[i] = signature[i][0];
			}
			
			
			/*
			for(int i=0; i<pi_tab.length;i++) {
				System.out.println(tab_trie[i]);
			}
			System.out.println("");*/
			
			/***VERiF signature*/	
			/*
			System.out.println("Matrice signature :");
			for(int i=0; i<taille;i++) {
				for(int z=0;z<nbr_colonnes;z++) {
					System.out.print(signature[i][z]);
				}
				System.out.println("");
			}
			
			System.out.println("Ancienne signature :");
			for(int i=0; i<taille;i++) {
				for(int z=0;z<nbr_colonnes;z++) {
					System.out.print(ancien_signature[i][z]);
				}
				System.out.println("");
			}*/
			
			
			
			/*
			//renumerotation
			//la signature est trie dans l'ordre croissant
			int num=1;
			//nouvelle partition
			//a quel etat correspondait tab_trie[0] et ça donne un int
			pi_tab[tab_trie[0]]=num;
			for(int l=1; l<taille;l++) {
				//comparaison de signature
				//System.out.println((!signature_egales(signature[tab_trie[l]], signature[tab_trie[l-1]])) );
				if(!signature_egales(ancien_signature[tab_trie[l]], ancien_signature[tab_trie[l-1]])) {
					num = num+1;
				}
				
				pi_tab[tab_trie[l]]=num;
				
			}
			if(pi_num!=num) {
				change=true;
				pi_num=num;
			}
			else {
				change=false;
			}*/
			
			
			//renumerotation
			//la signature est trie dans l'ordre croissant
			int num=1;
			//nouvelle partition
			//a quel etat correspondait tab_trie[0] et ça donne un int
			pi_tab[tab_trie[0]]=num;
			for(int l=1; l<taille;l++) {
				//comparaison de signature
				//System.out.println((!signature_egales(signature[tab_trie[l]], signature[tab_trie[l-1]])) );
				if(!signature_egales(signature[l], signature[l-1])) {
					num = num+1;
				}
				
				pi_tab[tab_trie[l]]=num;
				
			}
			if(pi_num!=num) {
				change=true;
				pi_num=num;
			}
			else {
				change=false;
			}
			
		}while(change==true);
		
		/*
		for(int i=0; i<pi_tab.length;i++) {
			System.out.println(pi_tab[i]);
		}
		System.out.println("");*/
		
		
		
		List<Etat> deja_traite = new ArrayList<Etat>();
		//on a donc un tableau pi_tab dans lequel la position correspond a la pos de l'etat dans la liste de l'automate
		//et dans les cases est indiqué leur appartenance a un grp
		//si 2 etats sont dans un meme grp il faut les fusionner car ils ont le meme comportement
		
		for(int i=0; i<automate.getEtats().size(); i++) {
			int grp_etat_courant = pi_tab[i];
			List<Etat> etats_meme_grp = new ArrayList<Etat>();
			
			if(!deja_traite.contains(automate.getEtats().get(i))) { 
				etats_meme_grp.add(automate.getEtats().get(i));
				deja_traite.add(automate.getEtats().get(i));
				
				
				for(int j=0;j<automate.getEtats().size();j++) {
					if(j!=i) {
						if(pi_tab[j]==grp_etat_courant) {
							if(!deja_traite.contains(automate.getEtats().get(j))) {
								etats_meme_grp.add(automate.getEtats().get(j));
								deja_traite.add(automate.getEtats().get(j));
							}
							
						}
					}
				}
				
				Etat new_etat = merge(etats_meme_grp);
				//il faut changer le nom des etats de la liste en leur nouveau nom dans les transi
				for(Etat etat : automate.getEtats()) {
					for(String clef : etat.getTransi().keySet()) {
						for(Etat etat2 : etat.getTransi().get(clef)) {
							for(Etat etat_modifie : etats_meme_grp) {
								if(etat2.equals(etat_modifie)) {
									etat2.setNom(new_etat.getNom());
								}
							}
							
						}
							
						
					}
				}
				automate_min.getEtats().add(new_etat);
				
				//deja_traite.add(automate.getEtats().get(i));
			}
			
		}
		
		for(Etat etat : automate_min.getEtats()) {
			for(String clef : etat.getTransi().keySet()) {
				automate_min.setNbTransitions(automate_min.getNbTransitions()+1);
			}
		}
		
		
		
		
		automate_min.setAlphabet(automate.getAlphabet());
		
		
		return automate_min;
		
	}
	
	private boolean signature_egales(int[] sign1, int[] sign2) {
		
	
		for(int i=1; i<sign1.length;i++) {
			/*
			System.out.println("sign1 :" +sign1[i]);
			System.out.println("sign2 :" +sign2[i]);*/
			if(sign1[i]!=sign2[i]) {
				return false;
			}
		}
		
		return true;
	}

	public void programme() {
		Automate automate_depart = new Automate(this);
		Automate automate_a_modifier = new Automate(this);
		
		
		Scanner sc = new Scanner(System.in);
		String mot = "";
		
		System.out.println("Pour arrêter l'execution taper EXIT");
		do {
			System.out.println("Veuillez taper un chiffre :");
			System.out.println("1 : standardisation ");
			System.out.println("2 : completion ");
			System.out.println("3 : automate complementaire ");
			System.out.println("4 : reconaissance de mots sur l'automate");
			System.out.println("5 : determinisation synchrone");
			System.out.println("6 : minimisation");
			System.out.println("7 : revenir a l'automate de depart");
			mot = sc.nextLine();
			
			while(!( (mot.equals("1")) || (mot.equals("2")) || (mot.equals("3")) || (mot.equals("4")) || (mot.equals("5")) || (mot.equals("6")) || (mot.equals("7")) || (mot.equals("EXIT")) )) {
				System.out.println("Veuillez faire un choix valide ");
				System.out.println("Veuillez taper un chiffre :");
				System.out.println("1 : standardisation ");
				System.out.println("2 : completion ");
				System.out.println("3 : automate complementaire ");
				System.out.println("4 : reconaissance de mots sur l'automate");
				System.out.println("5 : determinisation synchrone");
				System.out.println("6 : minimisation");
				System.out.println("7 : revenir a l'automate de depart");
				mot = sc.nextLine();
			}
			
			if(mot.equals("1")){
				automate_a_modifier.standardisation();
				System.out.println("Standardisation effectuée : ");
				automate_a_modifier.afficher_automate();
			}
			if(mot.equals("2")){
				automate_a_modifier.completion();
				System.out.println("Completion effectuée : ");
				automate_a_modifier.afficher_automate();			
			}
			if(mot.equals("3")){
				automate_a_modifier.automate_complementaire();
				System.out.println("Automate complementaire : ");
				automate_a_modifier.afficher_automate();	
			}
			if(mot.equals("4")){
				automate_a_modifier.reconnaitre_plusieurs_mot();	
			}
			if(mot.equals("5")){
				automate_a_modifier = automate_a_modifier.determinisation(automate_a_modifier);
				System.out.println("Determinisation effectuée : ");
				automate_a_modifier.afficher_automate();	
			}
			if(mot.equals("6")){
				automate_a_modifier = automate_a_modifier.minimisation();
			}
			if(mot.equals("7")){
				automate_a_modifier = automate_depart;
				automate_a_modifier.afficher_automate();
			}
			
		}while(!mot.equals("EXIT"));
		//fin
		System.out.println("Fin du programme");
		sc.close();
		
	}
}



