package position;
import ressources.Chemins;

public class Position {
	int x;
	int y;

	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int x() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}

	 /**
     * Retrourne le fichier image de la fleche correspondant au deplacement
     * @param other la position ou l'on souhaite se deplacer
     * @return retourne un String du fichier correspondant
     */
	public String voisin(Position other) {
		if(voisinCheminDroite(other)) return Chemins.DIRECTION_DROITE;
		if(voisinCheminGauche(other)) return Chemins.DIRECTION_GAUCHE;
		if(voisinCheminHaut(other)) return Chemins.DIRECTION_HAUT;
		if(voisinCheminBas(other)) return Chemins.DIRECTION_BAS;
		else return "";
	}

	/**
     * Determine si la position other est voisine sans prendre en compte les diagonales , sert à augmenter le chemin
     * @param other la position ou l'on souhaite se deplacer
     * @return un boolean
     */
	public boolean isVoisinChemin (Position other) {
		return voisinCheminDroite(other) || voisinCheminGauche(other) || voisinCheminHaut(other) || voisinCheminBas(other);
	}
	
	/**
     * Determine si la position other est voisine en prenant en compte les diagonales, sert à attaquer
     * @param other la position ou l'on souhaite se deplacer
     * @return un boolean
     */
	public boolean isVoisin (Position other) {
		return voisinDroite(other) || voisinGauche(other) || voisinHaut(other) || voisinBas(other);
	}
	
	
	// Determine si une position est voisineen prenant en compte les diagonales 
	public boolean voisinDroite(Position other) {
		if (this.x == other.x - 1 ) {
			if( this.y == other.y || this.y == other.y+1 || this.y == other.y-1) return true;
		}
		return false;
	}

	public boolean voisinGauche(Position other) {
		if (this.x == other.x + 1 ) {
			if( this.y == other.y || this.y == other.y+1 || this.y == other.y-1) return true;
		}
		return false;
	}

	public boolean voisinHaut(Position other) {
		if (this.y == other.y - 1 ) {
			if( this.x == other.x || this.x == other.x+1 || this.x == other.x-1) return true;
		}
		return false;
	}

	public boolean voisinBas(Position other) {
		if (this.y == other.y + 1 ) {
			if( this.x == other.x || this.x == other.x+1 || this.x == other.x-1) return true;
		}
		return false;
	}

	
// Determine si une position est voisine sans prendre en compte les diagonales 
	
	public boolean voisinCheminDroite(Position other) {
		return (this.x == other.x - 1 && this.y == other.y);
	}

	public boolean voisinCheminGauche(Position other) {
		return (this.x == other.x + 1 && this.y == other.y);
	}

	public boolean voisinCheminHaut(Position other) {
		return (this.x == other.x && this.y == other.y - 1);
	}

	public boolean voisinCheminBas(Position other) {
		return (this.x == other.x && this.y == other.y + 1);
	}

	public boolean meme(Position other) {
		return (this.x == other.x && this.y == other.y );
	}

	@Override
	public String toString(){
		return x + " " + y;
	}


	public Position coordornee( int carteTailleY) {
		return new Position( carteTailleY -y-1 , x);
	}
	//produit un nombre unique (comme une ID) pour rapidement reconnaitre la valeur en entr e

	@Override
	public int hashCode() {
		int multiplier = 35;
		if(x % 2 == 0) {
			return x + y * multiplier;
		}
		return (x + y * multiplier) * -1;
	}
	//renvoie true si les deux hashCode sont identiques, indiquant que l'objet en parametre
	//est le meme que l'objet courant (this)
	@Override
	public boolean equals(Object other) {
		if(other != null) {
			return this.hashCode() == other.hashCode();
		}
		return false;

	}

}
