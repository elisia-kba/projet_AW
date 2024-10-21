package type_arme;

import java.util.HashMap;
import java.util.Map;

public class Desarme extends Armes {
	public Desarme() {
		super(initialisation());
	}
	
	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", -1);
		res.put("Bazooka", -1);
		res.put("Tank", -1);
		res.put("DCA", -1);
		res.put("Helicoptere", -1);
		res.put("Bombardier", -1);
		res.put("convoi", -1);
		res.put("Artillerie", -1);
		return res ;
	}
}
