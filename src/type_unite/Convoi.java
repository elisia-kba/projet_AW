package type_unite;
import ressources.Affichage;
import ressources.Chemins;
import type_arme.Desarme;

import type_deplacement.SurChenilles;

public class Convoi extends Transport{
	
	public Convoi( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible, new Desarme(), new SurChenilles(6));
	}
	
	public static  Convoi stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Convoi:1")) return new Convoi(y,carteTailleY-x-1,1,true);
		if (str.equals("Convoi:2")) return new Convoi(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_GENIE));
	}
	
	public void deplacementInitial () {
		deplacement = new SurChenilles(6);
	}
	
	
	
}
