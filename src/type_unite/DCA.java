package type_unite;

import ressources.Affichage;
import ressources.Chemins;
import type_arme.MitrailleuseLourde;
import type_deplacement.SurChenilles;

public class DCA extends Unite{
	
	
	public DCA( int x, int y ,int joueur, boolean disponible) {
		super(x,y,joueur,disponible, new MitrailleuseLourde(), new SurChenilles(6));
	}
	public static  DCA stringEnUnite (int x, int y,String str,int  carteTailleY) {
		if (str.equals("DCA:1")) return new DCA(y,carteTailleY-x-1,1,true);
		if (str.equals("DCA:2")) return new DCA(y,carteTailleY-x-1,2, true);
		else return null ;
	}
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminUnite(joueur, disponible,Chemins.FICHIER_ANTIAIR));
	}
	public void deplacementInitial () {
		deplacement = new SurChenilles(6);
	}

}
