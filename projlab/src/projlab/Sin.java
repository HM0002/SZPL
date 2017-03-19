package projlab;

public class Sin {
	
	protected Sin[] szomszedok;
	protected boolean alagut;
	protected int foglalt;
	protected Palya palya;
	
	/**Konstruktor*/
	Sin() {
		szomszedok = new Sin[3];
		alagut = false;
		foglalt = 0;
		palya = null;
	}
	
	public void setPalya(Palya p) {
		palya = p;
	}
	
	public void setSzomszedok(Sin sz0, Sin sz1) {
		szomszedok[0] = sz0;
		szomszedok[1] = sz1;
		szomszedok[2] = null;
	}
	
	public Sin elfogad(Jarmu j) {
		if (alagut == true)
			if (palya.getAlagutSzam() == 2)
				return palya.alagut(this).getFirstSzomszed();
				//return palya.alagut(this).szomszedok[0]; ezt kiprobalni, mert a fordito nem ad hibat WTF
		
		if (j.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		}
		else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}
	
	public void setAlagut() {
		if (foglalt > 0) {
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
	}
	
	public boolean getAlagut() {
		return alagut;
	}
	
	public int getFoglalt() {
		return foglalt;
	}
	
	public void setFoglalt() {
		foglalt++;
	}
	
	public Sin getFirstSzomszed() {
		foglalt--;
		szomszedok[0].setFoglalt();
		return szomszedok[0];
	}
	
}