package type_terrain;

import ressources.Affichage;
import ressources.Chemins;
import type_deplacement.APied;
import type_deplacement.SurChenilles;

public class Eau extends Terrain{	
	public Eau (int x, int y) {
		super(x,y,new APied(-1), new SurChenilles(-1));
	}

	public void display () {
		Affichage.dessineImageDansCase(x, y, Chemins.getCheminTerrain(Chemins.FICHIER_EAU));
	}
}



