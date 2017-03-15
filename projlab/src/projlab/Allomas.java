package projlab;

public class Allomas extends Sin {
	
	private int szin;
	private boolean aktiv;
	
	Allomas(Palya p, Sin sz0, Sin sz1, int i) {
		super(p, sz0, sz1);
		szin = i;
		aktiv = false;
	}
	
	public void setAlagut() {}
	
	public Sin elfogad(Mozdony m) {
		aktiv = true;
		
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();
				//return palya.alagut(this).szomszedok[0]; ezt kiprobalni, mert a fordito nem ad hibat WTF
		
		if (m.getElozoPozicio() == szomszedok[0])
			return szomszedok[1];
		else
			return szomszedok[0];			
	}
	
	public Sin elfogad(Kocsi k) {
		if (k.getSzin() != szin) {
			if (k.getSzin() != 0)
				aktiv = false;
		}
		else 
			if (aktiv == true)
				k.kiurit();
						
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();
				//return palya.alagut(this).szomszedok[0]; ezt kiprobalni, mert a fordito nem ad hibat WTF
		
		if (k.getElozoPozicio() == szomszedok[0])
			return szomszedok[1];
		else
			return szomszedok[0];	
	}
	
	public void setAktiv(boolean b) {
		aktiv = b;
	}
	
}