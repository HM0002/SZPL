package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allomas oszt�ly:
 * 
 * A PalyaElem objektumnak egy lesz�rmazottja, a grafikus interface-en a s�n
 * mellett lesz egy �llom�s is kirajzolva, de logikailag a PalyaElem-hez
 * tartozik. Az �llom�sok sz�nesek, (�s a kocsik is) �gy jel�lj�k, milyen utasok
 * k�v�nnak oda utazni (az azonos sz�n�ek).
 * 
 * Felel�ss�g:
 * 
 * Felel�s az utasok lesz�ll�t�s��rt, teh�t a kocsik ki�r�t�s��rt. Mivel a
 * PalyaElem gyereke, ez�rt felel m�g a rajtalev� Jarmu k�vetkez� poz�ci�j�nak
 * megad�s��rt is. Nem lehet r� alagutat �p�teni.
 */
public class Allomas extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/** szin: Meghat�rozza az �llom�s sz�nk�dj�t. */
	private int szin;

	/**
	 * aktiv: Azt hat�rozza meg, hogy az utasok lesz�llhatnak-e a rajta l�v�
	 * Jarmu p�ld�nyr�l, vagy sem. Akkor igaz, ha megengedj�k a lesz�ll�st,
	 * egy�bk�nt hamis.
	 */
	private boolean aktiv;

	/**
	 * varakozoUtas: Itt t�roljuk, hogy van-e v�rakoz� utas az �llom�son.
	 */
	private boolean varakozoUtas;

	/**
	 * Konstruktor, megh�vjuk az �s konstruktor�t, �s kieg�sz�tj�k a szin, �s az
	 * aktiv �rt�kad�s�val.
	 */
	Allomas(int i, boolean b, String id) {
		super(id);
		szin = i;
		aktiv = false;
		varakozoUtas = b;
		logger.log(Level.INFO, "param�terei: szin = " + i);
	}

	/**
	 * �res, hogy ne lehesssen ide alagutat �p�teni.
	 */
	public void setAlagut() {
	}

	/**
	 * Igazra �ll�tja az aktiv-ot, �s megadja a k�vetkez� PalyaElem p�ld�nyt,
	 * ahova a Mozdony ker�lni fog az �j id�pillanatban, �gy, hogy megn�zi a
	 * Mozdony elozoPozicio-j�t a getElozoPozicio met�dussal, �s a szomszedok
	 * t�mbb�l visszat�r az ett�l k�l�nb�z� �rt�kkel az els� k�t elem k�z�l.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + "), aktiv = true;");

		aktiv = true;

		if (m.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

	/**
	 * �sszehasonl�tja az �llom�s �s a kocsi sz�nk�dj�t, melyet a Kocsi getSzin
	 * met�dus�val k�r le. Ha nem egyezik meg, hamisra �ll�tja az aktiv-ot, ha a
	 * kocsi szin attrib�tuma nem 0, teh�t ha nem �res. Ha megegyezik �s az
	 * aktiv igaz, akkor lesz�ll�tja az utasokat, teh�t ki�r�ti a kocsit a Kocsi
	 * kiurit metodus�val (0-ra rakja a szin-t). Ezut�n megn�zz�k, hogy van e
	 * v�rakoz� utas az �llom�son, teh�t hogy a varakozoUtas �rt�ke igaz-e. Ha
	 * nem, nincs felsz�ll�s, viszont ha igen, megn�zz�k a kocsi sz�n�t a
	 * getSzin met�dussal. Ha 0, teh�t ha �res a kocsi, megn�zz�k, hogy alapb�l
	 * milyen sz�n� volt, a getEredetiSzin met�dussal. Ha az eredetiSzin
	 * megegyezik az allom�sunk szin-�vel, akkor felsz�ll�tjuk a v�rakoz�
	 * utasokat, teh�t megh�juk a kocsi setSzin metudus�t, �s hamisra �ll�tjuk a
	 * v�rakoz� utasokat. E mellett megadja a k�vetkez� PalyaElem p�ld�nyt,
	 * ahova a Kocsi ker�lni fog az �j id�pillanatban, �gy, hogy megn�zi a
	 * Mozdony elozoPozicio-j�t a getElozoPozicio met�dussal, �s a szomszedok
	 * t�mbb�l visszat�r az ett�l k�l�nb�z� �rt�kkel az els� k�t elem k�z�l.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		int kSzin = k.getSzin();

		if (kSzin != szin) {
			if (kSzin != 0)
				aktiv = false;
		} else if (aktiv == true)
			k.kiurit();

		if (varakozoUtas == true)
			if (k.getSzin() == 0 && k.getEredetiSzin() == szin) {
				k.setSzin();
				varakozoUtas = false;
			}

		if (k.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

	/**
	 * Megadja a k�vetkez� PalyaElem p�ld�nyt, ahova a Szeneskocsi ker�lni fog
	 * az �j id�pillanatban, �gy, hogy megn�zi a Szeneskocsi elozoPozicio-j�t a
	 * getElozoPozicio met�dussal, �s a szomszedok t�mbb�l visszat�r az ett�l
	 * k�l�nb�z� �rt�kkel az els� k�t elem k�z�l.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + "), aktiv = true;");

		if (sz.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

}