package projlab;

import java.util.ArrayList;

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

	// Attrib�tumok

	/**
	 * jarmuvek: A 0. elem�ben egy Mozdony p�ld�nyt, a t�bbi elem�ben Kocsi
	 * p�ld�nyokat t�rolunk.
	 */
	private ArrayList<Jarmu> jarmuvek;

	/** Konstruktor */
	Vonat(ArrayList<Jarmu> j) {
		jarmuvek = j;
	}

	/**
	 * Visszat�r a jarmuvek list�val, ha az nem �res. Ha igen, akkor null-al.
	 */
	public ArrayList<Jarmu> getJarmuvek() {
		if (jarmuvek.isEmpty())
			// throw exception
			return null;
		else
			return jarmuvek;
	}

}