package projlab;

/**
 * Váltó osztály:
 * 
 * A Sin objektum leszármazottja. Abban különbözik, hogy 3 szomszédos sínje van,
 * nem 2. Azért felel, hogy ebbõl a háromból mindig összekössön kettõt, és
 * lehessen változtatni, hogy melyik kettõ az. Mivel a Sin gyereke, ezért felel
 * még a rajtalevõ Jarmu következõ pozíciójának megadásáért is. Nem lehet rá
 * alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felel azért, hogy az elágazódás Sin példányai közül kettõt összekössön. Mivel
 * a Sin gyereke, ezért felel még a rajtalevõ Jarmu következõ pozíciójának
 * megadásáért is. Nem lehet rá alagutat építeni.
 */
public class Valto extends Sin {

	/**
	 * Értékül adja s0-t a szomszedok 0. elemének, s1-et a szomszedok 1.
	 * elemének, és s2-t a szomszedok 3. elemének.
	 */
	public void setSzomszedok(Sin s0, Sin s1, Sin s2) {
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = s2;
	}

	/**
	 * Felülírjuk az õs (Sin) setAlagut-ját, hogy ne lehesssen ide alagutat
	 * építeni.
	 */
	public void setAlagut() {
	}

	/**
	 * A szomszedok tömb elemeit kicseréli, a 0. a második, az 1. a nulladik, és
	 * a 2. elem az elsõ helyre kerül. Mindig a 0. és az 1. elem van összekötve.
	 */
	public void atallit() {
		Sin temp = szomszedok[0];
		szomszedok[0] = szomszedok[1];
		szomszedok[1] = szomszedok[2];
		szomszedok[2] = temp;
	}

}