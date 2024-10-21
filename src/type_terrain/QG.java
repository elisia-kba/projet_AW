package type_terrain;

import ressources.Affichage;
import ressources.Chemins;

public class QG extends Propriete{
	
	
	public QG (int x, int y, int joueur) {
		super(x,y,joueur);
	}
	
	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminPropriete(Chemins.FICHIER_QG,joueur));
	}
}
