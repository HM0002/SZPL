package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Szeneskocsi osztály:
 * 
 * A Jarmu leszármazottja, nem lehet rajta utas. tároljuk a pozícióját és az
 * elõzõ pozicíóját, melyek PalyaElem típusúak. Amelyik PalyaElem-en Szeneskocsi
 * van (tehát a Szeneskocsi poziciója ez a PalyaElem), oda nem lehet alagutat
 * építeni.
 * 
 * Felelõsség:
 * 
 * Felel a Szeneskocsi mozgásáért.Az idõ múlásának függvényében meglátogatja a
 * pozíciójában tárolt PalyaElem példányt,melynek átadja önmagát.Ez visszatér az
 * új pozícióval,így mozog a Szeneskocsink.
 */
public class Szeneskocsi extends Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Konstruktor */
	Szeneskocsi(String id) {
		super(id);
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
	}
}
