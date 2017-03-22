package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kocsi oszt�ly:
 * 
 * A Jarmu lesz�rmazottja, t�roljuk a poz�ci�j�t �s az el�z� pozic��j�t, melyek
 * Sin t�pus�ak. Amelyik Sin-en Kocsi van (teh�t a Kocsi pozici�ja ez a Sin),
 * oda nem lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel a Kocsi mozg�s��rt.Az id� m�l�s�nak f�ggv�ny�ben megl�togatja a
 * poz�ci�j�ban t�rolt Sin p�ld�nyt,melynek �tadja �nmag�t.Ez visszat�r az �j
 * poz�ci�val,�gy mozog a Kocsi-nk. E mellett felel az utasok sz�ll�t�s��rt,�s
 * az utasok lesz�ll�t�s��rt.Van sz�ne, ugyan�gy, mint az �llom�soknak. A sz�n
 * jelzi, hogy utasok vannak a Kocsi-ban, �s hogy melyik �llom�sra szeretn�nek
 * menni. Az �res kocsi szin �rt�ke 0. Attrib�tumok int szin: meghat�rozza a
 * kocsi sz�nk�dj�t, a 0 felel az �res�rt,ami a sz�rke
 */
public class Kocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attrib�tumok:

	/**
	 * szin: Meghat�rozza a kocsi sz�nk�dj�t, a 0 felel az �res�rt, ami a
	 * sz�rke.
	 */
	private int szin;

	/**
	 * Konstruktor, megh�vjuk az �s konstruktor�t, �s kieg�sz�tj�k a szin
	 * �rt�kad�s�val.
	 */
	Kocsi(int i, String id) {
		super(id);
		logger.log(Level.INFO, "Kocsi(int) param�terr� konstruktor elindult, param�ter: " + i);
		szin = i;
	}

	/**
	 * Be�ll�tja a szin �rt�k�t 0-ra, ez�ltal a Kocsi �resnek van tekintve, �s a
	 * felhaszn�l� inform�lva van err�l az�ltal, hogy a kocsi sz�rke.
	 */
	public void kiurit() {
		logger.log(Level.INFO, "Kocsi.kiurit() ezen az elemen:"+this.getID());
		szin = 0;
	}

	/** Visszat�r a szin attrib�tum �rt�k�vel. */
	public int getSzin() {
		logger.log(Level.INFO, "Kocsi.getSzin() ezen az elemen:"+this.getID());
		return szin;
	}

}