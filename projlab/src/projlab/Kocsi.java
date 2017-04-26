package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kocsi osztály:
 * 
 * A Jarmu leszármazottja, tároljuk a pozícióját és az elõzõ pozicíóját, melyek
 * PalyaElem típusúak. Amelyik PalyaElem-en Kocsi van (tehát a Kocsi poziciója
 * ez a PalyaElem), oda nem lehet alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felel a Kocsi mozgásáért.Az idõ múlásának függvényében meglátogatja a
 * pozíciójában tárolt PalyaElem példányt,melynek átadja önmagát.Ez visszatér az
 * új pozícióval,így mozog a Kocsi. E mellett felel az utasok szállításáért.Van
 * színe, ugyanúgy, mint az állomásoknak. A szín jelzi, hogy utasok vannak a
 * Kocsi-ban, és hogy melyik állomásra szeretnének menni. Az üres kocsi szin
 * értéke 0,ami a szürke.
 */
public class Kocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/**
	 * szin: Tárolja a kocsi aktuális színkódját, a 0 felel az üresért, ami a
	 * szürke.
	 */
	private int szin;

	/**
	 * eredetiSzin: Tárolja, milyen színû volt a kocsi az inicializálásakor.
	 */
	private int eredetiSzin;

	/**
	 * Konstruktor, meghívjuk az õs konstruktorát, és kiegészítjük a szin
	 * értékadásával.
	 */
	Kocsi(int i, String id) {
		super(id);
		logger.log(Level.INFO, "paraméterei: szin = " + i);
		szin = i;
		eredetiSzin = i;
	}

	/**
	 * Beállítja a szin értékét 0-ra, ezáltal a Kocsi üresnek van tekintve, és a
	 * felhasználó informálva van errõl azáltal, hogy a kocsi szürke.
	 */
	public void kiurit() {
		logger.log(Level.INFO, this.getID() + ".kiurit()");
		szin = 0;
	}

	/** Visszatér a szin attribútum értékével. */
	public int getSzin() {
		logger.log(Level.INFO, this.getID() + ".getSzin(), viszaadott érték: " + szin);
		return szin;
	}

	/** Értékül adjuk az eredetiSzin-t a szin-nek. */
	public void setSzin() {
		logger.log(Level.INFO, this.getID() + ".setSzin()");
		szin = eredetiSzin;
	}

	/** Visszatér a eredetiSzin attribútum értékével. */
	public int getEredetiSzin() {
		logger.log(Level.INFO, this.getID() + ".getEredetiSzin(), viszaadott érték: " + eredetiSzin);
		return eredetiSzin;
	}

	/**
	 * A PalyaElem típusú pozicio elfogad metódusát hívja meg, saját magát
	 * paraméterül átadva, hogy az elfogad metódus visszatérhessen új pozícióval
	 * (PalyaElem példánnyal).
	 */
	protected void latogat() {
		logger.log(Level.INFO, this.getID() + ".latogat(" + pozicio.getID() + ")");

		PalyaElem temp = pozicio;
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
		testValto();
	}

}