package type_arme;

import java.util.HashMap;
import java.util.Map;

public class MitrailleuseLeg extends Armes{

	public MitrailleuseLeg() {
		super(initialisation());
		
	}

	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", 60);
		res.put("Bazooka", 55);
		res.put("Tank", 15);
		res.put("DCA", 10);
		res.put("Helicoptere", 30);
		res.put("Bombardier", -1);
		res.put("convoi", 40);
		res.put("Artillerie", 40);
		return res;
	}
}
