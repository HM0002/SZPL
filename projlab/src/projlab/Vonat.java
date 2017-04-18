package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Vonat oszt�ly:
 * 
 * Itt t�roljuk az egy Vonat-hoz tartoz� Mozdony, Kocsi �s Szeneskocsi
 * p�ld�nyainkat.
 * 
 * Felel�ss�g:
 *
 * Felel�ss�ge, hogy egy Mozdony �s t�bb Kocsi illetve Szeneskocsi p�ld�nyt
 * t�roljon egy Jarmu t�pus� list�ban, rendezve, melyet mostant�l vonatnak
 * h�vhatunk.
 */
public class Vonat {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attrib�tumok

	/**
	 * jarmuvek: A 0. elem�ben egy Mozdony p�ld�nyt, a t�bbi elem�ben Kocsi �s
	 * Szeneskocsi p�ld�nyokat t�rolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** A vonat sz�veges azons�t�ja. */
	private String id;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j, String id) {
		jarmuvek = j;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult");
	}

	/**
	 * Visszat�r a jarmuvek list�val, ha az nem �res. Ha igen, akkor null-al.
	 */
	public ArrayList<Jarmu> getJarmuvek() {
		logger.log(Level.INFO, this.getID() + ".getJarmuvek()");
		if (jarmuvek.isEmpty())
			// throw exception
			return null;
		else
			return jarmuvek;
	}

	/** Visszat�r az id �rt�k�vel. */
	public String getID() {
		return id;
	}

}