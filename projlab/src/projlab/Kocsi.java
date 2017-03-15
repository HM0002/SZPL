package projlab;

public class Kocsi extends Jarmu {
	
	private int szin;
	
	/*Kocsi(){
		super();
		szin = 0 + (int)(Math.random() * (6));
	}*/
	
	Kocsi(int i) {
		super();
		szin = i;
	}
	
	public void kiurit() {
		szin = 0;
	}
	
	public int getSzin() {
		return szin;
	}
	
}