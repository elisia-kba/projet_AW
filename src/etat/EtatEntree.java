package etat;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import achatUnite.AchatUnite;
import ele_de_jeu.EleDeJeu;
import position.Position;
import ressources.Affichage;
import type_deplacement.APied;
import type_terrain.Propriete;
import type_terrain.Terrain;
import type_terrain.Usine;
import type_unite.Artillerie;
import type_unite.Transport;
import type_unite.Unite;

public class EtatEntree extends Etat {
	boolean actionAttaque = false ; // Determine si on est dans l'etat attaque ;
	boolean actionDeposer = false;
	

	public EtatEntree(EleDeJeu edj) {
		super(edj);

		
	}
	public boolean isActionAttaque() {
		return actionAttaque;
	}
	public boolean isActionDeposer() {
		return actionDeposer;
	}
	
	@Override
	public void actionDroite( ) {
		System.out.println("Touche DROITE");
		if(!actionAttaque && !actionDeposer) super.actionDroite();
		else if( edj.curseurPos.getX()  < edj.carteTailleX-1)  {
			edj.curseurPos.setX( edj.curseurPos.getX()+1);

		}
	}

	@Override
	public void  actionBas() {
		System.out.println("Touche BAS");
		if(!actionAttaque && !actionDeposer) super.actionBas();
		else if( edj.curseurPos.getY() > 0) {
			edj.curseurPos.setY( edj.curseurPos.getY()-1);
		}
	}
	
	@Override
	public  void actionGauche() {
		System.out.println("Touche GAUCHE");
		if(!actionAttaque && !actionDeposer) super.actionGauche();
		else if( edj.curseurPos.getX()  > 0)  {
			edj.curseurPos.setX( edj.curseurPos.getX()-1);
		}
	}
	
	@Override
	public  void actionHaut ( ) {
		System.out.println("Touche HAUT");
		if(!actionAttaque && !actionDeposer) super.actionHaut();
		else if( edj.curseurPos.getY() <  edj.carteTailleY-1) { 
			edj.curseurPos.setY( edj.curseurPos.getY()+1);
		}
	}
	
	@Override 
	public Etat actionEchap() { // permet d'annuler le choix qu'on a fait
		super.actionEchap();
		if(actionAttaque || actionDeposer) {
			actionAttaque = false;
			actionDeposer = false ;
		}
		return new Etat(edj);
	}

