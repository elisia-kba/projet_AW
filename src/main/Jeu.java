/** package principal */
package main;
import librairies.AssociationTouches;
import librairies.StdDraw;
import position.Position;
import ressources.Config;
import ressources.ParseurCartes;
import type_terrain.Propriete;
import type_terrain.Terrain;
import type_unite.Transport;
import type_unite.Unite;
import ressources.Affichage;




import ele_de_jeu.EleDeJeu;
import etat.Etat;
import etat.EtatEntree;
 

public class Jeu {
	private EleDeJeu edj = new EleDeJeu();	//on initialiste les elements du jeu
	private Etat etat;

	

	public Jeu(String fileName) throws Exception {
		//appel au parseur, qui renvoie un tableau de String 
		String [][] carteString = ParseurCartes.parseCarte(fileName);
		edj.setTailleCarte(carteString[0].length, carteString.length);
		
		for (int i = 0; i < edj.getTailleY(); i++) {
			for (int j = 0; j <edj.getTailleX(); j++){
				Position pos = new Position(i,j);
				
				//on separe la partie terrain de la partie unite
				String [] tmp =carteString[i][j].split(";");
				if (tmp.length ==2 ) {
					Unite tmpU = Unite.stringEnUnite(i,j, tmp[1],edj.carteTailleY);
					if (tmpU != null) {
						edj.carteU.put(pos, tmpU);
						if (tmpU.getJoueur()==1) edj.rouge.ajouteUnite(tmpU);
						if (tmpU.getJoueur()==2)  edj.bleu.ajouteUnite(tmpU);
					}
				}
				edj.carteT.put(pos, Terrain.stringEnTerrain(i,j,tmp[0],edj.carteTailleY));
				if(Terrain.stringEnTerrain(i,j,tmp[0],edj.carteTailleY) instanceof Propriete )
				edj.carteP.put(pos, (Propriete.stringEnPropriete(i, j, tmp[0], edj.carteTailleY)));
				
				System.out.print(carteString[i][j]);
				if (j != edj.carteTailleX) {
					System.out.print(" | ");
				}
				else {
					System.out.println();
				}
			}
			
			System.out.println();
		}	


		Config.setDimension(carteString[0].length, carteString.length);
		// initialise la configuration avec la longueur de la carte
		edj.credit();
		edj.indexJoueurActif = 1; // rouge commence
		etat= new Etat (edj);
	}


	public boolean isOver() {
		return false;
	}

	public void afficheStatutJeu() {
		Affichage.videZoneTexte();
		String status ="Joueur actif " +edj.getJoueurActif().toString();
		if(etat instanceof EtatEntree) {
			if(((EtatEntree) etat).isActionAttaque())status="Vous devez choisir une unité à attaquer";
			if(((EtatEntree) etat).isActionDeposer())status="Vous devez choisir un terrain pour deposer votre unité";
		}
		Affichage.afficheTexteDescriptif(status);
	}


	public void display() {
		StdDraw.clear();
		
		afficheStatutJeu();
		edj.display();
		//Affichage.dessineGrille(); //affiche une grille, mais n'affiche rien dans les cases		
		drawGameCursor();
		StdDraw.show(); //montre a l'ecran les changement demandes
	}

	public void initialDisplay() {
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche qu'au prochain StdDraw.show();
		display();
	}

	public void drawGameCursor() {
		Affichage.dessineCurseur(edj.curseurPos.getX(), edj.curseurPos.getY()); 
	}

	public void update() {
		AssociationTouches toucheSuivante = AssociationTouches.trouveProchaineEntree(); //cette fonction boucle jusqu'a la prochaine entree de l'utilisateur
		edj.finDeTourAuto();
		if(toucheSuivante.isHaut()) 		etat.actionHaut();
		if(toucheSuivante.isBas()) 			etat.actionBas();
		if(toucheSuivante.isGauche()) 		etat.actionGauche();
		if(toucheSuivante.isDroite())		etat.actionDroite();
		if(toucheSuivante.isCaractere('t')) etat.actionT() ;
		if(toucheSuivante.isEchap()) 		etat = etat.actionEchap();
		if(toucheSuivante.isCaractere('s')) etat.actionS();
		if(toucheSuivante.isEntree()) 		etat= etat.actionEntree();
		display();
	}
}


