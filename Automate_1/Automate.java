package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
			
			/*
			//s'il y a plusieurs entrees on va devoir les fusionner en une nouvelle entree
			Etat new_entry = new Etat(liste_entrees.get(0));
			
			
			for(int i=1;i<liste_entrees.size();i++) {
				new_entry.getTypes().addAll(liste_entrees.get(i).getTypes());
				for(String clef : liste_entrees.get(i).getTransi().keySet()) { //pour toutes les clefs
					for(int j=0;j<liste_entrees.get(i).getTransi().get(clef).size();j++){
						new_entry.addTransi( clef, liste_entrees.get(i).getTransi().get(clef).get(j));
						//automate_deter_sync.nbTransitions = automate_deter_sync.getNbTransitions()+1;
					}
				}
				String ancien_nom1 = new_entry.getNom();
				String ancien_nom2 = liste_entrees.get(i).getNom();
				new_entry.setNom(ancien_nom1 + "."+ ancien_nom2);
				
				//il faudrait aussi changer les noms des transis associees

				
				/*
				for(Etat etat : ancien_automate.etats) {
					if(etat.getNom().equals(ancien_nom1) || etat.getNom().equals(ancien_nom2) ) {
						etat.setNom(new_entry.getNom());
					}
					
					/*
					for(String clef : etat.getTransi().keySet()) {
						for(int j=0; j<etat.getTransi().get(clef).size();j++) {
							if(etat.getTransi().get(clef).get(j).getNom().equals(ancien_nom1) || etat.getTransi().get(clef).get(j).getNom().equals(ancien_nom1) ) {
								etat.getTransi().get(clef).get(j).setNom(new_entry.getNom());
							}
						}
					}
					*/
						
				//}
			
			//}
			
			
			
		
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
				
				/*
				System.out.println(!liste_nouveaux_etats_determinse.contains(etat_courant));
				System.out.println("etat courant : " + etat_courant);
				System.out.println("liste " + liste_nouveaux_etats_determinse );
				*/
				//il arrive pas à savoir que les etats sont deja contenus dans la liste car ils on pas la même référence
				
				if(liste_nouveaux_etats_determinse.stream().noneMatch(etat -> etat.getNom().equals(etat_courant.getNom()))) {
					
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
	
	/*
	private Etat merge(List<Etat> liste) {
		Etat new_etat = new Etat(liste.get(0));	
		//on regarde si les autres etats contiennent un type que l'etat d'indice 0 n'a pas
		for(int j=1; j<liste.size();j++) {
			for(int i=0; i<liste.get(j).getTypes().size();i++) {
				if(!new_etat.getTypes().contains(liste.get(j).getTypes().get(i))) {
					new_etat.getTypes().add(liste.get(j).getTypes().get(i));
				}
			}
		}
	
		List<String> liste_anciens_noms = new ArrayList<String>();
		liste_anciens_noms.add(new_etat.getNom());
		//maintenant on fusionne les noms
		for(int i=1; i<liste.size();i++) {
			liste_anciens_noms.add(liste.get(i).getNom());
			new_etat.setNom(new_etat.getNom() +"."+ liste.get(i).getNom());
		}
		
		//il va falloir remplacer tous les anciens noms par le nouveau
	
		
		//mtn s'occupper des transitions
		for(Etat etat : liste) {
			for(String clef : new_etat.getTransi().keySet() ) {

				if(etat.getTransi().get(clef)!=null) {
					for(int i=0;i<etat.getTransi().get(clef).size();i++) {
						if(!new_etat.getTransi().get(clef).contains(etat.getTransi().get(clef).get(i))) {
							new_etat.getTransi().get(clef).add(etat.getTransi().get(clef).get(i));
						}
						
					}
				}
			}
		}
		
		//mtn il faut changer les noms dans la table de transi
		// tous les etats dont le nom appartient à la liste des anciens noms
		//doivent avoir comme nouveau nom new_etat.getNom()
		
		for(Etat etat : this.etats) {
			for(String clef : etat.getTransi().keySet() ) {
				for(Etat etat_destination : etat.getTransi().get(clef)) {
					if(liste_anciens_noms.contains(etat_destination.getNom())) {
						etat_destination.setNom(new_etat.getNom());
					}
				}
			}
		}
		
		
		return new_etat;
	}
	*/
	
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
	
	

		
	
	
		
	/* Les piles en java
		
		Stack<Etat> pile = new Stack<Etat>();

		pile.push(init);
		pile.pop();

		while(!pile.empty()){
			
	}
	*/
}
