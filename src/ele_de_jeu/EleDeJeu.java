package ele_de_jeu;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


import joueur.Joueur;
import position.Position;
import ressources.Affichage;
import type_chemin.Chemin;
import type_terrain.Propriete;
import type_terrain.Terrain;
import type_unite.Transport;
import type_unite.Unite;

public class EleDeJeu {
	public int indexJoueurActif; //l'indice du joueur actif:  1 = rouge, 2 = bleu
	// l'indice 0 est reserve au neutre, qui ne joue pas mais peut posseder des proprietes
	public Map <Position, Terrain> carteT;	 //carte terrain
	public Map <Position, Unite> carteU;	 //carte unite
	public Map <Position, Propriete> carteP; // carte proprieté
	public Chemin carteC;                    //carte Chemin
	public Position curseurPos;              // la position du curseur 
	public Unite uniteSel = null;            //unite actuellement selectionnee
	public Position positionSel = null ;     //position actuellement selectionnee
	public int carteTailleX;	             //taille horizontale de la carte
	public int carteTailleY;	             //taille verticalle de la carte
	public Joueur rouge ;					 // joueur rouge
	public Joueur bleu ;


	public EleDeJeu() {
		//appel au parseur, qui renvoie un tableau de String 
		curseurPos  = new Position(0, 0);
		rouge		= new Joueur("rouge");
		bleu   		= new Joueur("bleu");
		carteT 		= new HashMap <Position, Terrain> ();
		carteU		= new HashMap <Position, Unite> ();
		carteP 		= new HashMap <Position, Propriete> ();
		carteC 		= new Chemin();


	}
	

	
	public void credit() {
		for (Map.Entry<Position,Propriete> courant : carteP.entrySet()) {
			if(courant.getValue().getJoueur()==1) rouge.incrCredit();
			else if(courant.getValue().getJoueur()== 2) bleu.incrCredit();
		}
	}

	//remet toutes les unites comme disponible
	public void changementJoueur() {
		getJoueurActif().setUniteAchete(false);
		if( indexJoueurActif == 1)indexJoueurActif = 2 ;
		else indexJoueurActif = 1;
		for(Map.Entry<Position, Unite> courant : carteU.entrySet()) {
			courant.getValue().setDisponible(true);	
			courant.getValue().deplacementInitial();
			
		}
	}
	
	public void finDeTourAuto () {
		if(!getJoueurActif().uniteDispo()) changementJoueur();
	}
	
	
	public void display () {
		for (Map.Entry<Position,Terrain> courant : carteT.entrySet()) {
			courant.getValue().display();
		}
		for (Map.Entry<Position,Propriete> courant : carteP.entrySet()) {
			if(courant.getValue().getResistance()<20) {
				DecimalFormat df = new DecimalFormat("0.00");
				String tmpR = df.format(courant.getValue().getResistance());
				Affichage.afficheTexteDansCase(courant.getValue().getX(),courant.getValue().getY(), tmpR,Color.BLACK,0.7,0.0, new Font("SansSerif", Font.PLAIN, 12));	
			}
		}
		for (Map.Entry<Position,Unite> courant : carteU.entrySet()) {
			courant.getValue().display();
			if(courant.getValue().getPv()<10) {
			DecimalFormat df = new DecimalFormat("0.00");
			String tmpPv = df.format(courant.getValue().getPv());
			Affichage.afficheTexteDansCase(courant.getValue().getX(),courant.getValue().getY(), tmpPv,Color.WHITE,0.7,0.0, new Font("SansSerif", Font.PLAIN, 12));
			}
		}
		carteC.display();
		System.out.println(carteC);
		
	}

	public void setTailleCarte(int tailleX, int tailleY) {
		this.carteTailleX = tailleX;
		this.carteTailleY = tailleY;
	}

	public int getTailleX() {
		return carteTailleX;
	}

	public int getTailleY() {
		return carteTailleY;
	}

	public Map <Position, Terrain> getCarteT(){
		return carteT;
	}

	public Map <Position, Unite> getCarteU(){
		return carteU;
	}

	public Chemin getChemin(){
		return carteC;
	}

	
	public Joueur getJoueurActif() {
		if  (indexJoueurActif == 1) return rouge;
		else return bleu ;
	}
	
	public Joueur getEnnemi () {
		if  (indexJoueurActif == 1) return bleu;
		else return rouge ;
	}

	

}
