package projlab;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Palya osztály:
 * 
 * Sin, Allomas, Valto objektumokat tárol egy listában, illetve Vonat
 * objektumokat egy másikban. Tárolja, hogy hány vonat tartozik a pályához,
 * mennyi idõközönként(tick) indulnak új vonatok, hány kocsi tartozik a
 * vonatokhoz, és hogy hány alagút van építve éppen a pályán. Ez így megalkot
 * egy pályát.
 * 
 * Felelõsség:
 * 
 * Azért felel, hogy egy pályát fogalmazzon meg a részobjektumok példányainak és
 * a megkapott pályainformációk összegyûjtésével.
 */
public class Palya {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	// Attribútumok:

	/** elemek: Itt tároljuk, hogy mely Sin példányok tartoznak a pályához. */
	private ArrayList<Sin> elemek;

	/**
	 * vonatok: Itt tároljuk, hogy mely Vonat példányok tartoznak a pályához.
	 */
	private ArrayList<Vonat> vonatok;

	/**
	 * vonatSzam: Tartalmazza, hogy az adott pályához hány vonat tartozik, ennyi
	 * vonatot kell majd indítani.
	 */
	private int vonatSzam;

	/**
	 * kocsiSzam: Tartalmazza, hogy hány kocsi tartozik ezekhez a vonatokhoz.
	 */
	private int kocsiSzam;

	/**
	 * alagutSzam: Itt tároljuk, hogy az elemek-ben hány Sin példánynak igaz az
	 * alagut változója.
	 */
	private int alagutSzam;

	/**
	 * keslelteto: Azt tároljuk itt, hogy a pálya mennyi idõközönként(tick)
	 * enged be új vonatot.
	 */
	private int keslelteto;

	/** Debuggoláshoz, hogy tudjuk kicsoda. */
	private String id;

	/**
	 * Konstruktor, a paraméterül kapott Sin példányoknak beállítjuk, hogy ehhez
	 * a Palya-hoz tartoznak.
	 */
	Palya(int vSz, int kSz, int k, ArrayList<Sin> e, ArrayList<Vonat> v, String id) {
		elemek = e;
		for (Sin sin : elemek)
			sin.setPalya(this);
		vonatok = v;
		vonatSzam = vSz;
		kocsiSzam = kSz;
		keslelteto = k;
		alagutSzam = 0;
		this.id = id;
		logger.log(Level.INFO, id + " konstruktora elindult.\nparaméterei: vonatSzam = " + vSz + " kocsiSzam = " + kSz
				+ " keslelteto = " + k);
	}

	/** Visszatér a vonatSzam értékével. */
	public int getVonatSzam() {
		logger.log(Level.INFO, this.getID() + ".getVonatSzam()");
		return vonatSzam;
	}

	/** Visszatér a kocsiSzam értékével. */
	public int getKocsiSzam() {
		logger.log(Level.INFO, this.getID() + ".getKocsiSzam()");
		return kocsiSzam;
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
	public ArrayList<Sin> getElemek() {
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
	 * olyan Sin példányt, aminek az alagut attribútumának értéke igaz. Ezután
	 * végig megy az elemek listán, és a getAlagut metódussal alagutakat keres.
	 * Ha talál, és az nem a paraméterül kapott Sin, visszatér vele.
	 */
	public Sin alagut(Sin s) {
		logger.log(Level.INFO, this.getID() + ".alagut(" + s.getID() + ")");
		for (Sin sin : elemek) {
			if (sin.getAlagut() == true && sin != s)
				return sin;
		}

		// this should never happen
		return null;
	}

	/** Visszatér az id értékével. */
	public String getID() {
		return id;
	}

}
