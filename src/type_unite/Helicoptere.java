package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.Missiles;
import type_deplacement.Aerien;


public class Helicoptere extends Transport {
	
	
	public Helicoptere( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible, new Missiles(),new Aerien(6));
	}
	public static  Helicoptere stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Helico:1")) return new Helicoptere(y,carteTailleY-x-1,1,true);
		if (str.equals("Helico:2")) return new Helicoptere(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_HELICOPTERE));
	}
	public void deplacementInitial () {
		deplacement = new Aerien(6);
	}
}
