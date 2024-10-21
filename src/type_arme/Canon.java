package type_arme;

import java.util.HashMap;
import java.util.Map;

public class Canon extends Armes{

	public Canon() {
		super(initialisation());
	}
	
	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", 45);
		res.put("Bazooka", 45);
		res.put("Tank", 55);
		res.put("DCA", 60);
		res.put("Helicoptere", 30);
		res.put("Bombardier", -1);
		res.put("convoi", 70);
		res.put("Artillerie", 70);
		return res ;
	}

}
