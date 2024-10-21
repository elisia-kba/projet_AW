package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.MitrailleuseLeg;
import type_deplacement.APied;

public class Infanterie extends Unite{
	
	public Infanterie( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible, new MitrailleuseLeg(), new APied(3));
	}
	public static  Infanterie stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Infanterie:1")) return new Infanterie(y,carteTailleY-x-1,1,true);
		if (str.equals("Infanterie:2")) return new Infanterie(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_INFANTERIE));
	}
	
	public void deplacementInitial () {
		deplacement = new APied(3);
	}
}
