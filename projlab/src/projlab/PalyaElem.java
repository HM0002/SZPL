package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PalyaElem oszt�ly:
 * 
 * A Sin, Valto, Allomas �s Keresztezodes �se. Ez egy abstract oszt�ly, ugyanis
 * p�lyaelemeket nem p�ld�nyos�tunk, csak s�neket, v�lt�kat, alagutakat vagy
 * keresztez�d�seket. Ezeken az p�lyaelemeken mozognak a vonataink. A Sin-ek
 * lehetnek alagutak, ezt egy boolean v�ltoz�ban t�roljuk. T�roljuk m�g a
 * szomsz�dos PalyaElem p�ld�nyokat, egy n�gy elem� t�mbben. A harmadik �s
 * negyedik elem null, ha csak 2 szomsz�d van. E mellett t�roljuk, hogy melyik
 * p�ly�hoz tartozik ez a PalyaElem, �s azt is, hogy jelenleg van e rajta j�rm�.
 * 
 * Felel�ss�g:
 * 
 * Ez az oszt�ly felel a 4 fajta p�lyaelem (s�n, v�lt�, alag�t, keresztez�d�s)
 * csoportos�t�s��rt. E mellett felel�ss�ge a vonatok mozg�s�hoz a helyes
 * k�vetkez� poz�ci� visszaad�sa(alg�t eset�n is) a szomsz�dok t�mb
 * seg�ts�g�vel.
 */
public abstract class PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok:

	/**
	 * szomszedok: A konstruktorban 4 elem�k�nt van l�trehozva. Tartalmazza a
	 * szomsz�dos PalyaElem p�ld�nyokat, az els� k�t elemben vannak az �rv�nyes
	 * szomsz�dok, teh�t azok vannak �sszek�tve, a harmadik �s negyedik elem
	 * �res (null). A Valto lesz�rmazott rak a harmadik elembe is egy p�ld�nyt,
	 * �s ahogyan v�ltogat a felhaszn�l�, �gy cser�l�dik ebben a t�mbben a
	 * sorrend. Keresztezodes eseten pedig mind a n�gy elembe ker�l egy p�ld�ny,
	 * az els� k�t elem van egym�ssal �sszek�tve, illetve a harmadik �s negyedik
	 * elem van egym�ssal �sszek�tve.
	 */
	protected PalyaElem[] szomszedok;

	/** id: Debuggol�shoz, hogy tudjuk kicsoda. */
	private String id;

	/** alagut: Igaz, ha alag�t van �p�tve ezen a p�ld�nyon, egy�bk�nt hamis. */
	protected boolean alagut;

	/** foglalt: T�roljuk, hogy h�ny Jarmu pozicio-ja ez a PalyaElem p�ld�ny. */
	protected int foglalt;

	/**
	 * palya: T�rolja, melyik Palya p�ld�nyhoz tartozik ez a PalyaElem p�ld�ny.
	 */
	protected Palya palya;

	/** Konstruktor */
	PalyaElem(String id) {
		szomszedok = new PalyaElem[4];
		alagut = false;
		palya = null;
		foglalt = 0;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult");
	}

	/** Visszat�r az id �rt�k�vel. */
	public String getID() {
		return id;
	}

	/**
	 * �rt�k�l adja e0-t a szomszedok 0. elem�nek, e1-et a szomszedok 1.
	 * elem�nek, e2-t a szomszedok 2. elem�nek �s e3-at a szomszedok 3.
	 * elem�nek.
	 */
	public void setSzomszedok(PalyaElem e0, PalyaElem e1, PalyaElem e2, PalyaElem e3) {
		if (e2 == null)
			logger.log(Level.INFO,
					this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", null, null) felh�vva");
		else if (e3 == null)
			logger.log(Level.INFO, this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", " + e2.getID()
					+ ", null) felh�vva");
		else
			logger.log(Level.INFO, this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", " + e2.getID()
					+ ", " + e3.getID() + ") felh�vva");
		szomszedok[0] = e0;
		szomszedok[1] = e1;
		szomszedok[2] = e2;
		szomszedok[3] = e3;
	}

	/**
	 * Abstract, a Sin, Valto, Alagut �s a Keresztezodes-ben implement�ljuk.
	 * Alag�t �p�t�s / lebont�s logik�j�t tartalmazza.
	 */
	public abstract void setAlagut();

	/**
	 * Abstract, a Sin, Valto, Alagut �s a Keresztezodes-ben implement�ljuk. A
	 * Mozdony k�vetkez� poz�ci�j�hoz tartoz� logika.
	 */
	public abstract PalyaElem elfogad(Mozdony k);

	/**
	 * Abstract, a Sin, Valto, Alagut �s a Keresztezodes-ben implement�ljuk. A
	 * Kocsi k�vetkez� poz�ci�j�hoz tartoz� logika.
	 */
	public abstract PalyaElem elfogad(Kocsi k);

	/**
	 * Abstract, a Sin, Valto, Alagut �s a Keresztezodes-ben implement�ljuk. A
	 * Szeneskocsi k�vetkez� poz�ci�j�hoz tartoz� logika.
	 */
	public abstract PalyaElem elfogad(Szeneskocsi k);

	/** �rt�k�l adjda p-t a palya-nak. */
	public void setPalya(Palya p) {
		palya = p;
	}

	/** Visszat�r az alagut �rt�k�vel. */
	public boolean getAlagut() {
		logger.log(Level.INFO, this.getID() + ".getAlagut(), visszaadott �rt�k: " + alagut);
		return alagut;
	}

	/**
	 * A foglalt v�ltoz� �rt�k�t adja vissza, jelezve, hogy a PalyaElem-en h�ny
	 * Jarmu tart�zkodik.
	 */
	public int getFoglalt() {
		logger.log(Level.INFO, this.getID() + ".getFoglalt(), visszaadott �rt�k: " + foglalt);
		return foglalt;
	}

	/**
	 * A foglalt v�ltoz� �rt�k�t n�veli, jelezve, hogy a PalyaElem plusz egy
	 * Jarmu pozicio-ja lett.
	 */
	public void setFoglalt() {
		logger.log(Level.INFO, this.getID() + ".setFoglalt()");
		foglalt++;
	}

	/**
	 * Cs�kkenti a foglalt �rt�k�t 1-el, majd n�veli a szomsz�dok 0. elem�nek
	 * foglalt �rt�k�t 1-el a setFoglalt met�dus seg�ts�g�vel. Ezut�n visszat�r
	 * a szomszedok t�mb 0. elem�vel.
	 */
	public PalyaElem getFirstSzomszed() {
		logger.log(Level.INFO, this.getID() + ".getFirstSzomszed(), visszaadott �rt�k: " + szomszedok[0].getID());
		foglalt--;
		szomszedok[0].setFoglalt();
		return szomszedok[0];
	}
}
