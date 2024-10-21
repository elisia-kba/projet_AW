package type_chemin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import position.Position;
import ressources.Affichage;
import ressources.Chemins;
import type_deplacement.APied;
import type_deplacement.Aerien;
import type_deplacement.SurChenilles;
import type_terrain.Terrain;
import type_unite.Unite;

public class Chemin {

	/**
	 * met a jour le chemin
	 * @param pos la nouvelle position que constitue le chemin
	 */
	private List<Position> chemin;


	public Chemin() {
		chemin = new LinkedList<Position>(); 
	}

	/**
	 * met a jour le chemin
	 * @param pos la nouvelle position que constitue le chemin
	 * @param a boolean qui indique si le chemin est possible
	 */
	public void ajoute(  Position pos, boolean a) {
		
		boolean boucle = false; //true si pos est une position presente dans chemin
		
		int i = 0;
		//parcourt des positions du chemin jusqu'a trouver une position avec les memes coordonnees que pos
		//ou lorsque que l'on a attein le bout du chemin
		while(i < chemin.size() && !boucle) {
			if(chemin.get(i++).equals(pos)) boucle = true;
		}
		
		//une position avec les mm coord que pos est deja dans chemin,
		//on eleve tous les elements apres cette position
		if(boucle) {	
			for(int j = chemin.size()-1 ; j >= i; j--) 
				chemin.remove(j);
		}
		

		else  if (a)chemin.add(pos);
		//s'il n'y a pas de position de meme coordonnes que pos n'est pas dans chemin

	}
	
	
	// Renvoit le nombre de deplacement effectués
	public float nbDeplacement(Unite uSel,int carteTailleY , Position pos, Map <Position ,Terrain> t,  Map <Position ,Unite> u) {
		int nbDeplacement =uSel.getDeplacement().getValeur();
		if (chemin.get(chemin.size()-1).isVoisinChemin(pos)) {
			if(!u.containsKey(pos.coordornee(carteTailleY)) || u.get(pos.coordornee(carteTailleY)).getJoueur()== uSel.getJoueur()) {
				if(uSel.getDeplacement() instanceof Aerien) return nbDeplacement - chemin.size()-1;
				if(uSel.getDeplacement() instanceof APied && deplacementApied(uSel,carteTailleY,pos,t,u) != -1) return nbDeplacement- deplacementApied(uSel,carteTailleY,pos,t,u);
				if(uSel.getDeplacement() instanceof SurChenilles && deplacementSurChenilles(uSel,carteTailleY,pos,t,u) !=-1) return nbDeplacement- deplacementSurChenilles(uSel,carteTailleY,pos,t,u);
			}
		}
	return -1;
	}

	

	public int deplacementApied( Unite uSel,int carteTailleY , Position pos, Map <Position ,Terrain> t,  Map <Position ,Unite> u) {

		int res =0;
		int i = 1;
		while(i < chemin.size()) {
			Position tmpP = chemin.get(i++).coordornee(carteTailleY);
			Terrain tmp = t.get(tmpP);
			if ( tmp.getCoutDeplacementPied().getValeur() == -1 )return -1;	
			else if(!u.containsKey(tmpP) ||u.get(tmpP).getJoueur()== uSel.getJoueur() ) {
				res += tmp.getCoutDeplacementPied().getValeur() ;

			}
			else return -1;
		}
		if(t.get(pos.coordornee(carteTailleY)).getCoutDeplacementPied().getValeur()!= -1 )
			res += t.get(pos.coordornee(carteTailleY)).getCoutDeplacementPied().getValeur();
		else return -1;

		return res;

	}


	public int deplacementSurChenilles( Unite uSel,int carteTailleY , Position pos, Map <Position ,Terrain> t,  Map <Position ,Unite> u ) {

		int res =0;
		int i = 1;
		while(i < chemin.size()) {
			Position tmpP = chemin.get(i++).coordornee(carteTailleY);
			Terrain tmp = t.get(tmpP);
			if ( tmp.getCoutDeplacementChenilles().getValeur() == -1 )return -1;	
			else if(!u.containsKey(tmpP) ||u.get(tmpP).getJoueur()== uSel.getJoueur() ) {
				res += tmp.getCoutDeplacementChenilles().getValeur() ;

			}
			else return -1;
		}
		if(t.get(pos.coordornee(carteTailleY)).getCoutDeplacementChenilles().getValeur()!= -1 )
			res += t.get(pos.coordornee(carteTailleY)).getCoutDeplacementChenilles().getValeur();
		else return -1;

		return res;

	}

	
	


	@Override
	public String toString() {
		String res = "[CHEMIN-DEBUT-----\n";
		for(int i = 0; i < chemin.size(); i++) {
			res += "CHEMIN : " + chemin.get(i) + "\n";
		}
		return res + "-------CHEMIN-FIN]\n";
	}


	public void display() {
		if(chemin.size() == 1) 
			Affichage.dessineImageDansCase(chemin.get(0).getX(), chemin.get(0).getY(), Chemins.getCheminFleche(Chemins.DIRECTION_DEBUT, Chemins.DIRECTION_FIN));

		else if(chemin.size() > 1){
			String suite = chemin.get(0).voisin(chemin.get(1));
			Affichage.dessineImageDansCase(chemin.get(0).getX(), chemin.get(0).getY(), Chemins.getCheminFleche(Chemins.DIRECTION_DEBUT,suite));


			String prec = chemin.get(1).voisin(chemin.get(0));
			for(int i = 1; i < chemin.size() - 1; i++) {

				prec = chemin.get(i).voisin(chemin.get(i-1));
				suite = chemin.get(i).voisin(chemin.get(i+1));
				Affichage.dessineImageDansCase(chemin.get(i).getX(), chemin.get(i).getY(), Chemins.getCheminFleche(prec,suite));
			}

			prec = chemin.get(chemin.size() -1).voisin(chemin.get(chemin.size() -2));
			Affichage.dessineImageDansCase(chemin.get(chemin.size() -1).getX(), chemin.get(chemin.size() -1).getY(), Chemins.getCheminFleche(prec,Chemins.DIRECTION_FIN));
		}

		


	}



	public List<Position> getChemin() {
		return chemin;
	}

	public void etatInitial() {
		for(int j = chemin.size()-1 ; j >= 0; j--) 
			chemin.remove(j);
	}

}
