package type_unite;

import type_arme.Armes;
import type_deplacement.Deplacement;

abstract public class Unite {
	double pv ;
	int x;
	int y;
	int joueur ;
	boolean disponible ;
	Armes arme;
	Deplacement deplacement ;
	
	
	public Unite (int x, int y, int joueur, boolean disponible, Armes arme, Deplacement deplacement) {
		this.joueur= joueur ;
		this.x= x;
		this.y= y;
		this.disponible = disponible ;
		this.pv = 10 ;
		this.arme = arme;
		this.deplacement= deplacement ;
	}
	
	public abstract void display ();
	
	public abstract void deplacementInitial() ;
	
	

	public static Unite stringEnUnite (int x, int y,String str,int carteTailleY) {
		String [] tmp =str.split(":");
		if (tmp[0].equals("DCA")) 			return DCA.stringEnUnite(x, y, str, carteTailleY);
		if (tmp[0].equals("Bombardier"))	return Bombardier.stringEnUnite(x,y,str,carteTailleY) ;
		if (tmp[0].equals("Bazooka")) 		return Bazooka.stringEnUnite(x, y, str, carteTailleY);
		if (tmp[0].equals("Tank")) 			return Tank.stringEnUnite(x, y, str, carteTailleY);
		if (tmp[0].equals("Infanterie")) 	return  Infanterie.stringEnUnite(x, y, str, carteTailleY);
		if (tmp[0].equals("Artillerie")) 	return  Artillerie.stringEnUnite(x, y, str, carteTailleY);
		else return Transport.stringEnTransport(x, y, str, carteTailleY);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getJoueur() {
		return joueur;
	}


	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public double getPv() {
		return pv;
	}


	public void setPv(double pv) {
		this.pv = pv;
	}

	public Armes getArme() {
		return arme;
	}

	public Deplacement getDeplacement() {
		return deplacement;
	}
	
	public void setDeplacement(Deplacement deplacement) {
		this.deplacement = deplacement;
	}
	
	

}
