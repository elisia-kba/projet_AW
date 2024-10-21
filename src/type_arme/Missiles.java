package type_arme;

import java.util.HashMap;
import java.util.Map;

public class Missiles extends Armes{

	public Missiles() {
		super(initialisation());
	}

	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", 50);
		res.put("Bazooka", 50);
		res.put("Tank", 70);
		res.put("DCA", 40);
		res.put("Helicoptere", 70);
		res.put("Bombardier", 70);
		res.put("convoi", 70);
		res.put("Artillerie", 70);

		return res;
	}

}
