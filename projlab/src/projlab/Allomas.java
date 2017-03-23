package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allomas osztály:
 * 
 * A Sin objektumnak egy leszármazottja, a grafikus interface-en a sín mellett
 * lesz egy állomás is kirajzolva, de logikailag a Sin-hez tartozik. Az
 * állomások színesek, (és a kocsik is) így jelöljük, milyen utasok kívánnak oda
 * utazni (az azonos színûek).
 * 
 * Felelõsség:
 * 
 * Felelõs az utasok leszállításáért, tehát a kocsik kiürítéséért. Mivel a Sin
 * gyereke, ezért felel még a rajtalevõ Jarmu következõ pozíciójának megadásáért
 * is. Nem lehet rá alagutat építeni.
 */
public class Allomas extends Sin {
	 private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/** szin: Meghatározza az állomás színkódját. */
	private int szin;

	/**
	 * aktiv: Azt határozza meg, hogy az utasok leszállhatnak-e a rajta lévõ
	 * Jarmu példányról, vagy sem. Akkor igaz, ha megengedjük a leszállást,
	 * egyébként hamis.
	 */
	private boolean aktiv;

	/**
	 * Konstruktor, meghívjuk az õs konstruktorát, és kiegészítjük a szin, és az
	 * aktiv értékadásával.
	 */
	Allomas(int i, String id) {
		super(id);
		logger.log(Level.INFO, "Allomás konstruktor, paraméter: " + i);
		szin = i;
		aktiv = false;
	}

	/**
	 * Felülírjuk az õs (Sin) setAlagut-ját, hogy ne lehesssen ide alagutat
	 * építeni.
	 */
	public void setAlagut() {
		logger.log(Level.INFO, "Allomas.setAlagut() ezen a elemen: "+this.getID());
	}

	/**
	 * Sin elfogad(Mozdony m): Igazra állítja az aktiv-ot, és megadja a
	 * következõ sín példányt, ahova a Jarmu kerülni fog az új idõpillanatban,
	 * úgy, hogy megnézi a Mozdony elozoPozicio-ját a getElozoPozicio
	 * metódussal, és a szomszedok tömbbõl visszatér az ettõl különbözõ értékkel
	 * az elsõ két elem közül. Mivel állmoásra nem épülhet alagút, nincs más
	 * feltételnézés. Ezután csökkentjük a a foglalt értékét, és növeljük az új
	 * pozicio-ban lévõ Sin foglalt értékét, a setFoglalt metódussal.
	 */
	public Sin elfogad(Mozdony m) {
		logger.log(Level.INFO, "Allomas.elfogad(Mozdony), paraméter: " + m.toString());
		aktiv = true;

		if (m.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

	/**
	 * Sin elfogad(Kocsi k): Összehasonlítja az állomás és a kocsi színkódját,
	 * melyet a Kocsi getSzin metódusával kér le. Ha nem egyezik meg, hamisra
	 * állítja az aktiv-ot, ha a kocsi szin attribútuma nem 0, tehát ha nem
	 * üres. Ha megegyezik és az aktiv igaz, akkor leszállítja az utasokat,
	 * tehát kiüríti a kocsit a Kocsi kiurit metodusával (0-ra rakja a szin-t).
	 * mellett megadja a következõ sín példányt, ahova a Jarmu kerülni fog az új
	 * idõpillanatban, úgy, hogy megnézi a Mozdony elozoPozicio-ját a
	 * getElozoPozicio metódussal, és a szomszedok tömbbõl visszatér az ettõl
	 * különbözõ értékkel az elsõ két elem közül. Mivel állmoásra nem épülhet
	 * alagút, nincs más feltételnézés. Ezután csökkentjük a a foglalt értékét,
	 * és növeljük az új pozicio-ban lévõ Sin foglalt értékét, a setFoglalt
	 * metódussal.
	 */
	public Sin elfogad(Kocsi k) {
		logger.log(Level.INFO, "Allomas.elfogad(Kocsi), paraméter: " + k.toString());
		int kSzin = k.getSzin();
		if (kSzin != szin) {
			if (kSzin != 0)
				aktiv = false;
		} else if (aktiv == true)
			k.kiurit();

		if (k.getElozoPozicio() == szomszedok[0]) {
			foglalt--;
			szomszedok[1].setFoglalt();
			return szomszedok[1];
		} else {
			foglalt--;
			szomszedok[0].setFoglalt();
			return szomszedok[0];
		}
	}

}