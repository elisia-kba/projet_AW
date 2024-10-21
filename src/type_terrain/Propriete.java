package type_terrain;

import type_deplacement.APied;
import type_deplacement.SurChenilles;

public abstract class Propriete extends Terrain{
	int joueur ;
	double resistance ;

	public Propriete(int x, int y, int joueur) {
		super(x,y,new APied(1), new SurChenilles(1));
		this.joueur = joueur;
		resistance =20 ;
	}

	public int getJoueur() {
		return joueur;
	}
	public static Propriete stringEnPropriete (int x, int y,String str,int carteTailleY) {
	if (str.equals("QG:2")) return new QG(y,carteTailleY-x-1,2);
	if (str.equals("QG:1")) return new QG(y,carteTailleY-x-1,1);
	if (str.equals("QG:0")) return new QG(y,carteTailleY-x-1,0);
	if (str.equals("Ville:0")) return new Ville(y,carteTailleY-x-1,0);
	if (str.equals("Ville:1")) return new Ville(y,carteTailleY-x-1,1);
	if (str.equals("Ville:2")) return new Ville(y,carteTailleY-x-1,2);
	if (str.equals("Usine:2")) return new Usine(y,carteTailleY-x-1,2);
	if (str.equals("Usine:1")) return new Usine(y,carteTailleY-x-1,1);
	if (str.equals("Usine:0")) return new Usine(y,carteTailleY-x-1,0);
	else return null;
	}
	
	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public double getResistance() {
		return resistance;
	}

	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	
	

}
