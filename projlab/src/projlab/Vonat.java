package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Vonat osztály:
 * 
 * Itt tároljuk az egy Vonat-hoz tartozó Mozdony és Kocsi példányainkat.
 * 
 * Felelõsség:
 *
 * Felelõssége, hogy egy Mozdony és több Kocsi példányt tároljon egy Jarmu
 * típusú listában, rendezve, melyet mostantól vonatnak hívhatunk.
 */
public class Vonat {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok

	/**
	 * jarmuvek: A 0. elemében egy Mozdony példányt, a többi elemében Kocsi
	 * példányokat tárolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** Debuggoláshoz, hogy tudjuk kicsoda. */
	private String id;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j, String id) {
		jarmuvek = j;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult");
	}

	/**
	 * Visszatér a jarmuvek listával, ha az nem üres. Ha igen, akkor null-al.
	 */
	public ArrayList<Jarmu> getJarmuvek() {
		logger.log(Level.INFO, this.getID() + ".getJarmuvek()");
		if (jarmuvek.isEmpty())
			// throw exception
			return null;
		else
			return jarmuvek;
	}

	/** Visszatér az id értékével. */
	public String getID() {
		return id;
	}

}