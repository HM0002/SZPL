package projlab;

import java.util.ArrayList;

public class Palya {
	
	private ArrayList<Sin> elemek;
	private int vonatSzam;
	private int kocsiSzam;
	private int alagutSzam;
	private int keslelteto;
	
	Palya(int vSz, int kSz, int k) {
		elemek = new ArrayList<Sin>();
		//Sin eket felhivja, vagy megkapja??
		vonatSzam = vSz;
		kocsiSzam = kSz;
		keslelteto = k;
		alagutSzam = 0;
	}
	
	public int getVonatSzam() {
		return vonatSzam;
	}
	
	public int getKocsiSzam() {
		return kocsiSzam;
	}
	
	public int getAlagutSzam() {
		return alagutSzam;
	}
	
	public void setAlagutSzam(int i) {
			alagutSzam += i;
	}
	
	public int getKeslelteto() {
		return keslelteto;
	}
	
	public Sin getSin(int i) {
		if (elemek.size() >= i)
			return elemek.get(i);
		else
			//throw exception
			return null;
	}
	
	public Sin getKezdoSin() {
		if (elemek.isEmpty())
			//throw exception
			return null;
		else
			return elemek.get(0);
	}
	
	public Sin alagut(Sin s) {
		for (Sin sin : elemek) {
			if (sin.getAlagut() == true && sin != s)
				return sin;
		}
		
		//this should never happen
		return null;
	}
	
}
