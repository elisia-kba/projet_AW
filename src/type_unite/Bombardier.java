package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.Bombes;
import type_deplacement.Aerien;


public class Bombardier extends Unite{
	
	public Bombardier( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible,new Bombes(), new Aerien(7));
	}
	public static  Bombardier stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("Bombardier:1")) return new Bombardier(y,carteTailleY-x-1,1,true);
		if (str.equals("Bombardier:2")) return new Bombardier(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_BOMBARDIER));
	}
	
	public void deplacementInitial () {
		deplacement = new Aerien(7);
	}
	
	
}
