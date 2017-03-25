package projlab;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Jarmu osztály:
 * 
 * A Mozdony és a Kocsi objektumok õse. Ez egy abstract osztály, ugyanis
 * jármûveket nem példányosítunk, csak kocsikat vagy mozdonyokat. Tároljuk
 * ezeknek a pozíciójukat és az elõzõ pozicíójukat, melyek PalyaElem típusúak.
 * Amelyik PalyaElem-en Jarmu van (tehát a Jarmu poziciója ez a PalyaElem), oda
 * nem lehet alagutat építeni.
 * 
 * Felelõsség:
 * 
 * Ez az objektum felel a mozgó mozdonyok és kocsik csoportosításáért. Az idõ
 * múlásának függvényében (tick) meglátogatja a pozíciójában tárolt PalyaElem
 * példányt, melynek átadja önmagát. Ez visszatér az új pozícióval, így mozog a
 * Jarmu-vünk.
 */
public abstract class Jarmu {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/**
	 * pozicio: Itt tároljuk, hogy a jármû melyik PalyaElem példányon
	 * helyezkedik el.
	 */
	protected PalyaElem pozicio;

	/**
	 * elozoPozicio: Itt tároljuk, az egy ütemmel (tick) korábbi PalyaElem
	 * példányt, amin elhelyezkedett.
	 */
	protected PalyaElem elozoPozicio;

	/** id: Debuggoláshoz, hogy tudjuk kicsoda. */
	private String id;

	/** Konstruktor */
	Jarmu(String id) {
		pozicio = null;
		elozoPozicio = null;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult");
	}

	/** Meghívja a latogat metódust, ezáltal jelezve, hogy mozogni kell. */
	public void tick() {
		logger.log(Level.INFO, this.getID() + ".tick()");
		latogat();
	}

	/**
	 * A latogat metódusunk abstract, melyet külön implementálunk a Mozdony,
	 * Kocsi, Szeneskocsi osztályokban.
	 */
	protected abstract void latogat();

	/**
	 * Visszatér a pozicio-val ami egy PalyaElem objektum, ezzel jelezve, hogy a
	 * Jarmu itt tartozkodik.
	 */
	public PalyaElem getPozicio() {
		logger.log(Level.INFO, this.getID() + ".getPozicio()");
		return pozicio;
	}

	/**
	 * Visszatér az elozopozicio-val ami egy PalyaElem objektum, ezzel jelezve,
	 * hogy a Jarmu itt tartozkodott az elozo idopillanatban.
	 */
	public PalyaElem getElozoPozicio() {
		logger.log(Level.INFO, this.getID() + ".getElozoPozicio()");
		return elozoPozicio;
	}

	/**
	 * Értékül adja a kapott pozíciót (sP) a pozicio-nak, és a kapott elõzõ
	 * pozíciót (sEP) az elozoPozicio-nak. Ezután beállítja az Sp PalyaElem-t
	 * foglaltra, a setFoglalt metódussal.
	 */
	public void setKezdoPoziciok(PalyaElem sP, PalyaElem sEP) {
		logger.log(Level.INFO, this.getID() + ".setKezdoPoziciok(" + sP.getID() + ", " + sEP.getID() + ") felhívva");
		elozoPozicio = sEP;
		pozicio = sP;
		sP.setFoglalt();
	}

	/**
	 * Visszatérünk 0-val, mert a Mozdony és a Szeneskocsi mindig üres. A Kocsi
	 * felüldefiniálja ezt a metódust.
	 */
	public int getSzin() {
		logger.log(Level.INFO, this.getID() + ".getSzin(), visszaadott érték: 0");
		return 0;
	}

	/** Visszatér az id értékével. */
	public String getID() {
		return id;
	}
}