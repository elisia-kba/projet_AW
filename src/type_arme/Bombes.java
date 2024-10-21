package type_arme;

import java.util.HashMap;
import java.util.Map;

public class Bombes extends Armes{
	 

	public Bombes() {
		super(initialisation());
		
	}
	
	public static Map <String, Integer>  initialisation (){
		Map <String, Integer> res =new HashMap <String, Integer> ();
		res.put("Infanterie", 100);
		res.put("Bazooka", 100);
		res.put("Tank", 100);
		res.put("DCA", 70);
		res.put("Helicoptere", -1);
		res.put("Bombardier", -1);
		res.put("convoi", 100);
		res.put("Artillerie", 100);
		return res;
		
	}

}