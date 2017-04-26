package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kocsi oszt�ly:
 * 
 * A Jarmu lesz�rmazottja, t�roljuk a poz�ci�j�t �s az el�z� pozic��j�t, melyek
 * PalyaElem t�pus�ak. Amelyik PalyaElem-en Kocsi van (teh�t a Kocsi pozici�ja
 * ez a PalyaElem), oda nem lehet alagutat �p�teni.
 * 
 * Felel�ss�g:
 * 
 * Felel a Kocsi mozg�s��rt.Az id� m�l�s�nak f�ggv�ny�ben megl�togatja a
 * poz�ci�j�ban t�rolt PalyaElem p�ld�nyt,melynek �tadja �nmag�t.Ez visszat�r az
 * �j poz�ci�val,�gy mozog a Kocsi. E mellett felel az utasok sz�ll�t�s��rt.Van
 * sz�ne, ugyan�gy, mint az �llom�soknak. A sz�n jelzi, hogy utasok vannak a
 * Kocsi-ban, �s hogy melyik �llom�sra szeretn�nek menni. Az �res kocsi szin
 * �rt�ke 0,ami a sz�rke.
 */
public class Kocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/**
	 * szin: T�rolja a kocsi aktu�lis sz�nk�dj�t, a 0 felel az �res�rt, ami a
	 * sz�rke.
	 */
	private int szin;

	/**
	 * eredetiSzin: T�rolja, milyen sz�n� volt a kocsi az inicializ�l�sakor.
	 */
	private int eredetiSzin;

	/**
	 * Konstruktor, megh�vjuk az �s konstruktor�t, �s kieg�sz�tj�k a szin
	 * �rt�kad�s�val.
	 */
	Kocsi(int i, String id) {
		super(id);
		logger.log(Level.INFO, "param�terei: szin = " + i);
		szin = i;
		eredetiSzin = i;
	}

	/**
	 * Be�ll�tja a szin �rt�k�t 0-ra, ez�ltal a Kocsi �resnek van tekintve, �s a
	 * felhaszn�l� inform�lva van err�l az�ltal, hogy a kocsi sz�rke.
	 */
	public void kiurit() {
		logger.log(Level.INFO, this.getID() + ".kiurit()");
		szin = 0;
	}

	/** Visszat�r a szin attrib�tum �rt�k�vel. */
	public int getSzin() {
		logger.log(Level.INFO, this.getID() + ".getSzin(), viszaadott �rt�k: " + szin);
		return szin;
	}

	/** �rt�k�l adjuk az eredetiSzin-t a szin-nek. */
	public void setSzin() {
		logger.log(Level.INFO, this.getID() + ".setSzin()");
		szin = eredetiSzin;
	}

	/** Visszat�r a eredetiSzin attrib�tum �rt�k�vel. */
	public int getEredetiSzin() {
		logger.log(Level.INFO, this.getID() + ".getEredetiSzin(), viszaadott �rt�k: " + eredetiSzin);
		return eredetiSzin;
	}

	/**
	 * A PalyaElem t�pus� pozicio elfogad met�dus�t h�vja meg, saj�t mag�t
	 * param�ter�l �tadva, hogy az elfogad met�dus visszat�rhessen �j poz�ci�val
	 * (PalyaElem p�ld�nnyal).
	 */
	protected void latogat() {
		logger.log(Level.INFO, this.getID() + ".latogat(" + pozicio.getID() + ")");

		PalyaElem temp = pozicio;
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
		testValto();
	}

}