package type_arme;

import java.util.HashMap;
import java.util.Map;

public class Mortier extends Armes {
	
	public Mortier() {
		super(initialisation());
	}
	
	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Artillerie", 70);
		res.put("Infanterie", 40);
		res.put("Bazooka", 50);
		res.put("Tank", 70);
		res.put("DCA", 70);
		res.put("Helicoptere", -1);
		res.put("Bombardier", -1);
		res.put("convoi", 70);
		return res ;
	}
}
