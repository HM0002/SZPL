package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Palya osztály:
 * 
 * PalyaElem objektumokat tárol egy listában, illetve Vonat objektumokat egy
 * másikban. Tárolja, hogy hány vonat tartozik a pályához, mennyi
 * idõközönként(tick) indulnak új vonatok, hány kocsi tartozik a vonatokhoz, és
 * hogy hány alagút van építve éppen a pályán. Ez így megalkot egy pályát.
 * 
 * Felelõsség:
 * 
 * Azért felel, hogy egy pályát fogalmazzon meg a részobjektumok példányainak és
 * a megkapott pályainformációk összegyûjtésével.
 */
public class Palya {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/**
	 * elemek: Itt tároljuk, hogy mely PalyaElem példányok tartoznak a pályához.
	 */
	private ArrayList<PalyaElem> elemek;

	/**
	 * elemek képe: Itt tároljuk, hogy melyik ID-jû pályaelem hol helyezkedik el
	 * az aktuális pályán.
	 */
	private String[][] palyaKep;

	/**
	 * vonatok: Itt tároljuk, hogy mely Vonat példányok tartoznak a pályához.
	 */
	private ArrayList<Vonat> vonatok;

	/**
	 * alagutSzam: Itt tároljuk, hogy az elemek-ben hány PalyaElem példánynak
	 * igaz az alagut változója.
	 */
	private int alagutSzam;

	/**
	 * keslelteto: Azt tároljuk itt, hogy a pálya mennyi idõközönként(tick)
	 * enged be új vonatot.
	 */
	private int keslelteto;

	/** id: Debuggoláshoz, hogy tudjuk kicsoda. */
	private String id;

	/**
	 * Konstruktor, a paraméterül kapott PalyaElem példányoknak beállítjuk, hogy
	 * ehhez a Palya-hoz tartoznak.
	 */
	Palya(int k, ArrayList<PalyaElem> e, ArrayList<Vonat> v, String[][] kep, String id) {
		elemek = e;
		for (PalyaElem pe : elemek)
			pe.setPalya(this);
		vonatok = v;
		keslelteto = k;
		alagutSzam = 0;
		palyaKep = kep;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult.\nparaméterei: keslelteto = " + k);
	}

	/** Visszatér az alagutSzam értékével. */
	public int getAlagutSzam() {
		logger.log(Level.INFO, this.getID() + ".getAlagutSzam(), visszaadott érték: " + alagutSzam);
		return alagutSzam;
	}

	/** Hozzáad i-t az alagutSzam értékéhez. */
	public void setAlagutSzam(int i) {
		logger.log(Level.INFO, this.getID() + ".setAlagutSzam(" + i + ")");
		alagutSzam += i;
	}

	/** Visszatér a keslelteto értékével. */
	public int getKeslelteto() {
		logger.log(Level.INFO, this.getID() + ".getKeslelteto()");
		return keslelteto;
	}

	/** Visszatér az elemek listával, ha az nem üres. Ha igen, akkor null-al. */
	public ArrayList<PalyaElem> getElemek() {
		logger.log(Level.INFO, this.getID() + ".getElemek()");
		if (elemek.isEmpty())
			// throw exception
			return null;
		else
			return elemek;
	}

	/**
	 * Visszatér az vonatok listával, ha az nem üres. Ha igen, akkor null-al.
	 */
	public ArrayList<Vonat> getVonatok() {
		logger.log(Level.INFO, this.getID() + ".getVonatok()");
		if (vonatok.isEmpty())
			// throw exception
			return null;
		else
			return vonatok;
	}

	/**
	 * Csak akkor van meghívva, ha 2 alagút van a pályán. Paraméterül kap egy
	 * olyan PalyaElem példányt, aminek az alagut attribútumának értéke igaz.
	 * Ezután végig megy az elemek listán, és a getAlagut metódussal alagutakat
	 * keres. Ha talál, és az nem a paraméterül kapott PalyaElem, visszatér
	 * vele.
	 */
	public PalyaElem alagut(PalyaElem pe) {
		logger.log(Level.INFO, this.getID() + ".alagut(" + pe.getID() + ")");
		for (PalyaElem e : elemek) {
			if (e.getAlagut() == true && e != pe)
				return e;
		}

		// this should never happen
		return null;
	}

	/** Visszatér az id értékével. */
	public String getID() {
		return id;
	}
	
	/** Visszatér a pályakép értékével. */
	public String[][] getPalyaKep() {
		return palyaKep;
	}
	

}
