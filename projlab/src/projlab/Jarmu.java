package projlab;

/**
 * Jarmu osztály:
 * 
 * A Mozdony és a Kocsi objektumok õse. Ez egy abstract osztály, ugyanis
 * jármûveket nem példányosítunk, csak kocsikat vagy mozdonyokat. Tároljuk
 * ezeknek a pozíciójukat és az elõzõ pozicíójukat, melyek Sin típusúak. Amelyik
 * Sin-en Jarmu van (tehát a Jarmu poziciója ez a Sin), oda nem lehet alagutat
 * építeni.
 * 
 * Felelõsség:
 * 
 * Ez az objektum felel a mozgó mozdonyok és kocsik csoportosításáért. Az idõ
 * múlásának függvényében (tick) meglátogatja a pozíciójában tárolt Sin
 * példányt, melynek átadja önmagát. Ez visszatér az új pozícióval, így mozog a
 * Jarmu-vünk.
 */
public abstract class Jarmu {

	// Attribútumok:

	/**
	 * pozicio: Itt tároljuk, hogy a jármû melyik Sin példányon helyezkedik el.
	 */
	private Sin pozicio;

	/**
	 * elozoPozicio: Itt tároljuk, az egy ütemmel (tick) korábbi Sin példányt,
	 * amin elhelyezkedett.
	 */
	private Sin elozoPozicio;

	/** Konstruktor */
	Jarmu() {
		pozicio = null;
		elozoPozicio = null;
	}

	/** Meghívja a latogat metódust, ezáltal jelezve, hogy mozogni kell. */
	public void tick() {
		latogat();
	}

	/**
	 * A Sin típusú pozicio attribútum elfogad metódusát hívja meg, saját magát
	 * paraméterül átadva, hogy az elfogad metódus visszatérhessen új pozícióval
	 * (Sin példánnyal).
	 */
	protected void latogat() {
		Sin temp = pozicio;
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
	}

	/**
	 * Visszatér a pozicio-val ami egy Sin objektum, ezzel jelezve, hogy a Jarmu
	 * itt tartozkodik.
	 */
	public Sin getPozicio() {
		return pozicio;
	}

	/**
	 * Visszatér az elozopozicio-val ami egy Sin objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public Sin getElozoPozicio() {
		return elozoPozicio;
	}

	/** Értékül adja a pozicio-t az elozoPozicio-nak, s-et a pozicio-nak. */
	public void setPozicio(Sin s) {
		elozoPozicio = pozicio;
		pozicio = s;
	}

	/** Abstract, a Mozdony és Kocsi-ban ezt implementáljuk. */
	public abstract int getSzin();

}