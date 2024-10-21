package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.Canon;
import type_deplacement.APied;



public class Bazooka extends Unite{
	
	public Bazooka( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible,  new Canon(), new APied(2));
		
	}
	
	public static Bazooka stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Bazooka:1")) return new Bazooka(y,carteTailleY-x-1,1,true);
		if (str.equals("Bazooka:2")) return new Bazooka(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_BAZOOKA));
	}
	
	public void deplacementInitial () {
		deplacement = new APied(2);
	}

	
	

}
