package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.Mortier;
import type_deplacement.SurChenilles;


public class Artillerie extends Unite {

	public Artillerie( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible,new Mortier (), new SurChenilles(5));
	}
	public static  Artillerie stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Artillerie:1")) return new Artillerie(y,carteTailleY-x-1,1,true);
		if (str.equals("Artillerie:2")) return new Artillerie(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_ARTILLERIE));
	}
	
	public void deplacementInitial () {
		deplacement = new SurChenilles(5);
	}
}
