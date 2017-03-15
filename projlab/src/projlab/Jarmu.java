package projlab;

public class Jarmu {
	
	private Sin pozicio;
	private Sin elozoPozicio;
	
	Jarmu() {
		pozicio = null;
		elozoPozicio = null;
	}
	
	public void tick() {
		latogat();
	}
	
	public void latogat() {
		elozoPozicio = pozicio;
		pozicio = pozicio.elfogad(this);
	}
	
	public Sin getPozicio() {
		return pozicio;
	}
	
	public Sin getElozoPozicio() {
		return elozoPozicio;
	}
	
	public void setPozicio(Sin s) {
		elozoPozicio = pozicio;
		pozicio = s;
	}
	
	public int getSzin() {
		return 0;
	}
	
}