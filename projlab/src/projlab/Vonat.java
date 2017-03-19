package projlab;

import java.util.ArrayList;

public class Vonat {
	
	private ArrayList<Jarmu> jarmuvek;
	
	/**Konstruktor*/
	Vonat(ArrayList<Jarmu> j) {
		jarmuvek = j;
	}
	
	public ArrayList<Jarmu> getJarmuvek(){
		if (jarmuvek.isEmpty())
			//throw exception
			return null;
		else
			return jarmuvek;
	}
	
}