package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kocsi osztály:
 * 
 * A Jarmu leszármazottja, tároljuk a pozícióját és az elõzõ pozicíóját, melyek
 * Sin típusúak. Amelyik Sin-en Kocsi van (tehát a Kocsi poziciója ez a Sin),
 * oda nem lehet alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felel a Kocsi mozgásáért.Az idõ múlásának függvényében meglátogatja a
 * pozíciójában tárolt Sin példányt,melynek átadja önmagát.Ez visszatér az új
 * pozícióval,így mozog a Kocsi-nk. E mellett felel az utasok szállításáért,és
 * az utasok leszállításáért.Van színe, ugyanúgy, mint az állomásoknak. A szín
 * jelzi, hogy utasok vannak a Kocsi-ban, és hogy melyik állomásra szeretnének
 * menni. Az üres kocsi szin értéke 0. Attribútumok int szin: meghatározza a
 * kocsi színkódját, a 0 felel az üresért,ami a szürke
 */
public class Kocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Main.class.getName());

	// Attribútumok:

	/**
	 * szin: Meghatározza a kocsi színkódját, a 0 felel az üresért, ami a
	 * szürke.
	 */
	private int szin;

	/**
	 * Konstruktor, meghívjuk az õs konstruktorát, és kiegészítjük a szin
	 * értékadásával.
	 */
	Kocsi(int i, String id) {
		super(id);
		logger.log(Level.INFO, "Kocsi(int) paraméterrû konstruktor elindult, paraméter: " + i);
		szin = i;
	}

	/**
	 * Beállítja a szin értékét 0-ra, ezáltal a Kocsi üresnek van tekintve, és a
	 * felhasználó informálva van errõl azáltal, hogy a kocsi szürke.
	 */
	public void kiurit() {
		logger.log(Level.INFO, "Kocsi.kiurit() ezen az elemen:"+this.getID());
		szin = 0;
	}

	/** Visszatér a szin attribútum értékével. */
	public int getSzin() {
		logger.log(Level.INFO, "Kocsi.getSzin() ezen az elemen:"+this.getID());
		return szin;
	}

}