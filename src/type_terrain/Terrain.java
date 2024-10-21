package type_terrain;

import type_deplacement.APied;
import type_deplacement.SurChenilles;

abstract public class Terrain {
	protected int x;
	protected int y;
	protected APied coutDeplacementPied ;
	protected SurChenilles coutDeplacementChenilles;
	
	public Terrain (int x, int y, APied coutDeplacementPied, SurChenilles coutDeplacementChenilles) {
		this.x = x;
		this.y = y ;
		this.coutDeplacementPied = coutDeplacementPied;
		this.coutDeplacementChenilles = coutDeplacementChenilles;
	}
	
	public abstract void display () ;
	
	public static Terrain stringEnTerrain (int x, int y,String str,int carteTailleY) {
		if (str.equals("Plaine")) return new Plaine(y,carteTailleY-x-1);
		if (str.equals("Eau")) return new Eau(y,carteTailleY-x-1);
		if (str.equals("Foret")) return new Foret(y,carteTailleY-x-1);
		if (str.equals("Montagne")) return  new Montagne(y,carteTailleY-x-1);
		else return Propriete.stringEnPropriete(x, y, str, carteTailleY);
	}

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public APied getCoutDeplacementPied() {
		return coutDeplacementPied;
	}

	public SurChenilles getCoutDeplacementChenilles() {
		return coutDeplacementChenilles;
	}
	
}
