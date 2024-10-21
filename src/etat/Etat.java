package etat;


import ele_de_jeu.EleDeJeu;
import joueur.Joueur;
import position.Position;
import ressources.Affichage;
import type_unite.Unite;


public  class Etat {
	protected EleDeJeu edj ;
	
	
	public Etat (  EleDeJeu edj) {
		this.edj= edj ;
		
	}
	
	// Deplace le curseur et l'unité selectionée vers la droite
	public void actionDroite( ) {
		System.out.println("Touche DROITE");
		if( edj.curseurPos.getX()  < edj.carteTailleX-1)  {
			edj.curseurPos.setX( edj.curseurPos.getX()+1);
			if (edj.uniteSel != null ) {
				edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), possible());
			}
		}
	}

	// Deplace le curseur et l'unité selectionée vers le bas
	public void actionBas() {
		System.out.println("Touche BAS");
		if( edj.curseurPos.getY() > 0) {
			edj.curseurPos.setY( edj.curseurPos.getY()-1);
			if (edj.uniteSel != null ) {
				edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), possible());
			}
		}
	}

	// Deplace le curseur et l'unité selectionée vers la gauche
	public void  actionGauche() {
		System.out.println("Touche GAUCHE");
		if( edj.curseurPos.getX()  > 0)  {
			edj.curseurPos.setX( edj.curseurPos.getX()-1);
			if (edj.uniteSel != null ) {
				edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), possible());
			}
		}
	}
	
	// Deplace le curseur et l'unité selectionée vers le haut
	public  void actionHaut ( ) {
		System.out.println("Touche HAUT");
		if( edj.curseurPos.getY() <  edj.carteTailleY-1) { 
			edj.curseurPos.setY( edj.curseurPos.getY()+1);
			if (edj.uniteSel != null  ) {
				edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), possible());
			}
		}
	}
	
	// Renvoi le nouveau etat après avoir cliqué sur "entree"
	public Etat actionEntree() {
		Etat tmp = new EtatEntree(edj);
		tmp.actionEntree();
		
		return tmp;
	}
	
	// indique si le deplacement est possible
	public boolean possible() {
		return edj.carteC.nbDeplacement(edj.uniteSel, edj.carteTailleY,edj.curseurPos, edj.carteT, edj.carteU) >= 0;
	}
	
	
	
   //Permet le changement de joueur
	public  void actionT () {
		String[] options = {"Oui", "Non"};
		if (Affichage.popup("Finir votre tour?", options, true, 1) == 0) {
			edj.changementJoueur () ;
			System.out.println("FIN DE TOUR");
		}
	}
	
    //Met le curseur sur une case d'une unité disponible
	public void actionS () {
		Affichage.afficheTexteDescriptif("Touche S");
		boolean trouve = false;
		while(!trouve) {
			int indiceDispo = edj.getJoueurActif().indiceDispo();
			Unite tmpU =edj.getJoueurActif().getArmee().get(indiceDispo);
			if((edj.curseurPos.getX() != tmpU.getX() && edj.curseurPos.getY() != tmpU.getY() )||  edj.getJoueurActif().uneUnite() ) {
				edj.curseurPos.setX(tmpU.getX());
				edj.curseurPos.setY(tmpU.getY());
				trouve =true;
			}
		}
	}
	
	

	//Déselectionne une unité
	public Etat  actionEchap () {
		edj.carteC.etatInitial() ;
		edj.positionSel = null;
		edj.uniteSel = null ;
		return new Etat(edj);
	}
}
