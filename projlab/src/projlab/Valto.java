package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Valto(String id) {
		super(id);
	}

	/**
	 * Értékül adja s0-t a szomszedok 0. elemének, s1-et a szomszedok 1.
	 * elemének, és s2-t a szomszedok 3. elemének.
	 */
	public void setSzomszedok(Sin s0, Sin s1, Sin s2) {
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = s2;
		logger.log(Level.INFO,
				this.getID() + ".setSzomszedok(" + s0.getID() + ", " + s1.getID() + ", " + s2.getID() + ") felhívva");
	}

	/**
	 * Felülírjuk az õs (Sin) setAlagut-ját, hogy ne lehesssen ide alagutat
	 * építeni.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Valto.setAlagut() ezen:" + this.getID());
	}

	/**
	 * Sin elfogad(Jarmu j): Megadja a következõ sín példányt, ahova a Jarmu
	 * kerülni fog az új idõpillanatban, úgy, hogy megnézi a Mozdony
	 * elozoPozicio-ját a getElozoPozicio metódussal, és vizsgálja, hogy ez
	 * benne van-e a szomszédok tömb elsõ két elemében. Ha igen visszatér a
	 * másik elemmel a kettõ közül, viszont ha nem, az azt jelenti, hogy nem
	 * volt az elõzõ sínünk összekötve a váltóval, úgyhogy ütközést generálunk a
	 * setFoglalt metódussal.
	 */
	public Sin elfogad(Jarmu j) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + j.getID() + ")");
		if (j.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (j.getElozoPozicio() == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else {
			setFoglalt();
			setFoglalt();
			return null;
		}
	}

	/**
	 * Ha nincs jármû a váltón, tehát ha a foglalt értéke 0, a szomszedok tömb
	 * elemeit kicseréli. A 0. a második, az 1. a nulladik, és a 2. elem az elsõ
	 * helyre kerül. Mindig a 0. és az 1. elem van összekötve.
	 */
	public void atallit() {
		logger.log(Level.INFO, this.getID() + ".atallit()");
		if (foglalt == 0) {
			Sin temp = szomszedok[0];
			szomszedok[0] = szomszedok[1];
			szomszedok[1] = szomszedok[2];
			szomszedok[2] = temp;
		}
	}
}