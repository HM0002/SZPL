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
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	Valto(String id) {
		super(id);
		logger.log(Level.INFO, "Valto() paraméter nélküli konstruktor elindult.");
	}

	/**
	 * Értékül adja s0-t a szomszedok 0. elemének, s1-et a szomszedok 1.
	 * elemének, és s2-t a szomszedok 3. elemének.
	 */
	public void setSzomszedok(Sin s0, Sin s1, Sin s2) {
		logger.log(Level.INFO, "Valto.setSzomszedok(Sin, Sin, Sin), rajta: "+this.getID()+", szomszédok:"+ s0.getID()+","+s1.getID()+","+s2.getID());
		szomszedok[0] = s0;
		szomszedok[1] = s1;
		szomszedok[2] = s2;
	}

	/**
	 * Felülírjuk az õs (Sin) setAlagut-ját, hogy ne lehesssen ide alagutat
	 * építeni.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Valto.setAlagut() ezen:"+this.getID());
	}

	/**
	 * A szomszedok tömb elemeit kicseréli, a 0. a második, az 1. a nulladik, és
	 * a 2. elem az elsõ helyre kerül. Mindig a 0. és az 1. elem van összekötve.
	 */
	public void atallit() {
		logger.log(Level.INFO, "Valto.atallit() ezen."+this.getID());
		Sin temp = szomszedok[0];
		szomszedok[0] = szomszedok[1];
		szomszedok[1] = szomszedok[2];
		szomszedok[2] = temp;
	}
}