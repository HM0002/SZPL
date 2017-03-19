package projlab;

import java.util.ArrayList;

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

	// Attribútumok

	/**
	 * jarmuvek: A 0. elemében egy Mozdony példányt, a többi elemében Kocsi
	 * példányokat tárolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j) {
		jarmuvek = j;
	}

	/**
	 * Visszatér a jarmuvek listával, ha az nem üres. Ha igen, akkor null-al.
	 */
	public ArrayList<Jarmu> getJarmuvek() {
		if (jarmuvek.isEmpty())
			// throw exception
			return null;
		else
			return jarmuvek;
	}

}