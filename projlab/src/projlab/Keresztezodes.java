package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kereszteztodes osztály:
 * 
 * A PalyaElem objektum leszármazottja. Abban különbözik, hogy négy szomszédos
 * eleme van, nem kettõ. Ebbõl a négybõl mindig összeköti a megfelelõ kettõt.
 * 
 * Felelõsség:
 * 
 * Felel azért, hogy a keresztezõdés PalyaElem példányai közül páronként kettõt
 * összekössön, és ezáltal a helyes új pozicio-val térjen vissza. Mivel a
 * PalyaElem gyereke, ezért felel még a rajtalevõ Jarmu következõ pozíciójának
 * megadásáért is. Nem lehet rá alagutat építeni.
 */
public class Keresztezodes extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Keresztezodes(String id) {
		super(id);
	}

	/**
	 * Megadja a következõ PalyaElem példányt, ahova a Szeneskocsi kerülni fog
	 * az új idõpillanatban, úgy, hogy megnézi a Szeneskocsi elozoPozicio-ját a
	 * getElozoPozicio metódussal, és a szomszedok tömbbõl visszatér az elsõ
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt és egyébként a második elemmel.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + "), aktiv = true;");

		PalyaElem e = m.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

	/**
	 * Megadja a következõ PalyaElem példányt, ahova a Szeneskocsi kerülni fog
	 * az új idõpillanatban, úgy, hogy megnézi a Szeneskocsi elozoPozicio-ját a
	 * getElozoPozicio metódussal, és a szomszedok tömbbõl visszatér az elsõ
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt és egyébként a második elemmel.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + "), aktiv = true;");

		PalyaElem e = k.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

	/**
	 * Megadja a következõ PalyaElem példányt, ahova a Szeneskocsi kerülni fog
	 * az új idõpillanatban, úgy, hogy megnézi a Szeneskocsi elozoPozicio-ját a
	 * getElozoPozicio metódussal, és a szomszedok tömbbõl visszatér az elsõ
	 * elemmel, ha 0 volt, a nulladik elemmel ha 1 volt, illetve a harmadik
	 * elemmel, ha 2 volt és egyébként a második elemmel.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + "), aktiv = true;");

		PalyaElem e = sz.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		} else if (e == szomszedok[2]) {
			foglalt--;
			szomszedok[3].setFoglalt();
			return szomszedok[3];
		} else {
			foglalt--;
			szomszedok[2].setFoglalt();
			return szomszedok[2];
		}
	}

}
