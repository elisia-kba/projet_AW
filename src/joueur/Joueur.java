package joueur;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import position.Position;
import type_unite.Transport;
import type_unite.Unite;

public class Joueur {
	private List<Unite> armee ;
	private int credit ;
	private Position capturePos ;
	private String nom ;
	private Map<Unite, Unite> uniteTransport;
	private boolean uniteAchete ;

	public Joueur(String nom) {
		this.nom = nom;
		this.armee = new LinkedList<Unite>() ;
		this.credit = 0;
		uniteTransport = new HashMap<Unite, Unite>();
		capturePos = null ;
		uniteAchete = false ;
	}
	
	public void ajouteUniteTransport(Unite transport, Unite u) {
		uniteTransport.put(transport, u);
	}
	
	public void retireUniteTransport(Unite transport, Unite u) {
		uniteTransport.remove(transport, u);
	}

	//** ajoute une unité dans l'armee du joueur
	public void ajouteUnite (Unite u) {
		armee.add(u);
	}
	
	public void retireUnite (Unite u) {
		armee.remove(u);
	}

	//** verifie s'il il y'a des unités disponible dans l'armee du joueur
	public boolean uniteDispo () {
		boolean res = false;
		for(int i = 0 ; i < armee.size() && !res ; i++) {
			if(armee.get(i).isDisponible()) res = true;
		}
		return res;
	}

	//** verifie qu'il reste qu'une unité disponible	
	public boolean uneUnite() {
		int cpt =0;
		for(int i = 0 ; i < armee.size()   ; i++) {
			if(armee.get(i).isDisponible()) cpt ++ ;
		}
		return cpt==1;
	}


	//** renvoit un indince d'une unité disponible
	public int indiceDispo() {
		List<Integer> list =  new LinkedList<Integer>();
		for(int i = 0 ; i < armee.size()  ; i++) {
			if(armee.get(i).isDisponible()) {
				list.add(i);
			}
		}
		Random r = new Random(); // Pour renvoyer un int different à chaque fois
		int n = r.nextInt(list.size());
		return list.get(n);
	}
	
	



	@Override
	public String toString() {
		return "" + nom ;
	}

	public int getCredit() {
		return credit;
	}

	public void incrCredit() {
		this.credit +=1000;
	}
	

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	

	public boolean isUniteAchete() {
		return uniteAchete;
	}

	public void setUniteAchete(boolean uniteAchete) {
		this.uniteAchete = uniteAchete;
	}

	public Map<Unite, Unite> getUniteTransport() {
		return uniteTransport;
	}

	public List<Unite> getArmee() {
		return armee;
	}

	public Position getCapturePos() {
		return capturePos;
	}

	public void setCapturePos(Position capturePos) {
		this.capturePos = capturePos;
	}

}
