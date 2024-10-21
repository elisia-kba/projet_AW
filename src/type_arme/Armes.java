package type_arme;

import java.util.HashMap;
import java.util.Map;

import type_unite.Bazooka;
import type_unite.Bombardier;
import type_unite.Convoi;
import type_unite.DCA;
import type_unite.Helicoptere;
import type_unite.Infanterie;
import type_unite.Tank;
import type_unite.Unite;

public abstract class Armes {
	
	protected Map <String ,Integer> efficacite ;


	public Armes(Map <String ,Integer> efficacite) {
		this.efficacite = efficacite;
	}




	public int efficacite (Unite unite) {
		if(unite instanceof Infanterie) return efficacite.get("Infanterie");
		if(unite instanceof DCA) return efficacite.get("DCA");
		if(unite instanceof Tank) return efficacite.get("Tank");
		if(unite instanceof Bazooka) return efficacite.get("Bazooka");
		if(unite instanceof Bombardier) return efficacite.get("Bombardier");
		if(unite instanceof Helicoptere) return efficacite.get("Helicoptere");
		if(unite instanceof Convoi) return efficacite.get("Convoi");
		else return 0;
	}
	
	
}
