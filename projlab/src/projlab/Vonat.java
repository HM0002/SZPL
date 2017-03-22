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
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attribútumok

	/**
	 * jarmuvek: A 0. elemében egy Mozdony példányt, a többi elemében Kocsi
	 * példányokat tárolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j) {
		logger.log(Level.INFO, "Vonat(ArrayList<Jarmu>) paraméterû konstruktor elindult.");
		jarmuvek = j;
	}

	/**
	 * Visszatér a jarmuvek listával, ha az nem üres. Ha igen, akkor null-al.
	 */
	public ArrayList<Jarmu> getJarmuvek() {
		logger.log(Level.INFO, "Vonat.getJarmuvek()");
		if (jarmuvek.isEmpty())
			// throw exception
			return null;
		else
			return jarmuvek;
	}

}