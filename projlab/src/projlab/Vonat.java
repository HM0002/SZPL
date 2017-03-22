package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Vonat oszt�ly:
 * 
 * Itt t�roljuk az egy Vonat-hoz tartoz� Mozdony �s Kocsi p�ld�nyainkat.
 * 
 * Felel�ss�g:
 *
 * Felel�ss�ge, hogy egy Mozdony �s t�bb Kocsi p�ld�nyt t�roljon egy Jarmu
 * t�pus� list�ban, rendezve, melyet mostant�l vonatnak h�vhatunk.
 */
public class Vonat {
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attrib�tumok

	/**
	 * jarmuvek: A 0. elem�ben egy Mozdony p�ld�nyt, a t�bbi elem�ben Kocsi
	 * p�ld�nyokat t�rolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j) {
		logger.log(Level.INFO, "Vonat(ArrayList<Jarmu>) param�ter� konstruktor elindult.");
		jarmuvek = j;
	}

	/**
	 * Visszat�r a jarmuvek list�val, ha az nem �res. Ha igen, akkor null-al.
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