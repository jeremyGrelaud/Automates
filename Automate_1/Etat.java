<<<<<<< Updated upstream
package Automate_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
//avec le jdk 1.4 :
for (int i = 0; i < listNom.size(); i++){
    String s = (String)listNom.get(i); //cast obligatoire
    System.out.println(s);
}

avec le jdk 1.5

for (int i = 0; i < listNom.size(); i++){

    String s = listNom.get(i); //pas besoin du cast vu qu'a la cr�ation est d�fini un mod�le de type String don tu ne peux y mettre autre chose que des String

    System.out.println(s);

} 
*/

public class Etat{
	
	private String nom; //le nom de l'etat 1 ou A par exemple
	private Map<String, List<Etat>> transition; //pour une lettre donn�e = key on aura la liste des etats reli�s
	private List<TypeEtat> types; //nous permet de savoir les particularites de l etat sortie, entree, etc
	
	public Etat(String nom) {
		this.nom=nom;
		this.types = new ArrayList<TypeEtat>();
		this.types.add(TypeEtat.COMMON); //quand on initialise on sait pas encore
		
		transition = new HashMap<String, List<Etat>>();
	}
	
	public Etat(Etat etat) { //construct de copie
		this.nom = etat.getNom();
		this.transition = etat.getTransi();
		this.types = etat.getTypes();
	}

	public void addType(TypeEtat type) { 
		if(! this.types.contains(type)) {
			this.types.add(type);
		}	
	}

	public void addTransi(String transitionWord, Etat next) {
		
		//if(this.transition.get(transitionWord)==null) {
		if(!this.transition.containsKey(transitionWord)){
			List<Etat> new_liste = new ArrayList<Etat>();
			new_liste.add(next);
			this.transition.put(transitionWord, new_liste);
		}
		else {
			/*
			this.transition.get(transitionWord).add(next);
			this.transition.put(transitionWord, this.transition.get(transitionWord));
			*/
			this.transition.get(transitionWord).add(next);
		}
		
		
	} 
	public String getNom() {
		return nom;
	}

	public void setNom(String id) {
		this.nom = id;
	}

	public Map<String, List<Etat>> getTransi() {
		return transition;
	}

	public void setTransi(Map<String, List<Etat>> liens) {
		this.transition = liens;
	}

	public List<TypeEtat> getTypes() {
		return types;
	}

	public void setTypes(List<TypeEtat> types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return this.getNom();
	}
=======
package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
//avec le jdk 1.4 :
for (int i = 0; i < listNom.size(); i++){
    String s = (String)listNom.get(i); //cast obligatoire
    System.out.println(s);
}

avec le jdk 1.5

for (int i = 0; i < listNom.size(); i++){

    String s = listNom.get(i); //pas besoin du cast vu qu'a la cr�ation est d�fini un mod�le de type String don tu ne peux y mettre autre chose que des String

    System.out.println(s);

} 
*/

public class Etat{
	
	private String nom; //le nom de l'etat 1 ou A par exemple
	private Map<String, List<Etat>> transition; //pour une lettre donn�e = key on aura la liste des etats reli�s
	private List<TypeEtat> types; //nous permet de savoir les particularites de l etat sortie, entree, etc
	
	public Etat(String nom) {
		this.nom=nom;
		this.types = new ArrayList<TypeEtat>();
		this.types.add(TypeEtat.COMMON); //quand on initialise on sait pas encore
		
		transition = new HashMap<String, List<Etat>>();
	}
	
	public Etat(Etat etat) { //construct de copie
		this.nom = etat.getNom();
		//this.transition = etat.getTransi();
		//this.types = etat.getTypes();
		
		List<TypeEtat> nouveaux_types = new ArrayList<TypeEtat>();
			for(int i=0; i<etat.types.size();i++) {
				TypeEtat new_type = etat.getTypes().get(i);
				nouveaux_types.add(new_type);
			}
		this.types = nouveaux_types;
		
		
		HashMap<String, List<Etat>> nouvelles_transis = new HashMap<String, List<Etat>>();
		
		
		for(String clef : etat.getTransi().keySet()) {
			List<Etat> new_liste = new ArrayList<Etat>();
			for(int i=0; i<etat.getTransi().get(clef).size();i++) {
				Etat new_etat = etat.getTransi().get(clef).get(i);
				new_liste.add(new_etat);
			}
			nouvelles_transis.put(clef, new_liste);
		}
		
		this.transition = nouvelles_transis;
	}
	
	
	

	public void addType(TypeEtat type) { 
		if(! this.types.contains(type)) {
			this.types.add(type);
		}	
	}

	public void addTransi(String transitionWord, Etat next) {
		
		//if(this.transition.get(transitionWord)==null) {
		if(!this.transition.containsKey(transitionWord)){
			List<Etat> new_liste = new ArrayList<Etat>();
			new_liste.add(next);
			this.transition.put(transitionWord, new_liste);
		}
		else {
			/*
			this.transition.get(transitionWord).add(next);
			this.transition.put(transitionWord, this.transition.get(transitionWord));
			*/
			this.transition.get(transitionWord).add(next);
		}
		
		
	} 
	public String getNom() {
		return nom;
	}

	public void setNom(String id) {
		this.nom = id;
	}

	public Map<String, List<Etat>> getTransi() {
		return transition;
	}

	public void setTransi(Map<String, List<Etat>> liens) {
		this.transition = liens;
	}

	public List<TypeEtat> getTypes() {
		return types;
	}

	public void setTypes(List<TypeEtat> types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return this.getNom();
	}
>>>>>>> Stashed changes
}