package achatUnite;

import java.util.LinkedList;


import java.util.List;

import type_unite.Artillerie;
import type_unite.Bazooka;
import type_unite.Bombardier;
import type_unite.Convoi;
import type_unite.DCA;
import type_unite.Helicoptere;
import type_unite.Infanterie;
import type_unite.Tank;
import type_unite.Unite;


public class AchatUnite {
		private List< String> possible;


		public AchatUnite(int credit) {
			possible = new LinkedList<String>();
			if(credit>= 1500) possible.add("Infanterie 1500");
			if(credit>= 3500) possible.add("Bazooka 3500"); 
			if(credit>= 7000) possible.add ("Tank 7000");
			if(credit>= 6000) possible.add("DCA 6000");
			if(credit>= 12000)possible.add("Helicoptere 12000");
			if(credit>= 20000)possible.add("Bombardier 20000");
			if(credit>= 5000) possible.add("Convoi 5000");
			if(credit>= 6000) possible.add("Artillerie 6000");
		
	}
	
	public Unite uniteAchetee(int x, int y, int joueur,String str) {
		 if(str.equals("Infanterie")) return new Infanterie(x,y,joueur,false);
		 if(str.equals("Bazooka")) return new Bazooka(x,y,joueur,false);
		 if(str.equals("Tank"))  return new Tank(x,y,joueur,false);
		 if(str.equals("DCA")) return new DCA(x,y,joueur,false);
		 if(str.equals("Helicoptere")) return new Helicoptere(x,y,joueur,false);
		 if(str.equals("Bombardier")) return new Bombardier(x,y,joueur,false);
		 if(str.equals("Convoi") )return new Convoi(x,y,joueur,false);
		 if(str.equals("Artillerie") )return new Artillerie(x,y,joueur,false);
		 else return null ;
	}

	
	public String [] achatPossible() {
		String [] res = new String [possible.size()];
		for(int i=0; i< possible.size(); i++) {
			res[i] = possible.get(i);
		}
		return res;
	}
	
}