	@Override
	public Etat actionEntree ( ) {
		Position tmpPos = edj.curseurPos.coordornee(edj.carteTailleY);	
		if (edj.carteU.containsKey(tmpPos) ){	
			
			// Dans le cas ou est dans l'etat attaque
			if(actionAttaque) {
				if(ennemis(edj.positionSel.coordornee(edj.carteTailleY)).contains(tmpPos)) {
					actionAttaquer(edj.carteU.get(tmpPos));
					actionAttaque= false;
					edj.uniteSel.setDisponible(false);
					actionEchap();
					return new Etat(edj);
				}
				return this;
			}
			
			
			// selectionner une unité
			else if (edj.indexJoueurActif == edj.carteU.get(tmpPos).getJoueur() && edj.carteU.get(tmpPos).isDisponible() ) {
				if(edj.uniteSel == null ) {
					edj.uniteSel = edj.carteU.get(tmpPos);
					System.out.println("edj.uniteSel "+edj.uniteSel);
					edj.positionSel = new Position(edj.curseurPos.getX(), edj.curseurPos.getY());
					Etat tmp=actionSel();
					if(edj.uniteSel!= null ) {
						edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), true);
						return new Etat(edj);
					}
					return tmp;
				}
			}
		}
		
		//Ici on a déjà transporté une unité et on veut la deposer
		if(actionDeposer ) {
			deposerUnite(tmpPos);		
		}
		
		return actionSel();
	}
	
	
	/**
	 * Prends en paremetre une position , deplace l'unite tranporté si possible , sinon ne fait rien
	 * @param pos , l'unité adverse sélectionnée
	 * @return new Etat(edj) si depot effectué, sinon return l'etat dans lequelon se trouve
	 */
	public Etat deposerUnite(Position pos) {
		if(depotPossible(edj.positionSel.coordornee(edj.carteTailleY)).contains(pos)){
			Unite tmpU = edj.getJoueurActif().getUniteTransport().get(edj.uniteSel);
			((Transport) edj.uniteSel).setUniteTransporte(null);
			tmpU.setX(edj.curseurPos.getX());
			tmpU.setY(edj.curseurPos.getY());
			edj.carteU.put(pos, tmpU);
			edj.getJoueurActif().ajouteUnite(tmpU);
			actionDeposer = false;
			edj.uniteSel.setDisponible(false);
			actionEchap();
			return new Etat(edj);
		}
		return this;
	}

 


 // Permet au joueur de choisir une action et renvoit le nouvel Etat après l'action
	public Etat actionSel () {
		Affichage.afficheTexteDescriptif("Touche Entree");
		String [] options = actionPossible();
		if(options.length>0) {
			int choix = Affichage.popup("Que voulez vous faire?",options, true, 0) ;
			if(choix!= -1) {
				if(options[choix].equals("Attendre")) {
					actionDeplacement();
					edj.uniteSel.setDisponible(false);
					actionEchap();
					return new Etat(edj);
				}
				if(options[choix].equals("Capturer")) {

					actionCapturer();
					return new Etat(edj);
				}
				if(options[choix].equals("Attaquer")) {
					actionAttaque = true;
					if(!(edj.uniteSel instanceof Artillerie)) actionDeplacement();

					return this;
				}
				if(options[choix].equals("Acheter une Unité")) {	
					achatUnite();
					return new Etat(edj);
				}
				if(options[choix].equals("Monter à bord")) {
					edj.uniteSel.setDisponible(false);
					((Transport) edj.carteU.get(edj.curseurPos.coordornee(edj.carteTailleY))).setUniteTransporte(edj.uniteSel); ;
					edj.getJoueurActif().ajouteUniteTransport(edj.carteU.get(edj.curseurPos.coordornee(edj.carteTailleY)), edj.uniteSel);
					edj.carteU.remove(edj.positionSel.coordornee(edj.carteTailleY), edj.uniteSel);
					edj.getJoueurActif().retireUnite( edj.uniteSel);
					actionEchap();
					return new Etat(edj);
				}

				if(options[choix].equals("Deposer")) {
					actionDeposer = true;
					actionDeplacement();
					return this;
				}
			}
		}
		
		return new Etat(edj);
	}

	
	
	
	

	// Renvoit un tableau de String qui contient les actions possibles
	public String [] actionPossible() {
		List<String> options = new LinkedList<String>(); 
		Position tmpPos = (edj.curseurPos.coordornee(edj.carteTailleY));
		if(! edj.carteU.containsKey(tmpPos) || edj.curseurPos.equals(edj.positionSel)) {// On verifie que le curseur n'est pas sur unité autre que l'unité selectionnée
			if(edj.carteC.getChemin().size() >1 && edj.carteC.getChemin().contains(edj.curseurPos))options.add("Attendre");

			if ( ! edj.carteU.containsKey(tmpPos) && edj.carteP.containsKey(tmpPos) && edj.carteP.get(tmpPos) instanceof Usine  
					&& edj.carteP.get(tmpPos).getJoueur() == edj.indexJoueurActif && !edj.getJoueurActif().isUniteAchete()) { //Si on se trouve sur une unsine qui nous appartient
				options.add("Acheter une Unité");
			}
			
			if( edj.uniteSel != null && edj.uniteSel instanceof Transport ) { // Dans le cas ou on a sélectionné une unité de transport
				if(((Transport)edj.uniteSel).getUniteTransporte() != null && !depotPossible(tmpPos).isEmpty()) options.add("Deposer");
			}

			if( !ennemis(tmpPos).isEmpty()) {
				options.add("Attaquer");

			}
		}
		
		
		if(edj.uniteSel!= null && edj.uniteSel.getDeplacement() instanceof APied) { // Dans le cas ou l'unité selectionné est une unité à pied
			if (edj.carteP.containsKey(tmpPos) && edj.carteP.get(tmpPos).getJoueur() != edj.indexJoueurActif ) {
				if( edj.getJoueurActif().getCapturePos() == null || edj.getJoueurActif().getCapturePos().equals(edj.positionSel) )options.add("Capturer");
			}
			if(edj.carteU.containsKey(tmpPos) && edj.carteU.get(tmpPos) instanceof Transport ) { // Si le curseur se trouve sur une unité qui peux deplacer l'unité selectionée 
				options.add("Monter à bord");
				
			}
		}


		if(!options.isEmpty() && ! options.contains("Attendre")) options.add("Rien"); // permet de fermer le pop-up sans faire echap
		String [] res = new String [options.size()];
		for(int i=0; i<options.size(); i++) {
			res[i]= options.get(i);
		}
		return res ;
	}
	
	
	//Determine si une unité de tranport peut deposer l'unité qu'elle tranporte dans les cases adjacente
	public List<Position> depotPossible(Position pos) {
		List<Position> res = new LinkedList <Position>();
		for (Map.Entry<Position,Terrain> courant : edj.carteT.entrySet()) {
			if(courant.getKey().isVoisin(pos) ) {
				if(courant.getValue().getCoutDeplacementPied().getValeur() != -1) res.add(courant.getKey());
			}
		}
		return res ;
	}
	
	
	
	//Renvoit la liste des ennemis proche et qu'on peut attaquer
	public List<Position> ennemis (Position pos){
		List<Position> res = new LinkedList <Position>();
		if(!(edj.uniteSel instanceof Artillerie)) {
			for (Map.Entry<Position,Unite> courant : edj.carteU.entrySet()) {
				if(courant.getKey().isVoisin(pos) && courant.getValue().getJoueur() != edj.indexJoueurActif) {
					if(edj.uniteSel.getArme().efficacite(courant.getValue())!= -1)res.add(courant.getKey());
				}
			}
		}

		else {
			for (Map.Entry<Position,Unite> courant : edj.carteU.entrySet()) {
				for(int i =2; i<=3; i++) {
					if(courant.getKey().getX()== pos.getX()+i && courant.getKey().getY()== pos.getY())res.add(courant.getKey());
					if(courant.getKey().getX()== pos.getX()-i && courant.getKey().getY()== pos.getY())res.add(courant.getKey());
					if(courant.getKey().getX()== pos.getX() && courant.getKey().getY()-i == pos.getY())res.add(courant.getKey());
					if(courant.getKey().getX()== pos.getX() && courant.getKey().getY()+i== pos.getY())res.add(courant.getKey());


				}
			}
		}
		return res;
	}

	
	
	
	// Affiche les unités que le joueur peut acheter et met sur le terrain la nouvelle unité
		public void achatUnite( ) {
			Position pos = edj.curseurPos.coordornee(edj.carteTailleY);
			int credit = edj.getJoueurActif().getCredit();
			AchatUnite tmp = new AchatUnite(credit);
			String [] optionsAchat = tmp.achatPossible();
			Affichage.afficheTexteDescriptif("Votre solde est de :" + edj.getJoueurActif().getCredit());
			int choix = Affichage.popup("Quelle unité voulez-vous acheter?", optionsAchat, true, 1) ;

			if (choix != -1) {
				String [] tmpU = optionsAchat[choix].split(" ");
				edj.getJoueurActif().setCredit(credit- Integer.valueOf(tmpU[1]));
				edj.carteU.put(pos, tmp.uniteAchetee(edj.curseurPos.getX(), edj.curseurPos.getY(), edj.indexJoueurActif, tmpU[0]));
				edj.getJoueurActif().ajouteUnite(tmp.uniteAchetee(edj.curseurPos.getX(), edj.curseurPos.getY(), edj.indexJoueurActif, tmpU[0]));
				edj.getJoueurActif().setUniteAchete(true);

			}
		}
	
	
		
		

	// Deplace une unité 
	public void actionDeplacement() {
		if(!edj.curseurPos.equals(edj.positionSel) && edj.carteC.getChemin().contains(edj.curseurPos)) {
			int nbDeplacement = (int)edj.carteC.nbDeplacement(edj.uniteSel, edj.carteTailleY, edj.curseurPos, edj.carteT,edj.carteU);
			
			if(edj.getJoueurActif().getCapturePos() != null && edj.positionSel.equals(edj.getJoueurActif().getCapturePos())) {
				edj.carteP.get(edj.getJoueurActif().getCapturePos().coordornee(edj.carteTailleY)).setResistance(20);
				edj.getJoueurActif().setCapturePos(null);
			}
			
			edj.uniteSel.getDeplacement().setValeur(nbDeplacement);
			edj.uniteSel.setX(edj.curseurPos.getX());
			edj.uniteSel.setY(edj.curseurPos.getY());
			edj.carteU.remove(edj.positionSel.coordornee(edj.carteTailleY), edj.uniteSel) ;
			edj.carteU.put(edj.curseurPos.coordornee(edj.carteTailleY), edj.uniteSel) ;		
			edj.positionSel=new Position(edj.curseurPos.getX(), edj.curseurPos.getY());
		}
		if(actionAttaque || actionDeposer) {
			edj.carteC.etatInitial();
			edj.carteC.ajoute(new Position(edj.curseurPos.getX(), edj.curseurPos.getY()), true);
		}
	}

	
	
	
	
	// Permet de capturer une proprieté neutre ou ennemi
	public void actionCapturer() {
		
		Position tmpPos = edj.curseurPos.coordornee(edj.carteTailleY);
		Propriete tmpP = edj.carteP.get(tmpPos);
		actionDeplacement();
		edj.getJoueurActif().setCapturePos(edj.positionSel);
		tmpP.setResistance(tmpP.getResistance() - (int)edj.uniteSel.getPv());
		
		if(tmpP.getResistance() ==0){
			tmpP.setJoueur(edj.indexJoueurActif);
			tmpP.setResistance(20);
			edj.carteT.put(tmpPos, tmpP);
			edj.carteP.put(tmpPos, tmpP);
			edj.getJoueurActif().incrCredit();
			edj.getJoueurActif().setCapturePos(null);
		}
		edj.uniteSel.setDisponible(false);
        actionEchap();
	}
	
	
	
	
	/**
	 * Attaque l'unité sélectionée
	 * @param ennemi , l'unité adverse sélectionnée
	 */
	public void actionAttaquer(Unite ennemi) {
		double efficacite = (edj.uniteSel.getArme().efficacite(ennemi) * edj.uniteSel.getPv())/100;
		ennemi.setPv(ennemi.getPv()- efficacite);
		if(ennemi.getArme().efficacite(edj.uniteSel) != -1 && !(edj.uniteSel instanceof Artillerie) && !(ennemi instanceof Artillerie)) {
			efficacite =(ennemi.getArme().efficacite(edj.uniteSel) * ennemi.getPv())/100;
			edj.uniteSel.setPv(edj.uniteSel.getPv() - (int) efficacite);
		}
		edj.uniteSel.setDisponible(false);
		if(ennemi.getPv()==0.0) {
			edj.carteU.remove(edj.curseurPos.coordornee(edj.carteTailleY), ennemi);
			if(edj.getEnnemi().getCapturePos() != null && edj.getEnnemi().getCapturePos().equals(edj.curseurPos)) {
				edj.carteP.get(edj.getEnnemi().getCapturePos().coordornee(edj.carteTailleY)).setResistance(20);;
				edj.getEnnemi().setCapturePos (null) ;
			}
		}
	}




}