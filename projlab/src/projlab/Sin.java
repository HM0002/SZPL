package projlab;

public class Sin {
	
	protected Sin[] szomszedok;
	protected boolean alagut;
	protected Palya palya;
	
	Sin(Palya p, Sin sz0, Sin sz1){
		szomszedok = new Sin[3];
		szomszedok[0] = sz0;
		szomszedok[1] = sz1;
		alagut = false;
		palya = p;
	}
	
	public Sin elfogad(Jarmu j) {
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();
				//return palya.alagut(this).szomszedok[0]; ezt kiprobalni, mert a fordito nem ad hibat WTF
		
		if (j.getElozoPozicio() == szomszedok[0])
			return szomszedok[1];
		else
			return szomszedok[0];
	}
	
	public void setAlagut() {
		if (palya.getAlagutSzam() == 2) {
			if (alagut == false)
				//throw exception, nem lehet 2 nel tobb alagut
				return;
			else {
				alagut = false;
				palya.setAlagutSzam(-1);
				return;
			}
		}
		
		alagut = !alagut;
		if (alagut == false){
			palya.setAlagutSzam(-1);
			return;
		}
		
		palya.setAlagutSzam(1);
	}
	
	public boolean getAlagut() {
		return alagut;
	}
	
	Sin getFirstSzomszed() {
		return szomszedok[0];
	}
	
}