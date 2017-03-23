package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

	// debuggoláshoz, hogy tudjuk kicsoda
	private String id;

	/** Konstruktor */
	Jarmu(String id) {
		logger.log(Level.INFO, "Jarmu paraméter nélküli konstruktor elindult");
		pozicio = null;
		elozoPozicio = null;
		this.id = id;
	}

	/** Meghívja a latogat metódust, ezáltal jelezve, hogy mozogni kell. */
	public void tick() {
		logger.log(Level.INFO, "Jarmu.tick()");
		latogat();
	}

	/**
	 * A Sin típusú pozicio attribútum elfogad metódusát hívja meg, saját magát
	 * paraméterül átadva, hogy az elfogad metódus visszatérhessen új pozícióval
	 * (Sin példánnyal).
	 */
	protected void latogat() {
		logger.log(Level.INFO, "Jarmu.latogat() ezen példány:" + this.getID());
		Sin temp = pozicio;
		logger.log(Level.INFO, "Jarmu.latogat() hivas adatai:" + pozicio.getID());
		pozicio = pozicio.elfogad(this);
		elozoPozicio = temp;
	}

	/**
	 * Visszatér a pozicio-val ami egy Sin objektum, ezzel jelezve, hogy a Jarmu
	 * itt tartozkodik.
	 */
	public Sin getPozicio() {
		logger.log(Level.INFO, "Jarmu.getPozicio(), ezen az elemen:" + this.getID());
		return pozicio;
	}

	/**
	 * Visszatér az elozopozicio-val ami egy Sin objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public Sin getElozoPozicio() {
		logger.log(Level.INFO, "Jarmu.getElozoPozicio() ezen az elemen:" + this.getID());
		return elozoPozicio;
	}

	/**
	 * Értékül adja a kapott pozíciót (sP) a pozicio-nak, és a kapott elõzõ
	 * pozíciót (sEP) az elozoPozicio-nak. Ezután beállítja az Sp Sin-t
	 * foglaltra, a setFoglalt metódussal.
	 */
	public void setKezdoPoziciok(Sin sP, Sin sEP) {
		logger.log(Level.INFO, "Jarmu.setKezdoPoziciok(), beállítandó pozíció: " + sP.getID()
				+ " beallítandó kezdõpozíció: " + sEP.getID() + " ezen a Jarmu-n hívva:" + this.getID());
		elozoPozicio = sEP;
		pozicio = sP;
		sP.setFoglalt();
	}

	/** Abstract, a Mozdony és Kocsi-ban ezt implementáljuk. */
	public abstract int getSzin();

	// debug method
	public String getID() {
		return id;
	}
}