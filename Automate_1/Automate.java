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

	
	public Etat getEtatByNom(String id) { //on veut recup un etat specifique de l automate avec son id
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
	
	public void afficher_automate(Automate automate) {
		
		/*
		System.out.println(automate.getAlphabet().getDictionary().get(2));
		Etat new_etat = automate.getEtatByNom("2");
		new_etat.addTransi("*", new_etat);
		new_etat.getTransi().get("*").get(0).getNom(); 
		System.out.println();
		*/
		//2*1 normalement
		//c'est chelou les transis epsilon on pas l'air d'�tre accessible
		
		
		
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
	
	public boolean est_standard(Automate automate) { //il faut une seule entr�e et aucune transi vers cette entr�e
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
			//v�rifier qu'aucune transi ne m�ne � cet entr�e
			String id = new_etat.getNom();
			//aucune transi ne doit avoir comme etat d'arriv�e un etat.getNom() == id
			
			for(Etat etat : this.etats) {
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
	
	public Automate standardisation(Automate automate) {
		
		if(est_standard(automate)) {
			System.out.println("Votre automate est deja standard ! ");
			return automate;
		}
		else {
			//faire une fonction qui recup tous les etats de TypeEtat ENTRY
			List<Etat> etats_entree = get_toutes_entree(automate);
			
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
				etat.getTypes().get(i);
			}
			
			
			//ajouter la nouvelle entree a l automate
			automate.etats.add(nouvelle_entree);
			
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
	

}
