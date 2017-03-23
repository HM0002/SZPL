package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mozdony osztály:
 * 
 * A Jarmu leszármazottja, tároljuk a pozícióját és az elõzõ pozicíóját, melyek
 * Sin típusúak. Amelyik Sin-en Mozdony van (tehát a Mozdony poziciója ez a
 * Sin), oda nem lehet alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Felel a Mozdony mozgásáért.Az idõ múlásának függvényében meglátogatja a
 * pozíciójában tárolt Sin példányt,melynek átadja önmagát.Ez visszatér az új
 * pozícióval,így mozog a Mozdony-unk.
 */
public class Mozdony extends Jarmu {
	 private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	Mozdony(String id) {
		super(id);
		logger.log(Level.INFO, "Mozdony() paraméter nélküli konstruktor elindult.");
	}

	/** Visszatér 0-val, ugyanis a mozdony mindig üres. */
	public int getSzin() {
		logger.log(Level.INFO, "Mozdony.getSzin() ezen az elemen:"+this.getID());
		return 0;
	}

}