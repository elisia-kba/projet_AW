package type_arme;

import java.util.HashMap;
import java.util.Map;

public class MitrailleuseLourde extends Armes{

	public MitrailleuseLourde() {
		super(initialisation());
	}
	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", 100);
		res.put("Bazooka", 80);
		res.put("Tank", 30);
		res.put("DCA", 30);
		res.put("Helicoptere", 110);
		res.put("Bombardier", 70);
		res.put("convoi", 70);
		res.put("Artillerie", 70);
		return res;
	}

}
