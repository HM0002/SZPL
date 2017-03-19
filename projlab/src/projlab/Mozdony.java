package projlab;

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

	/** Visszatér 0-val, ugyanis a mozdony mindig üres. */
	public int getSzin() {
		return 0;
	}

}