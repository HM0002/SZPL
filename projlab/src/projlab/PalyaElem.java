package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PalyaElem osztály:
 * 
 * A Sin, Valto, Allomas és Keresztezodes õse. Ez egy abstract osztály, ugyanis
 * pályaelemeket nem példányosítunk, csak síneket, váltókat, alagutakat vagy
 * keresztezõdéseket. Ezeken az pályaelemeken mozognak a vonataink. A Sin-ek
 * lehetnek alagutak, ezt egy boolean változóban tároljuk. Tároljuk még a
 * szomszédos PalyaElem példányokat, egy négy elemû tömbben. A harmadik és
 * negyedik elem null, ha csak 2 szomszéd van. E mellett tároljuk, hogy melyik
 * pályához tartozik ez a PalyaElem, és azt is, hogy jelenleg van e rajta jármû.
 * 
 * Felelõsség:
 * 
 * Ez az osztály felel a 4 fajta pályaelem (sín, váltó, alagút, keresztezõdés)
 * csoportosításáért. E mellett felelõssége a vonatok mozgásához a helyes
 * következõ pozíció visszaadása(algút esetén is) a szomszédok tömb
 * segítségével.
 */
public abstract class PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/**
	 * szomszedok: A konstruktorban 4 elemûként van létrehozva. Tartalmazza a
	 * szomszédos PalyaElem példányokat, az elsõ két elemben vannak az érvényes
	 * szomszédok, tehát azok vannak összekötve, a harmadik és negyedik elem
	 * üres (null). A Valto leszármazott rak a harmadik elembe is egy példányt,
	 * és ahogyan váltogat a felhasználó, úgy cserélõdik ebben a tömbben a
	 * sorrend. Keresztezodes eseten pedig mind a négy elembe kerül egy példány,
	 * az elsõ két elem van egymással összekötve, illetve a harmadik és negyedik
	 * elem van egymással összekötve.
	 */
	protected PalyaElem[] szomszedok;

	/** id: Debuggoláshoz, hogy tudjuk kicsoda. */
	private String id;

	/** alagut: Igaz, ha alagút van építve ezen a példányon, egyébként hamis. */
	protected boolean alagut;

	/** foglalt: Tároljuk, hogy hány Jarmu pozicio-ja ez a PalyaElem példány. */
	protected int foglalt;

	/**
	 * palya: Tárolja, melyik Palya példányhoz tartozik ez a PalyaElem példány.
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

	/** Visszatér az id értékével. */
	public String getID() {
		return id;
	}

	/**
	 * Értékül adja e0-t a szomszedok 0. elemének, e1-et a szomszedok 1.
	 * elemének, e2-t a szomszedok 2. elemének és e3-at a szomszedok 3.
	 * elemének.
	 */
	public void setSzomszedok(PalyaElem e0, PalyaElem e1, PalyaElem e2, PalyaElem e3) {
		if (e2 == null)
			logger.log(Level.INFO,
					this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", null, null) felhívva");
		else if (e3 == null)
			logger.log(Level.INFO, this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", " + e2.getID()
					+ ", null) felhívva");
		else
			logger.log(Level.INFO, this.getID() + ".setSzomszedok(" + e0.getID() + ", " + e1.getID() + ", " + e2.getID()
					+ ", " + e3.getID() + ") felhívva");
		szomszedok[0] = e0;
		szomszedok[1] = e1;
		szomszedok[2] = e2;
		szomszedok[3] = e3;
	}

	/**
	 * Abstract, a Sin, Valto, Alagut és a Keresztezodes-ben implementáljuk.
	 * Alagút építés / lebontás logikáját tartalmazza.
	 */
	public abstract void setAlagut();

	/**
	 * Abstract, a Sin, Valto, Alagut és a Keresztezodes-ben implementáljuk. A
	 * Mozdony következõ pozíciójához tartozó logika.
	 */
	public abstract PalyaElem elfogad(Mozdony k);

	/**
	 * Abstract, a Sin, Valto, Alagut és a Keresztezodes-ben implementáljuk. A
	 * Kocsi következõ pozíciójához tartozó logika.
	 */
	public abstract PalyaElem elfogad(Kocsi k);

	/**
	 * Abstract, a Sin, Valto, Alagut és a Keresztezodes-ben implementáljuk. A
	 * Szeneskocsi következõ pozíciójához tartozó logika.
	 */
	public abstract PalyaElem elfogad(Szeneskocsi k);

	/** Értékül adjda p-t a palya-nak. */
	public void setPalya(Palya p) {
		palya = p;
	}

	/** Visszatér az alagut értékével. */
	public boolean getAlagut() {
		logger.log(Level.INFO, this.getID() + ".getAlagut(), visszaadott érték: " + alagut);
		return alagut;
	}

	/**
	 * A foglalt változó értékét adja vissza, jelezve, hogy a PalyaElem-en hány
	 * Jarmu tartózkodik.
	 */
	public int getFoglalt() {
		logger.log(Level.INFO, this.getID() + ".getFoglalt(), visszaadott érték: " + foglalt);
		return foglalt;
	}

	/**
	 * A foglalt változó értékét növeli, jelezve, hogy a PalyaElem plusz egy
	 * Jarmu pozicio-ja lett.
	 */
	public void setFoglalt() {
		logger.log(Level.INFO, this.getID() + ".setFoglalt()");
		foglalt++;
	}

	/**
	 * Csökkenti a foglalt értékét 1-el, majd növeli a szomszédok 0. elemének
	 * foglalt értékét 1-el a setFoglalt metódus segítségével. Ezután visszatér
	 * a szomszedok tömb 0. elemével.
	 */
	public PalyaElem getFirstSzomszed() {
		logger.log(Level.INFO, this.getID() + ".getFirstSzomszed(), visszaadott érték: " + szomszedok[0].getID());
		foglalt--;
		szomszedok[0].setFoglalt();
		return szomszedok[0];
	}
}
