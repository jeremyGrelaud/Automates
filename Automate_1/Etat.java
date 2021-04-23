package Automate_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

    String s = listNom.get(i); //pas besoin du cast vu qu'a la création est défini un modèle de type String don tu ne peux y mettre autre chose que des String

    System.out.println(s);

} 
*/

public class Etat{
	
	
	private String nom; //le nom de l'etat 1 ou A par exemple
	private Map<String, List<Etat>> transition; //pour une lettre donnée = key on aura la liste des etats reliés
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
	
	protected Etat copieEtat() {
		
		Etat new_etat = new Etat(this);
		return new_etat;
		
	}
	
	public boolean est_sortie() {
		if(this.getTypes().contains(TypeEtat.EXIT)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void mergeWith(Etat etat_a_fusionner) {
		
		if(this == etat_a_fusionner) {
			return;
		}
		
		//Operations.mergeMaps(this.transition, etat_a_fusionner.getTransi());
		
		
		Map<String, List<Etat>> dico = mergeMaps(this.transition, etat_a_fusionner.getTransi());
		this.setTransi(dico);
		
		if(etat_a_fusionner.est_sortie() && !this.est_sortie()) {
			this.types.add(TypeEtat.EXIT);
		}
		
		String ancien_nom = this.nom;
		
		this.nom = String.join(".", this.nom, etat_a_fusionner.getNom());
		
			/*
			for(String clef : this.getTransi().keySet() ) {
				for(Etat etat : this.getTransi().get(clef)) {
					if((etat.getNom().equals(ancien_nom)) || (etat.getNom().equals(etat_a_fusionner.getNom()))) {
						etat.setNom(this.nom);
					}
				}
			}
			*/
	}
	
	//fonction pour fusionner 2 map
	protected static Map<String, List<Etat>> mergeMaps(Map<String, List<Etat>> map1, Map<String, List<Etat>> map2){
		
		Map<String, List<Etat>> new_map = new HashMap<String, List<Etat>>();
		
		if(!map1.isEmpty() && !map2.isEmpty()) {
			
			new_map.putAll(map1);
			
			for(String clef : map2.keySet()) { //pour toutes les clefs de map2
				
					if(!new_map.containsKey(clef)) {
						new_map.put(clef, map2.get(clef));
					}
					
					else {
						List<Etat> Etats_new_map = new_map.get(clef);
						List<Etat> Etats_map2 = map2.get(clef);
						
						List<Etat> fusion = mergeLists(Etats_new_map, Etats_map2);
						new_map.remove(clef);
						new_map.put(clef, fusion);
					}
				
			}
			
			return new_map;
		}
		
		if(map1.isEmpty() && !map2.isEmpty()) {
			return map2;
		}
		else {
			return map1;
		}
		//si la condition est vrai on retourne map2 sinon map1
	}

	protected static List<Etat> mergeLists(List<Etat> l1, List<Etat> l2) {
		
		List<Etat> etats = new ArrayList<Etat>();
		
		etats.addAll(l1);
		
		for(Etat etat : l2) {
			if(!etats.contains(etat)) {
				etats.add(etat);
			}
		}
		
		return etats;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		
		Etat etat = (Etat) o;
		return this.getNom().equals(etat.getNom());
		
		
	}

}