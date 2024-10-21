package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.Canon;
import type_deplacement.SurChenilles;

public class Tank extends Unite{
	
	public Tank(int x, int y ,int joueur, boolean disponible ) {
		super(x,y,joueur, disponible, new Canon(), new SurChenilles(6));
	}
	
	public static  Tank stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Tank:1")) return new Tank(y,carteTailleY-x-1,1,true);
		if (str.equals("Tank:2")) return new Tank(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_TANK));
	}
	
	public void deplacementInitial () {
		deplacement = new SurChenilles(6);
	}
}
