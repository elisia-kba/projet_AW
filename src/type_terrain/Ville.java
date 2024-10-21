package type_terrain;

import ressources.Affichage;
import ressources.Chemins;

public class Ville extends Propriete {
	
	public Ville (int x, int y,int joueur) {
		super(x,y,joueur);
	}
	
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminPropriete(Chemins.FICHIER_VILLE,joueur));
	}
}
