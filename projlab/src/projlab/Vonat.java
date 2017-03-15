package projlab;

import java.util.ArrayList;

public class Vonat {
	
	private ArrayList<Jarmu> jarmuvek;
	
	Vonat(int i) {
		jarmuvek = new ArrayList<Jarmu>();
		// 1 mozdony es i-1 kocsi konstruktor hivas, majd jarmuvek.add mindet
	}
	
	//szeritem ez nem fog kelleni
	public Jarmu getJarmu(int i) {
		if (jarmuvek.size() >= i)
			return jarmuvek.get(i);		
		//else throw exception
		return null;
	}
	
	public ArrayList<Jarmu> getVonat(){
		return jarmuvek;
	}
	
}