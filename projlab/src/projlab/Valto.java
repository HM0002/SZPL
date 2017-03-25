package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Váltó osztály:
 * 
 * A PalyaElem objektum leszármazottja. Abban különbözik, hogy három szomszédos
 * eleme van, nem kettõ. Ebbõl a háromból mindig összeköt kettõt, és lehet
 * változtatni, hogy melyik kettõ az. Mivel a PalyaElem gyereke, ezért felel még
 * a rajtalevõ Jarmu következõ pozíciójának megadásáért is. Nem lehet rá
 * alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felel azért, hogy az elágazódás PalyaElem példányai közül kettõt összekössön,
 * és ezáltal a helyes új pozicio-val térjen vissza. Mivel a PalyaElem gyereke,
 * ezért felel még a rajtalevõ Jarmu következõ pozíciójának megadásáért is. Nem
 * lehet rá alagutat építeni.
 */
public class Valto extends PalyaElem {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Valto(String id) {
		super(id);
	}

	/**
	 * Üres, hogy ne lehesssen ide alagutat építeni.
	 */
	public void setAlagut() {
	}

	/**
	 * Megadja a következõ PalyaElem példányt, ahova a Mozdony kerülni fog az új
	 * idõpillanatban, úgy, hogy megnézi a Mozdony elozoPozicio-ját a
	 * getElozoPozicio metódussal, és vizsgálja, hogy ez benne van-e a
	 * szomszédok tömb elsõ két elemében. Ha igen visszatér a másik elemmel a
	 * kettõ közül, viszont ha nem, az azt jelenti, hogy nem volt az elõzõ
	 * elemünk összekötve a váltóval, úgyhogy ütközést generálunk a setFoglalt
	 * metódussal.
	 */
	public PalyaElem elfogad(Mozdony m) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + m.getID() + ")");

		PalyaElem e = m.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
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
	 * Megadja a következõ PalyaElem példányt, ahova a Kocsi kerülni fog az új
	 * idõpillanatban, úgy, hogy megnézi a Mozdony elozoPozicio-ját a
	 * getElozoPozicio metódussal, és vizsgálja, hogy ez benne van-e a
	 * szomszédok tömb elsõ két elemében. Ha igen visszatér a másik elemmel a
	 * kettõ közül, viszont ha nem, az azt jelenti, hogy nem volt az elõzõ
	 * elemünk összekötve a váltóval, úgyhogy ütközést generálunk a setFoglalt
	 * metódussal.
	 */
	public PalyaElem elfogad(Kocsi k) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + k.getID() + ")");

		PalyaElem e = k.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
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
	 * Megadja a következõ PalyaElem példányt, ahova a Szeneskocsi kerülni fog
	 * az új idõpillanatban, úgy, hogy megnézi a Mozdony elozoPozicio-ját a
	 * getElozoPozicio metódussal, és vizsgálja, hogy ez benne van-e a
	 * szomszédok tömb elsõ két elemében. Ha igen visszatér a másik elemmel a
	 * kettõ közül, viszont ha nem, az azt jelenti, hogy nem volt az elõzõ
	 * elemünk összekötve a váltóval, úgyhogy ütközést generálunk a setFoglalt
	 * metódussal.
	 */
	public PalyaElem elfogad(Szeneskocsi sz) {
		logger.log(Level.INFO, this.getID() + ".elfogad(" + sz.getID() + ")");

		PalyaElem e = sz.getElozoPozicio();

		if (e == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else if (e == szomszedok[1]) {
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
			PalyaElem temp = szomszedok[0];
			szomszedok[0] = szomszedok[1];
			szomszedok[1] = szomszedok[2];
			szomszedok[2] = temp;
		}
	}
}