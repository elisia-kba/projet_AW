package type_unite;

import type_arme.Armes;
import type_deplacement.Deplacement;

public abstract class  Transport extends Unite{

	private Unite uniteTransporte ;
	
	public Transport(int x, int y, int joueur, boolean disponible, Armes arme, Deplacement deplacement) {
		super(x, y, joueur, disponible, arme, deplacement);
		uniteTransporte = null;
	}

	public Unite getUniteTransporte() {
		return uniteTransporte;
	}

	public void setUniteTransporte(Unite uniteTransporte) {
		this.uniteTransporte = uniteTransporte;
	}

	public static Transport stringEnTransport (int x, int y,String str,int carteTailleY) {
		String [] tmp =str.split(":");
		if (tmp[0].equals("Convoi")) return Convoi.stringEnUnite(x, y, str, carteTailleY);
		if(tmp[0].equals("Helico")) return Helicoptere.stringEnUnite(x, y, str, carteTailleY);
		return null;
	}
}
